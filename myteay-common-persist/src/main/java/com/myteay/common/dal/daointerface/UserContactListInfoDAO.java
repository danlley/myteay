/**
 * Myteay.com Inc.
 * Copyright (c) 2005-2017 All Rights Reserved.
 */
package com.myteay.common.dal.daointerface;

import com.myteay.common.dal.dataobject.UserContactListInfoDO;
import com.myteay.common.util.constants.MtDBKey;

/**
 * ��ϵ�˲���DAO
 * 
 * @author danlley
 * @version $Id: UserContactListInfoDAO.java, v 0.1 Sep 2, 2017 5:30:23 PM danlley Exp $
 */
public interface UserContactListInfoDAO extends MtDBKey {
    /**
     * �����û�������Ϣ
     * 
     * @param userContactListInfoDO   �û�������Ϣ����ģ��
     * @return
     */
    String insert(UserContactListInfoDO userContactListInfoDO);

    /**
     * ͨ��userid�����û���Ϣ
     * 
     * @param userContactListInfoDO    �û�������Ϣ����ģ��
     * @return
     */
    UserContactListInfoDO queryUserContactListInfoDO(UserContactListInfoDO userContactListInfoDO);
}
