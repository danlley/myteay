/**
 * Myteay.com Inc.
 * Copyright (c) 2005-2017 All Rights Reserved.
 */
package com.myteay.core.model.repository;

import com.myteay.common.service.facade.model.MtOperateResult;
import com.myteay.core.model.user.MtUserMobileBaseInfoModel;

/**
 * �û��ֻ�������Ϣ�����ִ�
 * 
 * @author danlley
 * @version $Id: MtUserMobileBaseInfoRepository.java, v 0.1 Sep 3, 2017 5:29:12 PM danlley Exp $
 */
public interface MtUserMobileBaseInfoRepository {

    /**
     * �����û��ֻ�������Ϣ
     * 
     * @param mtUserMobileBaseInfoModel
     * @return
     */
    public MtOperateResult<String> saveUserMobileInfo(MtUserMobileBaseInfoModel mtUserMobileBaseInfoModel);
}
