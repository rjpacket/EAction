package com.rjp.eaction.swiper;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.rjp.eaction.util.ImageUtils;

/**
 * author : Gimpo create on 2018/5/25 14:19
 * email  : jimbo922@163.com
 */
public class ImageSwiper extends SwiperLayout<String> {

    private ImageView imageView;

    public ImageSwiper(@NonNull Context context) {
        super(context);
    }

    @Override
    public void inflateModel(String model) {
        imageView = new ImageView(mContext);
        imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
        LayoutParams params = new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        imageView.setLayoutParams(params);
        ImageUtils.load(mContext, model, imageView);
        addView(imageView);
    }

    @Override
    public void notifyModel(String model) {
        ImageUtils.load(mContext, model, imageView);
    }
}
