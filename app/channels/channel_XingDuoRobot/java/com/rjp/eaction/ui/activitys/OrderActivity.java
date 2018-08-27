package com.rjp.eaction.ui.activitys;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;

import com.flyco.tablayout.CommonTabLayout;
import com.flyco.tablayout.listener.CustomTabEntity;
import com.flyco.tablayout.listener.OnTabSelectListener;
import com.rjp.eaction.R;
import com.rjp.eaction.base.BaseActivity;
import com.rjp.eaction.bean.TabEntity;
import com.rjp.eaction.ui.fragments.OrderFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class OrderActivity extends BaseActivity {

    @BindView(R.id.tab_layout)
    CommonTabLayout tabLayout;
    @BindView(R.id.view_pager)
    ViewPager viewPager;
    private List<Fragment> fragments;

    public static void trendTo(Context mContext){
        mContext.startActivity(new Intent(mContext, OrderActivity.class));
    }

    @Override
    protected void handle() {
        ArrayList<CustomTabEntity> tabEntities = new ArrayList<>();
        tabEntities.add(new TabEntity("全部"));
        tabEntities.add(new TabEntity("待付款"));
        tabEntities.add(new TabEntity("待收货"));
        tabEntities.add(new TabEntity("已完成"));
        tabEntities.add(new TabEntity("已取消"));
        fragments = new ArrayList<>();
        fragments.add(OrderFragment.getInstance());
        fragments.add(OrderFragment.getInstance());
        fragments.add(OrderFragment.getInstance());
        fragments.add(OrderFragment.getInstance());
        fragments.add(OrderFragment.getInstance());
        tabLayout.setTabData(tabEntities);
        tabLayout.setOnTabSelectListener(new OnTabSelectListener() {
            @Override
            public void onTabSelect(int position) {
                viewPager.setCurrentItem(position);
            }

            @Override
            public void onTabReselect(int position) {

            }
        });
        viewPager.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public int getCount() {
                return fragments.size();
            }

            @Override
            public Fragment getItem(int position) {
                return fragments.get(position);
            }
        });
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                tabLayout.setCurrentTab(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    @Override
    protected void networkReload() {

    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_order;
    }

    @Override
    protected String getPageTitle() {
        return "订单";
    }

}
