/**
 * Myteay.com Inc.
 * Copyright (c) 2015-2016 All Rights Reserved.
 */
package com.myteay.core.service.cache.listeners;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

import com.myteay.common.async.event.EventListener;
import com.myteay.common.async.event.MtEvent;
import com.myteay.common.service.facade.enums.MtOperateExResultEnum;
import com.myteay.common.service.facade.enums.MtProcessManageTypeEnum;
import com.myteay.common.service.facade.model.MtUserRegQRCodeMessage;
import com.myteay.common.util.comm.LoggerNames;
import com.myteay.common.util.log.Logger;
import com.myteay.common.util.log.LoggerFactory;
import com.myteay.common.util.qrcode.QRCodeUtil;
import com.myteay.common.utils.exception.MtBizProcessException;
import com.myteay.core.model.repository.MtUsersInfoRepository;
import com.myteay.core.model.repository.MtUsersQrWfRepository;
import com.myteay.core.service.manage.template.MtManageCallback;
import com.myteay.core.service.manage.template.MtOperateManageTemplate;

/**
 * 为缩短注册接口的响应时间，用户注册之后的 二维码身份信息生成过程采用异步的方式放在这个异步监听器中实现
 * 
 * @author Administrator
 * @version $Id: MtUserQRCodeEventListener.java, v 0.1 2016年9月6日 下午9:00:07 Administrator Exp $
 */
public class MtUserQRCodeEventListener extends EventListener<Object> {

    /** 日志 */
    private static final Logger     loggerDigester = LoggerFactory
        .getLogger(LoggerNames.MT_USR_REG_APPENDER);

    /** 用户二维码生成操作流水 */
    @Autowired
    private MtUsersQrWfRepository   mtUsersQrWfRepository;

    /** 用户信息仓储类 */
    @Autowired
    private MtUsersInfoRepository   mtUsersInfoRepository;

    /** 操作执行模板 */
    @Autowired
    private MtOperateManageTemplate mtOperateManageTemplate;

