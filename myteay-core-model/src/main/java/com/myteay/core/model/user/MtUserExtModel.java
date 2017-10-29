/**
 * Myteay.com Inc.
 * Copyright (c) 2015-2015 All Rights Reserved.
 */
package com.myteay.core.model.user;

import java.io.Serializable;

import com.myteay.common.util.tools.ToStringUtil;

/**
 * 会员扩展模型
 * 
 * @author Administrator
 * @version $Id: MtUserExtModel.java, v 0.1 2015年11月15日 下午5:56:40 Administrator Exp $
 */
public class MtUserExtModel implements Serializable {

    /** serialVersionUID */
    private static final long serialVersionUID = 5809152573357176803L;

    /** 
     * @see java.lang.Object#toString()
     */
    public String toString() {
        return ToStringUtil.toShortString(this);
    }
}
