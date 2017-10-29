/**
 * Myteay.com Inc.
 * Copyright (c) 2015-2015 All Rights Reserved.
 */
package com.myteay.common.dal.daointerface;

import com.myteay.common.dal.dataobject.UsersSecurityInfoDO;
import com.myteay.common.util.constants.MtDBKey;

/**
 * �û���֤��Ϣ����DAO
 * 
 * @author Administrator
 * @version $Id: UsersSecurityInfoDAO.java, v 0.1 2015��12��1�� ����3:44:08 Administrator Exp $
 */
public interface UsersSecurityInfoDAO extends MtDBKey {

    /**
     * д���û���֤��Ϣ
     * 
     * @param usersSecurityInfoDO
     * @return
     */
    public String insert(UsersSecurityInfoDO usersSecurityInfoDO);
}
