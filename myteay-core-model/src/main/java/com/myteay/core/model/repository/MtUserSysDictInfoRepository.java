/**
 * Myteay.com Inc.
 * Copyright (c) 2015-2016 All Rights Reserved.
 */
package com.myteay.core.model.repository;

import java.util.Map;

/**
 * �û��ֵ�ִ�
 * 
 * @author Administrator
 * @version $Id: MtUserSysDictInfoRepository.java, v 0.1 2016��9��7�� ����12:40:19 Administrator Exp $
 */
public interface MtUserSysDictInfoRepository {

    /**
     * ��ȡ���е��ֵ���Ϣ
     * 
     * @return
     */
    public Map<String, String> findAll();
}
