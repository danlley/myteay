/**
 * Myteay.com Inc.
 * Copyright (c) 2015-2016 All Rights Reserved.
 */
package com.myteay.common.dal.dinner.ibatis;

import java.util.List;

import com.myteay.common.dal.dinner.daointerface.GoodsInfoDAO;
import com.myteay.common.dal.dinner.dataobject.GoodsInfoDO;
import com.myteay.common.dal.utils.MtSqlSessionDaoSupport;

/**
 * 商品信息管理DAO
 * 
 * @author Administrator
 * @version $Id: IbatisGoodsInfoDAO.java, v 0.1 2016年3月5日 上午12:40:24 Administrator Exp $
 */
public class IbatisGoodsInfoDAO extends MtSqlSessionDaoSupport implements GoodsInfoDAO {

    /** 
     * @see com.myteay.common.dal.dinner.daointerface.GoodsInfoDAO#insert(com.myteay.common.dal.dinner.dataobject.GoodsInfoDO)
     */
    @Override
    public String insert(GoodsInfoDO goodsInfoDO) {
        if (goodsInfoDO == null) {
            throw new IllegalArgumentException("Can't insert a null data object into db.");
        }

        this.getSqlSession(dinner).insert("MS-GOODS-INFO-INSERT", goodsInfoDO);

        return goodsInfoDO.getId();
    }

    /** 
     * @see com.myteay.common.dal.dinner.daointerface.GoodsInfoDAO#findAll()
     */
    @Override
    public List<GoodsInfoDO> findAll() {
        return this.getSqlSession(dinner).selectList("MS-MT-GOODS-INFO-ALL");
    }

    /** 
     * @see com.myteay.common.dal.dinner.daointerface.GoodsInfoDAO#findById(java.lang.String)
     */
    @Override
    public GoodsInfoDO findById(String id) {
        return (GoodsInfoDO) this.getSqlSession(dinner).selectOne("MS-MT-GOODS-INFO-FIND-BY-ID", id);
    }

    /** 
     * @see com.myteay.common.dal.dinner.daointerface.GoodsInfoDAO#deleteById(java.lang.String)
     */
    @Override
    public int deleteById(String id) {
        return this.getSqlSession(dinner).delete("MS-MT-GOODS-INFO-DELETE-BY-ID", id);
    }

}
