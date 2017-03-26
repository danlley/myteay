/**
 * Myteay.com Inc.
 * Copyright (c) 2015-2016 All Rights Reserved.
 */
package com.myteay.core.model.repository;

import com.myteay.common.dal.dataobject.UsersQrCodeWfInfoDO;
import com.myteay.common.service.facade.model.MtOperateResult;
import com.myteay.common.utils.exception.MtBizProcessException;

/**
 * 用户二维码生成操作流水
 * 
 * @author Administrator
 * @version $Id: MtUsersQrWfRepository.java, v 0.1 2016年9月7日 上午1:36:04 Administrator Exp $
 */
public interface MtUsersQrWfRepository {

    /**
     * 保存用户注册生成二维码所需的用户信息
     * 
     * @param userId                    会员ID
     * @throws MtBizProcessException    业务处理异常
     */
    public void save(String userId) throws MtBizProcessException;

    /**
     * 锁定注册生成二维码记录
     * 
     * @param userId                    会员ID
     * @return                          注册生成二维码数据模型
     * @throws MtBizProcessException    业务处理异常
     */
    public UsersQrCodeWfInfoDO lock(String userId) throws MtBizProcessException;

    /**
     * 将注册生成二维码记录的最后修改时间从当前时间往前推移60秒
     * 
     * eg. 当前时间为：23点53分，则变更后的修改时间为：23点54分
     * 
     * @param userId                    会员Id
     * @throws MtBizProcessException    业务处理异常
     */
    public void updateLasttime(String userId) throws MtBizProcessException;

    /**
     * 清理二维码生成流水
     * 
     * @param userId    会员ID
     * @return          返回处理结果
     */
    public MtOperateResult<String> removeQrcode(String userId);

}
