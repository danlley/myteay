/**
 * Myteay.com Inc.
 * Copyright (c) 2015-2016 All Rights Reserved.
 */
package com.myteay.common.service.facade.model;

import com.myteay.common.util.comm.StringUtils;

/**
 * ���ڰ�ȫ���估��ȫ�ֻ�����֤״̬��ʶ
 * 
 * @author Administrator
 * @version $Id: MtAuthenticFlagEnum.java, v 0.1 2016��2��24�� ����10:11:20 Administrator Exp $
 */
public enum MtAuthenticFlagEnum {

    /** ��֤�û� */
    CS_CHECKED_FLAG("CS_CHECKED_FLAG", "����֤"),

    /** δ��֤ */
    CS_UNCHECKED_FLAG("CS_UNCHECKED_FLAG", "δ��֤"),

    /** δ��ȫ */
    CS_UNSUBMIT_FLAG("CS_UNSUBMIT_FLAG", "δ��ȫ"),

    ;
    /** value */
    private final String value;

    /** message */
    private final String message;

    /**
     * ˽�й��췽��
     * @param code
     * @param description
     */
    private MtAuthenticFlagEnum(String value, String message) {
        this.value = value;
        this.message = message;
    }

    /**
     * @return Returns the value.
     */
    public String getValue() {
        return value;
    }

    /**
     * @return Returns the message.
     */
    public String getMessage() {
        return message;
    }

    /**
     * ͨ��ֵ��ȡö�ٶ���
     * @param value
     * @return
     */
    public static MtAuthenticFlagEnum getByCode(String value) {
        if (StringUtils.isBlank(value)) {
            return null;
        }
        value = value.trim();
        for (MtAuthenticFlagEnum type : values()) {
            if (type.getValue().equals(value))
                return type;
        }
        return null;

    }

    /**
     * ͨ��ö��<code>value</code>���ö��
     * 
     * @param value
     * @return
     */
    public static MtAuthenticFlagEnum getByValue(String value) {
        if (value == null) {
            return null;
        }
        for (MtAuthenticFlagEnum result : values()) {
            if (result.getValue().equals(value)) {
                return result;
            }
        }
        return null;
    }

    /**
     * ͨ��ö��<code>name</code>���ö��
     * 
     * @param message
     * @return
     */
    public static MtAuthenticFlagEnum getByMessage(String message) {
        if (StringUtils.isBlank(message)) {
            return null;
        }
        for (MtAuthenticFlagEnum result : values()) {
            if (result.getMessage().equals(message)) {
                return result;
            }
        }
        return null;
    }
}
