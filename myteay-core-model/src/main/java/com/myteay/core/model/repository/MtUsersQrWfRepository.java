/**
 * Myteay.com Inc.
 * Copyright (c) 2015-2016 All Rights Reserved.
 */
package com.myteay.core.model.repository;

import com.myteay.common.dal.dataobject.UsersQrCodeWfInfoDO;
import com.myteay.common.service.facade.model.MtOperateResult;
import com.myteay.common.utils.exception.MtBizProcessException;

/**
 * �û���ά�����ɲ�����ˮ
 * 
 * @author Administrator
 * @version $Id: MtUsersQrWfRepository.java, v 0.1 2016��9��7�� ����1:36:04 Administrator Exp $
 */
public interface MtUsersQrWfRepository {

    /**
     * �����û�ע�����ɶ�ά��������û���Ϣ
     * 
     * @param userId                    ��ԱID
     * @throws MtBizProcessException    ҵ�����쳣
     */
    public void save(String userId) throws MtBizProcessException;

    /**
     * ����ע�����ɶ�ά���¼
     * 
     * @param userId                    ��ԱID
     * @return                          ע�����ɶ�ά������ģ��
     * @throws MtBizProcessException    ҵ�����쳣
     */
    public UsersQrCodeWfInfoDO lock(String userId) throws MtBizProcessException;

    /**
     * ��ע�����ɶ�ά���¼������޸�ʱ��ӵ�ǰʱ����ǰ����60��
     * 
     * eg. ��ǰʱ��Ϊ��23��53�֣���������޸�ʱ��Ϊ��23��54��
     * 
     * @param userId                    ��ԱId
     * @throws MtBizProcessException    ҵ�����쳣
     */
    public void updateLasttime(String userId) throws MtBizProcessException;

    /**
     * �����ά��������ˮ
     * 
     * @param userId    ��ԱID
     * @return          ���ش�����
     */
    public MtOperateResult<String> removeQrcode(String userId);

}
