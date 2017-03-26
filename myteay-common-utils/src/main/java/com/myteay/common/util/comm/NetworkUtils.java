/**
 * Danlley.wei
 * 
 * Copyright (c) 2011-2012 All Rights Reserved.
 */
package com.myteay.common.util.comm;

import java.util.Map;

/**
 * this is a network communication tool, when you login some web site, it will
 * hold on you login session
 * 
 * @author danlley.wei
 * @version $Id: NetworkUtils.java, v 0.1 2012-8-23 PM 9:42:49 Administrator Exp
 *          $
 */
public class NetworkUtils {

    /** char-set of GBK */
    public static final String GBK        = "GBK";

    /** char-set of GB2312 */
    public static final String GB2312     = "gb2312";

    /** char-set of ISO-8859-1 */
    public static final String ISO_8859_1 = "ISO-8859-1";

    /**
     * post type of HTTP request with certain session
     * 
     * <pre>
     * <li>should provide the char-set so that you can get the content properly</li>
     * 
     * @param keywords
     * @return
     */
    public static String sendPostRequest(String url, Map<String, String> context) {

        return null;
    }

    /**
     * the get type of post request, this will never with session infos
     * 
     * <pre>
     * <li>this can be used for get content with the web site that do not need you login</li>
     * 
     * @param httpURL
     * @return
     */
    public static String sendGetRequest(String httpURL) {

        String str = StringUtils.EMPTY_STRING;

        return str;
    }
}
