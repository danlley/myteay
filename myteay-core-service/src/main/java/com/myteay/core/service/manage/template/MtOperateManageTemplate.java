/**
 * Myteay.com Inc.
 * Copyright (c) 2015-2016 All Rights Reserved.
 */
package com.myteay.core.service.manage.template;

import com.myteay.common.service.facade.enums.MtProcessManageTypeEnum;
import com.myteay.common.service.facade.model.MtOperateResult;

/**
 * 操作执行模板<br><br>
 * 1、事务处理模板：
 * <ul>
 *      <li>为确保事务一致性，事务处理模板应尽量放在业务操作的入库进行接入</li>
 * </ul>
 * 
 * 2、不带事务的处理模板使用场景：
 * <ul>
 *      <li>事务控制已经在上层完全控制，但操作类型依然有事前、事中、事后，需要根据操作类型区别处理</li>
 * </ul>
 * 
 * @author Administrator
 * @version $Id: MtOperateManageTemplate.java, v 0.1 2016年9月7日 下午8:06:31 Administrator Exp $
 */
public interface MtOperateManageTemplate {

    /**
     * 操作执行模板
     * 
     * @param manageType        操作类型
     * @param obj               操作数据
     * @param callback          操作回调抽象类实例
     * @return                  操作结果
     */
    public MtOperateResult<String> execute(MtProcessManageTypeEnum manageType, Object obj,
                                           MtManageCallback callback);

    /**
     * 不带事务处理的操作模板
     * 
     * @param manageType        操作类型
     * @param obj               操作数据
     * @param callback          操作回调抽象类实例
     * @return                  执行结果
     */
    public MtOperateResult<String> executeWithOutTrans(final MtProcessManageTypeEnum manageType,
                                                       final Object obj,
                                                       final MtManageCallback callback);
}
