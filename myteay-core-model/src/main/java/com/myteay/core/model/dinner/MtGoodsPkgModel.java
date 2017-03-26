/**
 * Myteay.com Inc.
 * Copyright (c) 2015-2016 All Rights Reserved.
 */
package com.myteay.core.model.dinner;

import java.io.Serializable;
import java.util.Date;

import com.myteay.common.util.comm.ToStringUtil;

/**
 * �ײ���Ϣģ��
 * 
 * @author Administrator
 * @version $Id: MtGoodsPkgModel.java, v 0.1 2016��3��5�� ����9:23:04 Administrator Exp $
 */
public class MtGoodsPkgModel implements Serializable {

    /** serialVersionUID */
    private static final long serialVersionUID = 885917187926980536L;

    /** �ײ�������Ϣ��ˮ�� */
    private String            id;

    /** �ײ����� */
    private String            pkgName;

    /** ��Ʒ�б� */
    private String            goodsId;

    /** �ֻ��۸� */
    private String            priceMobile;

    /** ���̼۸� */
    private String            price;

    /** СͼƬ��ַ */
    private String            picAddr;

    /** ��ͼƬ��ַ */
    private String            picBigAddr;

    /** ������ˮ�� */
    private String            shopId;

    /** �ײʹ���ʱ�� */
    private Date              gmtCreated;

    /** �ײ������ʱ�� */
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
     * Getter method for property <tt>pkgName</tt>.
     * 
     * @return property value of pkgName
     */
    public String getPkgName() {
        return pkgName;
    }

    /**
     * Setter method for property <tt>pkgName</tt>.
     * 
     * @param pkgName value to be assigned to property pkgName
     */
    public void setPkgName(String pkgName) {
        this.pkgName = pkgName;
    }

    /**
     * Getter method for property <tt>goodsId</tt>.
     * 
     * @return property value of goodsId
     */
    public String getGoodsId() {
        return goodsId;
    }

    /**
     * Setter method for property <tt>goodsId</tt>.
     * 
     * @param goodsId value to be assigned to property goodsId
     */
    public void setGoodsId(String goodsId) {
        this.goodsId = goodsId;
    }

    /**
     * Getter method for property <tt>priceMobile</tt>.
     * 
     * @return property value of priceMobile
     */
    public String getPriceMobile() {
        return priceMobile;
    }

    /**
     * Setter method for property <tt>priceMobile</tt>.
     * 
     * @param priceMobile value to be assigned to property priceMobile
     */
    public void setPriceMobile(String priceMobile) {
        this.priceMobile = priceMobile;
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
     * Getter method for property <tt>picBigAddr</tt>.
     * 
     * @return property value of picBigAddr
     */
    public String getPicBigAddr() {
        return picBigAddr;
    }

    /**
     * Setter method for property <tt>picBigAddr</tt>.
     * 
     * @param picBigAddr value to be assigned to property picBigAddr
     */
    public void setPicBigAddr(String picBigAddr) {
        this.picBigAddr = picBigAddr;
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
