package com.rjp.eaction.ui.fragments;


import android.support.v4.app.Fragment;

import com.rjp.eaction.R;
import com.rjp.eaction.baseAF.BaseFragment;

/**
 * 战绩
 * A simple {@link Fragment} subclass.
 */
public class MatchDetailGainsFragment extends BaseFragment {

    public MatchDetailGainsFragment() {
        // Required empty public constructor
    }

    public static MatchDetailGainsFragment getInstance(){
        MatchDetailGainsFragment testFragment = new MatchDetailGainsFragment();
        return testFragment;
    }

    @Override
    protected void networkReload() {

    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_match_detail_gains;
    }

    @Override
    protected String getPageTitle() {
        return "";
    }

    @Override
    protected boolean showTopBar() {
        return false;
    }

    @Override
    protected void handle() {

    }
}
