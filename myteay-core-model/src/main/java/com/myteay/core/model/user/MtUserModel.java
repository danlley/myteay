/**
 * Myteay.com Inc.
 * Copyright (c) 2015-2015 All Rights Reserved.
 */
package com.myteay.core.model.user;

import java.io.Serializable;

import com.myteay.common.util.tools.ToStringUtil;

/**
 * 会员模型
 * 
 * @author Administrator
 * @version $Id: MtUserModel.java, v 0.1 2015年11月15日 下午5:41:10 Administrator Exp $
 */
public class MtUserModel implements Serializable {

    /** serialVersionUID */
    private static final long  serialVersionUID   = 3422034121035104833L;

    /** 会员基本信息模型 */
    private MtUserBaseModel    mtUserBaseModel    = new MtUserBaseModel();

    /** 会员高级信息模型 */
    private MtUserAdvBaseModel mtUserAdvBaseModel = new MtUserAdvBaseModel();

    /** 会员扩展信息模型 */
    private MtUserExtModel     mtUserExtModel     = new MtUserExtModel();

    /**
     * Getter method for property <tt>mtUserBaseModel</tt>.
     * 
     * @return property value of mtUserBaseModel
     */
    public MtUserBaseModel getMtUserBaseModel() {
        return mtUserBaseModel;
    }

    /**
     * Setter method for property <tt>mtUserBaseModel</tt>.
     * 
     * @param mtUserBaseModel value to be assigned to property mtUserBaseModel
     */
    public void setMtUserBaseModel(MtUserBaseModel mtUserBaseModel) {
        this.mtUserBaseModel = mtUserBaseModel;
    }

    /**
     * Getter method for property <tt>mtUserAdvBaseModel</tt>.
     * 
     * @return property value of mtUserAdvBaseModel
     */
    public MtUserAdvBaseModel getMtUserAdvBaseModel() {
        return mtUserAdvBaseModel;
    }

    /**
     * Setter method for property <tt>mtUserAdvBaseModel</tt>.
     * 
     * @param mtUserAdvBaseModel value to be assigned to property mtUserAdvBaseModel
     */
    public void setMtUserAdvBaseModel(MtUserAdvBaseModel mtUserAdvBaseModel) {
        this.mtUserAdvBaseModel = mtUserAdvBaseModel;
    }

    /**
     * Getter method for property <tt>mtUserExtModel</tt>.
     * 
     * @return property value of mtUserExtModel
     */
    public MtUserExtModel getMtUserExtModel() {
        return mtUserExtModel;
    }

    /**
     * Setter method for property <tt>mtUserExtModel</tt>.
     * 
     * @param mtUserExtModel value to be assigned to property mtUserExtModel
     */
    public void setMtUserExtModel(MtUserExtModel mtUserExtModel) {
        this.mtUserExtModel = mtUserExtModel;
    }

    /** 
     * @see java.lang.Object#toString()
     */
    public String toString() {
        return ToStringUtil.toShortString(this);
    }
}
