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
 * �����������ļ�����
 * 
 * @author Administrator
 * @version $Id: ListenerDescriptorCache.java, v 0.1 2016��3��20�� ����8:57:22 Administrator Exp $
 */
public class ListenerDescriptorCache {

    /** ��־ */
    public static final Logger              logger         = Logger
                                                               .getLogger(ListenerDescriptorCache.class);

    /** �¼��������¼�������ӳ���ϵ */
    private Map<String, ListenerDescriptor> topicListeners = new HashMap<String, ListenerDescriptor>();

    /**
     * ͨ���¼�����õ������������ļ�
     * 
     * @param topic         �¼�����
     * @return              �����������ļ�
     * @throws MtException  �쳣
     */
    public ListenerDescriptor getListenerDescriptor(String topic) throws MtException {

        if (StringUtils.isBlank(topic)) {
            logger.error("���ڲ��Ҽ�������������Ϣ�����ڣ��޷��õ������������ļ���topic is null");
            return null;
        }

        if (CollectionUtils.isEmpty(topicListeners)) {
            logger.warn("topicListeners must not be null");
            throw new MtException("topicListeners must not be null");
        }
        return topicListeners.get(topic);
    }

    /**
     * ע�������
     * 
     * @param descriptor    �첽�̳߳�
     * @throws Exception    �쳣����
     */
    public void registerExtension(ListenerDescriptor descriptor, EventListener<?> listener)
                                                                                           throws Exception {

        if (descriptor == null || listener == null) {
            logger.error("�¼������ļ������� descriptor is null! descriptor=" + descriptor + " listener="
                         + listener);

            return;
        }

        if (StringUtils.isBlank(descriptor.getTopic())) {
            logger.error("������ע��ʧ�ܣ�topic=" + descriptor.getTopic() + " async="
                         + descriptor.isAsync() + " taskExecutor=" + descriptor.getTaskExecutor()
                         + " listener=" + listener);
            return;
        }

        if (descriptor.isAsync() && descriptor.getTaskExecutor() == null) {
            logger.error("�첽��������Ҫ��Ӧ���첽�̳߳ء�����������Ϣ��taskExecutor is null! topic="
                         + descriptor.getTopic());
            return;
        }

        //���ü�������Ϣ
        descriptor.setListener(listener);
        topicListeners.put(descriptor.getTopic(), descriptor);

        logger.warn("������ע��ɹ���topic=" + descriptor.getTopic() + " descriptor=" + descriptor);

    }
}
