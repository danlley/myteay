/**
 * Myteay.com Inc.
 * Copyright (c) 2015-2015 All Rights Reserved.
 */
package com.myteay.common.utils.exception;

/**
 * 业务处理异常
 * 
 * @author Administrator
 * @version $Id: MtException.java, v 0.1 2015年11月15日 下午6:30:08 Administrator Exp $
 */
public class MtBizProcessException extends Exception {

    /** serialVersionUID */
    private static final long serialVersionUID = -1698555152104131128L;

    /** 后台配置管理的错误详情，数据来源于CampOperateExResultEnum */
    private String            operateExResult;

    /**
     * Getter method for property <tt>operateExResult</tt>.
     * 
     * @return property value of operateExResult
     */
    public String getOperateExResult() {
        return operateExResult;
    }

    /**
     * MtBizException
     * 
     * @param code              错误内容（取自MtOperateExResultEnum的code）
     * @param operateExResult   取自MtOperateExResultEnum中的message
     */
    public MtBizProcessException(String code, String operateExResult) {
        super(code);
        this.operateExResult = operateExResult;
    }

    /**
     * MtBizException
     * 
     * @param code      错误内容
     * @param e         异常
     */
    public MtBizProcessException(String code, Exception e, String operateExResult) {
        super(code, e);
        this.operateExResult = operateExResult;
    }
}
