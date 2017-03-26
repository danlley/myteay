/**
 * Myteay.com Inc.
 * Copyright (c) 2015-2016 All Rights Reserved.
 */
package com.myteay.core.service.components;

import com.myteay.common.service.facade.exceptions.MtBizException;
import com.myteay.common.service.facade.model.MtOperateResult;
import com.myteay.common.service.facade.model.MtUserMessage;

/**
 * �û�������ķ���ӿ�
 * 
 * @author Administrator
 * @version $Id: MtUserServiceComponents.java, v 0.1 2016��2��27�� ����11:05:06 Administrator Exp $
 */
public interface MtUserServiceComponents {

    /**
     * ע�����û�
     * 
     * @param message           �û�������Ϣ
     * @return                  ϵͳ������
     * @throws MtBizException   ϵͳ�쳣
     */
    public MtOperateResult<String> userRegistery(MtUserMessage message) throws MtBizException;

    /**
     * ����userId��ѯ�����û���Ϣ
     * 
     * @param userId            ��ԱID
     * @return                  ��ѯ���
     * @throws MtBizException   �쳣���
     */
    public MtOperateResult<MtUserMessage> findSingleUserInfo(String userId) throws MtBizException;
}
