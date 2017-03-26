/**
 * Myteay.com Inc.
 * Copyright (c) 2015-2016 All Rights Reserved.
 */
package com.myteay.core.model.user.validators;

import com.myteay.common.dal.dataobject.UsersInfoDO;
import com.myteay.common.dal.dataobject.UsersSecurityInfoDO;
import com.myteay.common.utils.exception.MtException;

/**
 * 用户数据模型校验器
 * 
 * @author Administrator
 * @version $Id: MtUserDOValidator.java, v 0.1 2016年2月28日 上午1:33:02 Administrator Exp $
 */
public class MtUserDOValidator {

    /**
     * 用户安全数据模型合法性校验
     * 
     * @param usersSecurityInfoDO   用户安全数据模型
     * @throws MtException          系统内部异常
     */
    public static void validateUsersSecurityInfoDO(UsersSecurityInfoDO usersSecurityInfoDO)
                                                                                           throws MtException {
        //TODO
    }

    /**
     * 用户基本信息数据模型合法性校验
     * 
     * @param usersInfoDO   用户基本数据模型
     * @throws MtException  系统内部异常
     */
    public static void validateUsersInfoDO(UsersInfoDO usersInfoDO) throws MtException {
        //TODO
    }

}
