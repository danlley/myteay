/**
 * Myteay.com Inc.
 * Copyright (c) 2015-2016 All Rights Reserved.
 */
package com.myteay.common.service.facade.model;

import java.io.Serializable;

import com.myteay.common.service.facade.enums.MtProcessManageTypeEnum;
import com.myteay.common.util.tools.ToStringUtil;

/**
 * 用户二维码注册过程中需要用到的中间消息，用于生成二维码
 * 
 * @author Administrator
 * @version $Id: MtUserRegQRCodeMessage.java, v 0.1 2016年9月6日 下午9:17:51 Administrator Exp $
 */
public class MtUserRegQRCodeMessage implements Serializable {

    /** serialVersionUID */
    private static final long       serialVersionUID = -8802666480557923190L;

    /** 图片存放地址 */
    private String                  storePath;

    /** 默认图片所在位置 */
    private String                  defaultImg;

    /** 二维码携带信息 */
    private String                  content;

    /** 二维码图片的文件名 */
    private String                  filename;

    /** 流程处理管理操作类型枚举 */
    private MtProcessManageTypeEnum manageType       = MtProcessManageTypeEnum.CS_VALIDATE_PROCESS;

    /**
     * 构造方法
     * 
     * @param storePath     图片存放地址
     * @param defaultImg    默认图片所在位置
     * @param content       二维码携带信息
     */
    public MtUserRegQRCodeMessage(String storePath, String defaultImg, String content) {
        this.content = content;
        this.storePath = storePath;
        this.defaultImg = defaultImg;
    }

    /**
     * Getter method for property <tt>filename</tt>.
     * 
     * @return property value of filename
     */
    public String getFilename() {
        return filename;
    }

    /**
     * Setter method for property <tt>filename</tt>.
     * 
     * @param filename value to be assigned to property filename
     */
    public void setFilename(String filename) {
        this.filename = filename;
    }

    /**
     * Getter method for property <tt>manageType</tt>.
     * 
     * @return property value of manageType
     */
    public MtProcessManageTypeEnum getManageType() {
        return manageType;
    }

    /**
     * Setter method for property <tt>manageType</tt>.
     * 
     * @param manageType value to be assigned to property manageType
     */
    public void setManageType(MtProcessManageTypeEnum manageType) {
        this.manageType = manageType;
    }

    /**
     * Getter method for property <tt>storePath</tt>.
     * 
     * @return property value of storePath
     */
    public String getStorePath() {
        return storePath;
    }

    /**
     * Setter method for property <tt>storePath</tt>.
     * 
     * @param storePath value to be assigned to property storePath
     */
    public void setStorePath(String storePath) {
        this.storePath = storePath;
    }

    /**
     * Getter method for property <tt>defaultImg</tt>.
     * 
     * @return property value of defaultImg
     */
    public String getDefaultImg() {
        return defaultImg;
    }

    /**
     * Setter method for property <tt>defaultImg</tt>.
     * 
     * @param defaultImg value to be assigned to property defaultImg
     */
    public void setDefaultImg(String defaultImg) {
        this.defaultImg = defaultImg;
    }

    /**
     * Getter method for property <tt>content</tt>.
     * 
     * @return property value of content
     */
    public String getContent() {
        return content;
    }

    /**
     * Setter method for property <tt>content</tt>.
     * 
     * @param content value to be assigned to property content
     */
    public void setContent(String content) {
        this.content = content;
    }

    /** 
     * @see java.lang.Object#toString()
     */
    public String toString() {
        return ToStringUtil.toShortString(this);
    }
}
