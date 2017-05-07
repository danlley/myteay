/**
 * Myteay.com Inc.
 * Copyright (c) 2015-2016 All Rights Reserved.
 */
package com.myteay.common.dal.daointerface;

import java.util.List;

import com.myteay.common.dal.dataobject.UsersQrCodeWfInfoDO;
import com.myteay.common.dal.utils.MtDBKey;

/**
 * ��ά��������ˮ����DAO
 * 
 * @author Administrator
 * @version $Id: UsersQrCodeWfInfoDAO.java, v 0.1 2016��9��7�� ����1:24:25 Administrator Exp $
 */
public interface UsersQrCodeWfInfoDAO extends MtDBKey {

    /**
     * ��ά��������ˮ���
     * 
     * @param usersQrCodeWfInfoDO   ��ά��������ˮ����ģ��
     * @return
     */
    public String insert(UsersQrCodeWfInfoDO usersQrCodeWfInfoDO);

    /**
     * ��������ѯ100������
     * 
     * @return  ��ά��������ˮ�б�
     */
    public List<UsersQrCodeWfInfoDO> getByLimit();

    /**
     * ͨ��userId��ѯ�������ض���¼��ֹ�������̴߳���
     * 
     * @param userId    ��ԱID
     * @return          ��ά��������ˮ
     */
    public UsersQrCodeWfInfoDO getById(String userId);

    /**
     * �������ж�ά��������ˮ��¼
     * 
     * @param userId    ��ԱID
     * @return          ��ά��������ˮ����ģ��
     */
    public UsersQrCodeWfInfoDO getByIdForUpdate(String userId);

    /**
     * ���¼�¼�����ʱ�䣬��������60��
     * 
     * @param userId    ��ԱID
     */
    public void updateDateByUserid(String userId);

    /**
     * ɾ����ά����ʱ��ˮ
     * 
     * @param userId    ��ԱID
     * @return          ������
     */
    public int delete(String userId);
}
