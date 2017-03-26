package com.myteay.common.util.comm;

import java.text.MessageFormat;
import java.util.MissingResourceException;
import java.util.ResourceBundle;

/**
 * ��<code>ResourceBundle</code>����Ϣ�ַ����йصĹ����ࡣ
 *
 * @author Michael Zhou
 * @version $Id: MessageUtil.java 1201 2004-12-09 01:39:09Z baobao $
 */
public class MessageUtil {
    /* ============================================================================ */
    /* �������й�resource bundle�ķ���                                              */
    /* ============================================================================ */

    /**
     * ��<code>ResourceBundle</code>��ȡ���ַ�������ʹ��<code>MessageFormat</code>��ʽ���ַ���.
     *
     * @param bundle resource bundle
     * @param key Ҫ���ҵļ�
     * @param params ������
     *
     * @return key��Ӧ���ַ��������keyΪ<code>null</code>��resource
     *         bundleΪ<code>null</code>����resource keyδ�ҵ����򷵻�<code>key</code>
     */
    public static String getMessage(ResourceBundle bundle, String key, Object[] params) {
        if ((bundle == null) || (key == null)) {
            return key;
        }

        try {
            String message = bundle.getString(key);

            return formatMessage(message, params);
        } catch (MissingResourceException e) {
            return key;
        }
    }

    /**
     * ��<code>ResourceBundle</code>��ȡ���ַ�������ʹ��<code>MessageFormat</code>��ʽ���ַ���.
     *
     * @param bundle resource bundle
     * @param key Ҫ���ҵļ�
     * @param param1 ����1
     *
     * @return key��Ӧ���ַ��������keyΪ<code>null</code>��resource
     *         bundleΪ<code>null</code>���򷵻�<code>null</code>�����resource keyδ�ҵ����򷵻�<code>key</code>
     */
    public static String getMessage(ResourceBundle bundle, String key, Object param1) {
        return getMessage(bundle, key, new Object[] { param1 });
    }

    /**
     * ��<code>ResourceBundle</code>��ȡ���ַ�������ʹ��<code>MessageFormat</code>��ʽ���ַ���.
     *
     * @param bundle resource bundle
     * @param key Ҫ���ҵļ�
     * @param param1 ����1
     * @param param2 ����2
     *
     * @return key��Ӧ���ַ��������keyΪ<code>null</code>��resource
     *         bundleΪ<code>null</code>���򷵻�<code>null</code>�����resource keyδ�ҵ����򷵻�<code>key</code>
     */
    public static String getMessage(ResourceBundle bundle, String key, Object param1, Object param2) {
        return getMessage(bundle, key, new Object[] { param1, param2 });
    }

    /**
     * ��<code>ResourceBundle</code>��ȡ���ַ�������ʹ��<code>MessageFormat</code>��ʽ���ַ���.
     *
     * @param bundle resource bundle
     * @param key Ҫ���ҵļ�
     * @param param1 ����1
     * @param param2 ����2
     * @param param3 ����3
     *
     * @return key��Ӧ���ַ��������keyΪ<code>null</code>��resource
     *         bundleΪ<code>null</code>���򷵻�<code>null</code>�����resource keyδ�ҵ����򷵻�<code>key</code>
     */
    public static String getMessage(ResourceBundle bundle, String key, Object param1,
                                    Object param2, Object param3) {
        return getMessage(bundle, key, new Object[] { param1, param2, param3 });
    }

    /**
     * ��<code>ResourceBundle</code>��ȡ���ַ�������ʹ��<code>MessageFormat</code>��ʽ���ַ���.
     *
     * @param bundle resource bundle
     * @param key Ҫ���ҵļ�
     * @param param1 ����1
     * @param param2 ����2
     * @param param3 ����3
     * @param param4 ����4
     *
     * @return key��Ӧ���ַ��������keyΪ<code>null</code>��resource
     *         bundleΪ<code>null</code>���򷵻�<code>null</code>�����resource keyδ�ҵ����򷵻�<code>key</code>
     */
    public static String getMessage(ResourceBundle bundle, String key, Object param1,
                                    Object param2, Object param3, Object param4) {
        return getMessage(bundle, key, new Object[] { param1, param2, param3, param4 });
    }

