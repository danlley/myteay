/**
 * Danlley Wei (mailto://danlley@126.com)
 * Copyright (c) 2005-2017 All Rights Reserved.
 */
package com.myteay.common.dal.utils;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.support.DaoSupport;

/**
 * 本类参考了org.mybatis.spring.support.SqlSessionDaoSupport类的实现，但解决了多数据源管理的问题
 * 
 * @see org.mybatis.spring.support.SqlSessionDaoSupport
 * @author danlley(danlley@126.com)
 * @version $Id: MtSqlSessionDaoSupport.java, v 0.1 2017年5月7日 上午12:35:28 danlley(danlley@126.com) Exp $
 */
public class MtSqlSessionDaoSupport extends DaoSupport {

    /** 数据库切换管理器 */
    @Autowired
    private SqlSessionSwitcher switcher;

    /** SQL会话 */
    private SqlSession         sqlSession;

    /** 外部SQL会话标识 */
    private boolean            externalSqlSession = false;

    /**
     * 初始化SQL会话
     * 
     * @param sqlSessionFactory
     */
    public void setSqlSessionFactory(SqlSessionFactory sqlSessionFactory) {
        if (!this.externalSqlSession) {
            //this.sqlSession = new SqlSessionTemplate(sqlSessionFactory);
        }
    }

    /**
     * SQL会话
     * 
     * @param sqlSessionTemplate
     */
    public void setSqlSessionTemplate(SqlSessionTemplate sqlSessionTemplate) {
        //this.sqlSession = sqlSessionTemplate;
        // this.externalSqlSession = true;
    }

    /**
     * Users should use this method to get a SqlSession to call its statement methods
     * This is SqlSession is managed by spring. Users should not commit/rollback/close it
     * because it will be automatically done.
     *
     * @return Spring managed thread safe SqlSession
     * @throws Exception 
     */
    public SqlSession getSqlSession(String key) {

        if (sqlSession != null) {
            return this.sqlSession;
        }

        //防止线程多次对实例进行不必要的初始化
        if (switcher == null || switcher.getSqlSessionFactory(key) == null) {
            throw new IllegalArgumentException(
                "Property 'sqlSessionFactory' or 'sqlSessionTemplate' or 'switcher' are required");
        }

        sqlSession = new SqlSessionTemplate(switcher.getSqlSessionFactory(key));

        return this.sqlSession;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void checkDaoConfig() {
        //notNull(this.sqlSession, "Property 'sqlSessionFactory' or 'sqlSessionTemplate' are required");
    }

    /**
     * Setter method for property <tt>switcher</tt>.
     * 
     * @param switcher value to be assigned to property switcher
     */
    public void setSwitcher(SqlSessionSwitcher switcher) {
        this.switcher = switcher;
    }

}
