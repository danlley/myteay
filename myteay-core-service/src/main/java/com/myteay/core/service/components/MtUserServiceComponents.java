/**
 * Myteay.com Inc.
 * Copyright (c) 2015-2016 All Rights Reserved.
 */
package com.myteay.core.service.components;

import com.myteay.common.service.facade.exceptions.MtBizException;
import com.myteay.common.service.facade.model.MtOperateResult;
import com.myteay.common.service.facade.model.MtUserMessage;

/**
 * 用户处理核心服务接口
 * 
 * @author Administrator
 * @version $Id: MtUserServiceComponents.java, v 0.1 2016年2月27日 下午11:05:06 Administrator Exp $
 */
public interface MtUserServiceComponents {

    /**
     * 注册新用户
     * 
     * @param message           用户交互信息
     * @return                  系统处理结果
     * @throws MtBizException   系统异常
     */
    public MtOperateResult<String> userRegistery(MtUserMessage message) throws MtBizException;

    /**
     * 根据userId查询单个用户信息
     * 
     * @param userId            会员ID
     * @return                  查询结果
     * @throws MtBizException   异常情况
     */
    public MtOperateResult<MtUserMessage> findSingleUserInfo(String userId) throws MtBizException;
}
