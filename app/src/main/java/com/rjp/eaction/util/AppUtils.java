package com.rjp.eaction.util;

import android.content.Context;

/**
 * 辅助工具
 * author : Gimpo create on 2018/5/24 18:02
 * email  : jimbo922@163.com
 */
public class AppUtils {

    /**
     * dp 转化成 px
     * @param context
     * @param dpValue
     * @return
     */
    public static int dp2px(Context context, float dpValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }
}
