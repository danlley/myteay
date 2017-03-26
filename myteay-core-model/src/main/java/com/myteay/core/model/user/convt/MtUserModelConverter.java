/**
 * Myteay.com Inc.
 * Copyright (c) 2015-2015 All Rights Reserved.
 */
package com.myteay.core.model.user.convt;

import org.apache.log4j.Logger;

import com.myteay.common.dal.dataobject.UsersInfoDO;
import com.myteay.common.dal.dataobject.UsersSecurityInfoDO;
import com.myteay.common.service.facade.enums.MtUserFlagEnum;
import com.myteay.common.service.facade.model.MtAuthenticFlagEnum;
import com.myteay.common.service.facade.model.MtUserAdvBaseInfo;
import com.myteay.common.service.facade.model.MtUserBaseInfo;
import com.myteay.common.service.facade.model.MtUserMessage;
import com.myteay.common.util.comm.MyTeayException;
import com.myteay.common.util.comm.RandomNumStrUtils;
import com.myteay.common.util.comm.StringUtils;
import com.myteay.common.utils.UIDGener;
import com.myteay.core.model.user.MtUserAdvBaseModel;
import com.myteay.core.model.user.MtUserBaseModel;
import com.myteay.core.model.user.MtUserModel;

/**
 * 数据模型与领域模型之间的转换器
 * 
 * @author Administrator
 * @version $Id: MtUserModelConverter.java, v 0.1 2015年11月15日 下午6:02:12 Administrator Exp $
 */
public class MtUserModelConverter {

    /** 日志 */
    private static final Logger logger       = Logger.getLogger(MtUserModelConverter.class);

    /** 默认注册地 */
    public static final String  DEF_REG_FROM = "甘谷县";

    /**
     * 将用户领域模型转换为用户安全数据模型
     * 
     * @param model 领域模型
     * @return      用户安全数据模型
     */
    public static UsersSecurityInfoDO convertModel2UsersSecurityInfoDO(MtUserModel model) {
        if (model == null || model.getMtUserAdvBaseModel() == null) {
            return null;
        }

        UsersSecurityInfoDO usersSecurityInfoDO = new UsersSecurityInfoDO();
        MtUserAdvBaseModel advModel = model.getMtUserAdvBaseModel();

        if (advModel.getEmailFlag() != null) {
            usersSecurityInfoDO.setEmailFlag(advModel.getEmailFlag().getValue());
        }

        if (advModel.getPhoneFlag() != null) {
            usersSecurityInfoDO.setPhoneFlag(advModel.getPhoneFlag().getValue());
        }

        usersSecurityInfoDO.setGmtCreated(advModel.getGmtCreated());
        usersSecurityInfoDO.setGmtModified(advModel.getGmtModified());
        usersSecurityInfoDO.setSecurityEmail(advModel.getSecurityEmail());
        usersSecurityInfoDO.setSecurityPhone(advModel.getSecurityPhone());
        usersSecurityInfoDO.setUserId(advModel.getUserId());
        usersSecurityInfoDO.setUserPwd(advModel.getUserPwd());
        usersSecurityInfoDO.setRegFrom(advModel.getRegFrom());

        return usersSecurityInfoDO;
    }

