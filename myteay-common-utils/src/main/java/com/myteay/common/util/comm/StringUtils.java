package com.myteay.common.util.comm;

import java.util.Arrays;
import java.util.Comparator;

/**
 * 字符串处理工具类
 * 
 * @author Administrator
 * @version $Id: StringUtils.java, v 0.1 2012-8-22 下午10:22:33 Administrator Exp $
 */
public class StringUtils {

    /** 空字符串 */
    public static final String EMPTY_STRING = "";

    /** 换行字符串 */
    public static final String NEW_LINE     = "\r\n";

    /**
     * 判断输入的字符串参数是否为空。
     * @param args 输入的字串
     * @return true/false
     */
    public static boolean validateNull(String args) {
        if (args == null || args.length() == 0) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * 判断输入的字符串参数是否为空或者是"null"字符,如果是,就返回target参数,如果不是,就返回source参数。
     */
    public static String chanageNull(String source, String target) {
        if (source == null || source.length() == 0 || source.equalsIgnoreCase("null")) {
            return target;
        } else {
            return source;
        }
    }

    /**
     * 过滤<, >,n 字符的方法。
     * @param input 需要过滤的字符
     * @return 完成过滤以后的字符串
     */
    public static String filterHtml(String input) {
        if (input == null) {
            return null;
        }
        if (input.length() == 0) {
            return input;
        }
        input = input.replaceAll("&", "&amp;");
        input = input.replaceAll("<", "&lt;");
        input = input.replaceAll(">", "&gt;");
        input = input.replaceAll(" ", "&nbsp;");
        input = input.replaceAll("'", "&#39;");
        input = input.replaceAll("\"", "&quot;");
        input = input.replaceAll("n", "<br>");
        return input;
    }

    /**
     * 检查字符串是否为<code>null</code>或空字符串<code>""</code>。
     * <pre>
     * StringUtil.isEmpty(null)      = true
     * StringUtil.isEmpty("")        = true
     * StringUtil.isEmpty(" ")       = false
     * StringUtil.isEmpty("bob")     = false
     * StringUtil.isEmpty("  bob  ") = false
     * </pre>
     *
     * @param str 要检查的字符串
     *
     * @return 如果为空, 则返回<code>true</code>
     */
    public static boolean isEmpty(String str) {
        return ((str == null) || (str.length() == 0));
    }

    /**
     * 检查字符串是否不是<code>null</code>和空字符串<code>""</code>。
     * <pre>
     * StringUtil.isEmpty(null)      = false
     * StringUtil.isEmpty("")        = false
     * StringUtil.isEmpty(" ")       = true
     * StringUtil.isEmpty("bob")     = true
     * StringUtil.isEmpty("  bob  ") = true
     * </pre>
     *
     * @param str 要检查的字符串
     *
     * @return 如果不为空, 则返回<code>true</code>
     */
    public static boolean isNotEmpty(String str) {
        return ((str != null) && (str.length() > 0));
    }

    /**
     * 检查字符串是否不是空白：<code>null</code>、空字符串<code>""</code>或只有空白字符。
     * <pre>
     * StringUtil.isBlank(null)      = false
     * StringUtil.isBlank("")        = false
     * StringUtil.isBlank(" ")       = false
     * StringUtil.isBlank("bob")     = true
     * StringUtil.isBlank("  bob  ") = true
     * </pre>
     *
     * @param str 要检查的字符串
     *
     * @return 如果为空白, 则返回<code>true</code>
     */
    public static boolean isNotBlank(String str) {
        int length;

        if ((str == null) || ((length = str.length()) == 0)) {
            return false;
        }

        for (int i = 0; i < length; i++) {
            if (!Character.isWhitespace(str.charAt(i))) {
                return true;
            }
        }

        return false;
    }

    /**
     * 检查字符串是否是空白：<code>null</code>、空字符串<code>""</code>或只有空白字符。
     * <pre>
     * StringUtil.isBlank(null)      = true
     * StringUtil.isBlank("")        = true
     * StringUtil.isBlank(" ")       = true
     * StringUtil.isBlank("bob")     = false
     * StringUtil.isBlank("  bob  ") = false
     * </pre>
     *
     * @param str 要检查的字符串
     *
     * @return 如果为空白, 则返回<code>true</code>
     */
    public static boolean isBlank(String str) {
        int length;

        if ((str == null) || ((length = str.length()) == 0)) {
            return true;
        }

        for (int i = 0; i < length; i++) {
            if (!Character.isWhitespace(str.charAt(i))) {
                return false;
            }
        }

        return true;
    }

    /**
     * 对字符串进行排序
     * e.g. 排序前：“Improving code with Lambda expressions in Java 8 aa 11 123 122”
     *      排序后：“11, 122, 123, 8, aa, code, expressions, Improving, in, Java, Lambda, with”
     * 
     * @param str
     * @param splitor
     * @param newSplitor
     * @return
     */
    public static String sort(String str, String splitor, String newSplitor) {
        if (isBlank(str)) {
            return str;
        }
        String[] oldWay = str.split(splitor);
        Arrays.sort(oldWay, new Comparator<String>() {
            @Override
            public int compare(String s1, String s2) {
                // 忽略大小写排序:
                return s1.toLowerCase().compareTo(s2.toLowerCase());
            }
        });
        return String.join(", ", str);
    }
}