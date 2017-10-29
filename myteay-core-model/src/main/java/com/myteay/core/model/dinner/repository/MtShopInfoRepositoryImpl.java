/**
 * Myteay.com Inc.
 * Copyright (c) 2015-2016 All Rights Reserved.
 */
package com.myteay.core.model.dinner.repository;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.util.CollectionUtils;

import com.myteay.common.dal.dinner.daointerface.ShopInfoDAO;
import com.myteay.common.dal.dinner.dataobject.ShopInfoDO;
import com.myteay.common.service.facade.enums.MtOperateExResultEnum;
import com.myteay.common.service.facade.enums.MtOperateResultEnum;
import com.myteay.common.service.facade.model.MtOperateResult;
import com.myteay.core.model.dinner.MtShopModel;
import com.myteay.core.model.user.convt.MtShopModelConvertor;

/**
 * 店铺信息仓储层
 * 
 * @author Administrator
 * @version $Id: MtShopInfoRepositoryImpl.java, v 0.1 2016年3月4日 下午5:15:48 Administrator Exp $
 */
public class MtShopInfoRepositoryImpl implements MtShopInfoRepository {

    /** 日志 */
    public static final Logger logger = Logger.getLogger(MtShopInfoRepositoryImpl.class);

    /** 店铺信息操作DAO */
    private ShopInfoDAO        shopInfoDAO;

    /** 
     * @see com.myteay.core.model.dinner.repository.MtShopInfoRepository#queryShopList()
     */
    @Override
    public MtOperateResult<List<MtShopModel>> queryShopList() {

        MtOperateResult<List<MtShopModel>> result = new MtOperateResult<List<MtShopModel>>();
        List<ShopInfoDO> list = null;
        try {
            list = shopInfoDAO.findAll();
        } catch (Exception e) {
            logger.error("查询所有店铺信息出错 " + e.getMessage(), e);
            result.setOperateExResult(MtOperateExResultEnum.CAMP_SQL_EXE_INVALID);
            result.setOperateResult(MtOperateResultEnum.CAMP_OPERATE_FAILED);
            result.setErrorDetail(e.getMessage());
            return result;
        }

        if (CollectionUtils.isEmpty(list)) {
            logger.warn("系统当前未找到店铺信息，请核实相关数据！");
            result.setOperateExResult(MtOperateExResultEnum.CAMP_OPERATE_SUCCESS);
            result.setOperateResult(MtOperateResultEnum.CAMP_OPERATE_SUCCESS);
            return result;
        }

        MtShopModel model = null;
        List<MtShopModel> modelList = new ArrayList<MtShopModel>();
        for (ShopInfoDO shopInfoDO : list) {
            model = MtShopModelConvertor.convertDO2Model(shopInfoDO);

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
     * Setter method for property <tt>shopInfoDAO</tt>.
     * 
     * @param shopInfoDAO value to be assigned to property shopInfoDAO
     */
    public void setShopInfoDAO(ShopInfoDAO shopInfoDAO) {
        this.shopInfoDAO = shopInfoDAO;
    }
}
