package com.rjp.eaction.ui.fragments;


import android.support.v4.app.Fragment;

import com.rjp.eaction.R;
import com.rjp.eaction.baseAF.BaseFragment;
import com.rjp.eaction.ui.social.SendSocialActivity;

/**
 * A simple {@link Fragment} subclass.
 */
public class LiveFragment extends BaseFragment {


    public LiveFragment() {
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
        return "足球直播";
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_social;
    }

}
