/**
 * Myteay.com Inc.
 * Copyright (c) 2005-2017 All Rights Reserved.
 */
package com.myteay.common.dal.daointerface;

import com.myteay.common.dal.dataobject.UserMobileBaseInfoDO;
import com.myteay.common.util.constants.MtDBKey;

/**
 * �û��ֻ�������Ϣ����DAO
 * 
 * @author danlley
 * @version $Id: UserMobileBaseInfoDAO.java, v 0.1 Sep 3, 2017 5:19:18 PM danlley Exp $
 */
public interface UserMobileBaseInfoDAO extends MtDBKey {

    /**
     * �����û��ֻ�������Ϣ
     * 
     * @param userMobileBaseInfoDO   �û��ֻ�������Ϣ����ģ��
     * @return
     */
    String insert(UserMobileBaseInfoDO userMobileBaseInfoDO);

    /**
     * ͨ��userid�����û��ֻ���Ϣ
     * 
     * @param userMobileBaseInfoDO    �û�������Ϣ����ģ��
     * @return
     */
    UserMobileBaseInfoDO queryUserMobileBaseInfo(UserMobileBaseInfoDO userMobileBaseInfoDO);
}
