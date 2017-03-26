/**
 * Myteay.com Inc.
 * Copyright (c) 2015-2016 All Rights Reserved.
 */
package com.myteay.core.model.dinner.repository;

import java.util.List;

import com.myteay.common.service.facade.model.MtOperateResult;
import com.myteay.core.model.dinner.MtShopModel;

/**
 * ������Ϣ�ִ���
 * 
 * @author Administrator
 * @version $Id: MtShopInfoRepository.java, v 0.1 2016��3��4�� ����5:13:37 Administrator Exp $
 */
public interface MtShopInfoRepository {

    /**
     * ��ѯ���еĵ�����Ϣ
     * 
     * @return  ���ص�����Ϣ�б�
     */
    public MtOperateResult<List<MtShopModel>> queryShopList();
}
