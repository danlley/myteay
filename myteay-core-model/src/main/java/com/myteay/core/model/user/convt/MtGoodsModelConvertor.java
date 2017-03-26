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
 * ������Ʒģ��ת����
 * 
 * @author Administrator
 * @version $Id: MtGoodsModelConvertor.java, v 0.1 2016��3��5�� ����9:26:33 Administrator Exp $
 */
public class MtGoodsModelConvertor {
    /**
     * ����Ʒ��Ʒ��Ϣ��������ת��Ϊ��Ʒ��Ʒ��Ϣģ��
     * 
     * @param message   ��Ʒ��Ʒ��Ϣ��������
     * @return          ��Ʒ��Ʒ��Ϣģ��
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
     * ����Ʒ��Ϣģ��ת��Ϊ��Ʒ��Ϣ��������
     * 
     * @param model ��Ʒ��Ϣģ��
     * @return      ��Ʒ��Ϣ��������
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
     * ����Ʒ����ģ��ת��Ϊ��Ʒģ��
     * 
     * @param goodsInfoDO   ��Ʒ����ģ��
     * @return              ��Ʒģ��
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
     * ����Ʒ��Ʒ��Ϣģ��ת��Ϊ��Ʒ��Ʒ��Ϣ����ģ��
     * 
     * @param model ��Ʒ��Ʒ��Ϣģ��
     * @return      ��Ʒ��Ʒ��Ϣ����ģ��
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
