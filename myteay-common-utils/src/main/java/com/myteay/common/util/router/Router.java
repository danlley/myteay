/**
 * Copyright (c) 2004-2015 All Rights Reserved.
 */
package com.myteay.common.util.router;

/**
 * 
 * @author Administrator
 * @version $Id: Router.java, v 0.1 2015��10��26�� ����3:48:38 Administrator Exp $
 */
public class Router {

    //    public static void main(String[] args) {
    //        //"http://192.168.1.1/userRpm/SysRebootRpm.htm?Reboot=����·����";
    //
    //        // ��֤���û���������
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
    //            "http://192.168.1.1/userRpm/SysRebootRpm.htm?Reboot=����·����");
    //        //HttpUriRequest request= new HttpGet("http://192.168.1.1/userRpm/StatusRpm.htm?Disconnect=����&wan=1");
    //        //HttpUriRequest request= new HttpGet("http://192.168.1.1/userRpm/StatusRpm.htm?Connect=����&wan=1");
    //        //http://192.168.1.1/userRpm/StatusRpm.htm?Disconnect=����&wan=1
    //
    //        //�����֤��Ϣ
    //        request.addHeader("Authorization", auth);
    //
    //        // ��ӡ������Ϣ   
    //
    //        try {
    //            // �������󣬷�����Ӧ   
    //            HttpResponse response = httpClient.execute(request);
    //
    //            // ��ӡ��Ӧ��Ϣ   
    //            System.out.println(response.getStatusLine());
    //        } catch (ClientProtocolException e) {
    //            // Э�����   
    //            e.printStackTrace();
    //        } catch (IOException e) {
    //            // �����쳣   
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
