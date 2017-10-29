/**
 * Myteay.com Inc.
 * Copyright (c) 2015-2016 All Rights Reserved.
 */
package com.myteay.core.model.repository;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

import com.myteay.common.dal.daointerface.UsersQrCodeWfInfoDAO;
import com.myteay.common.dal.dataobject.UsersQrCodeWfInfoDO;
import com.myteay.common.service.facade.enums.MtOperateExResultEnum;
import com.myteay.common.service.facade.enums.MtOperateResultEnum;
import com.myteay.common.service.facade.model.MtOperateResult;
import com.myteay.common.utils.exception.MtBizProcessException;

/**
 * 用户二维码生成操作流水
 * 
 * @author Administrator
 * @version $Id: MtUsersQrWfRepositoryImpl.java, v 0.1 2016年9月7日 上午1:38:15 Administrator Exp $
 */
public class MtUsersQrWfRepositoryImpl implements MtUsersQrWfRepository {

    /** 日志 */
    public static final Logger   logger = Logger.getLogger(MtUsersQrWfRepositoryImpl.class);

    /** 二维码生成流水操作DAO */
    private UsersQrCodeWfInfoDAO mtUsersQrCodeWfInfoDAO;

    /** 
     * @see com.myteay.core.model.repository.MtUsersQrWfRepository#save(java.lang.String)
     */
    @Override
    public void save(String userId) throws MtBizProcessException {

        //基础校验
        if (StringUtils.isBlank(userId)) {
            logger.warn("[QRCODE]userId is illegl!!");
            throw new MtBizProcessException(MtOperateExResultEnum.CAMP_USERID_ERR.getCode(),
                MtOperateExResultEnum.CAMP_USERID_ERR.getMessage());
        }

        UsersQrCodeWfInfoDO usersQrCodeWfInfoDO = new UsersQrCodeWfInfoDO();
        usersQrCodeWfInfoDO.setUserId(userId);
        mtUsersQrCodeWfInfoDAO.insert(usersQrCodeWfInfoDO);
    }

    /** 
     * @see com.myteay.core.model.repository.MtUsersQrWfRepository#lock(java.lang.String)
     */
    @Override
    public UsersQrCodeWfInfoDO lock(String userId) throws MtBizProcessException {

        //基础校验
        if (StringUtils.isBlank(userId)) {
            logger.warn("[QRCODE]userId is illegl!!");
            throw new MtBizProcessException(MtOperateExResultEnum.CAMP_USERID_ERR.getCode(),
                MtOperateExResultEnum.CAMP_USERID_ERR.getMessage());
        }

        return mtUsersQrCodeWfInfoDAO.getByIdForUpdate(userId);
    }

    /** 
     * @see com.myteay.core.model.repository.MtUsersQrWfRepository#updateLasttime(java.lang.String)
     */
    @Override
    public void updateLasttime(String userId) throws MtBizProcessException {

        //基础校验
        if (StringUtils.isBlank(userId)) {
            logger.warn("[QRCODE]userId is illegl!!");
            throw new MtBizProcessException(MtOperateExResultEnum.CAMP_USERID_ERR.getCode(),
                MtOperateExResultEnum.CAMP_USERID_ERR.getMessage());
        }

        mtUsersQrCodeWfInfoDAO.updateDateByUserid(userId);
    }

    /** 
     * @see com.myteay.core.model.repository.MtUsersQrWfRepository#removeQrcode(java.lang.String)
     */
    @Override
    public MtOperateResult<String> removeQrcode(String userId) {
        MtOperateResult<String> result = new MtOperateResult<String>();
        try {
            mtUsersQrCodeWfInfoDAO.delete(userId);
        } catch (Exception e) {
            logger.error("删除二维码流水时发生未知异常 userId=" + userId, e);
            result.setErrorDetail("删除二维码流水时发生未知异常 message=" + e.getMessage());
            result.setOperateExResult(MtOperateExResultEnum.CAMP_QRCODE_EXE_FAILED);
            result.setOperateResult(MtOperateResultEnum.CAMP_OPERATE_FAILED);
            return result;
        }

        result.setOperateExResult(MtOperateExResultEnum.CAMP_OPERATE_SUCCESS);
        result.setOperateResult(MtOperateResultEnum.CAMP_OPERATE_SUCCESS);
        result.setResult(userId);

        return result;
    }

    /**
     * Setter method for property <tt>mtUsersQrCodeWfInfoDAO</tt>.
     * 
     * @param mtUsersQrCodeWfInfoDAO value to be assigned to property mtUsersQrCodeWfInfoDAO
     */
    public void setMtUsersQrCodeWfInfoDAO(UsersQrCodeWfInfoDAO mtUsersQrCodeWfInfoDAO) {
        this.mtUsersQrCodeWfInfoDAO = mtUsersQrCodeWfInfoDAO;
    }

}
