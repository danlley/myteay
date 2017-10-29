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
 * 增加套餐商品信息
 * 
 * @author Administrator
 * @version $Id: AdmAddGoodsPkgController.java, v 0.1 2016年3月5日 下午9:15:43 Administrator Exp $
 */
@Controller
@RequestMapping("/adm/add_single_goods_pkg")
public class AdmAddGoodsPkgController {

    /** 日志 */
    public static final Logger       logger = Logger.getLogger(AdmAddGoodsPkgController.class);

    /** 套餐信息管理组件 */
    @Autowired
    private MtGoodsPkgInfoComponents mtGoodsPkgInfoComponents;

    /** 商品管理组件 */
    @Autowired
    private MtGoodsInfoComponents    mtGoodsInfoComponents;

    /**
     * 添加单个套餐信息
     * 
     * @param session       交互会话信息
     * @param response      应答信息
     * @param request       请求信息
     * @return              服务路径
     * @throws IOException  异常
     * @throws MtException  异常
     */
    @RequestMapping(method = RequestMethod.GET)
    public ModelAndView queryGoodsList(HttpSession session, HttpServletResponse response,
                                       HttpServletRequest request) throws IOException, MtException {
        ModelAndView view = new ModelAndView("query_goods_list");
        MtOperateResult<List<MtGoodsModel>> result = null;
        try {
            result = mtGoodsInfoComponents.queryGoodsList();
        } catch (MtBizException e) {
            logger.warn("查询商品信息出现业务异常", e);
        } catch (Throwable e) {
            logger.warn("查询商品信息出现系统异常", e);
        }

        if (result == null || result.getOperateResult() != MtOperateResultEnum.CAMP_OPERATE_SUCCESS
            || result.getOperateExResult() != MtOperateExResultEnum.CAMP_OPERATE_SUCCESS) {
            logger.warn("查询商品信息失败result=" + result);
            return view;
        }

        if (CollectionUtils.isEmpty(result.getResult())) {
            logger.warn("查询成功！当前没有可用的商品信息result=" + result);
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
                logger.info("商品列表组装结束");
            }
            Map<String, Object> model = new HashMap<String, Object>();
            model.put("goods_pkg_add_list", messageList);
            view.addAllObjects(model);
        }
        return view;
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
            logger.info("开始生成录入套餐信息表单 pkgName=" + pkgName + " priceMobile=" + priceMobile
                        + " price=" + price + " picAddr=" + picAddr + " picBigAddr=" + picBigAddr
                        + " shopId=" + shopId + " goodsIds=" + getGoodsIds(goodsIds));
        }
        //开始上传文件
        picAddr = MtFileUtils.upload(fileSmall, request, MtConstants.GOODS_PKG_UPLOAD_URI);
        picBigAddr = MtFileUtils.upload(fileBig, request, MtConstants.GOODS_PKG_UPLOAD_URI);

        if (logger.isInfoEnabled()) {
            logger.info("套餐图片上传结束，picAddr=" + picAddr + " picBigAddr=" + picBigAddr);
        }

        MtGoodsPkgInfoMessage message = constructMtGoodsPkgInfoMessage(pkgName, priceMobile, price,
            picAddr, picBigAddr, shopId, getGoodsIds(goodsIds));
        if (message == null) {
            logger.warn("套餐必填信息不可用，无法生成有效的套餐信息交互单据 pkgName=" + pkgName + " priceMobile="
                        + priceMobile + " price=" + price + " picAddr=" + picAddr + " picBigAddr="
                        + picBigAddr + " shopId=" + shopId + " goodsIds=" + goodsIds);
            return view;
        }

        MtOperateResult<String> result = null;
        try {
            result = mtGoodsPkgInfoComponents.saveGoodsPkgMessage(message);
        } catch (Exception e) {
            logger.warn("套餐信息交互单据保存失败 " + e.getMessage(), e);
        }

        if (result == null
            || result.getOperateExResult() != MtOperateExResultEnum.CAMP_OPERATE_SUCCESS
            || result.getOperateResult() != MtOperateResultEnum.CAMP_OPERATE_SUCCESS) {
            logger.warn("保存套餐信息交互单据发生内部异常，message=" + message + " result=" + result);
            return view;
        }

        response.sendRedirect("/myteay-web/adm/view_single_goods_pkg?id=" + result.getResult());
        return null;
    }

    /**
     * 构建套餐信息交互单据
     * 
     * @param pkgName       套餐名称
     * @param priceMobile   手机端价格
     * @param price         门店价格
     * @param picAddr       小图片地址
     * @param picBigAddr    大图片地址
     * @param shopId        门店ID
     * @param goodsIds      套餐包含的商品ID列表
     * @return              套餐信息交互单据
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
     * 合成商品ID
     * 
     * @param goodsIds  商品单品ID列表
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
        logger.warn("当前套餐配置的商品信息为：goodsId=" + goodsId);
        return goodsId;
    }
}
