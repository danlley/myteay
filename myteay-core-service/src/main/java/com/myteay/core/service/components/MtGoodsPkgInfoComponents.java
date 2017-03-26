/**
 * Myteay.com Inc.
 * Copyright (c) 2015-2016 All Rights Reserved.
 */
package com.myteay.core.service.components;

import java.util.List;

import com.myteay.common.service.facade.exceptions.MtBizException;
import com.myteay.common.service.facade.model.MtOperateResult;
import com.myteay.common.service.facade.model.dinner.MtGoodsPkgInfoMessage;
import com.myteay.core.model.dinner.MtGoodsPkgModel;

/**
 * �ײ���Ϣ�������
 * 
 * @author Administrator
 * @version $Id: MtGoodsPkgInfoComponents.java, v 0.1 2016��3��5�� ����9:41:17 Administrator Exp $
 */
public interface MtGoodsPkgInfoComponents {

    /**
     * ͨ���ײ���ˮ��ɾ���ײ���Ϣ
     * 
     * @param id    �ײ���ˮ��
     * @return      ������
     */
    public MtOperateResult<String> deleteGoodsPkgById(String id);

    /**
     * ͨ���ײ���ˮ�Ų�ѯ�ײ���Ϣ��������
     * 
     * @param id    �ײ���ˮ��
     * @return      �ײ���Ϣ��������
     */
    public MtOperateResult<MtGoodsPkgInfoMessage> findGoodsPkgById(String id) throws MtBizException;

    /**
     * �����ײ���Ϣ��������
     * 
     * @param message   �ײ���Ϣ��������
     * @return          ������
     */
    public MtOperateResult<String> saveGoodsPkgMessage(MtGoodsPkgInfoMessage message);

    /**
     * ��ѯ���е��ײ���Ϣ
     * 
     * @return  �����ײ���Ϣ�б�
     */
    public MtOperateResult<List<MtGoodsPkgModel>> queryGoodsPkgList() throws MtBizException;
}
