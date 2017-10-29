/**
 * Myteay.com Inc.
 * Copyright (c) 2005-2017 All Rights Reserved.
 */
package com.myteay.core.model.user;

import java.io.Serializable;

import com.myteay.common.util.tools.ToStringUtil;

/**
 * �û��ֻ�������Ϣģ��
 * 
 * @author danlley
 * @version $Id: MtUserMobileBaseInfoModel.java, v 0.1 Sep 3, 2017 2:04:21 PM danlley Exp $
 */
public class MtUserMobileBaseInfoModel implements Serializable {

    /** serialVersionUID */
    private static final long serialVersionUID = -7990172221251306285L;

    /** ��ȡ�������,�汾�����汾�� */
    private String            mobilePackageInfo;

    /** ��ȡandroid��ǰ�����ڴ��С */
    private String            mobileAvailMemory;

    /** ���ϵͳ���ڴ� */
    private String            mobileTotalMemory;

    /** ����ֻ���Ļ��� */
    private String            mobileSize;

    /** ��ȡIMEI�ţ�IESI�ţ��ֻ��ͺ� */
    private String            mobileBaseInfo;

    /** ��ȡ�ֻ�MAC��ַ */
    private String            mobileMacAddress;

    /** ��ȡ�ֻ��Ƿ�root��Ϣ */
    private String            mobileRootProperty;

    /** �ֻ�CPU��Ϣ */
    private String            mobileCpuInfo;

    /** ��ԱID */
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
     * Getter method for property <tt>mobilePackageInfo</tt>.
     * 
     * @return property value of mobilePackageInfo
     */
    public String getMobilePackageInfo() {
        return mobilePackageInfo;
    }

    /**
     * Setter method for property <tt>mobilePackageInfo</tt>.
     * 
     * @param mobilePackageInfo value to be assigned to property mobilePackageInfo
     */
    public void setMobilePackageInfo(String mobilePackageInfo) {
        this.mobilePackageInfo = mobilePackageInfo;
    }

    /**
     * Getter method for property <tt>mobileAvailMemory</tt>.
     * 
     * @return property value of mobileAvailMemory
     */
    public String getMobileAvailMemory() {
        return mobileAvailMemory;
    }

    /**
     * Setter method for property <tt>mobileAvailMemory</tt>.
     * 
     * @param mobileAvailMemory value to be assigned to property mobileAvailMemory
     */
    public void setMobileAvailMemory(String mobileAvailMemory) {
        this.mobileAvailMemory = mobileAvailMemory;
    }

    /**
     * Getter method for property <tt>mobileTotalMemory</tt>.
     * 
     * @return property value of mobileTotalMemory
     */
    public String getMobileTotalMemory() {
        return mobileTotalMemory;
    }

    /**
     * Setter method for property <tt>mobileTotalMemory</tt>.
     * 
     * @param mobileTotalMemory value to be assigned to property mobileTotalMemory
     */
    public void setMobileTotalMemory(String mobileTotalMemory) {
        this.mobileTotalMemory = mobileTotalMemory;
    }

    /**
     * Getter method for property <tt>mobileSize</tt>.
     * 
     * @return property value of mobileSize
     */
    public String getMobileSize() {
        return mobileSize;
    }

    /**
     * Setter method for property <tt>mobileSize</tt>.
     * 
     * @param mobileSize value to be assigned to property mobileSize
     */
    public void setMobileSize(String mobileSize) {
        this.mobileSize = mobileSize;
    }

    /**
     * Getter method for property <tt>mobileBaseInfo</tt>.
     * 
     * @return property value of mobileBaseInfo
     */
    public String getMobileBaseInfo() {
        return mobileBaseInfo;
    }

    /**
     * Setter method for property <tt>mobileBaseInfo</tt>.
     * 
     * @param mobileBaseInfo value to be assigned to property mobileBaseInfo
     */
    public void setMobileBaseInfo(String mobileBaseInfo) {
        this.mobileBaseInfo = mobileBaseInfo;
    }

    /**
     * Getter method for property <tt>mobileMacAddress</tt>.
     * 
     * @return property value of mobileMacAddress
     */
    public String getMobileMacAddress() {
        return mobileMacAddress;
    }

    /**
     * Setter method for property <tt>mobileMacAddress</tt>.
     * 
     * @param mobileMacAddress value to be assigned to property mobileMacAddress
     */
    public void setMobileMacAddress(String mobileMacAddress) {
        this.mobileMacAddress = mobileMacAddress;
    }

    /**
     * Getter method for property <tt>mobileRootProperty</tt>.
     * 
     * @return property value of mobileRootProperty
     */
    public String getMobileRootProperty() {
        return mobileRootProperty;
    }

    /**
     * Setter method for property <tt>mobileRootProperty</tt>.
     * 
     * @param mobileRootProperty value to be assigned to property mobileRootProperty
     */
    public void setMobileRootProperty(String mobileRootProperty) {
        this.mobileRootProperty = mobileRootProperty;
    }

    /**
     * Getter method for property <tt>mobileCpuInfo</tt>.
     * 
     * @return property value of mobileCpuInfo
     */
    public String getMobileCpuInfo() {
        return mobileCpuInfo;
    }

    /**
     * Setter method for property <tt>mobileCpuInfo</tt>.
     * 
     * @param mobileCpuInfo value to be assigned to property mobileCpuInfo
     */
    public void setMobileCpuInfo(String mobileCpuInfo) {
        this.mobileCpuInfo = mobileCpuInfo;
    }

    /** 
     * @see java.lang.Object#toString()
     */
    public String toString() {
        return ToStringUtil.toShortString(this);
    }
}
