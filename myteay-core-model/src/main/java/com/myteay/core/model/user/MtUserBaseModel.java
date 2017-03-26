/**
 * Myteay.com Inc.
 * Copyright (c) 2015-2015 All Rights Reserved.
 */
package com.myteay.core.model.user;

import java.io.Serializable;
import java.util.Date;

import com.myteay.common.service.facade.enums.MtUserFlagEnum;
import com.myteay.common.util.comm.ToStringUtil;

/**
 * 用户基础信息模型
 * 
 * @author Administrator
 * @version $Id: MtUserBaseModel.java, v 0.1 2015年11月15日 下午5:42:17 Administrator Exp $
 */
public class MtUserBaseModel implements Serializable {

    /** serialVersionUID */
    private static final long serialVersionUID = 4643954370444124480L;

    /** 流水ID */
    private String            id;

    /** 用户ID */
    private String            userId;

    /** 用户姓名 */
    private String            userName;

    /** 用户昵称 */
    private String            nickName;

    /** 二维码信息 */
    private String            qrCode;

    /** 激活标识 */
    private MtUserFlagEnum    checkedFlag;

    /** 注册时间 */
    private Date              gmtCreate;

    /** 最后登录时间 */
    private Date              gmtModified;

    /**
     * Getter method for property <tt>qrCode</tt>.
     * 
     * @return property value of qrCode
     */
    public String getQrCode() {
        return qrCode;
    }

    /**
     * Setter method for property <tt>qrCode</tt>.
     * 
     * @param qrCode value to be assigned to property qrCode
     */
    public void setQrCode(String qrCode) {
        this.qrCode = qrCode;
    }

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
     * Getter method for property <tt>userName</tt>.
     * 
     * @return property value of userName
     */
    public String getUserName() {
        return userName;
    }

    /**
     * Setter method for property <tt>userName</tt>.
     * 
     * @param userName value to be assigned to property userName
     */
    public void setUserName(String userName) {
        this.userName = userName;
    }

    /**
     * Getter method for property <tt>nickName</tt>.
     * 
     * @return property value of nickName
     */
    public String getNickName() {
        return nickName;
    }

    /**
     * Setter method for property <tt>nickName</tt>.
     * 
     * @param nickName value to be assigned to property nickName
     */
    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    /**
     * Getter method for property <tt>checkedFlag</tt>.
     * 
     * @return property value of checkedFlag
     */
    public MtUserFlagEnum getCheckedFlag() {
        return checkedFlag;
    }

    /**
     * Setter method for property <tt>checkedFlag</tt>.
     * 
     * @param checkedFlag value to be assigned to property checkedFlag
     */
    public void setCheckedFlag(MtUserFlagEnum checkedFlag) {
        this.checkedFlag = checkedFlag;
    }

    /**
     * Getter method for property <tt>gmtCreate</tt>.
     * 
     * @return property value of gmtCreate
     */
    public Date getGmtCreate() {
        return gmtCreate;
    }

    /**
     * Setter method for property <tt>gmtCreate</tt>.
     * 
     * @param gmtCreate value to be assigned to property gmtCreate
     */
    public void setGmtCreate(Date gmtCreate) {
        this.gmtCreate = gmtCreate;
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
