/**
 * Myteay.com Inc.
 * Copyright (c) 2005-2017 All Rights Reserved.
 */
package com.myteay.common.dal.daointerface;

import com.myteay.common.dal.dataobject.UserMobileBaseInfoDO;
import com.myteay.common.dal.utils.MtDBKey;

/**
 * 用户手机基本信息操作DAO
 * 
 * @author danlley
 * @version $Id: UserMobileBaseInfoDAO.java, v 0.1 Sep 3, 2017 5:19:18 PM danlley Exp $
 */
public interface UserMobileBaseInfoDAO extends MtDBKey {

    /**
     * 插入用户手机基本信息
     * 
     * @param userMobileBaseInfoDO   用户手机基本信息数据模型
     * @return
     */
    String insert(UserMobileBaseInfoDO userMobileBaseInfoDO);

    /**
     * 通过userid查找用户手机信息
     * 
     * @param userMobileBaseInfoDO    用户基本信息数据模型
     * @return
     */
    UserMobileBaseInfoDO queryUserMobileBaseInfo(UserMobileBaseInfoDO userMobileBaseInfoDO);
}
