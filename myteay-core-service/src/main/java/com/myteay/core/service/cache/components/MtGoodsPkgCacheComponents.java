/**
 * Myteay.com Inc.
 * Copyright (c) 2015-2016 All Rights Reserved.
 */
package com.myteay.core.service.cache.components;

import com.myteay.common.service.facade.exceptions.MtBizException;

/**
 * �ײͻ�����Ϣִ�����
 * 
 * @author Administrator
 * @version $Id: MtGoodsPkgCacheComponents.java, v 0.1 2016��3��20�� ����9:17:36 Administrator Exp $
 */
public interface MtGoodsPkgCacheComponents {

    /**
     * ����ˢ��
     * 
     * @throws MtBizException
     */
    public void refreshCache() throws MtBizException;
}
