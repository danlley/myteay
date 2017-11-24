/**
 * Myteay.com Inc.
 * Copyright (c) 2015-2016 All Rights Reserved.
 */
package com.myteay.core.model.dinner.repository;

import java.util.List;

import com.myteay.common.service.facade.model.MtOperateResult;
import com.myteay.core.model.dinner.MtShopModel;

/**
 * 店铺信息仓储层
 * 
 * @author Administrator
 * @version $Id: MtShopInfoRepository.java, v 0.1 2016年3月4日 下午5:13:37 Administrator Exp $
 */
public interface MtShopInfoRepository {

    /**
     * 查询所有的店铺信息
     * 
     * @return  返回店铺信息列表
     */
    public MtOperateResult<List<MtShopModel>> queryShopList();
}
