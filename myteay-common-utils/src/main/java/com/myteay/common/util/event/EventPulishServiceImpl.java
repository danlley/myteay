/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2012 All Rights Reserved.
 */
package com.myteay.common.util.event;

import org.apache.log4j.Logger;
import org.springframework.core.task.TaskExecutor;

import com.myteay.common.utils.exception.MtException;

/**
 * 异步事件执行服务（默认支持同步）
 * 
 * @author min.weixm
 * @version $Id: EventPulishServiceImpl.java, v 0.1 2012-11-3 下午09:00:26 min.weixm Exp $
 * @param <T>
 */
public class EventPulishServiceImpl<T> implements EventPulishService<T> {

    /** 日志 */
    public static final Logger      logger = Logger.getLogger(EventPulishServiceImpl.class);

    /** 监听器描述文件缓存 */
    private ListenerDescriptorCache listenerDescriptorCache;

    /** 
     * @throws MtException 
     * @see com.alipay.promocore.core.model.event.EventPulishService#publishEvent(com.alipay.promocore.core.model.event.MtEvent)
     */
    @SuppressWarnings("unchecked")
    @Override
    public T publishEvent(MtEvent<?> event) throws MtException {

        if (event == null) {
            logger.warn("PromocoreEvent is null，默认不执行");
            return null;
        }

        String topic = event.getTopic();

        ListenerDescriptor descriptor = listenerDescriptorCache.getListenerDescriptor(topic);
        if (descriptor == null) {
            logger.warn("扩展描述文件配置不可为空，topic=" + topic);
            throw new MtException("扩展描述文件配置不可为空，topic=" + topic);
        }

        //扩展点在开发配置过程已进行校验，这里不需要重复校验
        EventListener<T> listener = (EventListener<T>) descriptor.getListener();

        //异步执行处理
        if (descriptor.isAsync()) {

            //扩展点在开发配置过程已进行校验，这里不需要重复校验
            TaskExecutor taskExecutor = descriptor.getTaskExecutor();

            try {
                taskExecutor.execute(new EventListenerExecutor(listener, event));
            } catch (RuntimeException e) {
                logger.warn("事件发布器发布事件发生运行时异常，极可能是异步运行时队列大小不够RuntimeException" + event, e);
            } catch (Exception e) {
                logger.warn("事件发布器发布事件时发生异常Exception" + event, e);
            }
        }
        //同步执行处理
        else {
            try {
                if (listener.aboutToHandleEvent(event)) {
                    return listener.handleEvent(event);
                }
            } catch (Exception e) {
                logger.warn("同步执行监听器失败Exception，" + e.getMessage(), e);
            }
        }

        //默认情况下返回值为空，只有当同步执行的情况下才会有返回值
        return null;

    }

    /**
     * Setter method for property <tt>listenerDescriptorCache</tt>.
     * 
     * @param listenerDescriptorCache value to be assigned to property listenerDescriptorCache
     */
    public void setListenerDescriptorCache(ListenerDescriptorCache listenerDescriptorCache) {
        this.listenerDescriptorCache = listenerDescriptorCache;
    }
}
