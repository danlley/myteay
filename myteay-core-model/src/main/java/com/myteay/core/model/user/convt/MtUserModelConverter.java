/**
 * Myteay.com Inc.
 * Copyright (c) 2015-2015 All Rights Reserved.
 */
package com.myteay.core.model.user.convt;

import org.apache.log4j.Logger;

import com.myteay.common.dal.dataobject.UsersInfoDO;
import com.myteay.common.dal.dataobject.UsersSecurityInfoDO;
import com.myteay.common.service.facade.enums.MtUserFlagEnum;
import com.myteay.common.service.facade.mobile.info.MtRegisterInfo;
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
    public static MtUserModel genUserRegModelFromMessage(MtRegisterInfo registerInfo) {
        if (registerInfo == null) {
            logger.warn("MtRegisterInfo is null, user registery flow is failed!");
            return null;
        }

        String userPwd = registerInfo.getMtPassword();
        if (StringUtils.isBlank(userPwd) || StringUtils.isBlank(registerInfo.getMtMobile())) {
            logger.warn("�û�ע����Ϣ���û���������Ϊ������Ϣ����ǰע����Ϣ��Ч�� registerInfo=" + registerInfo);
            return null;
        }

        String userId = null;
        try {
            userId = UIDGener.genUserId(DEF_REG_FROM, RandomNumStrUtils.getNum());
        } catch (MyTeayException e) {
            logger.warn("ע��ʧ�ܣ��޷�����userid��registerInfo=" + registerInfo, e);
            return null;
        }

        if (StringUtils.isBlank(userId)) {
            logger.warn("ע���������userid�����ã�userid is null!");
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

        usersInfoDO.setMtIdCard(mtUserModel.getMtUserBaseModel().getMtIdCard());
        usersInfoDO.setMtMobile(mtUserModel.getMtUserBaseModel().getMtMobile());
        usersInfoDO.setMtPassword(mtUserModel.getMtUserBaseModel().getMtPassword());

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
