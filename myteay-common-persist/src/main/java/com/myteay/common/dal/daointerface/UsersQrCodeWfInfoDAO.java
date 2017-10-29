/**
 * Myteay.com Inc.
 * Copyright (c) 2015-2016 All Rights Reserved.
 */
package com.myteay.common.dal.daointerface;

import java.util.List;

import com.myteay.common.dal.dataobject.UsersQrCodeWfInfoDO;
import com.myteay.common.util.constants.MtDBKey;

/**
 * 二维码生成流水操作DAO
 * 
 * @author Administrator
 * @version $Id: UsersQrCodeWfInfoDAO.java, v 0.1 2016年9月7日 上午1:24:25 Administrator Exp $
 */
public interface UsersQrCodeWfInfoDAO extends MtDBKey {

    /**
     * 二维码生成流水落地
     * 
     * @param usersQrCodeWfInfoDO   二维码生成流水数据模型
     * @return
     */
    public String insert(UsersQrCodeWfInfoDO usersQrCodeWfInfoDO);

    /**
     * 单次最多查询100条数据
     * 
     * @return  二维码生成流水列表
     */
    public List<UsersQrCodeWfInfoDO> getByLimit();

    /**
     * 通过userId查询并锁定特定记录防止被其他线程处理
     * 
     * @param userId    会员ID
     * @return          二维码生成流水
     */
    public UsersQrCodeWfInfoDO getById(String userId);

    /**
     * 锁定单行二维码生成流水记录
     * 
     * @param userId    会员ID
     * @return          二维码生成流水数据模型
     */
    public UsersQrCodeWfInfoDO getByIdForUpdate(String userId);

    /**
     * 更新记录的最后时间，往后推移60秒
     * 
     * @param userId    会员ID
     */
    public void updateDateByUserid(String userId);

    /**
     * 删除二维码临时流水
     * 
     * @param userId    会员ID
     * @return          处理结果
     */
    public int delete(String userId);
}
