package com.rjp.eaction.ui.views;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import android.widget.TextView;
import com.rjp.eaction.R;
import com.rjp.eaction.bean.HomeCategoryModel;
import com.rjp.eaction.interfaces.OnCategoryClickListener;
import com.rjp.eaction.util.ImageUtils;

import java.util.List;

/**
 * author : Gimpo create on 2018/9/12 17:03
 * email  : jimbo922@163.com
 */
public class IndicatorTabLayout extends LinearLayout {
    private int selectedIndex;
    private LinearLayout llContainer;
    private LayoutInflater layoutInflater;
    private List<HomeCategoryModel> models;
    private Context mContext;
    private OnCategoryClickListener onCategoryClickListener;

    public IndicatorTabLayout(Context context) {
        this(context, null);
    }

    public IndicatorTabLayout(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initView(context, attrs);
    }

    private void initView(Context context, AttributeSet attrs) {
        mContext = context;
        layoutInflater = LayoutInflater.from(context);
        layoutInflater.inflate(R.layout.view_indicator_tab_layout, this);
        llContainer = findViewById(R.id.ll_container);
    }

    public void setTabData(final List<HomeCategoryModel> models){
        this.models = models;
        int size = models.size();
        llContainer.removeAllViews();
        for (int i = 0; i < size; i++) {
            View view = layoutInflater.inflate(R.layout.item_indicator_tab_layout, null);
            HomeCategoryModel model = models.get(i);
            TextView tvTab = view.findViewById(R.id.tv_tab_name);
            tvTab.setText(model.getName());
            ImageView ivTabIcon = view.findViewById(R.id.iv_tab_icon);
            ImageUtils.load(mContext, model.getUncheckIcon(), ivTabIcon);
            view.setTag(i);
            view.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    int tag = (Integer) v.getTag();
                    setClickSelected(tag);
                    if(onCategoryClickListener != null){
                        HomeCategoryModel categoryModel = models.get(tag);
                        onCategoryClickListener.onCategoryClick(categoryModel.getId());
                    }
                }
            });
            llContainer.addView(view);
        }
    }

    public void setClickSelected(int position) {
        clearSelected();
        View view = llContainer.getChildAt(position);
        HomeCategoryModel model = models.get(position);
        ImageView ivTabIcon = view.findViewById(R.id.iv_tab_icon);
        ImageUtils.load(mContext, model.getSelectIcon(), ivTabIcon);
        view.findViewById(R.id.iv_indicator).setVisibility(VISIBLE);
        view.setSelected(true);
    }

    /**
     * 清除选中状态
     */
    private void clearSelected() {
        int count = llContainer.getChildCount();
        for (int i = 0; i < count; i++) {
            View view = llContainer.getChildAt(i);
            HomeCategoryModel model = models.get(i);
            ImageView ivTabIcon = view.findViewById(R.id.iv_tab_icon);
            ImageUtils.load(mContext, model.getUncheckIcon(), ivTabIcon);
            view.findViewById(R.id.iv_indicator).setVisibility(INVISIBLE);
            view.setSelected(false);
        }
    }

    public void setOnCategoryClickListener(OnCategoryClickListener onCategoryClickListener) {
        this.onCategoryClickListener = onCategoryClickListener;
    }
}
