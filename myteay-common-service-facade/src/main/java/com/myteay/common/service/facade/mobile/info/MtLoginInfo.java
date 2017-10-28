/**
 * Myteay.com Inc.
 * Copyright (c) 2005-2017 All Rights Reserved.
 */
package com.myteay.common.service.facade.mobile.info;

import java.io.Serializable;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

/**
 * 用户登录信息交互单据
 * 
 * @author danlley
 * @version $Id: MtLoginInfo.java, v 0.1 Sep 29, 2017 10:22:26 PM danlley Exp $
 */
public class MtLoginInfo implements Serializable {

    /** serialVersionUID */
    private static final long serialVersionUID = 1547952702972784421L;

    /** 手机号 */
    private String            mtMobile;

    /** 密码 */
    private String            mtPassword;

    /** 会员ID */
    private String            userid;

    /**
     * Getter method for property <tt>userid</tt>.
     * 
     * @return property value of userid
     */
    public String getUserid() {
        return userid;
    }

    /**
     * Setter method for property <tt>userid</tt>.
     * 
     * @param userid value to be assigned to property userid
     */
    public void setUserid(String userid) {
        this.userid = userid;
    }

    /**
     * Getter method for property <tt>mtMobile</tt>.
     * 
     * @return property value of mtMobile
     */
    public String getMtMobile() {
        return mtMobile;
    }

    /**
     * Setter method for property <tt>mtMobile</tt>.
     * 
     * @param mtMobile value to be assigned to property mtMobile
     */
    public void setMtMobile(String mtMobile) {
        this.mtMobile = mtMobile;
    }

    /**
     * Getter method for property <tt>mtPassword</tt>.
     * 
     * @return property value of mtPassword
     */
    public String getMtPassword() {
        return mtPassword;
    }

    /**
     * Setter method for property <tt>mtPassword</tt>.
     * 
     * @param mtPassword value to be assigned to property mtPassword
     */
    public void setMtPassword(String mtPassword) {
        this.mtPassword = mtPassword;
    }

    /** 
     * @see java.lang.Object#toString()
     */
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
    }
}
