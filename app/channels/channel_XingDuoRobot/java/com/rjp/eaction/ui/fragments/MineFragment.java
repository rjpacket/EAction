package com.rjp.eaction.ui.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.rjp.eaction.R;
import com.rjp.eaction.baseAF.BaseFragment;
import com.rjp.eaction.ui.activitys.DevicesManageActivity;
import com.rjp.eaction.ui.activitys.OrderActivity;

import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * A simple {@link Fragment} subclass.
 */
public class MineFragment extends BaseFragment {

    Unbinder unbinder;

    public MineFragment() {
        // Required empty public constructor
    }

    public static MineFragment getInstance() {
        MineFragment fragment = new MineFragment();
        return fragment;
    }

    @Override
    protected void networkReload() {

    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_mine;
    }

    @Override
    protected boolean showTopBar() {
        return false;
    }

    @Override
    protected String getPageTitle() {
        return "";
    }

    @Override
    protected void handle() {

    }

    @OnClick({R.id.ll_had_read, R.id.ll_today_with, R.id.ll_read_time, R.id.ll_family_with, R.id.ll_devices_label, R.id.ll_orders_label})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ll_had_read:
                break;
            case R.id.ll_today_with:
                break;
            case R.id.ll_read_time:
                break;
            case R.id.ll_family_with:
                break;
            case R.id.ll_devices_label:
                DevicesManageActivity.trendTo(mContext);
                break;
            case R.id.ll_orders_label:
                OrderActivity.trendTo(mContext);
                break;
        }
    }
}
