/**
 * Myteay.com Inc.
 * Copyright (c) 2015-2016 All Rights Reserved.
 */
package com.myteay.core.service.manage.template;

import com.myteay.common.service.facade.enums.MtProcessManageTypeEnum;
import com.myteay.common.utils.exception.MtBizProcessException;

/**
 * Ӫ��֪ͨ���ù������
 * 
 * @author Administrator
 * @version $Id: MtManageCallback.java, v 0.1 2016��9��7�� ����8:08:08 Administrator Exp $
 */
public abstract class MtManageCallback {

    /**
     * ������������
     * 
     * @return                              ���ݱ�����
     * @throws MtBizProcessException     Ӫ���������ò����쳣
     */
    public abstract MtProcessManageTypeEnum process() throws MtBizProcessException;

    /**
     * ׷����������
     * 
     * @return                              ���ݱ�����
     * @throws MtBizProcessException     Ӫ���������ò����쳣
     */
    public MtProcessManageTypeEnum auxiliaryProcess() throws MtBizProcessException {
        return MtProcessManageTypeEnum.CS_MAIN_PROCESS;
    }

    /**
     * �޸���������
     * 
     * @throws MtBizProcessException     Ӫ���������ò����쳣
     */
    public MtProcessManageTypeEnum afterProcess() throws MtBizProcessException {
        return MtProcessManageTypeEnum.CS_END;
    }

    /**
     * ���ò���У��
     * 
     * @return                              У��ɹ��򷵻�true
     * @throws MtBizProcessException     Ӫ���������ò����쳣
     */
    public MtProcessManageTypeEnum validate() throws MtBizProcessException {
        return MtProcessManageTypeEnum.CS_BEFORE_PROCESS;
    }

    /**
     * ����ˢ�¶���ִ��
     */
    public MtProcessManageTypeEnum beforeProcess() throws MtBizProcessException {
        return MtProcessManageTypeEnum.CS_AUXILIARY_PROCESS;
    }

}
