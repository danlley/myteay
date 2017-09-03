/**
 * Myteay.com Inc.
 * Copyright (c) 2005-2017 All Rights Reserved.
 */
package com.myteay.core.model.repository;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallback;
import org.springframework.transaction.support.TransactionTemplate;

import com.myteay.common.dal.daointerface.UserContactListInfoDAO;
import com.myteay.common.dal.dataobject.UserContactListInfoDO;
import com.myteay.common.service.facade.enums.MtOperateExResultEnum;
import com.myteay.common.service.facade.enums.MtOperateResultEnum;
import com.myteay.common.service.facade.model.MtOperateResult;
import com.myteay.common.util.comm.CollectionUtils;
import com.myteay.common.util.comm.StringUtils;
import com.myteay.core.model.user.MtUserContactModel;
import com.myteay.core.model.user.MtUserSingleContactModel;

/**
 * ��ϵ�˲ִ���
 * 
 * @author danlley
 * @version $Id: MtUserContactListInfoRepositoryImpl.java, v 0.1 Sep 2, 2017 5:38:22 PM danlley Exp $
 */
public class MtUserContactListInfoRepositoryImpl implements MtUserContactListInfoRepository {

    /** ��־ */
    public static final Logger     logger = Logger.getLogger(MtUserContactListInfoRepositoryImpl.class);

    /** ��ϵ�˲���DAO */
    private UserContactListInfoDAO userContactListInfoDAO;

    /** share������ģ�� */
    private TransactionTemplate    myteayCustomerTransactionTemplate;

    /** 
     * @see com.myteay.core.model.repository.MtUserContactListInfoRepository#saveUserContactListInfo(com.myteay.core.model.user.MtUserContactModel)
     */
    @Override
    public MtOperateResult<String> saveUserContactListInfo(final MtUserContactModel mtUserContactModel) {

        MtOperateResult<String> innerResult = myteayCustomerTransactionTemplate
            .execute(new TransactionCallback<MtOperateResult<String>>() {
                /** 
                 * @see org.springframework.transaction.support.TransactionCallback#doInTransaction(org.springframework.transaction.TransactionStatus)
                 */
                public MtOperateResult<String> doInTransaction(TransactionStatus status) {
                    MtOperateResult<String> result = new MtOperateResult<String>();
                    try {
                        return saveContactListInTransaction(mtUserContactModel);
                    } catch (Throwable e) {
                        logger.warn("�����û���Ϣ����ϵͳ�쳣 mtUserContactModel=" + mtUserContactModel, e);
                        status.setRollbackOnly();
                        result.setOperateExResult(MtOperateExResultEnum.CAMP_ILLEGAL_ARGUMENTS);
                        result.setOperateResult(MtOperateResultEnum.CAMP_OPERATE_FAILED);
                        status.setRollbackOnly();
                        return result;
                    }
                }

            });

        MtOperateResult<String> result = new MtOperateResult<String>();
        if (innerResult == null || MtOperateResultEnum.CAMP_OPERATE_SUCCESS != innerResult.getOperateResult()) {
            logger.warn("����������ϵ���б���Ϣʧ�� innerResult=" + innerResult);
            result.setOperateExResult(MtOperateExResultEnum.CAMP_QRCODE_EXE_FAILED);
            result.setOperateResult(MtOperateResultEnum.CAMP_OPERATE_FAILED);
            return result;
        }
        result.setOperateExResult(MtOperateExResultEnum.CAMP_OPERATE_SUCCESS);
        result.setOperateResult(MtOperateResultEnum.CAMP_OPERATE_SUCCESS);
        result.setResult(innerResult.getResult());

        return result;
    }

    /**
     * ��ʼ����������ϵ����Ϣ
     * 
     * @param mtUserContactModel
     * @return
     */
    private MtOperateResult<String> saveContactListInTransaction(
                                                                 MtUserContactModel mtUserContactModel) {

        MtOperateResult<String> result = new MtOperateResult<String>();

        if (mtUserContactModel == null || CollectionUtils.isEmpty(mtUserContactModel.getSingleContactList())) {
            logger.warn("��ϵ��ģ�Ͳ����ã��޷�������ϵ����Ϣ mtUserContactModel=" + mtUserContactModel);
            result.setOperateExResult(MtOperateExResultEnum.CAMP_QRCODE_EXE_FAILED);
            result.setOperateResult(MtOperateResultEnum.CAMP_OPERATE_FAILED);
            return result;
        }

        List<MtUserSingleContactModel> list = mtUserContactModel.getSingleContactList();
        for (MtUserSingleContactModel mtUserSingleContactModel : list) {
            if (mtUserSingleContactModel == null) {
                continue;
            }

            mtUserSingleContactModel.setUserid(mtUserContactModel.getUserid());
            mtUserSingleContactModel.setParentMobile(mtUserContactModel.getParentMobile());
            saveSingleUserContactListInfo(mtUserSingleContactModel);
        }

        return result;
    }

