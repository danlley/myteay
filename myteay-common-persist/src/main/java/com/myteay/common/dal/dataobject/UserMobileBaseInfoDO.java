/**
 * Myteay.com Inc.
 * Copyright (c) 2005-2017 All Rights Reserved.
 */
package com.myteay.common.dal.dataobject;

import java.io.Serializable;
import java.util.Date;

import com.myteay.common.util.tools.ToStringUtil;

/**
 * 用户手机基本信息
 * 
 * @author danlley
 * @version $Id: UserMobileBaseInfoDO.java, v 0.1 Sep 3, 2017 4:55:45 PM danlley Exp $
 */
public class UserMobileBaseInfoDO implements Serializable {

    /** serialVersionUID */
    private static final long serialVersionUID = -7119163838705375787L;

    /** 业务流水号 */
    private String            id;

    /** 手机内存大小 */
    private String            totalMemory;

    /** 剩余内存大小 */
    private String            availMemory;

    /** IMEI号 */
    private String            imeiNo;

    /** IESI号 */
    private String            iesiNo;

    /** 手机型号 */
    private String            mobileType;

    /** 手机生产厂商 */
    private String            mobileBrand;

    /** 手机号 */
    private String            mobileNo;

    /** CPU信息 */
    private String            cpu;

    /** CPU频率 */
    private String            cpuRange;

    /** MAC地址 */
    private String            macAddr;

    /** 是否支持root */
    private String            rootAble;

    /** 手机宽度 */
    private String            mobileWidth;

    /** 手机高度 */
    private String            mobileHeight;

    /** 软件包名 */
    private String            apkPackage;

    /** 软件版本名称 */
    private String            versionName;

    /** 软件版本码 */
    private String            versionCode;

    /** 采集数据类型 */
    private String            dataInfoType;

    /** 记录创建时间 */
    private Date              gmtCreated;

    /** 记录最后修改时间 */
    private Date              gmt_mModified;

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
     * Getter method for property <tt>totalMemory</tt>.
     * 
     * @return property value of totalMemory
     */
    public String getTotalMemory() {
        return totalMemory;
    }

    /**
     * Setter method for property <tt>totalMemory</tt>.
     * 
     * @param totalMemory value to be assigned to property totalMemory
     */
    public void setTotalMemory(String totalMemory) {
        this.totalMemory = totalMemory;
    }

    /**
     * Getter method for property <tt>availMemory</tt>.
     * 
     * @return property value of availMemory
     */
    public String getAvailMemory() {
        return availMemory;
    }

    /**
     * Setter method for property <tt>availMemory</tt>.
     * 
     * @param availMemory value to be assigned to property availMemory
     */
    public void setAvailMemory(String availMemory) {
        this.availMemory = availMemory;
    }

    /**
     * Getter method for property <tt>imeiNo</tt>.
     * 
     * @return property value of imeiNo
     */
    public String getImeiNo() {
        return imeiNo;
    }

    /**
     * Setter method for property <tt>imeiNo</tt>.
     * 
     * @param imeiNo value to be assigned to property imeiNo
     */
    public void setImeiNo(String imeiNo) {
        this.imeiNo = imeiNo;
    }

    /**
     * Getter method for property <tt>iesiNo</tt>.
     * 
     * @return property value of iesiNo
     */
    public String getIesiNo() {
        return iesiNo;
    }

    /**
     * Setter method for property <tt>iesiNo</tt>.
     * 
     * @param iesiNo value to be assigned to property iesiNo
     */
    public void setIesiNo(String iesiNo) {
        this.iesiNo = iesiNo;
    }

    /**
     * Getter method for property <tt>mobileType</tt>.
     * 
     * @return property value of mobileType
     */
    public String getMobileType() {
        return mobileType;
    }

    /**
     * Setter method for property <tt>mobileType</tt>.
     * 
     * @param mobileType value to be assigned to property mobileType
     */
    public void setMobileType(String mobileType) {
        this.mobileType = mobileType;
    }

    /**
     * Getter method for property <tt>mobileBrand</tt>.
     * 
     * @return property value of mobileBrand
     */
    public String getMobileBrand() {
        return mobileBrand;
    }

    /**
     * Setter method for property <tt>mobileBrand</tt>.
     * 
     * @param mobileBrand value to be assigned to property mobileBrand
     */
    public void setMobileBrand(String mobileBrand) {
        this.mobileBrand = mobileBrand;
    }

    /**
     * Getter method for property <tt>mobileNo</tt>.
     * 
     * @return property value of mobileNo
     */
    public String getMobileNo() {
        return mobileNo;
    }

    /**
     * Setter method for property <tt>mobileNo</tt>.
     * 
     * @param mobileNo value to be assigned to property mobileNo
     */
    public void setMobileNo(String mobileNo) {
        this.mobileNo = mobileNo;
    }

