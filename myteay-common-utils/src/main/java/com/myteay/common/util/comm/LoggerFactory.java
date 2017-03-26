package com.myteay.common.util.comm;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * 创建<code>Logger</code>的工厂.  从commons-logging的<code>LogFactory</code>派生.
 * 你可以调用原来的<code>LoggerFactory.getLog</code>方法, 但这样不能得到扩展的功能.
 * 调用<code>LoggerFactory.getLogger</code>则可以得到扩展的<code>Logger</code>的功能.
 *
 * @author Michael Zhou
 * @version $Id: LoggerFactory.java 509 2004-02-16 05:42:07Z baobao $
 */
public abstract class LoggerFactory extends LogFactory {
    /**
     * 取得扩展的<code>Logger</code>.
     *
     * @param clazz 代表<code>Logger</code>名称的类
     *
     * @return 扩展的<code>Logger</code>实现
     */
    public static Logger getLogger(Class<?> clazz) {
        return getLogger(getLog(clazz));
    }

    /**
     * 取得扩展的<code>Logger</code>.
     *
     * @param name <code>Logger</code>名称
     *
     * @return 扩展的<code>Logger</code>实现
     */
    public static Logger getLogger(String name) {
        return getLogger(getLog(name));
    }

    /**
     * 取得扩展的<code>Logger</code>, 如果指定的log对象已经实现了<code>Logger</code>接口, 则直接返回, 否则包装一下再返回.
     *
     * @param log 实现commons-logging的<code>Log</code>接口的对象
     *
     * @return 扩展的<code>Logger</code>实现
     */
    private static Logger getLogger(Log log) {
        if (log instanceof Logger) {
            return (Logger) log;
        }

        return new LoggerWrapper(log);
    }
}
