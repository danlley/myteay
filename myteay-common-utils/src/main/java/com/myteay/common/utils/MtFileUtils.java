/**
 * Myteay.com Inc.
 * Copyright (c) 2015-2016 All Rights Reserved.
 */
package com.myteay.common.utils;

import java.io.File;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.web.multipart.MultipartFile;

/**
 * 文件操作工具类
 * 
 * @author Administrator
 * @version $Id: MtFileUtils.java, v 0.1 2016年3月6日 下午11:19:22 Administrator Exp $
 */
public class MtFileUtils {

    /** 日志 */
    public static final Logger logger = Logger.getLogger(MtFileUtils.class);

    /**
     * 上传文件
     * 
     * @param file      待上传文件
     * @param request   请求上下文（必参）
     * @return          上传后文件所在的相对路径
     */
    public static String upload(MultipartFile file, HttpServletRequest request, String pathPrefix) {

        if (logger.isInfoEnabled()) {
            logger.info("开始保存上传文件");
        }
        String path = request.getSession().getServletContext().getRealPath(pathPrefix);

        String fileName = file.getOriginalFilename();
        String fileNameUploaded = new Date().getTime() + ".jpg";

        if (logger.isInfoEnabled()) {
            logger.info("原始文件名为：fileName=" + fileName + " 上传后文件名为：fileNameUploaded"
                        + fileNameUploaded + " 上传目标路径：path=" + path);
        }
        File targetFile = new File(path, fileNameUploaded);
        if (!targetFile.exists()) {
            targetFile.mkdirs();
        }

        //保存  
        try {
            file.transferTo(targetFile);
        } catch (Throwable e) {
            logger.warn("文件上传过程出现异常 fileName=" + fileName + " err: " + e.getMessage(), e);
            return null;
        }

        if (logger.isInfoEnabled()) {
            logger.info("文件上传成功： targetFile=" + targetFile.getAbsolutePath());
        }

        return pathPrefix + fileNameUploaded;
    }
}
