/**
 * Myteay.com Inc.
 * Copyright (c) 2015-2016 All Rights Reserved.
 */
package com.myteay.common.dal.dinner.daointerface;

import java.util.List;

import com.myteay.common.dal.dinner.dataobject.GoodsPkgInfoDO;
import com.myteay.common.util.constants.MtDBKey;

/**
 * 套餐信息操作DAO
 * 
 * @author Administrator
 * @version $Id: GoodsPkgInfoDAO.java, v 0.1 2016年3月5日 上午9:28:01 Administrator Exp $
 */
public interface GoodsPkgInfoDAO extends MtDBKey {

    /**
     * 删除指定的套餐数据信息
     * 
     * @param id    套餐流水号
     * @return
     */
    public int deleteById(String id);

    /**
     * 通过套餐信息流水号查找套餐信息数据模型
     * 
     * @param id    套餐信息流水号
     * @return      套餐信息数据模型
     */
    public GoodsPkgInfoDO findById(String id);

    /**
     * 保存所有套餐信息
     * 
     * @param goodsPkgInfoDO    套餐信息数据模型
     * @return                  套餐ID
     */
    public String insert(GoodsPkgInfoDO goodsPkgInfoDO);

    /**
     * 查询所有套餐信息列表
     * 
     * @return  套餐信息列表
     */
    public List<GoodsPkgInfoDO> findAll();
}
