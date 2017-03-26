package com.myteay.common.util.comm;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * ����<code>Logger</code>�Ĺ���.  ��commons-logging��<code>LogFactory</code>����.
 * ����Ե���ԭ����<code>LoggerFactory.getLog</code>����, ���������ܵõ���չ�Ĺ���.
 * ����<code>LoggerFactory.getLogger</code>����Եõ���չ��<code>Logger</code>�Ĺ���.
 *
 * @author Michael Zhou
 * @version $Id: LoggerFactory.java 509 2004-02-16 05:42:07Z baobao $
 */
public abstract class LoggerFactory extends LogFactory {
    /**
     * ȡ����չ��<code>Logger</code>.
     *
     * @param clazz ����<code>Logger</code>���Ƶ���
     *
     * @return ��չ��<code>Logger</code>ʵ��
     */
    public static Logger getLogger(Class<?> clazz) {
        return getLogger(getLog(clazz));
    }

    /**
     * ȡ����չ��<code>Logger</code>.
     *
     * @param name <code>Logger</code>����
     *
     * @return ��չ��<code>Logger</code>ʵ��
     */
    public static Logger getLogger(String name) {
        return getLogger(getLog(name));
    }

    /**
     * ȡ����չ��<code>Logger</code>, ���ָ����log�����Ѿ�ʵ����<code>Logger</code>�ӿ�, ��ֱ�ӷ���, �����װһ���ٷ���.
     *
     * @param log ʵ��commons-logging��<code>Log</code>�ӿڵĶ���
     *
     * @return ��չ��<code>Logger</code>ʵ��
     */
    private static Logger getLogger(Log log) {
        if (log instanceof Logger) {
            return (Logger) log;
        }

        return new LoggerWrapper(log);
    }
}
