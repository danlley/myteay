/**
 * Myteay.com Inc.
 * Copyright (c) 2015-2015 All Rights Reserved.
 */
package com.myteay.common.dal.ibatis;

import com.myteay.common.dal.daointerface.UsersSecurityInfoDAO;
import com.myteay.common.dal.dataobject.UsersSecurityInfoDO;
import com.myteay.common.utils.orm.ibatis.support.MtSqlMapClientDaoSupport;

/**
 * �û�������Ϣ����DAO
 * 
 * @author Administrator
 * @version $Id: IbatisUsersInfoDAO.java, v 0.1 2015��11��15�� ����4:37:30 Administrator Exp $
 */
public class IbatisUsersSecurityInfoDAO extends MtSqlMapClientDaoSupport implements
                                                                        UsersSecurityInfoDAO {

    /** 
     * @see com.myteay.common.dal.daointerface.UsersInfoDAO#insert(com.myteay.common.dal.dataobject.UsersInfoDO)
     */
    @SuppressWarnings("deprecation")
    @Override
    public String insert(UsersSecurityInfoDO usersSecurityInfoDO) {
        if (usersSecurityInfoDO == null) {
            throw new IllegalArgumentException("Can't insert a null data object into db.");
        }

        getSqlMapClientTemplate().insert("MS-USER-SECURITY-INFO-INSERT", usersSecurityInfoDO);

        return usersSecurityInfoDO.getUserId();
    }
}
