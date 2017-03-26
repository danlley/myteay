/**
 * Myteay.com Inc.
 * Copyright (c) 2015-2015 All Rights Reserved.
 */
package com.myteay.common.service.facade.exceptions;

/**
 * 业务处理异常
 * 
 * @author Administrator
 * @version $Id: MtException.java, v 0.1 2015年11月15日 下午6:30:08 Administrator Exp $
 */
public class MtBizException extends Throwable {

    /** serialVersionUID */
    private static final long     serialVersionUID = -1698555152104131128L;

    /** 系统默认错误码 */
    protected static final String DEFAULT_ERR_CODE = "-999999";

    /** 默认错误详情 */
    protected static final String DEFAULT_ERR_MSG  = "未知错误";

    /** 错误码 */
    protected String              errorCode;

    /** 错误详情 */
    protected String              message;

    /**
     * @param e
     */
    public MtBizException() {
        this.errorCode = DEFAULT_ERR_CODE;
        this.message = DEFAULT_ERR_MSG;
    }

    /**
     * @param e
     */
    public MtBizException(String message) {
        super(message);
        this.errorCode = DEFAULT_ERR_CODE;
        this.message = message;
    }

    /**
     * @param e
     */
    public MtBizException(Throwable e) {
        super(e);

        this.errorCode = DEFAULT_ERR_CODE;
        this.message = (e == null ? DEFAULT_ERR_MSG : e.toString());
    }

    /**
     * @param msg
     * @param e
     */
    public MtBizException(String msg, Throwable e) {
        super(msg, e);
    }
}
