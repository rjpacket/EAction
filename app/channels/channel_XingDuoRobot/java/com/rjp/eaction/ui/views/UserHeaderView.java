package com.rjp.eaction.ui.views;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import com.rjp.eaction.R;
import com.rjp.eaction.util.AppUtils;

/**
 * 头像View
 * author : Gimpo create on 2018/8/22 10:33
 * email  : jimbo922@163.com
 */
public class UserHeaderView extends View {
    private Context mContext;
    private int radius;
    private int width;
    private int borderWidth;
    private Path bgPath;
    private Paint mPaint;

    public UserHeaderView(Context context) {
        this(context, null);
    }

    public UserHeaderView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initView(context, attrs);
    }

    private void initView(Context context, AttributeSet attrs) {
        mContext = context;
        radius = AppUtils.dp2px(40);
        borderWidth = AppUtils.dp2px(10);

        bgPath = new Path();
        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        mPaint.setStyle(Paint.Style.FILL_AND_STROKE);
        mPaint.setColor(mContext.getResources().getColor(R.color.main_color));
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        width = MeasureSpec.getSize(widthMeasureSpec);
        setMeasuredDimension(width, radius * 3 + borderWidth);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        bgPath.moveTo(0, 0);
        bgPath.lineTo(width, 0);
        int height = radius * 2 + borderWidth;
        bgPath.lineTo(width, height);
        int x0 = width / 2 + radius + borderWidth + AppUtils.dp2px(10);
        int y0 = height;
        bgPath.lineTo(x0, y0);
        bgPath.arcTo(new RectF(width / 2 - radius - borderWidth, radius, width / 2 + radius + borderWidth, radius * 3), -5, -170);
        int x3 = width / 2 - radius - borderWidth - AppUtils.dp2px(10);
        int y3 = radius * 2 + borderWidth;
        bgPath.quadTo(width / 2 - radius - borderWidth, y3, x3, y3);
        bgPath.lineTo(0, y3);
        bgPath.lineTo(0, 0);
        canvas.drawPath(bgPath, mPaint);

        bgPath.moveTo(0, 0);
        bgPath.lineTo(0, height);
        bgPath.lineTo(x3, y3);
        bgPath.arcTo(new RectF(width / 2 - radius - borderWidth, radius, width / 2 + radius + borderWidth, radius * 3), -175, 170);
        bgPath.quadTo(width / 2 + radius + borderWidth, y0, x0, y0);
        bgPath.lineTo(width, height);
        bgPath.lineTo(width, 0);
        bgPath.close();
        canvas.drawPath(bgPath, mPaint);
    }
}
