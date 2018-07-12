package com.rjp.eaction.swiper;

import android.animation.Animator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.widget.FrameLayout;

import java.util.List;

/**
 * author : Gimpo create on 2018/5/25 11:52
 * email  : jimbo922@163.com
 */
public class SwiperView<T> extends FrameLayout {
    public static final int AUTO_START_ANIM = 200;
    public static final int MOVE_LEFT_ANIM = 400;
    public static final int MOVE_RIGHT_ANIM = 600;

    private int width;
    private int height;
    //数据源
    private List<T> mDatas;
    //布局  只需要缓存三个就行
    private SwiperLayout<T> leftSwiper;
    private SwiperLayout<T> midSwiper;
    private SwiperLayout<T> rightSwiper;
    private float downX;
    private float moveX;

    private boolean isAnimStart = false;

    public SwiperView(@NonNull Context context) {
        this(context, null);
    }

    public SwiperView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        width = MeasureSpec.getSize(widthMeasureSpec);
        height = (int) (width * 9 * 1.0 / 16);
        setMeasuredDimension(width, height);
        //一定要在测量之后添加view，否则view不可见，也就是宽高为0
        removeAllViews();
        addLeftSwiper();
        addRightSwiper();
        addMidSwiper();
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
        if (leftSwiper != null) {
            //首先在我们视线内的是midSwiper
            midSwiper.layout(0, 0, width, height);
            //左边的话往左边移动一个swiperView的位置
            leftSwiper.layout(0, 0, width, height);
            //右边的话往右边再移动一个swiperView的位置
            rightSwiper.layout(0, 0, width, height);
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if(isAnimStart){
            return super.onTouchEvent(event);
        }

        float curX = event.getX();
        int action = event.getAction();
        switch (action) {
            case MotionEvent.ACTION_DOWN:
                //手指触摸的时候，要把自动播放去掉
                handler.removeMessages(AUTO_START_ANIM);
                downX = curX;
                moveX = curX;
                break;
            case MotionEvent.ACTION_MOVE:
                float dx = (curX - moveX);

                midSwiper.setLeft((int) (midSwiper.getLeft() + dx));
                leftSwiper.layout(midSwiper.getLeft() - width, 0, midSwiper.getLeft(), height);
                rightSwiper.layout(midSwiper.getLeft() + width, 0, midSwiper.getLeft() + 2 * width, height);

                moveX = curX;
                break;
            case MotionEvent.ACTION_UP:
            case MotionEvent.ACTION_CANCEL:
                //同样的道理，抬起的瞬间会执行动画,不需要延迟
                float resultX = curX - downX;
                if(resultX > 0){
                    handler.sendEmptyMessage(MOVE_RIGHT_ANIM);
                }else{
                    handler.sendEmptyMessage(MOVE_LEFT_ANIM);
                }
                break;
        }
        return true;
    }

    /**
     * 设置数据源
     *
     * @param datas
     */
    public void setDatas(List<T> datas) {
        this.mDatas = datas;
        int size = mDatas.size();
        if (size <= 0) {
            throw new IllegalArgumentException("size must be larger than zero");
        }
        leftSwiper.inflateModel(mDatas.get(size - 1));
        midSwiper.inflateModel(mDatas.get(0));
        if (size == 1) {
            rightSwiper.inflateModel(mDatas.get(0));
        } else {
            rightSwiper.inflateModel(mDatas.get(1));
        }
        requestLayout();

        handler.sendEmptyMessageDelayed(AUTO_START_ANIM, 3000);
    }

    private Handler handler = new Handler(Looper.getMainLooper()) {
        @Override
        public void handleMessage(Message msg) {
            //这个动画的执行是在主线程，所以资源紧张的时候会卡顿
            switch (msg.what){
                case AUTO_START_ANIM:
                    startLeftValueAnim(0, -width, true);
                    break;
                case MOVE_LEFT_ANIM:
                    //移动的时候动画有两种，一种是过了屏幕一半，一种是没有过一半需要回复原样，所以这个方法需要修改
                    int left = midSwiper.getLeft();
                    if(left < -width / 2) {
                        startLeftValueAnim(left, -width, true);
                    }else{
                        startLeftValueAnim(left, 0, false);
                    }
                    break;
                case MOVE_RIGHT_ANIM:
                    int left1 = midSwiper.getLeft();
                    if(left1 > width / 2) {
                        startRightValueAnim(left1, width, true);
                    }else{
                        startRightValueAnim(left1, 0, false);
                    }
                    break;
            }
        }
    };

