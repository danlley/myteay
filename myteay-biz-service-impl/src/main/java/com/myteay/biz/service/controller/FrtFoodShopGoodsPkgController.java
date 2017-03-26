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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.myteay.common.service.facade.enums.MtOperateExResultEnum;
import com.myteay.common.service.facade.enums.MtOperateResultEnum;
import com.myteay.common.service.facade.exceptions.MtBizException;
import com.myteay.common.service.facade.model.MtOperateResult;
import com.myteay.common.service.facade.model.dinner.MtGoodsPkgInfoMessage;
import com.myteay.common.util.comm.CollectionUtils;
import com.myteay.common.util.comm.StringUtils;
import com.myteay.common.utils.exception.MtException;
import com.myteay.core.model.dinner.MtGoodsPkgModel;
import com.myteay.core.model.user.convt.MtGoodsPkgModelConvertor;
import com.myteay.core.service.components.MtGoodsPkgInfoComponents;

/**
 * 套餐结算处理
 * 
 * @author Administrator
 * @version $Id: FrtFoodShopGoodsPkgController.java, v 0.1 2016年4月2日 下午9:22:32 Administrator Exp $
 */
@Controller
@RequestMapping("/front/food_shop")
public class FrtFoodShopGoodsPkgController {

    /** 日志 */
    public static final Logger       logger = Logger.getLogger(FrtFoodShopGoodsPkgController.class);

    /** 套餐信息管理组件 */
    @Autowired
    private MtGoodsPkgInfoComponents mtGoodsPkgInfoComponents;

    /**
     * 查询套餐信息
     * 
     * @param session       交互会话信息
     * @param response      应答信息
     * @param request       请求信息
     * @param goodsPkgName  套餐名称
     * @return              服务路径
     * @throws IOException  异常
     * @throws MtException  异常
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
                logger.info("套餐列表组装结束");
            }

            Map<String, Object> model = new HashMap<String, Object>();
            model.put("goods_pkg_list", list);
            view.addAllObjects(model);
        }
        return view;
    }

    /**
     * 通过套餐名称查询套餐信息
     * 
     * @param goodsPkgName  套餐名称
     * @return              套餐交互信息列表
     */
    private List<MtGoodsPkgInfoMessage> searchByGoodsPkgName(String goodsPkgName) {

        if (logger.isInfoEnabled()) {
            logger.info("开始通过套餐名称查询套餐信息列表，goodsPkgName=" + goodsPkgName);
        }
        return null;
    }

    /**
     * 查询所有套餐信息
     * @return
     */
    private List<MtGoodsPkgInfoMessage> emptySearch() {
        MtOperateResult<List<MtGoodsPkgModel>> result = null;

        try {
            result = mtGoodsPkgInfoComponents.queryGoodsPkgList();
        } catch (MtBizException e) {
            logger.warn("查询套餐信息出现业务异常", e);
        } catch (Throwable e) {
            logger.warn("查询套餐信息出现系统异常", e);
        }

        if (result == null || result.getOperateResult() != MtOperateResultEnum.CAMP_OPERATE_SUCCESS
            || result.getOperateExResult() != MtOperateExResultEnum.CAMP_OPERATE_SUCCESS) {
            logger.warn("查询套餐信息失败result=" + result);
            return null;
        }

        if (CollectionUtils.isEmpty(result.getResult())) {
            logger.warn("查询成功！当前没有可用的套餐信息result=" + result);
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
