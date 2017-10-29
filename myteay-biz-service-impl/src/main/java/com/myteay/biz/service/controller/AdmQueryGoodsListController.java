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
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.myteay.common.service.facade.enums.MtOperateExResultEnum;
import com.myteay.common.service.facade.enums.MtOperateResultEnum;
import com.myteay.common.service.facade.exceptions.MtBizException;
import com.myteay.common.service.facade.model.MtOperateResult;
import com.myteay.common.service.facade.model.dinner.MtGoodsInfoMessage;
import com.myteay.common.util.exception.MtException;
import com.myteay.core.model.dinner.MtGoodsModel;
import com.myteay.core.model.user.convt.MtGoodsModelConvertor;
import com.myteay.core.service.components.MtGoodsInfoComponents;

/**
 * ��ѯ���е���Ʒ��Ϣ
 * 
 * @author Administrator
 * @version $Id: QueryGoodsListController.java, v 0.1 2016��3��5�� ����12:59:12 Administrator Exp $
 */
@Controller
@RequestMapping("/adm/query_goods_list")
public class AdmQueryGoodsListController {

    /** ��־ */
    public static final Logger    logger = Logger.getLogger(AdmQueryGoodsListController.class);

    /** ��Ʒ������� */
    @Autowired
    private MtGoodsInfoComponents mtGoodsInfoComponents;

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
    public ModelAndView queryGoodsList(HttpSession session, HttpServletResponse response,
                                       HttpServletRequest request) throws IOException, MtException {
        ModelAndView view = new ModelAndView("query_goods_list");
        MtOperateResult<List<MtGoodsModel>> result = null;
        try {
            result = mtGoodsInfoComponents.queryGoodsList();
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

        if (CollectionUtils.isEmpty(result.getResult())) {
            logger.warn("��ѯ�ɹ�����ǰû�п��õ���Ʒ��Ϣresult=" + result);
            return view;
        }

        List<MtGoodsModel> modelList = result.getResult();
        MtGoodsInfoMessage message = null;
        List<MtGoodsInfoMessage> messageList = new ArrayList<MtGoodsInfoMessage>();
        for (MtGoodsModel model : modelList) {
            message = MtGoodsModelConvertor.convertModel2Message(model);
            if (message == null) {
                continue;
            }
            messageList.add(message);
        }

        if (!CollectionUtils.isEmpty(messageList)) {
            if (logger.isInfoEnabled()) {
                logger.info("��Ʒ�б���װ����");
            }
            Map<String, Object> model = new HashMap<String, Object>();
            model.put("goods_list", messageList);
            view.addAllObjects(model);
        }
        return view;
    }

}
