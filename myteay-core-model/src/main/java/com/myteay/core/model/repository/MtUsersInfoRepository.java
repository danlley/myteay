/**
 * Myteay.com Inc.
 * Copyright (c) 2015-2015 All Rights Reserved.
 */
package com.myteay.core.model.repository;

import com.myteay.common.service.facade.model.MtOperateResult;
import com.myteay.common.service.facade.model.MtUserRegQRCodeMessage;
import com.myteay.common.utils.exception.MtBizProcessException;
import com.myteay.common.utils.exception.MtException;
import com.myteay.core.model.user.MtUserModel;

/**
 * �û���Ϣ�ִ���
 * 
 * @author Administrator
 * @version $Id: MtUsersInfoRepository.java, v 0.1 2015��11��15�� ����5:34:42 Administrator Exp $
 */
public interface MtUsersInfoRepository {

    /**
     * �����û���Ϣģ��
     * 
     * @param mtUserModel �û���Ϣģ��
     */
    public MtOperateResult<String> saveUserInfoModel(MtUserModel mtUserModel) throws MtException;

    /**
     * ͨ���û�ID�����û�ģ��
     * 
     * @param userId    �û�ID
     * @return
     */
    public MtOperateResult<MtUserModel> getUserInfoModelById(String userId);

    /**
     * �����û�ע��Ķ�ά����Ϣ
     * 
     * @param mtUserRegQRCodeMessage    ��ά����Ϣ����ģ��
     * @return
     * @throws MtBizProcessException
     */
    public MtOperateResult<MtUserModel> updateUserQrcode(MtUserRegQRCodeMessage mtUserRegQRCodeMessage)
                                                                                                       throws MtBizProcessException;

}
