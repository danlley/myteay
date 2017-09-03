/**
 * Myteay.com Inc.
 * Copyright (c) 2005-2017 All Rights Reserved.
 */
package com.myteay.core.service.cache.listeners;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import com.myteay.common.service.facade.model.MtOperateResult;
import com.myteay.common.util.event.EventListener;
import com.myteay.common.util.event.MtEvent;
import com.myteay.core.model.repository.MtUserContactListInfoRepository;
import com.myteay.core.model.user.MtUserContactModel;

/**
 * ���������û���ϵ���б���Ϣ���첽������
 * 
 * @author danlley
 * @version $Id: MtUserContactListListener.java, v 0.1 Sep 2, 2017 4:41:54 PM danlley Exp $
 */
public class MtUserContactListListener extends EventListener<Object> {

    /** ��־ */
    public static final Logger              logger = Logger.getLogger(MtUserContactListListener.class);

    /** ��ϵ���б�ִ� */
    @Autowired
    private MtUserContactListInfoRepository mtUserContactListInfoRepository;

    /** 
     * @see com.myteay.common.util.event.EventListener#handleEvent(com.myteay.common.util.event.MtEvent)
     */
    @Override
    public Object handleEvent(MtEvent<?> event) {

        if (logger.isInfoEnabled()) {
            logger.warn("��ʼ���û�����ϵ����Ϣ�����첽���� event=" + event);
        }

        if (event == null || event.getData() == null) {
            logger.warn("���û�����ϵ����Ϣ�����첽����ʱ���첽�¼���Ϣģ�Ͳ�����event=" + event);
            return null;
        }

        if (!(event.getData() instanceof MtUserContactModel)) {
            logger.warn("��ϵ��ģ�ͷǷ����޷�ִ����ϵ�˴����� event=" + event);
            return null;
        }

        MtOperateResult<String> result = mtUserContactListInfoRepository.saveUserContactListInfo(
            (MtUserContactModel) event.getData());

        if (logger.isInfoEnabled()) {
            logger.info("��Ա��ϵ����Ϣ������� result=" + result + " event=" + event);
        }

        return result;
    }

    /**
     * Setter method for property <tt>mtUserContactListInfoRepository</tt>.
     * 
     * @param mtUserContactListInfoRepository value to be assigned to property mtUserContactListInfoRepository
     */
    public void setMtUserContactListInfoRepository(MtUserContactListInfoRepository mtUserContactListInfoRepository) {
        this.mtUserContactListInfoRepository = mtUserContactListInfoRepository;
    }
}
