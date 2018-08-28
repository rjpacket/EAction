package com.rjp.eaction.ui.views;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Xfermode;
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
    private Paint circlePaint;
    private Bitmap srcBitmap;
    private Bitmap targetBitmap;
    private RectF dstRect;
    private Rect srcRect;
    private Xfermode src_atop;

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

        circlePaint = new Paint();
        circlePaint.setAntiAlias(true);

        src_atop = new PorterDuffXfermode(PorterDuff.Mode.SRC_ATOP);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        width = MeasureSpec.getSize(widthMeasureSpec);
        setMeasuredDimension(width, radius * 3 + borderWidth);

        srcBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.mine_user_head);
        targetBitmap = zoomBitmap(srcBitmap, radius * 2, radius * 2);
        if(srcRect == null) {
            srcRect = new Rect(targetBitmap.getWidth() / 2 - radius, targetBitmap.getHeight() / 2 - radius, targetBitmap.getWidth() / 2 + radius, targetBitmap.getHeight() / 2 + radius);
            dstRect = new RectF(width / 2 - radius, radius + borderWidth, width / 2 + radius, radius * 3 + borderWidth);
        }
    }

    /**
     * 放大图片   会生成新的图片，资源浪费
     *
     * @param bm
     * @param newWidth
     * @param newHeight
     * @return
     */
    public static Bitmap zoomBitmap(Bitmap bm, int newWidth, int newHeight) {
        // 获得图片的宽高
        int width = bm.getWidth();
        int height = bm.getHeight();
        // 计算缩放比例
        float scaleWidth = ((float) newWidth) / width;
        float scaleHeight = ((float) newHeight) / height;
        // 取得想要缩放的matrix参数
        Matrix matrix = new Matrix();
        float maxScale = Math.max(scaleWidth, scaleHeight);
        matrix.postScale(maxScale, maxScale);
        // 得到新的图片
        return Bitmap.createBitmap(bm, 0, 0, width, height, matrix, true);
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

        int sc = canvas.saveLayer(0, 0, width, radius * 3 + borderWidth, mPaint, Canvas.ALL_SAVE_FLAG);
        canvas.drawCircle(width / 2, radius * 2 + borderWidth, radius, mPaint);
        circlePaint.setXfermode(src_atop);
        canvas.drawBitmap(targetBitmap, srcRect, dstRect, circlePaint);
        circlePaint.setXfermode(null);
        canvas.restoreToCount(sc);
    }
}
