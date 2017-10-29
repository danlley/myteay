/**
 * Myteay.com Inc.
 * Copyright (c) 2015-2016 All Rights Reserved.
 */
package com.myteay.common.dal.dinner.dataobject;

import java.io.Serializable;
import java.util.Date;

import com.myteay.common.util.tools.ToStringUtil;

/**
 * ������Ϣ����ģ��
 * 
 * @author Administrator
 * @version $Id: ShopInfoDO.java, v 0.1 2016��3��4�� ����4:57:55 Administrator Exp $
 */
public class ShopInfoDO implements Serializable {

    /** serialVersionUID */
    private static final long serialVersionUID = -5797114231795812326L;

    /** ���̱�� */
    private String            shopId;

    /** �������� */
    private String            shopName;

    /** ���̵�ַ */
    private String            shopAddr;

    /** ���̵绰 */
    private String            shopTel;

    /** ������ */
    private String            cityCode;

    /** ����ʱ�� */
    private Date              gmtCreated;

    /** ����޸�ʱ�� */
    private Date              gmtModified;

    /**
     * Getter method for property <tt>shopId</tt>.
     * 
     * @return property value of shopId
     */
    public String getShopId() {
        return shopId;
    }

    /**
     * Setter method for property <tt>shopId</tt>.
     * 
     * @param shopId value to be assigned to property shopId
     */
    public void setShopId(String shopId) {
        this.shopId = shopId;
    }

    /**
     * Getter method for property <tt>shopName</tt>.
     * 
     * @return property value of shopName
     */
    public String getShopName() {
        return shopName;
    }

    /**
     * Setter method for property <tt>shopName</tt>.
     * 
     * @param shopName value to be assigned to property shopName
     */
    public void setShopName(String shopName) {
        this.shopName = shopName;
    }

    /**
     * Getter method for property <tt>shopAddr</tt>.
     * 
     * @return property value of shopAddr
     */
    public String getShopAddr() {
        return shopAddr;
    }

    /**
     * Setter method for property <tt>shopAddr</tt>.
     * 
     * @param shopAddr value to be assigned to property shopAddr
     */
    public void setShopAddr(String shopAddr) {
        this.shopAddr = shopAddr;
    }

    /**
     * Getter method for property <tt>shopTel</tt>.
     * 
     * @return property value of shopTel
     */
    public String getShopTel() {
        return shopTel;
    }

    /**
     * Setter method for property <tt>shopTel</tt>.
     * 
     * @param shopTel value to be assigned to property shopTel
     */
    public void setShopTel(String shopTel) {
        this.shopTel = shopTel;
    }

    /**
     * Getter method for property <tt>cityCode</tt>.
     * 
     * @return property value of cityCode
     */
    public String getCityCode() {
        return cityCode;
    }

    /**
     * Setter method for property <tt>cityCode</tt>.
     * 
     * @param cityCode value to be assigned to property cityCode
     */
    public void setCityCode(String cityCode) {
        this.cityCode = cityCode;
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
