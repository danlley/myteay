/**
 * Myteay.com Inc.
 * Copyright (c) 2015-2015 All Rights Reserved.
 */
package com.myteay.core.model.repository;

import com.myteay.common.service.facade.model.MtOperateResult;
import com.myteay.common.service.facade.model.MtUserRegQRCodeMessage;
import com.myteay.common.util.exception.MtException;
import com.myteay.common.utils.exception.MtBizProcessException;
import com.myteay.core.model.user.MtUserModel;

/**
 * 用户信息仓储类
 * 
 * @author Administrator
 * @version $Id: MtUsersInfoRepository.java, v 0.1 2015年11月15日 下午5:34:42 Administrator Exp $
 */
public interface MtUsersInfoRepository {

    /**
     * 保存用户信息模型
     * 
     * @param mtUserModel 用户信息模型
     */
    public MtOperateResult<String> saveUserInfoModel(MtUserModel mtUserModel) throws MtException;

    /**
     * 通过用户ID查找用户模型
     * 
     * @param userId    用户ID
     * @return
     */
    public MtOperateResult<MtUserModel> getUserInfoModelById(String userId);

    /**
     * 更新用户注册的二维码信息
     * 
     * @param mtUserRegQRCodeMessage    二维码信息交互模型
     * @return
     * @throws MtBizProcessException
     */
    public MtOperateResult<MtUserModel> updateUserQrcode(MtUserRegQRCodeMessage mtUserRegQRCodeMessage)
                                                                                                        throws MtBizProcessException;

}