    /**
     * ���浥����ϵ����Ϣ(������󻯣�����Ե�����ϵ���޷���������ⲻ������ع�����ȷ������޶ȵı�����ϵ����Ϣ)
     * 
     * @param mtUserSingleContactModel
     */
    private void saveSingleUserContactListInfo(MtUserSingleContactModel mtUserSingleContactModel) {

        if (mtUserSingleContactModel == null) {
            logger.warn("������ϵ����Ϣ������ mtUserSingleContactModel is null");
            return;
        }

        UserContactListInfoDO userContactListInfoDO = convertModel2DO(mtUserSingleContactModel);
        if (userContactListInfoDO == null) {
            logger.warn("ͨ��������ϵ��ģ�ͣ��޷��õ���Ӧ������ģ�ͣ���ǰ���ݱ���ʧ�� mtUserSingleContactModel=" + mtUserSingleContactModel);
            return;
        }

        if (!validateContactListDO(userContactListInfoDO)) {
            logger.warn("��ǰ��Ҫ���������ģ�Ͳ��Ϸ����޷��������б��涯��userContactListInfoDO=" + userContactListInfoDO);
            return;
        }

        UserContactListInfoDO queriedUserContactListDO = userContactListInfoDAO.queryUserContactListInfoDO(
            userContactListInfoDO);
        if (logger.isInfoEnabled()) {
            logger.info("��ʼ����ϵ��д��ǰ�ļ�鶯����ȷ�����ݿ��в����ڵ�ǰ��Ҫ���������userContactListInfoDO=" + userContactListInfoDO
                        + " queriedUserContactListDO=" + queriedUserContactListDO);
        }

        if (queriedUserContactListDO != null) {
            logger.warn("��ǰ��ϵ���Ѿ�����������ж��α��� queriedUserContactListDO=" + queriedUserContactListDO);
            return;
        }

        if (logger.isInfoEnabled()) {
            logger.info("��ʼ������ϵ����Ϣ����ģ��userContactListInfoDO=" + userContactListInfoDO);
        }
        userContactListInfoDAO.insert(userContactListInfoDO);
    }

    /**
     * ��֤����ģ�͵ĺϷ���
     * 
     * @param userContactListInfoDO
     * @return
     */
    private boolean validateContactListDO(UserContactListInfoDO userContactListInfoDO) {

        if (userContactListInfoDO == null || StringUtils.isBlank(userContactListInfoDO.getContactMobile())
            || StringUtils
                .isBlank(userContactListInfoDO.getContactName()) || StringUtils.isBlank(userContactListInfoDO
                    .getParentMobile()) || StringUtils.isBlank(userContactListInfoDO.getUserId())) {
            return false;
        }

        return true;
    }

    /**
     * ������ģ��ת��Ϊ����ģ��
     * 
     * @param mtUserSingleContactModel
     * @return
     */
    private UserContactListInfoDO convertModel2DO(MtUserSingleContactModel mtUserSingleContactModel) {

        if (mtUserSingleContactModel == null) {
            logger.warn("��ϵ��ģ�Ͳ����ã��޷�������ϵ����Ϣ mtUserContactModel is null");
            return null;
        }

        UserContactListInfoDO userContactListInfoDO = new UserContactListInfoDO();
        String mobile = mtUserSingleContactModel.getContactMobile();
        if (StringUtils.isNotBlank(mobile)) {
            mobile = mobile.trim().replaceAll(" ", "");
            userContactListInfoDO.setContactMobile(mobile);
        }

        userContactListInfoDO.setContactName(mtUserSingleContactModel.getContactName());
        userContactListInfoDO.setGmtCreated(mtUserSingleContactModel.getGmtCreated());
        userContactListInfoDO.setGmtModified(mtUserSingleContactModel.getGmtModified());
        userContactListInfoDO.setId(mtUserSingleContactModel.getId());
        userContactListInfoDO.setParentMobile(mtUserSingleContactModel.getParentMobile());
        userContactListInfoDO.setUserId(mtUserSingleContactModel.getUserid());

        return userContactListInfoDO;
    }

    /**
     * Setter method for property <tt>userContactListInfoDAO</tt>.
     * 
     * @param userContactListInfoDAO value to be assigned to property userContactListInfoDAO
     */
    public void setUserContactListInfoDAO(UserContactListInfoDAO userContactListInfoDAO) {
        this.userContactListInfoDAO = userContactListInfoDAO;
    }

    /**
     * Setter method for property <tt>myteayCustomerTransactionTemplate</tt>.
     * 
     * @param myteayCustomerTransactionTemplate value to be assigned to property myteayCustomerTransactionTemplate
     */
    public void setMyteayCustomerTransactionTemplate(TransactionTemplate myteayCustomerTransactionTemplate) {
        this.myteayCustomerTransactionTemplate = myteayCustomerTransactionTemplate;
    }
}
