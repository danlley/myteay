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
 * 后台页面查询套餐详情
 * 
 * @author Administrator
 * @version $Id: AdmViewGoodsPkgDetailController.java, v 0.1 2016年3月5日 下午11:55:53 Administrator Exp $
 */
@Controller
@RequestMapping("/adm/view_single_goods_pkg")
public class AdmViewGoodsPkgDetailController {

    /** 日志 */
    public static final Logger       logger = Logger
        .getLogger(AdmViewGoodsPkgDetailController.class);

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
                                       HttpServletRequest request, String id) throws IOException,
                                                                              MtException {
        ModelAndView view = new ModelAndView("view_single_goods_pkg");
        MtOperateResult<MtGoodsPkgInfoMessage> result = null;

        try {
            result = mtGoodsPkgInfoComponents.findGoodsPkgById(id);
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
        if (result.getResult() == null) {
            logger.warn("查询成功！当前没有可用的商品信息result=" + result);
            return view;
        }

        Map<String, Object> model = new HashMap<String, Object>();

        //设置详情的基本信息
        model.put("goods_pkg_view", result.getResult());

        List<MtGoodsInfoMessage> messageList = getGoodsInfoList(result.getResult());
        if (!CollectionUtils.isEmpty(messageList)) {
            //设置详情中的单品信息
            model.put("goods_pkg_view_goods_info_list", messageList);
        }
        view.addAllObjects(model);
        return view;
    }

    /**
     * 得到套餐信息中的单品信息交互单据列表
     * 
     * @param messagePkg    套餐信息交互单据
     * @return              单品信息交互单据列表
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
                logger.info("拆分后的单品信息： goodsId=" + goodsId);
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
     * 通过单品ID得到单品信息交互单据
     * 
     * @param goodsId   单品ID
     * @return          单品信息交互单据
     */
    private MtGoodsInfoMessage getGoodsInfoMessage(String goodsId) {

        if (logger.isInfoEnabled()) {
            logger.info("开始查询套餐中的单品信息交互单据 goodsId=" + goodsId);
        }

        MtOperateResult<MtGoodsInfoMessage> result = mtGoodsInfoComponents
            .findGoodsInfoById(goodsId);
        if (result == null || result.getOperateResult() != MtOperateResultEnum.CAMP_OPERATE_SUCCESS
            || result.getOperateExResult() != MtOperateExResultEnum.CAMP_OPERATE_SUCCESS) {
            logger.warn("查询单品信息失败result=" + result);
            return null;
        }

        return result.getResult();
    }

    /**
     * 得到套餐中包含的单品信息
     * 
     * @param goodsIds  单品ID集合
     * @return          单品ID列表
     */
    private String[] getIdArray(String goodsIds) {
        if (StringUtils.isBlank(goodsIds)) {
            return null;
        }

        return goodsIds.split("\\|");
    }
}
