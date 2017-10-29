/**
 * Myteay.com Inc.
 * Copyright (c) 2015-2015 All Rights Reserved.
 */
package com.myteay.biz.service.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.alibaba.fastjson.JSON;
import com.myteay.common.service.facade.model.MtOperateResult;
import com.myteay.common.service.facade.model.MtUserMessage;
import com.myteay.common.util.exception.MtException;
import com.myteay.core.service.components.MtUserServiceComponents;

/**
 * 会员信息查询接口（JSON格式）
 * 
 * 访问格式：http://localhost:8080/myteay-web/user_info?uid=MTCN000000201602281158041000000
 * 
 * @author Administrator
 * @version $Id: UserInfoController.java, v 0.1 2015年12月30日 下午1:30:08 Administrator Exp $
 */
@Controller
@RequestMapping("/")
public class UserInfoController {

    /** 日志 */
    public static final Logger      logger = Logger.getLogger(UserController.class);

    /** 用户服务组件 */
    @Autowired
    private MtUserServiceComponents mtUserServiceComponents;

    /**
     * 查询单个用户信息json接口
     * 
     * @param session       Session信息
     * @param response      response信息
     * @param request       request信息
     * @return              返回默认格式的json串
     * @throws IOException  异常情况
     * @throws MtException  系统异常
     */
    @RequestMapping("/user_info")
    public String userInfo(HttpSession session, HttpServletResponse response,
                           HttpServletRequest request) throws IOException, MtException {

        if (logger.isInfoEnabled()) {
            logger.info("----UserInfoController【被激活】-------------->");
        }

        if (request == null) {
            logger.warn("查询单个用户信息入参不可用，request is null");
            return "/user_info";
        }

        String uid = request.getParameter("uid");
        if (StringUtils.isBlank(uid) || StringUtils.isBlank(uid.trim())) {
            logger.warn("查询用户信息不可用，uid is null");
            return "/user_info";
        }

        if (logger.isInfoEnabled()) {
            logger.info("开始查询指定用户信息 uid=" + uid.trim());
        }

        MtOperateResult<MtUserMessage> result = null;
        try {
            result = mtUserServiceComponents.findSingleUserInfo(uid.trim());
        } catch (Throwable e) {
            logger.warn("查询用户信息失败， result=" + result, e);
        }

        if (logger.isInfoEnabled()) {
            logger.info("查询单个用户信息结束 uid=" + uid.trim() + " result=" + result);
        }

        if (result == null || result.getResult() == null) {
            logger.warn("未查询到指定用户信息 uid=" + uid.trim() + " result=" + result);
            return "/user_info";
        }

        String json = JSON.toJSONString(result.getResult());

        if (logger.isInfoEnabled()) {
            logger.info("单个用户json串 uid=" + uid + " json=" + json);
        }

        session.setAttribute("mt_json", json);

        return "/user_info";
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
