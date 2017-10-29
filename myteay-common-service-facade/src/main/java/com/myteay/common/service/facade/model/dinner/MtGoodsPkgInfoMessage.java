/**
 * Myteay.com Inc.
 * Copyright (c) 2015-2016 All Rights Reserved.
 */
package com.myteay.common.service.facade.model.dinner;

import java.io.Serializable;
import java.util.Date;

import com.myteay.common.util.tools.ToStringUtil;

/**
 * 套餐信息交互单据
 * 
 * @author Administrator
 * @version $Id: MtGoodsPkgInfoMessage.java, v 0.1 2016年3月5日 上午9:24:16 Administrator Exp $
 */
public class MtGoodsPkgInfoMessage implements Serializable {

    /** serialVersionUID */
    private static final long serialVersionUID = 5510661805727032144L;

    /** 套餐配置信息流水号 */
    private String            id;

    /** 套餐名称 */
    private String            pkgName;

    /** 商品列表 */
    private String            goodsId;

    /** 手机价格 */
    private String            priceMobile;

    /** 店铺价格 */
    private String            price;

    /** 小图片地址 */
    private String            picAddr;

    /** 大图片地址 */
    private String            picBigAddr;

    /** 店铺流水号 */
    private String            shopId;

    /** 套餐创建时间 */
    private Date              gmtCreated;

    /** 套餐最后变更时间 */
    private Date              gmtModified;

    /** 图片上层路径 */
    private String            imgParentPath;

    /**
     * Getter method for property <tt>imgParentPath</tt>.
     * 
     * @return property value of imgParentPath
     */
    public String getImgParentPath() {
        return imgParentPath;
    }

    /**
     * Setter method for property <tt>imgParentPath</tt>.
     * 
     * @param imgParentPath value to be assigned to property imgParentPath
     */
    public void setImgParentPath(String imgParentPath) {
        this.imgParentPath = imgParentPath;
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
