/**
 * Myteay.com Inc.
 * Copyright (c) 2015-2016 All Rights Reserved.
 */
package com.myteay.core.model.user.convt;

import com.myteay.common.dal.dinner.dataobject.GoodsInfoDO;
import com.myteay.common.service.facade.model.dinner.MtGoodsInfoMessage;
import com.myteay.common.util.constants.MtConstants;
import com.myteay.core.model.dinner.MtGoodsModel;

/**
 * 单个商品模型转换器
 * 
 * @author Administrator
 * @version $Id: MtGoodsModelConvertor.java, v 0.1 2016年3月5日 下午9:26:33 Administrator Exp $
 */
public class MtGoodsModelConvertor {
    /**
     * 将商品单品信息交互单据转换为商品单品信息模型
     * 
     * @param message   商品单品信息交互单据
     * @return          商品单品信息模型
     */
    public static MtGoodsModel convertMessage2Model(MtGoodsInfoMessage message) {
        if (message == null) {
            return null;
        }
        MtGoodsModel model = new MtGoodsModel();

        model.setGmtCreated(message.getGmtCreated());
        model.setGmtModified(message.getGmtModified());
        model.setGoodsTitle(message.getGoodsTitle());
        model.setId(message.getId());
        model.setPicAddr(message.getPicAddr());
        model.setPrice(message.getPrice());
        model.setShopId(message.getShopId());
        model.setSummary(message.getSummary());

        return model;
    }

    /**
     * 将商品信息模型转换为商品信息交互单据
     * 
     * @param model 商品信息模型
     * @return      商品信息交互单据
     */
    public static MtGoodsInfoMessage convertModel2Message(MtGoodsModel model) {
        if (model == null) {
            return null;
        }

        MtGoodsInfoMessage message = new MtGoodsInfoMessage();
        message.setGmtCreated(model.getGmtCreated());
        message.setGmtModified(model.getGmtModified());
        message.setGoodsTitle(model.getGoodsTitle());
        message.setId(model.getId());
        message.setPicAddr(model.getPicAddr());
        message.setPrice(model.getPrice());
        message.setShopId(model.getShopId());
        message.setSummary(model.getSummary());
        message.setImgParentPath(MtConstants.ROOT_URI);

        return message;
    }

    /**
     * 将商品数据模型转换为商品模型
     * 
     * @param goodsInfoDO   商品数据模型
     * @return              商品模型
     */
    public static MtGoodsModel convertDO2Model(GoodsInfoDO goodsInfoDO) {

        if (goodsInfoDO == null) {
            return null;
        }

        MtGoodsModel model = new MtGoodsModel();

        model.setGmtCreated(goodsInfoDO.getGmtCreated());
        model.setGmtModified(goodsInfoDO.getGmtModified());
        model.setGoodsTitle(goodsInfoDO.getGoodsTitle());
        model.setId(goodsInfoDO.getId());
        model.setPicAddr(goodsInfoDO.getPicAddr());
        model.setPrice(goodsInfoDO.getPrice());
        model.setShopId(goodsInfoDO.getShopId());
        model.setSummary(goodsInfoDO.getSummary());

        return model;
    }

    /**
     * 将商品单品信息模型转换为商品单品信息数据模型
     * 
     * @param model 商品单品信息模型
     * @return      商品单品信息数据模型
     */
    public static GoodsInfoDO convertModel2DO(MtGoodsModel model) {
        if (model == null) {
            return null;
        }

        GoodsInfoDO goodsInfoDO = new GoodsInfoDO();

        goodsInfoDO.setGmtCreated(model.getGmtCreated());
        goodsInfoDO.setGmtModified(model.getGmtModified());
        goodsInfoDO.setGoodsTitle(model.getGoodsTitle());
        goodsInfoDO.setId(model.getId());
        goodsInfoDO.setPicAddr(model.getPicAddr());
        goodsInfoDO.setPrice(model.getPrice());
        goodsInfoDO.setShopId(model.getShopId());
        goodsInfoDO.setSummary(model.getSummary());

        return goodsInfoDO;
    }
}
