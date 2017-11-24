/**
 * Myteay.com Inc.
 * Copyright (c) 2015-2016 All Rights Reserved.
 */
package com.myteay.core.service.components;

import java.util.List;

import com.myteay.common.service.facade.exceptions.MtBizException;
import com.myteay.common.service.facade.model.MtOperateResult;
import com.myteay.common.service.facade.model.dinner.MtGoodsPkgInfoMessage;
import com.myteay.core.model.dinner.MtGoodsPkgModel;

/**
 * 套餐信息管理组件
 * 
 * @author Administrator
 * @version $Id: MtGoodsPkgInfoComponents.java, v 0.1 2016年3月5日 上午9:41:17 Administrator Exp $
 */
public interface MtGoodsPkgInfoComponents {

    /**
     * 通过套餐流水号删除套餐信息
     * 
     * @param id    套餐流水号
     * @return      处理结果
     */
    public MtOperateResult<String> deleteGoodsPkgById(String id);

    /**
     * 通过套餐流水号查询套餐信息交互单据
     * 
     * @param id    套餐流水号
     * @return      套餐信息交互单据
     */
    public MtOperateResult<MtGoodsPkgInfoMessage> findGoodsPkgById(String id) throws MtBizException;

    /**
     * 保存套餐信息交互单据
     * 
     * @param message   套餐信息交互单据
     * @return          处理结果
     */
    public MtOperateResult<String> saveGoodsPkgMessage(MtGoodsPkgInfoMessage message);

    /**
     * 查询所有的套餐信息
     * 
     * @return  返回套餐信息列表
     */
    public MtOperateResult<List<MtGoodsPkgModel>> queryGoodsPkgList() throws MtBizException;
}
