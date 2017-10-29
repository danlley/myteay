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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.myteay.common.service.facade.enums.MtOperateExResultEnum;
import com.myteay.common.service.facade.enums.MtOperateResultEnum;
import com.myteay.common.service.facade.exceptions.MtBizException;
import com.myteay.common.service.facade.model.MtOperateResult;
import com.myteay.common.service.facade.model.dinner.MtGoodsInfoMessage;
import com.myteay.common.service.facade.model.dinner.MtGoodsPkgInfoMessage;
import com.myteay.common.util.constants.MtConstants;
import com.myteay.common.util.exception.MtException;
import com.myteay.common.util.tools.StringUtils;
import com.myteay.common.utils.MtFileUtils;
import com.myteay.core.model.dinner.MtGoodsModel;
import com.myteay.core.model.user.convt.MtGoodsModelConvertor;
import com.myteay.core.service.components.MtGoodsInfoComponents;
import com.myteay.core.service.components.MtGoodsPkgInfoComponents;

/**
 * �����ײ���Ʒ��Ϣ
 * 
 * @author Administrator
 * @version $Id: AdmAddGoodsPkgController.java, v 0.1 2016��3��5�� ����9:15:43 Administrator Exp $
 */
@Controller
@RequestMapping("/adm/add_single_goods_pkg")
public class AdmAddGoodsPkgController {

    /** ��־ */
    public static final Logger       logger = Logger.getLogger(AdmAddGoodsPkgController.class);

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

        view = new ModelAndView("add_single_goods_pkg");
        if (!CollectionUtils.isEmpty(messageList)) {
            if (logger.isInfoEnabled()) {
                logger.info("��Ʒ�б���װ����");
            }
            Map<String, Object> model = new HashMap<String, Object>();
            model.put("goods_pkg_add_list", messageList);
            view.addAllObjects(model);
        }
        return view;
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
    @RequestMapping(method = RequestMethod.POST)
    public ModelAndView querySingleGoodsForm(HttpSession session,
                                             HttpServletResponse response,
                                             HttpServletRequest request,
                                             String pkgName,
                                             String priceMobile,
                                             String price,
                                             String picAddr,
                                             String picBigAddr,
                                             String shopId,
                                             String[] goodsIds,
                                             @RequestParam(value = "fileBig", required = false) MultipartFile fileBig,
                                             @RequestParam(value = "fileSmall", required = false) MultipartFile fileSmall)
                                                                                                                           throws IOException,
                                                                                                                           MtException {

        ModelAndView view = new ModelAndView("add_single_goods_pkg");
        if (logger.isInfoEnabled()) {
            logger.info("��ʼ����¼���ײ���Ϣ�� pkgName=" + pkgName + " priceMobile=" + priceMobile
                        + " price=" + price + " picAddr=" + picAddr + " picBigAddr=" + picBigAddr
                        + " shopId=" + shopId + " goodsIds=" + getGoodsIds(goodsIds));
        }
        //��ʼ�ϴ��ļ�
        picAddr = MtFileUtils.upload(fileSmall, request, MtConstants.GOODS_PKG_UPLOAD_URI);
        picBigAddr = MtFileUtils.upload(fileBig, request, MtConstants.GOODS_PKG_UPLOAD_URI);

        if (logger.isInfoEnabled()) {
            logger.info("�ײ�ͼƬ�ϴ�������picAddr=" + picAddr + " picBigAddr=" + picBigAddr);
        }

        MtGoodsPkgInfoMessage message = constructMtGoodsPkgInfoMessage(pkgName, priceMobile, price,
            picAddr, picBigAddr, shopId, getGoodsIds(goodsIds));
        if (message == null) {
            logger.warn("�ײͱ�����Ϣ�����ã��޷�������Ч���ײ���Ϣ�������� pkgName=" + pkgName + " priceMobile="
                        + priceMobile + " price=" + price + " picAddr=" + picAddr + " picBigAddr="
                        + picBigAddr + " shopId=" + shopId + " goodsIds=" + goodsIds);
            return view;
        }

        MtOperateResult<String> result = null;
        try {
            result = mtGoodsPkgInfoComponents.saveGoodsPkgMessage(message);
        } catch (Exception e) {
            logger.warn("�ײ���Ϣ�������ݱ���ʧ�� " + e.getMessage(), e);
        }

        if (result == null
            || result.getOperateExResult() != MtOperateExResultEnum.CAMP_OPERATE_SUCCESS
            || result.getOperateResult() != MtOperateResultEnum.CAMP_OPERATE_SUCCESS) {
            logger.warn("�����ײ���Ϣ�������ݷ����ڲ��쳣��message=" + message + " result=" + result);
            return view;
        }

        response.sendRedirect("/myteay-web/adm/view_single_goods_pkg?id=" + result.getResult());
        return null;
    }

    /**
     * �����ײ���Ϣ��������
     * 
     * @param pkgName       �ײ�����
     * @param priceMobile   �ֻ��˼۸�
     * @param price         �ŵ�۸�
     * @param picAddr       СͼƬ��ַ
     * @param picBigAddr    ��ͼƬ��ַ
     * @param shopId        �ŵ�ID
     * @param goodsIds      �ײͰ�������ƷID�б�
     * @return              �ײ���Ϣ��������
     */
    private MtGoodsPkgInfoMessage constructMtGoodsPkgInfoMessage(String pkgName,
                                                                 String priceMobile, String price,
                                                                 String picAddr, String picBigAddr,
                                                                 String shopId, String goodsIds) {

        if (StringUtils.isBlank(goodsIds) || StringUtils.isBlank(shopId)
            || StringUtils.isBlank(picBigAddr) || StringUtils.isBlank(picAddr)
            || StringUtils.isBlank(price) || StringUtils.isBlank(priceMobile)
            || StringUtils.isBlank(pkgName)) {
            return null;
        }

        MtGoodsPkgInfoMessage message = new MtGoodsPkgInfoMessage();

        message.setGoodsId(goodsIds);
        message.setPicAddr(picAddr);
        message.setPicBigAddr(picBigAddr);
        message.setPkgName(pkgName);
        message.setPrice(price);
        message.setPriceMobile(priceMobile);
        message.setShopId(shopId);

        return message;
    }

    /**
     * �ϳ���ƷID
     * 
     * @param goodsIds  ��Ʒ��ƷID�б�
     * @return
     */
    private String getGoodsIds(String[] goodsIds) {
        if (goodsIds == null || goodsIds.length <= 0) {
            return null;
        }
        String goodsId = StringUtils.EMPTY_STRING;
        for (String str : goodsIds) {
            goodsId += (str + MtConstants.GOODS_PREFIX_FOR_GOODS_PKG);
        }
        logger.warn("��ǰ�ײ����õ���Ʒ��ϢΪ��goodsId=" + goodsId);
        return goodsId;
    }
}
