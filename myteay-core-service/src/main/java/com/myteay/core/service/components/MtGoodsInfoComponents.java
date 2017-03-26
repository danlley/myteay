/**
 * Myteay.com Inc.
 * Copyright (c) 2015-2016 All Rights Reserved.
 */
package com.myteay.core.service.components;

import java.util.List;

import com.myteay.common.service.facade.exceptions.MtBizException;
import com.myteay.common.service.facade.model.MtOperateResult;
import com.myteay.common.service.facade.model.dinner.MtGoodsInfoMessage;
import com.myteay.core.model.dinner.MtGoodsModel;

/**
 * ��Ʒ��Ϣ�������
 * 
 * @author Administrator
 * @version $Id: MtGoodsInfoComponents.java, v 0.1 2016��3��5�� ����12:54:07 Administrator Exp $
 */
public interface MtGoodsInfoComponents {

    /**
     * ͨ��IDɾ��ָ����Ʒ��Ϣ
     * 
     * @param id    ��ƷID
     * @return      ɾ�����
     */
    public MtOperateResult<String> removeGoodsInfoById(String id);

    /**
     * ͨ���ײ���ˮ�Ų����ײ�����
     * 
     * @param id    �ײ���ˮ��
     * @return      �ײ���Ϣ��������
     */
    public MtOperateResult<MtGoodsInfoMessage> findGoodsInfoById(String id);

    /**
     * ������Ʒ��Ʒ��Ϣ
     * 
     * @param message   ��Ʒ��Ϣģ��
     * @return          ������
     */
    public MtOperateResult<String> saveGoodsInfo(MtGoodsInfoMessage message);

    /**
     * ��ѯ���е���Ʒ��Ϣ
     * 
     * @return  ������Ʒ��Ϣ�б�
     */
    public MtOperateResult<List<MtGoodsModel>> queryGoodsList() throws MtBizException;
}
