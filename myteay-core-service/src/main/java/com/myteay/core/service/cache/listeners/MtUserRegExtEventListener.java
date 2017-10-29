/**
 * Myteay.com Inc.
 * Copyright (c) 2005-2017 All Rights Reserved.
 */
package com.myteay.core.service.cache.listeners;

import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;

import com.alibaba.fastjson.JSONObject;
import com.myteay.common.async.event.EventListener;
import com.myteay.common.async.event.EventPublishService;
import com.myteay.common.async.event.MtEvent;
import com.myteay.common.async.event.MtEventException;
import com.myteay.common.service.facade.mobile.info.MtRegisterInfo;
import com.myteay.common.service.facade.model.MtUserRegQRCodeMessage;
import com.myteay.common.util.enums.MtEventTopicEnum;
import com.myteay.core.model.user.MtUserContactModel;
import com.myteay.core.model.user.MtUserMobileBaseInfoModel;
import com.myteay.core.model.user.MtUserRegExtModel;

/**
 * 用户注册扩展信息异步监听器
 * 
 * @author danlley
 * @version $Id: MtUserRegExtEventListener.java, v 0.1 Sep 2, 2017 10:43:47 PM danlley Exp $
 */
public class MtUserRegExtEventListener extends EventListener<Object> {

    /** 套餐信息管理组件 */
    @Autowired
    private EventPublishService<String> eventPublishService;

    /** 
     * @see com.myteay.common.util.event.EventListener#handleEvent(com.myteay.common.util.event.MtEvent)
     */
    @Override
    public Object handleEvent(MtEvent<?> event) {

        if (event == null || event.getData() == null) {
            logger.warn("处理用户注册扩展信息时，异步事件模型不可用 event is null");
            return null;
        }

        if (!(event.getData() instanceof MtUserRegExtModel)) {
            logger.warn("处理用户注册扩展信息时，事件承载的消息体不可识别 event.getData()=" + event.getData());
            return null;
        }

        MtUserRegExtModel model = (MtUserRegExtModel) event.getData();

        //step 1: 生成二维码信息
        generateQrCode(model.getMtQrCodeModel());

        //step 2: 记录用户的联系人列表
        saveUserContactList(model.getMtUserContactModel());

        //step 3: 保存用户注册时手机的基本信息
        saveUserMobileBaseInfo(model);

        return null;
    }

    /**
     * 保存用户手机的基本信息
     * 
     * @param model
     */
    private void saveUserMobileBaseInfo(MtUserRegExtModel model) {

        if (model == null) {
            logger.warn("当前注册会员的注册扩展信息模型不可用  MtUserRegExtModel is null");
            return;
        }

        MtRegisterInfo mtRegisterInfo = model.getRegisterInfo();
        if (mtRegisterInfo == null || CollectionUtils.isEmpty(mtRegisterInfo.getExtRegInfo())) {
            logger.warn("当前注册信息不可用，或未找到注册扩展信息，无法完成手机基本信息的登记工作 mtRegisterInfo=" + mtRegisterInfo);
            return;
        }

        Map<String, String> extInfo = mtRegisterInfo.getExtRegInfo();
        String mobileBaseInfo = extInfo.get("MT_MOBILE_BASE_INFO");
        if (StringUtils.isBlank(mobileBaseInfo)) {
            logger.warn("当前用户的注册扩展信息中，未找到手机基本信息，无法进行登记 mtRegisterInfo=" + mtRegisterInfo);
            return;
        }

        MtUserMobileBaseInfoModel mtUserMobileBaseInfoModel = JSONObject.parseObject(mobileBaseInfo,
            MtUserMobileBaseInfoModel.class);
        mtUserMobileBaseInfoModel.setUserid(mtRegisterInfo.getUserid());
        saveUserMobileBaseInfo(mtUserMobileBaseInfoModel);

    }

    /**
     * 保存用户注册时所用的手机的基本信息
     * 
     * @param mtUserMobileBaseInfoModel
     */
    private void saveUserMobileBaseInfo(MtUserMobileBaseInfoModel mtUserMobileBaseInfoModel) {
        if (logger.isInfoEnabled()) {
            logger.info("开始处理用户注册时的手机基本信息模型 mtUserMobileBaseInfoModel = " + mtUserMobileBaseInfoModel);
        }

        //异步事件处理组件
        try {
            eventPublishService.publishEvent(new MtEvent<MtUserMobileBaseInfoModel>(
                MtEventTopicEnum.MT_USR_REG_MOBILE_INFO_EVENT.getValue(), mtUserMobileBaseInfoModel));
        } catch (MtEventException e) {
            logger.error("开始处理用户注册时的手机基本信息模型信息过程中出现异常", e);
        }
    }

    /**
     * 处理用户联系人模型
     * 
     * @param contactModel
     */
    private void saveUserContactList(MtUserContactModel contactModel) {
        if (logger.isInfoEnabled()) {
            logger.info("开始处理用户联系人模型 contactModel = " + contactModel);
        }

        //异步事件处理组件
        try {
            eventPublishService.publishEvent(new MtEvent<MtUserContactModel>(
                MtEventTopicEnum.MT_USR_CONTACT_LIST.getValue(), contactModel));
        } catch (MtEventException e) {
            logger.error("处理用户联系人信息过程中出现异常", e);
        }
    }

    /**
     * 通过QRCode生成用户的二维码信息
     * 
     * @param request
     * @param content
     */
    private void generateQrCode(MtUserRegQRCodeMessage qrcodeModel) {

        //异步事件处理组件
        try {
            eventPublishService.publishEvent(new MtEvent<MtUserRegQRCodeMessage>(
                MtEventTopicEnum.MT_USR_QR_CODE_REGISTERY.getValue(), qrcodeModel));
        } catch (MtEventException e) {
            logger.error("生成用户二维码过程中出现异常", e);
        }
    }
}
