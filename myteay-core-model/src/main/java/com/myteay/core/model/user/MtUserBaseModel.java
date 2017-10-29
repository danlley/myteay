/**
 * Myteay.com Inc.
 * Copyright (c) 2015-2015 All Rights Reserved.
 */
package com.myteay.core.model.user;

import java.io.Serializable;
import java.util.Date;

import com.myteay.common.service.facade.enums.MtUserFlagEnum;
import com.myteay.common.util.tools.ToStringUtil;

/**
 * �û�������Ϣģ��
 * 
 * @author Administrator
 * @version $Id: MtUserBaseModel.java, v 0.1 2015��11��15�� ����5:42:17 Administrator Exp $
 */
public class MtUserBaseModel implements Serializable {

    /** serialVersionUID */
    private static final long serialVersionUID = 4643954370444124480L;

    /** ��ˮID */
    private String            id;

    /** �û�ID */
    private String            userId;

    /** �û����� */
    private String            mtPassword;

    /** ���֤ */
    private String            mtIdCard;

    /** �ֻ��� */
    private String            mtMobile;

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
     * Getter method for property <tt>mtIdCard</tt>.
     * 
     * @return property value of mtIdCard
     */
    public String getMtIdCard() {
        return mtIdCard;
    }

    /**
     * Setter method for property <tt>mtIdCard</tt>.
     * 
     * @param mtIdCard value to be assigned to property mtIdCard
     */
    public void setMtIdCard(String mtIdCard) {
        this.mtIdCard = mtIdCard;
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

    /** �û����� */
    private String         userName;

    /** �û��ǳ� */
    private String         nickName;

    /** ��ά����Ϣ */
    private String         qrCode;

    /** �����ʶ */
    private MtUserFlagEnum checkedFlag;

    /** ע��ʱ�� */
    private Date           gmtCreate;

    /** ����¼ʱ�� */
    private Date           gmtModified;

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
