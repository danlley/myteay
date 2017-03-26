/**
 * Myteay.com Inc.
 * Copyright (c) 2015-2016 All Rights Reserved.
 */
package com.myteay.common.dal.dinner.ibatis;

import java.util.List;

import com.myteay.common.dal.dinner.daointerface.GoodsPkgInfoDAO;
import com.myteay.common.dal.dinner.dataobject.GoodsPkgInfoDO;
import com.myteay.common.util.comm.StringUtils;
import com.myteay.common.utils.orm.ibatis.support.MtSqlMapClientDaoSupport;

/**
 * 套餐信息操作DAO
 * 
 * @author Administrator
 * @version $Id: IbatisGoodsPkgInfoDAO.java, v 0.1 2016年3月5日 上午9:29:06 Administrator Exp $
 */
public class IbatisGoodsPkgInfoDAO extends MtSqlMapClientDaoSupport implements GoodsPkgInfoDAO {

    /** 
     * @see com.myteay.common.dal.dinner.daointerface.GoodsPkgInfoDAO#deleteById(java.lang.String)
     */
    @SuppressWarnings("deprecation")
    @Override
    public int deleteById(String id) {
        return getSqlMapClientTemplate().delete("MS-MT-PKG-GOODS-DELETE-BY-ID", id);
    }

    /** 
     * @see com.myteay.common.dal.dinner.daointerface.GoodsPkgInfoDAO#findById(java.lang.String)
     */
    @SuppressWarnings("deprecation")
    @Override
    public GoodsPkgInfoDO findById(String id) {

        if (StringUtils.isBlank(id)) {
            return null;
        }

        return (GoodsPkgInfoDO) getSqlMapClientTemplate().queryForObject(
            "MS-MT-PKG-GOODS-INFO-BY-ID", id);
    }

    /** 
     * @see com.myteay.common.dal.dinner.daointerface.GoodsPkgInfoDAO#insert(com.myteay.common.dal.dinner.dataobject.GoodsPkgInfoDO)
     */
    @SuppressWarnings("deprecation")
    @Override
    public String insert(GoodsPkgInfoDO goodsPkgInfoDO) {
        if (goodsPkgInfoDO == null) {
            throw new IllegalArgumentException("Can't insert a null data object into db.");
        }

        getSqlMapClientTemplate().insert("MS-GOODS-PKG-INFO-INSERT", goodsPkgInfoDO);

        return goodsPkgInfoDO.getId();
    }

    /** 
     * @see com.myteay.common.dal.dinner.daointerface.GoodsPkgInfoDAO#findAll()
     */
    @SuppressWarnings({ "deprecation", "unchecked" })
    @Override
    public List<GoodsPkgInfoDO> findAll() {
        return getSqlMapClientTemplate().queryForList("MS-MT-PKG-GOODS-INFO-ALL");
    }

}
