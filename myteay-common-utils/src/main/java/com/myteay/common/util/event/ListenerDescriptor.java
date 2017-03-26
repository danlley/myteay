/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2012 All Rights Reserved.
 */
package com.myteay.common.util.event;

import java.io.Serializable;

import org.springframework.core.task.TaskExecutor;

import com.myteay.common.util.comm.ToStringUtil;

/**
 * �¼���������
 * 
 * @author min.weixm
 * @version $Id: ListenerDescriptor.java, v 0.1 2012-11-9 ����10:39:17 min.weixm Exp $
 */
public class ListenerDescriptor implements Serializable {

    /** serialVersionUID */
    private static final long serialVersionUID = 5675513576693140655L;

    /** �¼����� */
    private String            topic;

    /** �Ƿ���Ҫ���첽���� */
    private boolean           async;

    /** �¼������� */
    private EventListener<?>  listener;

    /** �����첽�¼����������ִ����������Ϊ�첽����ʱ�������ø����� */
    private TaskExecutor      taskExecutor;

    /**
     * Getter method for property <tt>topic</tt>.
     * 
     * @return property value of topic
     */
    public String getTopic() {
        return topic;
    }

    /**
     * Setter method for property <tt>topic</tt>.
     * 
     * @param topic value to be assigned to property topic
     */
    public void setTopic(String topic) {
        this.topic = topic;
    }

    /**
     * Getter method for property <tt>async</tt>.
     * 
     * @return property value of async
     */
    public boolean isAsync() {
        return async;
    }

    /**
     * Setter method for property <tt>async</tt>.
     * 
     * @param async value to be assigned to property async
     */
    public void setAsync(boolean async) {
        this.async = async;
    }

    /**
     * Getter method for property <tt>listener</tt>.
     * 
     * @return property value of listener
     */
    public EventListener<?> getListener() {
        return listener;
    }

    /**
     * Setter method for property <tt>listener</tt>.
     * 
     * @param listener value to be assigned to property listener
     */
    public void setListener(EventListener<?> listener) {
        this.listener = listener;
    }

    /**
     * Getter method for property <tt>taskExecutor</tt>.
     * 
     * @return property value of taskExecutor
     */
    public TaskExecutor getTaskExecutor() {
        return taskExecutor;
    }

    /**
     * Setter method for property <tt>taskExecutor</tt>.
     * 
     * @param taskExecutor value to be assigned to property taskExecutor
     */
    public void setTaskExecutor(TaskExecutor taskExecutor) {
        this.taskExecutor = taskExecutor;
    }

    /** 
     * @see java.lang.Object#toString()
     */
    public String toString() {
        return ToStringUtil.toShortString(this);
    }
}
