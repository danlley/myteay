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
 * �û�ע����չ��Ϣ�첽������
 * 
 * @author danlley
 * @version $Id: MtUserRegExtEventListener.java, v 0.1 Sep 2, 2017 10:43:47 PM danlley Exp $
 */
public class MtUserRegExtEventListener extends EventListener<Object> {

    /** �ײ���Ϣ������� */
    @Autowired
    private EventPublishService<String> eventPublishService;

    /** 
     * @see com.myteay.common.util.event.EventListener#handleEvent(com.myteay.common.util.event.MtEvent)
     */
    @Override
    public Object handleEvent(MtEvent<?> event) {

        if (event == null || event.getData() == null) {
            logger.warn("�����û�ע����չ��Ϣʱ���첽�¼�ģ�Ͳ����� event is null");
            return null;
        }

        if (!(event.getData() instanceof MtUserRegExtModel)) {
            logger.warn("�����û�ע����չ��Ϣʱ���¼����ص���Ϣ�岻��ʶ�� event.getData()=" + event.getData());
            return null;
        }

        MtUserRegExtModel model = (MtUserRegExtModel) event.getData();

        //step 1: ���ɶ�ά����Ϣ
        generateQrCode(model.getMtQrCodeModel());

        //step 2: ��¼�û�����ϵ���б�
        saveUserContactList(model.getMtUserContactModel());

        //step 3: �����û�ע��ʱ�ֻ��Ļ�����Ϣ
        saveUserMobileBaseInfo(model);

        return null;
    }

    /**
     * �����û��ֻ��Ļ�����Ϣ
     * 
     * @param model
     */
    private void saveUserMobileBaseInfo(MtUserRegExtModel model) {

        if (model == null) {
            logger.warn("��ǰע���Ա��ע����չ��Ϣģ�Ͳ�����  MtUserRegExtModel is null");
            return;
        }

        MtRegisterInfo mtRegisterInfo = model.getRegisterInfo();
        if (mtRegisterInfo == null || CollectionUtils.isEmpty(mtRegisterInfo.getExtRegInfo())) {
            logger.warn("��ǰע����Ϣ�����ã���δ�ҵ�ע����չ��Ϣ���޷�����ֻ�������Ϣ�ĵǼǹ��� mtRegisterInfo=" + mtRegisterInfo);
            return;
        }

        Map<String, String> extInfo = mtRegisterInfo.getExtRegInfo();
        String mobileBaseInfo = extInfo.get("MT_MOBILE_BASE_INFO");
        if (StringUtils.isBlank(mobileBaseInfo)) {
            logger.warn("��ǰ�û���ע����չ��Ϣ�У�δ�ҵ��ֻ�������Ϣ���޷����еǼ� mtRegisterInfo=" + mtRegisterInfo);
            return;
        }

        MtUserMobileBaseInfoModel mtUserMobileBaseInfoModel = JSONObject.parseObject(mobileBaseInfo,
            MtUserMobileBaseInfoModel.class);
        mtUserMobileBaseInfoModel.setUserid(mtRegisterInfo.getUserid());
        saveUserMobileBaseInfo(mtUserMobileBaseInfoModel);

    }

    /**
     * �����û�ע��ʱ���õ��ֻ��Ļ�����Ϣ
     * 
     * @param mtUserMobileBaseInfoModel
     */
    private void saveUserMobileBaseInfo(MtUserMobileBaseInfoModel mtUserMobileBaseInfoModel) {
        if (logger.isInfoEnabled()) {
            logger.info("��ʼ�����û�ע��ʱ���ֻ�������Ϣģ�� mtUserMobileBaseInfoModel = " + mtUserMobileBaseInfoModel);
        }

        //�첽�¼��������
        try {
            eventPublishService.publishEvent(new MtEvent<MtUserMobileBaseInfoModel>(
                MtEventTopicEnum.MT_USR_REG_MOBILE_INFO_EVENT.getValue(), mtUserMobileBaseInfoModel));
        } catch (MtEventException e) {
            logger.error("��ʼ�����û�ע��ʱ���ֻ�������Ϣģ����Ϣ�����г����쳣", e);
        }
    }

    /**
     * �����û���ϵ��ģ��
     * 
     * @param contactModel
     */
    private void saveUserContactList(MtUserContactModel contactModel) {
        if (logger.isInfoEnabled()) {
            logger.info("��ʼ�����û���ϵ��ģ�� contactModel = " + contactModel);
        }

        //�첽�¼��������
        try {
            eventPublishService.publishEvent(new MtEvent<MtUserContactModel>(
                MtEventTopicEnum.MT_USR_CONTACT_LIST.getValue(), contactModel));
        } catch (MtEventException e) {
            logger.error("�����û���ϵ����Ϣ�����г����쳣", e);
        }
    }

    /**
     * ͨ��QRCode�����û��Ķ�ά����Ϣ
     * 
     * @param request
     * @param content
     */
    private void generateQrCode(MtUserRegQRCodeMessage qrcodeModel) {

        //�첽�¼��������
        try {
            eventPublishService.publishEvent(new MtEvent<MtUserRegQRCodeMessage>(
                MtEventTopicEnum.MT_USR_QR_CODE_REGISTERY.getValue(), qrcodeModel));
        } catch (MtEventException e) {
            logger.error("�����û���ά������г����쳣", e);
        }
    }
}
