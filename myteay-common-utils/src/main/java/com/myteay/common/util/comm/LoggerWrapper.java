package com.myteay.common.util.comm;

import java.util.ResourceBundle;

import org.apache.commons.logging.Log;

/**
 * 包装commons-logging的<code>Log</code>类, 实现扩展的log功能.
 *
 * @author Michael Zhou
 * @version $Id: LoggerWrapper.java 509 2004-02-16 05:42:07Z baobao $
 */
public class LoggerWrapper implements Logger {
    private Log            log;
    private ResourceBundle bundle;

    /**
     * 构造函数.
     *
     * @param log 被包装的<code>Log</code>对象
     */
    public LoggerWrapper(Log log) {
        this.log = log;
    }

    /**
     * 判断trace level是否被打开.
     *
     * @return 如果trace level被打开, 则返回<code>true</code>
     */
    public boolean isTraceEnabled() {
        return log.isTraceEnabled();
    }

    /**
     * 判断debug level是否被打开.
     *
     * @return 如果debug level被打开, 则返回<code>true</code>
     */
    public boolean isDebugEnabled() {
        return log.isDebugEnabled();
    }

    /**
     * 判断info level是否被打开.
     *
     * @return 如果info level被打开, 则返回<code>true</code>
     */
    public boolean isInfoEnabled() {
        return log.isInfoEnabled();
    }

    /**
     * 判断warn level是否被打开.
     *
     * @return 如果warn level被打开, 则返回<code>true</code>
     */
    public boolean isWarnEnabled() {
        return log.isWarnEnabled();
    }

    /**
     * 判断error level是否被打开.
     *
     * @return 如果error level被打开, 则返回<code>true</code>
     */
    public boolean isErrorEnabled() {
        return log.isErrorEnabled();
    }

    /**
     * 判断fatal level是否被打开.
     *
     * @return 如果fatal level被打开, 则返回<code>true</code>
     */
    public boolean isFatalEnabled() {
        return log.isFatalEnabled();
    }

    /**
     * 输出trace level的log信息.
     *
     * @param message log信息
     */
    public void trace(Object message) {
        log.trace(message);
    }

    /**
     * 输出trace level的log信息.
     *
     * @param message log信息
     * @param cause 异常
     */
    public void trace(Object message, Throwable cause) {
        log.trace(message, cause);
    }

    /**
     * 输出trace level的log信息.
     *
     * @param key 代表log信息的resource bundle key
     * @param params 参数数组
     */
    public void trace(Object key, Object[] params) {
        if (log.isTraceEnabled()) {
            log.trace(LoggerUtil.getMessage(this, key, params));
        }
    }

    /**
     * 输出trace level的log信息.
     *
     * @param key 代表log信息的resource bundle key
     * @param params 参数数组
     * @param cause 异常
     */
    public void trace(Object key, Object[] params, Throwable cause) {
        if (log.isTraceEnabled()) {
            log.trace(LoggerUtil.getMessage(this, key, params), cause);
        }
    }

    /**
     * 输出debug level的log信息.
     *
     * @param message log信息
     */
    public void debug(Object message) {
        log.debug(message);
    }

    /**
     * 输出debug level的log信息.
     *
     * @param message log信息
     * @param cause 异常
     */
    public void debug(Object message, Throwable cause) {
        log.debug(message, cause);
    }

    /**
     * 输出debug level的log信息.
     *
     * @param key 代表log信息的resource bundle key
     * @param params 参数数组
     */
    public void debug(Object key, Object[] params) {
        if (log.isDebugEnabled()) {
            log.debug(LoggerUtil.getMessage(this, key, params));
        }
    }

    /**
     * 输出debug level的log信息.
     *
     * @param key 代表log信息的resource bundle key
     * @param params 参数数组
     * @param cause 异常
     */
    public void debug(Object key, Object[] params, Throwable cause) {
        if (log.isDebugEnabled()) {
            log.debug(LoggerUtil.getMessage(this, key, params), cause);
        }
    }

    /**
     * 输出info level的log信息.
     *
     * @param message log信息
     */
    public void info(Object message) {
        log.info(message);
    }

    /**
     * 输出info level的log信息.
     *
     * @param message log信息
     * @param cause 异常
     */
    public void info(Object message, Throwable cause) {
        log.info(message, cause);
    }

