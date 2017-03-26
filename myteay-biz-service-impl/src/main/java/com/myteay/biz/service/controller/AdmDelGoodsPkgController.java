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

import com.myteay.common.utils.exception.MtException;
import com.myteay.core.service.components.MtGoodsPkgInfoComponents;

/**
 * 删除套餐信息
 * 
 * @author Administrator
 * @version $Id: AdmDelGoodsPkgController.java, v 0.1 2016年3月6日 下午12:36:03 Administrator Exp $
 */
@Controller
@RequestMapping("/adm/del_goods_pkg_by_id")
public class AdmDelGoodsPkgController {

    /** 日志 */
    public static final Logger       logger = Logger.getLogger(AdmDelGoodsPkgController.class);

    /** 套餐信息管理组件 */
    @Autowired
    private MtGoodsPkgInfoComponents mtGoodsPkgInfoComponents;

    @RequestMapping(method = RequestMethod.GET)
    public String queryGoodsList(HttpSession session, HttpServletResponse response,
                                 HttpServletRequest request, String id) throws IOException,
                                                                       MtException {

        if (logger.isInfoEnabled()) {
            logger.warn("开始删除套餐信息 id=" + id);
        }
        mtGoodsPkgInfoComponents.deleteGoodsPkgById(id);
        response.sendRedirect("/myteay-web/adm/query_pkg_goods_list");
        return null;
    }
}
