/**
 * Myteay.com Inc.
 * Copyright (c) 2005-2017 All Rights Reserved.
 */
package com.myteay.core.model.user.enums;

import org.apache.commons.lang.StringUtils;

/**
 * 用户手机基本信息来源类型枚举
 * 
 * @author danlley
 * @version $Id: MtUserMobileBaseInfoTypeEnum.java, v 0.1 Sep 3, 2017 6:20:57 PM danlley Exp $
 */
public enum MtUserMobileBaseInfoTypeEnum {

                                          /** 通过注册获取 */
                                          CS_USER_REG("CS_USER_REG", "通过注册获取"),

                                          /** 通过登陆获取 */
                                          CS_USER_LOGIN("CS_USER_LOGIN", "通过登陆获取"),

    ;
    /** value */
    private final String value;

    /** message */
    private final String message;

    /**
     * 私有构造方法
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
     * 通过枚举<code>value</code>获得枚举
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
     * 通过枚举<code>name</code>获得枚举
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
