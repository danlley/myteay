/**
 * Danlley Wei (mailto://danlley@126.com)
 * Copyright (c) 2005-2017 All Rights Reserved.
 */
package com.myteay.common.dal.utils;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.apache.commons.lang.StringUtils;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.defaults.DefaultSqlSessionFactory;
import org.springframework.util.CollectionUtils;

/**
 * SqlSessionFactory�Ựʵ���������
 * 
 * @author danlley(danlley@126.com)
 * @version $Id: SqlSession.java, v 0.1 2017��5��7�� ����12:40:16 danlley(danlley@126.com) Exp $
 */
public class SqlSessionSwitcher {

    /** ����ָ��DefaultSqlSessionFactory���̰߳�ȫ */
    private Map<String, DefaultSqlSessionFactory> switcherBeans = new ConcurrentHashMap<String, DefaultSqlSessionFactory>();

    /**
     * ��ȡָ����SqlSessionFactory
     * 
     * @param key
     * @return
     */
    public SqlSessionFactory getSqlSessionFactory(String key) {
        if (StringUtils.isBlank(key) || CollectionUtils.isEmpty(switcherBeans) || !switcherBeans.containsKey(key)) {
            throw new IllegalArgumentException("SqlSessionSwitcher��ʼ��ʧ�ܣ��޷��õ����õ�SqlSessionFactoryʵ�� key=" + key);
        }

        return switcherBeans.get(key);
    }

    /**
     * Setter method for property <tt>switcherBeans</tt>.
     * 
     * @param switcherBeans value to be assigned to property switcherBeans
     */
    public void setSwitcherBeans(Map<String, DefaultSqlSessionFactory> switcherBeans) {
        this.switcherBeans = switcherBeans;
    }
}
