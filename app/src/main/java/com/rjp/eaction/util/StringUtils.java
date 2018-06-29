package com.rjp.eaction.util;

import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * author : Gimpo create on 2018/6/27 11:28
 * email  : jimbo922@163.com
 */
public class StringUtils {

    /**
     * 为字符串中的局部染色
     *
     * @param originStr 源字符串
     * @param targetStr 需要染色字符串
     * @param color     需要染的颜色
     * @return
     */
    public static SpannableString setSpanColor(String originStr, String targetStr, int color) {
        if (originStr == null) return null;
        SpannableString span = new SpannableString(originStr);

        Pattern p = Pattern.compile(targetStr);
        Matcher m = p.matcher(originStr);
        while (m.find()) {
            int start = m.start();
            int end = start + m.group().length();
            span.setSpan(new ForegroundColorSpan(color), start, end, Spannable.SPAN_EXCLUSIVE_INCLUSIVE);
        }
        return span;
    }


}
