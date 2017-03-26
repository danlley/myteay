/**
 * Myteay.com Inc.
 * Copyright (c) 2015-2016 All Rights Reserved.
 */
package com.myteay.core.model.repository;

import java.util.Map;

/**
 * 用户字典仓储
 * 
 * @author Administrator
 * @version $Id: MtUserSysDictInfoRepository.java, v 0.1 2016年9月7日 上午12:40:19 Administrator Exp $
 */
public interface MtUserSysDictInfoRepository {

    /**
     * 获取所有的字典信息
     * 
     * @return
     */
    public Map<String, String> findAll();
}
