/**
 * Myteay.com Inc.
 * Copyright (c) 2015-2016 All Rights Reserved.
 */
package com.myteay.core.model.dinner.repository;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.util.CollectionUtils;

import com.myteay.common.dal.dinner.daointerface.GoodsPkgInfoDAO;
import com.myteay.common.dal.dinner.dataobject.GoodsPkgInfoDO;
import com.myteay.common.service.facade.enums.MtOperateExResultEnum;
import com.myteay.common.service.facade.enums.MtOperateResultEnum;
import com.myteay.common.service.facade.model.MtOperateResult;
import com.myteay.core.model.dinner.MtGoodsPkgModel;
import com.myteay.core.model.user.convt.MtGoodsPkgModelConvertor;

/**
 * 套餐信息单品管理仓储层
 * 
 * @author Administrator
 * @version $Id: MtGoodsPkgInfoRepositoryImpl.java, v 0.1 2016年3月5日 上午9:33:09 Administrator Exp $
 */
public class MtGoodsPkgInfoRepositoryImpl implements MtGoodsPkgInfoRepository {

    /** 日志 */
    public static final Logger logger = Logger.getLogger(MtGoodsPkgInfoRepositoryImpl.class);

    /** 套餐信息操作DAO */
    private GoodsPkgInfoDAO    goodsPkgInfoDAO;

    /** 
     * @see com.myteay.core.model.dinner.repository.MtGoodsPkgInfoRepository#deleteGoodsPkgById(java.lang.String)
     */
    @Override
    public MtOperateResult<String> deleteGoodsPkgById(String id) {

        if (logger.isInfoEnabled()) {
            logger.info("删除指定的套餐信息 id=" + id);
        }

        MtOperateResult<String> result = new MtOperateResult<String>();
        try {
            goodsPkgInfoDAO.deleteById(id);
        } catch (Exception e) {
            logger.error("删除指定套餐信息失败 id=" + id, e);
            result.setOperateExResult(MtOperateExResultEnum.CAMP_SQL_EXE_INVALID);
            result.setOperateResult(MtOperateResultEnum.CAMP_OPERATE_FAILED);
            result.setErrorDetail(e.getMessage());
            return result;
        }

        if (logger.isInfoEnabled()) {
            logger.info("删除指定的套餐信息成功 id=" + id);
        }
        result.setOperateExResult(MtOperateExResultEnum.CAMP_OPERATE_SUCCESS);
        result.setOperateResult(MtOperateResultEnum.CAMP_OPERATE_SUCCESS);
        result.setResult(id);
        return result;
    }

