/**
 * Myteay.com Inc.
 * Copyright (c) 2005-2017 All Rights Reserved.
 */
package com.myteay.biz.service.impl;

import java.net.MalformedURLException;

import com.caucho.hessian.client.HessianProxyFactory;
import com.myteay.common.service.facade.MtUserMobileService;
import com.myteay.common.service.facade.mobile.info.MtRegisterInfo;
import com.myteay.common.service.facade.results.MtServiceResult;

import junit.framework.TestCase;

/**
 * 
 * @author danlley
 * @version $Id: MtUserMobileServiceImplTest.java, v 0.1 Aug 31, 2017 9:51:38 AM danlley Exp $
 */
public class MtUserMobileServiceImplTest extends TestCase {

    /**
     * Test method for {@link com.myteay.biz.service.impl.MtUserMobileServiceImpl#register(com.myteay.common.service.facade.mobile.info.MtRegisterInfo)}.
     */
    public void testRegister() {
        HessianProxyFactory factory = new HessianProxyFactory();

        String url = "http://192.168.42.2/myteay-web/mtUserMobileService";

        MtUserMobileService us = null;
        try {
            us = (MtUserMobileService) factory.create(MtUserMobileService.class, url);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        MtRegisterInfo registerInfo = new MtRegisterInfo();
        MtServiceResult<MtRegisterInfo> result = us.register(registerInfo);

        System.out.println(result.getResult());
    }

}