    /**
     * 通过用户注册交互信息转化为用户注册模型
     * 
     * @param message   用户注册交互信息
     * @return          用户注册模型
     */
    public static MtUserModel genUserRegModelFromMessage(MtUserMessage message) {
        if (message == null || message.getUserAdvInfo() == null
            || message.getUserBaseInfo() == null) {
            logger.warn("MtUserMessage is null, user registery flow is failed!");
            return null;
        }

        String userPwd = message.getUserAdvInfo().getUserPwd();
        String userNick = message.getUserBaseInfo().getNickName();
        if (StringUtils.isBlank(userPwd) || StringUtils.isBlank(userNick)) {
            logger.warn("用户注册信息中用户名和密码为必须信息，当前注册信息无效！ message=" + message);
            return null;
        }

        String userId = null;
        try {
            userId = UIDGener.genUserId(DEF_REG_FROM, RandomNumStrUtils.getNum());
        } catch (MyTeayException e) {
            logger.warn("注册失败，无法生成userid。message=" + message, e);
            return null;
        }

        if (StringUtils.isBlank(userId)) {
            logger.warn("注册过程生成userid不可用，userid is null!");
            return null;
        }

        MtUserModel model = new MtUserModel();

        //user advanced info
        model.getMtUserAdvBaseModel().setEmailFlag(MtAuthenticFlagEnum.CS_UNSUBMIT_FLAG);
        model.getMtUserAdvBaseModel().setPhoneFlag(MtAuthenticFlagEnum.CS_UNSUBMIT_FLAG);
        if (StringUtils.isBlank(model.getMtUserAdvBaseModel().getRegFrom())) {
            model.getMtUserAdvBaseModel().setRegFrom(DEF_REG_FROM);
        }
        model.getMtUserAdvBaseModel().setUserId(userId);
        model.getMtUserAdvBaseModel().setUserPwd(userPwd);

        //user base info
        model.getMtUserBaseModel().setCheckedFlag(MtUserFlagEnum.CS_UN_CHECK_ED);
        model.getMtUserBaseModel().setNickName(userNick);
        model.getMtUserBaseModel().setUserId(userId);

        //user extends info

        return model;
    }

    /**
     * 将指定的用户模型转换为用户交互信息
     * 
     * @param model 用户模型
     * @return      用户交互信息
     */
    public static MtUserMessage convertModel2Msg(MtUserModel model) {

        if (model == null) {
            logger.warn("user model is null!");
            return null;
        }

        MtUserAdvBaseModel userAdvModel = model.getMtUserAdvBaseModel();
        MtUserBaseModel userBaseModel = model.getMtUserBaseModel();
        //MtUserExtModel userExtModel = model.getMtUserExtModel();

        if (userBaseModel == null) {
            logger.warn("用户模型转换用户交互信息时，关键信息不可用。model=" + model);
            return null;
        }

        MtUserMessage message = new MtUserMessage();

        MtUserBaseInfo baseInfo = message.getUserBaseInfo();
        //user base info
        baseInfo.setCheckedFlag(userBaseModel.getCheckedFlag());
        baseInfo.setGmtCreate(userBaseModel.getGmtCreate());
        baseInfo.setGmtModified(userBaseModel.getGmtModified());
        baseInfo.setId(userBaseModel.getId());
        baseInfo.setNickName(userBaseModel.getNickName());
        baseInfo.setQrCode(userBaseModel.getQrCode());
        baseInfo.setUserId(userBaseModel.getUserId());
        baseInfo.setUserName(userBaseModel.getUserName());

        MtUserAdvBaseInfo advInfo = message.getUserAdvInfo();
        //user advanced info

        //TODO
        if (userAdvModel != null) {
            advInfo.setEmailFlag(userAdvModel.getEmailFlag());
            advInfo.setGmtCreated(userAdvModel.getGmtCreated());
            advInfo.setGmtModified(userAdvModel.getGmtModified());
            advInfo.setPhoneFlag(userAdvModel.getPhoneFlag());
            advInfo.setSecurityEmail(userAdvModel.getSecurityEmail());
            advInfo.setSecurityPhone(userAdvModel.getSecurityPhone());
            advInfo.setUserId(userAdvModel.getUserId());
            advInfo.setUserPwd(userAdvModel.getUserPwd());
            advInfo.setRegFrom(userAdvModel.getRegFrom());
        }

        //MtUserExtInfo extInfo = message.getUserExtInfo();
        //user extends info

        return message;
    }

