package com.rjp.eaction.swiper;

import android.content.Context;
import android.support.annotation.NonNull;
import android.widget.FrameLayout;

/**
 * author : Gimpo create on 2018/5/25 12:12
 * email  : jimbo922@163.com
 */
public abstract class SwiperLayout<T> extends FrameLayout{
    public Context mContext;

    public SwiperLayout(@NonNull Context context) {
        super(context);
        mContext = context;
    }

    public abstract void inflateModel(T model);

    public abstract void notifyModel(T model);
}
