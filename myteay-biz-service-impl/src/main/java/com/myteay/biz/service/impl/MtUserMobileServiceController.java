/**
 * Myteay.com Inc.
 * Copyright (c) 2005-2017 All Rights Reserved.
 */
package com.myteay.biz.service.impl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.myteay.common.async.event.EventPublishService;
import com.myteay.common.async.event.MtEvent;
import com.myteay.common.async.event.MtEventException;
import com.myteay.common.service.facade.enums.MtOperateResultEnum;
import com.myteay.common.service.facade.exceptions.MtBizException;
import com.myteay.common.service.facade.mobile.info.MtLoginInfo;
import com.myteay.common.service.facade.mobile.info.MtRegisterInfo;
import com.myteay.common.service.facade.model.MtOperateResult;
import com.myteay.common.service.facade.model.MtUserRegQRCodeMessage;
import com.myteay.common.service.facade.results.MtServiceResult;
import com.myteay.common.util.enums.MtEventTopicEnum;
import com.myteay.core.model.user.MtUserRegExtModel;
import com.myteay.core.model.user.convt.MtUserRegConvertor;
import com.myteay.core.service.components.MtUserServiceComponents;

/**
 * 
 * @author danlley
 * @version $Id: MtUserMobileServiceController.java, v 0.1 Sep 1, 2017 2:43:36 PM danlley Exp $
 */
@RestController
public class MtUserMobileServiceController {

    /** ��־ */
    public static final Logger          logger = Logger.getLogger(MtUserMobileServiceController.class);

    /** �û�������� */
    @Autowired
    private MtUserServiceComponents     mtUserServiceComponents;

    /** �ײ���Ϣ������� */
    @Autowired
    private EventPublishService<String> eventPublishService;

    @RequestMapping(value = "/login", method = { RequestMethod.POST })
    public MtServiceResult<MtLoginInfo> login(@RequestBody MtLoginInfo mtLoginInfo,
                                              HttpServletRequest request,
                                              HttpServletResponse response) {

        if (logger.isInfoEnabled()) {
            logger.info("��ʼ�յ���Ա��¼���� mtLoginInfo=" + mtLoginInfo);
        }

        MtServiceResult<MtLoginInfo> serviceResult = new MtServiceResult<MtLoginInfo>();
        serviceResult.setOperateResult(MtOperateResultEnum.CAMP_OPERATE_SUCCESS.getValue());
        serviceResult.setResult(mtLoginInfo);

        return serviceResult;
    }

    /**
     * ��Աע��
     * 
     * @param registerInfo
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = "/registery", method = { RequestMethod.POST })
    public MtServiceResult<MtRegisterInfo> registery(@RequestBody MtRegisterInfo registerInfo,
                                                     HttpServletRequest request,
                                                     HttpServletResponse response) {

        if (logger.isInfoEnabled()) {
            logger.info("��ʼ�յ���Աע������ registerInfo=" + registerInfo);
        }

        //��������Ļ�����Ϣ
        MtServiceResult<MtRegisterInfo> serviceResult = new MtServiceResult<MtRegisterInfo>();
        if (registerInfo == null) {
            logger.warn("��װ�û�ע�ύ����Ϣʧ�� registerInfo is null");
            serviceResult.setOperateResult(MtOperateResultEnum.CAMP_OPERATE_FAILED.getValue());
            return serviceResult;
        }

        MtOperateResult<String> result = null;
        try {
            result = mtUserServiceComponents.userRegistery(registerInfo);
        } catch (MtBizException e) {
            logger.warn("�û�ע��ʧ�ܣ� registerInfo=" + registerInfo, e);
            serviceResult.setOperateResult(MtOperateResultEnum.CAMP_OPERATE_FAILED.getValue());
            return serviceResult;
        }

        if (result == null || StringUtils.isBlank(result.getResult())) {
            logger.warn("�û�ע��ʧ�ܣ�ע��������ؽ�������ã�result=" + result);
            serviceResult.setOperateResult(MtOperateResultEnum.CAMP_OPERATE_FAILED.getValue());
            return serviceResult;
        }

        if (logger.isInfoEnabled()) {
            logger.info("�û�ע��ɹ� registerInfo=" + registerInfo + " result=" + result);
        }

        serviceResult.setOperateResult(MtOperateResultEnum.CAMP_OPERATE_SUCCESS.getValue());

        registerInfo.setUserid(result.getResult());
        registerInfo.setQrCodeId(result.getResult());
        serviceResult.setResult(registerInfo);

        /*
         * TODO �����û���ά��
         * 
         * ��Ҫ�������û��ظ�ע�ᡢ����ע������ȶ�����������˴�����Ҫ�³���core-service��
         */
        publishEvent(request, registerInfo);

        return serviceResult;
    }

    /**
     * �����첽�¼������û���ά�루���ｫ�Ե������쳣�������ɶ�ά��ʧ�ܣ����к�������ظ�������Э�������
     * 
     * @param request   ����
     * @param content   ������
     */
    private void publishEvent(HttpServletRequest request, MtRegisterInfo registerInfo) {

        if (logger.isInfoEnabled()) {
            logger.info("��ʼִ���û�ע������е���չ���� registerInfo = " + registerInfo);
        }

        //�첽�¼��������
        try {
            eventPublishService.publishEvent(new MtEvent<MtUserRegExtModel>(
                MtEventTopicEnum.MT_USR_REG_EXT_EVENT.getValue(), constructAsynchronizedMessage(request,
                    registerInfo)));
        } catch (MtEventException e) {
            logger.error("�����û���ϵ����Ϣ�����г����쳣", e);
        }

    }

    /**
     * �����첽��Ϣģ��
     * 
     * @param request
     * @param registerInfo
     * @return
     */
    private MtUserRegExtModel constructAsynchronizedMessage(HttpServletRequest request, MtRegisterInfo registerInfo) {
        MtUserRegExtModel model = new MtUserRegExtModel();

        //step 1: �������������û���ά����Ϣ����Ϣģ��
        String path = request.getSession().getServletContext().getRealPath("/qrcode/usercode/");
        String defpng = request.getSession().getServletContext()
            .getRealPath("/images/page1_image.png");

        MtUserRegQRCodeMessage qrcodeModel = new MtUserRegQRCodeMessage(
            path, defpng, registerInfo.getQrCodeId());
        model.setMtQrCodeModel(qrcodeModel);

        //step 2: �������ڴ����û���ϵ����Ϣ��ģ��
        model.setMtUserContactModel(MtUserRegConvertor.convertInfo2Model(registerInfo));

        //step 3: Я���������ݣ����ں����������Ų�ͽ���ҵ����չ
        model.setRegisterInfo(registerInfo);

        return model;
    }

}
