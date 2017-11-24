/**
 * Myteay.com Inc.
 * Copyright (c) 2015-2016 All Rights Reserved.
 */
package com.myteay.core.model.dinner.repository;

import java.util.List;

import com.myteay.common.service.facade.model.MtOperateResult;
import com.myteay.core.model.dinner.MtGoodsModel;

/**
 * 单品信息单品管理仓储层
 * 
 * @author Administrator
 * @version $Id: MtGoodsInfoRepository.java, v 0.1 2016年3月5日 上午12:24:56 Administrator Exp $
 */
public interface MtGoodsInfoRepository {
    /**
     * 通过ID删除指定单品信息
     * 
     * @param id    单品ID
     * @return      删除结果
     */
    public MtOperateResult<String> removeGoodsInfoById(String id);

    /**
     * 通过ID查找指定单品信息
     * 
     * @param id    单品信息流水号
     * @return      单品信息模型
     */
    public MtOperateResult<MtGoodsModel> findGoodsInfoById(String id);

    /**
     * 保存单品单品信息
     * 
     * @param model 单品信息模型
     * @return      处理结果
     */
    public MtOperateResult<String> saveGoodsInfo(MtGoodsModel model);

    /**
     * 查询所有的单品信息
     * 
     * @return  返回单品信息列表
     */
    public MtOperateResult<List<MtGoodsModel>> queryGoodsList();
}
