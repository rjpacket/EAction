package com.rjp.eaction.views.other;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ScrollView;

/**
 *
 * 可以监听滚动的ScrollView
 * author : Gimpo create on 2018/7/17 15:38
 * email  : jimbo922@163.com
 */
public class IScrollView extends ScrollView {
    public IScrollChangeListener scrollChangeListener;

    public IScrollView(Context context) {
        super(context);
    }

    public IScrollView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onScrollChanged(int l, int t, int oldl, int oldt) {
        super.onScrollChanged(l, t, oldl, oldt);
        if(scrollChangeListener != null){
            scrollChangeListener.onScrollChanged(l, t, oldl, oldt);
        }
    }

    public void setScrollChangeListener(IScrollChangeListener scrollChangeListener){
        this.scrollChangeListener = scrollChangeListener;
    }

    public interface IScrollChangeListener{
        void onScrollChanged(int l, int t, int oldl, int oldt);
    }
}
