/**
 * Myteay.com Inc.
 * Copyright (c) 2005-2017 All Rights Reserved.
 */
package com.myteay.core.model.user;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.myteay.common.util.tools.ToStringUtil;

/**
 * 联系人模型
 * 
 * @author danlley
 * @version $Id: MtUserContactModel.java, v 0.1 Sep 2, 2017 3:21:00 PM danlley Exp $
 */
public class MtUserContactModel implements Serializable {

    /** serialVersionUID */
    private static final long              serialVersionUID  = -8731688618997948346L;

    /** 会员ID */
    private String                         userid;

    /** 会员手机号 */
    private String                         parentMobile;

    /** 会员对应联系人列表 */
    private List<MtUserSingleContactModel> singleContactList = new ArrayList<MtUserSingleContactModel>();

    /**
     * Getter method for property <tt>userid</tt>.
     * 
     * @return property value of userid
     */
    public String getUserid() {
        return userid;
    }

    /**
     * Setter method for property <tt>userid</tt>.
     * 
     * @param userid value to be assigned to property userid
     */
    public void setUserid(String userid) {
        this.userid = userid;
    }

    /**
     * Getter method for property <tt>parentMobile</tt>.
     * 
     * @return property value of parentMobile
     */
    public String getParentMobile() {
        return parentMobile;
    }

    /**
     * Setter method for property <tt>parentMobile</tt>.
     * 
     * @param parentMobile value to be assigned to property parentMobile
     */
    public void setParentMobile(String parentMobile) {
        this.parentMobile = parentMobile;
    }

    /**
     * Getter method for property <tt>singleContactList</tt>.
     * 
     * @return property value of singleContactList
     */
    public List<MtUserSingleContactModel> getSingleContactList() {
        return singleContactList;
    }

    /**
     * Setter method for property <tt>singleContactList</tt>.
     * 
     * @param singleContactList value to be assigned to property singleContactList
     */
    public void setSingleContactList(List<MtUserSingleContactModel> singleContactList) {
        this.singleContactList = singleContactList;
    }

    /** 
     * @see java.lang.Object#toString()
     */
    public String toString() {
        return ToStringUtil.toShortString(this);
    }
}
