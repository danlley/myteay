/**
 * Myteay.com Inc.
 * Copyright (c) 2015-2015 All Rights Reserved.
 */
package com.myteay.core.model.repository;

import org.apache.log4j.Logger;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallback;
import org.springframework.transaction.support.TransactionTemplate;

import com.myteay.common.dal.daointerface.UsersInfoDAO;
import com.myteay.common.dal.daointerface.UsersSecurityInfoDAO;
import com.myteay.common.dal.dataobject.UsersInfoDO;
import com.myteay.common.dal.dataobject.UsersSecurityInfoDO;
import com.myteay.common.service.facade.enums.MtOperateExResultEnum;
import com.myteay.common.service.facade.enums.MtOperateResultEnum;
import com.myteay.common.service.facade.model.MtOperateResult;
import com.myteay.common.service.facade.model.MtUserRegQRCodeMessage;
import com.myteay.common.util.comm.StringUtils;
import com.myteay.common.utils.exception.MtBizProcessException;
import com.myteay.common.utils.exception.MtException;
import com.myteay.core.model.user.MtUserModel;
import com.myteay.core.model.user.convt.MtUserModelConverter;
import com.myteay.core.model.user.validators.MtUserDOValidator;

/**
 * �û���Ϣ�ִ���
 * 
 * @author Administrator
 * @version $Id: MtUsersInfoRepositoryImpl.java, v 0.1 2015��11��15�� ����6:25:08 Administrator Exp $
 */
public class MtUsersInfoRepositoryImpl implements MtUsersInfoRepository {

    /** ��־ */
    public static final Logger   logger = Logger.getLogger(MtUsersInfoRepositoryImpl.class);

    /** �û�������Ϣ����ģ�Ͳ���DAO */
    private UsersInfoDAO         mtUserDAO;

    /** �û�������Ϣ����ģ�Ͳ���DAO */
    private UsersSecurityInfoDAO mtUserSecurityDAO;

    /** share������ģ�� */
    private TransactionTemplate  myteayCustomerTransactionTemplate;

    /** 
     * @see com.myteay.core.model.repository.MtUsersInfoRepository#getUserInfoModelById(java.lang.String)
     */
    @Override
    public MtOperateResult<MtUserModel> getUserInfoModelById(String userId) {

        UsersInfoDO usersInfoDO = mtUserDAO.getById(userId);
        if (usersInfoDO == null) {
            logger.warn("δ�ҵ���Ӧ�û���userId=" + userId);
            return null;
        }

        MtUserModel model = MtUserModelConverter.convertDO2Model(usersInfoDO, null);
        MtOperateResult<MtUserModel> result = new MtOperateResult<MtUserModel>();
        if (model == null) {
            logger.warn("��ѯ�û���Ϣģ��ת��ʧ�� usersInfoDO=" + usersInfoDO);
            result.setOperateExResult(MtOperateExResultEnum.CAMP_ILLEGAL_ARGUMENTS);
            result.setOperateResult(MtOperateResultEnum.CAMP_OPERATE_FAILED);
            return result;
        }

        if (logger.isInfoEnabled()) {
            logger.info("��ѯ�û���Ϣ userid=" + userId + "model=" + model);
        }
        result.setOperateExResult(MtOperateExResultEnum.CAMP_OPERATE_SUCCESS);
        result.setOperateResult(MtOperateResultEnum.CAMP_OPERATE_SUCCESS);
        result.setResult(model);

        return result;
    }

    /** 
     * @see com.myteay.core.model.repository.MtUsersInfoRepository#saveUserInfoModel(com.myteay.core.model.user.MtUserModel)
     */
    @Override
    public MtOperateResult<String> saveUserInfoModel(final MtUserModel mtUserModel)
                                                                                    throws MtException {

        final MtOperateResult<String> result = new MtOperateResult<String>();
        return myteayCustomerTransactionTemplate
            .execute(new TransactionCallback<MtOperateResult<String>>() {
                /** 
                 * @see org.springframework.transaction.support.TransactionCallback#doInTransaction(org.springframework.transaction.TransactionStatus)
                 */
                public MtOperateResult<String> doInTransaction(TransactionStatus status) {
                    try {
                        //step 1: �����û�������Ϣ
                        saveUserBaseInfo(mtUserModel);

                        //step 2: �����û���ȫ��Ϣ
                        saveUserSecurityInfo(mtUserModel);
                    } catch (MtException e) {
                        logger.warn("�����û���Ϣ����ҵ���쳣 mtUserModel=" + mtUserModel, e);
                        status.setRollbackOnly();
                        result.setOperateExResult(MtOperateExResultEnum.CAMP_ILLEGAL_ARGUMENTS);
                        result.setOperateResult(MtOperateResultEnum.CAMP_OPERATE_FAILED);
                        status.setRollbackOnly();
                        return result;
                    } catch (Throwable e) {
                        logger.warn("�����û���Ϣ����ϵͳ�쳣 mtUserModel=" + mtUserModel, e);
                        status.setRollbackOnly();
                        result.setOperateExResult(MtOperateExResultEnum.CAMP_ILLEGAL_ARGUMENTS);
                        result.setOperateResult(MtOperateResultEnum.CAMP_OPERATE_FAILED);
                        status.setRollbackOnly();
                        return result;
                    }
                    result.setOperateExResult(MtOperateExResultEnum.CAMP_OPERATE_SUCCESS);
                    result.setOperateResult(MtOperateResultEnum.CAMP_OPERATE_SUCCESS);
                    result.setResult(mtUserModel.getMtUserBaseModel().getUserId());

                    return result;
                }

            });
    }

