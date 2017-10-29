/**
 * Myteay.com Inc.
 * Copyright (c) 2015-2016 All Rights Reserved.
 */
package com.myteay.core.service.cache.components.impl;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.util.CollectionUtils;

import com.myteay.common.service.facade.enums.MtSysKeyEnum;
import com.myteay.core.model.repository.MtUserSysDictInfoRepository;
import com.myteay.core.service.cache.components.MtSysKeysCacheComponents;

/**
 * ����ϵͳ�ֵ�Ļ�����Ϣ
 * 
 * @author Administrator
 * @version $Id: MtSysKeysCacheComponentsImpl.java, v 0.1 2016��9��7�� ����12:20:25 Administrator Exp $
 */
public class MtSysKeysCacheComponentsImpl implements MtSysKeysCacheComponents,
                                          ApplicationListener<ContextRefreshedEvent> {

    /** ��־ */
    public static final Logger               logger        = Logger
        .getLogger(MtSysKeysCacheComponentsImpl.class);

    /** ϵͳ�ֵ�Ļ�����Ϣ */
    private static final Map<String, String> SYS_KEY_CACHE = Collections
        .synchronizedMap(new HashMap<String, String>());

    /** �û��ֵ�ִ� */
    private MtUserSysDictInfoRepository      mtUserSysDictInfoRepository;

    /** 
     * @see com.myteay.core.service.cache.components.MtSysKeysCacheComponents#getValueByKey(com.myteay.common.service.facade.enums.MtSysKeyEnum)
     */
    @Override
    public String getValueByKey(MtSysKeyEnum key) {

        if (key == null) {
            return null;
        }

        if (CollectionUtils.isEmpty(SYS_KEY_CACHE)) {
            refreshCache();
        }
        return SYS_KEY_CACHE.get(key.getValue());
    }

    /** 
     * @see org.springframework.context.ApplicationListener#onApplicationEvent(org.springframework.context.ApplicationEvent)
     */
    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {

        if (logger.isInfoEnabled()) {
            logger.info("��ʼ���������ֵ���뻺��");
        }

        refreshCache();

        if (logger.isInfoEnabled()) {
            logger.info("�����ֵ�Ļ����ʼ������");
        }
    }

    /**
     * ˢ�������ֵ仺��
     */
    private void refreshCache() {
        Map<String, String> map = mtUserSysDictInfoRepository.findAll();
        if (CollectionUtils.isEmpty(map)) {
            logger.warn("��ǰϵͳδ�ҵ��κ������ֵ����ڳ�ʼ��ϵͳ������Ϣ");
            return;
        }

        //�������沢ˢ�£���ֹ�����쳣
        synchronized (SYS_KEY_CACHE) {
            SYS_KEY_CACHE.putAll(map);
        }

        logger.warn("����ˢ�½��� SYS_KEY_CACHE=" + SYS_KEY_CACHE);
    }

    /**
     * Setter method for property <tt>mtUserSysDictInfoRepository</tt>.
     * 
     * @param mtUserSysDictInfoRepository value to be assigned to property mtUserSysDictInfoRepository
     */
    public void setMtUserSysDictInfoRepository(MtUserSysDictInfoRepository mtUserSysDictInfoRepository) {
        this.mtUserSysDictInfoRepository = mtUserSysDictInfoRepository;
    }
}
