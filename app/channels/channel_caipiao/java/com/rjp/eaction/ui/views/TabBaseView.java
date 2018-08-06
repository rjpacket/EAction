package com.rjp.eaction.ui.views;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.LinearLayout;

import com.flyco.tablayout.CommonTabLayout;
import com.flyco.tablayout.listener.CustomTabEntity;
import com.rjp.eaction.R;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by small on 2018/7/27.
 */

public abstract class TabBaseView extends LinearLayout {
    @BindView(R.id.tab_layout)
    CommonTabLayout tabLayout;

    private ArrayList<CustomTabEntity> mTabEntities = new ArrayList<>();
    private ArrayList<Fragment> mFragments = new ArrayList<>();

    public TabBaseView(Context context) {
        this(context, null);
    }

    public TabBaseView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initView(context, attrs);
    }

    private void initView(Context context, AttributeSet attrs) {
        LayoutInflater.from(context).inflate(R.layout.view_tab_base_view, this);
        ButterKnife.bind(this);

        mFragments = getFragments();
        mTabEntities = getTabEntities();
        if(context instanceof FragmentActivity) {
            tabLayout.setTabData(mTabEntities, (FragmentActivity) context, R.id.layout_container, mFragments);
        }
    }

    protected abstract ArrayList<CustomTabEntity> getTabEntities();

    public abstract ArrayList<Fragment> getFragments();
}
