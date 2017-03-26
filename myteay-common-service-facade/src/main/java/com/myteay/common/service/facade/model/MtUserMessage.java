/**
 * Myteay.com Inc.
 * Copyright (c) 2015-2016 All Rights Reserved.
 */
package com.myteay.common.service.facade.model;

import java.io.Serializable;

import com.myteay.common.util.comm.ToStringUtil;

/**
 * 用户信息交互单据
 * 
 * @author Administrator
 * @version $Id: MtUserMessage.java, v 0.1 2016年2月24日 下午9:57:43 Administrator Exp $
 */
public class MtUserMessage implements Serializable {

    /** serialVersionUID */
    private static final long serialVersionUID = -3043617257175902169L;

    /** 用户基本信息 */
    private MtUserBaseInfo    userBaseInfo     = new MtUserBaseInfo();

    /** 用户高级基本信息 */
    private MtUserAdvBaseInfo userAdvInfo      = new MtUserAdvBaseInfo();

    /** 用户扩展信息 */
    private MtUserExtInfo     userExtInfo      = new MtUserExtInfo();

    /**
     * Getter method for property <tt>userBaseInfo</tt>.
     * 
     * @return property value of userBaseInfo
     */
    public MtUserBaseInfo getUserBaseInfo() {
        return userBaseInfo;
    }

    /**
     * Getter method for property <tt>userAdvInfo</tt>.
     * 
     * @return property value of userAdvInfo
     */
    public MtUserAdvBaseInfo getUserAdvInfo() {
        return userAdvInfo;
    }

    /**
     * Getter method for property <tt>userExtInfo</tt>.
     * 
     * @return property value of userExtInfo
     */
    public MtUserExtInfo getUserExtInfo() {
        return userExtInfo;
    }

    /**
     * Setter method for property <tt>userBaseInfo</tt>.
     * 
     * @param userBaseInfo value to be assigned to property userBaseInfo
     */
    public void setUserBaseInfo(MtUserBaseInfo userBaseInfo) {
        this.userBaseInfo = userBaseInfo;
    }

    /**
     * Setter method for property <tt>userAdvInfo</tt>.
     * 
     * @param userAdvInfo value to be assigned to property userAdvInfo
     */
    public void setUserAdvInfo(MtUserAdvBaseInfo userAdvInfo) {
        this.userAdvInfo = userAdvInfo;
    }

    /**
     * Setter method for property <tt>userExtInfo</tt>.
     * 
     * @param userExtInfo value to be assigned to property userExtInfo
     */
    public void setUserExtInfo(MtUserExtInfo userExtInfo) {
        this.userExtInfo = userExtInfo;
    }

    /** 
     * @see java.lang.Object#toString()
     */
    public String toString() {
        return ToStringUtil.toShortString(this);
    }
}
