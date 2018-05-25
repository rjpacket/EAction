### onMeasure

轮播图的话，我认为宽高最合适的比例是16:9，所以我按照这个尺寸来：
```
public class SwiperView extends FrameLayout {


    private int width;
    private int height;

    public SwiperView(@NonNull Context context) {
        super(context);
    }

    public SwiperView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        width = MeasureSpec.getSize(widthMeasureSpec);
        height = (int) (width * 9 * 1.0 / 16);
        setMeasuredDimension(width, height);
    }
}
```
首先确定View的宽高，其他的不管。既然是轮播图，肯定有数据源和三张图，左边一张看不见的图，中间一张显示的图，右边一张将要进来的图。我们写的通用化一点，不单单是图片轮播，布局轮播也可以。

### SwiperLayout

我们抽象一个布局，必要的时候再去填充，而且是一个用数据填充的布局：
```
public abstract class SwiperLayout<T> extends FrameLayout{

    public SwiperLayout(@NonNull Context context) {
        super(context);
    }

    public SwiperLayout(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public SwiperLayout(@NonNull Context context, T model){
        super(context);
        View view = inflateModel(model);
        LayoutParams params = new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        view.setLayoutParams(params);
        addView(view);
    }

    public abstract View inflateModel(T model);

    public abstract View notifyModel(T model);
}
```
注意区分它和上面的SwiperView，SwiperLayout只是每一个可滑动的最小单元。SwiperLayout上面两个方法是固定要重写的，主要看下面三个方法，一个是入参的时候带一个泛型的model，根据这个model去填充布局，有点类似ListView，这个填充的方法我们抽象，暂时不需要知道细节。然后抽象出来的布局，我们设置LayoutParams，让它充满整个布局。inflateModel() 和 notifyModel() 功能类似，只不过一个是填充，一个是更新，优化节省UI渲染时间。

### setData setLeftSwiper setMidSwiper setRightSwiper

```
public class SwiperView<T> extends FrameLayout {

    private int width;
    private int height;
    //数据源
    private List<T> mDatas;
    //布局  只需要缓存三个就行
    private SwiperLayout<T> leftSwiper;
    private SwiperLayout<T> midSwiper;
    private SwiperLayout<T> rightSwiper;

    public SwiperView(@NonNull Context context) {
        super(context);
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
        addLeftSwiper();
        addMidSwiper();
        addRightSwiper();
    }

    /**
     * 设置数据源
     * @param datas
     */
    public void setDatas(List<T> datas){
        this.mDatas = datas;
        int size = mDatas.size();
        if(size <= 0){
            throw new IllegalArgumentException("size must be larger than zero");
        }
        leftSwiper.inflateModel(mDatas.get(size - 1));
        midSwiper.inflateModel(mDatas.get(0));
        if(size == 1){
            rightSwiper.inflateModel(mDatas.get(0));
        }else{
            rightSwiper.inflateModel(mDatas.get(1));
        }
        requestLayout();
        //startAnim();
    }

    /**
     * 设置左边不可见的swiper
     * @param leftSwiper
     */
    public void setLeftSwiper(SwiperLayout<T> leftSwiper){
        this.leftSwiper = leftSwiper;
    }

    /**
     * 设置中间可见的swiper
     * @param midSwiper
     */
    public void setMidSwiper(SwiperLayout<T> midSwiper){
        this.midSwiper = midSwiper;
    }

    /**
     * 设置右边不可见的swiper
     * @param rightSwiper
     */
    public void setRightSwiper(SwiperLayout<T> rightSwiper){
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
```
在设置数据源的时候，我们应该在之前设置好三个布局，然后调用requestLayout()，进入到 onLayout() 里面。

### onLayout