    /**
     * 输出info level的log信息.
     *
     * @param key 代表log信息的resource bundle key
     * @param params 参数数组
     */
    public void info(Object key, Object[] params) {
        if (log.isInfoEnabled()) {
            log.info(LoggerUtil.getMessage(this, key, params));
        }
    }

    /**
     * 输出info level的log信息.
     *
     * @param key 代表log信息的resource bundle key
     * @param params 参数数组
     * @param cause 异常
     */
    public void info(Object key, Object[] params, Throwable cause) {
        if (log.isInfoEnabled()) {
            log.info(LoggerUtil.getMessage(this, key, params), cause);
        }
    }

    /**
     * 输出warn level的log信息.
     *
     * @param message log信息
     */
    public void warn(Object message) {
        log.warn(message);
    }

    /**
     * 输出warn level的log信息.
     *
     * @param message log信息
     * @param cause 异常
     */
    public void warn(Object message, Throwable cause) {
        log.warn(message, cause);
    }

    /**
     * 输出warn level的log信息.
     *
     * @param key 代表log信息的resource bundle key
     * @param params 参数数组
     */
    public void warn(Object key, Object[] params) {
        if (log.isWarnEnabled()) {
            log.warn(LoggerUtil.getMessage(this, key, params));
        }
    }

    /**
     * 输出warn level的log信息.
     *
     * @param key 代表log信息的resource bundle key
     * @param params 参数数组
     * @param cause 异常
     */
    public void warn(Object key, Object[] params, Throwable cause) {
        if (log.isWarnEnabled()) {
            log.warn(LoggerUtil.getMessage(this, key, params), cause);
        }
    }

    /**
     * 输出error level的log信息.
     *
     * @param message log信息
     */
    public void error(Object message) {
        log.error(message);
    }

    /**
     * 输出error level的log信息.
     *
     * @param message log信息
     * @param cause 异常
     */
    public void error(Object message, Throwable cause) {
        log.error(message, cause);
    }

    /**
     * 输出error level的log信息.
     *
     * @param key 代表log信息的resource bundle key
     * @param params 参数数组
     */
    public void error(Object key, Object[] params) {
        if (log.isErrorEnabled()) {
            log.error(LoggerUtil.getMessage(this, key, params));
        }
    }

    /**
     * 输出error level的log信息.
     *
     * @param key 代表log信息的resource bundle key
     * @param params 参数数组
     * @param cause 异常
     */
    public void error(Object key, Object[] params, Throwable cause) {
        if (log.isErrorEnabled()) {
            log.error(LoggerUtil.getMessage(this, key, params), cause);
        }
    }

    /**
     * 输出fatal level的log信息.
     *
     * @param message log信息
     */
    public void fatal(Object message) {
        log.fatal(message);
    }

    /**
     * 输出fatal level的log信息.
     *
     * @param message log信息
     * @param cause 异常
     */
    public void fatal(Object message, Throwable cause) {
        log.fatal(message, cause);
    }

    /**
     * 输出fatal level的log信息.
     *
     * @param key 代表log信息的resource bundle key
     * @param params 参数数组
     */
    public void fatal(Object key, Object[] params) {
        if (log.isFatalEnabled()) {
            log.fatal(LoggerUtil.getMessage(this, key, params));
        }
    }

    /**
     * 输出fatal level的log信息.
     *
     * @param key 代表log信息的resource bundle key
     * @param params 参数数组
     * @param cause 异常
     */
    public void fatal(Object key, Object[] params, Throwable cause) {
        if (log.isFatalEnabled()) {
            log.fatal(LoggerUtil.getMessage(this, key, params), cause);
        }
    }

    /**
     * 取得logger对应的资源束.
     *
     * @return 资源束
     */
    public ResourceBundle getResourceBundle() {
        return bundle;
    }

    /**
     * 设置logger对应的资源束.
     *
     * @param bundle 资源束
     */
    public void setResourceBundle(ResourceBundle bundle) {
        this.bundle = bundle;
    }

    /**
     * 取得logger的字符串表示, 通常返回logger的名称.
     *
     * @return logger的字符串表示
     */
    public String toString() {
        return log.toString();
    }

}
