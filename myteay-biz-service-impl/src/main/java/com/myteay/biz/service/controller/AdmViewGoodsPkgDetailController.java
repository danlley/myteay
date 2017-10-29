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

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.myteay.common.service.facade.enums.MtOperateExResultEnum;
import com.myteay.common.service.facade.enums.MtOperateResultEnum;
import com.myteay.common.service.facade.exceptions.MtBizException;
import com.myteay.common.service.facade.model.MtOperateResult;
import com.myteay.common.service.facade.model.dinner.MtGoodsInfoMessage;
import com.myteay.common.service.facade.model.dinner.MtGoodsPkgInfoMessage;
import com.myteay.common.util.exception.MtException;
import com.myteay.core.service.components.MtGoodsInfoComponents;
import com.myteay.core.service.components.MtGoodsPkgInfoComponents;

/**
 * ��̨ҳ���ѯ�ײ�����
 * 
 * @author Administrator
 * @version $Id: AdmViewGoodsPkgDetailController.java, v 0.1 2016��3��5�� ����11:55:53 Administrator Exp $
 */
@Controller
@RequestMapping("/adm/view_single_goods_pkg")
public class AdmViewGoodsPkgDetailController {

    /** ��־ */
    public static final Logger       logger = Logger
        .getLogger(AdmViewGoodsPkgDetailController.class);

    /** �ײ���Ϣ������� */
    @Autowired
    private MtGoodsPkgInfoComponents mtGoodsPkgInfoComponents;

    /** ��Ʒ������� */
    @Autowired
    private MtGoodsInfoComponents    mtGoodsInfoComponents;

    /**
     * ��ӵ����ײ���Ϣ
     * 
     * @param session       �����Ự��Ϣ
     * @param response      Ӧ����Ϣ
     * @param request       ������Ϣ
     * @return              ����·��
     * @throws IOException  �쳣
     * @throws MtException  �쳣
     */
    @RequestMapping(method = RequestMethod.GET)
    public ModelAndView queryGoodsList(HttpSession session, HttpServletResponse response,
                                       HttpServletRequest request, String id) throws IOException,
                                                                              MtException {
        ModelAndView view = new ModelAndView("view_single_goods_pkg");
        MtOperateResult<MtGoodsPkgInfoMessage> result = null;

        try {
            result = mtGoodsPkgInfoComponents.findGoodsPkgById(id);
        } catch (MtBizException e) {
            logger.warn("��ѯ��Ʒ��Ϣ����ҵ���쳣", e);
        } catch (Throwable e) {
            logger.warn("��ѯ��Ʒ��Ϣ����ϵͳ�쳣", e);
        }

        if (result == null || result.getOperateResult() != MtOperateResultEnum.CAMP_OPERATE_SUCCESS
            || result.getOperateExResult() != MtOperateExResultEnum.CAMP_OPERATE_SUCCESS) {
            logger.warn("��ѯ��Ʒ��Ϣʧ��result=" + result);
            return view;
        }
        if (result.getResult() == null) {
            logger.warn("��ѯ�ɹ�����ǰû�п��õ���Ʒ��Ϣresult=" + result);
            return view;
        }

        Map<String, Object> model = new HashMap<String, Object>();

        //��������Ļ�����Ϣ
        model.put("goods_pkg_view", result.getResult());

        List<MtGoodsInfoMessage> messageList = getGoodsInfoList(result.getResult());
        if (!CollectionUtils.isEmpty(messageList)) {
            //���������еĵ�Ʒ��Ϣ
            model.put("goods_pkg_view_goods_info_list", messageList);
        }
        view.addAllObjects(model);
        return view;
    }

    /**
     * �õ��ײ���Ϣ�еĵ�Ʒ��Ϣ���������б�
     * 
     * @param messagePkg    �ײ���Ϣ��������
     * @return              ��Ʒ��Ϣ���������б�
     */
    private List<MtGoodsInfoMessage> getGoodsInfoList(MtGoodsPkgInfoMessage messagePkg) {
        if (messagePkg == null) {
            logger.warn("messagePkg is null");
            return null;
        }

        String[] goodsIdArray = getIdArray(messagePkg.getGoodsId());
        List<MtGoodsInfoMessage> messageList = new ArrayList<MtGoodsInfoMessage>();
        MtGoodsInfoMessage message = null;
        for (String goodsId : goodsIdArray) {

            if (logger.isInfoEnabled()) {
                logger.info("��ֺ�ĵ�Ʒ��Ϣ�� goodsId=" + goodsId);
            }

            message = getGoodsInfoMessage(goodsId);
            if (message == null) {
                continue;
            }
            messageList.add(message);
        }

        return messageList;
    }

    /**
     * ͨ����ƷID�õ���Ʒ��Ϣ��������
     * 
     * @param goodsId   ��ƷID
     * @return          ��Ʒ��Ϣ��������
     */
    private MtGoodsInfoMessage getGoodsInfoMessage(String goodsId) {

        if (logger.isInfoEnabled()) {
            logger.info("��ʼ��ѯ�ײ��еĵ�Ʒ��Ϣ�������� goodsId=" + goodsId);
        }

        MtOperateResult<MtGoodsInfoMessage> result = mtGoodsInfoComponents
            .findGoodsInfoById(goodsId);
        if (result == null || result.getOperateResult() != MtOperateResultEnum.CAMP_OPERATE_SUCCESS
            || result.getOperateExResult() != MtOperateExResultEnum.CAMP_OPERATE_SUCCESS) {
            logger.warn("��ѯ��Ʒ��Ϣʧ��result=" + result);
            return null;
        }

        return result.getResult();
    }

    /**
     * �õ��ײ��а����ĵ�Ʒ��Ϣ
     * 
     * @param goodsIds  ��ƷID����
     * @return          ��ƷID�б�
     */
    private String[] getIdArray(String goodsIds) {
        if (StringUtils.isBlank(goodsIds)) {
            return null;
        }

        return goodsIds.split("\\|");
    }
}
