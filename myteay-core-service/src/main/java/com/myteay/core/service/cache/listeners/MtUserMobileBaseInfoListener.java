/**
 * Myteay.com Inc.
 * Copyright (c) 2005-2017 All Rights Reserved.
 */
package com.myteay.core.service.cache.listeners;

import org.springframework.beans.factory.annotation.Autowired;

import com.myteay.common.async.event.EventListener;
import com.myteay.common.async.event.MtEvent;
import com.myteay.core.model.repository.MtUserMobileBaseInfoRepository;
import com.myteay.core.model.user.MtUserMobileBaseInfoModel;

/**
 * �û�ע��ʱ�����ֻ�������Ϣ����
 * 
 * @author danlley
 * @version $Id: MtUserMobileBaseInfoListener.java, v 0.1 Sep 3, 2017 3:35:35 PM danlley Exp $
 */
public class MtUserMobileBaseInfoListener extends EventListener<Object> {

    /** �û�ע��ʱ�����ֻ�������Ϣ����ִ� */
    @Autowired
    private MtUserMobileBaseInfoRepository mtUserMobileBaseInfoRepository;

    /** 
     * @see com.myteay.common.util.event.EventListener#handleEvent(com.myteay.common.util.event.MtEvent)
     */
    @Override
    public Object handleEvent(MtEvent<?> event) {

        if (logger.isInfoEnabled()) {
            logger.info("�յ��û�ע��ʱ�����ֻ�������Ϣ���������¼�event=" + event);
        }

        if (event == null || event.getData() == null) {
            logger.warn("[�û�ע��]�ֻ�������Ϣ������");
            return null;
        }

        if (!(event.getData() instanceof MtUserMobileBaseInfoModel)) {
            logger.warn("[�û�ע��] �ֻ�������Ϣģ��ʵ���޷�ʶ�� event=" + event);
            return null;
        }

        return mtUserMobileBaseInfoRepository.saveUserMobileInfo((MtUserMobileBaseInfoModel) event.getData());
    }

    /**
     * Setter method for property <tt>mtUserMobileBaseInfoRepository</tt>.
     * 
     * @param mtUserMobileBaseInfoRepository value to be assigned to property mtUserMobileBaseInfoRepository
     */
    public void setMtUserMobileBaseInfoRepository(MtUserMobileBaseInfoRepository mtUserMobileBaseInfoRepository) {
        this.mtUserMobileBaseInfoRepository = mtUserMobileBaseInfoRepository;
    }
}