    /**
     * ��<code>ResourceBundle</code>��ȡ���ַ�������ʹ��<code>MessageFormat</code>��ʽ���ַ���.
     *
     * @param bundle resource bundle
     * @param key Ҫ���ҵļ�
     * @param param1 ����1
     * @param param2 ����2
     * @param param3 ����3
     * @param param4 ����4
     * @param param5 ����5
     *
     * @return key��Ӧ���ַ��������keyΪ<code>null</code>��resource
     *         bundleΪ<code>null</code>���򷵻�<code>null</code>�����resource keyδ�ҵ����򷵻�<code>key</code>
     */
    public static String getMessage(ResourceBundle bundle, String key, Object param1,
                                    Object param2, Object param3, Object param4, Object param5) {
        return getMessage(bundle, key, new Object[] { param1, param2, param3, param4, param5 });
    }

    /* ============================================================================ */
    /* ��������MessageFormat��ʽ���ַ����ķ���                                      */
    /* ============================================================================ */

    /**
     * ʹ��<code>MessageFormat</code>��ʽ���ַ���.
     *
     * @param message Ҫ��ʽ�����ַ���
     * @param params ������
     *
     * @return ��ʽ�����ַ��������messageΪ<code>null</code>���򷵻�<code>null</code>
     */
    public static String formatMessage(String message, Object[] params) {
        if ((message == null) || (params == null) || (params.length == 0)) {
            return message;
        }

        return MessageFormat.format(message, params);
    }

    /**
     * ʹ��<code>MessageFormat</code>��ʽ���ַ���.
     *
     * @param message Ҫ��ʽ�����ַ���
     * @param param1 ����1
     *
     * @return ��ʽ�����ַ��������messageΪ<code>null</code>���򷵻�<code>null</code>
     */
    public static String formatMessage(String message, Object param1) {
        return formatMessage(message, new Object[] { param1 });
    }

    /**
     * ʹ��<code>MessageFormat</code>��ʽ���ַ���.
     *
     * @param message Ҫ��ʽ�����ַ���
     * @param param1 ����1
     * @param param2 ����2
     *
     * @return ��ʽ�����ַ��������messageΪ<code>null</code>���򷵻�<code>null</code>
     */
    public static String formatMessage(String message, Object param1, Object param2) {
        return formatMessage(message, new Object[] { param1, param2 });
    }

    /**
     * ʹ��<code>MessageFormat</code>��ʽ���ַ���.
     *
     * @param message Ҫ��ʽ�����ַ���
     * @param param1 ����1
     * @param param2 ����2
     * @param param3 ����3
     *
     * @return ��ʽ�����ַ��������messageΪ<code>null</code>���򷵻�<code>null</code>
     */
    public static String formatMessage(String message, Object param1, Object param2, Object param3) {
        return formatMessage(message, new Object[] { param1, param2, param3 });
    }

    /**
     * ʹ��<code>MessageFormat</code>��ʽ���ַ���.
     *
     * @param message Ҫ��ʽ�����ַ���
     * @param param1 ����1
     * @param param2 ����2
     * @param param3 ����3
     * @param param4 ����4
     *
     * @return ��ʽ�����ַ��������messageΪ<code>null</code>���򷵻�<code>null</code>
     */
    public static String formatMessage(String message, Object param1, Object param2, Object param3,
                                       Object param4) {
        return formatMessage(message, new Object[] { param1, param2, param3, param4 });
    }

    /**
     * ʹ��<code>MessageFormat</code>��ʽ���ַ���.
     *
     * @param message Ҫ��ʽ�����ַ���
     * @param param1 ����1
     * @param param2 ����2
     * @param param3 ����3
     * @param param4 ����4
     * @param param5 ����5
     *
     * @return ��ʽ�����ַ��������messageΪ<code>null</code>���򷵻�<code>null</code>
     */
    public static String formatMessage(String message, Object param1, Object param2, Object param3,
                                       Object param4, Object param5) {
        return formatMessage(message, new Object[] { param1, param2, param3, param4, param5 });
    }
}
