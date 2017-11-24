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
import com.myteay.common.util.enums.MtEventTopicEnum;
import com.myteay.common.util.exception.MtException;
import com.myteay.core.model.dinner.MtGoodsPkgModel;
import com.myteay.core.model.user.convt.MtGoodsPkgModelConvertor;
import com.myteay.core.service.components.MtGoodsPkgInfoComponents;

/**
 * 查询所有套餐信息
 * 
 * @author Administrator
 * @version $Id: QueryPkgGoodsListController.java, v 0.1 2016年3月5日 上午9:10:39 Administrator Exp $
 */
@Controller
@RequestMapping("/adm/query_pkg_goods_list")
public class AdmQueryPkgGoodsListController {

    /** 日志 */
    public static final Logger          logger = Logger
        .getLogger(AdmQueryPkgGoodsListController.class);

    /** 套餐信息管理组件 */
    @Autowired
    private MtGoodsPkgInfoComponents    mtGoodsPkgInfoComponents;

    /** 套餐信息管理组件 */
    @Autowired
    private EventPublishService<String> eventPublishService;

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
                                       HttpServletRequest request) throws IOException, MtEventException {
        MtOperateResult<List<MtGoodsPkgModel>> result = null;
        ModelAndView view = new ModelAndView("query_pkg_goods_list");
        try {
            result = mtGoodsPkgInfoComponents.queryGoodsPkgList();
        } catch (MtBizException e) {
            logger.warn("查询套餐信息出现业务异常", e);
        } catch (Throwable e) {
            logger.warn("查询套餐信息出现系统异常", e);
        }

        //TODO 测试异步事件处理组件
        eventPublishService.publishEvent(new MtEvent<String>(
            MtEventTopicEnum.MT_CACHE_GOODS_PKG_FRESH.getValue(), null));

        if (result == null || result.getOperateResult() != MtOperateResultEnum.CAMP_OPERATE_SUCCESS
            || result.getOperateExResult() != MtOperateExResultEnum.CAMP_OPERATE_SUCCESS) {
            logger.warn("查询套餐信息失败result=" + result);
            return view;
        }

        if (CollectionUtils.isEmpty(result.getResult())) {
            logger.warn("查询成功！当前没有可用的套餐信息result=" + result);
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
                logger.info("套餐列表组装结束");
            }

            Map<String, Object> model = new HashMap<String, Object>();
            model.put("goods_pkg_list", messageList);
            view.addAllObjects(model);
        }
        return view;
    }

}
