package com.rjp.eaction.fragments;


import android.support.v4.app.Fragment;
import android.view.View;

import com.rjp.eaction.R;
import com.rjp.eaction.baseAF.BaseFragment;

/**
 * A simple {@link Fragment} subclass.
 */
public class SocialFragment extends BaseFragment {


    public SocialFragment() {
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
        return "社区";
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_social;
    }

}
