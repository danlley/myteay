/**
 * Myteay.com Inc.
 * Copyright (c) 2015-2015 All Rights Reserved.
 */
package com.myteay.common.service.facade.model;

import java.io.Serializable;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import com.myteay.common.service.facade.enums.MtOperateExResultEnum;
import com.myteay.common.service.facade.enums.MtOperateResultEnum;

/**
 * ���ؽ��
 * 
 * @author Administrator
 * @version $Id: MtOperateResult.java, v 0.1 2015��12��1�� ����4:46:13 Administrator Exp $
 */
public class MtOperateResult<T> implements Serializable {

    /** serialVersionUID */
    private static final long     serialVersionUID = -1112148186814322176L;

    /** ��������� */
    private MtOperateResultEnum   operateResult;

    /** ���������չ�� */
    private MtOperateExResultEnum operateExResult;

    /** ����Ŀ�귵�ض��� */
    private T                     result;

    /** ������Ϊ FAILEDʱ����Ӧ�Ĵ���ο���Ϣ */
    private String                errorDetail;

    /**
     * default constructor
     */
    public MtOperateResult() {
    }

    /**
     * constructor for initial instance
     * 
     * @param operateResult     ���ؽ������
     * @param result            ִ�н��
     * @param errorDetail       ��������
     * @param operateExResult   ��չ��Ϣ
     */
    public MtOperateResult(MtOperateResultEnum operateResult, T result, String errorDetail,
                           MtOperateExResultEnum operateExResult) {
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
    public MtOperateExResultEnum getOperateExResult() {
        return operateExResult;
    }

    /**
     * Setter method for property <tt>operateExResult</tt>.
     * 
     * @param operateExResult value to be assigned to property operateExResult
     */
    public void setOperateExResult(MtOperateExResultEnum operateExResult) {
        this.operateExResult = operateExResult;
    }

    /**
     * Getter method for property <tt>operateResult</tt>.
     * 
     * @return property value of operateResult
     */
    public MtOperateResultEnum getOperateResult() {
        return operateResult;
    }

    /**
     * Setter method for property <tt>operateResult</tt>.
     * 
     * @param operateResult value to be assigned to property operateResult
     */
    public void setOperateResult(MtOperateResultEnum operateResult) {
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

    /** 
     * @see java.lang.Object#toString()
     */
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
    }
}
