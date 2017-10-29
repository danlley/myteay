/**
 * Myteay.com Inc.
 * Copyright (c) 2015-2016 All Rights Reserved.
 */
package com.myteay.common.dal.dinner.daointerface;

import java.util.List;

import com.myteay.common.dal.dinner.dataobject.GoodsInfoDO;
import com.myteay.common.util.constants.MtDBKey;

/**
 * ��Ʒ��Ϣ����DAO
 * 
 * @author Administrator
 * @version $Id: GoodsInfoDAO.java, v 0.1 2016��3��5�� ����12:39:22 Administrator Exp $
 */
public interface GoodsInfoDAO extends MtDBKey {

    /**
     * ͨ��IDɾ��ָ����Ʒ��Ϣ
     * 
     * @param id    ��ƷID
     * @return      ɾ�����
     */
    int deleteById(String id);

    /**
     * ͨ��ID���ҵ�Ʒ��Ϣ����ģ��
     * 
     * @param id    ��Ʒ��ˮ��
     * @return
     */
    GoodsInfoDO findById(String id);

    /**
     * ���뵥Ʒ������Ϣ
     * 
     * @param goodsInfoDO   ��Ʒ������Ϣ����ģ��
     * @return
     */
    String insert(GoodsInfoDO goodsInfoDO);

    /**
     * ��ѯ���е�Ʒ��Ϣ�б�
     * 
     * @return  ��Ʒ��Ϣ�б�
     */
    public List<GoodsInfoDO> findAll();
}
