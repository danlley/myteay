/**
 * Myteay.com Inc.
 * Copyright (c) 2015-2016 All Rights Reserved.
 */
package com.myteay.common.util.enums;

import org.apache.commons.lang.StringUtils;

/**
 * 消息事件主题
 * 
 * @author Administrator
 * @version $Id: MtEventTopicEnum.java, v 0.1 2016年3月20日 下午9:57:48 Administrator Exp $
 */
public enum MtEventTopicEnum {

                              /** 套餐缓存刷新事件 */
                              MT_CACHE_GOODS_PKG_FRESH("MT_CACHE_GOODS_PKG_FRESH", "套餐缓存刷新事件"),

                              /** 用户二维码注册生成事件 */
                              MT_USR_QR_CODE_REGISTERY("MT_USR_QR_CODE_REGISTERY", "用户二维码注册生成事件"),

                              /** 用户联系人列表 */
                              MT_USR_CONTACT_LIST("MT_USR_CONTACT_LIST", "用户联系人列表"),

                              /** 用户注册扩展信息异步事件 */
                              MT_USR_REG_EXT_EVENT("MT_USR_REG_EXT_EVENT", "用户注册扩展信息异步事件"),

                              /** 用户注册手机信息异步事件 */
                              MT_USR_REG_MOBILE_INFO_EVENT("MT_USR_REG_MOBILE_INFO_EVENT", "用户注册手机信息异步事件"),

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
    private MtEventTopicEnum(String value, String message) {
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
    public static MtEventTopicEnum getByCode(String value) {
        if (StringUtils.isBlank(value)) {
            return null;
        }
        value = value.trim();
        for (MtEventTopicEnum type : values()) {
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
    public static MtEventTopicEnum getByValue(String value) {
        if (value == null) {
            return null;
        }
        for (MtEventTopicEnum result : values()) {
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
    public static MtEventTopicEnum getByMessage(String message) {
        if (StringUtils.isBlank(message)) {
            return null;
        }
        for (MtEventTopicEnum result : values()) {
            if (result.getMessage().equals(message)) {
                return result;
            }
        }
        return null;
    }
}
