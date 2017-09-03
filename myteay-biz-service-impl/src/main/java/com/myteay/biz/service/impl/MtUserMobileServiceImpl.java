/**
 * Myteay.com Inc.
 * Copyright (c) 2005-2017 All Rights Reserved.
 */
package com.myteay.biz.service.impl;

import com.myteay.common.service.facade.MtUserMobileService;
import com.myteay.common.service.facade.enums.MtOperateResultEnum;
import com.myteay.common.service.facade.mobile.info.MtRegisterInfo;
import com.myteay.common.service.facade.results.MtServiceResult;

/**
 * 用户手机端服务入口
 * 
 * @author danlley
 * @version $Id: MtUserMobileServiceImpl.java, v 0.1 Aug 31, 2017 9:23:58 AM danlley Exp $
 */
public class MtUserMobileServiceImpl implements MtUserMobileService {

    /** 
     * @see com.myteay.common.service.facade.MtUserMobileService#register(com.myteay.common.service.facade.mobile.info.MtRegisterInfo)
     */
    @Override
    public MtServiceResult<MtRegisterInfo> register(MtRegisterInfo registerInfo) {

        MtServiceResult<MtRegisterInfo> result = new MtServiceResult<MtRegisterInfo>();
        result.setOperateResult(MtOperateResultEnum.CAMP_OPERATE_SUCCESS.getValue());

        registerInfo.setUserid("3454134324321413241431");
        registerInfo.setQrCodeId("3543425245425245245");

        result.setResult(registerInfo);
        return result;
    }

}
