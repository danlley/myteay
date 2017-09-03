/**
 * Myteay.com Inc.
 * Copyright (c) 2005-2017 All Rights Reserved.
 */
package com.myteay.core.model.user.enums;

import com.myteay.common.util.comm.StringUtils;

/**
 * �û��ֻ�������Ϣ��Դ����ö��
 * 
 * @author danlley
 * @version $Id: MtUserMobileBaseInfoTypeEnum.java, v 0.1 Sep 3, 2017 6:20:57 PM danlley Exp $
 */
public enum MtUserMobileBaseInfoTypeEnum {

                                          /** ͨ��ע���ȡ */
                                          CS_USER_REG("CS_USER_REG", "ͨ��ע���ȡ"),

                                          /** ͨ����½��ȡ */
                                          CS_USER_LOGIN("CS_USER_LOGIN", "ͨ����½��ȡ"),

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
    private MtUserMobileBaseInfoTypeEnum(String value, String message) {
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
     * ͨ��ö��<code>value</code>���ö��
     * 
     * @param value
     * @return
     */
    public static MtUserMobileBaseInfoTypeEnum getByValue(String value) {
        if (value == null) {
            return null;
        }
        for (MtUserMobileBaseInfoTypeEnum result : values()) {
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
    public static MtUserMobileBaseInfoTypeEnum getByMessage(String message) {
        if (StringUtils.isBlank(message)) {
            return null;
        }
        for (MtUserMobileBaseInfoTypeEnum result : values()) {
            if (result.getMessage().equals(message)) {
                return result;
            }
        }
        return null;
    }
}
