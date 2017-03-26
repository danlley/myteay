/**
 * Copyright (c) 2004-2015 All Rights Reserved.
 */
package com.myteay.common.util.router;

/**
 * 
 * @author Administrator
 * @version $Id: Router.java, v 0.1 2015年10月26日 下午3:48:38 Administrator Exp $
 */
public class Router {

    //    public static void main(String[] args) {
    //        //"http://192.168.1.1/userRpm/SysRebootRpm.htm?Reboot=重启路由器";
    //
    //        // 验证的用户名和密码
    //
    //        String login_user = "admin";
    //
    //        String login_pw = "weidingrong";
    //        String auth = "Basic " + getBASE64(login_user + ":" + login_pw);
    //
    //        System.out.println(auth);
    //
    //        HttpClient httpClient = new DefaultHttpClient();
    //        HttpUriRequest request = new HttpGet(
    //            "http://192.168.1.1/userRpm/SysRebootRpm.htm?Reboot=重启路由器");
    //        //HttpUriRequest request= new HttpGet("http://192.168.1.1/userRpm/StatusRpm.htm?Disconnect=断线&wan=1");
    //        //HttpUriRequest request= new HttpGet("http://192.168.1.1/userRpm/StatusRpm.htm?Connect=连接&wan=1");
    //        //http://192.168.1.1/userRpm/StatusRpm.htm?Disconnect=断线&wan=1
    //
    //        //添加验证信息
    //        request.addHeader("Authorization", auth);
    //
    //        // 打印请求信息   
    //
    //        try {
    //            // 发送请求，返回响应   
    //            HttpResponse response = httpClient.execute(request);
    //
    //            // 打印响应信息   
    //            System.out.println(response.getStatusLine());
    //        } catch (ClientProtocolException e) {
    //            // 协议错误   
    //            e.printStackTrace();
    //        } catch (IOException e) {
    //            // 网络异常   
    //            e.printStackTrace();
    //        }
    //
    //    }

    public static String getBASE64(String s) {
        if (s == null)
            return null;
        return (new sun.misc.BASE64Encoder()).encode(s.getBytes());
    }

}
