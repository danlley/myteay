/**
 * Myteay.com Inc.
 * Copyright (c) 2005-2017 All Rights Reserved.
 */
package com.myteay.common.dal.daointerface;

import com.myteay.common.dal.dataobject.UserContactListInfoDO;
import com.myteay.common.dal.utils.MtDBKey;

/**
 * 联系人操作DAO
 * 
 * @author danlley
 * @version $Id: UserContactListInfoDAO.java, v 0.1 Sep 2, 2017 5:30:23 PM danlley Exp $
 */
public interface UserContactListInfoDAO extends MtDBKey {
    /**
     * 插入用户基本信息
     * 
     * @param userContactListInfoDO   用户基本信息数据模型
     * @return
     */
    String insert(UserContactListInfoDO userContactListInfoDO);

    /**
     * 通过userid查找用户信息
     * 
     * @param userContactListInfoDO    用户基本信息数据模型
     * @return
     */
    UserContactListInfoDO queryUserContactListInfoDO(UserContactListInfoDO userContactListInfoDO);
}
