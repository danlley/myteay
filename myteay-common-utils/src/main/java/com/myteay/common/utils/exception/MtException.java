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
public class MtException extends Throwable {

    /** serialVersionUID */
    private static final long     serialVersionUID = -1698555152104131128L;

    /** ϵͳĬ�ϴ����� */
    protected static final String DEFAULT_ERR_CODE = "-999999";

    /** Ĭ�ϴ������� */
    protected static final String DEFAULT_ERR_MSG  = "δ֪����";

    /** ������ */
    protected String              errorCode;

    /** �������� */
    protected String              message;

    /**
     * @param e
     */
    public MtException() {
        this.errorCode = DEFAULT_ERR_CODE;
        this.message = DEFAULT_ERR_MSG;
    }

    /**
     * @param e
     */
    public MtException(String message) {
        super(message);
        this.errorCode = DEFAULT_ERR_CODE;
        this.message = message;
    }

    /**
     * @param e
     */
    public MtException(Throwable e) {
        super(e);

        this.errorCode = DEFAULT_ERR_CODE;
        this.message = (e == null ? DEFAULT_ERR_MSG : e.toString());
    }

    /**
     * @param msg
     * @param e
     */
    public MtException(String msg, Throwable e) {
        super(msg, e);
    }
}
