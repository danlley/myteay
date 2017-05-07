/**
 * Danlley Wei (mailto://danlley@126.com)
 * Copyright (c) 2005-2017 All Rights Reserved.
 */
package com.myteay.common.dal.utils;

/**
 * 各个DAO类继承此类，以便能够得到指定的数据库
 * 
 * @author danlley(danlley@126.com)
 * @version $Id: MtDBKey.java, v 0.1 2017年5月7日 上午2:08:07 danlley(danlley@126.com) Exp $
 */
public interface MtDBKey {

    /** dinner库标识 */
    public final static String dinner   = "dinnerSqlSessionFactory";

    /** customer库标识 */
    public final static String customer = "customerSqlSessionFactory";
}
