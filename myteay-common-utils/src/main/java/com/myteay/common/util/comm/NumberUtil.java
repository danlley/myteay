/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2011 All Rights Reserved.
 */
package com.myteay.common.util.comm;

import org.apache.commons.lang.StringUtils;

/**
 * 数字转换工具
 *
 * @author haiping.gong
 * @version $Id: NumberUtil.java, v 0.1 2011-2-18 下午03:47:32 haiping.gong Exp $
 */
public class NumberUtil {

    public static int convertString2Int(String str, int defaultValue) {
        if (StringUtils.isNotBlank(str)) {
            try {
                return Integer.parseInt(str);
            } catch (NumberFormatException e) {
                return defaultValue;
            }
        }
        return defaultValue;
    }
}
