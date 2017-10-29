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
import com.myteay.common.util.event.MtEventTopicEnum;
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

    /** 日志 */
    public static final Logger          logger = Logger.getLogger(MtUserMobileServiceController.class);

    /** 用户服务组件 */
    @Autowired
    private MtUserServiceComponents     mtUserServiceComponents;

    /** 套餐信息管理组件 */
    @Autowired
    private EventPublishService<String> eventPublishService;

    @RequestMapping(value = "/login", method = { RequestMethod.POST })
    public MtServiceResult<MtLoginInfo> login(@RequestBody MtLoginInfo mtLoginInfo,
                                              HttpServletRequest request,
                                              HttpServletResponse response) {

        if (logger.isInfoEnabled()) {
            logger.info("开始收到会员登录请求 mtLoginInfo=" + mtLoginInfo);
        }

        MtServiceResult<MtLoginInfo> serviceResult = new MtServiceResult<MtLoginInfo>();
        serviceResult.setOperateResult(MtOperateResultEnum.CAMP_OPERATE_SUCCESS.getValue());
        serviceResult.setResult(mtLoginInfo);

        return serviceResult;
    }

    /**
     * 会员注册
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
            logger.info("开始收到会员注册请求 registerInfo=" + registerInfo);
        }

        //设置详情的基本信息
        MtServiceResult<MtRegisterInfo> serviceResult = new MtServiceResult<MtRegisterInfo>();
        if (registerInfo == null) {
            logger.warn("组装用户注册交互信息失败 registerInfo is null");
            serviceResult.setOperateResult(MtOperateResultEnum.CAMP_OPERATE_FAILED.getValue());
            return serviceResult;
        }

        MtOperateResult<String> result = null;
        try {
            result = mtUserServiceComponents.userRegistery(registerInfo);
        } catch (MtBizException e) {
            logger.warn("用户注册失败， registerInfo=" + registerInfo, e);
            serviceResult.setOperateResult(MtOperateResultEnum.CAMP_OPERATE_FAILED.getValue());
            return serviceResult;
        }

        if (result == null || StringUtils.isBlank(result.getResult())) {
            logger.warn("用户注册失败，注册组件返回结果不可用！result=" + result);
            serviceResult.setOperateResult(MtOperateResultEnum.CAMP_OPERATE_FAILED.getValue());
            return serviceResult;
        }

        if (logger.isInfoEnabled()) {
            logger.info("用户注册成功 registerInfo=" + registerInfo + " result=" + result);
        }

        serviceResult.setOperateResult(MtOperateResultEnum.CAMP_OPERATE_SUCCESS.getValue());

        registerInfo.setUserid(result.getResult());
        registerInfo.setQrCodeId(result.getResult());
        serviceResult.setResult(registerInfo);

        /*
         * TODO 生成用户二维码
         * 
         * 需要增加如用户重复注册、恶意注册请求等动作进来，因此代码需要下沉到core-service层
         */
        publishEvent(request, registerInfo);

        return serviceResult;
    }

    /**
     * 发布异步事件生成用户二维码（这里将吃掉所有异常，如生成二维码失败，则有后续的相关辅助流程协助解决）
     * 
     * @param request   请求
     * @param content   处理结果
     */
    private void publishEvent(HttpServletRequest request, MtRegisterInfo registerInfo) {

        if (logger.isInfoEnabled()) {
            logger.info("开始执行用户注册过程中的扩展流程 registerInfo = " + registerInfo);
        }

        //异步事件处理组件
        try {
            eventPublishService.publishEvent(new MtEvent<MtUserRegExtModel>(
                MtEventTopicEnum.MT_USR_REG_EXT_EVENT.getValue(), constructAsynchronizedMessage(request,
                    registerInfo)));
        } catch (MtEventException e) {
            logger.error("处理用户联系人信息过程中出现异常", e);
        }

    }

    /**
     * 构建异步消息模型
     * 
     * @param request
     * @param registerInfo
     * @return
     */
    private MtUserRegExtModel constructAsynchronizedMessage(HttpServletRequest request, MtRegisterInfo registerInfo) {
        MtUserRegExtModel model = new MtUserRegExtModel();

        //step 1: 构建用于生成用户二维码信息的消息模型
        String path = request.getSession().getServletContext().getRealPath("/qrcode/usercode/");
        String defpng = request.getSession().getServletContext()
            .getRealPath("/images/page1_image.png");

        MtUserRegQRCodeMessage qrcodeModel = new MtUserRegQRCodeMessage(
            path, defpng, registerInfo.getQrCodeId());
        model.setMtQrCodeModel(qrcodeModel);

        //step 2: 构建用于处理用户联系人信息的模型
        model.setMtUserContactModel(MtUserRegConvertor.convertInfo2Model(registerInfo));

        //step 3: 携带交互单据，便于后面做问题排查和进行业务扩展
        model.setRegisterInfo(registerInfo);

        return model;
    }

}
