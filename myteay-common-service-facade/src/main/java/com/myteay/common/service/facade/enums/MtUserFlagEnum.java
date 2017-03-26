/**
 * Myteay.com Inc.
 * Copyright (c) 2015-2016 All Rights Reserved.
 */
package com.myteay.common.service.facade.enums;

import com.myteay.common.util.comm.StringUtils;

/**
 * �û�״̬ö��
 * 
 * @author Administrator
 * @version $Id: MtUserFlagEnum.java, v 0.1 2016��2��24�� ����10:05:44 Administrator Exp $
 */
public enum MtUserFlagEnum {
    /** ��Ա��ע�ᣬû�и߼���֤��Ϣ */
    CS_UN_CHECK_ED("CS_UN_CHECK_ED", "��Ա��ע�ᣬû�и߼���֤��Ϣ"),

    /** ��֤�û� */
    CS_CHECKED_USER("CS_CHECKED_USER", "��֤�û�"),

    /** δ֪�û� */
    CS_UNKNOW_USER("CS_UNKNOW_USER", "δ֪�û�"),

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
    private MtUserFlagEnum(String value, String message) {
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
    public static MtUserFlagEnum getByCode(String value) {
        if (StringUtils.isBlank(value)) {
            return null;
        }
        value = value.trim();
        for (MtUserFlagEnum type : values()) {
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
    public static MtUserFlagEnum getByValue(String value) {
        if (value == null) {
            return null;
        }
        for (MtUserFlagEnum result : values()) {
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
    public static MtUserFlagEnum getByMessage(String message) {
        if (StringUtils.isBlank(message)) {
            return null;
        }
        for (MtUserFlagEnum result : values()) {
            if (result.getMessage().equals(message)) {
                return result;
            }
        }
        return null;
    }
}
