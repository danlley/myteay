/**
 * Myteay.com Inc.
 * Copyright (c) 2015-2015 All Rights Reserved.
 */
package com.myteay.common.utils.orm.ibatis.support;

import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

/**
 * ibatis因spring4中不再支持，这里用空实现的方式解决
 * 
 * 注：
 * 支持代码拿自springmvc3.2.15版本，此版本中，spring对ibatis的支持已经标注为不建议使用
 * 
 * @author Administrator
 * @version $Id: SqlMapClientDaoSupport.java, v 0.1 2015年11月15日 下午5:01:43 Administrator Exp $
 */
@SuppressWarnings("deprecation")
public class MtSqlMapClientDaoSupport extends SqlMapClientDaoSupport {

}
