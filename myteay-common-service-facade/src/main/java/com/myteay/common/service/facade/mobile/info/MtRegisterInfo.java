/**
 * Myteay.com Inc.
 * Copyright (c) 2005-2017 All Rights Reserved.
 */
package com.myteay.common.service.facade.mobile.info;

import java.io.Serializable;
import java.util.Map;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

/**
 * ���������û�ͨ��rest����ע���˺Ź����е����ݴ���
 * 
 * @author danlley
 * @version $Id: MtRegisterInfo.java, v 0.1 Aug 30, 2017 9:06:43 PM danlley Exp $
 */
public class MtRegisterInfo implements Serializable {

    /** serialVersionUID */
    private static final long   serialVersionUID = -1627603560657715508L;

    /** �ֻ��� */
    private String              mtMobile;

    /** ���� */
    private String              mtPassword;

    /** ȷ������ */
    private String              mtPasswordConfirm;

    /** ���֤ */
    private String              mtIdCard;

    /** �ǳ� */
    private String              mtNickName;

    /** ��֤�� */
    private String              mtCheckCode;

    /** ע����չ��Ϣ */
    private Map<String, String> extRegInfo;

    /** ��ԱID */
    private String              userid;

    /** ��ֵ */
    private String              qrCodeId;

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
     * Getter method for property <tt>qrCodeId</tt>.
     * 
     * @return property value of qrCodeId
     */
    public String getQrCodeId() {
        return qrCodeId;
    }

    /**
     * Setter method for property <tt>qrCodeId</tt>.
     * 
     * @param qrCodeId value to be assigned to property qrCodeId
     */
    public void setQrCodeId(String qrCodeId) {
        this.qrCodeId = qrCodeId;
    }

    /**
     * Getter method for property <tt>extRegInfo</tt>.
     * 
     * @return property value of extRegInfo
     */
    public Map<String, String> getExtRegInfo() {
        return extRegInfo;
    }

    /**
     * Setter method for property <tt>extRegInfo</tt>.
     * 
     * @param extRegInfo value to be assigned to property extRegInfo
     */
    public void setExtRegInfo(Map<String, String> extRegInfo) {
        this.extRegInfo = extRegInfo;
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
     * Getter method for property <tt>mtPasswordConfirm</tt>.
     * 
     * @return property value of mtPasswordConfirm
     */
    public String getMtPasswordConfirm() {
        return mtPasswordConfirm;
    }

    /**
     * Setter method for property <tt>mtPasswordConfirm</tt>.
     * 
     * @param mtPasswordConfirm value to be assigned to property mtPasswordConfirm
     */
    public void setMtPasswordConfirm(String mtPasswordConfirm) {
        this.mtPasswordConfirm = mtPasswordConfirm;
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
     * Getter method for property <tt>mtNickName</tt>.
     * 
     * @return property value of mtNickName
     */
    public String getMtNickName() {
        return mtNickName;
    }

    /**
     * Setter method for property <tt>mtNickName</tt>.
     * 
     * @param mtNickName value to be assigned to property mtNickName
     */
    public void setMtNickName(String mtNickName) {
        this.mtNickName = mtNickName;
    }

    /**
     * Getter method for property <tt>mtCheckCode</tt>.
     * 
     * @return property value of mtCheckCode
     */
    public String getMtCheckCode() {
        return mtCheckCode;
    }

    /**
     * Setter method for property <tt>mtCheckCode</tt>.
     * 
     * @param mtCheckCode value to be assigned to property mtCheckCode
     */
    public void setMtCheckCode(String mtCheckCode) {
        this.mtCheckCode = mtCheckCode;
    }

    /** 
     * @see java.lang.Object#toString()
     */
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
    }
}
