package com.rjp.eaction.views.pick_photo;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.ThumbnailUtils;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.GridView;
import android.widget.ImageView;

import com.rjp.commonadapter.CommonAdapter;
import com.rjp.commonadapter.ViewHolder;
import com.rjp.eaction.R;
import com.rjp.eaction.permission.PermissionCallback;
import com.rjp.eaction.permission.PermissionUtils;
import com.rjp.eaction.util.AppUtils;
import com.rjp.eaction.util.ImageUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * 选择图片的  选择器
 * author : Gimpo create on 2018/7/5 10:42
 * email  : jimbo922@163.com
 */
public class PickPhotoView extends FrameLayout {

    private Context mContext;
    private GridView gridView;
    private List<PhotoModel> models;
    private int imageWidth;
    private CommonAdapter<PhotoModel> photoAdapter;
    private float downY;
    private int measuredHeight;

    public PickPhotoView(@NonNull Context context) {
        this(context, null);
    }

    public PickPhotoView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initView(context, attrs);
    }

    private void initView(Context context, AttributeSet attrs) {
        mContext = context;
        LayoutInflater.from(mContext).inflate(R.layout.view_pick_photo_view, this);
        gridView = (GridView) findViewById(R.id.grid_view);
        models = new ArrayList<>();
        models.add(0, new PhotoModel(""));
        gridView.setAdapter(photoAdapter = new CommonAdapter<PhotoModel>(mContext, R.layout.item_pick_photo_view, models) {
            @Override
            protected void convert(ViewHolder viewHolder, PhotoModel item, final int position) {
                ImageView ivImage = viewHolder.getView(R.id.iv_photo);
                ImageView ivDelete = viewHolder.getView(R.id.iv_delete);
                FrameLayout.LayoutParams params = new LayoutParams(imageWidth, imageWidth);
                ivImage.setLayoutParams(params);
                if(position == models.size() - 1){
                    ivDelete.setVisibility(GONE);
                    ivImage.setImageResource(R.mipmap.add_more_photo);
                    ivImage.setOnClickListener(new OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            new PermissionUtils.Builder()
                                    .permission(Manifest.permission.WRITE_EXTERNAL_STORAGE)
                                    .context(mContext)
                                    .build()
                                    .request(new PermissionCallback() {
                                        @Override
                                        public void allow() {
                                            ImageUtils.pickPhoto((Activity) mContext, 10086);
                                        }

                                        @Override
                                        public void deny() {

                                        }
                                    });
                        }
                    });
                }else {
                    ivDelete.setVisibility(VISIBLE);
                    Bitmap bitmap = BitmapFactory.decodeFile(item.getFilePath());
                    ivImage.setImageBitmap(ThumbnailUtils.extractThumbnail(bitmap, imageWidth, imageWidth));
                    ivImage.setOnClickListener(new OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            CheckPhotoView checkPhotoView = new CheckPhotoView(mContext);
                            checkPhotoView.setPhotoModels(models.subList(0, models.size() - 1), position);
                            checkPhotoView.display();
                        }
                    });
                }
            }
        });


    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int width = MeasureSpec.getSize(widthMeasureSpec);
        imageWidth = (width - 2 * AppUtils.dp2px(6)) / 3;
        int size = models.size() - 1;
        //额外的高度
        int extra = 0;
        int lines = size / 3;
        if (lines > 1) {
            lines = 1;
            extra = AppUtils.dp2px(40);
        }
        measuredHeight = imageWidth * (lines + 1) + AppUtils.dp2px(6) * lines + extra;
        setMeasuredDimension(width, measuredHeight);
    }

    public void addPickPhotoModel(PhotoModel photoModel) {
        models.add(0, photoModel);
        invalidate();
        photoAdapter.notifyDataSetChanged();
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        int action = ev.getAction();
        float curY = ev.getY();
        switch (action) {
            case MotionEvent.ACTION_DOWN:
                downY = curY;
                break;
            case MotionEvent.ACTION_MOVE:
                float dy = curY - downY;
                if(Math.abs(dy) > 20){
                    return true;
                }
                break;
            case MotionEvent.ACTION_UP:
                break;
        }
        return super.onInterceptTouchEvent(ev);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int action = event.getAction();
        float curY = event.getY();
        switch (action) {
            case MotionEvent.ACTION_DOWN:
                downY = curY;
                break;
            case MotionEvent.ACTION_MOVE:
                float dy = curY - downY;
                scrollTo(0, (int) (getScrollY() - dy));
                downY = curY;
                break;
            case MotionEvent.ACTION_UP:
                break;
        }
        return true;
    }

    @Override
    public void scrollTo(int x, int y) {
        if(y < 0){
            y = 0;
        }
        int lines = (models.size() - 1) / 3;
        int totalHeight = (lines + 1) * imageWidth + lines * AppUtils.dp2px(6);
        int disHeight = totalHeight - measuredHeight;
        if(y > disHeight){
            y = disHeight;
        }
        super.scrollTo(x, y);
    }
}
