package com.rjp.eaction.ui.views;

import android.content.Context;
import android.graphics.Canvas;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import com.rjp.eaction.util.LogUtils;

/**
 * author : Gimpo create on 2018/9/12 11:57
 * email  : jimbo922@163.com
 */
public class TestView extends View {
    private long beforeTime;

    public TestView(Context context) {
        this(context, null);
    }

    public TestView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        beforeTime = System.nanoTime();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        long nanoTime = System.nanoTime();
        LogUtils.e("------on draw-------->", (nanoTime - beforeTime) + "ms" );
        beforeTime = nanoTime;
        super.onDraw(canvas);
    }
}
