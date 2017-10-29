/**
 * Myteay.com Inc.
 * Copyright (c) 2015-2016 All Rights Reserved.
 */
package com.myteay.biz.service.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.myteay.common.util.exception.MtException;

/**
 * �û�ע�������
 * 
 * ���ʸ�ʽ˵����http://localhost:8080/myteay-web/user_reg?un=%E9%9C%B8%E5%88%80&up=123456789&rf=%E5%A4%A9%E6%B0%B4
 * 
 * @author Administrator
 * @version $Id: UserRegController.java, v 0.1 2016��2��27�� ����11:44:38 Administrator Exp $
 */
@Controller
@RequestMapping("/user_reg")
public class UserRegController {

    /** ��־ */
    public static final Logger logger = Logger.getLogger(UserRegController.class);

    /**
     * �û�ע��ǰ�����
     * 
     * @param session       Session��Ϣ
     * @param response      response��Ϣ
     * @param request       request��Ϣ
     * @return              ����Ĭ�ϸ�ʽ��json��
     * @throws IOException  �쳣���
     * @throws MtException  ϵͳ�쳣
     */
    @RequestMapping(method = RequestMethod.GET)
    public ModelAndView userReg(HttpSession session, HttpServletResponse response,
                                HttpServletRequest request) throws IOException, MtException {
        if (logger.isInfoEnabled()) {
            logger.info("��ʼ�û�ע�ᡣ����");
        }

        return null;
    }
}