    /**
     * Getter method for property <tt>cpu</tt>.
     * 
     * @return property value of cpu
     */
    public String getCpu() {
        return cpu;
    }

    /**
     * Setter method for property <tt>cpu</tt>.
     * 
     * @param cpu value to be assigned to property cpu
     */
    public void setCpu(String cpu) {
        this.cpu = cpu;
    }

    /**
     * Getter method for property <tt>cpuRange</tt>.
     * 
     * @return property value of cpuRange
     */
    public String getCpuRange() {
        return cpuRange;
    }

    /**
     * Setter method for property <tt>cpuRange</tt>.
     * 
     * @param cpuRange value to be assigned to property cpuRange
     */
    public void setCpuRange(String cpuRange) {
        this.cpuRange = cpuRange;
    }

    /**
     * Getter method for property <tt>macAddr</tt>.
     * 
     * @return property value of macAddr
     */
    public String getMacAddr() {
        return macAddr;
    }

    /**
     * Setter method for property <tt>macAddr</tt>.
     * 
     * @param macAddr value to be assigned to property macAddr
     */
    public void setMacAddr(String macAddr) {
        this.macAddr = macAddr;
    }

    /**
     * Getter method for property <tt>rootAble</tt>.
     * 
     * @return property value of rootAble
     */
    public String getRootAble() {
        return rootAble;
    }

    /**
     * Setter method for property <tt>rootAble</tt>.
     * 
     * @param rootAble value to be assigned to property rootAble
     */
    public void setRootAble(String rootAble) {
        this.rootAble = rootAble;
    }

    /**
     * Getter method for property <tt>mobileWidth</tt>.
     * 
     * @return property value of mobileWidth
     */
    public String getMobileWidth() {
        return mobileWidth;
    }

    /**
     * Setter method for property <tt>mobileWidth</tt>.
     * 
     * @param mobileWidth value to be assigned to property mobileWidth
     */
    public void setMobileWidth(String mobileWidth) {
        this.mobileWidth = mobileWidth;
    }

    /**
     * Getter method for property <tt>mobileHeight</tt>.
     * 
     * @return property value of mobileHeight
     */
    public String getMobileHeight() {
        return mobileHeight;
    }

    /**
     * Setter method for property <tt>mobileHeight</tt>.
     * 
     * @param mobileHeight value to be assigned to property mobileHeight
     */
    public void setMobileHeight(String mobileHeight) {
        this.mobileHeight = mobileHeight;
    }

    /**
     * Getter method for property <tt>apkPackage</tt>.
     * 
     * @return property value of apkPackage
     */
    public String getApkPackage() {
        return apkPackage;
    }

    /**
     * Setter method for property <tt>apkPackage</tt>.
     * 
     * @param apkPackage value to be assigned to property apkPackage
     */
    public void setApkPackage(String apkPackage) {
        this.apkPackage = apkPackage;
    }

    /**
     * Getter method for property <tt>versionName</tt>.
     * 
     * @return property value of versionName
     */
    public String getVersionName() {
        return versionName;
    }

    /**
     * Setter method for property <tt>versionName</tt>.
     * 
     * @param versionName value to be assigned to property versionName
     */
    public void setVersionName(String versionName) {
        this.versionName = versionName;
    }

    /**
     * Getter method for property <tt>versionCode</tt>.
     * 
     * @return property value of versionCode
     */
    public String getVersionCode() {
        return versionCode;
    }

    /**
     * Setter method for property <tt>versionCode</tt>.
     * 
     * @param versionCode value to be assigned to property versionCode
     */
    public void setVersionCode(String versionCode) {
        this.versionCode = versionCode;
    }

    /**
     * Getter method for property <tt>dataInfoType</tt>.
     * 
     * @return property value of dataInfoType
     */
    public String getDataInfoType() {
        return dataInfoType;
    }

    /**
     * Setter method for property <tt>dataInfoType</tt>.
     * 
     * @param dataInfoType value to be assigned to property dataInfoType
     */
    public void setDataInfoType(String dataInfoType) {
        this.dataInfoType = dataInfoType;
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
     * Getter method for property <tt>gmt_mModified</tt>.
     * 
     * @return property value of gmt_mModified
     */
    public Date getGmt_mModified() {
        return gmt_mModified;
    }

    /**
     * Setter method for property <tt>gmt_mModified</tt>.
     * 
     * @param gmt_mModified value to be assigned to property gmt_mModified
     */
    public void setGmt_mModified(Date gmt_mModified) {
        this.gmt_mModified = gmt_mModified;
    }

    /** 
     * @see java.lang.Object#toString()
     */
    public String toString() {
        return ToStringUtil.toShortString(this);
    }
}
