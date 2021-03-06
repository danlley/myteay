/**
 * Myteay.com Inc.
 * Copyright (c) 2015-2015 All Rights Reserved.
 */
package com.myteay.common.service.facade.enums;

import org.apache.commons.lang.StringUtils;

/**
 * 返回结果扩展码
 * 
 * @author Administrator
 * @version $Id: MtOperateExResultEnum.java, v 0.1 2015年12月1日 下午4:49:41 Administrator Exp $
 */
public enum MtOperateExResultEnum {

                                   //----------------          基本扩展结果信息信息          ----------------
                                   CAMP_OPERATE_SUCCESS("CAMP_OPERATE_SUCCESS", "00010001", "操作成功"),

                                   CAMP_SQL_EXE_INVALID("CAMP_SQL_EXE_INVALID", "00010002", "SQL执行异常"),

                                   CAMP_ILLEGAL_ARGUMENTS("CAMP_ILLEGAL_ARGUMENTS", "00010003", "参数异常"),

                                   CAMP_PROCESS_UNKNOW_ERR("CAMP_CONFIG_MNG_UNKNOW_ERR", "00028002", "数据执行过程发生异常"),

                                   CAMP_QRCODE_EXE_FAILED("CAMP_QRCODE_EXE_FAILED", "00028003", "用户注册二维码身份信息生成失败"),

                                   CAMP_USERID_ERR("CAMP_USERID_ERR", "00028004", "userid不合法"),

                                   CAMP_QRCODE_FILENAME_FAILED("CAMP_QRCODE_FILENAME_FAILED", "00028005", "二维码信息交互模型中的二维码文件信息不可用"),

                                   CAMP_USERID_NO_USER("CAMP_USERID_NO_USER", "00028006", "当前用户未找到"),

    ;

    /** 枚举值 */
    private final String value;

    /** 枚举描述 */
    private final String message;

    /** 枚举描述 */
    private final String code;

    /**
     * 私有构造方法
     * 
     * @param value         枚举值
     * @param message       枚举描述
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
     * 通过值获取枚举对象
     * @param value     枚举值
     * @return          枚举对象
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
     * 通过枚举<code>value</code>获得枚举
     * 
     * @param value     枚举值
     * @return          枚举对象
     */
    public static MtOperateExResultEnum getByValue(String value) {
        return getByCode(value);
    }

    /**
     * 通过枚举<code>message</code>获得枚举
     * 
     * @param message       枚举描述
     * @return              枚举对象
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
