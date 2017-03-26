/**
 * Myteay.com Inc.
 * Copyright (c) 2015-2015 All Rights Reserved.
 */
package com.myteay.common.service.facade.enums;

import com.myteay.common.util.comm.StringUtils;

/**
 * ���ؽ����չ��
 * 
 * @author Administrator
 * @version $Id: MtOperateExResultEnum.java, v 0.1 2015��12��1�� ����4:49:41 Administrator Exp $
 */
public enum MtOperateExResultEnum {

    //----------------          ������չ�����Ϣ��Ϣ          ----------------
    CAMP_OPERATE_SUCCESS("CAMP_OPERATE_SUCCESS", "00010001", "�����ɹ�"),

    CAMP_SQL_EXE_INVALID("CAMP_SQL_EXE_INVALID", "00010002", "SQLִ���쳣"),

    CAMP_ILLEGAL_ARGUMENTS("CAMP_ILLEGAL_ARGUMENTS", "00010003", "�����쳣"),

    CAMP_PROCESS_UNKNOW_ERR("CAMP_CONFIG_MNG_UNKNOW_ERR", "00028002", "����ִ�й��̷����쳣"),

    CAMP_QRCODE_EXE_FAILED("CAMP_QRCODE_EXE_FAILED", "00028003", "�û�ע���ά�������Ϣ����ʧ��"),

    CAMP_USERID_ERR("CAMP_USERID_ERR", "00028004", "userid���Ϸ�"),

    CAMP_QRCODE_FILENAME_FAILED("CAMP_QRCODE_FILENAME_FAILED", "00028005", "��ά����Ϣ����ģ���еĶ�ά���ļ���Ϣ������"),

    CAMP_USERID_NO_USER("CAMP_USERID_NO_USER", "00028006", "��ǰ�û�δ�ҵ�"),

    ;

    /** ö��ֵ */
    private final String value;

    /** ö������ */
    private final String message;

    /** ö������ */
    private final String code;

    /**
     * ˽�й��췽��
     * 
     * @param value         ö��ֵ
     * @param message       ö������
     */
    private MtOperateExResultEnum(String value, String code, String message) {
        this.value = value;
        this.code = code;
        this.message = message;
    }

    /**
     * Getter method for property <tt>code</tt>.
     * 
     * @return property value of code
     */
    public String getCode() {
        return code;
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
    public static MtOperateExResultEnum getByCode(String value) {
        if (StringUtils.isBlank(value)) {
            return null;
        }
        value = value.trim();
        for (MtOperateExResultEnum type : values()) {
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
    public static MtOperateExResultEnum getByValue(String value) {
        return getByCode(value);
    }

    /**
     * ͨ��ö��<code>message</code>���ö��
     * 
     * @param message       ö������
     * @return              ö�ٶ���
     */
    public static MtOperateExResultEnum getByMessage(String message) {
        if (StringUtils.isBlank(message)) {
            return null;
        }
        for (MtOperateExResultEnum result : values()) {
            if (result.getMessage().equals(message)) {
                return result;
            }
        }
        return null;
    }
}
