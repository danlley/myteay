/**
 * Myteay.com Inc.
 * Copyright (c) 2015-2016 All Rights Reserved.
 */
package com.myteay.core.service.cache.components;

import com.myteay.common.service.facade.enums.MtSysKeyEnum;

/**
 * 系统字典缓存组件
 * 
 * @author Administrator
 * @version $Id: MtSysKeysCacheComponents.java, v 0.1 2016年9月7日 上午12:17:38 Administrator Exp $
 */
public interface MtSysKeysCacheComponents {

    /**
     * 通过关键字得到对应的键值
     * 
     * @param key   系统关键字
     * @return      键值
     */
    public String getValueByKey(MtSysKeyEnum key);

}
