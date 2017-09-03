/**
 * Myteay.com Inc.
 * Copyright (c) 2005-2017 All Rights Reserved.
 */
package com.myteay.common.service.facade;

import com.myteay.common.service.facade.mobile.info.MtRegisterInfo;
import com.myteay.common.service.facade.results.MtServiceResult;

/**
 * 用户手机端服务入口
 * 
 * @author danlley
 * @version $Id: MtUserMobileService.java, v 0.1 Aug 31, 2017 9:10:01 AM danlley Exp $
 */
public interface MtUserMobileService {

    /**
     * 用户注册服务接口，对MtRegisterInfo信息进行注册处理后，将注册后的userid和二维码码值信息进行回填并返回回填结果
     * 
     * @param registerInfo  注册请求交互单据
     * @return              注册请求处理完成后的回填结果
     */
    public MtServiceResult<MtRegisterInfo> register(MtRegisterInfo registerInfo);
}
