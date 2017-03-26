/**
 * Myteay.com Inc.
 * Copyright (c) 2015-2016 All Rights Reserved.
 */
package com.myteay.common.util.zxing.utils;

import java.awt.BasicStroke;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Shape;
import java.awt.geom.RoundRectangle2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.OutputStream;
import java.util.Hashtable;

import javax.imageio.ImageIO;

import org.apache.log4j.Logger;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.BinaryBitmap;
import com.google.zxing.DecodeHintType;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatReader;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.Result;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.common.HybridBinarizer;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;
import com.myteay.common.util.comm.MyTeayException;
import com.myteay.common.util.comm.RandomNumStrUtils;
import com.myteay.common.util.comm.StringUtils;
import com.myteay.common.utils.UIDGener;

/**
 * ��ά�����ɹ����ࣨ������ͼƬģʽ��
 * 
 * @author Administrator
 * @version $Id: QRCodeUtil.java, v 0.1 2016��9��3�� ����11:20:21 Administrator Exp $
 */
public class QRCodeUtil {

    /** ��־ */
    public static final Logger  logger      = Logger.getLogger(QRCodeUtil.class);

    /** �����ʽ */
    private static final String CHARSET     = "utf-8";

    /** ͼƬ���� */
    private static final String FORMAT_NAME = "JPG";

    /** ��ά��ߴ� */
    private static final int    QRCODE_SIZE = 300;

    /** LOGO��� */
    private static final int    WIDTH       = 60;

    /** LOGO�߶� */
    private static final int    HEIGHT      = 60;

