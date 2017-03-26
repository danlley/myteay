/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2010 All Rights Reserved.
 */
package com.myteay.common.util.event;

import org.apache.log4j.Logger;

/**
 * �¼�ִ����</br>
 * �¼�ִ����ʵ����{@link Runnable}�ӿڣ�����������Զ�����ִ�У�����һ���¼�ִ����ʱ��Ҫ�����и��¼�������ʱ��Ҫ���¼�������¼���������
 * 
 * @author min.weixm
 * @version $Id: EventListenerExecutor.java, v 0.1 2012-11-3 ����08:53:25 min.weixm Exp $
 */
public class EventListenerExecutor implements Runnable {

    /** ��־ */
    public static final Logger logger = Logger.getLogger(EventListenerExecutor.class);

    /** �¼������� */
    private EventListener<?>   eventListener;

    /** �¼�ģ�� */
    private MtEvent<?>  event;

    /**
     * ���췽��
     * @param eventListener     ������
     * @param event             �첽�¼�ģ��
     */
    public EventListenerExecutor(EventListener<?> eventListener, MtEvent<?> event) {
        super();
        this.eventListener = eventListener;
        this.event = event;
    }

    /**
     * @see java.lang.Runnable#run()
     */
    public void run() {

        try {
            if (eventListener == null) {
                logger.warn("�¼�û���ҵ���Ӧ��ִ�м�������event" + event);
                return;
            }

            if (eventListener.aboutToHandleEvent(event)) {
                eventListener.handleEvent(event);
            }

        } catch (Exception e) {
            logger.error("�¼�ִ��������ʱ�����쳣 event=" + event + ", eventListener=" + eventListener, e);
        }
    }
}
