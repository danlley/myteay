/**
 * Myteay.com Inc.
 * Copyright (c) 2015-2016 All Rights Reserved.
 */
package com.myteay.core.model.repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import com.myteay.common.dal.daointerface.UserSysDictInfoDAO;
import com.myteay.common.dal.dataobject.UserSysDictInfoDO;
import com.myteay.common.util.comm.CollectionUtils;
import com.myteay.common.util.comm.StringUtils;

/**
 * �û��ֵ�ִ�
 * 
 * @author Administrator
 * @version $Id: MtUserSysDictInfoRepositoryImpl.java, v 0.1 2016��9��7�� ����12:41:31 Administrator Exp $
 */
public class MtUserSysDictInfoRepositoryImpl implements MtUserSysDictInfoRepository {

    /** ��־ */
    public static final Logger logger = Logger.getLogger(MtUserSysDictInfoRepositoryImpl.class);

    /** �û��ֵ����DAO */
    public UserSysDictInfoDAO  mtUserSysDictInfoDAO;

    /** 
     * @see com.myteay.core.model.repository.MtUserSysDictInfoRepository#findAll()
     */
    @Override
    public Map<String, String> findAll() {
        List<UserSysDictInfoDO> list = mtUserSysDictInfoDAO.findAll();

        if (CollectionUtils.isEmpty(list)) {
            logger.warn("ϵͳδ���ص��κ��ֵ���Ϣ������");
            return null;
        }

        Map<String, String> map = new HashMap<String, String>();
        for (UserSysDictInfoDO userSysDictInfoDO : list) {
            if (userSysDictInfoDO == null || StringUtils.isBlank(userSysDictInfoDO.getDicKey())
                || StringUtils.isBlank(userSysDictInfoDO.getDicValue())) {
                continue;
            }

            map.put(userSysDictInfoDO.getDicKey(), userSysDictInfoDO.getDicValue());
        }

        return map;
    }

    /**
     * Setter method for property <tt>mtUserSysDictInfoDAO</tt>.
     * 
     * @param mtUserSysDictInfoDAO value to be assigned to property mtUserSysDictInfoDAO
     */
    public void setMtUserSysDictInfoDAO(UserSysDictInfoDAO mtUserSysDictInfoDAO) {
        this.mtUserSysDictInfoDAO = mtUserSysDictInfoDAO;
    }
}
