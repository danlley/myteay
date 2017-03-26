package com.myteay.common.util.comm;

import java.util.ResourceBundle;

import org.apache.commons.logging.Log;

/**
 * ��װcommons-logging��<code>Log</code>��, ʵ����չ��log����.
 *
 * @author Michael Zhou
 * @version $Id: LoggerWrapper.java 509 2004-02-16 05:42:07Z baobao $
 */
public class LoggerWrapper implements Logger {
    private Log            log;
    private ResourceBundle bundle;

    /**
     * ���캯��.
     *
     * @param log ����װ��<code>Log</code>����
     */
    public LoggerWrapper(Log log) {
        this.log = log;
    }

    /**
     * �ж�trace level�Ƿ񱻴�.
     *
     * @return ���trace level����, �򷵻�<code>true</code>
     */
    public boolean isTraceEnabled() {
        return log.isTraceEnabled();
    }

    /**
     * �ж�debug level�Ƿ񱻴�.
     *
     * @return ���debug level����, �򷵻�<code>true</code>
     */
    public boolean isDebugEnabled() {
        return log.isDebugEnabled();
    }

    /**
     * �ж�info level�Ƿ񱻴�.
     *
     * @return ���info level����, �򷵻�<code>true</code>
     */
    public boolean isInfoEnabled() {
        return log.isInfoEnabled();
    }

    /**
     * �ж�warn level�Ƿ񱻴�.
     *
     * @return ���warn level����, �򷵻�<code>true</code>
     */
    public boolean isWarnEnabled() {
        return log.isWarnEnabled();
    }

    /**
     * �ж�error level�Ƿ񱻴�.
     *
     * @return ���error level����, �򷵻�<code>true</code>
     */
    public boolean isErrorEnabled() {
        return log.isErrorEnabled();
    }

    /**
     * �ж�fatal level�Ƿ񱻴�.
     *
     * @return ���fatal level����, �򷵻�<code>true</code>
     */
    public boolean isFatalEnabled() {
        return log.isFatalEnabled();
    }

    /**
     * ���trace level��log��Ϣ.
     *
     * @param message log��Ϣ
     */
    public void trace(Object message) {
        log.trace(message);
    }

    /**
     * ���trace level��log��Ϣ.
     *
     * @param message log��Ϣ
     * @param cause �쳣
     */
    public void trace(Object message, Throwable cause) {
        log.trace(message, cause);
    }

    /**
     * ���trace level��log��Ϣ.
     *
     * @param key ����log��Ϣ��resource bundle key
     * @param params ��������
     */
    public void trace(Object key, Object[] params) {
        if (log.isTraceEnabled()) {
            log.trace(LoggerUtil.getMessage(this, key, params));
        }
    }

    /**
     * ���trace level��log��Ϣ.
     *
     * @param key ����log��Ϣ��resource bundle key
     * @param params ��������
     * @param cause �쳣
     */
    public void trace(Object key, Object[] params, Throwable cause) {
        if (log.isTraceEnabled()) {
            log.trace(LoggerUtil.getMessage(this, key, params), cause);
        }
    }

    /**
     * ���debug level��log��Ϣ.
     *
     * @param message log��Ϣ
     */
    public void debug(Object message) {
        log.debug(message);
    }

    /**
     * ���debug level��log��Ϣ.
     *
     * @param message log��Ϣ
     * @param cause �쳣
     */
    public void debug(Object message, Throwable cause) {
        log.debug(message, cause);
    }

    /**
     * ���debug level��log��Ϣ.
     *
     * @param key ����log��Ϣ��resource bundle key
     * @param params ��������
     */
    public void debug(Object key, Object[] params) {
        if (log.isDebugEnabled()) {
            log.debug(LoggerUtil.getMessage(this, key, params));
        }
    }

    /**
     * ���debug level��log��Ϣ.
     *
     * @param key ����log��Ϣ��resource bundle key
     * @param params ��������
     * @param cause �쳣
     */
    public void debug(Object key, Object[] params, Throwable cause) {
        if (log.isDebugEnabled()) {
            log.debug(LoggerUtil.getMessage(this, key, params), cause);
        }
    }

    /**
     * ���info level��log��Ϣ.
     *
     * @param message log��Ϣ
     */
    public void info(Object message) {
        log.info(message);
    }

    /**
     * ���info level��log��Ϣ.
     *
     * @param message log��Ϣ
     * @param cause �쳣
     */
    public void info(Object message, Throwable cause) {
        log.info(message, cause);
    }

