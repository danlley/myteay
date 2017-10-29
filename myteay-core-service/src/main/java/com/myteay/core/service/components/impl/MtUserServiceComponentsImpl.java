/**
 * Myteay.com Inc.
 * Copyright (c) 2015-2016 All Rights Reserved.
 */
package com.myteay.core.service.components.impl;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import com.myteay.common.service.facade.enums.MtOperateExResultEnum;
import com.myteay.common.service.facade.enums.MtOperateResultEnum;
import com.myteay.common.service.facade.exceptions.MtBizException;
import com.myteay.common.service.facade.mobile.info.MtRegisterInfo;
import com.myteay.common.service.facade.model.MtOperateResult;
import com.myteay.common.service.facade.model.MtUserMessage;
import com.myteay.common.util.exception.MtException;
import com.myteay.core.model.repository.MtUsersInfoRepository;
import com.myteay.core.model.user.MtUserModel;
import com.myteay.core.model.user.convt.MtUserModelConverter;
import com.myteay.core.model.user.validators.MtUserModelValidator;
import com.myteay.core.service.components.MtUserServiceComponents;

/**
 * 用户处理核心服务实现
 * 
 * @author Administrator
 * @version $Id: MtUserServiceComponentsImpl.java, v 0.1 2016年2月27日 下午11:07:02 Administrator Exp $
 */
public class MtUserServiceComponentsImpl implements MtUserServiceComponents {

    /** 日志 */
    public static final Logger    logger = Logger.getLogger(MtUserServiceComponentsImpl.class);

    /** 用户信息仓储 */
    @Autowired
    private MtUsersInfoRepository mtUsersInfoRepository;

    /** 
     * @see com.myteay.core.service.components.MtUserServiceComponents#userRegistery(com.myteay.common.service.facade.mobile.info.MtRegisterInfo)
     */
    @Override
    public MtOperateResult<String> userRegistery(MtRegisterInfo registerInfo) throws MtBizException {
        if (registerInfo == null) {
            logger.warn("MtUserMessage is null, user registery flow is failed!");
            return null;
        }

        //模型转换及校验
        MtUserModel model = MtUserModelConverter.genUserRegModelFromMessage(registerInfo);
        MtUserModelValidator.validateUserRegModel(model);

        MtOperateResult<String> resultInner = null;
        try {
            resultInner = mtUsersInfoRepository.saveUserInfoModel(model);
        } catch (MtException e) {
            logger.warn("保存用户注册信息失败 model=" + model, e);
            return null;
        }

        return resultInner;
    }

    /** 
     * @see com.myteay.core.service.components.MtUserServiceComponents#findSingleUserInfo(java.lang.String)
     */
    @Override
    public MtOperateResult<MtUserMessage> findSingleUserInfo(String userId) throws MtBizException {

        if (logger.isInfoEnabled()) {
            logger.info("单个用户信息查询 userId = " + userId);
        }

        MtOperateResult<MtUserModel> resultInner = null;
        try {
            resultInner = mtUsersInfoRepository.getUserInfoModelById(userId);
        } catch (Throwable e) {
            logger.warn("查询用户信息失败， result=" + resultInner, e);
        }

        if (logger.isInfoEnabled()) {
            logger.info("查询单个用户信息结束 userId=" + userId + " result=" + resultInner);
        }

        if (resultInner == null || resultInner.getResult() == null) {
            logger.warn("未查询到指定用户信息 uid=" + userId + " result=" + resultInner);
            throw new MtBizException("未查询到指定用户信息 uid=" + userId + " result=" + resultInner);
        }

        MtOperateResult<MtUserMessage> result = new MtOperateResult<MtUserMessage>();
        //convert model
        //MtUserMessage message = MtUserModelConverter.convertModel2Msg(resultInner.getResult());

        //validate message
        //        MtUserModelValidator.validateUserMessage(message);
        //
        //        result.setResult(message);
        result.setOperateExResult(MtOperateExResultEnum.CAMP_OPERATE_SUCCESS);
        result.setOperateResult(MtOperateResultEnum.CAMP_OPERATE_SUCCESS);
        return result;
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
