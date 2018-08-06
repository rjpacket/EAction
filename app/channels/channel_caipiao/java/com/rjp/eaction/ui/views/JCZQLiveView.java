package com.rjp.eaction.ui.views;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.AttributeSet;

import com.flyco.tablayout.listener.CustomTabEntity;
import com.rjp.eaction.bean.TabEntity;
import com.rjp.eaction.ui.fragments.JCZQSingleLiveFragment;

import java.util.ArrayList;

/**
 * Created by small on 2018/7/27.
 */

public class JCZQLiveView extends TabBaseView {
    public JCZQLiveView(Context context) {
        super(context);
    }

    public JCZQLiveView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected ArrayList<CustomTabEntity> getTabEntities() {
        ArrayList<CustomTabEntity> tabEntities = new ArrayList<>();
        tabEntities.add(new TabEntity("未结束"));
        tabEntities.add(new TabEntity("已结束"));
        return tabEntities;
    }

    @Override
    public ArrayList<Fragment> getFragments() {
        ArrayList<Fragment> fragments = new ArrayList<>();
        fragments.add(JCZQSingleLiveFragment.getInstance("1"));
        fragments.add(JCZQSingleLiveFragment.getInstance("2"));
        return fragments;
    }
}
