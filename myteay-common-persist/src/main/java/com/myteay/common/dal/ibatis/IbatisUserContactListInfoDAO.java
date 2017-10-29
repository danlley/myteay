/**
 * Myteay.com Inc.
 * Copyright (c) 2005-2017 All Rights Reserved.
 */
package com.myteay.common.dal.ibatis;

import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;

import com.myteay.common.dal.daointerface.UserContactListInfoDAO;
import com.myteay.common.dal.dataobject.UserContactListInfoDO;
import com.myteay.common.mybatis.support.tools.MtSqlSessionDaoSupport;

/**
 * 联系人操作DAO
 * 
 * @author danlley
 * @version $Id: IbatisUserContactListInfoDAO.java, v 0.1 Sep 2, 2017 5:32:28 PM danlley Exp $
 */
public class IbatisUserContactListInfoDAO extends MtSqlSessionDaoSupport implements UserContactListInfoDAO {

    /** 日志 */
    public static final Logger logger = Logger.getLogger(IbatisUserContactListInfoDAO.class);

    /** 
     * @see com.myteay.common.dal.daointerface.UserContactListInfoDAO#insert(com.myteay.common.dal.dataobject.UserContactListInfoDO)
     */
    @Override
    public String insert(UserContactListInfoDO userContactListInfoDO) {
        if (userContactListInfoDO == null) {
            throw new IllegalArgumentException("Can't insert a null data object into db.");
        }

        this.getSqlSession(customer).insert("MS-USER-CONTACT-LIST-INFO-INSERT", userContactListInfoDO);

        return userContactListInfoDO.getId();
    }

    /** 
     * @see com.myteay.common.dal.daointerface.UserContactListInfoDAO#queryUserContactListInfoDO(com.myteay.common.dal.dataobject.UserContactListInfoDO)
     */
    @Override
    public UserContactListInfoDO queryUserContactListInfoDO(UserContactListInfoDO userContactListInfoDO) {
        Map<String, Object> param = new HashMap<String, Object>();

        param.put("parentMobile", userContactListInfoDO.getParentMobile());
        param.put("contactMobile", userContactListInfoDO.getContactMobile());

        return (UserContactListInfoDO) this.getSqlSession(customer).selectOne(
            "MS-MT-USER-CONTACT-LIST-INFO-GET-BY-CONDITONGS",
            param);
    }

}