    /**
     * 开始右滑估值器动画
     * @param start
     * @param end
     */
    private void startLeftValueAnim(int start, int end, final boolean exchange) {
        ValueAnimator valueAnimator = ValueAnimator.ofInt(start, end);
        valueAnimator.setDuration(1000);
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                int midValue = (Integer) animation.getAnimatedValue();
                midSwiper.layout(midValue, 0, midValue + width, height);
                rightSwiper.layout(midValue + width, 0,  midValue + 2 * width, height);
            }
        });
        valueAnimator.setInterpolator(new AccelerateDecelerateInterpolator());
        valueAnimator.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {

            }

            @Override
            public void onAnimationEnd(Animator animation) {
                isAnimStart = false;
                if(exchange) {
                    SwiperLayout<T> temp = leftSwiper;
                    leftSwiper = midSwiper;
                    midSwiper = rightSwiper;
                    rightSwiper = temp;
                    requestLayout();
                }
                handler.sendEmptyMessageDelayed(AUTO_START_ANIM, 3000);
            }

            @Override
            public void onAnimationCancel(Animator animation) {

            }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }
        });
        valueAnimator.start();
        isAnimStart = true;
    }

    /**
     * 开始右滑估值器动画
     * @param start
     * @param end
     */
    private void startRightValueAnim(int start, int end, final boolean exchange) {
        ValueAnimator valueAnimator = ValueAnimator.ofInt(start, end);
        valueAnimator.setDuration(1000);
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                int midValue = (Integer) animation.getAnimatedValue();
                midSwiper.layout(midValue, 0, midValue + width, height);
                leftSwiper.layout(midValue - width, 0,  midValue, height);
            }
        });
        valueAnimator.setInterpolator(new AccelerateDecelerateInterpolator());
        valueAnimator.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {

            }

            @Override
            public void onAnimationEnd(Animator animation) {
                isAnimStart = false;
                if(exchange) {
                    SwiperLayout<T> temp = rightSwiper;
                    rightSwiper = midSwiper;
                    midSwiper = leftSwiper;
                    leftSwiper = temp;
                    requestLayout();
                }
                handler.sendEmptyMessageDelayed(AUTO_START_ANIM, 3000);
            }

            @Override
            public void onAnimationCancel(Animator animation) {

            }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }
        });
        valueAnimator.start();
        isAnimStart = true;
    }

    /**
     * 设置左边不可见的swiper
     *
     * @param leftSwiper
     */
    public void setLeftSwiper(SwiperLayout<T> leftSwiper) {
        this.leftSwiper = leftSwiper;
    }

    /**
     * 设置中间可见的swiper
     *
     * @param midSwiper
     */
    public void setMidSwiper(SwiperLayout<T> midSwiper) {
        this.midSwiper = midSwiper;
    }

    /**
     * 设置右边不可见的swiper
     *
     * @param rightSwiper
     */
    public void setRightSwiper(SwiperLayout<T> rightSwiper) {
        this.rightSwiper = rightSwiper;
    }

    private void addLeftSwiper() {
        LayoutParams params = new LayoutParams(width, height);
        leftSwiper.setLayoutParams(params);
        addView(leftSwiper);
    }

    private void addMidSwiper() {
        LayoutParams params = new LayoutParams(width, height);
        midSwiper.setLayoutParams(params);
        addView(midSwiper);
    }

    private void addRightSwiper() {
        LayoutParams params = new LayoutParams(width, height);
        rightSwiper.setLayoutParams(params);
        addView(rightSwiper);
    }
}
