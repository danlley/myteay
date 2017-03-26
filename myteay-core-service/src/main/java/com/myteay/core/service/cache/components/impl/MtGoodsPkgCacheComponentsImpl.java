/**
 * Myteay.com Inc.
 * Copyright (c) 2015-2016 All Rights Reserved.
 */
package com.myteay.core.service.cache.components.impl;

import org.apache.log4j.Logger;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;

import com.myteay.common.service.facade.exceptions.MtBizException;
import com.myteay.core.service.cache.components.MtGoodsPkgCacheComponents;

/**
 * �ײͻ�����Ϣִ�����
 * 
 * @author Administrator
 * @version $Id: MtGoodsPkgCacheComponentsImpl.java, v 0.1 2016��3��20�� ����9:46:35 Administrator Exp $
 */
public class MtGoodsPkgCacheComponentsImpl implements MtGoodsPkgCacheComponents,
                                          ApplicationListener<ContextRefreshedEvent> {

    /** ��־ */
    public static final Logger logger = Logger.getLogger(MtGoodsPkgCacheComponentsImpl.class);

    /** 
     * @see com.myteay.core.service.cache.components.MtGoodsPkgCacheComponents#refreshCache()
     */
    @Override
    public void refreshCache() throws MtBizException {
        logger.warn("��ʼ��ʱˢ�»���");
    }

    /** 
     * @see org.springframework.context.ApplicationListener#onApplicationEvent(org.springframework.context.ApplicationEvent)
     */
    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {

        if (logger.isInfoEnabled()) {
            logger.info("ϵͳ����������ؽ�������ʼ��ʼ���ײ���Ϣ����");
        }

        try {
            refreshCache();
        } catch (MtBizException e) {
            logger.error("ϵͳ�ڳ�ʼ�������У�����ˢ�¹��̳����쳣", e);
        }

        if (logger.isInfoEnabled()) {
            logger.info("ϵͳ���������У������ײ���Ϣ�������������");
        }
    }

}
