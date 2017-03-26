/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2012 All Rights Reserved.
 */
package com.myteay.common.util.event;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.InitializingBean;

/**
 * 事件执行抽象接口
 * 
 * @author min.weixm
 * @version $Id: EventListener.java, v 0.1 2012-11-3 下午08:50:00 min.weixm Exp $
 */
public abstract class EventListener<T> implements InitializingBean {

    /** 日志 */
    public static final Logger      logger = Logger.getLogger(EventListener.class);

    /** 监听器描述文件缓存 */
    private ListenerDescriptorCache listenerDescriptorCache;

    /** 监听器描述文件 */
    private ListenerDescriptor      descriptor;

    /**
     * 判断是否处理该事件
     * @param event 待处理事件
     * @return 返回true，表示需要处理该事件；返回false，表示不处理该事件
     */
    public boolean aboutToHandleEvent(MtEvent<?> event) {
        return true;
    }

    /**
     * 处理事件
     * @param event 待处理的事件
     * @return      用于支持同步的返回值
     */
    public abstract T handleEvent(MtEvent<?> event);

    /** 
     * @see org.springframework.beans.factory.InitializingBean#afterPropertiesSet()
     */
    @Override
    public void afterPropertiesSet() throws Exception {
        try {
            listenerDescriptorCache.registerExtension(descriptor, this);
        } catch (Exception e) {
            logger.error("当前事件描述文件注册失败：descriptor=" + descriptor, e);
        }
    }

    /**
     * Setter method for property <tt>listenerDescriptorCache</tt>.
     * 
     * @param listenerDescriptorCache value to be assigned to property listenerDescriptorCache
     */
    public void setListenerDescriptorCache(ListenerDescriptorCache listenerDescriptorCache) {
        this.listenerDescriptorCache = listenerDescriptorCache;
    }

    /**
     * Setter method for property <tt>descriptor</tt>.
     * 
     * @param descriptor value to be assigned to property descriptor
     */
    public void setDescriptor(ListenerDescriptor descriptor) {
        this.descriptor = descriptor;
    }
}
