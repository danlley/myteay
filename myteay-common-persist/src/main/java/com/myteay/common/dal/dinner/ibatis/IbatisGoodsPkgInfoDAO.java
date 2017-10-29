/**
 * Myteay.com Inc.
 * Copyright (c) 2015-2016 All Rights Reserved.
 */
package com.myteay.common.dal.dinner.ibatis;

import java.util.List;

import org.apache.commons.lang.StringUtils;

import com.myteay.common.dal.dinner.daointerface.GoodsPkgInfoDAO;
import com.myteay.common.dal.dinner.dataobject.GoodsPkgInfoDO;
import com.myteay.common.mybatis.support.tools.MtSqlSessionDaoSupport;

/**
 * 套餐信息操作DAO
 * 
 * @author Administrator
 * @version $Id: IbatisGoodsPkgInfoDAO.java, v 0.1 2016年3月5日 上午9:29:06 Administrator Exp $
 */
public class IbatisGoodsPkgInfoDAO extends MtSqlSessionDaoSupport implements GoodsPkgInfoDAO {

    /** 
     * @see com.myteay.common.dal.dinner.daointerface.GoodsPkgInfoDAO#deleteById(java.lang.String)
     */
    @Override
    public int deleteById(String id) {
        return this.getSqlSession(dinner).delete("MS-MT-PKG-GOODS-DELETE-BY-ID", id);
    }

    /** 
     * @see com.myteay.common.dal.dinner.daointerface.GoodsPkgInfoDAO#findById(java.lang.String)
     */
    @Override
    public GoodsPkgInfoDO findById(String id) {

        if (StringUtils.isBlank(id)) {
            return null;
        }

        return (GoodsPkgInfoDO) this.getSqlSession(dinner).selectOne("MS-MT-PKG-GOODS-INFO-BY-ID", id);
    }

    /** 
     * @see com.myteay.common.dal.dinner.daointerface.GoodsPkgInfoDAO#insert(com.myteay.common.dal.dinner.dataobject.GoodsPkgInfoDO)
     */
    @Override
    public String insert(GoodsPkgInfoDO goodsPkgInfoDO) {
        if (goodsPkgInfoDO == null) {
            throw new IllegalArgumentException("Can't insert a null data object into db.");
        }

        this.getSqlSession(dinner).insert("MS-GOODS-PKG-INFO-INSERT", goodsPkgInfoDO);

        return goodsPkgInfoDO.getId();
    }

    /** 
     * @see com.myteay.common.dal.dinner.daointerface.GoodsPkgInfoDAO#findAll()
     */
    @Override
    public List<GoodsPkgInfoDO> findAll() {
        return this.getSqlSession(dinner).selectList("MS-MT-PKG-GOODS-INFO-ALL");
    }

}
