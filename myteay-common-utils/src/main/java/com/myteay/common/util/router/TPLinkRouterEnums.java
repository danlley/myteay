/**
 * Myteay.com Inc.
 * Copyright (c) 2015-2016 All Rights Reserved.
 */
package com.myteay.common.util.router;

/**
 * 
 * @author Administrator
 * @version $Id: TPLinkRouterEnums.java, v 0.1 2016��4��29�� ����11:02:01 Administrator Exp $
 */
public enum TPLinkRouterEnums {

    /**  */
    TPLINK_CONN("TPLINK_CONN", "StatusRpm", "Connect", "�� ��", "&wan="),

    /**  */
    TPLINK_DISCONNECT("TPLINK_DISCONNECT", "StatusRpm", "Disconnect", "�� ��", "&wan="),

    /**  */
    TPLINK_RENEW("TPLINK_RENEW", "StatusRpm", "RenewIp", "�� ��", "&wan="),

    /**  */
    TPLINK_RELEASE_IP("TPLINK_RELEASE_IP", "StatusRpm", "ReleaseIp", "�� ��", "&wan="),

    /**  */
    TPLINK_LOGIN("TPLINK_LOGIN", "StatusRpm", "Login", "�� ¼", "&wan="),

    /**  */
    TPLINK_LOGOUT("TPLINK_LOGOUT", "StatusRpm", "Logout", "�� ��", "&wan="),

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
     * ˽�й��췽��
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
     * ͨ��ö��<code>value</code>���ö��
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
