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
 * �ļ�����������
 * 
 * @author Administrator
 * @version $Id: MtFileUtils.java, v 0.1 2016��3��6�� ����11:19:22 Administrator Exp $
 */
public class MtFileUtils {

    /** ��־ */
    public static final Logger logger = Logger.getLogger(MtFileUtils.class);

    /**
     * �ϴ��ļ�
     * 
     * @param file      ���ϴ��ļ�
     * @param request   ���������ģ��زΣ�
     * @return          �ϴ����ļ����ڵ����·��
     */
    public static String upload(MultipartFile file, HttpServletRequest request, String pathPrefix) {

        if (logger.isInfoEnabled()) {
            logger.info("��ʼ�����ϴ��ļ�");
        }
        String path = request.getSession().getServletContext().getRealPath(pathPrefix);

        String fileName = file.getOriginalFilename();
        String fileNameUploaded = new Date().getTime() + ".jpg";

        if (logger.isInfoEnabled()) {
            logger.info("ԭʼ�ļ���Ϊ��fileName=" + fileName + " �ϴ����ļ���Ϊ��fileNameUploaded"
                        + fileNameUploaded + " �ϴ�Ŀ��·����path=" + path);
        }
        File targetFile = new File(path, fileNameUploaded);
        if (!targetFile.exists()) {
            targetFile.mkdirs();
        }

        //����  
        try {
            file.transferTo(targetFile);
        } catch (Throwable e) {
            logger.warn("�ļ��ϴ����̳����쳣 fileName=" + fileName + " err: " + e.getMessage(), e);
            return null;
        }

        if (logger.isInfoEnabled()) {
            logger.info("�ļ��ϴ��ɹ��� targetFile=" + targetFile.getAbsolutePath());
        }

        return pathPrefix + fileNameUploaded;
    }
}