```
public class SwiperView<T> extends FrameLayout {

    private int width;
    private int height;
    //数据源
    private List<T> mDatas;
    //布局  只需要缓存三个就行
    private SwiperLayout<T> leftSwiper;
    private SwiperLayout<T> midSwiper;
    private SwiperLayout<T> rightSwiper;

    public SwiperView(@NonNull Context context) {
        super(context);
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
        addLeftSwiper();
        addMidSwiper();
        addRightSwiper();
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
        if(leftSwiper != null) {
            //首先在我们视线内的是midSwiper
            midSwiper.layout(0, 0, width, height);
            //左边的话往左边移动一个swiperView的位置
            leftSwiper.layout(-width, 0, 0, height);
            //右边的话往右边再移动一个swiperView的位置
            rightSwiper.layout(width, 0, 2 * width, height);
        }
    }

    /**
     * 设置数据源
     * @param datas
     */
    public void setDatas(List<T> datas){
        this.mDatas = datas;
        int size = mDatas.size();
        if(size <= 0){
            throw new IllegalArgumentException("size must be larger than zero");
        }
        leftSwiper.inflateModel(mDatas.get(size - 1));
        midSwiper.inflateModel(mDatas.get(0));
        if(size == 1){
            rightSwiper.inflateModel(mDatas.get(0));
        }else{
            rightSwiper.inflateModel(mDatas.get(1));
        }
        requestLayout();
        //startAnim();
    }

    /**
     * 设置左边不可见的swiper
     * @param leftSwiper
     */
    public void setLeftSwiper(SwiperLayout<T> leftSwiper){
        this.leftSwiper = leftSwiper;
    }

    /**
     * 设置中间可见的swiper
     * @param midSwiper
     */
    public void setMidSwiper(SwiperLayout<T> midSwiper){
        this.midSwiper = midSwiper;
    }

    /**
     * 设置右边不可见的swiper
     * @param rightSwiper
     */
    public void setRightSwiper(SwiperLayout<T> rightSwiper){
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
```
onLayout()方法比较简单，目的就是控制三个swiperLayout的位置。接下来我们先不增加手指的触摸事件，我们让它自己执行一个动画，属性动画或者补间动画都没有问题。(感觉没问题，但是补间动画有可能会产生那种无限执行的情况，不研究了)

### 动画

```
public class SwiperView<T> extends FrameLayout {
    public static final int START_ANIM = 200;

    private int width;
    private int height;
    //数据源
    private List<T> mDatas;
    //布局  只需要缓存三个就行
    private SwiperLayout<T> leftSwiper;
    private SwiperLayout<T> midSwiper;
    private SwiperLayout<T> rightSwiper;

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
        if(leftSwiper != null) {
            //首先在我们视线内的是midSwiper
            midSwiper.layout(0, 0, width, height);
            //左边的话往左边移动一个swiperView的位置
            leftSwiper.layout(0, 0, width, height);
            //右边的话往右边再移动一个swiperView的位置
            rightSwiper.layout(0, 0, width, height);
        }
    }

    /**
     * 设置数据源
     * @param datas
     */
    public void setDatas(List<T> datas){
        this.mDatas = datas;
        int size = mDatas.size();
        if(size <= 0){
            throw new IllegalArgumentException("size must be larger than zero");
        }
        leftSwiper.inflateModel(mDatas.get(size - 1));
        midSwiper.inflateModel(mDatas.get(0));
        if(size == 1){
            rightSwiper.inflateModel(mDatas.get(0));
        }else{
            rightSwiper.inflateModel(mDatas.get(1));
        }
        requestLayout();

        handler.sendEmptyMessageDelayed(START_ANIM, 3000);
    }

    private Handler handler = new Handler(Looper.getMainLooper()){
        @Override
        public void handleMessage(Message msg) {
            startAnim(midSwiper.getLeft(), width);
        }
    };

    /**
     * 开启动画 间隔3s
     */
    private void startAnim(float midSwiperLeft, float rightSwiperLeft) {
        ObjectAnimator midAnim = ObjectAnimator.ofFloat(midSwiper, "translationX", midSwiperLeft, -width);
        ObjectAnimator rightAnim = ObjectAnimator.ofFloat(rightSwiper, "translationX", rightSwiperLeft, 0);
        AnimatorSet animSet = new AnimatorSet();
        animSet.playTogether(midAnim, rightAnim);
        animSet.setInterpolator(new AccelerateDecelerateInterpolator());
        animSet.setDuration(500);
        animSet.start();
        animSet.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {

            }

            @Override
            public void onAnimationEnd(Animator animation) {
                SwiperLayout<T> temp = leftSwiper;
                leftSwiper = midSwiper;
                midSwiper = rightSwiper;
                rightSwiper = temp;
                requestLayout();
                handler.sendEmptyMessageDelayed(START_ANIM, 3000);
            }

            @Override
            public void onAnimationCancel(Animator animation) {

            }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }
        });
    }

    /**
     * 设置左边不可见的swiper
     * @param leftSwiper
     */
    public void setLeftSwiper(SwiperLayout<T> leftSwiper){
        this.leftSwiper = leftSwiper;
    }

    /**
     * 设置中间可见的swiper
     * @param midSwiper
     */
    public void setMidSwiper(SwiperLayout<T> midSwiper){
        this.midSwiper = midSwiper;
    }

    /**
     * 设置右边不可见的swiper
     * @param rightSwiper
     */
    public void setRightSwiper(SwiperLayout<T> rightSwiper){
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
```
我们在设置数据源之后，立马利用Handler延迟开启一个动画，让中间的布局移动到左边，右边的布局移动到中间。千万注意，这里onLayout中的代码我们已经修改了，是三个布局层叠的放置，因为如果是并排放置，动画会有问题。ViewGroup超出屏幕的部分被截断了，所以会有空白出现，总之这里是非常机智的写法。