    /**
     * ��ά������
     * 
     * @param content       ��ά������
     * @param imgPath       ͼƬ·��
     * @param needCompress  �Ƿ���Ҫѹ��
     * @return              �������ɺõĶ�ά��ͼƬ
     * @throws Exception    �쳣����
     */
    private static BufferedImage createImage(String content, String imgPath, boolean needCompress)
                                                                                                  throws Exception {
        Hashtable<EncodeHintType, Object> hints = new Hashtable<EncodeHintType, Object>();
        hints.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.H);
        hints.put(EncodeHintType.CHARACTER_SET, CHARSET);
        hints.put(EncodeHintType.MARGIN, 1);
        BitMatrix bitMatrix = new MultiFormatWriter().encode(content, BarcodeFormat.QR_CODE,
            QRCODE_SIZE, QRCODE_SIZE, hints);
        int width = bitMatrix.getWidth();
        int height = bitMatrix.getHeight();
        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                image.setRGB(x, y, bitMatrix.get(x, y) ? 0xFF000000 : 0xFFFFFFFF);
            }
        }
        if (imgPath == null || "".equals(imgPath)) {
            return image;
        }
        // ����ͼƬ
        QRCodeUtil.insertImage(image, imgPath, needCompress);
        return image;
    }

    /**
     * ��������ͼƬ
     * 
     * @param source        �Ѿ���ʥ�Ķ�ά��
     * @param imgPath       ��ά������ͼƬ����λ��
     * @param needCompress  �Ƿ���Ҫѹ��
     * @throws Exception    �쳣����
     */
    private static void insertImage(BufferedImage source, String imgPath, boolean needCompress)
                                                                                               throws Exception {
        File file = new File(imgPath);
        if (!file.exists()) {
            System.err.println("" + imgPath + "   ���ļ������ڣ�");
            return;
        }
        Image src = ImageIO.read(new File(imgPath));
        int width = src.getWidth(null);
        int height = src.getHeight(null);
        if (needCompress) { // ѹ��LOGO
            if (width > WIDTH) {
                width = WIDTH;
            }
            if (height > HEIGHT) {
                height = HEIGHT;
            }
            Image image = src.getScaledInstance(width, height, Image.SCALE_SMOOTH);
            BufferedImage tag = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
            Graphics g = tag.getGraphics();
            g.drawImage(image, 0, 0, null); // ������С���ͼ
            g.dispose();
            src = image;
        }
        // ����LOGO
        Graphics2D graph = source.createGraphics();
        int x = (QRCODE_SIZE - width) / 2;
        int y = (QRCODE_SIZE - height) / 2;
        graph.drawImage(src, x, y, width, height, null);
        Shape shape = new RoundRectangle2D.Float(x, y, width, width, 6, 6);
        graph.setStroke(new BasicStroke(3f));
        graph.draw(shape);
        graph.dispose();
    }

    /**
     * ��ά�����
     * 
     * @param content       ��ά������
     * @param imgPath       ����ͼƬ���·��
     * @param destPath      Ŀ���ά�����ɺ��ͼƬ���·��
     * @param needCompress  �Ƿ�ѹ��
     * @throws Exception    �쳣����
     */
    public static void encode(String content, String imgPath, String destPath, boolean needCompress)
                                                                                                    throws Exception {

        if (logger.isInfoEnabled()) {
            logger.info("��ʼ������ά��content=" + content + " imgPath=" + imgPath + " destPath="
                        + destPath + " needCompress=" + needCompress);
        }

        BufferedImage image = QRCodeUtil.createImage(content, imgPath, needCompress);
        mkdirs(destPath);
        File file = new File(destPath + "/" + content + ".jpg");
        ImageIO.write(image, FORMAT_NAME, file);
        String path = null;
        if (file != null) {
            path = file.getAbsolutePath();
        }

        logger.warn("��ά������·��Ϊ path=" + path);
    }

    /**
     * ��ά�����
     * 
     * @param content       ��ά������
     * @param imgPath       ����ͼƬ���·��
     * @param destPath      Ŀ���ά�����ɺ��ͼƬ���·��
     * @param needCompress  �Ƿ�ѹ��
     * @throws Exception    �쳣����
     */
    public static String encode(String content, String imgPath, boolean needCompress,
                                String destPath) throws Exception {

        if (logger.isInfoEnabled()) {
            logger.info("��ʼ������ά��content=" + content + " imgPath=" + imgPath + " destPath="
                        + destPath + " needCompress=" + needCompress);
        }

        BufferedImage image = QRCodeUtil.createImage(content, imgPath, needCompress);
        mkdirs(destPath);

        String filename = null;
        try {
            filename = UIDGener.genUserId("�ʹ���", RandomNumStrUtils.getNum());
        } catch (MyTeayException e) {
            logger.warn("���ɶ�ά���ļ���ʧ�ܡ�content=" + content, e);
            throw new Exception("���ɶ�ά���ļ���ʧ�� content=" + content);
        }

        if (StringUtils.isBlank(filename)) {
            logger.warn("���ɶ�ά���ļ���ʧ��filename�����ã�filename is null!");
            throw new Exception("���ɶ�ά���ļ���ʧ�� filename is null content=" + content);
        }

        File file = new File(destPath + "/" + filename + ".jpg");
        ImageIO.write(image, FORMAT_NAME, file);
        String path = null;
        if (file != null) {
            path = file.getAbsolutePath();
        }

        logger.warn("��ά������·��Ϊ path=" + path);

        return filename + ".jpg";
    }

    /**
     * ���ɶ�ά����·��
     * 
     * @param destPath  Ŀ��·����ַ
     */
    public static void mkdirs(String destPath) {
        File file = new File(destPath);
        //���ļ��в�����ʱ��mkdirs���Զ��������Ŀ¼��������mkdir��(mkdir�����Ŀ¼����������׳��쳣)
        if (!file.exists() && !file.isDirectory()) {
            file.mkdirs();
        }
    }

    /**
     * Ĭ�϶�ά�����ɷ�ʽ
     * 
     * @param content       ��ά������
     * @param imgPath       ����ͼƬ���·��
     * @param destPath      ��ά��ͼƬ���·��
     * @throws Exception    �쳣����
     */
    public static void encode(String content, String imgPath, String destPath) throws Exception {
        QRCodeUtil.encode(content, imgPath, destPath, false);
    }

    /**
     * ����ͼƬ�Ķ�ά�����ɷ�ʽ
     * 
     * @param content       ��ά������
     * @param destPath      ͼƬ���·��
     * @param needCompress  �Ƿ�ѹ��
     * @throws Exception    �쳣����ʽ
     */
    public static void encode(String content, String destPath, boolean needCompress)
                                                                                    throws Exception {
        QRCodeUtil.encode(content, null, destPath, needCompress);
    }

    /**
     * ������ѹ���Ķ�ά��ͼƬ���ɣ���û��Я������ͼƬ
     * 
     * @param content       ��ά������
     * @param destPath      ��ά��ͼƬ���·��
     * @throws Exception    �쳣����
     */
    public static void encode(String content, String destPath) throws Exception {
        QRCodeUtil.encode(content, null, destPath, false);
    }

    /**
     * ��ά�����ɺ�ֱ��д��IO�ı��뷽ʽ
     * 
     * @param content       ��ά������
     * @param imgPath       ����ͼƬ����·��
     * @param output        ����д���ά���IO��
     * @param needCompress  �Ƿ���Ҫѹ��
     * @throws Exception    �쳣����
     */
    public static void encode(String content, String imgPath, OutputStream output,
                              boolean needCompress) throws Exception {
        BufferedImage image = QRCodeUtil.createImage(content, imgPath, needCompress);
        ImageIO.write(image, FORMAT_NAME, output);
    }

    /**
     * ��������ͼƬ�Ķ�ά������ֱ��д��IO����ͬʱ����ѹ������
     * 
     * @param content       ��ά������
     * @param output        ����д���ά���IO��
     * @throws Exception    �쳣����
     */
    public static void encode(String content, OutputStream output) throws Exception {
        QRCodeUtil.encode(content, null, output, false);
    }

    /**
     * ��ά���ļ�����
     * 
     * @param file          ��ά���ļ�
     * @return              ������Ķ�ά���������Ϣ
     * @throws Exception    �쳣����
     */
    public static String decode(File file) throws Exception {
        BufferedImage image;
        image = ImageIO.read(file);
        if (image == null) {
            return null;
        }
        BufferedImageLuminanceSource source = new BufferedImageLuminanceSource(image);
        BinaryBitmap bitmap = new BinaryBitmap(new HybridBinarizer(source));
        Result result;
        Hashtable<DecodeHintType, String> hints = new Hashtable<DecodeHintType, String>();
        hints.put(DecodeHintType.CHARACTER_SET, CHARSET);
        result = new MultiFormatReader().decode(bitmap, hints);
        String resultStr = result.getText();
        return resultStr;
    }

    /**
     * ֱ��ͨ����ά��ͼƬ���·���Զ�ά����н���
     * 
     * @param path          ��ά��ͼƬ���·��
     * @return              �����Ķ�ά������Ϣ
     * @throws Exception    �쳣����
     */
    public static String decode(String path) throws Exception {
        return QRCodeUtil.decode(new File(path));
    }

    public static void main(String[] args) throws Exception {
        String text = "http://www.dans88.com.cn";
        QRCodeUtil.encode(text, "d:/MyWorkDoc/my180.jpg", "d:/MyWorkDoc", true);
    }

}
