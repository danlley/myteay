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
import com.myteay.common.util.exception.MtException;
import com.myteay.common.util.generators.MtUidGeneratorException;
import com.myteay.common.util.generators.UIDGener;
import com.myteay.common.util.tools.RandomNumStrUtils;
import com.myteay.core.model.repository.MtUsersInfoRepository;
import com.myteay.core.model.user.MtUserBaseModel;
import com.myteay.core.model.user.MtUserModel;

/**
 * 
 * @author Administrator
 * @version $Id: UserController.java, v 0.1 2015��11��13�� ����7:08:59 Administrator Exp $
 */
@Controller
@RequestMapping("/")
public class UserController {

    /** ��־ */
    Logger                        logger = Logger.getLogger(UserController.class);

    @Autowired
    private MtUsersInfoRepository mtUsersInfoRepository;

    @RequestMapping("/index")
    public String index(HttpSession session, HttpServletResponse response) throws IOException,
                                                                           MtUidGeneratorException {

        if (logger.isInfoEnabled()) {
            logger.info("��ʼ�û�ע�ᡣ����");
        }

        MtUserModel mtUserModel = new MtUserModel();
        try {
            MtUserBaseModel mtUserBaseModel = new MtUserBaseModel();
            mtUserBaseModel.setCheckedFlag(MtUserFlagEnum.CS_UN_CHECK_ED);
            mtUserBaseModel.setNickName("�Ե�");
            mtUserBaseModel.setUserName("��С��");
            mtUserBaseModel.setUserId(UIDGener.genUserId("����", RandomNumStrUtils.getNum()));
            mtUserModel.setMtUserBaseModel(mtUserBaseModel);
            if (logger.isInfoEnabled()) {
                logger.info("��ע���Ա��Ϣ mtUserModel=" + mtUserModel);
            }
            mtUsersInfoRepository.saveUserInfoModel(mtUserModel);
        } catch (MtException e) {
            logger.warn("�û�ע��ʧ�ܣ� mtUserModel=" + mtUserModel, e);
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
