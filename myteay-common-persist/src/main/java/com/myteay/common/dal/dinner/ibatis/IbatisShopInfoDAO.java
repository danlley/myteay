/**
 * Myteay.com Inc.
 * Copyright (c) 2015-2016 All Rights Reserved.
 */
package com.myteay.common.dal.dinner.ibatis;

import java.util.List;

import com.myteay.common.dal.dinner.daointerface.ShopInfoDAO;
import com.myteay.common.dal.dinner.dataobject.ShopInfoDO;
import com.myteay.common.mybatis.support.tools.MtSqlSessionDaoSupport;

/**
 * 店铺信息操作DAO
 * 
 * @author Administrator
 * @version $Id: IbatisShopInfoDAO.java, v 0.1 2016年3月4日 下午5:46:19 Administrator Exp $
 */
public class IbatisShopInfoDAO extends MtSqlSessionDaoSupport implements ShopInfoDAO {

    /** 
     * @see com.myteay.common.dal.dinner.daointerface.ShopInfoDAO#findAll()
     */
    @Override
    public List<ShopInfoDO> findAll() {
        return this.getSqlSession(dinner).selectList("MS-MT-SHOP-INFO-ALL");

    }

}
