/**
 * Myteay.com Inc.
 * Copyright (c) 2015-2016 All Rights Reserved.
 */
package com.myteay.core.service.cache.components.impl;

import org.apache.log4j.Logger;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;

import com.myteay.common.service.facade.exceptions.MtBizException;
import com.myteay.core.service.cache.components.MtGoodsPkgCacheComponents;

/**
 * 套餐缓存信息执行组件
 * 
 * @author Administrator
 * @version $Id: MtGoodsPkgCacheComponentsImpl.java, v 0.1 2016年3月20日 下午9:46:35 Administrator Exp $
 */
public class MtGoodsPkgCacheComponentsImpl implements MtGoodsPkgCacheComponents,
                                          ApplicationListener<ContextRefreshedEvent> {

    /** 日志 */
    public static final Logger logger = Logger.getLogger(MtGoodsPkgCacheComponentsImpl.class);

    /** 
     * @see com.myteay.core.service.cache.components.MtGoodsPkgCacheComponents#refreshCache()
     */
    @Override
    public void refreshCache() throws MtBizException {
        logger.warn("开始即时刷新缓存");
    }

    /** 
     * @see org.springframework.context.ApplicationListener#onApplicationEvent(org.springframework.context.ApplicationEvent)
     */
    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {

        if (logger.isInfoEnabled()) {
            logger.info("系统所有组件加载结束，开始初始化套餐信息缓存");
        }

        try {
            refreshCache();
        } catch (MtBizException e) {
            logger.error("系统在初始化过程中，缓存刷新过程出现异常", e);
        }

        if (logger.isInfoEnabled()) {
            logger.info("系统启动过程中，加载套餐信息缓存任务处理结束");
        }
    }

}
