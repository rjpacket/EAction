package com.rjp.eaction.util;

import android.content.res.Resources;

/**
 * 辅助工具
 * author : Gimpo create on 2018/5/24 18:02
 * email  : jimbo922@163.com
 */
public class AppUtils {

    /**
     * dp 转化成 px
     * @param dp
     * @return
     */
    public static int dp2px(int dp) {
        return (int) (dp * Resources.getSystem().getDisplayMetrics().density);
    }

    /**
     * dp 转化成 px
     * @param px
     * @return
     */
    public static int px2dp(int px) {
        return (int) (px / Resources.getSystem().getDisplayMetrics().density);
    }
}
