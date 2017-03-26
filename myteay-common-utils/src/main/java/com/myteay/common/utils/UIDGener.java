/**
 * Copyright (c) 2004-2015 All Rights Reserved.
 */
package com.myteay.common.utils;

import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import com.myteay.common.util.comm.MyTeayException;
import com.myteay.common.util.comm.StringUtils;

/**
 * 会员ID生成工具
 * 
 * @author Administrator
 * @version $Id: UIDGener.java, v 0.1 2015年7月5日 下午9:13:47 Administrator Exp $
 */
public class UIDGener {

    /** 会员ID前缀 */
    public static final String              UID_FRONT             = "MT";

    /** 国家码（默认中国） */
    public static final String              NATION_CODE           = "CN";

    /** 会员籍贯省份信息缓存 */
    public static final Map<String, String> PROVINCE_LOCAL_CACHE  = Collections
        .synchronizedMap(new HashMap<String, String>());

    /** 省份默认代码 */
    public static final String              DEFAULT_PRIVINCE_CODE = "000000";

    /**
     * 整体初始化缓存(悲观的并发处理方式)
     * 
     * @param params    省份及邮编信息
     */
    public synchronized void initPrivinceCache(Map<String, String> params) {
        synchronized (PROVINCE_LOCAL_CACHE) {
            PROVINCE_LOCAL_CACHE.putAll(params);
        }
    }

    /**
     * 刷新单个省份的缓存信息（乐观的并发处理方式）
     * 
     * @param key   省份名称
     * @param value 邮政编码
     */
    public static void freshSingleCache(String key, String value) {
        synchronized (PROVINCE_LOCAL_CACHE) {
            PROVINCE_LOCAL_CACHE.put(key, value);
        }
    }

    /**
     * 获取当前用户的UID
     * 
     * ====================================================================================================<br>
     * USERID生成规则：  UID_FRONT + NATION_CODE + PROVINCE_LOCAL_CACHE + 当前时间（精确到毫秒） + 4位随机序列码<br>
     *             如： MT          CN            741200                 2015 07 05 21 35 48 555      1234<br>
     *       合成后为：  MTCN741200201507052135485551234<br>MTCN741200    2015 07 06 00 56 31 726      0000
     * ====================================================================================================<br>
     * 
     * @return  返回当前用户需要生成的userid
     */
    public static String genUserId(String privince, String randomString) throws MyTeayException {

        if (StringUtils.isBlank(privince) || StringUtils.isBlank(randomString)) {
            throw new MyTeayException();
        }
        freshSingleCache("甘谷县", "741200");
        String privinceCode = PROVINCE_LOCAL_CACHE.get(privince);
        if (StringUtils.isBlank(privinceCode)) {
            privinceCode = DEFAULT_PRIVINCE_CODE;
        }

        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmssSSS");
        String curr = sdf.format(date);

        return UID_FRONT + NATION_CODE + privinceCode + curr + randomString;
    }

}
