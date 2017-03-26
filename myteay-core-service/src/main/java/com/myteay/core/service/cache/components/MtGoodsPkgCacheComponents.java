/**
 * Myteay.com Inc.
 * Copyright (c) 2015-2016 All Rights Reserved.
 */
package com.myteay.core.service.cache.components;

import com.myteay.common.service.facade.exceptions.MtBizException;

/**
 * 套餐缓存信息执行组件
 * 
 * @author Administrator
 * @version $Id: MtGoodsPkgCacheComponents.java, v 0.1 2016年3月20日 下午9:17:36 Administrator Exp $
 */
public interface MtGoodsPkgCacheComponents {

    /**
     * 缓存刷新
     * 
     * @throws MtBizException
     */
    public void refreshCache() throws MtBizException;
}
