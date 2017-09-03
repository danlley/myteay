/**
 * Myteay.com Inc.
 * Copyright (c) 2005-2017 All Rights Reserved.
 */
package com.myteay.core.model.repository;

import com.myteay.common.service.facade.model.MtOperateResult;
import com.myteay.core.model.user.MtUserContactModel;

/**
 * ��ϵ�˲ִ���
 * 
 * @author danlley
 * @version $Id: MtUserContactListInfoRepository.java, v 0.1 Sep 2, 2017 5:36:22 PM danlley Exp $
 */
public interface MtUserContactListInfoRepository {

    /**
     * ������ϵ����Ϣ
     * 
     * @param mtUserContactModel
     * @return
     */
    public MtOperateResult<String> saveUserContactListInfo(MtUserContactModel mtUserContactModel);
}
