/**
 * Myteay.com Inc.
 * Copyright (c) 2015-2015 All Rights Reserved.
 */
package com.myteay.common.dal.ibatis;

import com.myteay.common.dal.daointerface.UsersInfoDAO;
import com.myteay.common.dal.dataobject.UsersInfoDO;
import com.myteay.common.utils.orm.ibatis.support.MtSqlMapClientDaoSupport;

/**
 * 用户基本信息操作DAO
 * 
 * @author Administrator
 * @version $Id: IbatisUsersInfoDAO.java, v 0.1 2015年11月15日 下午4:37:30 Administrator Exp $
 */
@SuppressWarnings("deprecation")
public class IbatisUsersInfoDAO extends MtSqlMapClientDaoSupport implements UsersInfoDAO {

    /** 
     * @see com.myteay.common.dal.daointerface.UsersInfoDAO#insert(com.myteay.common.dal.dataobject.UsersInfoDO)
     */
    @Override
    public String insert(UsersInfoDO usersInfoDO) {
        if (usersInfoDO == null) {
            throw new IllegalArgumentException("Can't insert a null data object into db.");
        }

        getSqlMapClientTemplate().insert("MS-USER-INFO-INSERT", usersInfoDO);

        return usersInfoDO.getId();
    }

    /** 
     * @see com.myteay.common.dal.daointerface.UsersInfoDAO#getById(java.lang.String)
     */
    @Override
    public UsersInfoDO getById(String userId) {
        return (UsersInfoDO) getSqlMapClientTemplate().queryForObject("MS-MT-USER-INFO-GET-BY-ID",
            userId);
    }

    /** 
     * @see com.myteay.common.dal.daointerface.UsersInfoDAO#updateQrCode(com.myteay.common.dal.dataobject.UsersInfoDO)
     */
    @Override
    public void updateQrCode(UsersInfoDO usersInfoDO) {
        if (usersInfoDO == null) {
            throw new IllegalArgumentException("Can't update by a null data object.");
        }

        getSqlMapClientTemplate().update("MS-MT-USER-UPDATE-QRCODE-BY-USERID", usersInfoDO);
    }

}