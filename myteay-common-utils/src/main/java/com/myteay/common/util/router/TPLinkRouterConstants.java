/**
 * Myteay.com Inc.
 * Copyright (c) 2015-2016 All Rights Reserved.
 */
package com.myteay.common.util.router;

/**
 * 
 * @author Administrator
 * @version $Id: TPLinkRouterConstants.java, v 0.1 2016年4月29日 下午11:13:15 Administrator Exp $
 */
public class TPLinkRouterConstants {

    /** 设置向导首页（wan=16 路由器自己选择；wan=2 PPPOE方式；wan=0 动态IP（以太网宽带，自动从网络服务商获取IP地址）;wan=1 静态IP（以太网宽带，网络服务商提供固定IP地址）） */
    public static final String WZD_WANTYPE_RPM = "http://192.168.1.1/userRpm/WzdWanTypeRpm.htm";

    /** 设置拨号账号（acc:上网帐号; psw:上网口令; confirm:确认口令） */
    public static final String WZD_PPPOE_RPM   = "http://192.168.1.1/userRpm/WzdPPPoERpm.htm";
}
