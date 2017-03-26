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
 * ��ԱID���ɹ���
 * 
 * @author Administrator
 * @version $Id: UIDGener.java, v 0.1 2015��7��5�� ����9:13:47 Administrator Exp $
 */
public class UIDGener {

    /** ��ԱIDǰ׺ */
    public static final String              UID_FRONT             = "MT";

    /** �����루Ĭ���й��� */
    public static final String              NATION_CODE           = "CN";

    /** ��Ա����ʡ����Ϣ���� */
    public static final Map<String, String> PROVINCE_LOCAL_CACHE  = Collections
        .synchronizedMap(new HashMap<String, String>());

    /** ʡ��Ĭ�ϴ��� */
    public static final String              DEFAULT_PRIVINCE_CODE = "000000";

    /**
     * �����ʼ������(���۵Ĳ�������ʽ)
     * 
     * @param params    ʡ�ݼ��ʱ���Ϣ
     */
    public synchronized void initPrivinceCache(Map<String, String> params) {
        synchronized (PROVINCE_LOCAL_CACHE) {
            PROVINCE_LOCAL_CACHE.putAll(params);
        }
    }

    /**
     * ˢ�µ���ʡ�ݵĻ�����Ϣ���ֹ۵Ĳ�������ʽ��
     * 
     * @param key   ʡ������
     * @param value ��������
     */
    public static void freshSingleCache(String key, String value) {
        synchronized (PROVINCE_LOCAL_CACHE) {
            PROVINCE_LOCAL_CACHE.put(key, value);
        }
    }

    /**
     * ��ȡ��ǰ�û���UID
     * 
     * ====================================================================================================<br>
     * USERID���ɹ���  UID_FRONT + NATION_CODE + PROVINCE_LOCAL_CACHE + ��ǰʱ�䣨��ȷ�����룩 + 4λ���������<br>
     *             �磺 MT          CN            741200                 2015 07 05 21 35 48 555      1234<br>
     *       �ϳɺ�Ϊ��  MTCN741200201507052135485551234<br>MTCN741200    2015 07 06 00 56 31 726      0000
     * ====================================================================================================<br>
     * 
     * @return  ���ص�ǰ�û���Ҫ���ɵ�userid
     */
    public static String genUserId(String privince, String randomString) throws MyTeayException {

        if (StringUtils.isBlank(privince) || StringUtils.isBlank(randomString)) {
            throw new MyTeayException();
        }
        freshSingleCache("�ʹ���", "741200");
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
