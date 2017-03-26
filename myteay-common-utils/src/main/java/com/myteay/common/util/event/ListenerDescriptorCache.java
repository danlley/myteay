/**
 * Myteay.com Inc.
 * Copyright (c) 2015-2016 All Rights Reserved.
 */
package com.myteay.common.util.event;

import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.util.CollectionUtils;

import com.myteay.common.util.comm.StringUtils;
import com.myteay.common.utils.exception.MtException;

/**
 * 监听器描述文件缓存
 * 
 * @author Administrator
 * @version $Id: ListenerDescriptorCache.java, v 0.1 2016年3月20日 下午8:57:22 Administrator Exp $
 */
public class ListenerDescriptorCache {

    /** 日志 */
    public static final Logger              logger         = Logger
                                                               .getLogger(ListenerDescriptorCache.class);

    /** 事件主题与事件监听器映射关系 */
    private Map<String, ListenerDescriptor> topicListeners = new HashMap<String, ListenerDescriptor>();

    /**
     * 通过事件主题得到监听器描述文件
     * 
     * @param topic         事件主题
     * @return              监听器描述文件
     * @throws MtException  异常
     */
    public ListenerDescriptor getListenerDescriptor(String topic) throws MtException {

        if (StringUtils.isBlank(topic)) {
            logger.error("用于查找监听器的主题信息不存在，无法得到监听器描述文件，topic is null");
            return null;
        }

        if (CollectionUtils.isEmpty(topicListeners)) {
            logger.warn("topicListeners must not be null");
            throw new MtException("topicListeners must not be null");
        }
        return topicListeners.get(topic);
    }

    /**
     * 注册监听器
     * 
     * @param descriptor    异步线程池
     * @throws Exception    异常处理
     */
    public void registerExtension(ListenerDescriptor descriptor, EventListener<?> listener)
                                                                                           throws Exception {

        if (descriptor == null || listener == null) {
            logger.error("事件描述文件不可用 descriptor is null! descriptor=" + descriptor + " listener="
                         + listener);

            return;
        }

        if (StringUtils.isBlank(descriptor.getTopic())) {
            logger.error("监听器注册失败，topic=" + descriptor.getTopic() + " async="
                         + descriptor.isAsync() + " taskExecutor=" + descriptor.getTaskExecutor()
                         + " listener=" + listener);
            return;
        }

        if (descriptor.isAsync() && descriptor.getTaskExecutor() == null) {
            logger.error("异步监听器需要响应的异步线程池、队列配置信息。taskExecutor is null! topic="
                         + descriptor.getTopic());
            return;
        }

        //设置监听器信息
        descriptor.setListener(listener);
        topicListeners.put(descriptor.getTopic(), descriptor);

        logger.warn("监听器注册成功：topic=" + descriptor.getTopic() + " descriptor=" + descriptor);

    }
}
