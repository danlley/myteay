/**
 * Danlley Wei (mailto://danlley@126.com)
 * Copyright (c) 2005-2017 All Rights Reserved.
 */
package com.myteay.common.dal.utils;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.defaults.DefaultSqlSessionFactory;
import org.springframework.util.CollectionUtils;

import com.myteay.common.util.comm.StringUtils;

/**
 * SqlSessionFactory会话实例管理组件
 * 
 * @author danlley(danlley@126.com)
 * @version $Id: SqlSession.java, v 0.1 2017年5月7日 上午12:40:16 danlley(danlley@126.com) Exp $
 */
public class SqlSessionSwitcher {

    /** 用于指定DefaultSqlSessionFactory，线程安全 */
    private Map<String, DefaultSqlSessionFactory> switcherBeans = new ConcurrentHashMap<String, DefaultSqlSessionFactory>();

    /**
     * 获取指定的SqlSessionFactory
     * 
     * @param key
     * @return
     */
    public SqlSessionFactory getSqlSessionFactory(String key) {
        if (StringUtils.isBlank(key) || CollectionUtils.isEmpty(switcherBeans) || !switcherBeans.containsKey(key)) {
            throw new IllegalArgumentException("SqlSessionSwitcher初始化失败，无法得到可用的SqlSessionFactory实例 key=" + key);
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
