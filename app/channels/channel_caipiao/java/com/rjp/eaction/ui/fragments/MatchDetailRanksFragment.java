package com.rjp.eaction.ui.fragments;


import android.support.v4.app.Fragment;

import com.rjp.eaction.R;
import com.rjp.eaction.baseAF.BaseFragment;

/**
 * 积分
 * A simple {@link Fragment} subclass.
 */
public class MatchDetailRanksFragment extends BaseFragment {

    public MatchDetailRanksFragment() {
        // Required empty public constructor
    }

    public static MatchDetailRanksFragment getInstance(){
        MatchDetailRanksFragment testFragment = new MatchDetailRanksFragment();
        return testFragment;
    }

    @Override
    protected void networkReload() {

    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_match_detail_ranks;
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
