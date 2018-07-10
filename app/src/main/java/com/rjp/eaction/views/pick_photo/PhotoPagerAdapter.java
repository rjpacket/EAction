package com.rjp.eaction.views.pick_photo;

import android.content.Context;
import android.graphics.Bitmap;
import android.support.v4.view.PagerAdapter;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;

import com.github.chrisbanes.photoview.PhotoView;
import com.rjp.eaction.util.AppUtils;
import com.rjp.eaction.util.ImageUtils;

import java.util.ArrayList;
import java.util.List;


public class PhotoPagerAdapter extends PagerAdapter {

    private List<PhotoView> views = new ArrayList<>();
    private List<PhotoModel> models;
    private Context mContext;
    private int screenWidth;
    private int screenHeight;

    public PhotoPagerAdapter(Context context, List<PhotoModel> models) {
        mContext = context;
        this.models = models;
        screenWidth = AppUtils.getScreenWidth(mContext);
        screenHeight = AppUtils.getScreenHeight(mContext);
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        PhotoView cellView;
        if(views.size() == 0){
            cellView = new PhotoView(mContext);
        }else{
            cellView = views.remove(0);
        }
//        PhotoView cellView = new PhotoView(mContext);
        ViewGroup.LayoutParams params = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        cellView.setLayoutParams(params);
        PhotoModel photoModel = models.get(position);
        String filePath = photoModel.getFilePath();
        if(!TextUtils.isEmpty(filePath)) {
            Bitmap bitmap = ImageUtils.decodeBitmapFromFile(filePath, AppUtils.getScreenWidth(mContext), AppUtils.getScreenHeight(mContext));
            cellView.setImageBitmap(bitmap);
        }else{
            ImageUtils.load(mContext, photoModel.getImageUrl(), cellView);
        }
        container.addView(cellView);
        return cellView;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        PhotoView cellView = (PhotoView) object;
        container.removeView(cellView);
        views.add(cellView);
    }

    @Override
    public int getCount() {
        return models.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }
}