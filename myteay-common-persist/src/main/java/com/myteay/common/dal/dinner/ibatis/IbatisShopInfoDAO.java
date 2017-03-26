/**
 * Myteay.com Inc.
 * Copyright (c) 2015-2016 All Rights Reserved.
 */
package com.myteay.common.dal.dinner.ibatis;

import java.util.List;

import com.myteay.common.dal.dinner.daointerface.ShopInfoDAO;
import com.myteay.common.dal.dinner.dataobject.ShopInfoDO;
import com.myteay.common.utils.orm.ibatis.support.MtSqlMapClientDaoSupport;

/**
 * 店铺信息操作DAO
 * 
 * @author Administrator
 * @version $Id: IbatisShopInfoDAO.java, v 0.1 2016年3月4日 下午5:46:19 Administrator Exp $
 */
public class IbatisShopInfoDAO extends MtSqlMapClientDaoSupport implements ShopInfoDAO {

    /** 
     * @see com.myteay.common.dal.dinner.daointerface.ShopInfoDAO#findAll()
     */
    @SuppressWarnings({ "deprecation", "unchecked" })
    @Override
    public List<ShopInfoDO> findAll() {
        return getSqlMapClientTemplate().queryForList("MS-MT-SHOP-INFO-ALL");

    }

}
