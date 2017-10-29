/**
 * Myteay.com Inc.
 * Copyright (c) 2015-2016 All Rights Reserved.
 */
package com.myteay.common.dal.ibatis;

import java.util.List;

import com.myteay.common.dal.daointerface.UserSysDictInfoDAO;
import com.myteay.common.dal.dataobject.UserSysDictInfoDO;
import com.myteay.common.mybatis.support.tools.MtSqlSessionDaoSupport;

/**
 * 用户字典操作DAO
 * 
 * @author Administrator
 * @version $Id: IbatisUserSysDictInfoDAO.java, v 0.1 2016年9月7日 上午12:34:35 Administrator Exp $
 */
public class IbatisUserSysDictInfoDAO extends MtSqlSessionDaoSupport implements UserSysDictInfoDAO {

    /** 
     * @see com.myteay.common.dal.daointerface.UserSysDictInfoDAO#findAll()
     */
    @Override
    public List<UserSysDictInfoDO> findAll() {
        return this.getSqlSession(customer).selectList("MS-SYSDICT-INFO-GET-BY-ALL");
    }

    /** 
     * @see com.myteay.common.dal.daointerface.UserSysDictInfoDAO#getByKey(String)
     */
    @Override
    public UserSysDictInfoDO getByKey(String key) {
        return (UserSysDictInfoDO) this.getSqlSession(customer).selectOne("MS-SYSDICT-INFO-GET-BY-ID", key);
    }

}
