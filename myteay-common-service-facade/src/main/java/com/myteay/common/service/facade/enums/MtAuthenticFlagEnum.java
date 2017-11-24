/**
 * Myteay.com Inc.
 * Copyright (c) 2015-2016 All Rights Reserved.
 */
package com.myteay.common.service.facade.enums;

import org.apache.commons.lang.StringUtils;

/**
 * 用于安全邮箱及安全手机的认证状态标识
 * 
 * @author Administrator
 * @version $Id: MtAuthenticFlagEnum.java, v 0.1 2016年2月24日 下午10:11:20 Administrator Exp $
 */
public enum MtAuthenticFlagEnum {

                                 /** 认证用户 */
                                 CS_CHECKED_FLAG("CS_CHECKED_FLAG", "已认证"),

                                 /** 未认证 */
                                 CS_UNCHECKED_FLAG("CS_UNCHECKED_FLAG", "未认证"),

                                 /** 未补全 */
                                 CS_UNSUBMIT_FLAG("CS_UNSUBMIT_FLAG", "未补全"),

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
     * 通过值获取枚举对象
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
     * 通过枚举<code>value</code>获得枚举
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
     * 通过枚举<code>name</code>获得枚举
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
