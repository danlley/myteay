/**
 * Myteay.com Inc.
 * Copyright (c) 2005-2017 All Rights Reserved.
 */
package com.myteay.core.model.repository;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallback;
import org.springframework.transaction.support.TransactionTemplate;
import org.springframework.util.CollectionUtils;

import com.myteay.common.dal.daointerface.UserContactListInfoDAO;
import com.myteay.common.dal.dataobject.UserContactListInfoDO;
import com.myteay.common.service.facade.enums.MtOperateExResultEnum;
import com.myteay.common.service.facade.enums.MtOperateResultEnum;
import com.myteay.common.service.facade.model.MtOperateResult;
import com.myteay.core.model.user.MtUserContactModel;
import com.myteay.core.model.user.MtUserSingleContactModel;

/**
 * 联系人仓储层
 * 
 * @author danlley
 * @version $Id: MtUserContactListInfoRepositoryImpl.java, v 0.1 Sep 2, 2017 5:38:22 PM danlley Exp $
 */
public class MtUserContactListInfoRepositoryImpl implements MtUserContactListInfoRepository {

    /** 日志 */
    public static final Logger     logger = Logger.getLogger(MtUserContactListInfoRepositoryImpl.class);

    /** 联系人操作DAO */
    private UserContactListInfoDAO userContactListInfoDAO;

    /** share库事务模板 */
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
                        logger.warn("保存用户信息发生系统异常 mtUserContactModel=" + mtUserContactModel, e);
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
            logger.warn("批量保存联系人列表信息失败 innerResult=" + innerResult);
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
     * 开始批量保存联系人信息
     * 
     * @param mtUserContactModel
     * @return
     */
    private MtOperateResult<String> saveContactListInTransaction(
                                                                 MtUserContactModel mtUserContactModel) {

        MtOperateResult<String> result = new MtOperateResult<String>();

        if (mtUserContactModel == null || CollectionUtils.isEmpty(mtUserContactModel.getSingleContactList())) {
            logger.warn("联系人模型不可用，无法保存联系人信息 mtUserContactModel=" + mtUserContactModel);
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
     * 保存单个联系人信息(利益最大化：这里对单条联系人无法保存的问题不做事务回滚处理，确保最大限度的保留联系人信息)
     * 
     * @param mtUserSingleContactModel
     */
    private void saveSingleUserContactListInfo(MtUserSingleContactModel mtUserSingleContactModel) {

        if (mtUserSingleContactModel == null) {
            logger.warn("单个联系人信息不可用 mtUserSingleContactModel is null");
            return;
        }

        UserContactListInfoDO userContactListInfoDO = convertModel2DO(mtUserSingleContactModel);
        if (userContactListInfoDO == null) {
            logger.warn("通过单个联系人模型，无法得到对应的数据模型，当前数据保存失败 mtUserSingleContactModel=" + mtUserSingleContactModel);
            return;
        }

        if (!validateContactListDO(userContactListInfoDO)) {
            logger.warn("当前需要保存的数据模型不合法，无法继续进行保存动作userContactListInfoDO=" + userContactListInfoDO);
            return;
        }

        UserContactListInfoDO queriedUserContactListDO = userContactListInfoDAO.queryUserContactListInfoDO(
            userContactListInfoDO);
        if (logger.isInfoEnabled()) {
            logger.info("开始做联系人写库前的检查动作，确保数据库中不存在当前需要插入的数据userContactListInfoDO=" + userContactListInfoDO
                        + " queriedUserContactListDO=" + queriedUserContactListDO);
        }

        if (queriedUserContactListDO != null) {
            logger.warn("当前联系人已经存在无需进行二次保存 queriedUserContactListDO=" + queriedUserContactListDO);
            return;
        }

        if (logger.isInfoEnabled()) {
            logger.info("开始保存联系人信息数据模型userContactListInfoDO=" + userContactListInfoDO);
        }
        userContactListInfoDAO.insert(userContactListInfoDO);
    }

    /**
     * 验证数据模型的合法性
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
     * 将领域模型转换为数据模型
     * 
     * @param mtUserSingleContactModel
     * @return
     */
    private UserContactListInfoDO convertModel2DO(MtUserSingleContactModel mtUserSingleContactModel) {

        if (mtUserSingleContactModel == null) {
            logger.warn("联系人模型不可用，无法保存联系人信息 mtUserContactModel is null");
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
