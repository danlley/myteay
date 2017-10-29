/**
 * Myteay.com Inc.
 * Copyright (c) 2015-2015 All Rights Reserved.
 */
package com.myteay.common.dal.daointerface;

import com.myteay.common.dal.dataobject.UsersInfoDO;
import com.myteay.common.util.constants.MtDBKey;

/**
 * 用户基本信息操作DAO
 * 
 * @author Administrator
 * @version $Id: UsersInfoDAO.java, v 0.1 2015年11月15日 下午4:37:30 Administrator Exp $
 */
public interface UsersInfoDAO extends MtDBKey {
    /**
     * 插入用户基本信息
     * 
     * @param usersInfoDO   用户基本信息数据模型
     * @return
     */
    String insert(UsersInfoDO usersInfoDO);

    /**
     * 通过userid查找用户信息
     * 
     * @param userID    会员ID
     * @return
     */
    UsersInfoDO getById(String userId);

    /**
     * 
     * @param usersInfoDO
     */
    public void updateQrCode(UsersInfoDO usersInfoDO);
}
