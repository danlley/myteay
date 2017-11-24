/**
 * Myteay.com Inc.
 * Copyright (c) 2015-2016 All Rights Reserved.
 */
package com.myteay.common.service.facade.enums;

import org.apache.commons.lang.StringUtils;

/**
 * 系统字典关键字
 * 
 * @author Administrator
 * @version $Id: MtSysKeyEnum.java, v 0.1 2016年9月6日 下午11:52:41 Administrator Exp $
 */
public enum MtSysKeyEnum {

                          /** 系统工作根路径 */
                          CS_SYS_ROOT_PATH("CS_SYS_ROOT_PATH", "系统工作根路径"),

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
    private MtSysKeyEnum(String value, String message) {
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
    public static MtSysKeyEnum getByCode(String value) {
        if (StringUtils.isBlank(value)) {
            return null;
        }
        value = value.trim();
        for (MtSysKeyEnum type : values()) {
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
    public static MtSysKeyEnum getByValue(String value) {
        if (value == null) {
            return null;
        }
        for (MtSysKeyEnum result : values()) {
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
    public static MtSysKeyEnum getByMessage(String message) {
        if (StringUtils.isBlank(message)) {
            return null;
        }
        for (MtSysKeyEnum result : values()) {
            if (result.getMessage().equals(message)) {
                return result;
            }
        }
        return null;
    }
}
