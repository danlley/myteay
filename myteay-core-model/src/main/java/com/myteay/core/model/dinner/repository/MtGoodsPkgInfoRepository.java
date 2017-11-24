/**
 * Myteay.com Inc.
 * Copyright (c) 2015-2016 All Rights Reserved.
 */
package com.myteay.core.model.dinner.repository;

import java.util.List;

import com.myteay.common.service.facade.model.MtOperateResult;
import com.myteay.core.model.dinner.MtGoodsPkgModel;

/**
 * 套餐信息管理仓储层
 * 
 * @author Administrator
 * @version $Id: MtGoodsPkgInfoRepository.java, v 0.1 2016年3月5日 上午9:31:33 Administrator Exp $
 */
public interface MtGoodsPkgInfoRepository {

    /**
     * 通过套餐流水号删除套餐信息
     * 
     * @param id    套餐流水号
     * @return      处理结果
     */
    public MtOperateResult<String> deleteGoodsPkgById(String id);

    /**
     * 通过套餐ID查找套餐模型
     * 
     * @param id    套餐ID
     * @return      套餐模型
     */
    public MtOperateResult<MtGoodsPkgModel> findGoodsInfoById(String id);

    /**
     * 存储套餐信息模型
     * 
     * @param model 存储套餐信息模型
     * @return      保存后存储套餐信息流水号及详细返回结果
     */
    public MtOperateResult<String> saveGoodsPkgModel(MtGoodsPkgModel model);

    /**
     * 查询所有的套餐信息
     * 
     * @return  返回套餐信息列表
     */
    public MtOperateResult<List<MtGoodsPkgModel>> queryGoodsPkgList();
}
