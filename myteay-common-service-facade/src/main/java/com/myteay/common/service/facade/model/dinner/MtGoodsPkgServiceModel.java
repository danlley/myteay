/**
 * Myteay.com Inc.
 * Copyright (c) 2015-2016 All Rights Reserved.
 */
package com.myteay.common.service.facade.model.dinner;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import com.myteay.common.util.comm.ToStringUtil;

/**
 * 套餐服务模型
 * 
 * @author Administrator
 * @version $Id: MtGoodsPkgServiceModel.java, v 0.1 2016年3月20日 下午9:34:44 Administrator Exp $
 */
public class MtGoodsPkgServiceModel implements Serializable {

    /** serialVersionUID */
    private static final long        serialVersionUID = 1463103948286849319L;

    /** 套餐配置信息流水号 */
    private String                   id;

    /** 套餐名称 */
    private String                   pkgName;

    /** 商品列表 */
    private String                   goodsId;

    /** 手机价格 */
    private String                   priceMobile;

    /** 店铺价格 */
    private String                   price;

    /** 小图片地址 */
    private String                   picAddr;

    /** 大图片地址 */
    private String                   picBigAddr;

    /** 店铺流水号 */
    private String                   shopId;

    /** 套餐创建时间 */
    private Date                     gmtCreated;

    /** 套餐最后变更时间 */
    private Date                     gmtModified;

    /** 套餐单品信息 */
    private List<MtGoodsInfoMessage> mtGoodsInfoList;

    /**
     * Getter method for property <tt>mtGoodsInfoList</tt>.
     * 
     * @return property value of mtGoodsInfoList
     */
    public List<MtGoodsInfoMessage> getMtGoodsInfoList() {
        return mtGoodsInfoList;
    }

    /**
     * Setter method for property <tt>mtGoodsInfoList</tt>.
     * 
     * @param mtGoodsInfoList value to be assigned to property mtGoodsInfoList
     */
    public void setMtGoodsInfoList(List<MtGoodsInfoMessage> mtGoodsInfoList) {
        this.mtGoodsInfoList = mtGoodsInfoList;
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
