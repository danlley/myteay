/**
 * Myteay.com Inc.
 * Copyright (c) 2015-2016 All Rights Reserved.
 */
package com.myteay.core.model.user.validators;

import com.myteay.common.service.facade.exceptions.MtBizException;
import com.myteay.common.service.facade.model.MtUserMessage;
import com.myteay.core.model.user.MtUserModel;

/**
 * 校验用户模型相关信息
 * 
 * @author Administrator
 * @version $Id: MtUserModelValidator.java, v 0.1 2016年2月27日 下午11:20:07 Administrator Exp $
 */
public class MtUserModelValidator {

    /**
     * 校验用户注册模型
     * 
     * @param model             用户注册模型
     * @throws MtBizException   系统业务异常
     */
    public static void validateUserRegModel(MtUserModel model) throws MtBizException {
        //TODO
    }

    /**
     * 验证对外提供的用户信息是否正确
     * 
     * @param message           用户交互信息
     * @throws MtBizException   系统异常
     */
    public static void validateUserMessage(MtUserMessage message) throws MtBizException {
        //TODO
    }
}
