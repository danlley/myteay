/**
 * Myteay.com Inc.
 * Copyright (c) 2015-2016 All Rights Reserved.
 */
package com.myteay.common.dal.dinner.dataobject;

import java.io.Serializable;
import java.util.Date;

import com.myteay.common.util.tools.ToStringUtil;

/**
 * ��Ʒ��Ϣ����ģ��
 * 
 * @author Administrator
 * @version $Id: GoodsInfoDO.java, v 0.1 2016��3��5�� ����12:28:53 Administrator Exp $
 */
public class GoodsInfoDO implements Serializable {

    /** serialVersionUID */
    private static final long serialVersionUID = 4624303532159349944L;

    /** ID��ˮ�� */
    private String            id;

    /** ������ˮ�� */
    private String            shopId;

    /** ͼƬ��ַ */
    private String            picAddr;

    /** ��Ʒ���� */
    private String            goodsTitle;

    /** �۸� */
    private String            price;

    /** ��ע */
    private String            summary;

    /** �ϼ�ʱ�� */
    private Date              gmtCreated;

    /** ����޸�ʱ�� */
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
     * Getter method for property <tt>picAddr</tt>.
     * 
     * @return property value of picAddr
     */
    public String getPicAddr() {
        return picAddr;
    }

    /**
     * Setter method for property <tt>picAddr</tt>.
     * 
     * @param picAddr value to be assigned to property picAddr
     */
    public void setPicAddr(String picAddr) {
        this.picAddr = picAddr;
    }

    /**
     * Getter method for property <tt>goodsTitle</tt>.
     * 
     * @return property value of goodsTitle
     */
    public String getGoodsTitle() {
        return goodsTitle;
    }

    /**
     * Setter method for property <tt>goodsTitle</tt>.
     * 
     * @param goodsTitle value to be assigned to property goodsTitle
     */
    public void setGoodsTitle(String goodsTitle) {
        this.goodsTitle = goodsTitle;
    }

    /**
     * Getter method for property <tt>price</tt>.
     * 
     * @return property value of price
     */
    public String getPrice() {
        return price;
    }

    /**
     * Setter method for property <tt>price</tt>.
     * 
     * @param price value to be assigned to property price
     */
    public void setPrice(String price) {
        this.price = price;
    }

    /**
     * Getter method for property <tt>summary</tt>.
     * 
     * @return property value of summary
     */
    public String getSummary() {
        return summary;
    }

    /**
     * Setter method for property <tt>summary</tt>.
     * 
     * @param summary value to be assigned to property summary
     */
    public void setSummary(String summary) {
        this.summary = summary;
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
