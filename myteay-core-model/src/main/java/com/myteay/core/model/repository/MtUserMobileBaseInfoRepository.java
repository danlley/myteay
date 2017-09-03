/**
 * Myteay.com Inc.
 * Copyright (c) 2005-2017 All Rights Reserved.
 */
package com.myteay.core.model.repository;

import com.myteay.common.service.facade.model.MtOperateResult;
import com.myteay.core.model.user.MtUserMobileBaseInfoModel;

/**
 * 用户手机基本信息操作仓储
 * 
 * @author danlley
 * @version $Id: MtUserMobileBaseInfoRepository.java, v 0.1 Sep 3, 2017 5:29:12 PM danlley Exp $
 */
public interface MtUserMobileBaseInfoRepository {

    /**
     * 保存用户手机基本信息
     * 
     * @param mtUserMobileBaseInfoModel
     * @return
     */
    public MtOperateResult<String> saveUserMobileInfo(MtUserMobileBaseInfoModel mtUserMobileBaseInfoModel);
}
