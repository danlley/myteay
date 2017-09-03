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
 * 用来处理用户联系人列表信息的异步监听器
 * 
 * @author danlley
 * @version $Id: MtUserContactListListener.java, v 0.1 Sep 2, 2017 4:41:54 PM danlley Exp $
 */
public class MtUserContactListListener extends EventListener<Object> {

    /** 日志 */
    public static final Logger              logger = Logger.getLogger(MtUserContactListListener.class);

    /** 联系人列表仓储 */
    @Autowired
    private MtUserContactListInfoRepository mtUserContactListInfoRepository;

    /** 
     * @see com.myteay.common.util.event.EventListener#handleEvent(com.myteay.common.util.event.MtEvent)
     */
    @Override
    public Object handleEvent(MtEvent<?> event) {

        if (logger.isInfoEnabled()) {
            logger.warn("开始对用户的联系人信息进行异步处理 event=" + event);
        }

        if (event == null || event.getData() == null) {
            logger.warn("对用户的联系人信息进行异步处理时，异步事件消息模型不可用event=" + event);
            return null;
        }

        if (!(event.getData() instanceof MtUserContactModel)) {
            logger.warn("联系人模型非法，无法执行联系人处理动作 event=" + event);
            return null;
        }

        MtOperateResult<String> result = mtUserContactListInfoRepository.saveUserContactListInfo(
            (MtUserContactModel) event.getData());

        if (logger.isInfoEnabled()) {
            logger.info("会员联系人信息保存结束 result=" + result + " event=" + event);
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
