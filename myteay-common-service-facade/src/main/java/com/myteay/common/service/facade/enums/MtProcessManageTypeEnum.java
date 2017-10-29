/**
 * Myteay.com Inc.
 * Copyright (c) 2015-2016 All Rights Reserved.
 */
package com.myteay.common.service.facade.enums;

import org.apache.commons.lang.StringUtils;

/**
 * ���̴�������������ö��
 * 
 * @author Administrator
 * @version $Id: MtManageTypeEnum.java, v 0.1 2016��9��7�� ����10:52:39 Administrator Exp $
 */
public enum MtProcessManageTypeEnum {

                                     /** ��������ִ�б�� */
                                     CS_AUXILIARY_PROCESS("CS_AUXILIARY_PROCESS", "��������ִ��"),

                                     /** ������ִ�б�� */
                                     CS_MAIN_PROCESS("CS_MAIN_PROCESS", "������ִ��"),

                                     /** ���̽������ɨβ������� */
                                     CS_AFTER_PROCESS("CS_AFTER_PROCESS", "���̽������ɨβ����"),

                                     /** ���̿�ʼǰ��׼��������� */
                                     CS_BEFORE_PROCESS("CS_BEFORE_PROCESS", "���̿�ʼǰ��׼������"),

                                     /** ��֤�������ݱ�� */
                                     CS_VALIDATE_PROCESS("CS_VALIDATE_PROCESS", "��֤��������"),

                                     /**������� */
                                     CS_END("CS_END", "�������"),

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
    private MtProcessManageTypeEnum(String value, String message) {
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
    public static MtProcessManageTypeEnum getByCode(String value) {
        if (StringUtils.isBlank(value)) {
            return null;
        }
        value = value.trim();
        for (MtProcessManageTypeEnum type : values()) {
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
    public static MtProcessManageTypeEnum getByValue(String value) {
        if (value == null) {
            return null;
        }
        for (MtProcessManageTypeEnum result : values()) {
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
    public static MtProcessManageTypeEnum getByMessage(String message) {
        if (StringUtils.isBlank(message)) {
            return null;
        }
        for (MtProcessManageTypeEnum result : values()) {
            if (result.getMessage().equals(message)) {
                return result;
            }
        }
        return null;
    }
}
