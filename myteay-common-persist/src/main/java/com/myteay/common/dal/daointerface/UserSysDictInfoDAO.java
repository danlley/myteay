/**
 * Myteay.com Inc.
 * Copyright (c) 2015-2016 All Rights Reserved.
 */
package com.myteay.common.dal.daointerface;

import java.util.List;

import com.myteay.common.dal.dataobject.UserSysDictInfoDO;

/**
 * �û��ֵ����DAO
 * 
 * @author Administrator
 * @version $Id: UserSysDictInfoDAO.java, v 0.1 2016��9��7�� ����12:30:52 Administrator Exp $
 */
public interface UserSysDictInfoDAO {

    /**
     * ��ȡ�����û��ֵ�
     * 
     * @return  �û��ֵ��б�
     */
    public List<UserSysDictInfoDO> findAll();

    /**
     * ͨ���ֵ�ؼ��ֻ�ȡ�ֵ�ֵ
     * 
     * @param key
     * @return  �ֵ��ֵ
     */
    public UserSysDictInfoDO getByKey(String key);
}
