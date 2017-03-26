/**
 * Myteay.com Inc.
 * Copyright (c) 2015-2015 All Rights Reserved.
 */
package com.myteay.common.dal.ibatis;

import com.myteay.common.dal.daointerface.UsersSecurityInfoDAO;
import com.myteay.common.dal.dataobject.UsersSecurityInfoDO;
import com.myteay.common.utils.orm.ibatis.support.MtSqlMapClientDaoSupport;

/**
 * 用户基本信息操作DAO
 * 
 * @author Administrator
 * @version $Id: IbatisUsersInfoDAO.java, v 0.1 2015年11月15日 下午4:37:30 Administrator Exp $
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
