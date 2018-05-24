package com.rjp.eaction.fragments;


import android.support.v4.app.Fragment;

import com.rjp.eaction.R;
import com.rjp.eaction.baseAF.BaseFragment;

/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends BaseFragment {


    public HomeFragment() {
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
        return "首页";
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_home;
    }
}
