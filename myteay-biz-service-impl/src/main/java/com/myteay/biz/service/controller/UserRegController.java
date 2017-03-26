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
    public static final Logger         logger = Logger.getLogger(UserRegController.class);

    /** �û�������� */
    @Autowired
    private MtUserServiceComponents    mtUserServiceComponents;

    /** �ײ���Ϣ������� */
    @Autowired
    private EventPulishService<String> eventPulishService;

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
        ModelAndView view = new ModelAndView("user_reg");
        String un = request.getParameter("un");
        String up = request.getParameter("up");
        String rf = request.getParameter("rf");

        MtUserMessage message = constructUserMessage(un, up, rf);

        Map<String, Object> model = new HashMap<String, Object>();

        //��������Ļ�����Ϣ
        if (message == null) {
            logger.warn("��װ�û�ע�ύ����Ϣʧ�� message is null");
            model.put("mt_json", "{invalid}");
            view.addAllObjects(model);
            return view;
        }

        if (logger.isInfoEnabled()) {
            logger.info("��ע���Ա��Ϣ message=" + message);
        }
        MtOperateResult<String> result = null;
        try {
            result = mtUserServiceComponents.userRegistery(message);
        } catch (MtBizException e) {
            logger.warn("�û�ע��ʧ�ܣ� message=" + message, e);
            model.put("mt_json", "{exception}");
            view.addAllObjects(model);
            return view;
        }

        if (result == null || StringUtils.isBlank(result.getResult())) {
            logger.warn("�û�ע��ʧ�ܣ�ע��������ؽ�������ã�result=" + result);
            model.put("mt_json", "{failed}");
            view.addAllObjects(model);
            return view;
        }

        if (logger.isInfoEnabled()) {
            logger.info("�û�ע��ɹ� message=" + message + " result=" + result);
        }

        /*
         * TODO �����û���ά��
         * 
         * ��Ҫ�������û��ظ�ע�ᡢ����ע������ȶ�����������˴�����Ҫ�³���core-service��
         */
        publishEvent(request, result.getResult());

        model.put("mt_json", "{" + result.getResult() + "}");
        view.addAllObjects(model);

        return view;
    }

    /**
     * �����첽�¼������û���ά�루���ｫ�Ե������쳣�������ɶ�ά��ʧ�ܣ����к�������ظ�������Э�������
     * 
     * @param request   ����
     * @param content   ������
     */
    private void publishEvent(HttpServletRequest request, String content) {

        String path = request.getSession().getServletContext().getRealPath("/qrcode/usercode/");
        String defpng = request.getSession().getServletContext()
            .getRealPath("/images/page1_image.png");

        if (StringUtils.isBlank(path) || StringUtils.isBlank(defpng)
            || StringUtils.isBlank(content)) {
            logger.error("��ά������������Ϣ��ȫ��path=" + path + " defpng=" + defpng + " content=" + content);
            return;
        }

        //�첽�¼��������
        try {
            eventPulishService.publishEvent(new MtEvent<MtUserRegQRCodeMessage>(
                MtEventTopicEnum.MT_USR_QR_CODE_REGISTERY.getValue(), new MtUserRegQRCodeMessage(
                    path, defpng, content)));
        } catch (MtException e) {
            logger.error("�����û���ά������г����쳣", e);
        }
    }

    /**
     * ��װ�û���Ϣ������Ϣ
     * 
     * @param userNickName  �û��ǳƣ��û�����
     * @param userPwd       �û�����
     * @param registerFrom  ע����Դ��
     * @return              �û���Ϣ������Ϣ
     */
    private MtUserMessage constructUserMessage(String userNickName, String userPwd,
                                               String registerFrom) {
        if (StringUtils.isBlank(userPwd) || StringUtils.isBlank(userNickName)) {
            logger.warn("�û�ע����Ϣ������ userNickName=" + userNickName + " userPwd=" + userPwd
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
