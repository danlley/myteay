/**
 * Myteay.com Inc.
 * Copyright (c) 2005-2017 All Rights Reserved.
 */
package com.myteay.core.model.user;

import java.io.Serializable;

import com.myteay.common.service.facade.mobile.info.MtRegisterInfo;
import com.myteay.common.service.facade.model.MtUserRegQRCodeMessage;
import com.myteay.common.util.tools.ToStringUtil;

/**
 * 注册用户扩展信息模型
 * 
 * @author danlley
 * @version $Id: MtUserRegExtModel.java, v 0.1 Sep 2, 2017 10:35:24 PM danlley Exp $
 */
public class MtUserRegExtModel implements Serializable {

    /** serialVersionUID */
    private static final long         serialVersionUID = 2967441364307639719L;

    /** 会员联系人列表模型 */
    private MtUserContactModel        mtUserContactModel;

    /** 二维码模型 */
    private MtUserRegQRCodeMessage    mtQrCodeModel;

    /** 携带交互单据，便于后面做问题排查和进行业务扩展 */
    private MtRegisterInfo            registerInfo;

    /** 用户手机基本信息模型 */
    private MtUserMobileBaseInfoModel mtUserMobileBaseInfoModel;

    /**
     * Getter method for property <tt>mtUserMobileBaseInfoModel</tt>.
     * 
     * @return property value of mtUserMobileBaseInfoModel
     */
    public MtUserMobileBaseInfoModel getMtUserMobileBaseInfoModel() {
        return mtUserMobileBaseInfoModel;
    }

    /**
     * Setter method for property <tt>mtUserMobileBaseInfoModel</tt>.
     * 
     * @param mtUserMobileBaseInfoModel value to be assigned to property mtUserMobileBaseInfoModel
     */
    public void setMtUserMobileBaseInfoModel(MtUserMobileBaseInfoModel mtUserMobileBaseInfoModel) {
        this.mtUserMobileBaseInfoModel = mtUserMobileBaseInfoModel;
    }

    /**
     * Getter method for property <tt>registerInfo</tt>.
     * 
     * @return property value of registerInfo
     */
    public MtRegisterInfo getRegisterInfo() {
        return registerInfo;
    }

    /**
     * Setter method for property <tt>registerInfo</tt>.
     * 
     * @param registerInfo value to be assigned to property registerInfo
     */
    public void setRegisterInfo(MtRegisterInfo registerInfo) {
        this.registerInfo = registerInfo;
    }

    /**
     * Getter method for property <tt>mtUserContactModel</tt>.
     * 
     * @return property value of mtUserContactModel
     */
    public MtUserContactModel getMtUserContactModel() {
        return mtUserContactModel;
    }

    /**
     * Setter method for property <tt>mtUserContactModel</tt>.
     * 
     * @param mtUserContactModel value to be assigned to property mtUserContactModel
     */
    public void setMtUserContactModel(MtUserContactModel mtUserContactModel) {
        this.mtUserContactModel = mtUserContactModel;
    }

    /**
     * Getter method for property <tt>mtQrCodeModel</tt>.
     * 
     * @return property value of mtQrCodeModel
     */
    public MtUserRegQRCodeMessage getMtQrCodeModel() {
        return mtQrCodeModel;
    }

    /**
     * Setter method for property <tt>mtQrCodeModel</tt>.
     * 
     * @param mtQrCodeModel value to be assigned to property mtQrCodeModel
     */
    public void setMtQrCodeModel(MtUserRegQRCodeMessage mtQrCodeModel) {
        this.mtQrCodeModel = mtQrCodeModel;
    }

    /** 
     * @see java.lang.Object#toString()
     */
    public String toString() {
        return ToStringUtil.toShortString(this);
    }
}
