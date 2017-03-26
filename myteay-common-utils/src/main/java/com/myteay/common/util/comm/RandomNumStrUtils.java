/**
 * Copyright (c) 2004-2015 All Rights Reserved.
 */
package com.myteay.common.util.comm;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;

/**
 * 4位随机数字串生成工具类
 * 
 * @author Administrator
 * @version $Id: RandomNumStrUtils.java, v 0.1 2015年7月5日 下午11:23:53 Administrator Exp $
 */
public class RandomNumStrUtils {

    /** 当前队列 */
    private static ArrayBlockingQueue<String> CURR_QUEUE = new ArrayBlockingQueue<String>(10000);

    /**
     * 获取数字串，如果目标数字串队列已经没有数据，则对该队列进行初始化
     * 
     * @return
     */
    public static String getNum() {
        if (CURR_QUEUE.remainingCapacity() < 10000) {
            return CURR_QUEUE.poll();
        }

        synchronized (CURR_QUEUE) {
            initQueue();
            return CURR_QUEUE.poll();
        }
    }

    /**
     * 队列初始化
     */
    private static void initQueue() {
        if (CURR_QUEUE != null && CURR_QUEUE.remainingCapacity() <= 0) {
            return;
        }

        List<String> dataList = getQueueDataList();
        if (CollectionUtils.isEmpty(dataList)) {
            return;
        }

        for (String str : dataList) {
            if (StringUtils.isBlank(str)) {
                continue;
            }
            CURR_QUEUE.add(str);
        }
    }

    /**
     * 得到待加入队列的队列数据
     * 
     * @return
     */
    private static List<String> getQueueDataList() {
        List<String> result = new ArrayList<String>();
        String num = null;
        for (int i = 0; i < 9999; i++) {
            num = getSingleData(StringUtils.EMPTY_STRING + i);
            if (StringUtils.isBlank(num)) {
                continue;
            }
            result.add(num);
        }

        return result;
    }

    /**
     * 得到一个长度为4的数字字符串
     * 
     * @param num
     * @return
     */
    private static String getSingleData(String num) {

        if (StringUtils.isBlank(num)) {
            return null;
        }

        char[] charArr = num.toCharArray();
        if (charArr == null || charArr.length <= 0 || charArr.length > 4) {
            return null;
        }

        if (charArr.length == 4) {
            return num;
        }
        //
        char[] emptyArr = initCharArray(4);
        int index = emptyArr.length - charArr.length;
        System.arraycopy(charArr, 0, emptyArr, index, charArr.length);

        return new String(emptyArr);
    }

    /**
     * 初始化，并得到一个全部数组内容置0的目标char型数组
     * 
     * @param len
     * @return
     */
    private static char[] initCharArray(int len) {
        char[] result = new char[len];
        char commonChar = '0';
        for (int i = 0; i < len; i++) {
            result[i] = commonChar;
        }

        return result;
    }

    /**
     * 
     * @param args
     */
    public static void main(String[] args) {
        for (int i = 0; i < 10000; i++) {
            System.out.println(RandomNumStrUtils.getNum());
        }

    }

}
