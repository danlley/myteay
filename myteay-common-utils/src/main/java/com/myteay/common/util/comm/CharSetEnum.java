package com.myteay.common.util.comm;

/**
 * �ַ�����ö����
 * 
 * @author Administrator
 * @version $Id: CharSetEnum.java, v 0.1 2012-8-25 ����9:31:44 Administrator Exp $
 */
public enum CharSetEnum {
    /**  */
    GBK("GBK", "GBK"),

    /**  */
    GB2312("gb2312", "gb2312"),

    /**  */
    ISO_8859_1("ISO-8859-1", "ISO-8859-1"),

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
    private CharSetEnum(String value, String message) {
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
    public static CharSetEnum getByCode(String value) {
        if (StringUtils.isBlank(value)) {
            return null;
        }
        value = value.trim();
        for (CharSetEnum type : values()) {
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
    public static CharSetEnum getByValue(String value) {
        if (value == null) {
            return null;
        }
        for (CharSetEnum result : values()) {
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
    public static CharSetEnum getByMessage(String message) {
        if (StringUtils.isBlank(message)) {
            return null;
        }
        for (CharSetEnum result : values()) {
            if (result.getMessage().equals(message)) {
                return result;
            }
        }
        return null;
    }
}