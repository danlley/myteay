/**
 * Myteay.com Inc.
 * Copyright (c) 2015-2016 All Rights Reserved.
 */
package com.myteay.common.service.facade.enums;

import com.myteay.common.util.comm.StringUtils;

/**
 * 用户状态枚举
 * 
 * @author Administrator
 * @version $Id: MtUserFlagEnum.java, v 0.1 2016年2月24日 下午10:05:44 Administrator Exp $
 */
public enum MtUserFlagEnum {
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
     * 通过值获取枚举对象
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
     * 通过枚举<code>value</code>获得枚举
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
     * 通过枚举<code>name</code>获得枚举
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
