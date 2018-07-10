package com.rjp.eaction.views.pick_photo;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;

import com.rjp.eaction.util.AppUtils;
import com.rjp.eaction.util.ImageUtils;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * 图片展示器
 * author : Gimpo create on 2018/7/6 11:49
 * email  : jimbo922@163.com
 */
public class ShowPhotoView extends FrameLayout {
    private List<PhotoModel> models = new ArrayList<>();
    private Context mContext;
    private int lineSpace = 20;
    private int imageWidth;
    private int imageHeight;

    public ShowPhotoView(@NonNull Context context) {
        this(context, null);
    }

    public ShowPhotoView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initView(context, attrs);
    }

    private void initView(Context context, AttributeSet attrs) {
        mContext = context;
        lineSpace = AppUtils.dp2px(6);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int width = 0, height = 0;
        int count = models.size();
        if(count == 1){
            imageWidth = width = MeasureSpec.getSize(widthMeasureSpec);
            imageHeight = height = width * 9 / 16;
        }else if(count == 2){
            width = MeasureSpec.getSize(widthMeasureSpec);
            height = (width - lineSpace) / 2;
            imageWidth = imageHeight = height;
        }else{
            width = MeasureSpec.getSize(widthMeasureSpec);
            int lines = (count - 1) / 3 + 1;
            if(lines > 3){
                lines = 3;
            }
            imageWidth = imageHeight = (width - lineSpace * 2) / 3;
            height = imageWidth * lines + lineSpace * (lines - 1);
        }
        setMeasuredDimension(width, height);
        addAllImages();
    }

    /**
     * 添加所有的图片
     */
    private void addAllImages() {
        removeAllViews();
        int size = models.size();
        for (int i = 0; i < size; i++) {
            PhotoModel photoModel = models.get(i);
            ImageView imageView = new ImageView(mContext);
            imageView.setLayoutParams(new ViewGroup.LayoutParams(imageWidth, imageHeight));
            ImageUtils.loadBitmap(mContext, photoModel.getImageUrl(), imageView, imageWidth, imageHeight);
            imageView.setBackgroundColor(Color.parseColor("#ff545454"));
            imageView.setTag(i);
            imageView.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    int tag = (Integer) v.getTag();
                    CheckPhotoView checkPhotoView = new CheckPhotoView(mContext);
                    checkPhotoView.setPhotoModels(models, tag);
                    checkPhotoView.display();
                }
            });
            addView(imageView);
        }
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
        int count = models.size();
        if(count == 2){
            View view1 = getChildAt(0);
            View view2 = getChildAt(1);
            view1.layout(0, 0, imageWidth, imageWidth);
            view2.layout(imageWidth + lineSpace, 0, 2 * imageWidth + lineSpace, imageWidth);
        }else{
            int childCount = getChildCount();
            for (int i = 0; i < childCount; i++) {
                View view = getChildAt(i);
                view.layout(
                        (i % 3) * (imageWidth + lineSpace),
                        (i / 3) * (imageHeight + lineSpace),
                        (i % 3) * (imageWidth + lineSpace) + imageWidth,
                        (i / 3) * (imageHeight + lineSpace) + imageHeight
                );
            }
        }
    }

    public void setPhotoModels(List<PhotoModel> models) {
        this.models = models;
        invalidate();
//        requestLayout();
    }
}
