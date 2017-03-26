/**
 * Myteay.com Inc.
 * Copyright (c) 2015-2015 All Rights Reserved.
 */
package com.myteay.biz.service.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.myteay.common.service.facade.enums.MtUserFlagEnum;
import com.myteay.common.util.comm.RandomNumStrUtils;
import com.myteay.common.utils.UIDGener;
import com.myteay.common.utils.exception.MtException;
import com.myteay.core.model.repository.MtUsersInfoRepository;
import com.myteay.core.model.user.MtUserBaseModel;
import com.myteay.core.model.user.MtUserModel;

/**
 * 
 * @author Administrator
 * @version $Id: UserController.java, v 0.1 2015年11月13日 下午7:08:59 Administrator Exp $
 */
@Controller
@RequestMapping("/")
public class UserController {

    /** 日志 */
    Logger                        logger = Logger.getLogger(UserController.class);

    @Autowired
    private MtUsersInfoRepository mtUsersInfoRepository;

    @RequestMapping("/index")
    public String index(HttpSession session, HttpServletResponse response) throws IOException,
                                                                           MtException {

        if (logger.isInfoEnabled()) {
            logger.info("开始用户注册。。。");
        }

        MtUserModel mtUserModel = new MtUserModel();
        try {
            MtUserBaseModel mtUserBaseModel = new MtUserBaseModel();
            mtUserBaseModel.setCheckedFlag(MtUserFlagEnum.CS_UN_CHECK_ED);
            mtUserBaseModel.setNickName("霸刀");
            mtUserBaseModel.setUserName("王小明");
            mtUserBaseModel.setUserId(UIDGener.genUserId("甘肃", RandomNumStrUtils.getNum()));
            mtUserModel.setMtUserBaseModel(mtUserBaseModel);
            if (logger.isInfoEnabled()) {
                logger.info("待注册会员信息 mtUserModel=" + mtUserModel);
            }
            mtUsersInfoRepository.saveUserInfoModel(mtUserModel);
        } catch (MtException e) {
            logger.warn("用户注册失败， mtUserModel=" + mtUserModel, e);
        }

        return "/index";
    }

    /**
     * Setter method for property <tt>mtUsersInfoRepository</tt>.
     * 
     * @param mtUsersInfoRepository value to be assigned to property mtUsersInfoRepository
     */
    public void setMtUsersInfoRepository(MtUsersInfoRepository mtUsersInfoRepository) {
        this.mtUsersInfoRepository = mtUsersInfoRepository;
    }
}
