package com.myteay.common.util.comm;

import java.util.ResourceBundle;

import org.apache.commons.logging.Log;

/**
 * ��չ��commons-logging��<code>Log</code>�ӿ�, ���������¹���:
 * 
 * <ul>
 * <li>
 * <code>ResourceBundle</code>֧��
 * </li>
 * <li>
 * <code>NDC</code>, <code>MDC</code>֧��
 * </li>
 * </ul>
 * 
 *
 * @author Michael Zhou
 * @version $Id: Logger.java 509 2004-02-16 05:42:07Z baobao $
 */
public interface Logger extends Log {
    /**
     * ���trace level��log��Ϣ.
     *
     * @param key ����log��Ϣ��resource bundle key
     * @param params ��������
     */
    void trace(Object key, Object[] params);

    /**
     * ���trace level��log��Ϣ.
     *
     * @param key ����log��Ϣ��resource bundle key
     * @param params ��������
     * @param cause �쳣
     */
    void trace(Object key, Object[] params, Throwable cause);

    /**
     * ���debug level��log��Ϣ.
     *
     * @param key ����log��Ϣ��resource bundle key
     * @param params ��������
     */
    void debug(Object key, Object[] params);

    /**
     * ���debug level��log��Ϣ.
     *
     * @param key ����log��Ϣ��resource bundle key
     * @param params ��������
     * @param cause �쳣
     */
    void debug(Object key, Object[] params, Throwable cause);

    /**
     * ���info level��log��Ϣ.
     *
     * @param key ����log��Ϣ��resource bundle key
     * @param params ��������
     */
    void info(Object key, Object[] params);

    /**
     * ���info level��log��Ϣ.
     *
     * @param key ����log��Ϣ��resource bundle key
     * @param params ��������
     * @param cause �쳣
     */
    void info(Object key, Object[] params, Throwable cause);

    /**
     * ���warn level��log��Ϣ.
     *
     * @param key ����log��Ϣ��resource bundle key
     * @param params ��������
     */
    void warn(Object key, Object[] params);

    /**
     * ���warn level��log��Ϣ.
     *
     * @param key ����log��Ϣ��resource bundle key
     * @param params ��������
     * @param cause �쳣
     */
    void warn(Object key, Object[] params, Throwable cause);

    /**
     * ���error level��log��Ϣ.
     *
     * @param key ����log��Ϣ��resource bundle key
     * @param params ��������
     */
    void error(Object key, Object[] params);

    /**
     * ���error level��log��Ϣ.
     *
     * @param key ����log��Ϣ��resource bundle key
     * @param params ��������
     * @param cause �쳣
     */
    void error(Object key, Object[] params, Throwable cause);

    /**
     * ���fatal level��log��Ϣ.
     *
     * @param key ����log��Ϣ��resource bundle key
     * @param params ��������
     */
    void fatal(Object key, Object[] params);

    /**
     * ���fatal level��log��Ϣ.
     *
     * @param key ����log��Ϣ��resource bundle key
     * @param params ��������
     * @param cause �쳣
     */
    void fatal(Object key, Object[] params, Throwable cause);

    /**
     * ȡ��logger��Ӧ����Դ��.
     *
     * @return ��Դ��
     */
    ResourceBundle getResourceBundle();

    /**
     * ����logger��Ӧ����Դ��.
     *
     * @param bundle ��Դ��
     */
    void setResourceBundle(ResourceBundle bundle);

    /**
     * ȡ��logger���ַ�����ʾ, ͨ������logger������.
     *
     * @return logger���ַ�����ʾ
     */
    String toString();
}