### 触摸事件 onTouchEvent 事件

我写下这段话的时候还不肯定是不是就是重写onTouchEvent事件，如果只是触摸左右滑onTouchEvent肯定是足够了，但是如果想有一些sao操作，肯定是要配合事件分发的三个方法一起使用效果更好。
```
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
            //这个动画的执行是在主线程，所以资源紧张的时候会卡顿。你问我什么资源紧张？我也不知道啊。可能cpu(70%)，可能内存(30%)
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
        valueAnimator.setDuration(500);
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                int midValue = (Integer) animation.getAnimatedValue();
                midSwiper.layout(midValue, 0, midValue + width, height);
                rightSwiper.layout(midValue + width, 0,  midValue + 2 * width, height);
            }
        });
        valueAnimator.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {

            }

            @Override
            public void onAnimationEnd(Animator animation) {
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
    }

    /**
     * 开始右滑估值器动画
     * @param start
     * @param end
     */
    private void startRightValueAnim(int start, int end, final boolean exchange) {
        ValueAnimator valueAnimator = ValueAnimator.ofInt(start, end);
        valueAnimator.setDuration(500);
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                int midValue = (Integer) animation.getAnimatedValue();
                midSwiper.layout(midValue, 0, midValue + width, height);
                leftSwiper.layout(midValue - width, 0,  midValue, height);
            }
        });
        valueAnimator.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {

            }

            @Override
            public void onAnimationEnd(Animator animation) {
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
```
这里代码突然变的多了起来，主要是有左滑和右滑的区别，在onTouchEvent()里面需要根据不同的方向写不同的逻辑。到这里一个轮播图差不多完成了，但是还是有瑕疵的。

### 瑕疵

>  1.当一个动画没有结束，手指立马继续滑动的时候，会造成重复发送自动播放Handler，然后下次播放的时候会播放两张图片
>  2.快速切换的时候，动画执行时间应该缩短
>  3.快速左切右切，动画会混乱

1,3问题都可以在down事件里面进行拦截判断，或者加一个动画在执行的flag。
第二个问题也不是不能解决，判断如果手指的速度非常大，那么就将动画的执行时间设置短一点。
上面问题都是可以通过flag解决:
```
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
            //这个动画的执行是在主线程，所以资源紧张的时候会卡顿。你问我什么资源紧张？我也不知道啊。可能cpu(70%)，可能内存(30%)
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
        valueAnimator.setDuration(500);
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                int midValue = (Integer) animation.getAnimatedValue();
                midSwiper.layout(midValue, 0, midValue + width, height);
                rightSwiper.layout(midValue + width, 0,  midValue + 2 * width, height);
            }
        });
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
        valueAnimator.setDuration(500);
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                int midValue = (Integer) animation.getAnimatedValue();
                midSwiper.layout(midValue, 0, midValue + width, height);
                leftSwiper.layout(midValue - width, 0,  midValue, height);
            }
        });
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

```

### 抽象SwiperLayout, 方便定制
```
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

```

### 文中用到的ImageSwiper
```
package com.rjp.eaction.swiper;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.rjp.eaction.util.ImageUtils;

/**
 * author : Gimpo create on 2018/5/25 14:19
 * email  : jimbo922@163.com
 */
public class ImageSwiper extends SwiperLayout<String> {

    private ImageView imageView;

    public ImageSwiper(@NonNull Context context) {
        super(context);
    }

    @Override
    public void inflateModel(String model) {
        imageView = new ImageView(mContext);
        imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
        LayoutParams params = new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        imageView.setLayoutParams(params);
        ImageUtils.load(mContext, model, imageView);
        addView(imageView);
    }

    @Override
    public void notifyModel(String model) {
        ImageUtils.load(mContext, model, imageView);
    }
}

```

以上就是完整代码。喜欢可以点个赞。



