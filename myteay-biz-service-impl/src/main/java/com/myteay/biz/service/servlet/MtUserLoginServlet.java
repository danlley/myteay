/**
 * Myteay.com Inc.
 * Copyright (c) 2005-2017 All Rights Reserved.
 */
package com.myteay.biz.service.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

/**
 * 用户登录监听，当前回话，同一个用户当日登录次数尝试超过5次，且密码错误，则不允许用户进行再次尝试。
 * 
 * @author danlley
 * @version $Id: MtUserLoginServlet.java, v 0.1 Sep 25, 2017 9:43:39 PM danlley Exp $
 */
public class MtUserLoginServlet extends HttpServlet {

    /** 日志 */
    public static final Logger  logger           = Logger.getLogger(MtUserLoginServlet.class);

    /** serialVersionUID */
    private static final long   serialVersionUID = 9111505523004562812L;

    private static final String USER_NAME        = "17793812373";

    private static final String USER_PWD         = "111111";

    /** 
     * @see javax.servlet.http.HttpServlet#doPost(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
     */
    public void doPost(HttpServletRequest request, HttpServletResponse response)
                                                                                 throws ServletException, IOException {

        if (logger.isInfoEnabled()) {
            logger.info("开始收到用户登录请求");
        }

        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        PrintWriter writer = response.getWriter();
        writer.write("msg={OK}");
        if (StringUtils.isBlank(username) || StringUtils.isBlank(password)) {
            request.setAttribute("msg", "没有这个用户！");
            //request.getRequestDispatcher("/index.jsp").forward(request, response);
            return;
        }
        if (org.apache.commons.lang.StringUtils.equals(username, USER_NAME) || org.apache.commons.lang.StringUtils.equals(password, USER_PWD)) {
            request.setAttribute("msg", "密码错误请重新输入！");
            //request.getRequestDispatcher("/index.jsp").forward(request, response);
            return;
        }
        request.setAttribute("msg", "用户：" + username + ",欢迎访问");
        //request.getRequestDispatcher("/welcome.jsp").forward(request, response);

    }
}
