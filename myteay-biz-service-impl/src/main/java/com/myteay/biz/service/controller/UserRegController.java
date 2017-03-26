/**
 * Myteay.com Inc.
 * Copyright (c) 2015-2016 All Rights Reserved.
 */
package com.myteay.biz.service.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.myteay.common.service.facade.exceptions.MtBizException;
import com.myteay.common.service.facade.model.MtOperateResult;
import com.myteay.common.service.facade.model.MtUserAdvBaseInfo;
import com.myteay.common.service.facade.model.MtUserBaseInfo;
import com.myteay.common.service.facade.model.MtUserMessage;
import com.myteay.common.service.facade.model.MtUserRegQRCodeMessage;
import com.myteay.common.util.comm.StringUtils;
import com.myteay.common.util.event.EventPulishService;
import com.myteay.common.util.event.MtEvent;
import com.myteay.common.util.event.MtEventTopicEnum;
import com.myteay.common.utils.exception.MtException;
import com.myteay.core.service.components.MtUserServiceComponents;

/**
 * 用户注册基础类
 * 
 * 访问格式说明：http://localhost:8080/myteay-web/user_reg?un=%E9%9C%B8%E5%88%80&up=123456789&rf=%E5%A4%A9%E6%B0%B4
 * 
 * @author Administrator
 * @version $Id: UserRegController.java, v 0.1 2016年2月27日 下午11:44:38 Administrator Exp $
 */
@Controller
@RequestMapping("/user_reg")
public class UserRegController {

    /** 日志 */
    public static final Logger         logger = Logger.getLogger(UserRegController.class);

    /** 用户服务组件 */
    @Autowired
    private MtUserServiceComponents    mtUserServiceComponents;

    /** 套餐信息管理组件 */
    @Autowired
    private EventPulishService<String> eventPulishService;

    /**
     * 用户注册前端组件
     * 
     * @param session       Session信息
     * @param response      response信息
     * @param request       request信息
     * @return              返回默认格式的json串
     * @throws IOException  异常情况
     * @throws MtException  系统异常
     */
    @RequestMapping(method = RequestMethod.GET)
    public ModelAndView userReg(HttpSession session, HttpServletResponse response,
                                HttpServletRequest request) throws IOException, MtException {
        if (logger.isInfoEnabled()) {
            logger.info("开始用户注册。。。");
        }
        ModelAndView view = new ModelAndView("user_reg");
        String un = request.getParameter("un");
        String up = request.getParameter("up");
        String rf = request.getParameter("rf");

        MtUserMessage message = constructUserMessage(un, up, rf);

        Map<String, Object> model = new HashMap<String, Object>();

        //设置详情的基本信息
        if (message == null) {
            logger.warn("组装用户注册交互信息失败 message is null");
            model.put("mt_json", "{invalid}");
            view.addAllObjects(model);
            return view;
        }

        if (logger.isInfoEnabled()) {
            logger.info("待注册会员信息 message=" + message);
        }
        MtOperateResult<String> result = null;
        try {
            result = mtUserServiceComponents.userRegistery(message);
        } catch (MtBizException e) {
            logger.warn("用户注册失败， message=" + message, e);
            model.put("mt_json", "{exception}");
            view.addAllObjects(model);
            return view;
        }

        if (result == null || StringUtils.isBlank(result.getResult())) {
            logger.warn("用户注册失败，注册组件返回结果不可用！result=" + result);
            model.put("mt_json", "{failed}");
            view.addAllObjects(model);
            return view;
        }

        if (logger.isInfoEnabled()) {
            logger.info("用户注册成功 message=" + message + " result=" + result);
        }

        /*
         * TODO 生成用户二维码
         * 
         * 需要增加如用户重复注册、恶意注册请求等动作进来，因此代码需要下沉到core-service层
         */
        publishEvent(request, result.getResult());

        model.put("mt_json", "{" + result.getResult() + "}");
        view.addAllObjects(model);

        return view;
    }

    /**
     * 发布异步事件生成用户二维码（这里将吃掉所有异常，如生成二维码失败，则有后续的相关辅助流程协助解决）
     * 
     * @param request   请求
     * @param content   处理结果
     */
    private void publishEvent(HttpServletRequest request, String content) {

        String path = request.getSession().getServletContext().getRealPath("/qrcode/usercode/");
        String defpng = request.getSession().getServletContext()
            .getRealPath("/images/page1_image.png");

        if (StringUtils.isBlank(path) || StringUtils.isBlank(defpng)
            || StringUtils.isBlank(content)) {
            logger.error("二维码生成所需信息不全，path=" + path + " defpng=" + defpng + " content=" + content);
            return;
        }

        //异步事件处理组件
        try {
            eventPulishService.publishEvent(new MtEvent<MtUserRegQRCodeMessage>(
                MtEventTopicEnum.MT_USR_QR_CODE_REGISTERY.getValue(), new MtUserRegQRCodeMessage(
                    path, defpng, content)));
        } catch (MtException e) {
            logger.error("生成用户二维码过程中出现异常", e);
        }
    }

    /**
     * 组装用户信息交互消息
     * 
     * @param userNickName  用户昵称（用户名）
     * @param userPwd       用户密码
     * @param registerFrom  注册来源地
     * @return              用户信息交互消息
     */
    private MtUserMessage constructUserMessage(String userNickName, String userPwd,
                                               String registerFrom) {
        if (StringUtils.isBlank(userPwd) || StringUtils.isBlank(userNickName)) {
            logger.warn("用户注册信息不可用 userNickName=" + userNickName + " userPwd=" + userPwd
                        + " registerFrom=" + registerFrom);
            return null;
        }

        MtUserMessage message = new MtUserMessage();
        MtUserBaseInfo baseInfo = message.getUserBaseInfo();
        MtUserAdvBaseInfo advInfo = message.getUserAdvInfo();

        baseInfo.setNickName(userNickName.trim());
        advInfo.setUserPwd(userPwd.trim());
        advInfo.setRegFrom(registerFrom.trim());

        return message;
    }

    /**
     * Setter method for property <tt>mtUserServiceComponents</tt>.
     * 
     * @param mtUserServiceComponents value to be assigned to property mtUserServiceComponents
     */
    public void setMtUserServiceComponents(MtUserServiceComponents mtUserServiceComponents) {
        this.mtUserServiceComponents = mtUserServiceComponents;
    }
}
