/**
 * Myteay.com Inc.
 * Copyright (c) 2015-2016 All Rights Reserved.
 */
package com.myteay.core.model.user.convt;

import com.myteay.common.dal.dinner.dataobject.ShopInfoDO;
import com.myteay.core.model.dinner.MtShopModel;

/**
 * 店铺模型转换器
 * 
 * @author Administrator
 * @version $Id: MtShopModelConvertor.java, v 0.1 2016年3月5日 下午9:28:46 Administrator Exp $
 */
public class MtShopModelConvertor {

    /**
     * 将店铺信息数据模型转换为领域模型
     * 
     * @param shopInfoDO    店铺信息数据模型
     * @return              店铺信息模型
     */
    public static MtShopModel convertDO2Model(ShopInfoDO shopInfoDO) {

        if (shopInfoDO == null) {
            return null;
        }

        MtShopModel model = new MtShopModel();

        model.setCityCode(shopInfoDO.getCityCode());
        model.setGmtCreated(shopInfoDO.getGmtCreated());
        model.setGmtModified(shopInfoDO.getGmtModified());
        model.setShopAddr(shopInfoDO.getShopAddr());
        model.setShopId(shopInfoDO.getShopId());
        model.setShopName(shopInfoDO.getShopName());
        model.setShopTel(shopInfoDO.getShopTel());

        return model;
    }
}
