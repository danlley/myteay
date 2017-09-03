/**
 * Myteay.com Inc.
 * Copyright (c) 2005-2017 All Rights Reserved.
 */
package com.myteay.biz.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.myteay.common.service.facade.mobile.info.MtRegisterInfo;
import com.myteay.common.service.facade.results.MtServiceResult;

import junit.framework.TestCase;

/**
 * 
 * @author danlley
 * @version $Id: MtUserMobileServiceControllerTest.java, v 0.1 Sep 1, 2017 2:48:22 PM danlley Exp $
 */
public class MtUserMobileServiceControllerTest extends TestCase {

    /**
     * Test method for {@link com.myteay.biz.service.impl.MtUserMobileServiceController#greeting(com.myteay.common.service.facade.mobile.info.MtRegisterInfo, javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)}.
     */
    public void testGreeting() {

        MtServiceResult<MtRegisterInfo> result = new MtServiceResult<MtRegisterInfo>();
        MtRegisterInfo registerInfo = new MtRegisterInfo();
        registerInfo.setUserid("adsfafa");
        result.setResult(registerInfo);
        String str = JSONObject.toJSONString(result);
        System.out.println(str);
    }

}
