/**
 * Myteay.com Inc.
 * Copyright (c) 2015-2016 All Rights Reserved.
 */
package com.myteay.common.dal.daointerface;

import java.util.List;

import com.myteay.common.dal.dataobject.UserSysDictInfoDO;
import com.myteay.common.util.constants.MtDBKey;

/**
 * 用户字典操作DAO
 * 
 * @author Administrator
 * @version $Id: UserSysDictInfoDAO.java, v 0.1 2016年9月7日 上午12:30:52 Administrator Exp $
 */
public interface UserSysDictInfoDAO extends MtDBKey {

    /**
     * 获取所有用户字典
     * 
     * @return  用户字典列表
     */
    public List<UserSysDictInfoDO> findAll();

    /**
     * 通过字典关键字获取字典值
     * 
     * @param key
     * @return  字典键值
     */
    public UserSysDictInfoDO getByKey(String key);
}
