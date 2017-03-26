/**
 * Myteay.com Inc.
 * Copyright (c) 2015-2015 All Rights Reserved.
 */
package com.myteay.common.dal.dataobject;

import java.io.Serializable;
import java.util.Date;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

/**
 * 
 * @author Administrator
 * @version $Id: UsersSecurityInfoDO.java, v 0.1 2015年12月1日 下午3:39:16 Administrator Exp $
 */
public class UsersSecurityInfoDO implements Serializable {

    /** serialVersionUID */
    private static final long serialVersionUID = 1118177156766085274L;

    /**  */
    private String            userId;

    /**  */
    private String            userPwd;

    /**  */
    private String            securityEmail;

    /**  */
    private String            securityPhone;

    /**  */
    private String            emailFlag;

    /**  */
    private String            phoneFlag;

    /**  */
    private Date              gmtCreated;

    /**  */
    private Date              gmtModified;

    /**  */
    private String            regFrom;

    /**
     * Getter method for property <tt>regFrom</tt>.
     * 
     * @return property value of regFrom
     */
    public String getRegFrom() {
        return regFrom;
    }

    /**
     * Setter method for property <tt>regFrom</tt>.
     * 
     * @param regFrom value to be assigned to property regFrom
     */
    public void setRegFrom(String regFrom) {
        this.regFrom = regFrom;
    }

    /**
     * Getter method for property <tt>gmtCreated</tt>.
     * 
     * @return property value of gmtCreated
     */
    public Date getGmtCreated() {
        return gmtCreated;
    }

    /**
     * Setter method for property <tt>gmtCreated</tt>.
     * 
     * @param gmtCreated value to be assigned to property gmtCreated
     */
    public void setGmtCreated(Date gmtCreated) {
        this.gmtCreated = gmtCreated;
    }

    /**
     * Getter method for property <tt>gmtModified</tt>.
     * 
     * @return property value of gmtModified
     */
    public Date getGmtModified() {
        return gmtModified;
    }

    /**
     * Setter method for property <tt>gmtModified</tt>.
     * 
     * @param gmtModified value to be assigned to property gmtModified
     */
    public void setGmtModified(Date gmtModified) {
        this.gmtModified = gmtModified;
    }

    /**
     * Getter method for property <tt>userId</tt>.
     * 
     * @return property value of userId
     */
    public String getUserId() {
        return userId;
    }

    /**
     * Setter method for property <tt>userId</tt>.
     * 
     * @param userId value to be assigned to property userId
     */
    public void setUserId(String userId) {
        this.userId = userId;
    }

    /**
     * Getter method for property <tt>userPwd</tt>.
     * 
     * @return property value of userPwd
     */
    public String getUserPwd() {
        return userPwd;
    }

    /**
     * Setter method for property <tt>userPwd</tt>.
     * 
     * @param userPwd value to be assigned to property userPwd
     */
    public void setUserPwd(String userPwd) {
        this.userPwd = userPwd;
    }

    /**
     * Getter method for property <tt>securityEmail</tt>.
     * 
     * @return property value of securityEmail
     */
    public String getSecurityEmail() {
        return securityEmail;
    }

    /**
     * Setter method for property <tt>securityEmail</tt>.
     * 
     * @param securityEmail value to be assigned to property securityEmail
     */
    public void setSecurityEmail(String securityEmail) {
        this.securityEmail = securityEmail;
    }

    /**
     * Getter method for property <tt>securityPhone</tt>.
     * 
     * @return property value of securityPhone
     */
    public String getSecurityPhone() {
        return securityPhone;
    }

    /**
     * Setter method for property <tt>securityPhone</tt>.
     * 
     * @param securityPhone value to be assigned to property securityPhone
     */
    public void setSecurityPhone(String securityPhone) {
        this.securityPhone = securityPhone;
    }

    /**
     * Getter method for property <tt>emailFlag</tt>.
     * 
     * @return property value of emailFlag
     */
    public String getEmailFlag() {
        return emailFlag;
    }

    /**
     * Setter method for property <tt>emailFlag</tt>.
     * 
     * @param emailFlag value to be assigned to property emailFlag
     */
    public void setEmailFlag(String emailFlag) {
        this.emailFlag = emailFlag;
    }

    /**
     * Getter method for property <tt>phoneFlag</tt>.
     * 
     * @return property value of phoneFlag
     */
    public String getPhoneFlag() {
        return phoneFlag;
    }

    /**
     * Setter method for property <tt>phoneFlag</tt>.
     * 
     * @param phoneFlag value to be assigned to property phoneFlag
     */
    public void setPhoneFlag(String phoneFlag) {
        this.phoneFlag = phoneFlag;
    }

    /** 
     * @see java.lang.Object#toString()
     */
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
    }
}
