/**
 * Myteay.com Inc.
 * Copyright (c) 2015-2016 All Rights Reserved.
 */
package com.myteay.common.dal.ibatis;

import java.util.List;

import com.myteay.common.dal.daointerface.UsersQrCodeWfInfoDAO;
import com.myteay.common.dal.dataobject.UsersQrCodeWfInfoDO;
import com.myteay.common.util.comm.StringUtils;
import com.myteay.common.utils.orm.ibatis.support.MtSqlMapClientDaoSupport;

/**
 * 二维码生成流水操作DAO
 * 
 * @author Administrator
 * @version $Id: IbatisUsersQrCodeWfInfoDAO.java, v 0.1 2016年9月7日 上午1:30:37 Administrator Exp $
 */
@SuppressWarnings({ "deprecation", "unchecked" })
public class IbatisUsersQrCodeWfInfoDAO extends MtSqlMapClientDaoSupport implements
                                                                        UsersQrCodeWfInfoDAO {

    /** 
     * @see com.myteay.common.dal.daointerface.UsersQrCodeWfInfoDAO#insert(com.myteay.common.dal.dataobject.UsersQrCodeWfInfoDO)
     */
    @Override
    public String insert(UsersQrCodeWfInfoDO usersQrCodeWfInfoDO) {
        if (usersQrCodeWfInfoDO == null) {
            throw new IllegalArgumentException("Can't insert a null data object into db.");
        }

        getSqlMapClientTemplate().insert("MS-USER-QR-WF-INFO-INSERT", usersQrCodeWfInfoDO);

        return usersQrCodeWfInfoDO.getId();
    }

    /** 
     * @see com.myteay.common.dal.daointerface.UsersQrCodeWfInfoDAO#getByLimit()
     */
    @Override
    public List<UsersQrCodeWfInfoDO> getByLimit() {
        return getSqlMapClientTemplate().queryForList("MS-MT-USER-QR-WFINFO-GET-BY-LIMIT");
    }

    /** 
     * @see com.myteay.common.dal.daointerface.UsersQrCodeWfInfoDAO#getById(java.lang.String)
     */
    @Override
    public UsersQrCodeWfInfoDO getById(String userId) {

        if (StringUtils.isBlank(userId)) {
            throw new IllegalArgumentException("userId is empty.");
        }

        return (UsersQrCodeWfInfoDO) getSqlMapClientTemplate().queryForObject(
            "MS-MT-USER-QR-WFINFO-GET-BY-ID", userId);
    }

    /** 
     * @see com.myteay.common.dal.daointerface.UsersQrCodeWfInfoDAO#getByIdForUpdate(java.lang.String)
     */
    @Override
    public UsersQrCodeWfInfoDO getByIdForUpdate(String userId) {

        if (StringUtils.isBlank(userId)) {
            throw new IllegalArgumentException("userId is empty.");
        }

        return (UsersQrCodeWfInfoDO) getSqlMapClientTemplate().queryForObject(
            "MS-MT-USER-QR-WFINFO-GET-BY-ID-FOR-UPDATE", userId);
    }

    /** 
     * @see com.myteay.common.dal.daointerface.UsersQrCodeWfInfoDAO#updateDateByUserid(java.lang.String)
     */
    @Override
    public void updateDateByUserid(String userId) {

        if (StringUtils.isBlank(userId)) {
            throw new IllegalArgumentException("userId is empty.");
        }

        getSqlMapClientTemplate().update("MS-MT-USER-QR-WFINFO-UPDATE-BY-USERID", userId);
    }

    /** 
     * @see com.myteay.common.dal.daointerface.UsersQrCodeWfInfoDAO#delete(java.lang.String)
     */
    @Override
    public int delete(String userId) {

        if (StringUtils.isBlank(userId)) {
            throw new IllegalArgumentException("userId is empty.");
        }

        return getSqlMapClientTemplate().delete("MP-SEC-CARD-LOG-DELETE", userId);
    }

}
