/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2015 All Rights Reserved.
 */
package com.myteay.common.util.comm;

import com.myteay.common.utils.exception.MtException;

/**
 * 系统异常处理
 * 
 * @author Administrator
 * @version $Id: MyTeayException.java, v 0.1 2015年7月5日 下午9:55:10 Administrator Exp $
 */
public class MyTeayException extends MtException {

    /** serialVersionUID */
    private static final long serialVersionUID = -4231201238990489868L;

    /**
     * @param e
     */
    public MyTeayException() {
        this.errorCode = DEFAULT_ERR_CODE;
        this.message = DEFAULT_ERR_MSG;
    }

    /**
     * @param e
     */
    public MyTeayException(String message) {
        this.errorCode = DEFAULT_ERR_CODE;
        this.message = message;
    }

    /**
     * @param e
     */
    public MyTeayException(Throwable e) {
        super(e);

        this.errorCode = DEFAULT_ERR_CODE;
        this.message = (e == null ? DEFAULT_ERR_MSG : e.toString());
    }

    /**
     * @param e
     * @param errorCode
     */
    public MyTeayException(Throwable e, String errorCode) {
        super(errorCode, e);
        this.errorCode = errorCode;
        this.message = (e == null ? DEFAULT_ERR_MSG : e.toString());
    }

    /**
     * @param e
     * @param errorCode
     * @param errorDetail
     */
    public MyTeayException(Throwable e, String errorCode, String errorDetail) {
        super(errorCode, e);
        this.errorCode = errorCode;
        this.message = errorDetail;
    }

    /**
     * Getter method for property <tt>errorCode</tt>.
     * 
     * @return property value of errorCode
     */
    public String getErrorCode() {
        return errorCode;
    }

    /**
     * Setter method for property <tt>errorCode</tt>.
     * 
     * @param errorCode value to be assigned to property errorCode
     */
    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
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
     * Setter method for property <tt>message</tt>.
     * 
     * @param message value to be assigned to property message
     */
    public void setMessage(String message) {
        this.message = message;
    }
}
