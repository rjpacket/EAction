package com.rjp.eaction.util;

import java.text.DecimalFormat;

/**
 * author : Gimpo create on 2018/6/27 10:26
 * email  : jimbo922@163.com
 */
public class MathUtils {

    /**
     * 1 -> 01 字符串格式化
     *
     * @param number
     * @return
     */
    public static String format02d(int number) {
        return String.format("%02d", number);
    }

    public static String keep20(double num){
        return new DecimalFormat("0.00").format(num);
    }
}
