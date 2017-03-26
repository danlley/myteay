/**
 * Myteay.com Inc.
 * Copyright (c) 2015-2015 All Rights Reserved.
 */
package com.myteay.common.service.facade.enums;

import com.myteay.common.util.comm.StringUtils;

/**
 * �������������
 * 
 * @author Administrator
 * @version $Id: MtOperateResultEnum.java, v 0.1 2015��12��1�� ����4:48:14 Administrator Exp $
 */
public enum MtOperateResultEnum {

    CAMP_OPERATE_SUCCESS("CAMP_OPERATE_SUCCESS", "�����ɹ�"),

    CAMP_OPERATE_FAILED("CAMP_OPERATE_FAILED", "����ʧ��"),

    CAMP_OPERATE_UNKONW("CAMP_OPERATE_UNKONW", "�������δ֪"),

    ;

    /** ö��ֵ */
    private final String value;

    /** ö������ */
    private final String message;

    /**
     * ˽�й��췽��
     * 
     * @param value         ö��ֵ
     * @param message       ö������
     */
    private MtOperateResultEnum(String value, String message) {
        this.value = value;
        this.message = message;
    }

    /**
     * Getter method for property <tt>value</tt>.
     * 
     * @return property value of value
     */
    public String getValue() {
        return value;
    }

    /**
     * Getter method for property <tt>message</tt>.
     * 
     * @return property value of message
     */
    public String getMessage() {
        return message;
    }

    /**
     * ͨ��ֵ��ȡö�ٶ���
     * @param value     ö��ֵ
     * @return          ö�ٶ���
     */
    public static MtOperateResultEnum getByCode(String value) {
        if (StringUtils.isBlank(value)) {
            return null;
        }
        value = value.trim();
        for (MtOperateResultEnum type : values()) {
            if (type.getValue().equals(value)) {
                return type;
            }
        }
        return null;

    }

    /**
     * ͨ��ö��<code>value</code>���ö��
     * 
     * @param value     ö��ֵ
     * @return          ö�ٶ���
     */
    public static MtOperateResultEnum getByValue(String value) {
        return getByCode(value);
    }

    /**
     * ͨ��ö��<code>message</code>���ö��
     * 
     * @param message       ö������
     * @return              ö�ٶ���
     */
    public static MtOperateResultEnum getByMessage(String message) {
        if (StringUtils.isBlank(message)) {
            return null;
        }
        for (MtOperateResultEnum result : values()) {
            if (result.getMessage().equals(message)) {
                return result;
            }
        }
        return null;
    }
}
