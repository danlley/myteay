/**
 * Myteay.com Inc.
 * Copyright (c) 2015-2016 All Rights Reserved.
 */
package com.myteay.common.service.facade.enums;

import org.apache.commons.lang.StringUtils;

/**
 * 流程处理管理操作类型枚举
 * 
 * @author Administrator
 * @version $Id: MtManageTypeEnum.java, v 0.1 2016年9月7日 下午10:52:39 Administrator Exp $
 */
public enum MtProcessManageTypeEnum {

                                     /** 辅助流程执行标记 */
                                     CS_AUXILIARY_PROCESS("CS_AUXILIARY_PROCESS", "辅助流程执行"),

                                     /** 主流程执行标记 */
                                     CS_MAIN_PROCESS("CS_MAIN_PROCESS", "主流程执行"),

                                     /** 流程结束后的扫尾工作标记 */
                                     CS_AFTER_PROCESS("CS_AFTER_PROCESS", "流程结束后的扫尾工作"),

                                     /** 流程开始前的准备工作标记 */
                                     CS_BEFORE_PROCESS("CS_BEFORE_PROCESS", "流程开始前的准备工作"),

                                     /** 验证流程数据标记 */
                                     CS_VALIDATE_PROCESS("CS_VALIDATE_PROCESS", "验证流程数据"),

                                     /**结束标记 */
                                     CS_END("CS_END", "结束标记"),

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
     * 通过值获取枚举对象
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
     * 通过枚举<code>value</code>获得枚举
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
     * 通过枚举<code>name</code>获得枚举
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
