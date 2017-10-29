/**
 * Myteay.com Inc.
 * Copyright (c) 2015-2015 All Rights Reserved.
 */
package com.myteay.core.model.user.convt;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

import com.myteay.common.dal.dataobject.UsersInfoDO;
import com.myteay.common.dal.dataobject.UsersSecurityInfoDO;
import com.myteay.common.service.facade.enums.MtUserFlagEnum;
import com.myteay.common.service.facade.mobile.info.MtRegisterInfo;
import com.myteay.common.util.generators.MtUidGeneratorException;
import com.myteay.common.util.generators.UIDGener;
import com.myteay.common.util.tools.RandomNumStrUtils;
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
    public static MtUserModel genUserRegModelFromMessage(MtRegisterInfo registerInfo) {
        if (registerInfo == null) {
            logger.warn("MtRegisterInfo is null, user registery flow is failed!");
            return null;
        }

        String userPwd = registerInfo.getMtPassword();
        if (StringUtils.isBlank(userPwd) || StringUtils.isBlank(registerInfo.getMtMobile())) {
            logger.warn("用户注册信息中用户名和密码为必须信息，当前注册信息无效！ registerInfo=" + registerInfo);
            return null;
        }

        String userId = null;
        try {
            userId = UIDGener.genUserId(DEF_REG_FROM, RandomNumStrUtils.getNum());
        } catch (MtUidGeneratorException e) {
            logger.warn("注册失败，无法生成userid。registerInfo=" + registerInfo, e);
            return null;
        }

        if (StringUtils.isBlank(userId)) {
            logger.warn("注册过程生成userid不可用，userid is null!");
            return null;
        }

        MtUserModel model = new MtUserModel();

        model.getMtUserAdvBaseModel().setRegFrom(DEF_REG_FROM);
        model.getMtUserAdvBaseModel().setUserId(userId);

        //user base info
        model.getMtUserBaseModel().setCheckedFlag(MtUserFlagEnum.CS_UN_CHECK_ED);
        model.getMtUserBaseModel().setNickName(registerInfo.getMtNickName());
        model.getMtUserBaseModel().setUserId(userId);
        model.getMtUserBaseModel().setMtIdCard(registerInfo.getMtIdCard());
        model.getMtUserBaseModel().setMtMobile(registerInfo.getMtMobile());
        model.getMtUserBaseModel().setMtPassword(userPwd);
        model.getMtUserBaseModel().setQrCode(userId);

        //user extends info

        return model;
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

        usersInfoDO.setMtIdCard(mtUserModel.getMtUserBaseModel().getMtIdCard());
        usersInfoDO.setMtMobile(mtUserModel.getMtUserBaseModel().getMtMobile());
        usersInfoDO.setMtPassword(mtUserModel.getMtUserBaseModel().getMtPassword());

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
