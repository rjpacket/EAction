package com.rjp.eaction.views.base_listview;

import android.animation.ValueAnimator;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.LinearInterpolator;

import com.rjp.eaction.R;

/**
 * author : Gimpo create on 2018/8/20 15:12
 * email  : jimbo922@163.com
 */
public class RefreshImageView extends View {
    private Context mContext;
    private int width;
    private int borderWidth = 10;
    private int innerWidth;
    private Paint borderPaint;
    private RectF rectF;
    private int dAngle = 0;
    private PorterDuffXfermode mXfermode;
    private Paint progressPaint;
    private Bitmap srcBitmap;
    private int offset;
    private Paint bitmapPaint;
    private ValueAnimator animator;
    private float progress;
    private int borderColor;
    private int borderCoverColor;
    private int angle = 10;

    public RefreshImageView(Context context) {
        this(context, null);
    }

    public RefreshImageView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initView(context, attrs);
    }

    private void initView(Context context, AttributeSet attrs) {
        mContext = context;

        if (attrs != null) {
            TypedArray array = mContext.obtainStyledAttributes(attrs, R.styleable.RefreshImageView);
            borderWidth = (int) array.getDimension(R.styleable.RefreshImageView_refresh_iv_border_width, 10);
            borderColor = array.getColor(R.styleable.RefreshImageView_refresh_iv_border_color, Color.GRAY);
            borderCoverColor = array.getColor(R.styleable.RefreshImageView_refresh_iv_cover_color, Color.RED);
            angle = array.getInt(R.styleable.RefreshImageView_refresh_iv_gap_angle, 10);
            int srcId = array.getResourceId(R.styleable.RefreshImageView_refresh_iv_src, R.drawable.ic_launcher);
            srcBitmap = BitmapFactory.decodeResource(getResources(), srcId);
        }

        borderPaint = new Paint();
        borderPaint.setAntiAlias(true);
        borderPaint.setStrokeWidth(borderWidth);
        borderPaint.setStrokeCap(Paint.Cap.ROUND);
        borderPaint.setStyle(Paint.Style.STROKE);
        borderPaint.setColor(borderColor);

        progressPaint = new Paint();
        progressPaint.setAntiAlias(true);
        progressPaint.setColor(borderCoverColor);

        bitmapPaint = new Paint();
        bitmapPaint.setAntiAlias(true);

        //最重要的一点，选择合适的图片重叠模式
        mXfermode = new PorterDuffXfermode(PorterDuff.Mode.SRC_ATOP);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        width = MeasureSpec.getSize(widthMeasureSpec);
        setMeasuredDimension(width, width);
        rectF = new RectF(borderWidth, borderWidth, width - borderWidth, width - borderWidth);
        int innerCircleRadius = width / 2 - borderWidth * 2;
        innerWidth = (int) (Math.sqrt(2) * innerCircleRadius);
        offset = width / 2 - innerWidth / 2;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        int sc = canvas.saveLayer(0, 0, width, width, null, Canvas.ALL_SAVE_FLAG);
        for (int i = 0; i < 4; i++) {
            int sweepAngle = 90 - angle;
            canvas.drawArc(rectF, -sweepAngle / 2 + 90 * i + dAngle, sweepAngle, false, borderPaint);
        }
        progressPaint.setXfermode(mXfermode);
        canvas.drawRect(0, width - width * progress, width, width, progressPaint);
        progressPaint.setXfermode(null);
        canvas.restoreToCount(sc);

        canvas.drawBitmap(srcBitmap, null, new RectF(offset, offset, width - offset, width - offset), bitmapPaint);
    }

    public void startAnim() {
        progress = 1;
        animator = ValueAnimator.ofInt(0, 360);
        animator.setDuration(1000);
        animator.setRepeatCount(ValueAnimator.INFINITE);
        animator.setInterpolator(new LinearInterpolator());
        animator.start();
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                dAngle = (Integer) animation.getAnimatedValue();
                invalidate();
            }
        });
    }

    public void stopAnim() {
        if (animator != null) {
            animator.cancel();
            dAngle = 0;
            progress = 0;
            invalidate();
        }
    }

    public void setProgress(float progress) {
        this.progress = progress;
        invalidate();
    }
}
