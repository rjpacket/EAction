package com.rjp.eaction.views.other;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.widget.LinearLayout;

import com.rjp.eaction.R;
import com.rjp.eaction.util.AppUtils;

/**
 * author : Gimpo create on 2018/8/7 10:57
 * email  : jimbo922@163.com
 */
public class DetailScrollView extends LinearLayout {

    private float downY;

    public DetailScrollView(Context context) {
        this(context, null);
    }

    public DetailScrollView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initView(context, attrs);
    }

    private void initView(Context context, AttributeSet attrs) {
        LayoutInflater.from(context).inflate(R.layout.view_detail_scroll_view, this);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        float curY = ev.getY();
        int action = ev.getAction();
        switch (action) {
            case MotionEvent.ACTION_DOWN:
                downY = curY;
                break;
            case MotionEvent.ACTION_MOVE:
                float dy = curY - downY;
                if(dy > 0 && getScrollY() <= 0){
                    return super.dispatchTouchEvent(ev);
                }else if(getScrollY() == AppUtils.dp2px(200)){
                    return super.dispatchTouchEvent(ev);
                }else{
                    scrollTo(0, (int) (getScrollY() - dy));
                }
                downY = curY;
                break;
            case MotionEvent.ACTION_UP:
            case MotionEvent.ACTION_CANCEL:
                break;
        }
        return super.dispatchTouchEvent(ev);
    }
}