    /**
     * ���info level��log��Ϣ.
     *
     * @param key ����log��Ϣ��resource bundle key
     * @param params ��������
     */
    public void info(Object key, Object[] params) {
        if (log.isInfoEnabled()) {
            log.info(LoggerUtil.getMessage(this, key, params));
        }
    }

    /**
     * ���info level��log��Ϣ.
     *
     * @param key ����log��Ϣ��resource bundle key
     * @param params ��������
     * @param cause �쳣
     */
    public void info(Object key, Object[] params, Throwable cause) {
        if (log.isInfoEnabled()) {
            log.info(LoggerUtil.getMessage(this, key, params), cause);
        }
    }

    /**
     * ���warn level��log��Ϣ.
     *
     * @param message log��Ϣ
     */
    public void warn(Object message) {
        log.warn(message);
    }

    /**
     * ���warn level��log��Ϣ.
     *
     * @param message log��Ϣ
     * @param cause �쳣
     */
    public void warn(Object message, Throwable cause) {
        log.warn(message, cause);
    }

    /**
     * ���warn level��log��Ϣ.
     *
     * @param key ����log��Ϣ��resource bundle key
     * @param params ��������
     */
    public void warn(Object key, Object[] params) {
        if (log.isWarnEnabled()) {
            log.warn(LoggerUtil.getMessage(this, key, params));
        }
    }

    /**
     * ���warn level��log��Ϣ.
     *
     * @param key ����log��Ϣ��resource bundle key
     * @param params ��������
     * @param cause �쳣
     */
    public void warn(Object key, Object[] params, Throwable cause) {
        if (log.isWarnEnabled()) {
            log.warn(LoggerUtil.getMessage(this, key, params), cause);
        }
    }

    /**
     * ���error level��log��Ϣ.
     *
     * @param message log��Ϣ
     */
    public void error(Object message) {
        log.error(message);
    }

    /**
     * ���error level��log��Ϣ.
     *
     * @param message log��Ϣ
     * @param cause �쳣
     */
    public void error(Object message, Throwable cause) {
        log.error(message, cause);
    }

    /**
     * ���error level��log��Ϣ.
     *
     * @param key ����log��Ϣ��resource bundle key
     * @param params ��������
     */
    public void error(Object key, Object[] params) {
        if (log.isErrorEnabled()) {
            log.error(LoggerUtil.getMessage(this, key, params));
        }
    }

    /**
     * ���error level��log��Ϣ.
     *
     * @param key ����log��Ϣ��resource bundle key
     * @param params ��������
     * @param cause �쳣
     */
    public void error(Object key, Object[] params, Throwable cause) {
        if (log.isErrorEnabled()) {
            log.error(LoggerUtil.getMessage(this, key, params), cause);
        }
    }

    /**
     * ���fatal level��log��Ϣ.
     *
     * @param message log��Ϣ
     */
    public void fatal(Object message) {
        log.fatal(message);
    }

    /**
     * ���fatal level��log��Ϣ.
     *
     * @param message log��Ϣ
     * @param cause �쳣
     */
    public void fatal(Object message, Throwable cause) {
        log.fatal(message, cause);
    }

    /**
     * ���fatal level��log��Ϣ.
     *
     * @param key ����log��Ϣ��resource bundle key
     * @param params ��������
     */
    public void fatal(Object key, Object[] params) {
        if (log.isFatalEnabled()) {
            log.fatal(LoggerUtil.getMessage(this, key, params));
        }
    }

    /**
     * ���fatal level��log��Ϣ.
     *
     * @param key ����log��Ϣ��resource bundle key
     * @param params ��������
     * @param cause �쳣
     */
    public void fatal(Object key, Object[] params, Throwable cause) {
        if (log.isFatalEnabled()) {
            log.fatal(LoggerUtil.getMessage(this, key, params), cause);
        }
    }

    /**
     * ȡ��logger��Ӧ����Դ��.
     *
     * @return ��Դ��
     */
    public ResourceBundle getResourceBundle() {
        return bundle;
    }

    /**
     * ����logger��Ӧ����Դ��.
     *
     * @param bundle ��Դ��
     */
    public void setResourceBundle(ResourceBundle bundle) {
        this.bundle = bundle;
    }

    /**
     * ȡ��logger���ַ�����ʾ, ͨ������logger������.
     *
     * @return logger���ַ�����ʾ
     */
    public String toString() {
        return log.toString();
    }

}
