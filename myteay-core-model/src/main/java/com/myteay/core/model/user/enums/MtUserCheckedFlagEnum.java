/**
 * Myteay.com Inc.
 * Copyright (c) 2015-2015 All Rights Reserved.
 */
package com.myteay.core.model.user.enums;

import org.apache.commons.lang.StringUtils;

/**
 * 会员状态枚举
 * 
 * @author Administrator
 * @version $Id: MtUserCheckedFlagEnum.java, v 0.1 2015年11月15日 下午6:10:16 Administrator Exp $
 */
public enum MtUserCheckedFlagEnum {

                                   /** 会员已注册，没有高级认证信息 */
                                   CS_UN_CHECK_ED("CS_UN_CHECK_ED", "会员已注册，没有高级认证信息"),

                                   /** 认证用户 */
                                   CS_CHECKED_USER("CS_CHECKED_USER", "认证用户"),

                                   /** 未知用户 */
                                   CS_UNKNOW_USER("CS_UNKNOW_USER", "未知用户"),

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
     * 通过值获取枚举对象
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
     * 通过枚举<code>value</code>获得枚举
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
     * 通过枚举<code>name</code>获得枚举
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
