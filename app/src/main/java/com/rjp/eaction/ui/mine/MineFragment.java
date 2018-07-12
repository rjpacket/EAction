package com.rjp.eaction.ui.mine;


import android.support.v4.app.Fragment;
import android.view.View;

import com.rjp.eaction.R;
import com.rjp.eaction.baseAF.BaseFragment;
import com.rjp.eaction.ui.login.LoginActivity;

import butterknife.OnClick;

/**
 * A simple {@link Fragment} subclass.
 */
public class MineFragment extends BaseFragment {


    public MineFragment() {
        // Required empty public constructor
    }

    @Override
    protected void networkReload() {

    }

    @Override
    protected void handle() {
        LoginActivity.trendTo(mContext);
    }

    @Override
    protected boolean showTopBar() {
        return false;
    }

    @Override
    protected String getPageTitle() {
        return "我的";
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_mine;
    }

    @OnClick({R.id.ll_personal_info, R.id.ll_system_setting})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.ll_personal_info:
                PersonalInfoActivity.trendTo(mContext);
                break;
            case R.id.ll_system_setting:
                break;
        }
    }
}
