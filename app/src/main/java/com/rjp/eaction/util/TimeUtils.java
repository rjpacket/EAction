package com.rjp.eaction.util;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * author : Gimpo create on 2018/6/27 10:41
 * email  : jimbo922@163.com
 */
public class TimeUtils {
    public static final String T0 = "yyyy-MM-dd HH:mm:ss";
    public static final String T1 = "MM-dd HH:mm";

    /**
     * 计算某一时间与现在时间间隔的文字提示
     */
    public static String countTimeIntervalText(long time) {
        long dTime = System.currentTimeMillis() - time;
        // 15分钟
        if (dTime < 15 * 60 * 1000) {
            return "刚刚";
        } else if (dTime < 60 * 60 * 1000) {
            // 1小时内
            return (int) (dTime / (60 * 1000)) + "分钟前";
        } else if (dTime < 24 * 60 * 60 * 1000) {
            // 1天内
            return (int) (dTime / (60 * 60 * 1000)) + "小时前";
        } else {
            return parseTime(time, T1);
        }
    }

    /**
     * 将Long时间转化成字符串
     * @param time
     * @return
     */
    public static String parseTime(long time){
        return parseTime(time, T0);
    }

    /**
     * 带format
     * @param time
     * @param format
     * @return
     */
    public static String parseTime(long time, String format){
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        return sdf.format(new Date(time));
    }

    /**
     * 将时间转化成Long
     * @param time
     * @return
     */
    public static long getTime(String time){
        return getTime(time, T0);
    }

    /**
     * 将时间转化成Long
     * @param time
     * @param format
     * @return
     */
    public static long getTime(String time, String format){
        try {
            SimpleDateFormat sdf = new SimpleDateFormat(format);
            return sdf.parse(time).getTime();
        }catch (Exception e){

        }
        return 0;
    }
}
