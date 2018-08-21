package com.rjp.eaction;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.widget.LinearLayout;

import com.rjp.eaction.baseAF.BaseActivity;
import com.rjp.eaction.ui.fragments.BookFragment;
import com.rjp.eaction.ui.fragments.HomeFragment;
import com.rjp.eaction.ui.fragments.MineFragment;
import com.rjp.eaction.ui.fragments.StoreFragment;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends BaseActivity {

    @BindView(R.id.ll_tab1)
    LinearLayout llTab1;
    @BindView(R.id.ll_tab2)
    LinearLayout llTab2;
    @BindView(R.id.ll_tab3)
    LinearLayout llTab3;
    @BindView(R.id.ll_tab4)
    LinearLayout llTab4;

    private ArrayList<Fragment> fragments;
    private FragmentManager fragmentManager;
    private int selectIndex = 0;

    @Override
    protected boolean showTopBar() {
        return false;
    }

    @Override
    protected void handle() {
        fragmentManager = getSupportFragmentManager();
        fragments = new ArrayList<>();
        fragments.add(HomeFragment.getInstance());
        fragments.add(BookFragment.getInstance());
        fragments.add(StoreFragment.getInstance());
        fragments.add(MineFragment.getInstance());

        showFragment(0);
        setTabSelect(llTab1, llTab2, llTab3, llTab4);
    }

    private void showFragment(int currentIndex) {
        if (fragments == null) {
            return;
        }
        FragmentTransaction trx = fragmentManager.beginTransaction();
        trx.hide(fragments.get(selectIndex));
        Fragment currentFragment = fragments.get(currentIndex);
        if (!currentFragment.isAdded()) {
            trx.add(R.id.main_container, currentFragment);
        }
        trx.show(currentFragment).commitAllowingStateLoss();
        selectIndex = currentIndex;
    }

    @Override
    protected void networkReload() {

    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected String getPageTitle() {
        return "首页";
    }

    @OnClick({R.id.ll_tab1, R.id.ll_tab2, R.id.ll_tab3, R.id.ll_tab4})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ll_tab1:
                showFragment(0);
                setTabSelect(llTab1, llTab2, llTab3, llTab4);
                break;
            case R.id.ll_tab2:
                showFragment(1);
                setTabSelect(llTab2, llTab1, llTab3, llTab4);
                break;
            case R.id.ll_tab3:
                showFragment(2);
                setTabSelect(llTab3, llTab2, llTab1, llTab4);
                break;
            case R.id.ll_tab4:
                showFragment(3);
                setTabSelect(llTab4, llTab2, llTab3, llTab1);
                break;
        }
    }

    private void setTabSelect(LinearLayout llTab1, LinearLayout llTab2, LinearLayout llTab3, LinearLayout llTab4) {
        llTab1.setSelected(true);
        llTab2.setSelected(false);
        llTab3.setSelected(false);
        llTab4.setSelected(false);
    }
}
