/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2012 All Rights Reserved.
 */
package com.myteay.common.util.event;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.InitializingBean;

/**
 * �¼�ִ�г���ӿ�
 * 
 * @author min.weixm
 * @version $Id: EventListener.java, v 0.1 2012-11-3 ����08:50:00 min.weixm Exp $
 */
public abstract class EventListener<T> implements InitializingBean {

    /** ��־ */
    public static final Logger      logger = Logger.getLogger(EventListener.class);

    /** �����������ļ����� */
    private ListenerDescriptorCache listenerDescriptorCache;

    /** �����������ļ� */
    private ListenerDescriptor      descriptor;

    /**
     * �ж��Ƿ�����¼�
     * @param event �������¼�
     * @return ����true����ʾ��Ҫ������¼�������false����ʾ��������¼�
     */
    public boolean aboutToHandleEvent(MtEvent<?> event) {
        return true;
    }

    /**
     * �����¼�
     * @param event ��������¼�
     * @return      ����֧��ͬ���ķ���ֵ
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
            logger.error("��ǰ�¼������ļ�ע��ʧ�ܣ�descriptor=" + descriptor, e);
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
