package com.rjp.eaction.ui.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.rjp.eaction.R;
import com.rjp.eaction.baseAF.BaseFragment;
import com.rjp.eaction.ui.listviews.JCZQOddsListView;

import butterknife.BindView;

/**
 * A simple {@link Fragment} subclass.
 */
public class JCZQOddsFragment extends BaseFragment {
    @BindView(R.id.jczq_odds_list_view)
    JCZQOddsListView jczqOddsListView;

    public JCZQOddsFragment() {
        // Required empty public constructor
    }

    @Override
    protected void networkReload() {

    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_jczqodds;
    }

    @Override
    protected String getPageTitle() {
        return "竞足赔率分析";
    }

    @Override
    protected void handle() {
        jczqOddsListView.requestData();
    }

}
