/**
 * Myteay.com Inc.
 * Copyright (c) 2015-2015 All Rights Reserved.
 */
package com.myteay.core.model.user.enums;

import com.myteay.common.util.comm.StringUtils;

/**
 * ��Ա״̬ö��
 * 
 * @author Administrator
 * @version $Id: MtUserCheckedFlagEnum.java, v 0.1 2015��11��15�� ����6:10:16 Administrator Exp $
 */
public enum MtUserCheckedFlagEnum {

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
    private MtUserCheckedFlagEnum(String value, String message) {
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
    public static MtUserCheckedFlagEnum getByCode(String value) {
        if (StringUtils.isBlank(value)) {
            return null;
        }
        value = value.trim();
        for (MtUserCheckedFlagEnum type : values()) {
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
    public static MtUserCheckedFlagEnum getByValue(String value) {
        if (value == null) {
            return null;
        }
        for (MtUserCheckedFlagEnum result : values()) {
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
    public static MtUserCheckedFlagEnum getByMessage(String message) {
        if (StringUtils.isBlank(message)) {
            return null;
        }
        for (MtUserCheckedFlagEnum result : values()) {
            if (result.getMessage().equals(message)) {
                return result;
            }
        }
        return null;
    }
}
