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
import com.myteay.common.service.facade.model.dinner.MtGoodsPkgInfoMessage;
import com.myteay.common.util.exception.MtException;
import com.myteay.core.model.dinner.MtGoodsPkgModel;
import com.myteay.core.model.user.convt.MtGoodsPkgModelConvertor;
import com.myteay.core.service.components.MtGoodsPkgInfoComponents;

/**
 * �ײͽ��㴦��
 * 
 * @author Administrator
 * @version $Id: FrtFoodShopGoodsPkgController.java, v 0.1 2016��4��2�� ����9:22:32 Administrator Exp $
 */
@Controller
@RequestMapping("/front/food_shop")
public class FrtFoodShopGoodsPkgController {

    /** ��־ */
    public static final Logger       logger = Logger.getLogger(FrtFoodShopGoodsPkgController.class);

    /** �ײ���Ϣ������� */
    @Autowired
    private MtGoodsPkgInfoComponents mtGoodsPkgInfoComponents;

    /**
     * ��ѯ�ײ���Ϣ
     * 
     * @param session       �����Ự��Ϣ
     * @param response      Ӧ����Ϣ
     * @param request       ������Ϣ
     * @param goodsPkgName  �ײ�����
     * @return              ����·��
     * @throws IOException  �쳣
     * @throws MtException  �쳣
     */
    @RequestMapping(method = RequestMethod.GET)
    public ModelAndView queryFrtGoodsPkgList(HttpSession session, HttpServletResponse response,
                                             HttpServletRequest request, String goodsPkgName)
                                                                                              throws IOException,
                                                                                              MtException {
        List<MtGoodsPkgInfoMessage> list = null;
        if (StringUtils.isBlank(goodsPkgName)) {
            list = emptySearch();
        } else {
            list = searchByGoodsPkgName(goodsPkgName);
        }

        ModelAndView view = new ModelAndView("front/food_shop");
        if (!CollectionUtils.isEmpty(list)) {
            if (logger.isInfoEnabled()) {
                logger.info("�ײ��б���װ����");
            }

            Map<String, Object> model = new HashMap<String, Object>();
            model.put("goods_pkg_list", list);
            view.addAllObjects(model);
        }
        return view;
    }

    /**
     * ͨ���ײ����Ʋ�ѯ�ײ���Ϣ
     * 
     * @param goodsPkgName  �ײ�����
     * @return              �ײͽ�����Ϣ�б�
     */
    private List<MtGoodsPkgInfoMessage> searchByGoodsPkgName(String goodsPkgName) {

        if (logger.isInfoEnabled()) {
            logger.info("��ʼͨ���ײ����Ʋ�ѯ�ײ���Ϣ�б�goodsPkgName=" + goodsPkgName);
        }
        return null;
    }

    /**
     * ��ѯ�����ײ���Ϣ
     * @return
     */
    private List<MtGoodsPkgInfoMessage> emptySearch() {
        MtOperateResult<List<MtGoodsPkgModel>> result = null;

        try {
            result = mtGoodsPkgInfoComponents.queryGoodsPkgList();
        } catch (MtBizException e) {
            logger.warn("��ѯ�ײ���Ϣ����ҵ���쳣", e);
        } catch (Throwable e) {
            logger.warn("��ѯ�ײ���Ϣ����ϵͳ�쳣", e);
        }

        if (result == null || result.getOperateResult() != MtOperateResultEnum.CAMP_OPERATE_SUCCESS
            || result.getOperateExResult() != MtOperateExResultEnum.CAMP_OPERATE_SUCCESS) {
            logger.warn("��ѯ�ײ���Ϣʧ��result=" + result);
            return null;
        }

        if (CollectionUtils.isEmpty(result.getResult())) {
            logger.warn("��ѯ�ɹ�����ǰû�п��õ��ײ���Ϣresult=" + result);
            return null;
        }

        List<MtGoodsPkgModel> modelList = result.getResult();
        MtGoodsPkgInfoMessage message = null;
        List<MtGoodsPkgInfoMessage> messageList = new ArrayList<MtGoodsPkgInfoMessage>();
        for (MtGoodsPkgModel model : modelList) {
            message = MtGoodsPkgModelConvertor.convertModel2Message(model);
            if (message == null) {
                continue;
            }
            messageList.add(message);
        }

        return messageList;
    }
}
