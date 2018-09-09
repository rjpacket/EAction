package com.rjp.eaction.ui.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.BindView;
import com.rjp.eaction.R;
import com.rjp.eaction.baseAF.BaseFragment;
import com.rjp.eaction.ui.activitys.*;

import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import com.rjp.eaction.ui.views.UserHeaderView;
import com.rjp.eaction.utils.SPUtils;

/**
 * A simple {@link Fragment} subclass.
 */
public class MineFragment extends BaseFragment {

    @BindView(R.id.mine_user_header_view)
    UserHeaderView userHeaderView;

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

    @OnClick({R.id.ll_setting_label, R.id.mine_user_header_view, R.id.ll_had_read, R.id.ll_today_with, R.id.ll_read_time, R.id.ll_family_with, R.id.ll_devices_label, R.id.ll_orders_label})
    public void onViewClicked(View view) {
        if(view.getId() == R.id.ll_setting_label){
            SettingActivity.trendTo(mContext);
            return;
        }
        if(!SPUtils.getInstance(mContext).getIsLogin()){
            LoginActivity.trendTo(mContext);
            return;
        }
        switch (view.getId()) {
            case R.id.mine_user_header_view:
                AccountManageActivity.trendTo(mContext);
                break;
            case R.id.ll_had_read:
                AlreadyReadBookActivity.trendTo(mContext);
                break;
            case R.id.ll_today_with:
                TodayWithActivity.trendTo(mContext);
                break;
            case R.id.ll_read_time:
                ReadTimeActivity.trendTo(mContext);
                break;
            case R.id.ll_family_with:
                FamilyWithActivity.trendTo(mContext);
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
