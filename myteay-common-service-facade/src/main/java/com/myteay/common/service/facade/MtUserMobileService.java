/**
 * Myteay.com Inc.
 * Copyright (c) 2005-2017 All Rights Reserved.
 */
package com.myteay.common.service.facade;

import com.myteay.common.service.facade.mobile.info.MtRegisterInfo;
import com.myteay.common.service.facade.results.MtServiceResult;

/**
 * �û��ֻ��˷������
 * 
 * @author danlley
 * @version $Id: MtUserMobileService.java, v 0.1 Aug 31, 2017 9:10:01 AM danlley Exp $
 */
public interface MtUserMobileService {

    /**
     * �û�ע�����ӿڣ���MtRegisterInfo��Ϣ����ע�ᴦ��󣬽�ע����userid�Ͷ�ά����ֵ��Ϣ���л�����ػ�����
     * 
     * @param registerInfo  ע�����󽻻�����
     * @return              ע����������ɺ�Ļ�����
     */
    public MtServiceResult<MtRegisterInfo> register(MtRegisterInfo registerInfo);
}
