package com.rjp.eaction.ui.views;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;

import com.rjp.eaction.R;

/**
 * author : Gimpo create on 2018/9/12 17:03
 * email  : jimbo922@163.com
 */
public class IndicatorTabLayout extends LinearLayout {
    private int selectedIndex;
    private LinearLayout llContainer;

    public IndicatorTabLayout(Context context) {
        this(context, null);
    }

    public IndicatorTabLayout(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initView(context, attrs);
    }

    private void initView(Context context, AttributeSet attrs) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        layoutInflater.inflate(R.layout.view_indicator_tab_layout, this);
        llContainer = findViewById(R.id.ll_container);

        for (int i = 0; i < 4; i++) {
            View view = layoutInflater.inflate(R.layout.item_indicator_tab_layout, null);
            view.setTag(i);
            view.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    clearSelected();
                    int tag = (Integer) v.getTag();
                    setClickSelected(tag);
                }
            });
            llContainer.addView(view);
        }
    }

    private void setClickSelected(int position) {
        View view = llContainer.getChildAt(position);
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
            view.findViewById(R.id.iv_indicator).setVisibility(INVISIBLE);
            view.setSelected(false);
        }
    }
}
