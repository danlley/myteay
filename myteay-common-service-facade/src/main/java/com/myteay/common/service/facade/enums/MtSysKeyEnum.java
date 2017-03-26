/**
 * Myteay.com Inc.
 * Copyright (c) 2015-2016 All Rights Reserved.
 */
package com.myteay.common.service.facade.enums;

import com.myteay.common.util.comm.StringUtils;

/**
 * ϵͳ�ֵ�ؼ���
 * 
 * @author Administrator
 * @version $Id: MtSysKeyEnum.java, v 0.1 2016��9��6�� ����11:52:41 Administrator Exp $
 */
public enum MtSysKeyEnum {

    /** ��ά�����ɺ�Ĵ��·�� */
    CS_CURR_QRCODE_PATH("CS_CURR_QRCODE_PATH", "��ά�����ɺ�Ĵ��·��"),

    /** ��ά������Ĭ��ͼƬ���·�� */
    CS_QRCODE_DEFIMG_PATH("CS_QRCODE_DEFIMG_PATH", "��ά������Ĭ��ͼƬ���·��"),

    /** ϵͳ������·�� */
    CS_SYS_ROOT_PATH("CS_SYS_ROOT_PATH", "ϵͳ������·��"),

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
     * ͨ��ֵ��ȡö�ٶ���
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
     * ͨ��ö��<code>value</code>���ö��
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
     * ͨ��ö��<code>name</code>���ö��
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
