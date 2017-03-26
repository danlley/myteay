/**
 * Myteay.com Inc.
 * Copyright (c) 2015-2015 All Rights Reserved.
 */
package com.myteay.test.test;

import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * 
 * @author Administrator
 * @version $Id: JettyCustomServer.java, v 0.1 2015年11月16日 上午10:12:28 Administrator Exp $
 */
public class JettyCustomServer {
    @SuppressWarnings("resource")
    public static void main(String[] args) throws Exception {
        new ClassPathXmlApplicationContext("classpath*:/WEB-INF/applicationContext.xml");
    }
}
