/**
 * Myteay.com Inc.
 * Copyright (c) 2015-2016 All Rights Reserved.
 */
package com.myteay.core.service.manage.template;

import com.myteay.common.service.facade.enums.MtProcessManageTypeEnum;
import com.myteay.common.utils.exception.MtBizProcessException;

/**
 * 营销通知配置管理组件
 * 
 * @author Administrator
 * @version $Id: MtManageCallback.java, v 0.1 2016年9月7日 下午8:08:08 Administrator Exp $
 */
public abstract class MtManageCallback {

    /**
     * 新增配置数据
     * 
     * @return                              数据保存结果
     * @throws MtBizProcessException     营销核心配置操作异常
     */
    public abstract MtProcessManageTypeEnum process() throws MtBizProcessException;

    /**
     * 追加配置数据
     * 
     * @return                              数据保存结果
     * @throws MtBizProcessException     营销核心配置操作异常
     */
    public MtProcessManageTypeEnum auxiliaryProcess() throws MtBizProcessException {
        return MtProcessManageTypeEnum.CS_MAIN_PROCESS;
    }

    /**
     * 修改配置数据
     * 
     * @throws MtBizProcessException     营销核心配置操作异常
     */
    public MtProcessManageTypeEnum afterProcess() throws MtBizProcessException {
        return MtProcessManageTypeEnum.CS_END;
    }

    /**
     * 配置参数校验
     * 
     * @return                              校验成功则返回true
     * @throws MtBizProcessException     营销核心配置操作异常
     */
    public MtProcessManageTypeEnum validate() throws MtBizProcessException {
        return MtProcessManageTypeEnum.CS_BEFORE_PROCESS;
    }

    /**
     * 缓存刷新动作执行
     */
    public MtProcessManageTypeEnum beforeProcess() throws MtBizProcessException {
        return MtProcessManageTypeEnum.CS_AUXILIARY_PROCESS;
    }

}
