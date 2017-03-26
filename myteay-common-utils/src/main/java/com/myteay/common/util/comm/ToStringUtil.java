/**
 * Copyright (c) 2004-2011 All Rights Reserved.
 */
package com.myteay.common.util.comm;

import java.util.List;
import java.util.Map;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;
import org.springframework.util.CollectionUtils;

/**
 * ʹ��ToStringBuilderʵ�ֵ�ToString�����࣬����û�и�дtoString�����Ķ���
 * @author ren.zhang
 * @version $Id: ToStringUtil.java, v 0.1 2011-5-18 ����02:23:23 ren.zhang Exp $
 */
public class ToStringUtil {

    /**
     * ��ToStringStyle.SHORT_PREFIX_STYLE���ͷ��ز�������ת������ַ���
     * @param object
     * @return
     */
    public static String toShortString(Object object) {
        return ToStringBuilder.reflectionToString(object, ToStringStyle.SHORT_PREFIX_STYLE);
    }

    /**
     * ��ToStringStyle.SHORT_PREFIX_STYLE���ͷ��ز���Listת������ַ���
     * @param list
     * @return
     */
    public static String listToString(List<?> list) {

        StringBuffer buffer = new StringBuffer("List={");
        if (!CollectionUtils.isEmpty(list)) {
            for (Object obj : list) {
                buffer.append(toShortString(obj)).append(", ");
            }
        }
        return buffer.substring(0, buffer.length() - 2) + "}";
    }

    /**
     * ��ToStringStyle.SHORT_PREFIX_STYLE���ͷ��ز���Mapת������ַ���
     * @param map
     * @return
     */
    public static String mapToString(Map<String, ?> map) {
        StringBuffer buffer = new StringBuffer("Map={");
        if (!CollectionUtils.isEmpty(map)) {
            for (String key : map.keySet()) {
                buffer.append("[")
                    .append(key).append("=>").append(ToStringBuilder
                        .reflectionToString(map.get(key), ToStringStyle.SHORT_PREFIX_STYLE))
                    .append("]");
            }
            buffer.append("}");
        }

        return buffer.toString();
    }
}
