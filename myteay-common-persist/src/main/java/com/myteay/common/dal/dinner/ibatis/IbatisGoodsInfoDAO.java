/**
 * Myteay.com Inc.
 * Copyright (c) 2015-2016 All Rights Reserved.
 */
package com.myteay.common.dal.dinner.ibatis;

import java.util.List;

import com.myteay.common.dal.dinner.daointerface.GoodsInfoDAO;
import com.myteay.common.dal.dinner.dataobject.GoodsInfoDO;
import com.myteay.common.utils.orm.ibatis.support.MtSqlMapClientDaoSupport;

/**
 * 商品信息管理DAO
 * 
 * @author Administrator
 * @version $Id: IbatisGoodsInfoDAO.java, v 0.1 2016年3月5日 上午12:40:24 Administrator Exp $
 */
public class IbatisGoodsInfoDAO extends MtSqlMapClientDaoSupport implements GoodsInfoDAO {

    /** 
     * @see com.myteay.common.dal.dinner.daointerface.GoodsInfoDAO#insert(com.myteay.common.dal.dinner.dataobject.GoodsInfoDO)
     */
    @SuppressWarnings("deprecation")
    @Override
    public String insert(GoodsInfoDO goodsInfoDO) {
        if (goodsInfoDO == null) {
            throw new IllegalArgumentException("Can't insert a null data object into db.");
        }

        getSqlMapClientTemplate().insert("MS-GOODS-INFO-INSERT", goodsInfoDO);

        return goodsInfoDO.getId();
    }

    /** 
     * @see com.myteay.common.dal.dinner.daointerface.GoodsInfoDAO#findAll()
     */
    @SuppressWarnings({ "deprecation", "unchecked" })
    @Override
    public List<GoodsInfoDO> findAll() {
        return getSqlMapClientTemplate().queryForList("MS-MT-GOODS-INFO-ALL");
    }

    /** 
     * @see com.myteay.common.dal.dinner.daointerface.GoodsInfoDAO#findById(java.lang.String)
     */
    @SuppressWarnings("deprecation")
    @Override
    public GoodsInfoDO findById(String id) {
        return (GoodsInfoDO) getSqlMapClientTemplate().queryForObject(
            "MS-MT-GOODS-INFO-FIND-BY-ID", id);
    }

    /** 
     * @see com.myteay.common.dal.dinner.daointerface.GoodsInfoDAO#deleteById(java.lang.String)
     */
    @SuppressWarnings("deprecation")
    @Override
    public int deleteById(String id) {
        return getSqlMapClientTemplate().delete("MS-MT-GOODS-INFO-DELETE-BY-ID", id);
    }

}
