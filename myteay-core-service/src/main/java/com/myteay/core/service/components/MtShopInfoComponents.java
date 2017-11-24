/**
 * Myteay.com Inc.
 * Copyright (c) 2015-2016 All Rights Reserved.
 */
package com.myteay.core.service.components;

import java.util.List;

import com.myteay.common.service.facade.exceptions.MtBizException;
import com.myteay.common.service.facade.model.MtOperateResult;
import com.myteay.core.model.dinner.MtShopModel;

/**
 * 店铺管理组件
 * 
 * @author Administrator
 * @version $Id: MtShopInfoComponents.java, v 0.1 2016年3月4日 下午5:19:21 Administrator Exp $
 */
public interface MtShopInfoComponents {

    /**
     * 查询所有的店铺信息
     * 
     * @return  返回店铺信息列表
     */
    public MtOperateResult<List<MtShopModel>> queryShopList() throws MtBizException;
}
