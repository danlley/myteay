/**
 * Myteay.com Inc.
 * Copyright (c) 2015-2016 All Rights Reserved.
 */
package com.myteay.core.model.dinner.repository;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.util.CollectionUtils;

import com.myteay.common.dal.dinner.daointerface.GoodsInfoDAO;
import com.myteay.common.dal.dinner.dataobject.GoodsInfoDO;
import com.myteay.common.service.facade.enums.MtOperateExResultEnum;
import com.myteay.common.service.facade.enums.MtOperateResultEnum;
import com.myteay.common.service.facade.model.MtOperateResult;
import com.myteay.core.model.dinner.MtGoodsModel;
import com.myteay.core.model.user.convt.MtGoodsModelConvertor;

/**
 * 商品信息单品管理仓储层
 * 
 * @author Administrator
 * @version $Id: MtGoodsInfoRepositoryImpl.java, v 0.1 2016年3月5日 上午12:43:36 Administrator Exp $
 */
public class MtGoodsInfoRepositoryImpl implements MtGoodsInfoRepository {

    /** 日志 */
    public static final Logger logger = Logger.getLogger(MtGoodsInfoRepositoryImpl.class);

    /** 商品信息管理DAO */
    private GoodsInfoDAO       goodsInfoDAO;

    /** 
     * @see com.myteay.core.model.dinner.repository.MtGoodsInfoRepository#removeGoodsInfoById(java.lang.String)
     */
    @Override
    public MtOperateResult<String> removeGoodsInfoById(String id) {
        if (logger.isInfoEnabled()) {
            logger.info("删除指定的单品信息 id=" + id);
        }

        MtOperateResult<String> result = new MtOperateResult<String>();
        try {
            goodsInfoDAO.deleteById(id);
        } catch (Exception e) {
            logger.error("删除指定单品信息失败 id=" + id, e);
            result.setOperateExResult(MtOperateExResultEnum.CAMP_SQL_EXE_INVALID);
            result.setOperateResult(MtOperateResultEnum.CAMP_OPERATE_FAILED);
            result.setErrorDetail(e.getMessage());
            return result;
        }

        if (logger.isInfoEnabled()) {
            logger.info("删除指定的单品信息成功 id=" + id);
        }
        result.setOperateExResult(MtOperateExResultEnum.CAMP_OPERATE_SUCCESS);
        result.setOperateResult(MtOperateResultEnum.CAMP_OPERATE_SUCCESS);
        result.setResult(id);
        return result;
    }

    /** 
     * @see com.myteay.core.model.dinner.repository.MtGoodsInfoRepository#findGoodsInfoById(java.lang.String)
     */
    @Override
    public MtOperateResult<MtGoodsModel> findGoodsInfoById(String id) {

        GoodsInfoDO goodsInfoDO = null;
        MtOperateResult<MtGoodsModel> result = new MtOperateResult<MtGoodsModel>();
        try {
            goodsInfoDO = goodsInfoDAO.findById(id);
        } catch (Exception e) {
            logger.error("查询商品单品信息时发生异常 id=" + id, e);
            result.setOperateExResult(MtOperateExResultEnum.CAMP_SQL_EXE_INVALID);
            result.setOperateResult(MtOperateResultEnum.CAMP_OPERATE_FAILED);
            return result;
        }

        if (goodsInfoDO == null) {
            logger.warn("系统当前未找到单品信息，请核实相关数据！");
            result.setOperateExResult(MtOperateExResultEnum.CAMP_OPERATE_SUCCESS);
            result.setOperateResult(MtOperateResultEnum.CAMP_OPERATE_SUCCESS);
            return result;
        }

        MtGoodsModel model = MtGoodsModelConvertor.convertDO2Model(goodsInfoDO);
        if (model == null) {
            logger.warn("单品信息数据模型转化单品信息模型失败 model is null,goodsInfoDO=" + goodsInfoDO);
            result.setOperateExResult(MtOperateExResultEnum.CAMP_ILLEGAL_ARGUMENTS);
            result.setOperateResult(MtOperateResultEnum.CAMP_OPERATE_FAILED);
            return result;
        }

        result.setOperateExResult(MtOperateExResultEnum.CAMP_OPERATE_SUCCESS);
        result.setOperateResult(MtOperateResultEnum.CAMP_OPERATE_SUCCESS);
        result.setResult(model);
        return result;
    }

