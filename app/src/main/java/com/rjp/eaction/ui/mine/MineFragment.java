package com.rjp.eaction.ui.mine;


import android.support.v4.app.Fragment;

import com.rjp.eaction.R;
import com.rjp.eaction.baseAF.BaseFragment;
import com.rjp.eaction.ui.login.LoginActivity;

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
    protected String getPageTitle() {
        return "我的";
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_mine;
    }


}