    /**
     * �����û�˽����Ϣ
     * 
     * @param mtUserModel   �û�ģ��
     * @throws MtException  ϵͳ�쳣
     */
    private void saveUserSecurityInfo(MtUserModel mtUserModel) throws MtException {
        UsersSecurityInfoDO usersSecurityInfoDO = MtUserModelConverter
            .convertModel2SecurityDO(mtUserModel);
        if (usersSecurityInfoDO == null) {
            throw new MtException("�û�����ģ��ת��ʧ�� usersSecurityInfoDO is null,mtUserModel="
                                  + mtUserModel);
        }

        //��֤����ģ�ͺϷ���
        MtUserDOValidator.validateUsersSecurityInfoDO(usersSecurityInfoDO);

        mtUserSecurityDAO.insert(usersSecurityInfoDO);
    }

    /**
     * �����û�������Ϣ
     * 
     * @param mtUserModel   �û�ģ��
     * @throws MtException  ϵͳ�쳣
     */
    private void saveUserBaseInfo(MtUserModel mtUserModel) throws MtException {
        UsersInfoDO usersInfoDO = MtUserModelConverter.convertModel2BaseDO(mtUserModel);
        if (usersInfoDO == null) {
            throw new MtException("�û�����ģ��ת��ʧ�� usersInfoDO is null,mtUserModel=" + mtUserModel);
        }

        if (StringUtils.isBlank(usersInfoDO.getCheckedFlag())) {
            throw new MtException("�û�����ģ��ת��ʧ��,�û�״̬������,mtUserModel=" + mtUserModel);
        }

        //���ݺϷ���У��
        MtUserDOValidator.validateUsersInfoDO(usersInfoDO);

        String id = mtUserDAO.insert(usersInfoDO);

        if (logger.isInfoEnabled()) {
            logger.info("��Ա������Ϣע������id=" + id + " usersInfoDO=" + usersInfoDO);
        }

    }

    /** 
     * @see com.myteay.core.model.repository.MtUsersInfoRepository#updateUserQrcode(com.myteay.common.service.facade.model.MtUserRegQRCodeMessage)
     */
    @Override
    public MtOperateResult<MtUserModel> updateUserQrcode(MtUserRegQRCodeMessage mtUserRegQRCodeMessage)
                                                                                                        throws MtBizProcessException {

        if (mtUserRegQRCodeMessage == null
            || StringUtils.isBlank(mtUserRegQRCodeMessage.getContent())
            || StringUtils.isBlank(mtUserRegQRCodeMessage.getFilename())) {
            logger.warn("�������Ϸ���mtUserRegQRCodeMessage ����ģ�Ͳ�����");
            throw new MtBizProcessException(MtOperateExResultEnum.CAMP_ILLEGAL_ARGUMENTS.getCode(),
                MtOperateExResultEnum.CAMP_ILLEGAL_ARGUMENTS.getMessage());
        }

        UsersInfoDO usersInfoDO = mtUserDAO.getById(mtUserRegQRCodeMessage.getContent());
        if (usersInfoDO == null) {
            throw new MtBizProcessException(MtOperateExResultEnum.CAMP_USERID_NO_USER.getCode(),
                MtOperateExResultEnum.CAMP_USERID_NO_USER.getMessage());
        }

        //���ö�ά����Ϣ���û�����ģ��
        usersInfoDO.setQrCode(mtUserRegQRCodeMessage.getFilename());
        mtUserDAO.updateQrCode(usersInfoDO);

        //���·��ش��������˷�ʽĿǰ���ܿɽ��ܣ�
        return getUserInfoModelById(mtUserRegQRCodeMessage.getContent());
    }

    /**
     * Setter method for property <tt>mtUserDAO</tt>.
     * 
     * @param mtUserDAO value to be assigned to property mtUserDAO
     */
    public void setMtUserDAO(UsersInfoDAO mtUserDAO) {
        this.mtUserDAO = mtUserDAO;
    }

    /**
     * Setter method for property <tt>mtUserSecurityDAO</tt>.
     * 
     * @param mtUserSecurityDAO value to be assigned to property mtUserSecurityDAO
     */
    public void setMtUserSecurityDAO(UsersSecurityInfoDAO mtUserSecurityDAO) {
        this.mtUserSecurityDAO = mtUserSecurityDAO;
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
