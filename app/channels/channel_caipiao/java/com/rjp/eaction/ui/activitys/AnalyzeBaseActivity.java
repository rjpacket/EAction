package com.rjp.eaction.ui.activitys;

import android.support.v4.app.Fragment;

import com.flyco.tablayout.CommonTabLayout;
import com.flyco.tablayout.listener.CustomTabEntity;
import com.rjp.eaction.R;
import com.rjp.eaction.baseAF.BaseActivity;

import java.util.ArrayList;

import butterknife.BindView;

public abstract class AnalyzeBaseActivity extends BaseActivity {

    @BindView(R.id.tab_layout) CommonTabLayout tabLayout;

    private ArrayList<CustomTabEntity> mTabEntities = new ArrayList<>();
    private ArrayList<Fragment> mFragments = new ArrayList<>();

    @Override
    protected void networkReload() {

    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_analyze_base;
    }

    @Override
    protected void handle() {
        mFragments = getFragments();
        mTabEntities = getTabEntities();
        tabLayout.setTabData(mTabEntities, this, R.id.layout_container, mFragments);
    }

    protected abstract ArrayList<CustomTabEntity> getTabEntities();

    public abstract ArrayList<Fragment> getFragments();
}
