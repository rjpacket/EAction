package com.rjp.eaction.ui.views;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import com.rjp.eaction.R;

import static android.graphics.Color.WHITE;

/**
 * author : Gimpo create on 2018/8/1 15:06
 * email  : jimbo922@163.com
 */
public class RatioView extends View {

    private int maxRatio;
    private int ratio;
    private int ratioColor;
    private int width;
    private int height;
    private int length;
    private Paint whitePaint;
    private Paint mPaint;

    public RatioView(Context context) {
        this(context, null);
    }

    public RatioView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initView(context, attrs);
    }

    private void initView(Context context, AttributeSet attrs) {
        if (attrs != null) {
            TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.RatioView);
            maxRatio = typedArray.getInteger(R.styleable.RatioView_ratio_max, 100);
            ratioColor = typedArray.getColor(R.styleable.RatioView_ratio_color, Color.RED);
        }

        whitePaint = new Paint();
        whitePaint.setAntiAlias(true);
        whitePaint.setColor(WHITE);

        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        mPaint.setColor(ratioColor);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        width = MeasureSpec.getSize(widthMeasureSpec);
        height = MeasureSpec.getSize(heightMeasureSpec);
        setMeasuredDimension(width, height);
        if (maxRatio != 0 && ratio != 0) {
            length = (ratio * width) / maxRatio;
        }
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (maxRatio != 0 && ratio != 0) {
            canvas.drawRect(0, 0, width - length, height, whitePaint);
            canvas.drawRect(width - length, 0, width, height, mPaint);
        }
    }

    public void setMaxRatio(int maxRatio) {
        this.maxRatio = maxRatio;
        invalidate();
    }

    public void setRatio(int ratio) {
        this.ratio = ratio;
        invalidate();
    }
}
