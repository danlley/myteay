/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2012 All Rights Reserved.
 */
package com.myteay.common.util.event;

import com.myteay.common.utils.exception.MtException;

/**
 * 异步事件执行服务（默认支持同步）
 * 
 * @author min.weixm
 * @version $Id: EventPulishService.java, v 0.1 2012-11-3 下午09:00:54 min.weixm Exp $
 */
public interface EventPulishService<T> {

    /**
     * 发布事件
     * 
     * @param event     异步事件模型
     */
    public T publishEvent(MtEvent<?> event) throws MtException;

}
