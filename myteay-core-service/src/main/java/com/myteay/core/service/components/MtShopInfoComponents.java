/**
 * Myteay.com Inc.
 * Copyright (c) 2015-2016 All Rights Reserved.
 */
package com.myteay.core.service.components;

import java.util.List;

import com.myteay.common.service.facade.exceptions.MtBizException;
import com.myteay.common.service.facade.model.MtOperateResult;
import com.myteay.core.model.dinner.MtShopModel;

/**
 * ���̹������
 * 
 * @author Administrator
 * @version $Id: MtShopInfoComponents.java, v 0.1 2016��3��4�� ����5:19:21 Administrator Exp $
 */
public interface MtShopInfoComponents {

    /**
     * ��ѯ���еĵ�����Ϣ
     * 
     * @return  ���ص�����Ϣ�б�
     */
    public MtOperateResult<List<MtShopModel>> queryShopList() throws MtBizException;
}
