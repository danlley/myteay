/**
 * Myteay.com Inc.
 * Copyright (c) 2005-2017 All Rights Reserved.
 */
package com.myteay.common.service.facade.results;

import java.io.Serializable;

/**
 * ������񷵻ؽ��
 * 
 * @author danlley
 * @version $Id: MtServiceResult.java, v 0.1 Aug 31, 2017 9:43:39 AM danlley Exp $
 */
public class MtServiceResult<T> implements Serializable {

    /** serialVersionUID */
    private static final long serialVersionUID = -465379895931499387L;

    /** ��������� */
    private String            operateResult;

    /** ���������չ�� */
    private String            operateExResult;

    /** ����Ŀ�귵�ض��� */
    private T                 result;

    /** ������Ϊ FAILEDʱ����Ӧ�Ĵ���ο���Ϣ */
    private String            errorDetail;

    /**
     * default constructor
     */
    public MtServiceResult() {
    }

    /**
     * constructor for initial instance
     * 
     * @param operateResult     ���ؽ������
     * @param result            ִ�н��
     * @param errorDetail       ��������
     * @param operateExResult   ��չ��Ϣ
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
