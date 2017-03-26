/**
 * Myteay.com Inc.
 * Copyright (c) 2015-2016 All Rights Reserved.
 */
package com.myteay.common.util.zxing.utils;

import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.File;

import com.google.zxing.LuminanceSource;

/**
 * 二维码生成工具辅助处理类，主要负责图片处理
 * 
 * @author Administrator
 * @version $Id: BufferedImageLuminanceSource.java, v 0.1 2016年9月3日 下午11:20:01 Administrator Exp $
 */
public class BufferedImageLuminanceSource extends LuminanceSource {

    /** 图片流 */
    private final BufferedImage image;

    /** 左侧位置 */
    private final int           left;

    /** 顶部位置 */
    private final int           top;

    /**
     * 测试工具类
     * 
     * @param args
     * @throws Exception
     */
    public static void main(String[] args) throws Exception {
        //生成二维码到指定的目录下
        QRCodeUtil.encode("http://www.163.com", "d:/860187528828488679.jpg", "d:/MyWorkDoc", true);

        //解析二维码中的信息
        String str = QRCodeUtil.decode(new File("d:/MyWorkDoc/17659887.jpg"));
        System.out.println(str);
    }

    /**
     * 默认构造方法
     * 
     * @param image 待处理的图片
     */
    public BufferedImageLuminanceSource(BufferedImage image) {
        this(image, 0, 0, image.getWidth(), image.getHeight());
    }

    /**
     * 默认构造方法
     * 
     * @param image     待处理的图片
     * @param left      左侧位置
     * @param top       顶部位置
     * @param width     图片宽度
     * @param height    图片高度
     */
    public BufferedImageLuminanceSource(BufferedImage image, int left, int top, int width,
                                        int height) {
        super(width, height);

        int sourceWidth = image.getWidth();
        int sourceHeight = image.getHeight();
        if (left + width > sourceWidth || top + height > sourceHeight) {
            throw new IllegalArgumentException("Crop rectangle does not fit within image data.");
        }

        for (int y = top; y < top + height; y++) {
            for (int x = left; x < left + width; x++) {
                if ((image.getRGB(x, y) & 0xFF000000) == 0) {
                    image.setRGB(x, y, 0xFFFFFFFF); // = white
                }
            }
        }

        this.image = new BufferedImage(sourceWidth, sourceHeight, BufferedImage.TYPE_BYTE_GRAY);
        this.image.getGraphics().drawImage(image, 0, 0, null);
        this.left = left;
        this.top = top;
    }

    public byte[] getRow(int y, byte[] row) {
        if (y < 0 || y >= getHeight()) {
            throw new IllegalArgumentException("Requested row is outside the image: " + y);
        }
        int width = getWidth();
        if (row == null || row.length < width) {
            row = new byte[width];
        }
        image.getRaster().getDataElements(left, top + y, width, 1, row);
        return row;
    }

    public byte[] getMatrix() {
        int width = getWidth();
        int height = getHeight();
        int area = width * height;
        byte[] matrix = new byte[area];
        image.getRaster().getDataElements(left, top, width, height, matrix);
        return matrix;
    }

    public boolean isCropSupported() {
        return true;
    }

    public LuminanceSource crop(int left, int top, int width, int height) {
        return new BufferedImageLuminanceSource(image, this.left + left, this.top + top, width,
            height);
    }

    public boolean isRotateSupported() {
        return true;
    }

    public LuminanceSource rotateCounterClockwise() {
        int sourceWidth = image.getWidth();
        int sourceHeight = image.getHeight();
        AffineTransform transform = new AffineTransform(0.0, -1.0, 1.0, 0.0, 0.0, sourceWidth);
        BufferedImage rotatedImage = new BufferedImage(sourceHeight, sourceWidth,
            BufferedImage.TYPE_BYTE_GRAY);
        Graphics2D g = rotatedImage.createGraphics();
        g.drawImage(image, transform, null);
        g.dispose();
        int width = getWidth();
        return new BufferedImageLuminanceSource(rotatedImage, top, sourceWidth - (left + width),
            getHeight(), width);
    }
}
