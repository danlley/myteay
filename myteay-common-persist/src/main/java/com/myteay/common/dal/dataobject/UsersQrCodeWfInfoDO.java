/**
 * Myteay.com Inc.
 * Copyright (c) 2015-2016 All Rights Reserved.
 */
package com.myteay.common.dal.dataobject;

import java.io.Serializable;
import java.util.Date;

import com.myteay.common.util.comm.ToStringUtil;

/**
 * ��ά��������ˮ����ģ��
 * 
 * @author Administrator
 * @version $Id: UsersQrCodeWfInfoDO.java, v 0.1 2016��9��7�� ����1:22:37 Administrator Exp $
 */
public class UsersQrCodeWfInfoDO implements Serializable {

    /** serialVersionUID */
    private static final long serialVersionUID = -8219257515933289934L;

    /** ��ˮID */
    private String            id;

    /** �û�ID */
    private String            userId;

    /** ע��ʱ�� */
    private Date              gmtCreated;

    /** ����¼ʱ�� */
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
