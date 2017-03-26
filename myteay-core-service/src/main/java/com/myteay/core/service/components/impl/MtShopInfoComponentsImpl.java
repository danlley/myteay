/**
 * Myteay.com Inc.
 * Copyright (c) 2015-2016 All Rights Reserved.
 */
package com.myteay.core.service.components.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.myteay.common.service.facade.exceptions.MtBizException;
import com.myteay.common.service.facade.model.MtOperateResult;
import com.myteay.core.model.dinner.MtShopModel;
import com.myteay.core.model.dinner.repository.MtShopInfoRepository;
import com.myteay.core.service.components.MtShopInfoComponents;

/**
 * 店铺管理组件
 * 
 * @author Administrator
 * @version $Id: MtShopInfoComponentsImpl.java, v 0.1 2016年3月4日 下午5:19:56 Administrator Exp $
 */
public class MtShopInfoComponentsImpl implements MtShopInfoComponents {

    /** 店铺信息仓储层 */
    @Autowired
    private MtShopInfoRepository mtShopInfoRepository;

    /** 
     * @see com.myteay.core.service.components.MtShopInfoComponents#queryShopList()
     */
    @Override
    public MtOperateResult<List<MtShopModel>> queryShopList() throws MtBizException {
        return mtShopInfoRepository.queryShopList();
    }

    /**
     * Setter method for property <tt>mtShopInfoRepository</tt>.
     * 
     * @param mtShopInfoRepository value to be assigned to property mtShopInfoRepository
     */
    public void setMtShopInfoRepository(MtShopInfoRepository mtShopInfoRepository) {
        this.mtShopInfoRepository = mtShopInfoRepository;
    }
}
