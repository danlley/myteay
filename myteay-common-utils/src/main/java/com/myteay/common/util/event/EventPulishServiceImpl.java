/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2012 All Rights Reserved.
 */
package com.myteay.common.util.event;

import org.apache.log4j.Logger;
import org.springframework.core.task.TaskExecutor;

import com.myteay.common.utils.exception.MtException;

/**
 * �첽�¼�ִ�з���Ĭ��֧��ͬ����
 * 
 * @author min.weixm
 * @version $Id: EventPulishServiceImpl.java, v 0.1 2012-11-3 ����09:00:26 min.weixm Exp $
 * @param <T>
 */
public class EventPulishServiceImpl<T> implements EventPulishService<T> {

    /** ��־ */
    public static final Logger      logger = Logger.getLogger(EventPulishServiceImpl.class);

    /** �����������ļ����� */
    private ListenerDescriptorCache listenerDescriptorCache;

    /** 
     * @throws MtException 
     * @see com.alipay.promocore.core.model.event.EventPulishService#publishEvent(com.alipay.promocore.core.model.event.MtEvent)
     */
    @SuppressWarnings("unchecked")
    @Override
    public T publishEvent(MtEvent<?> event) throws MtException {

        if (event == null) {
            logger.warn("PromocoreEvent is null��Ĭ�ϲ�ִ��");
            return null;
        }

        String topic = event.getTopic();

        ListenerDescriptor descriptor = listenerDescriptorCache.getListenerDescriptor(topic);
        if (descriptor == null) {
            logger.warn("��չ�����ļ����ò���Ϊ�գ�topic=" + topic);
            throw new MtException("��չ�����ļ����ò���Ϊ�գ�topic=" + topic);
        }

        //��չ���ڿ������ù����ѽ���У�飬���ﲻ��Ҫ�ظ�У��
        EventListener<T> listener = (EventListener<T>) descriptor.getListener();

        //�첽ִ�д���
        if (descriptor.isAsync()) {

            //��չ���ڿ������ù����ѽ���У�飬���ﲻ��Ҫ�ظ�У��
            TaskExecutor taskExecutor = descriptor.getTaskExecutor();

            try {
                taskExecutor.execute(new EventListenerExecutor(listener, event));
            } catch (RuntimeException e) {
                logger.warn("�¼������������¼���������ʱ�쳣�����������첽����ʱ���д�С����RuntimeException" + event, e);
            } catch (Exception e) {
                logger.warn("�¼������������¼�ʱ�����쳣Exception" + event, e);
            }
        }
        //ͬ��ִ�д���
        else {
            try {
                if (listener.aboutToHandleEvent(event)) {
                    return listener.handleEvent(event);
                }
            } catch (Exception e) {
                logger.warn("ͬ��ִ�м�����ʧ��Exception��" + e.getMessage(), e);
            }
        }

        //Ĭ������·���ֵΪ�գ�ֻ�е�ͬ��ִ�е�����²Ż��з���ֵ
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
