/**
 * Myteay.com Inc.
 * Copyright (c) 2015-2016 All Rights Reserved.
 */
package com.myteay.core.service.components;

import java.util.List;

import com.myteay.common.service.facade.exceptions.MtBizException;
import com.myteay.common.service.facade.model.MtOperateResult;
import com.myteay.common.service.facade.model.dinner.MtGoodsInfoMessage;
import com.myteay.core.model.dinner.MtGoodsModel;

/**
 * 商品信息管理组件
 * 
 * @author Administrator
 * @version $Id: MtGoodsInfoComponents.java, v 0.1 2016年3月5日 上午12:54:07 Administrator Exp $
 */
public interface MtGoodsInfoComponents {

    /**
     * 通过ID删除指定单品信息
     * 
     * @param id    单品ID
     * @return      删除结果
     */
    public MtOperateResult<String> removeGoodsInfoById(String id);

    /**
     * 通过套餐流水号查找套餐详情
     * 
     * @param id    套餐流水号
     * @return      套餐信息交互单据
     */
    public MtOperateResult<MtGoodsInfoMessage> findGoodsInfoById(String id);

    /**
     * 保存商品单品信息
     * 
     * @param message   商品信息模型
     * @return          处理结果
     */
    public MtOperateResult<String> saveGoodsInfo(MtGoodsInfoMessage message);

    /**
     * 查询所有的商品信息
     * 
     * @return  返回商品信息列表
     */
    public MtOperateResult<List<MtGoodsModel>> queryGoodsList() throws MtBizException;
}
