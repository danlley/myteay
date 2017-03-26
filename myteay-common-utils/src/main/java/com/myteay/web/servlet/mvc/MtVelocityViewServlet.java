/**
 * Myteay.com Inc.
 * Copyright (c) 2015-2016 All Rights Reserved.
 */
package com.myteay.web.servlet.mvc;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.velocity.tools.view.VelocityViewServlet;

/**
 * ����VelocityViewServlet�����������
 * 
 * @author Administrator
 * @version $Id: MtVelocityViewServlet.java, v 0.1 2016��4��3�� ����12:08:54 Administrator Exp $
 */
public class MtVelocityViewServlet extends VelocityViewServlet {

    /** serialVersionUID */
    private static final long  serialVersionUID = -2085735608438148343L;

    /** ����servlet�ַ��� */
    public static final String CHAR_SET         = "text/html;charset=UTF-8";

    /** 
     * @see org.apache.velocity.tools.view.VelocityViewServlet#doGet(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
     */
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response)
                                                                               throws ServletException,
                                                                               IOException {
        response.setContentType(CHAR_SET);
        super.doGet(request, response);
    }

    /** 
     * @see org.apache.velocity.tools.view.VelocityViewServlet#doPost(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
     */
    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response)
                                                                                throws ServletException,
                                                                                IOException {
        response.setContentType(CHAR_SET);
        super.doPost(request, response);
    }
}
