package com.myteay.common.util.comm;

import java.util.Arrays;
import java.util.Comparator;

/**
 * �ַ�����������
 * 
 * @author Administrator
 * @version $Id: StringUtils.java, v 0.1 2012-8-22 ����10:22:33 Administrator Exp $
 */
public class StringUtils {

    /** ���ַ��� */
    public static final String EMPTY_STRING = "";

    /** �����ַ��� */
    public static final String NEW_LINE     = "\r\n";

    /**
     * �ж�������ַ��������Ƿ�Ϊ�ա�
     * @param args ������ִ�
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
     * �ж�������ַ��������Ƿ�Ϊ�ջ�����"null"�ַ�,�����,�ͷ���target����,�������,�ͷ���source������
     */
    public static String chanageNull(String source, String target) {
        if (source == null || source.length() == 0 || source.equalsIgnoreCase("null")) {
            return target;
        } else {
            return source;
        }
    }

    /**
     * ����<, >,n �ַ��ķ�����
     * @param input ��Ҫ���˵��ַ�
     * @return ��ɹ����Ժ���ַ���
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
     * ����ַ����Ƿ�Ϊ<code>null</code>����ַ���<code>""</code>��
     * <pre>
     * StringUtil.isEmpty(null)      = true
     * StringUtil.isEmpty("")        = true
     * StringUtil.isEmpty(" ")       = false
     * StringUtil.isEmpty("bob")     = false
     * StringUtil.isEmpty("  bob  ") = false
     * </pre>
     *
     * @param str Ҫ�����ַ���
     *
     * @return ���Ϊ��, �򷵻�<code>true</code>
     */
    public static boolean isEmpty(String str) {
        return ((str == null) || (str.length() == 0));
    }

    /**
     * ����ַ����Ƿ���<code>null</code>�Ϳ��ַ���<code>""</code>��
     * <pre>
     * StringUtil.isEmpty(null)      = false
     * StringUtil.isEmpty("")        = false
     * StringUtil.isEmpty(" ")       = true
     * StringUtil.isEmpty("bob")     = true
     * StringUtil.isEmpty("  bob  ") = true
     * </pre>
     *
     * @param str Ҫ�����ַ���
     *
     * @return �����Ϊ��, �򷵻�<code>true</code>
     */
    public static boolean isNotEmpty(String str) {
        return ((str != null) && (str.length() > 0));
    }

    /**
     * ����ַ����Ƿ��ǿհף�<code>null</code>�����ַ���<code>""</code>��ֻ�пհ��ַ���
     * <pre>
     * StringUtil.isBlank(null)      = false
     * StringUtil.isBlank("")        = false
     * StringUtil.isBlank(" ")       = false
     * StringUtil.isBlank("bob")     = true
     * StringUtil.isBlank("  bob  ") = true
     * </pre>
     *
     * @param str Ҫ�����ַ���
     *
     * @return ���Ϊ�հ�, �򷵻�<code>true</code>
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
     * ����ַ����Ƿ��ǿհף�<code>null</code>�����ַ���<code>""</code>��ֻ�пհ��ַ���
     * <pre>
     * StringUtil.isBlank(null)      = true
     * StringUtil.isBlank("")        = true
     * StringUtil.isBlank(" ")       = true
     * StringUtil.isBlank("bob")     = false
     * StringUtil.isBlank("  bob  ") = false
     * </pre>
     *
     * @param str Ҫ�����ַ���
     *
     * @return ���Ϊ�հ�, �򷵻�<code>true</code>
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
     * ���ַ�����������
     * e.g. ����ǰ����Improving code with Lambda expressions in Java 8 aa 11 123 122��
     *      ����󣺡�11, 122, 123, 8, aa, code, expressions, Improving, in, Java, Lambda, with��
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
                // ���Դ�Сд����:
                return s1.toLowerCase().compareTo(s2.toLowerCase());
            }
        });
        return String.join(", ", str);
    }
}