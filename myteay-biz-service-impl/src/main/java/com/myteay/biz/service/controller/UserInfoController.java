/**
 * Myteay.com Inc.
 * Copyright (c) 2015-2015 All Rights Reserved.
 */
package com.myteay.biz.service.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.alibaba.fastjson.JSON;
import com.myteay.common.service.facade.model.MtOperateResult;
import com.myteay.common.service.facade.model.MtUserMessage;
import com.myteay.common.util.comm.StringUtils;
import com.myteay.common.utils.exception.MtException;
import com.myteay.core.service.components.MtUserServiceComponents;

/**
 * ��Ա��Ϣ��ѯ�ӿڣ�JSON��ʽ��
 * 
 * ���ʸ�ʽ��http://localhost:8080/myteay-web/user_info?uid=MTCN000000201602281158041000000
 * 
 * @author Administrator
 * @version $Id: UserInfoController.java, v 0.1 2015��12��30�� ����1:30:08 Administrator Exp $
 */
@Controller
@RequestMapping("/")
public class UserInfoController {

    /** ��־ */
    public static final Logger      logger = Logger.getLogger(UserController.class);

    /** �û�������� */
    @Autowired
    private MtUserServiceComponents mtUserServiceComponents;

    /**
     * ��ѯ�����û���Ϣjson�ӿ�
     * 
     * @param session       Session��Ϣ
     * @param response      response��Ϣ
     * @param request       request��Ϣ
     * @return              ����Ĭ�ϸ�ʽ��json��
     * @throws IOException  �쳣���
     * @throws MtException  ϵͳ�쳣
     */
    @RequestMapping("/user_info")
    public String userInfo(HttpSession session, HttpServletResponse response,
                           HttpServletRequest request) throws IOException, MtException {

        if (logger.isInfoEnabled()) {
            logger.info("----UserInfoController�������-------------->");
        }

        if (request == null) {
            logger.warn("��ѯ�����û���Ϣ��β����ã�request is null");
            return "/user_info";
        }

        String uid = request.getParameter("uid");
        if (StringUtils.isBlank(uid) || StringUtils.isBlank(uid.trim())) {
            logger.warn("��ѯ�û���Ϣ�����ã�uid is null");
            return "/user_info";
        }

        if (logger.isInfoEnabled()) {
            logger.info("��ʼ��ѯָ���û���Ϣ uid=" + uid.trim());
        }

        MtOperateResult<MtUserMessage> result = null;
        try {
            result = mtUserServiceComponents.findSingleUserInfo(uid.trim());
        } catch (Throwable e) {
            logger.warn("��ѯ�û���Ϣʧ�ܣ� result=" + result, e);
        }

        if (logger.isInfoEnabled()) {
            logger.info("��ѯ�����û���Ϣ���� uid=" + uid.trim() + " result=" + result);
        }

        if (result == null || result.getResult() == null) {
            logger.warn("δ��ѯ��ָ���û���Ϣ uid=" + uid.trim() + " result=" + result);
            return "/user_info";
        }

        String json = JSON.toJSONString(result.getResult());

        if (logger.isInfoEnabled()) {
            logger.info("�����û�json�� uid=" + uid + " json=" + json);
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
