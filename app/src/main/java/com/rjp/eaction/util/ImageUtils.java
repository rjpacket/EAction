package com.rjp.eaction.util;

import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

/**
 * author : Gimpo create on 2018/5/25 14:22
 * email  : jimbo922@163.com
 */
public class ImageUtils {

    /**
     * 加载一张图片
     * @param context
     * @param url
     * @param imageView
     */
    public static void load(Context context, String url, ImageView imageView){
        Glide.with(context).load(url).into(imageView);
    }
}
