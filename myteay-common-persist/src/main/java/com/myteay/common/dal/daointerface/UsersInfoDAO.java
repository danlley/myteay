/**
 * Myteay.com Inc.
 * Copyright (c) 2015-2015 All Rights Reserved.
 */
package com.myteay.common.dal.daointerface;

import com.myteay.common.dal.dataobject.UsersInfoDO;

/**
 * �û�������Ϣ����DAO
 * 
 * @author Administrator
 * @version $Id: UsersInfoDAO.java, v 0.1 2015��11��15�� ����4:37:30 Administrator Exp $
 */
public interface UsersInfoDAO {
    /**
     * �����û�������Ϣ
     * 
     * @param usersInfoDO   �û�������Ϣ����ģ��
     * @return
     */
    String insert(UsersInfoDO usersInfoDO);

    /**
     * ͨ��userid�����û���Ϣ
     * 
     * @param userID    ��ԱID
     * @return
     */
    UsersInfoDO getById(String userId);

    /**
     * 
     * @param usersInfoDO
     */
    public void updateQrCode(UsersInfoDO usersInfoDO);
}