    /**
     * 数据模型转换为用户领域模型
     * 
     * @param usersInfoDO           用户基础信息数据模型
     * @param usersSecurityInfoDO   用户安全信息数据模型
     * @return
     */
    public static MtUserModel convertDO2Model(UsersInfoDO usersInfoDO,
                                              UsersSecurityInfoDO usersSecurityInfoDO) {
        if (usersInfoDO == null) {
            return null;
        }
        MtUserModel model = new MtUserModel();
        MtUserBaseModel mtUserBaseModel = new MtUserBaseModel();
        MtUserFlagEnum flag = null;
        if (StringUtils.isNotBlank(usersInfoDO.getCheckedFlag())) {
            flag = MtUserFlagEnum.getByCode(usersInfoDO.getCheckedFlag());
        }
        mtUserBaseModel.setCheckedFlag(flag);
        mtUserBaseModel.setGmtCreate(usersInfoDO.getGmtCreated());
        mtUserBaseModel.setGmtModified(usersInfoDO.getGmtModified());
        mtUserBaseModel.setId(usersInfoDO.getId());
        mtUserBaseModel.setNickName(usersInfoDO.getNickName());
        mtUserBaseModel.setUserId(usersInfoDO.getUserId());
        mtUserBaseModel.setUserName(usersInfoDO.getUserName());
        mtUserBaseModel.setQrCode(usersInfoDO.getQrCode());
        model.setMtUserBaseModel(mtUserBaseModel);
        return model;

    }

    /**
     * 从用户领域模型中转换出用户基本信息数据模型
     * 
     * @param mtUserModel   用户模型
     * @return              用户基本信息数据模型
     */
    public static UsersInfoDO convertModel2BaseDO(MtUserModel mtUserModel) {
        if (mtUserModel == null || mtUserModel.getMtUserBaseModel() == null) {
            logger.warn("会员模型不可用，无法转换出用户基本信息数据模型  mtUserModel=" + mtUserModel);
            return null;
        }

        UsersInfoDO usersInfoDO = new UsersInfoDO();
        if (mtUserModel.getMtUserBaseModel().getCheckedFlag() != null) {
            usersInfoDO
                .setCheckedFlag(mtUserModel.getMtUserBaseModel().getCheckedFlag().getValue());
        }
        usersInfoDO.setGmtCreated(mtUserModel.getMtUserBaseModel().getGmtCreate());
        usersInfoDO.setGmtModified(mtUserModel.getMtUserBaseModel().getGmtModified());
        usersInfoDO.setId(mtUserModel.getMtUserBaseModel().getId());
        usersInfoDO.setNickName(mtUserModel.getMtUserBaseModel().getNickName());
        usersInfoDO.setQrCode(mtUserModel.getMtUserBaseModel().getQrCode());
        usersInfoDO.setUserId(mtUserModel.getMtUserBaseModel().getUserId());
        usersInfoDO.setUserName(mtUserModel.getMtUserBaseModel().getUserName());

        return usersInfoDO;
    }

    /**
     * 从用户领域模型中转换出用户私密信息数据模型
     * 
     * @param mtUserModel   用户模型
     * @return              用户私密信息数据模型
     */
    public static UsersSecurityInfoDO convertModel2SecurityDO(MtUserModel mtUserModel) {
        if (mtUserModel == null || mtUserModel.getMtUserAdvBaseModel() == null) {
            logger.warn("会员模型不可用，无法完成会员注册 mtUserModel=" + mtUserModel);
            return null;
        }

        MtUserAdvBaseModel model = mtUserModel.getMtUserAdvBaseModel();
        UsersSecurityInfoDO usersSecurityInfoDO = new UsersSecurityInfoDO();

        if (model.getEmailFlag() != null) {
            usersSecurityInfoDO.setEmailFlag(model.getEmailFlag().getValue());
        }
        usersSecurityInfoDO.setGmtCreated(model.getGmtCreated());
        usersSecurityInfoDO.setGmtModified(model.getGmtModified());
        if (model.getPhoneFlag() != null) {
            usersSecurityInfoDO.setPhoneFlag(model.getPhoneFlag().getValue());
        }
        usersSecurityInfoDO.setSecurityEmail(model.getSecurityEmail());
        usersSecurityInfoDO.setSecurityPhone(model.getSecurityPhone());
        usersSecurityInfoDO.setUserId(model.getUserId());
        usersSecurityInfoDO.setUserPwd(model.getUserPwd());
        usersSecurityInfoDO.setRegFrom(model.getRegFrom());

        return usersSecurityInfoDO;
    }
}
