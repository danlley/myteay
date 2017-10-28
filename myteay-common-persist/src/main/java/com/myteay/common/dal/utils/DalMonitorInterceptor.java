/**
 * Danlley Wei (mailto://danlley@126.com)
 * Copyright (c) 2005-2017 All Rights Reserved.
 */
package com.myteay.common.dal.utils;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * DAL���ü��������, ��¼DALִ�е�ժҪ��־
 * 
 * @author danlley
 * @version $Id: DalMonitorInterceptor.java, v 0.1 Oct 27, 2017 11:40:25 PM danlley Exp $
 */
public class DalMonitorInterceptor implements MethodInterceptor {

    /** DAL��ժҪ��־��dal���޷�����common-util����룬�ʴ˴�д����־Name */
    private static final Logger digestLogger = LoggerFactory.getLogger("DAL-DIGEST");

    /** 
     * @see org.aopalliance.intercept.MethodInterceptor#invoke(org.aopalliance.intercept.MethodInvocation)
     */
    public Object invoke(MethodInvocation invocation) throws Throwable {
        String packageName = invocation.getMethod().getDeclaringClass().getPackage().getName();
        String className = invocation.getMethod().getDeclaringClass().getSimpleName();
        String method = className + "." + invocation.getMethod().getName();
        String dbName = parseDbName(packageName);

        long startTime = System.currentTimeMillis();

        //�Ƿ���Throwable
        boolean hasError = false;
        try {
            return invocation.proceed();
        } catch (Throwable ex) {
            hasError = true;
            throw ex;

        } finally {
            if (digestLogger.isInfoEnabled()) {
                long elapseTime = System.currentTimeMillis() - startTime;

                if (hasError) {
                    digestLogger.info("(" + dbName + "," + method + ",N," + elapseTime + "ms)");
                } else {
                    digestLogger.info("(" + dbName + "," + method + ",Y," + elapseTime + "ms)");
                }
            }
        }
    }

    /**
     * �������ݿ�����
     * 
     * @param packageName
     * @param className
     * @return
     */
    private String parseDbName(String packageName) {
        if (StringUtils.contains(packageName, "dinner")) {
            return "dinner";
        }

        return "customer";
    }
}
