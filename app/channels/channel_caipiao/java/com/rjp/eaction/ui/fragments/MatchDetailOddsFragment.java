package com.rjp.eaction.ui.fragments;


import android.support.v4.app.Fragment;

import com.rjp.eaction.R;
import com.rjp.eaction.baseAF.BaseFragment;

/**
 * 赔率
 * A simple {@link Fragment} subclass.
 */
public class MatchDetailOddsFragment extends BaseFragment {

    public MatchDetailOddsFragment() {
        // Required empty public constructor
    }

    public static MatchDetailOddsFragment getInstance(){
        MatchDetailOddsFragment testFragment = new MatchDetailOddsFragment();
        return testFragment;
    }

    @Override
    protected void networkReload() {

    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_match_detail_odds;
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
