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
import com.myteay.common.service.facade.model.dinner.MtShopInfoMessage;
import com.myteay.common.util.exception.MtException;
import com.myteay.core.model.dinner.MtShopModel;
import com.myteay.core.service.components.MtShopInfoComponents;

/**
 * 查询店铺信息列表
 * 
 * @author Administrator
 * @version $Id: QueryShopListController.java, v 0.1 2016年3月4日 下午5:07:13 Administrator Exp $
 */
@Controller
@RequestMapping("/adm/quey_shop_list")
public class AdmQueryShopListController {

    /** 日志 */
    public static final Logger   logger = Logger.getLogger(AdmQueryShopListController.class);

    /** 店铺管理组件 */
    @Autowired
    private MtShopInfoComponents mtShopInfoComponents;

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
    @RequestMapping(method = RequestMethod.GET)
    public ModelAndView queryShopList(HttpSession session, HttpServletResponse response,
                                      HttpServletRequest request) throws IOException, MtException {

        ModelAndView view = new ModelAndView("quey_shop_list");
        MtOperateResult<List<MtShopModel>> result = null;
        try {
            result = mtShopInfoComponents.queryShopList();
        } catch (MtBizException e) {
            logger.warn("查询店铺信息出现业务异常", e);
        } catch (Throwable e) {
            logger.warn("查询店铺信息出现系统异常", e);
        }

        if (result == null || result.getOperateResult() != MtOperateResultEnum.CAMP_OPERATE_SUCCESS
            || result.getOperateExResult() != MtOperateExResultEnum.CAMP_OPERATE_SUCCESS) {
            logger.warn("查询店铺信息失败result=" + result);
            return view;
        }

        if (CollectionUtils.isEmpty(result.getResult())) {
            logger.warn("查询成功！当前没有可用的店铺信息result=" + result);
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
                logger.info("店铺列表组装结束");
            }
            Map<String, Object> model = new HashMap<String, Object>();
            model.put("shop_list", messageList);
            view.addAllObjects(model);
        }

        return view;

    }

    /**
     * 将店铺模型转换为店铺交互信息
     * 
     * @param model 店铺模型
     * @return      店铺交互信息
     */
    private MtShopInfoMessage convertModel2Message(MtShopModel model) {

        if (model == null) {
            logger.warn("当前店铺模型无法转换成可用的店铺交互信息 model is null");
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