    /** 
     * @see com.myteay.common.util.event.EventListener#handleEvent(com.myteay.common.util.event.MtEvent)
     */
    @Override
    public Object handleEvent(MtEvent<?> event) {
        if (logger.isInfoEnabled()) {
            logger.info("收到生成用户二维码信息请求， event=" + event);
        }

        if (!(event.getData() instanceof MtUserRegQRCodeMessage)) {
            logger.error("生成用户二维码信息时，上层模块交互信息不合法 event=" + event);
            return null;
        }

        //step 1: 保存请求流水(保存流水不在事务中，否则遇到错误情况时，会造成保存数据流水回滚)
        final MtUserRegQRCodeMessage msg = (MtUserRegQRCodeMessage) event.getData();
        if (logger.isInfoEnabled()) {
            logger.info("开始保存二维码操作流水 msg=" + msg);
        }
        try {
            mtUsersQrWfRepository.save(msg.getContent());
        } catch (MtBizProcessException e) {
            logger.error("保存生成二维码请求流水 msg=" + msg, e);

            //出现异常情况，则流程不再继续往下执行
            return null;
        }

        //step 2: 前推二维码生成流水最后修改时间，此步放在事务外执行，确保能够成功
        try {
            mtUsersQrWfRepository.updateLasttime(msg.getContent());
        } catch (MtBizProcessException e) {
            logger.error("前推二维码生成流水最后修改时间时，出现异常，流程无法继续执行。msg=" + msg, e);
            //出现异常情况，则流程不再继续往下执行
            return null;
        }

        final MtProcessManageTypeEnum manageType = msg.getManageType();
        mtOperateManageTemplate.execute(manageType, msg, new MtManageCallback() {
            /** 
             * @see com.myteay.core.service.manage.template.MtManageCallback#beforeProcess()
             */
            public MtProcessManageTypeEnum beforeProcess() throws MtBizProcessException {

                //锁定流水
                mtUsersQrWfRepository.lock(msg.getContent());

                //最后指明变更流程指向执行辅助流程
                return MtProcessManageTypeEnum.CS_AUXILIARY_PROCESS;
            }

            /** 
             * @see com.myteay.core.service.manage.template.MtManageCallback#auxiliaryProcess()
             */
            public MtProcessManageTypeEnum auxiliaryProcess() throws MtBizProcessException {
                //step 2: 执行生成二维码动作
                if (logger.isInfoEnabled()) {
                    logger.info("执行生成二维码动作  msg=" + msg);
                }

                String filename = null;
                try {
                    filename = QRCodeUtil.encode(msg.getContent(), msg.getDefaultImg(), true,
                        msg.getStorePath());
                } catch (Exception e) {
                    logger.warn("生成二维码失败，当前事务处理失败 " + e.getMessage(), e);
                    throw new MtBizProcessException(MtOperateExResultEnum.CAMP_QRCODE_EXE_FAILED
                        .getCode(), MtOperateExResultEnum.CAMP_QRCODE_EXE_FAILED.getMessage());
                }

                if (StringUtils.isBlank(filename)) {
                    throw new MtBizProcessException(MtOperateExResultEnum.CAMP_QRCODE_EXE_FAILED
                        .getCode(), MtOperateExResultEnum.CAMP_QRCODE_EXE_FAILED.getMessage());
                }

                //将二维码信息放入二维码信息交互模型
                msg.setFilename(filename);

                //变更流程指向
                return MtProcessManageTypeEnum.CS_MAIN_PROCESS;
            }

            /** 
             * @see com.myteay.core.service.manage.template.MtManageCallback#process()
             */
            public MtProcessManageTypeEnum process() throws MtBizProcessException {
                //step 3: 将二维码信息同步到用户数据中
                if (logger.isInfoEnabled()) {
                    logger.info("将二维码信息同步到用户数据中  msg=" + msg);
                }

                if (StringUtils.isBlank(msg.getFilename())) {
                    logger.warn("二维码信息交互模型中的二维码文件信息不可用 msg=" + msg);
                    throw new MtBizProcessException(
                        MtOperateExResultEnum.CAMP_QRCODE_FILENAME_FAILED.getCode(),
                        MtOperateExResultEnum.CAMP_QRCODE_FILENAME_FAILED.getMessage());
                }

                //执行更新动作
                mtUsersInfoRepository.updateUserQrcode(msg);

                //变更流程
                return MtProcessManageTypeEnum.CS_AFTER_PROCESS;
            }

            /** 
             * @see com.myteay.core.service.manage.template.MtManageCallback#afterProcess()
             */
            public MtProcessManageTypeEnum afterProcess() throws MtBizProcessException {

                //step 4: 清理二维码流水
                if (logger.isInfoEnabled()) {
                    logger.info("执行生成二维码动作  msg=" + msg);
                }

                mtUsersQrWfRepository.removeQrcode(msg.getContent());

                //变更流程指向
                return MtProcessManageTypeEnum.CS_END;
            }
        });

        return null;
    }

    /**
     * Setter method for property <tt>mtUsersQrWfRepository</tt>.
     * 
     * @param mtUsersQrWfRepository value to be assigned to property mtUsersQrWfRepository
     */
    public void setMtUsersQrWfRepository(MtUsersQrWfRepository mtUsersQrWfRepository) {
        this.mtUsersQrWfRepository = mtUsersQrWfRepository;
    }

    /**
     * Setter method for property <tt>mtOperateManageTemplate</tt>.
     * 
     * @param mtOperateManageTemplate value to be assigned to property mtOperateManageTemplate
     */
    public void setMtOperateManageTemplate(MtOperateManageTemplate mtOperateManageTemplate) {
        this.mtOperateManageTemplate = mtOperateManageTemplate;
    }

    /**
     * Setter method for property <tt>mtUsersInfoRepository</tt>.
     * 
     * @param mtUsersInfoRepository value to be assigned to property mtUsersInfoRepository
     */
    public void setMtUsersInfoRepository(MtUsersInfoRepository mtUsersInfoRepository) {
        this.mtUsersInfoRepository = mtUsersInfoRepository;
    }
}
