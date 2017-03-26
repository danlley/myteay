/**
 * Myteay.com Inc.
 * Copyright (c) 2015-2016 All Rights Reserved.
 */
package com.myteay.common.dal.dinner.daointerface;

import java.util.List;

import com.myteay.common.dal.dinner.dataobject.ShopInfoDO;

/**
 * 店铺信息操作DAO
 * 
 * @author Administrator
 * @version $Id: ShopInfoDAO.java, v 0.1 2016年3月4日 下午5:44:53 Administrator Exp $
 */
public interface ShopInfoDAO {

    /**
     * 查询所有店铺信息列表
     * 
     * @return  店铺信息列表
     */
    public List<ShopInfoDO> findAll();
}
