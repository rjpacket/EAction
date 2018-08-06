package com.rjp.eaction.ui.fragments;


import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;

import com.flyco.tablayout.CommonTabLayout;
import com.flyco.tablayout.listener.CustomTabEntity;
import com.flyco.tablayout.listener.OnTabSelectListener;
import com.rjp.eaction.R;
import com.rjp.eaction.baseAF.BaseFragment;
import com.rjp.eaction.bean.TabEntity;

import java.util.ArrayList;

import butterknife.BindView;

/**
 * A simple {@link Fragment} subclass.
 */
public class JCZQLiveFragment extends BaseFragment {
    @BindView(R.id.tab_layout)
    CommonTabLayout tabLayout;
    private ArrayList<Fragment> fragments;
    private int currentIndex = 0;

    public JCZQLiveFragment() {
        // Required empty public constructor
    }

    @Override
    protected void networkReload() {

    }

    @Override
    protected void handle() {
        ArrayList<CustomTabEntity> tabEntities = new ArrayList<>();
        tabEntities.add(new TabEntity("未结束"));
        tabEntities.add(new TabEntity("已结束"));
        fragments = new ArrayList<>();
        fragments.add(JCZQSingleLiveFragment.getInstance("0"));
        fragments.add(JCZQSingleLiveFragment.getInstance("2"));
        tabLayout.setTabData(tabEntities);
        tabLayout.setOnTabSelectListener(new OnTabSelectListener() {
            @Override
            public void onTabSelect(int position) {
                showFragment(position);
            }

            @Override
            public void onTabReselect(int position) {

            }
        });
        FragmentTransaction transaction = getChildFragmentManager().beginTransaction();
        transaction.add(R.id.layout_container, fragments.get(currentIndex)).commit();
    }

    private void showFragment(int position) {
        if(currentIndex != position){
            FragmentTransaction transaction = getChildFragmentManager().beginTransaction();
            transaction.hide(fragments.get(currentIndex));
            if (!fragments.get(position).isAdded()) {
                transaction.add(R.id.layout_container, fragments.get(position));
            }
            transaction.show(fragments.get(position)).commit();
            currentIndex = position;
        }
    }

    @Override
    protected String getPageTitle() {
        return "足球直播";
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_live;
    }

}
