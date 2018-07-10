package com.rjp.eaction.views.pick_photo;

import android.app.Dialog;
import android.content.Context;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.rjp.eaction.R;

import java.util.ArrayList;
import java.util.List;

/**
 * 查看图片的 查看器
 * author : Gimpo create on 2018/7/5 15:58
 * email  : jimbo922@163.com
 */
public class CheckPhotoView {
    private Context mContext;
    private ViewPager photoViewPager;
    private ImageView ivBack;
    private TextView tvIndicator;
    private Dialog dialog;
    private List<PhotoModel> models = new ArrayList<>();
    private int currentPos;
    private LayoutInflater layoutInflater;

    public CheckPhotoView(Context context){
        mContext = context;
        initView();
    }

    private void initView() {
        layoutInflater = LayoutInflater.from(mContext);
        View rootView = layoutInflater.inflate(R.layout.view_check_photo_view, null);
        photoViewPager = (ViewPager) rootView.findViewById(R.id.photo_view_pager);
        ivBack = (ImageView) rootView.findViewById(R.id.iv_back);
        tvIndicator = (TextView) rootView.findViewById(R.id.tv_indicator);
        dialog = new Dialog(mContext, R.style.dialog_fullscreen);
        dialog.setContentView(rootView);

        ivBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

        photoViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                tvIndicator.setText((position + 1) + "/" + models.size());
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    public void setPhotoModels(List<PhotoModel> models, int currentPos){
        this.models = models;
        this.currentPos = currentPos;
    }

    /**
     * 图片查看器展示
     */
    public void display(){
        dialog.show();
        PhotoPagerAdapter photoPagerAdapter = new PhotoPagerAdapter(mContext, models);
        photoViewPager.setAdapter(photoPagerAdapter);
        tvIndicator.setText((currentPos + 1) + "/" + models.size());
        photoViewPager.setCurrentItem(currentPos, false);
    }
}
