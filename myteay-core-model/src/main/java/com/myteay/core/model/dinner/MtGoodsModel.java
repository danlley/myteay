/**
 * Myteay.com Inc.
 * Copyright (c) 2015-2016 All Rights Reserved.
 */
package com.myteay.core.model.dinner;

import java.io.Serializable;
import java.util.Date;

import com.myteay.common.util.tools.ToStringUtil;

/**
 * 商品信息模型
 * 
 * @author Administrator
 * @version $Id: MtGoodsModel.java, v 0.1 2016年3月5日 上午12:27:23 Administrator Exp $
 */
public class MtGoodsModel implements Serializable {

    /** serialVersionUID */
    private static final long serialVersionUID = 2675557765305071957L;

    /** ID流水号 */
    private String            id;

    /** 店铺流水号 */
    private String            shopId;

    /** 图片地址 */
    private String            picAddr;

    /** 商品标题 */
    private String            goodsTitle;

    /** 价格 */
    private String            price;

    /** 备注 */
    private String            summary;

    /** 上架时间 */
    private Date              gmtCreated;

    /** 最后修改时间 */
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
