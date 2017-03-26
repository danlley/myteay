/**
 * Myteay.com Inc.
 * Copyright (c) 2015-2016 All Rights Reserved.
 */
package com.myteay.core.model.dinner.repository;

import java.util.List;

import com.myteay.common.service.facade.model.MtOperateResult;
import com.myteay.core.model.dinner.MtGoodsPkgModel;

/**
 * �ײ���Ϣ����ִ���
 * 
 * @author Administrator
 * @version $Id: MtGoodsPkgInfoRepository.java, v 0.1 2016��3��5�� ����9:31:33 Administrator Exp $
 */
public interface MtGoodsPkgInfoRepository {

    /**
     * ͨ���ײ���ˮ��ɾ���ײ���Ϣ
     * 
     * @param id    �ײ���ˮ��
     * @return      ������
     */
    public MtOperateResult<String> deleteGoodsPkgById(String id);

    /**
     * ͨ���ײ�ID�����ײ�ģ��
     * 
     * @param id    �ײ�ID
     * @return      �ײ�ģ��
     */
    public MtOperateResult<MtGoodsPkgModel> findGoodsInfoById(String id);

    /**
     * �洢�ײ���Ϣģ��
     * 
     * @param model �洢�ײ���Ϣģ��
     * @return      �����洢�ײ���Ϣ��ˮ�ż���ϸ���ؽ��
     */
    public MtOperateResult<String> saveGoodsPkgModel(MtGoodsPkgModel model);

    /**
     * ��ѯ���е��ײ���Ϣ
     * 
     * @return  �����ײ���Ϣ�б�
     */
    public MtOperateResult<List<MtGoodsPkgModel>> queryGoodsPkgList();
}
