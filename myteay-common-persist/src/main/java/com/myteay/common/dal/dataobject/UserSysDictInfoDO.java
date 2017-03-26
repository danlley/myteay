/**
 * Myteay.com Inc.
 * Copyright (c) 2015-2016 All Rights Reserved.
 */
package com.myteay.common.dal.dataobject;

import java.io.Serializable;
import java.util.Date;

import com.myteay.common.util.comm.ToStringUtil;

/**
 * 用户字典DO
 * 
 * @author Administrator
 * @version $Id: UserSysDictInfoDO.java, v 0.1 2016年9月7日 上午12:27:42 Administrator Exp $
 */
public class UserSysDictInfoDO implements Serializable {

    /** serialVersionUID */
    private static final long serialVersionUID = -3472001677449904136L;

    /** 激活标识 */
    private String            dicKey;

    /** 激活标识 */
    private String            dicValue;

    /** 注册时间 */
    private Date              gmtCreated;

    /** 最后登录时间 */
    private Date              gmtModified;

    /**
     * Getter method for property <tt>dicKey</tt>.
     * 
     * @return property value of dicKey
     */
    public String getDicKey() {
        return dicKey;
    }

    /**
     * Setter method for property <tt>dicKey</tt>.
     * 
     * @param dicKey value to be assigned to property dicKey
     */
    public void setDicKey(String dicKey) {
        this.dicKey = dicKey;
    }

    /**
     * Getter method for property <tt>dicValue</tt>.
     * 
     * @return property value of dicValue
     */
    public String getDicValue() {
        return dicValue;
    }

    /**
     * Setter method for property <tt>dicValue</tt>.
     * 
     * @param dicValue value to be assigned to property dicValue
     */
    public void setDicValue(String dicValue) {
        this.dicValue = dicValue;
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
