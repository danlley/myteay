/**
 * Myteay.com Inc.
 * Copyright (c) 2015-2016 All Rights Reserved.
 */
package com.myteay.common.dal.dinner.daointerface;

import java.util.List;

import com.myteay.common.dal.dinner.dataobject.ShopInfoDO;

/**
 * ������Ϣ����DAO
 * 
 * @author Administrator
 * @version $Id: ShopInfoDAO.java, v 0.1 2016��3��4�� ����5:44:53 Administrator Exp $
 */
public interface ShopInfoDAO {

    /**
     * ��ѯ���е�����Ϣ�б�
     * 
     * @return  ������Ϣ�б�
     */
    public List<ShopInfoDO> findAll();
}
