/**
 * Myteay.com Inc.
 * Copyright (c) 2015-2016 All Rights Reserved.
 */
package com.myteay.core.service.cache.listeners;

import com.myteay.common.service.facade.exceptions.MtBizException;
import com.myteay.common.util.event.EventListener;
import com.myteay.common.util.event.MtEvent;
import com.myteay.core.service.cache.components.MtGoodsPkgCacheComponents;

/**
 * 套餐信息缓存监听器
 * 
 * @author Administrator
 * @version $Id: MtGoodsPkgEventListener.java, v 0.1 2016年3月20日 下午9:20:17 Administrator Exp $
 */
public class MtGoodsPkgEventListener extends EventListener<Object> {

    /** 套餐缓存信息执行组件 */
    private MtGoodsPkgCacheComponents mtGoodsPkgCacheComponents;

    /** 
     * @see com.myteay.common.util.event.EventListener#handleEvent(com.myteay.common.util.event.MtEvent)
     */
    @Override
    public Object handleEvent(MtEvent<?> event) {

        if (logger.isInfoEnabled()) {
            logger.info("收到刷新套餐信息缓存变更请求，开始刷新套餐信息缓存 event=" + event);
        }

        try {
            mtGoodsPkgCacheComponents.refreshCache();
        } catch (MtBizException e) {
            logger.warn("缓存信息刷新失败，event=" + event + "\r\n" + e.getMessage(), e);
        }

        if (logger.isInfoEnabled()) {
            logger.info("刷新套餐信息缓存变更请求处理结束");
        }

        return null;
    }

    /**
     * Setter method for property <tt>mtGoodsPkgCacheComponents</tt>.
     * 
     * @param mtGoodsPkgCacheComponents value to be assigned to property mtGoodsPkgCacheComponents
     */
    public void setMtGoodsPkgCacheComponents(MtGoodsPkgCacheComponents mtGoodsPkgCacheComponents) {
        this.mtGoodsPkgCacheComponents = mtGoodsPkgCacheComponents;
    }
}
