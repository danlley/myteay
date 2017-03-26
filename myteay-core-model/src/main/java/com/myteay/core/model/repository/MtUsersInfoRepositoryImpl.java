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
 * 用户信息仓储类
 * 
 * @author Administrator
 * @version $Id: MtUsersInfoRepositoryImpl.java, v 0.1 2015年11月15日 下午6:25:08 Administrator Exp $
 */
public class MtUsersInfoRepositoryImpl implements MtUsersInfoRepository {

    /** 日志 */
    public static final Logger   logger = Logger.getLogger(MtUsersInfoRepositoryImpl.class);

    /** 用户基本信息数据模型操作DAO */
    private UsersInfoDAO         mtUserDAO;

    /** 用户基本信息数据模型操作DAO */
    private UsersSecurityInfoDAO mtUserSecurityDAO;

    /** share库事务模板 */
    private TransactionTemplate  myteayTransactionTemplate;

    /** 
     * @see com.myteay.core.model.repository.MtUsersInfoRepository#getUserInfoModelById(java.lang.String)
     */
    @Override
    public MtOperateResult<MtUserModel> getUserInfoModelById(String userId) {

        UsersInfoDO usersInfoDO = mtUserDAO.getById(userId);
        if (usersInfoDO == null) {
            logger.warn("未找到对应用户，userId=" + userId);
            return null;
        }

        MtUserModel model = MtUserModelConverter.convertDO2Model(usersInfoDO, null);
        MtOperateResult<MtUserModel> result = new MtOperateResult<MtUserModel>();
        if (model == null) {
            logger.warn("查询用户信息模型转换失败 usersInfoDO=" + usersInfoDO);
            result.setOperateExResult(MtOperateExResultEnum.CAMP_ILLEGAL_ARGUMENTS);
            result.setOperateResult(MtOperateResultEnum.CAMP_OPERATE_FAILED);
            return result;
        }

        if (logger.isInfoEnabled()) {
            logger.info("查询用户信息 userid=" + userId + "model=" + model);
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
        return myteayTransactionTemplate
            .execute(new TransactionCallback<MtOperateResult<String>>() {
                /** 
                 * @see org.springframework.transaction.support.TransactionCallback#doInTransaction(org.springframework.transaction.TransactionStatus)
                 */
                public MtOperateResult<String> doInTransaction(TransactionStatus status) {
                    try {
                        //step 1: 保存用户基本信息
                        saveUserBaseInfo(mtUserModel);

                        //step 2: 保存用户安全信息
                        saveUserSecurityInfo(mtUserModel);
                    } catch (MtException e) {
                        logger.warn("保存用户信息发生业务异常 mtUserModel=" + mtUserModel, e);
                        status.setRollbackOnly();
                        result.setOperateExResult(MtOperateExResultEnum.CAMP_ILLEGAL_ARGUMENTS);
                        result.setOperateResult(MtOperateResultEnum.CAMP_OPERATE_FAILED);
                        return result;
                    } catch (Throwable e) {
                        logger.warn("保存用户信息发生系统异常 mtUserModel=" + mtUserModel, e);
                        status.setRollbackOnly();
                        result.setOperateExResult(MtOperateExResultEnum.CAMP_ILLEGAL_ARGUMENTS);
                        result.setOperateResult(MtOperateResultEnum.CAMP_OPERATE_FAILED);
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
     * 保存用户私密信息
     * 
     * @param mtUserModel   用户模型
     * @throws MtException  系统异常
     */
    private void saveUserSecurityInfo(MtUserModel mtUserModel) throws MtException {
        UsersSecurityInfoDO usersSecurityInfoDO = MtUserModelConverter
            .convertModel2SecurityDO(mtUserModel);
        if (usersSecurityInfoDO == null) {
            throw new MtException("用户基本模型转换失败 usersSecurityInfoDO is null,mtUserModel="
                                  + mtUserModel);
        }

        //验证数据模型合法性
        MtUserDOValidator.validateUsersSecurityInfoDO(usersSecurityInfoDO);

        mtUserSecurityDAO.insert(usersSecurityInfoDO);
    }

    /**
     * 保存用户基本信息
     * 
     * @param mtUserModel   用户模型
     * @throws MtException  系统异常
     */
    private void saveUserBaseInfo(MtUserModel mtUserModel) throws MtException {
        UsersInfoDO usersInfoDO = MtUserModelConverter.convertModel2BaseDO(mtUserModel);
        if (usersInfoDO == null) {
            throw new MtException("用户基本模型转换失败 usersInfoDO is null,mtUserModel=" + mtUserModel);
        }

        if (StringUtils.isBlank(usersInfoDO.getCheckedFlag())) {
            throw new MtException("用户基本模型转换失败,用户状态不可用,mtUserModel=" + mtUserModel);
        }

        //数据合法性校验
        MtUserDOValidator.validateUsersInfoDO(usersInfoDO);

        String id = mtUserDAO.insert(usersInfoDO);

        if (logger.isInfoEnabled()) {
            logger.info("会员基本信息注册结果：id=" + id + " usersInfoDO=" + usersInfoDO);
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
            logger.warn("参数不合法，mtUserRegQRCodeMessage 交互模型不可用");
            throw new MtBizProcessException(MtOperateExResultEnum.CAMP_ILLEGAL_ARGUMENTS.getCode(),
                MtOperateExResultEnum.CAMP_ILLEGAL_ARGUMENTS.getMessage());
        }

        UsersInfoDO usersInfoDO = mtUserDAO.getById(mtUserRegQRCodeMessage.getContent());
        if (usersInfoDO == null) {
            throw new MtBizProcessException(MtOperateExResultEnum.CAMP_USERID_NO_USER.getCode(),
                MtOperateExResultEnum.CAMP_USERID_NO_USER.getMessage());
        }

        //设置二维码信息到用户数据模型
        usersInfoDO.setQrCode(mtUserRegQRCodeMessage.getFilename());
        mtUserDAO.updateQrCode(usersInfoDO);

        //重新返回处理结果（此方式目前性能可接受）
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
     * Setter method for property <tt>myteayTransactionTemplate</tt>.
     * 
     * @param myteayTransactionTemplate value to be assigned to property myteayTransactionTemplate
     */
    public void setMyteayTransactionTemplate(TransactionTemplate myteayTransactionTemplate) {
        this.myteayTransactionTemplate = myteayTransactionTemplate;
    }

}
