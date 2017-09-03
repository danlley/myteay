/**
 * Myteay.com Inc.
 * Copyright (c) 2005-2017 All Rights Reserved.
 */
package com.myteay.common.dal.dataobject;

import java.io.Serializable;
import java.util.Date;

import com.myteay.common.util.comm.ToStringUtil;

/**
 * 会员联系人列表数据模型
 * 
 * @author danlley
 * @version $Id: UserContactListInfoDO.java, v 0.1 Sep 2, 2017 5:07:30 PM danlley Exp $
 */
public class UserContactListInfoDO implements Serializable {

    /** serialVersionUID */
    private static final long serialVersionUID = 7738773548208632763L;

    /** 流水号 */
    private String            id;

    /** 会员ID */
    private String            userId;

    /** 会员手机号 */
    private String            parentMobile;

    /** 联系人 */
    private String            contactName;

    /** 联系人电话 */
    private String            contactMobile;

    /** 注册时间 */
    private Date              gmtCreated;

    /** 最后登录时间 */
    private Date              gmtModified;

    /**
     * Getter method for property <tt>id</tt>.
     * 
     * @return property value of id
     */
    public String getId() {
        return id;
    }

    /**
     * Setter method for property <tt>id</tt>.
     * 
     * @param id value to be assigned to property id
     */
    public void setId(String id) {
        this.id = id;
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
     * Getter method for property <tt>parentMobile</tt>.
     * 
     * @return property value of parentMobile
     */
    public String getParentMobile() {
        return parentMobile;
    }

    /**
     * Setter method for property <tt>parentMobile</tt>.
     * 
     * @param parentMobile value to be assigned to property parentMobile
     */
    public void setParentMobile(String parentMobile) {
        this.parentMobile = parentMobile;
    }

    /**
     * Getter method for property <tt>contactName</tt>.
     * 
     * @return property value of contactName
     */
    public String getContactName() {
        return contactName;
    }

    /**
     * Setter method for property <tt>contactName</tt>.
     * 
     * @param contactName value to be assigned to property contactName
     */
    public void setContactName(String contactName) {
        this.contactName = contactName;
    }

    /**
     * Getter method for property <tt>contactMobile</tt>.
     * 
     * @return property value of contactMobile
     */
    public String getContactMobile() {
        return contactMobile;
    }

    /**
     * Setter method for property <tt>contactMobile</tt>.
     * 
     * @param contactMobile value to be assigned to property contactMobile
     */
    public void setContactMobile(String contactMobile) {
        this.contactMobile = contactMobile;
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
     * @see java.lang.Object#toString()
     */
    public String toString() {
        return ToStringUtil.toShortString(this);
    }
}
