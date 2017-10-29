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
 * Ϊ����ע��ӿڵ���Ӧʱ�䣬�û�ע��֮��� ��ά�������Ϣ���ɹ��̲����첽�ķ�ʽ��������첽��������ʵ��
 * 
 * @author Administrator
 * @version $Id: MtUserQRCodeEventListener.java, v 0.1 2016��9��6�� ����9:00:07 Administrator Exp $
 */
public class MtUserQRCodeEventListener extends EventListener<Object> {

    /** ��־ */
    private static final Logger     loggerDigester = LoggerFactory
        .getLogger(LoggerNames.MT_USR_REG_APPENDER);

    /** �û���ά�����ɲ�����ˮ */
    @Autowired
    private MtUsersQrWfRepository   mtUsersQrWfRepository;

    /** �û���Ϣ�ִ��� */
    @Autowired
    private MtUsersInfoRepository   mtUsersInfoRepository;

    /** ����ִ��ģ�� */
    @Autowired
    private MtOperateManageTemplate mtOperateManageTemplate;

    /** 
     * @see com.myteay.common.util.event.EventListener#handleEvent(com.myteay.common.util.event.MtEvent)
     */
    @Override
    public Object handleEvent(MtEvent<?> event) {
        if (logger.isInfoEnabled()) {
            logger.info("�յ������û���ά����Ϣ���� event=" + event);
        }

        if (!(event.getData() instanceof MtUserRegQRCodeMessage)) {
            logger.error("�����û���ά����Ϣʱ���ϲ�ģ�齻����Ϣ���Ϸ� event=" + event);
            return null;
        }

        //step 1: ����������ˮ(������ˮ���������У����������������ʱ������ɱ���������ˮ�ع�)
        final MtUserRegQRCodeMessage msg = (MtUserRegQRCodeMessage) event.getData();
        if (logger.isInfoEnabled()) {
            logger.info("��ʼ�����ά�������ˮ msg=" + msg);
        }
        try {
            mtUsersQrWfRepository.save(msg.getContent());
        } catch (MtBizProcessException e) {
            logger.error("�������ɶ�ά��������ˮ msg=" + msg, e);

            //�����쳣����������̲��ټ�������ִ��
            return null;
        }

        //step 2: ǰ�ƶ�ά��������ˮ����޸�ʱ�䣬�˲�����������ִ�У�ȷ���ܹ��ɹ�
        try {
            mtUsersQrWfRepository.updateLasttime(msg.getContent());
        } catch (MtBizProcessException e) {
            logger.error("ǰ�ƶ�ά��������ˮ����޸�ʱ��ʱ�������쳣�������޷�����ִ�С�msg=" + msg, e);
            //�����쳣����������̲��ټ�������ִ��
            return null;
        }

        final MtProcessManageTypeEnum manageType = msg.getManageType();
        mtOperateManageTemplate.execute(manageType, msg, new MtManageCallback() {
            /** 
             * @see com.myteay.core.service.manage.template.MtManageCallback#beforeProcess()
             */
            public MtProcessManageTypeEnum beforeProcess() throws MtBizProcessException {

                //������ˮ
                mtUsersQrWfRepository.lock(msg.getContent());

                //���ָ���������ָ��ִ�и�������
                return MtProcessManageTypeEnum.CS_AUXILIARY_PROCESS;
            }

            /** 
             * @see com.myteay.core.service.manage.template.MtManageCallback#auxiliaryProcess()
             */
            public MtProcessManageTypeEnum auxiliaryProcess() throws MtBizProcessException {
                //step 2: ִ�����ɶ�ά�붯��
                if (logger.isInfoEnabled()) {
                    logger.info("ִ�����ɶ�ά�붯��  msg=" + msg);
                }

                String filename = null;
                try {
                    filename = QRCodeUtil.encode(msg.getContent(), msg.getDefaultImg(), true,
                        msg.getStorePath());
                } catch (Exception e) {
                    logger.warn("���ɶ�ά��ʧ�ܣ���ǰ������ʧ�� " + e.getMessage(), e);
                    throw new MtBizProcessException(MtOperateExResultEnum.CAMP_QRCODE_EXE_FAILED
                        .getCode(), MtOperateExResultEnum.CAMP_QRCODE_EXE_FAILED.getMessage());
                }

                if (StringUtils.isBlank(filename)) {
                    throw new MtBizProcessException(MtOperateExResultEnum.CAMP_QRCODE_EXE_FAILED
                        .getCode(), MtOperateExResultEnum.CAMP_QRCODE_EXE_FAILED.getMessage());
                }

                //����ά����Ϣ�����ά����Ϣ����ģ��
                msg.setFilename(filename);

                //�������ָ��
                return MtProcessManageTypeEnum.CS_MAIN_PROCESS;
            }

            /** 
             * @see com.myteay.core.service.manage.template.MtManageCallback#process()
             */
            public MtProcessManageTypeEnum process() throws MtBizProcessException {
                //step 3: ����ά����Ϣͬ�����û�������
                if (logger.isInfoEnabled()) {
                    logger.info("����ά����Ϣͬ�����û�������  msg=" + msg);
                }

                if (StringUtils.isBlank(msg.getFilename())) {
                    logger.warn("��ά����Ϣ����ģ���еĶ�ά���ļ���Ϣ������ msg=" + msg);
                    throw new MtBizProcessException(
                        MtOperateExResultEnum.CAMP_QRCODE_FILENAME_FAILED.getCode(),
                        MtOperateExResultEnum.CAMP_QRCODE_FILENAME_FAILED.getMessage());
                }

                //ִ�и��¶���
                mtUsersInfoRepository.updateUserQrcode(msg);

                //�������
                return MtProcessManageTypeEnum.CS_AFTER_PROCESS;
            }

            /** 
             * @see com.myteay.core.service.manage.template.MtManageCallback#afterProcess()
             */
            public MtProcessManageTypeEnum afterProcess() throws MtBizProcessException {

                //step 4: �����ά����ˮ
                if (logger.isInfoEnabled()) {
                    logger.info("ִ�����ɶ�ά�붯��  msg=" + msg);
                }

                mtUsersQrWfRepository.removeQrcode(msg.getContent());

                //�������ָ��
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
