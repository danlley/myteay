/**
 * Myteay.com Inc.
 * Copyright (c) 2015-2016 All Rights Reserved.
 */
package com.myteay.core.model.user.convt;

import com.myteay.common.dal.dinner.dataobject.ShopInfoDO;
import com.myteay.core.model.dinner.MtShopModel;

/**
 * ����ģ��ת����
 * 
 * @author Administrator
 * @version $Id: MtShopModelConvertor.java, v 0.1 2016��3��5�� ����9:28:46 Administrator Exp $
 */
public class MtShopModelConvertor {

    /**
     * ��������Ϣ����ģ��ת��Ϊ����ģ��
     * 
     * @param shopInfoDO    ������Ϣ����ģ��
     * @return              ������Ϣģ��
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
