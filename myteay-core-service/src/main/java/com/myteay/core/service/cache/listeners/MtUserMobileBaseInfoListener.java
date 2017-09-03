/**
 * Myteay.com Inc.
 * Copyright (c) 2005-2017 All Rights Reserved.
 */
package com.myteay.core.service.cache.listeners;

import org.springframework.beans.factory.annotation.Autowired;

import com.myteay.common.util.event.EventListener;
import com.myteay.common.util.event.MtEvent;
import com.myteay.core.model.repository.MtUserMobileBaseInfoRepository;
import com.myteay.core.model.user.MtUserMobileBaseInfoModel;

/**
 * 用户注册时所用手机基本信息处理
 * 
 * @author danlley
 * @version $Id: MtUserMobileBaseInfoListener.java, v 0.1 Sep 3, 2017 3:35:35 PM danlley Exp $
 */
public class MtUserMobileBaseInfoListener extends EventListener<Object> {

    /** 用户注册时所用手机基本信息处理仓储 */
    @Autowired
    private MtUserMobileBaseInfoRepository mtUserMobileBaseInfoRepository;

    /** 
     * @see com.myteay.common.util.event.EventListener#handleEvent(com.myteay.common.util.event.MtEvent)
     */
    @Override
    public Object handleEvent(MtEvent<?> event) {

        if (logger.isInfoEnabled()) {
            logger.info("收到用户注册时所用手机基本信息处理请求事件event=" + event);
        }

        if (event == null || event.getData() == null) {
            logger.warn("[用户注册]手机基本信息不可用");
            return null;
        }

        if (!(event.getData() instanceof MtUserMobileBaseInfoModel)) {
            logger.warn("[用户注册] 手机基本信息模型实例无法识别 event=" + event);
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
