/**
 * Myteay.com Inc.
 * Copyright (c) 2015-2015 All Rights Reserved.
 */
package com.myteay.common.dal.daointerface;

import com.myteay.common.dal.dataobject.UsersSecurityInfoDO;
import com.myteay.common.dal.utils.MtDBKey;

/**
 * 用户认证信息操作DAO
 * 
 * @author Administrator
 * @version $Id: UsersSecurityInfoDAO.java, v 0.1 2015年12月1日 下午3:44:08 Administrator Exp $
 */
public interface UsersSecurityInfoDAO extends MtDBKey {

    /**
     * 写入用户认证信息
     * 
     * @param usersSecurityInfoDO
     * @return
     */
    public String insert(UsersSecurityInfoDO usersSecurityInfoDO);
}
