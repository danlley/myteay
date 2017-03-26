/**
 * Myteay.com Inc.
 * Copyright (c) 2015-2015 All Rights Reserved.
 */
package com.myteay.core.model.user;

import java.io.Serializable;

import com.myteay.common.util.comm.ToStringUtil;

/**
 * ��Աģ��
 * 
 * @author Administrator
 * @version $Id: MtUserModel.java, v 0.1 2015��11��15�� ����5:41:10 Administrator Exp $
 */
public class MtUserModel implements Serializable {

    /** serialVersionUID */
    private static final long  serialVersionUID   = 3422034121035104833L;

    /** ��Ա������Ϣģ�� */
    private MtUserBaseModel    mtUserBaseModel    = new MtUserBaseModel();

    /** ��Ա�߼���Ϣģ�� */
    private MtUserAdvBaseModel mtUserAdvBaseModel = new MtUserAdvBaseModel();

    /** ��Ա��չ��Ϣģ�� */
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
