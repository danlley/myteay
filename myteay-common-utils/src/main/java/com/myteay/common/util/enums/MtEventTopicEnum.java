/**
 * Myteay.com Inc.
 * Copyright (c) 2015-2016 All Rights Reserved.
 */
package com.myteay.common.util.enums;

import org.apache.commons.lang.StringUtils;

/**
 * ��Ϣ�¼�����
 * 
 * @author Administrator
 * @version $Id: MtEventTopicEnum.java, v 0.1 2016��3��20�� ����9:57:48 Administrator Exp $
 */
public enum MtEventTopicEnum {

                              /** �ײͻ���ˢ���¼� */
                              MT_CACHE_GOODS_PKG_FRESH("MT_CACHE_GOODS_PKG_FRESH", "�ײͻ���ˢ���¼�"),

                              /** �û���ά��ע�������¼� */
                              MT_USR_QR_CODE_REGISTERY("MT_USR_QR_CODE_REGISTERY", "�û���ά��ע�������¼�"),

                              /** �û���ϵ���б� */
                              MT_USR_CONTACT_LIST("MT_USR_CONTACT_LIST", "�û���ϵ���б�"),

                              /** �û�ע����չ��Ϣ�첽�¼� */
                              MT_USR_REG_EXT_EVENT("MT_USR_REG_EXT_EVENT", "�û�ע����չ��Ϣ�첽�¼�"),

                              /** �û�ע���ֻ���Ϣ�첽�¼� */
                              MT_USR_REG_MOBILE_INFO_EVENT("MT_USR_REG_MOBILE_INFO_EVENT", "�û�ע���ֻ���Ϣ�첽�¼�"),

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
     * ͨ��ֵ��ȡö�ٶ���
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
     * ͨ��ö��<code>value</code>���ö��
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
     * ͨ��ö��<code>name</code>���ö��
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
