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
import com.myteay.common.util.comm.StringUtils;
import com.myteay.common.util.constants.MtConstants;
import com.myteay.common.utils.MtFileUtils;
import com.myteay.common.utils.exception.MtException;
import com.myteay.core.service.components.MtGoodsInfoComponents;

/**
 * 单个商品信息录入控制器
 * 
 * @author Administrator
 * @version $Id: AdmAddSingleGoodsController.java, v 0.1 2016年3月3日 下午9:21:51 Administrator Exp $
 */
@Controller
@RequestMapping("/adm/upload_single_goods")
public class AdmAddSingleGoodsController {

    /** 日志 */
    public static final Logger    logger = Logger.getLogger(AdmAddSingleGoodsController.class);

    /** 商品管理组件 */
    @Autowired
    private MtGoodsInfoComponents mtGoodsInfoComponents;

    /**
     * 添加单个商品信息
     * 
     * @param session       交互会话信息
     * @param response      应答信息
     * @param request       请求信息
     * @return              服务路径
     * @throws IOException  异常
     * @throws MtException  异常
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
            logger.info("开始录入单个商品信息shop_id=" + shop_id + " pic_addr=" + pic_addr + " goods_title="
                        + goods_title + " price=" + price + " summary=" + summary);
        }
        pic_addr = MtFileUtils.upload(file, request, MtConstants.GOODS_INFO_UPLOAD_URI);

        logger.warn("待上传文件地址：" + pic_addr);

        if (StringUtils.isBlank(pic_addr)) {
            logger
                .warn("产品图片保存失败，无法继续保存其他信息shop_id=" + shop_id + " pic_addr=" + pic_addr
                      + " goods_title=" + goods_title + " price=" + price + " summary=" + summary);
            return view;
        }

        MtGoodsInfoMessage message = constructMessage(shop_id, pic_addr, goods_title, price,
            summary);
        String mt_msg = null;
        if (message == null) {
            logger
                .warn("构建商品单品信息交互单据失败，无法保存商品信息shop_id=" + shop_id + " pic_addr=" + pic_addr
                      + " goods_title=" + goods_title + " price=" + price + " summary=" + summary);
            return view;
        }

        MtOperateResult<String> result = null;
        try {
            result = mtGoodsInfoComponents.saveGoodsInfo(message);
        } catch (Exception e) {
            mt_msg = "保存商品单品信息交互单据时发生异常 message=" + message;
            logger.warn(mt_msg, e);
            return view;
        }

        if (result == null
            || result.getOperateExResult() != MtOperateExResultEnum.CAMP_OPERATE_SUCCESS
            || result.getOperateResult() != MtOperateResultEnum.CAMP_OPERATE_SUCCESS) {
            logger.warn("保存商品信息发生内部异常，message=" + message + " result=" + result);
            return view;
        }

        if (logger.isInfoEnabled()) {
            logger.info("保存商品单品信息成功 message=" + message + " result=" + result);
        }

        response.sendRedirect("/myteay-web/adm/query_goods_list");
        return null;
    }

    /**
     * 构建商品单品信息交互单据
     * 
     * @param shop_id       店铺流水号
     * @param pic_addr      图片地址
     * @param goods_title   商品名称
     * @param price         商品价格
     * @param summary       备注
     * @return              商品单品信息交互单据
     */
    private MtGoodsInfoMessage constructMessage(String shop_id, String pic_addr,
                                                String goods_title, String price, String summary) {

        if (StringUtils.isBlank(price) || StringUtils.isBlank(goods_title)
            || StringUtils.isBlank(pic_addr) || StringUtils.isBlank(shop_id)) {
            logger.warn("商品单品必填信息不可用shop_id=" + shop_id + " pic_addr=" + pic_addr + " goods_title="
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
     * 生成页面单据
     * 
     * @param session       交互会话信息
     * @param response      应答信息
     * @param request       请求信息
     * @return              服务路径
     * @throws IOException  异常
     * @throws MtException  异常
     */
    @RequestMapping(method = RequestMethod.GET)
    public ModelAndView querySingleGoodsForm(HttpSession session, HttpServletResponse response,
                                             HttpServletRequest request) throws IOException,
                                                                        MtException {

        if (logger.isInfoEnabled()) {
            logger.info("开始生成录入单个商品信息表单");
        }

        return new ModelAndView("upload_single_goods");
    }
}
