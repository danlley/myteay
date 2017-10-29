/**
 * Myteay.com Inc.
 * Copyright (c) 2015-2016 All Rights Reserved.
 */
package com.myteay.core.service.cache.listeners;

import com.myteay.common.async.event.EventListener;
import com.myteay.common.async.event.MtEvent;
import com.myteay.common.service.facade.exceptions.MtBizException;
import com.myteay.core.service.cache.components.MtGoodsPkgCacheComponents;

/**
 * �ײ���Ϣ���������
 * 
 * @author Administrator
 * @version $Id: MtGoodsPkgEventListener.java, v 0.1 2016��3��20�� ����9:20:17 Administrator Exp $
 */
public class MtGoodsPkgEventListener extends EventListener<Object> {

    /** �ײͻ�����Ϣִ����� */
    private MtGoodsPkgCacheComponents mtGoodsPkgCacheComponents;

    /** 
     * @see com.myteay.common.util.event.EventListener#handleEvent(com.myteay.common.util.event.MtEvent)
     */
    @Override
    public Object handleEvent(MtEvent<?> event) {

        if (logger.isInfoEnabled()) {
            logger.info("�յ�ˢ���ײ���Ϣ���������󣬿�ʼˢ���ײ���Ϣ���� event=" + event);
        }

        try {
            mtGoodsPkgCacheComponents.refreshCache();
        } catch (MtBizException e) {
            logger.warn("������Ϣˢ��ʧ�ܣ�event=" + event + "\r\n" + e.getMessage(), e);
        }

        if (logger.isInfoEnabled()) {
            logger.info("ˢ���ײ���Ϣ���������������");
        }

        return null;
    }

    /**
     * Setter method for property <tt>mtGoodsPkgCacheComponents</tt>.
     * 
     * @param mtGoodsPkgCacheComponents value to be assigned to property mtGoodsPkgCacheComponents
     */
    public void setMtGoodsPkgCacheComponents(MtGoodsPkgCacheComponents mtGoodsPkgCacheComponents) {
        this.mtGoodsPkgCacheComponents = mtGoodsPkgCacheComponents;
    }
}
