/**
 * Myteay.com Inc.
 * Copyright (c) 2015-2016 All Rights Reserved.
 */
package com.myteay.common.service.facade.model;

import java.io.Serializable;

import com.myteay.common.util.comm.ToStringUtil;

/**
 * 用户扩展信息
 * 
 * @author Administrator
 * @version $Id: MtUserExtInfo.java, v 0.1 2016年2月24日 下午10:02:35 Administrator Exp $
 */
public class MtUserExtInfo implements Serializable {

    /** serialVersionUID */
    private static final long serialVersionUID = -2279947679745688986L;

    /** 
     * @see java.lang.Object#toString()
     */
    public String toString() {
        return ToStringUtil.toShortString(this);
    }
}
