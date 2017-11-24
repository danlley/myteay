/**
 * Myteay.com Inc.
 * Copyright (c) 2015-2016 All Rights Reserved.
 */
package com.myteay.core.model.user.convt;

import com.myteay.common.dal.dinner.dataobject.GoodsPkgInfoDO;
import com.myteay.common.service.facade.model.dinner.MtGoodsPkgInfoMessage;
import com.myteay.common.util.constants.MtConstants;
import com.myteay.core.model.dinner.MtGoodsPkgModel;

/**
 * 套餐模型转换器
 * 
 * @author Administrator
 * @version $Id: MtGoodsPkgModelConvertor.java, v 0.1 2016年3月5日 下午9:22:35 Administrator Exp $
 */
public class MtGoodsPkgModelConvertor {

    /**
     * 将套餐模型转换为套餐信息数据模型
     * 
     * @param model 套餐模型
     * @return      套餐信息数据模型
     */
    public static GoodsPkgInfoDO convertModel2DO(MtGoodsPkgModel model) {

        if (model == null) {
            return null;
        }

        GoodsPkgInfoDO goodsPkgInfoDO = new GoodsPkgInfoDO();

        goodsPkgInfoDO.setGmtCreated(model.getGmtCreated());
        goodsPkgInfoDO.setGmtModified(model.getGmtModified());
        goodsPkgInfoDO.setGoodsId(model.getGoodsId());
        goodsPkgInfoDO.setId(model.getId());
        goodsPkgInfoDO.setPicAddr(model.getPicAddr());
        goodsPkgInfoDO.setPicBigAddr(model.getPicBigAddr());
        goodsPkgInfoDO.setPkgName(model.getPkgName());
        goodsPkgInfoDO.setPrice(model.getPrice());
        goodsPkgInfoDO.setPriceMobile(model.getPriceMobile());
        goodsPkgInfoDO.setShopId(model.getShopId());

        return goodsPkgInfoDO;
    }

    /**
     * 将套餐信息数据模型转换为套餐模型
     * 
     * @param goodsPkgInfoDO    套餐数据模型
     * @return                  套餐模型
     */
    public static MtGoodsPkgModel convertDO2Model(GoodsPkgInfoDO goodsPkgInfoDO) {

        if (goodsPkgInfoDO == null) {
            return null;
        }
        MtGoodsPkgModel model = new MtGoodsPkgModel();

        model.setGmtCreated(goodsPkgInfoDO.getGmtCreated());
        model.setGmtModified(goodsPkgInfoDO.getGmtModified());
        model.setGoodsId(goodsPkgInfoDO.getGoodsId());
        model.setId(goodsPkgInfoDO.getId());
        model.setPicAddr(goodsPkgInfoDO.getPicAddr());
        model.setPicBigAddr(goodsPkgInfoDO.getPicBigAddr());
        model.setPkgName(goodsPkgInfoDO.getPkgName());
        model.setPrice(goodsPkgInfoDO.getPrice());
        model.setPriceMobile(goodsPkgInfoDO.getPriceMobile());
        model.setShopId(goodsPkgInfoDO.getShopId());

        return model;
    }

    /**
     * 将套餐信息模型转换为套餐信息交互单据
     * 
     * @param model 套餐信息模型
     * @return      套餐信息交互单据
     */
    public static MtGoodsPkgInfoMessage convertModel2Message(MtGoodsPkgModel model) {

        if (model == null) {
            return null;
        }
        MtGoodsPkgInfoMessage message = new MtGoodsPkgInfoMessage();

        message.setGmtCreated(model.getGmtCreated());
        message.setGmtModified(model.getGmtModified());
        message.setGoodsId(model.getGoodsId());
        message.setId(model.getId());
        message.setPicAddr(model.getPicAddr());
        message.setPicBigAddr(model.getPicBigAddr());
        message.setPkgName(model.getPkgName());
        message.setPrice(model.getPrice());
        message.setPriceMobile(model.getPriceMobile());
        message.setShopId(model.getShopId());
        message.setImgParentPath(MtConstants.ROOT_URI);

        return message;
    }

    /**
     * 将套餐信息交互单据转换为套餐信息模型
     * 
     * @param message   套餐信息交互单据
     * @return          套餐信息模型
     */
    public static MtGoodsPkgModel convertMessage2Model(MtGoodsPkgInfoMessage message) {

        if (message == null) {
            return null;
        }
        MtGoodsPkgModel model = new MtGoodsPkgModel();

        model.setGmtCreated(message.getGmtCreated());
        model.setGmtModified(message.getGmtModified());
        model.setGoodsId(message.getGoodsId());
        model.setId(message.getId());
        model.setPicAddr(message.getPicAddr());
        model.setPicBigAddr(message.getPicBigAddr());
        model.setPkgName(message.getPkgName());
        model.setPrice(message.getPrice());
        model.setPriceMobile(message.getPriceMobile());
        model.setShopId(message.getShopId());

        return model;
    }
}
