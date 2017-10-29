/**
 * Myteay.com Inc.
 * Copyright (c) 2015-2016 All Rights Reserved.
 */
package com.myteay.biz.service.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.myteay.common.service.facade.enums.MtOperateExResultEnum;
import com.myteay.common.service.facade.enums.MtOperateResultEnum;
import com.myteay.common.service.facade.model.MtOperateResult;
import com.myteay.common.service.facade.model.dinner.MtGoodsInfoMessage;
import com.myteay.common.util.constants.MtConstants;
import com.myteay.common.util.exception.MtException;
import com.myteay.common.utils.MtFileUtils;
import com.myteay.core.service.components.MtGoodsInfoComponents;

/**
 * ������Ʒ��Ϣ¼�������
 * 
 * @author Administrator
 * @version $Id: AdmAddSingleGoodsController.java, v 0.1 2016��3��3�� ����9:21:51 Administrator Exp $
 */
@Controller
@RequestMapping("/adm/upload_single_goods")
public class AdmAddSingleGoodsController {

    /** ��־ */
    public static final Logger    logger = Logger.getLogger(AdmAddSingleGoodsController.class);

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
    @RequestMapping(method = RequestMethod.POST)
    public ModelAndView addSingleGoods(HttpSession session,
                                       HttpServletResponse response,
                                       HttpServletRequest request,
                                       String shop_id,
                                       String pic_addr,
                                       String goods_title,
                                       String price,
                                       String summary,
                                       @RequestParam(value = "file", required = false) MultipartFile file)
                                                                                                           throws IOException,
                                                                                                           MtException {
        ModelAndView view = new ModelAndView("upload_single_goods");
        if (logger.isInfoEnabled()) {
            logger.info("��ʼ¼�뵥����Ʒ��Ϣshop_id=" + shop_id + " pic_addr=" + pic_addr + " goods_title="
                        + goods_title + " price=" + price + " summary=" + summary);
        }
        pic_addr = MtFileUtils.upload(file, request, MtConstants.GOODS_INFO_UPLOAD_URI);

        logger.warn("���ϴ��ļ���ַ��" + pic_addr);

        if (StringUtils.isBlank(pic_addr)) {
            logger
                .warn("��ƷͼƬ����ʧ�ܣ��޷���������������Ϣshop_id=" + shop_id + " pic_addr=" + pic_addr
                      + " goods_title=" + goods_title + " price=" + price + " summary=" + summary);
            return view;
        }

        MtGoodsInfoMessage message = constructMessage(shop_id, pic_addr, goods_title, price,
            summary);
        String mt_msg = null;
        if (message == null) {
            logger
                .warn("������Ʒ��Ʒ��Ϣ��������ʧ�ܣ��޷�������Ʒ��Ϣshop_id=" + shop_id + " pic_addr=" + pic_addr
                      + " goods_title=" + goods_title + " price=" + price + " summary=" + summary);
            return view;
        }

        MtOperateResult<String> result = null;
        try {
            result = mtGoodsInfoComponents.saveGoodsInfo(message);
        } catch (Exception e) {
            mt_msg = "������Ʒ��Ʒ��Ϣ��������ʱ�����쳣 message=" + message;
            logger.warn(mt_msg, e);
            return view;
        }

        if (result == null
            || result.getOperateExResult() != MtOperateExResultEnum.CAMP_OPERATE_SUCCESS
            || result.getOperateResult() != MtOperateResultEnum.CAMP_OPERATE_SUCCESS) {
            logger.warn("������Ʒ��Ϣ�����ڲ��쳣��message=" + message + " result=" + result);
            return view;
        }

        if (logger.isInfoEnabled()) {
            logger.info("������Ʒ��Ʒ��Ϣ�ɹ� message=" + message + " result=" + result);
        }

        response.sendRedirect("/myteay-web/adm/query_goods_list");
        return null;
    }

    /**
     * ������Ʒ��Ʒ��Ϣ��������
     * 
     * @param shop_id       ������ˮ��
     * @param pic_addr      ͼƬ��ַ
     * @param goods_title   ��Ʒ����
     * @param price         ��Ʒ�۸�
     * @param summary       ��ע
     * @return              ��Ʒ��Ʒ��Ϣ��������
     */
    private MtGoodsInfoMessage constructMessage(String shop_id, String pic_addr,
                                                String goods_title, String price, String summary) {

        if (StringUtils.isBlank(price) || StringUtils.isBlank(goods_title)
            || StringUtils.isBlank(pic_addr) || StringUtils.isBlank(shop_id)) {
            logger.warn("��Ʒ��Ʒ������Ϣ������shop_id=" + shop_id + " pic_addr=" + pic_addr + " goods_title="
                        + goods_title + " price=" + price);
            return null;
        }

        MtGoodsInfoMessage mtGoodsInfoMessage = new MtGoodsInfoMessage();

        mtGoodsInfoMessage.setGoodsTitle(goods_title);
        mtGoodsInfoMessage.setPicAddr(pic_addr);
        mtGoodsInfoMessage.setPrice(price);
        mtGoodsInfoMessage.setShopId(shop_id);
        mtGoodsInfoMessage.setSummary(summary);
        return mtGoodsInfoMessage;
    }

    /**
     * ����ҳ�浥��
     * 
     * @param session       �����Ự��Ϣ
     * @param response      Ӧ����Ϣ
     * @param request       ������Ϣ
     * @return              ����·��
     * @throws IOException  �쳣
     * @throws MtException  �쳣
     */
    @RequestMapping(method = RequestMethod.GET)
    public ModelAndView querySingleGoodsForm(HttpSession session, HttpServletResponse response,
                                             HttpServletRequest request) throws IOException,
                                                                         MtException {

        if (logger.isInfoEnabled()) {
            logger.info("��ʼ����¼�뵥����Ʒ��Ϣ��");
        }

        return new ModelAndView("upload_single_goods");
    }
}
