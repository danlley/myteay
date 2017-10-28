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

import org.apache.log4j.Logger;

import com.myteay.common.util.comm.StringUtils;

/**
 * �û���¼��������ǰ�ػ���ͬһ���û����յ�¼�������Գ���5�Σ�����������������û������ٴγ��ԡ�
 * 
 * @author danlley
 * @version $Id: MtUserLoginServlet.java, v 0.1 Sep 25, 2017 9:43:39 PM danlley Exp $
 */
public class MtUserLoginServlet extends HttpServlet {

    /** ��־ */
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
            logger.info("��ʼ�յ��û���¼����");
        }

        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        PrintWriter writer = response.getWriter();
        writer.write("msg={OK}");
        if (StringUtils.isBlank(username) || StringUtils.isBlank(password)) {
            request.setAttribute("msg", "û������û���");
            //request.getRequestDispatcher("/index.jsp").forward(request, response);
            return;
        }
        if (org.apache.commons.lang.StringUtils.equals(username, USER_NAME) || org.apache.commons.lang.StringUtils.equals(password, USER_PWD)) {
            request.setAttribute("msg", "����������������룡");
            //request.getRequestDispatcher("/index.jsp").forward(request, response);
            return;
        }
        request.setAttribute("msg", "�û���" + username + ",��ӭ����");
        //request.getRequestDispatcher("/welcome.jsp").forward(request, response);

    }
}
