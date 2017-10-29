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

import com.myteay.common.async.event.EventPublishService;
import com.myteay.common.async.event.MtEvent;
import com.myteay.common.async.event.MtEventException;
import com.myteay.common.service.facade.enums.MtOperateExResultEnum;
import com.myteay.common.service.facade.enums.MtOperateResultEnum;
import com.myteay.common.service.facade.exceptions.MtBizException;
import com.myteay.common.service.facade.model.MtOperateResult;
import com.myteay.common.service.facade.model.dinner.MtGoodsPkgInfoMessage;
import com.myteay.common.util.event.MtEventTopicEnum;
import com.myteay.common.util.exception.MtException;
import com.myteay.core.model.dinner.MtGoodsPkgModel;
import com.myteay.core.model.user.convt.MtGoodsPkgModelConvertor;
import com.myteay.core.service.components.MtGoodsPkgInfoComponents;

/**
 * ��ѯ�����ײ���Ϣ
 * 
 * @author Administrator
 * @version $Id: QueryPkgGoodsListController.java, v 0.1 2016��3��5�� ����9:10:39 Administrator Exp $
 */
@Controller
@RequestMapping("/adm/query_pkg_goods_list")
public class AdmQueryPkgGoodsListController {

    /** ��־ */
    public static final Logger          logger = Logger
        .getLogger(AdmQueryPkgGoodsListController.class);

    /** �ײ���Ϣ������� */
    @Autowired
    private MtGoodsPkgInfoComponents    mtGoodsPkgInfoComponents;

    /** �ײ���Ϣ������� */
    @Autowired
    private EventPublishService<String> eventPublishService;

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
                                       HttpServletRequest request) throws IOException, MtEventException {
        MtOperateResult<List<MtGoodsPkgModel>> result = null;
        ModelAndView view = new ModelAndView("query_pkg_goods_list");
        try {
            result = mtGoodsPkgInfoComponents.queryGoodsPkgList();
        } catch (MtBizException e) {
            logger.warn("��ѯ�ײ���Ϣ����ҵ���쳣", e);
        } catch (Throwable e) {
            logger.warn("��ѯ�ײ���Ϣ����ϵͳ�쳣", e);
        }

        //TODO �����첽�¼��������
        eventPublishService.publishEvent(new MtEvent<String>(
            MtEventTopicEnum.MT_CACHE_GOODS_PKG_FRESH.getValue(), null));

        if (result == null || result.getOperateResult() != MtOperateResultEnum.CAMP_OPERATE_SUCCESS
            || result.getOperateExResult() != MtOperateExResultEnum.CAMP_OPERATE_SUCCESS) {
            logger.warn("��ѯ�ײ���Ϣʧ��result=" + result);
            return view;
        }

        if (CollectionUtils.isEmpty(result.getResult())) {
            logger.warn("��ѯ�ɹ�����ǰû�п��õ��ײ���Ϣresult=" + result);
            return view;
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

        if (!CollectionUtils.isEmpty(messageList)) {
            if (logger.isInfoEnabled()) {
                logger.info("�ײ��б���װ����");
            }

            Map<String, Object> model = new HashMap<String, Object>();
            model.put("goods_pkg_list", messageList);
            view.addAllObjects(model);
        }
        return view;
    }

}
