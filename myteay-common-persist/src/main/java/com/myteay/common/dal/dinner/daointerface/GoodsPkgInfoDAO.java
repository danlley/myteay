/**
 * Myteay.com Inc.
 * Copyright (c) 2015-2016 All Rights Reserved.
 */
package com.myteay.common.dal.dinner.daointerface;

import java.util.List;

import com.myteay.common.dal.dinner.dataobject.GoodsPkgInfoDO;
import com.myteay.common.dal.utils.MtDBKey;

/**
 * �ײ���Ϣ����DAO
 * 
 * @author Administrator
 * @version $Id: GoodsPkgInfoDAO.java, v 0.1 2016��3��5�� ����9:28:01 Administrator Exp $
 */
public interface GoodsPkgInfoDAO extends MtDBKey {

    /**
     * ɾ��ָ�����ײ�������Ϣ
     * 
     * @param id    �ײ���ˮ��
     * @return
     */
    public int deleteById(String id);

    /**
     * ͨ���ײ���Ϣ��ˮ�Ų����ײ���Ϣ����ģ��
     * 
     * @param id    �ײ���Ϣ��ˮ��
     * @return      �ײ���Ϣ����ģ��
     */
    public GoodsPkgInfoDO findById(String id);

    /**
     * ���������ײ���Ϣ
     * 
     * @param goodsPkgInfoDO    �ײ���Ϣ����ģ��
     * @return                  �ײ�ID
     */
    public String insert(GoodsPkgInfoDO goodsPkgInfoDO);

    /**
     * ��ѯ�����ײ���Ϣ�б�
     * 
     * @return  �ײ���Ϣ�б�
     */
    public List<GoodsPkgInfoDO> findAll();
}
