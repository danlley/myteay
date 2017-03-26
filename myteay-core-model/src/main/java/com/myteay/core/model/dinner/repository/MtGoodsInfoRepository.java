/**
 * Myteay.com Inc.
 * Copyright (c) 2015-2016 All Rights Reserved.
 */
package com.myteay.core.model.dinner.repository;

import java.util.List;

import com.myteay.common.service.facade.model.MtOperateResult;
import com.myteay.core.model.dinner.MtGoodsModel;

/**
 * ��Ʒ��Ϣ��Ʒ����ִ���
 * 
 * @author Administrator
 * @version $Id: MtGoodsInfoRepository.java, v 0.1 2016��3��5�� ����12:24:56 Administrator Exp $
 */
public interface MtGoodsInfoRepository {
    /**
     * ͨ��IDɾ��ָ����Ʒ��Ϣ
     * 
     * @param id    ��ƷID
     * @return      ɾ�����
     */
    public MtOperateResult<String> removeGoodsInfoById(String id);

    /**
     * ͨ��ID����ָ����Ʒ��Ϣ
     * 
     * @param id    ��Ʒ��Ϣ��ˮ��
     * @return      ��Ʒ��Ϣģ��
     */
    public MtOperateResult<MtGoodsModel> findGoodsInfoById(String id);

    /**
     * ���浥Ʒ��Ʒ��Ϣ
     * 
     * @param model ��Ʒ��Ϣģ��
     * @return      ������
     */
    public MtOperateResult<String> saveGoodsInfo(MtGoodsModel model);

    /**
     * ��ѯ���еĵ�Ʒ��Ϣ
     * 
     * @return  ���ص�Ʒ��Ϣ�б�
     */
    public MtOperateResult<List<MtGoodsModel>> queryGoodsList();
}
