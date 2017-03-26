/**
 * Myteay.com Inc.
 * Copyright (c) 2015-2016 All Rights Reserved.
 */
package com.myteay.core.service.cache.components.impl;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;

import com.myteay.common.service.facade.enums.MtSysKeyEnum;
import com.myteay.common.util.comm.CollectionUtils;
import com.myteay.core.model.repository.MtUserSysDictInfoRepository;
import com.myteay.core.service.cache.components.MtSysKeysCacheComponents;

/**
 * 构建系统字典的缓存信息
 * 
 * @author Administrator
 * @version $Id: MtSysKeysCacheComponentsImpl.java, v 0.1 2016年9月7日 上午12:20:25 Administrator Exp $
 */
public class MtSysKeysCacheComponentsImpl implements MtSysKeysCacheComponents,
                                         ApplicationListener<ContextRefreshedEvent> {

    /** 日志 */
    public static final Logger               logger        = Logger
                                                               .getLogger(MtSysKeysCacheComponentsImpl.class);

    /** 系统字典的缓存信息 */
    private static final Map<String, String> SYS_KEY_CACHE = Collections
                                                               .synchronizedMap(new HashMap<String, String>());

    /** 用户字典仓储 */
    private MtUserSysDictInfoRepository      mtUserSysDictInfoRepository;

    /** 
     * @see com.myteay.core.service.cache.components.MtSysKeysCacheComponents#getValueByKey(com.myteay.common.service.facade.enums.MtSysKeyEnum)
     */
    @Override
    public String getValueByKey(MtSysKeyEnum key) {

        if (key == null) {
            return null;
        }

        if (CollectionUtils.isEmpty(SYS_KEY_CACHE)) {
            refreshCache();
        }
        return SYS_KEY_CACHE.get(key.getValue());
    }

    /** 
     * @see org.springframework.context.ApplicationListener#onApplicationEvent(org.springframework.context.ApplicationEvent)
     */
    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {

        if (logger.isInfoEnabled()) {
            logger.info("开始加载数据字典进入缓存");
        }

        refreshCache();

        if (logger.isInfoEnabled()) {
            logger.info("数据字典的缓存初始化结束");
        }
    }

    /**
     * 刷新数据字典缓存
     */
    private void refreshCache() {
        Map<String, String> map = mtUserSysDictInfoRepository.findAll();
        if (CollectionUtils.isEmpty(map)) {
            logger.warn("当前系统未找到任何数据字典用于初始化系统缓存信息");
            return;
        }

        //锁定缓存并刷新，防止并发异常
        synchronized (SYS_KEY_CACHE) {
            SYS_KEY_CACHE.putAll(map);
        }

        logger.warn("缓存刷新结束 SYS_KEY_CACHE=" + SYS_KEY_CACHE);
    }

    /**
     * Setter method for property <tt>mtUserSysDictInfoRepository</tt>.
     * 
     * @param mtUserSysDictInfoRepository value to be assigned to property mtUserSysDictInfoRepository
     */
    public void setMtUserSysDictInfoRepository(MtUserSysDictInfoRepository mtUserSysDictInfoRepository) {
        this.mtUserSysDictInfoRepository = mtUserSysDictInfoRepository;
    }
}
