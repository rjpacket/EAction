package com.rjp.eaction.function;

import android.content.Context;
import android.graphics.Canvas;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.widget.TextView;

/**
 * 自动缩放的TextView
 * @author Gimpo create on 2017/9/20 10:34
 * @email : jimbo922@163.com
 */

public class AutoTextView extends TextView {

    private int viewWidth;
    private final float txtSize;

    public AutoTextView(Context context) {
        this(context, null);
    }

    public AutoTextView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        txtSize = getTextSize();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        viewWidth = getMeasuredWidth();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        setTextSize(TypedValue.COMPLEX_UNIT_PX,txtSize);
        float textSize = txtSize;
        float strWidth = getPaint().measureText(getText().toString());
        float totalWidth = strWidth + getPaddingLeft() + getPaddingRight();
        while (totalWidth > viewWidth){
            textSize--;
            setTextSize(TypedValue.COMPLEX_UNIT_PX,textSize);
            strWidth = getPaint().measureText(getText().toString());
            totalWidth = strWidth + getPaddingLeft() + getPaddingRight();
        }
        super.onDraw(canvas);
    }
}
