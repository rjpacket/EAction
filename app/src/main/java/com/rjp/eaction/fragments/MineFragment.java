package com.rjp.eaction.fragments;


import android.support.v4.app.Fragment;

import com.rjp.eaction.R;
import com.rjp.eaction.baseAF.BaseFragment;

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
