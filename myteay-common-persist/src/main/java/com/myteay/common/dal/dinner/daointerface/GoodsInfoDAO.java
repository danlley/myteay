/**
 * Myteay.com Inc.
 * Copyright (c) 2015-2016 All Rights Reserved.
 */
package com.myteay.common.dal.dinner.daointerface;

import java.util.List;

import com.myteay.common.dal.dinner.dataobject.GoodsInfoDO;

/**
 * 单品信息管理DAO
 * 
 * @author Administrator
 * @version $Id: GoodsInfoDAO.java, v 0.1 2016年3月5日 上午12:39:22 Administrator Exp $
 */
public interface GoodsInfoDAO {

    /**
     * 通过ID删除指定单品信息
     * 
     * @param id    单品ID
     * @return      删除结果
     */
    int deleteById(String id);

    /**
     * 通过ID查找单品信息数据模型
     * 
     * @param id    单品流水号
     * @return
     */
    GoodsInfoDO findById(String id);

    /**
     * 插入单品基本信息
     * 
     * @param goodsInfoDO   单品基本信息数据模型
     * @return
     */
    String insert(GoodsInfoDO goodsInfoDO);

    /**
     * 查询所有单品信息列表
     * 
     * @return  单品信息列表
     */
    public List<GoodsInfoDO> findAll();
}
