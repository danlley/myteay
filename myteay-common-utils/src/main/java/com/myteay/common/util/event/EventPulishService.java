/**
 * Copyright (c) 2004-2012 All Rights Reserved.
 */
package com.myteay.common.util.event;

import com.myteay.common.utils.exception.MtException;

/**
 * �첽�¼�ִ�з���Ĭ��֧��ͬ����
 * 
 * @author min.weixm
 * @version $Id: EventPulishService.java, v 0.1 2012-11-3 ����09:00:54 min.weixm Exp $
 */
public interface EventPulishService<T> {

    /**
     * �����¼�
     * 
     * @param event     �첽�¼�ģ��
     */
    public T publishEvent(MtEvent<?> event) throws MtException;

}
