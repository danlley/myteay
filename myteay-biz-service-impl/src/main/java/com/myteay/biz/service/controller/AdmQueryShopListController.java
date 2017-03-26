/**
 * Myteay.com Inc.
 * Copyright (c) 2015-2016 All Rights Reserved.
 */
package com.myteay.biz.service.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
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

import com.myteay.common.service.facade.enums.MtOperateExResultEnum;
import com.myteay.common.service.facade.enums.MtOperateResultEnum;
import com.myteay.common.service.facade.exceptions.MtBizException;
import com.myteay.common.service.facade.model.MtOperateResult;
import com.myteay.common.service.facade.model.dinner.MtShopInfoMessage;
import com.myteay.common.util.comm.CollectionUtils;
import com.myteay.common.utils.exception.MtException;
import com.myteay.core.model.dinner.MtShopModel;
import com.myteay.core.service.components.MtShopInfoComponents;

/**
 * ��ѯ������Ϣ�б�
 * 
 * @author Administrator
 * @version $Id: QueryShopListController.java, v 0.1 2016��3��4�� ����5:07:13 Administrator Exp $
 */
@Controller
@RequestMapping("/adm/quey_shop_list")
public class AdmQueryShopListController {

    /** ��־ */
    public static final Logger   logger = Logger.getLogger(AdmQueryShopListController.class);

    /** ���̹������ */
    @Autowired
    private MtShopInfoComponents mtShopInfoComponents;

    /**
     * ��ӵ�����Ʒ��Ϣ
     * 
     * @param session       �����Ự��Ϣ
     * @param response      Ӧ����Ϣ
     * @param request       ������Ϣ
     * @return              ����·��
     * @throws IOException  �쳣
     * @throws MtException  �쳣
     */
    @RequestMapping(method = RequestMethod.GET)
    public ModelAndView queryShopList(HttpSession session, HttpServletResponse response,
                                      HttpServletRequest request) throws IOException, MtException {

        ModelAndView view = new ModelAndView("quey_shop_list");
        MtOperateResult<List<MtShopModel>> result = null;
        try {
            result = mtShopInfoComponents.queryShopList();
        } catch (MtBizException e) {
            logger.warn("��ѯ������Ϣ����ҵ���쳣", e);
        } catch (Throwable e) {
            logger.warn("��ѯ������Ϣ����ϵͳ�쳣", e);
        }

        if (result == null || result.getOperateResult() != MtOperateResultEnum.CAMP_OPERATE_SUCCESS
            || result.getOperateExResult() != MtOperateExResultEnum.CAMP_OPERATE_SUCCESS) {
            logger.warn("��ѯ������Ϣʧ��result=" + result);
            return view;
        }

        if (CollectionUtils.isEmpty(result.getResult())) {
            logger.warn("��ѯ�ɹ�����ǰû�п��õĵ�����Ϣresult=" + result);
            return view;
        }

        List<MtShopModel> modelList = result.getResult();
        MtShopInfoMessage message = null;
        List<MtShopInfoMessage> messageList = new ArrayList<MtShopInfoMessage>();
        for (MtShopModel model : modelList) {
            message = convertModel2Message(model);
            if (message == null) {
                continue;
            }
            messageList.add(message);
        }

        if (!CollectionUtils.isEmpty(messageList)) {
            if (logger.isInfoEnabled()) {
                logger.info("�����б���װ����");
            }
            Map<String, Object> model = new HashMap<String, Object>();
            model.put("shop_list", messageList);
            view.addAllObjects(model);
        }

        return view;

    }

    /**
     * ������ģ��ת��Ϊ���̽�����Ϣ
     * 
     * @param model ����ģ��
     * @return      ���̽�����Ϣ
     */
    private MtShopInfoMessage convertModel2Message(MtShopModel model) {

        if (model == null) {
            logger.warn("��ǰ����ģ���޷�ת���ɿ��õĵ��̽�����Ϣ model is null");
            return null;
        }

        MtShopInfoMessage message = new MtShopInfoMessage();
        message.setCityCode(model.getCityCode());
        message.setGmtCreated(model.getGmtCreated());
        message.setGmtModified(model.getGmtModified());
        message.setShopAddr(model.getShopAddr());
        message.setShopId(model.getShopId());
        message.setShopName(model.getShopName());
        message.setShopTel(model.getShopTel());
        return message;
    }
}
