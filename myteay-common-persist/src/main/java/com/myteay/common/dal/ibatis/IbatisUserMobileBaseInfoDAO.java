/**
 * Myteay.com Inc.
 * Copyright (c) 2005-2017 All Rights Reserved.
 */
package com.myteay.common.dal.ibatis;

import java.util.HashMap;
import java.util.Map;

import com.myteay.common.dal.daointerface.UserMobileBaseInfoDAO;
import com.myteay.common.dal.dataobject.UserMobileBaseInfoDO;
import com.myteay.common.dal.utils.MtSqlSessionDaoSupport;

/**
 * 用户手机基本信息操作DAO
 * 
 * @author danlley
 * @version $Id: IbatisUserMobileBaseInfoDAO.java, v 0.1 Sep 3, 2017 5:21:45 PM danlley Exp $
 */
public class IbatisUserMobileBaseInfoDAO extends MtSqlSessionDaoSupport implements UserMobileBaseInfoDAO {

    /** 
     * @see com.myteay.common.dal.daointerface.UserMobileBaseInfoDAO#insert(com.myteay.common.dal.dataobject.UserMobileBaseInfoDO)
     */
    @Override
    public String insert(UserMobileBaseInfoDO userMobileBaseInfoDO) {
        if (userMobileBaseInfoDO == null) {
            throw new IllegalArgumentException("Can't insert a null data object into db.");
        }

        this.getSqlSession(customer).insert("MS-USER-MOBILE-BASE-INFO-INSERT", userMobileBaseInfoDO);

        return userMobileBaseInfoDO.getId();
    }

    /** 
     * @see com.myteay.common.dal.daointerface.UserMobileBaseInfoDAO#queryUserMobileBaseInfo(com.myteay.common.dal.dataobject.UserMobileBaseInfoDO)
     */
    @Override
    public UserMobileBaseInfoDO queryUserMobileBaseInfo(UserMobileBaseInfoDO userMobileBaseInfoDO) {
        Map<String, Object> param = new HashMap<String, Object>();

        param.put("userid", userMobileBaseInfoDO.getUserid());
        param.put("dataInfoType", userMobileBaseInfoDO.getDataInfoType());

        return (UserMobileBaseInfoDO) this.getSqlSession(customer).selectOne(
            "MS-MT-USER-MOBILE-BASE-INFO-GET-BY-CONDITONGS",
            param);
    }

}
