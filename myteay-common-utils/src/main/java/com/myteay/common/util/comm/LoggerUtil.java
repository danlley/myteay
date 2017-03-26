package com.myteay.common.util.comm;

import java.util.MissingResourceException;
import java.util.ResourceBundle;

/**
 * ����loggerʵ�ֵ�С����.
 *
 * @author Michael Zhou
 * @version $Id: LoggerUtil.java 509 2004-02-16 05:42:07Z baobao $
 */
public class LoggerUtil {
    /**
     * ��resource bundle��ȡ�ø�ʽ������Ϣ.
     *
     * @param logger logger
     * @param key resource bundle key
     * @param params ��������
     *
     * @return ��ʽ������Ϣ�ַ���, ���resource bundle������, ��keyδ�ҵ�, �򷵻�<code>key</code>
     */
    public static String getMessage(Logger logger, Object key, Object[] params) {
        ResourceBundle bundle = logger.getResourceBundle();
        String keyStr = String.valueOf(key);
        String message = null;

        if (bundle == null) {
            logger.error(new StringBuffer().append("Resource bundle not set for logger \"")
                .append(logger).append("\"").toString());
        } else {
            try {
                message = MessageUtil.getMessage(bundle, keyStr, params);
            } catch (MissingResourceException e) {
                logger.error(new StringBuffer().append("No resource is associated with key \"")
                    .append(keyStr).append("\" in logger \"").append(logger).append("\"")
                    .toString(), e);
            }
        }

        return (message == null) ? keyStr : message;
    }
}
