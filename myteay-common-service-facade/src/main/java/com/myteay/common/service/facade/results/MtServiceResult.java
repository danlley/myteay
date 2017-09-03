/**
 * Myteay.com Inc.
 * Copyright (c) 2005-2017 All Rights Reserved.
 */
package com.myteay.common.service.facade.results;

import java.io.Serializable;

/**
 * 对外服务返回结果
 * 
 * @author danlley
 * @version $Id: MtServiceResult.java, v 0.1 Aug 31, 2017 9:43:39 AM danlley Exp $
 */
public class MtServiceResult<T> implements Serializable {

    /** serialVersionUID */
    private static final long serialVersionUID = -465379895931499387L;

    /** 操作结果码 */
    private String            operateResult;

    /** 操作结果扩展码 */
    private String            operateExResult;

    /** 操作目标返回对象 */
    private T                 result;

    /** 返回码为 FAILED时，对应的错误参考信息 */
    private String            errorDetail;

    /**
     * default constructor
     */
    public MtServiceResult() {
    }

    /**
     * constructor for initial instance
     * 
     * @param operateResult     返回结果类型
     * @param result            执行结果
     * @param errorDetail       错误详情
     * @param operateExResult   扩展信息
     */
    public MtServiceResult(String operateResult, T result, String errorDetail,
                           String operateExResult) {
        this.errorDetail = errorDetail;
        this.operateResult = operateResult;
        this.result = result;
        this.operateExResult = operateExResult;
    }

    /**
     * Getter method for property <tt>operateExResult</tt>.
     * 
     * @return property value of operateExResult
     */
    public String getOperateExResult() {
        return operateExResult;
    }

    /**
     * Setter method for property <tt>operateExResult</tt>.
     * 
     * @param operateExResult value to be assigned to property operateExResult
     */
    public void setOperateExResult(String operateExResult) {
        this.operateExResult = operateExResult;
    }

    /**
     * Getter method for property <tt>operateResult</tt>.
     * 
     * @return property value of operateResult
     */
    public String getOperateResult() {
        return operateResult;
    }

    /**
     * Setter method for property <tt>operateResult</tt>.
     * 
     * @param operateResult value to be assigned to property operateResult
     */
    public void setOperateResult(String operateResult) {
        this.operateResult = operateResult;
    }

    /**
     * Getter method for property <tt>result</tt>.
     * 
     * @return property value of result
     */
    public T getResult() {
        return result;
    }

    /**
     * Setter method for property <tt>result</tt>.
     * 
     * @param result value to be assigned to property result
     */
    public void setResult(T result) {
        this.result = result;
    }

    /**
     * Getter method for property <tt>errorDetail</tt>.
     * 
     * @return property value of errorDetail
     */
    public String getErrorDetail() {
        return errorDetail;
    }

    /**
     * Setter method for property <tt>errorDetail</tt>.
     * 
     * @param errorDetail value to be assigned to property errorDetail
     */
    public void setErrorDetail(String errorDetail) {
        this.errorDetail = errorDetail;
    }
}
