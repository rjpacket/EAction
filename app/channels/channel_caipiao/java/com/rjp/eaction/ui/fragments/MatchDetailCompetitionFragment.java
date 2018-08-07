package com.rjp.eaction.ui.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;

import com.rjp.eaction.R;
import com.rjp.eaction.baseAF.BaseFragment;
import com.zhy.adapter.recyclerview.CommonAdapter;

import java.util.ArrayList;

import butterknife.BindView;

/**
 * 赛事
 * A simple {@link Fragment} subclass.
 */
public class MatchDetailCompetitionFragment extends BaseFragment {

    public MatchDetailCompetitionFragment() {
        // Required empty public constructor
    }

    public static MatchDetailCompetitionFragment getInstance(){
        MatchDetailCompetitionFragment testFragment = new MatchDetailCompetitionFragment();
        return testFragment;
    }

    @Override
    protected void networkReload() {

    }

    @Override
    protected boolean showTopBar() {
        return false;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_match_detail_competition;
    }

    @Override
    protected String getPageTitle() {
        return "";
    }

    @Override
    protected void handle() {

    }
}
