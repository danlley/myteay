/**
 * Myteay.com Inc.
 * Copyright (c) 2015-2015 All Rights Reserved.
 */
package com.myteay.common.utils.exception;

/**
 * ҵ�����쳣
 * 
 * @author Administrator
 * @version $Id: MtException.java, v 0.1 2015��11��15�� ����6:30:08 Administrator Exp $
 */
public class MtBizProcessException extends Exception {

    /** serialVersionUID */
    private static final long serialVersionUID = -1698555152104131128L;

    /** ��̨���ù���Ĵ������飬������Դ��CampOperateExResultEnum */
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
     * @param code              �������ݣ�ȡ��MtOperateExResultEnum��code��
     * @param operateExResult   ȡ��MtOperateExResultEnum�е�message
     */
    public MtBizProcessException(String code, String operateExResult) {
        super(code);
        this.operateExResult = operateExResult;
    }

    /**
     * MtBizException
     * 
     * @param code      ��������
     * @param e         �쳣
     */
    public MtBizProcessException(String code, Exception e, String operateExResult) {
        super(code, e);
        this.operateExResult = operateExResult;
    }
}
