/**
 * Myteay.com Inc.
 * Copyright (c) 2015-2016 All Rights Reserved.
 */
package com.myteay.core.service.cache.components;

import com.myteay.common.service.facade.enums.MtSysKeyEnum;

/**
 * ϵͳ�ֵ仺�����
 * 
 * @author Administrator
 * @version $Id: MtSysKeysCacheComponents.java, v 0.1 2016��9��7�� ����12:17:38 Administrator Exp $
 */
public interface MtSysKeysCacheComponents {

    /**
     * ͨ���ؼ��ֵõ���Ӧ�ļ�ֵ
     * 
     * @param key   ϵͳ�ؼ���
     * @return      ��ֵ
     */
    public String getValueByKey(MtSysKeyEnum key);

}
