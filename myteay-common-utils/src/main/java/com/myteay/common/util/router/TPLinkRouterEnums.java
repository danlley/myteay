/**
 * Myteay.com Inc.
 * Copyright (c) 2015-2016 All Rights Reserved.
 */
package com.myteay.common.util.router;

/**
 * 
 * @author Administrator
 * @version $Id: TPLinkRouterEnums.java, v 0.1 2016年4月29日 下午11:02:01 Administrator Exp $
 */
public enum TPLinkRouterEnums {

    /**  */
    TPLINK_CONN("TPLINK_CONN", "StatusRpm", "Connect", "连 接", "&wan="),

    /**  */
    TPLINK_DISCONNECT("TPLINK_DISCONNECT", "StatusRpm", "Disconnect", "断 线", "&wan="),

    /**  */
    TPLINK_RENEW("TPLINK_RENEW", "StatusRpm", "RenewIp", "更 新", "&wan="),

    /**  */
    TPLINK_RELEASE_IP("TPLINK_RELEASE_IP", "StatusRpm", "ReleaseIp", "释 放", "&wan="),

    /**  */
    TPLINK_LOGIN("TPLINK_LOGIN", "StatusRpm", "Login", "登 录", "&wan="),

    /**  */
    TPLINK_LOGOUT("TPLINK_LOGOUT", "StatusRpm", "Logout", "退 出", "&wan="),

    ;
    /** value */
    private final String value;

    /** message */
    private final String model;

    /** message */
    private final String type;

    /** message */
    private final String typeValue;

    /** message */
    private final String params;

    /**
     * 私有构造方法
     * @param code
     * @param description
     */
    private TPLinkRouterEnums(String value, String model, String type, String typeValue,
                              String params) {
        this.value = value;
        this.model = model;
        this.type = type;
        this.typeValue = typeValue;
        this.params = params;
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
        return model;
    }

    /**
     * 通过枚举<code>value</code>获得枚举
     * 
     * @param value
     * @return
     */
    public static TPLinkRouterEnums getByValue(String value) {
        if (value == null) {
            return null;
        }
        for (TPLinkRouterEnums result : values()) {
            if (result.getValue().equals(value)) {
                return result;
            }
        }
        return null;
    }

}
