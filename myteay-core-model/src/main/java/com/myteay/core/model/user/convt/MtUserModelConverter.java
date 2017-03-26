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
 * ����ģ��������ģ��֮���ת����
 * 
 * @author Administrator
 * @version $Id: MtUserModelConverter.java, v 0.1 2015��11��15�� ����6:02:12 Administrator Exp $
 */
public class MtUserModelConverter {

    /** ��־ */
    private static final Logger logger       = Logger.getLogger(MtUserModelConverter.class);

    /** Ĭ��ע��� */
    public static final String  DEF_REG_FROM = "�ʹ���";

    /**
     * ���û�����ģ��ת��Ϊ�û���ȫ����ģ��
     * 
     * @param model ����ģ��
     * @return      �û���ȫ����ģ��
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
     * ͨ���û�ע�ύ����Ϣת��Ϊ�û�ע��ģ��
     * 
     * @param message   �û�ע�ύ����Ϣ
     * @return          �û�ע��ģ��
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
            logger.warn("�û�ע����Ϣ���û���������Ϊ������Ϣ����ǰע����Ϣ��Ч�� message=" + message);
            return null;
        }

        String userId = null;
        try {
            userId = UIDGener.genUserId(DEF_REG_FROM, RandomNumStrUtils.getNum());
        } catch (MyTeayException e) {
            logger.warn("ע��ʧ�ܣ��޷�����userid��message=" + message, e);
            return null;
        }

        if (StringUtils.isBlank(userId)) {
            logger.warn("ע���������userid�����ã�userid is null!");
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
     * ��ָ�����û�ģ��ת��Ϊ�û�������Ϣ
     * 
     * @param model �û�ģ��
     * @return      �û�������Ϣ
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
            logger.warn("�û�ģ��ת���û�������Ϣʱ���ؼ���Ϣ�����á�model=" + model);
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
     * ����ģ��ת��Ϊ�û�����ģ��
     * 
     * @param usersInfoDO           �û�������Ϣ����ģ��
     * @param usersSecurityInfoDO   �û���ȫ��Ϣ����ģ��
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
     * ���û�����ģ����ת�����û�������Ϣ����ģ��
     * 
     * @param mtUserModel   �û�ģ��
     * @return              �û�������Ϣ����ģ��
     */
    public static UsersInfoDO convertModel2BaseDO(MtUserModel mtUserModel) {
        if (mtUserModel == null || mtUserModel.getMtUserBaseModel() == null) {
            logger.warn("��Աģ�Ͳ����ã��޷�ת�����û�������Ϣ����ģ��  mtUserModel=" + mtUserModel);
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
     * ���û�����ģ����ת�����û�˽����Ϣ����ģ��
     * 
     * @param mtUserModel   �û�ģ��
     * @return              �û�˽����Ϣ����ģ��
     */
    public static UsersSecurityInfoDO convertModel2SecurityDO(MtUserModel mtUserModel) {
        if (mtUserModel == null || mtUserModel.getMtUserAdvBaseModel() == null) {
            logger.warn("��Աģ�Ͳ����ã��޷���ɻ�Աע�� mtUserModel=" + mtUserModel);
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