    /** 
     * @see com.myteay.core.model.dinner.repository.MtGoodsInfoRepository#saveGoodsInfo(com.myteay.core.model.dinner.MtGoodsModel)
     */
    @Override
    public MtOperateResult<String> saveGoodsInfo(MtGoodsModel model) {

        GoodsInfoDO goodsInfoDO = MtGoodsModelConvertor.convertModel2DO(model);
        MtOperateResult<String> result = new MtOperateResult<String>();
        if (goodsInfoDO == null) {
            logger.warn("商品单品信息模型不可用，无法保存 model=" + model);
            result.setOperateExResult(MtOperateExResultEnum.CAMP_ILLEGAL_ARGUMENTS);
            result.setOperateResult(MtOperateResultEnum.CAMP_OPERATE_FAILED);
            return result;
        }

        String id = null;
        try {
            id = goodsInfoDAO.insert(goodsInfoDO);
        } catch (Exception e) {
            logger.error("保存商品单品信息时发生异常 model=" + model, e);
            result.setOperateExResult(MtOperateExResultEnum.CAMP_SQL_EXE_INVALID);
            result.setOperateResult(MtOperateResultEnum.CAMP_OPERATE_FAILED);
            return result;
        }

        result.setOperateExResult(MtOperateExResultEnum.CAMP_OPERATE_SUCCESS);
        result.setOperateResult(MtOperateResultEnum.CAMP_OPERATE_SUCCESS);
        result.setResult(id);
        return result;
    }

    /** 
     * @see com.myteay.core.model.dinner.repository.MtGoodsInfoRepository#queryGoodsList()
     */
    @Override
    public MtOperateResult<List<MtGoodsModel>> queryGoodsList() {

        List<GoodsInfoDO> list = null;
        MtOperateResult<List<MtGoodsModel>> result = new MtOperateResult<List<MtGoodsModel>>();
        try {
            list = goodsInfoDAO.findAll();
        } catch (Exception e) {
            logger.error("查询所有商品信息发生异常" + e.getMessage(), e);
            result.setOperateExResult(MtOperateExResultEnum.CAMP_SQL_EXE_INVALID);
            result.setOperateResult(MtOperateResultEnum.CAMP_OPERATE_FAILED);
            result.setErrorDetail(e.getMessage());
            return result;
        }

        if (CollectionUtils.isEmpty(list)) {
            logger.warn("系统当前未找到商品信息，请核实相关数据！");
            result.setOperateExResult(MtOperateExResultEnum.CAMP_OPERATE_SUCCESS);
            result.setOperateResult(MtOperateResultEnum.CAMP_OPERATE_SUCCESS);
            return result;
        }

        MtGoodsModel model = null;
        List<MtGoodsModel> modelList = new ArrayList<MtGoodsModel>();
        for (GoodsInfoDO goodsInfoDO : list) {
            model = MtGoodsModelConvertor.convertDO2Model(goodsInfoDO);

            if (model == null) {
                continue;
            }

            modelList.add(model);
        }

        result.setOperateExResult(MtOperateExResultEnum.CAMP_OPERATE_SUCCESS);
        result.setOperateResult(MtOperateResultEnum.CAMP_OPERATE_SUCCESS);
        result.setResult(modelList);

        return result;
    }

    /**
     * Setter method for property <tt>goodsInfoDAO</tt>.
     * 
     * @param goodsInfoDAO value to be assigned to property goodsInfoDAO
     */
    public void setGoodsInfoDAO(GoodsInfoDAO goodsInfoDAO) {
        this.goodsInfoDAO = goodsInfoDAO;
    }

}