    /** 
     * @see com.myteay.core.model.dinner.repository.MtGoodsPkgInfoRepository#findGoodsInfoById(java.lang.String)
     */
    @Override
    public MtOperateResult<MtGoodsPkgModel> findGoodsInfoById(String id) {
        MtOperateResult<MtGoodsPkgModel> result = new MtOperateResult<MtGoodsPkgModel>();
        GoodsPkgInfoDO goodsPkgInfoDO = null;
        try {
            goodsPkgInfoDO = goodsPkgInfoDAO.findById(id);
        } catch (Exception e) {
            logger.error("查询套餐信息出错：goodsPkgInfoDO=" + goodsPkgInfoDO, e);
            result.setOperateExResult(MtOperateExResultEnum.CAMP_SQL_EXE_INVALID);
            result.setOperateResult(MtOperateResultEnum.CAMP_OPERATE_FAILED);
            result.setErrorDetail(e.getMessage());
            return result;
        }

        if (goodsPkgInfoDO == null) {
            logger.warn("查询未找到套餐信息 id=" + id);
            result.setOperateExResult(MtOperateExResultEnum.CAMP_OPERATE_SUCCESS);
            result.setOperateResult(MtOperateResultEnum.CAMP_OPERATE_SUCCESS);
            return result;
        }

        MtGoodsPkgModel model = MtGoodsPkgModelConvertor.convertDO2Model(goodsPkgInfoDO);
        if (model == null) {
            logger.warn("套餐数据模型转换套餐信息模型出错 model is null, goodsPkgInfoDO=" + goodsPkgInfoDO);
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
     * @see com.myteay.core.model.dinner.repository.MtGoodsPkgInfoRepository#saveGoodsPkgModel(com.myteay.core.model.dinner.MtGoodsPkgModel)
     */
    @Override
    public MtOperateResult<String> saveGoodsPkgModel(MtGoodsPkgModel model) {
        GoodsPkgInfoDO goodsPkgInfoDO = MtGoodsPkgModelConvertor.convertModel2DO(model);
        MtOperateResult<String> result = new MtOperateResult<String>();
        if (goodsPkgInfoDO == null) {
            logger.warn("套餐数据模型不可用，无法保存套餐数据模型。goodsPkgInfoDO is null, model=" + model);
            result.setOperateExResult(MtOperateExResultEnum.CAMP_ILLEGAL_ARGUMENTS);
            result.setOperateResult(MtOperateResultEnum.CAMP_OPERATE_FAILED);
            return result;
        }

        String id = null;
        try {
            id = goodsPkgInfoDAO.insert(goodsPkgInfoDO);
        } catch (Exception e) {
            logger.error("保存套餐信息出错：goodsPkgInfoDO=" + goodsPkgInfoDO, e);
            result.setOperateExResult(MtOperateExResultEnum.CAMP_SQL_EXE_INVALID);
            result.setOperateResult(MtOperateResultEnum.CAMP_OPERATE_FAILED);
            result.setErrorDetail(e.getMessage());
            return result;
        }

        result.setOperateExResult(MtOperateExResultEnum.CAMP_OPERATE_SUCCESS);
        result.setOperateResult(MtOperateResultEnum.CAMP_OPERATE_SUCCESS);
        result.setResult(id);
        return result;
    }

    /** 
     * @see com.myteay.core.model.dinner.repository.MtGoodsPkgInfoRepository#queryGoodsPkgList()
     */
    @Override
    public MtOperateResult<List<MtGoodsPkgModel>> queryGoodsPkgList() {

        List<GoodsPkgInfoDO> list = null;
        MtOperateResult<List<MtGoodsPkgModel>> result = new MtOperateResult<List<MtGoodsPkgModel>>();
        try {
            list = goodsPkgInfoDAO.findAll();
        } catch (Exception e) {
            logger.error("查询所有套餐信息发生异常" + e.getMessage(), e);
            result.setOperateExResult(MtOperateExResultEnum.CAMP_SQL_EXE_INVALID);
            result.setOperateResult(MtOperateResultEnum.CAMP_OPERATE_FAILED);
            result.setErrorDetail(e.getMessage());
            return result;
        }

        if (CollectionUtils.isEmpty(list)) {
            logger.warn("系统当前未找到套餐信息，请核实相关数据！");
            result.setOperateExResult(MtOperateExResultEnum.CAMP_OPERATE_SUCCESS);
            result.setOperateResult(MtOperateResultEnum.CAMP_OPERATE_SUCCESS);
            return result;
        }

        MtGoodsPkgModel model = null;
        List<MtGoodsPkgModel> modelList = new ArrayList<MtGoodsPkgModel>();
        for (GoodsPkgInfoDO goodsPkgInfoDO : list) {
            model = MtGoodsPkgModelConvertor.convertDO2Model(goodsPkgInfoDO);

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
     * Setter method for property <tt>goodsPkgInfoDAO</tt>.
     * 
     * @param goodsPkgInfoDAO value to be assigned to property goodsPkgInfoDAO
     */
    public void setGoodsPkgInfoDAO(GoodsPkgInfoDAO goodsPkgInfoDAO) {
        this.goodsPkgInfoDAO = goodsPkgInfoDAO;
    }

}
