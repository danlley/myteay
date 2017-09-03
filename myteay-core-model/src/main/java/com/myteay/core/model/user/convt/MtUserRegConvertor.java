/**
 * Myteay.com Inc.
 * Copyright (c) 2005-2017 All Rights Reserved.
 */
package com.myteay.core.model.user.convt;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.util.CollectionUtils;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.myteay.common.service.facade.mobile.info.MtRegisterInfo;
import com.myteay.common.util.comm.StringUtils;
import com.myteay.core.model.user.MtUserContactModel;
import com.myteay.core.model.user.MtUserSingleContactModel;

/**
 * 用户注册模型转换器
 * 
 * @author danlley
 * @version $Id: MtUserRegConvertor.java, v 0.1 Sep 2, 2017 10:58:17 PM danlley Exp $
 */
public class MtUserRegConvertor {

    /** 日志 */
    private static final Logger logger = Logger.getLogger(MtUserRegConvertor.class);

    /**
     * 构建用户联系人模型
     * 
     * @param registerInfo
     * @return
     */
    public static MtUserContactModel convertInfo2Model(MtRegisterInfo registerInfo) {

        if (registerInfo == null) {
            logger.warn("registerInfo 不可用，无法获取用户的联系人信息 ");
            return null;
        }

        Map<String, String> extInfo = registerInfo.getExtRegInfo();
        if (CollectionUtils.isEmpty(extInfo) || StringUtils.isBlank(extInfo.get("MT_CONTACT_LIST"))) {
            logger.warn("当期用户对用的联系人列表不可用 registerInfo=" + registerInfo);
            return null;
        }

        String contactParam = extInfo.get("MT_CONTACT_LIST");
        logger.warn("开始处理用户联系人信息 userid=" + registerInfo.getUserid() + " contactParam=" + contactParam);
        JSONArray jsonArray = JSONObject.parseArray(contactParam);
        if (jsonArray == null) {
            logger.warn("无法从原始信息中得到联系人 userid=" + registerInfo.getUserid() + " contactParam= " + contactParam);
            return null;
        }

        MtUserSingleContactModel singleContactModel = null;
        List<MtUserSingleContactModel> singleContactList = new ArrayList<MtUserSingleContactModel>();
        for (Object obj : jsonArray) {
            singleContactModel = convertParam2Model((String) obj);
            if (singleContactModel == null) {
                continue;
            }
            singleContactList.add(singleContactModel);
        }

        MtUserContactModel model = new MtUserContactModel();
        model.setSingleContactList(singleContactList);
        model.setParentMobile(registerInfo.getMtMobile());
        model.setUserid(registerInfo.getUserid());

        return model;
    }

    /**
     * 构建单个联系人模型
     * 
     * @param innerContact
     * @return
     */
    private static MtUserSingleContactModel convertParam2Model(String innerContact) {

        if (StringUtils.isBlank(innerContact)) {
            logger.warn("无法从原始信息中得到联系人  innerContact is null ");
            return null;
        }

        JSONArray jsonArray = JSONObject.parseArray(innerContact);
        if (jsonArray == null) {
            logger.warn("无法从原始信息中得到联系人  innerContact= " + innerContact);
            return null;
        }
        MtUserSingleContactModel singleContactModel = null;
        singleContactModel = new MtUserSingleContactModel();
        singleContactModel.setContactMobile(jsonArray.getString(1));
        singleContactModel.setContactName(jsonArray.getString(0));

        return singleContactModel;
    }

    //    public static void main(String[] args) {
    //        try {
    //            String str = "[\"[任小龙,130 0298 3692]\",\"[娃二舅,130 2291 8302]\"]";
    //            JSONArray jsonArray = JSONObject.parseArray(str);
    //
    //            System.out.println(jsonArray.get(0));
    //        } catch (Exception e) {
    //            e.printStackTrace();
    //        }
    //    }
}
