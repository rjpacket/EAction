package com.rjp.eaction.ui.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.rjp.eaction.R;
import com.rjp.eaction.baseAF.BaseFragment;
import com.rjp.eaction.ui.listviews.OpenPrizeListView;

import butterknife.BindView;

/**
 * A simple {@link Fragment} subclass.
 */
public class OpenPrizeFragment extends BaseFragment {
    @BindView(R.id.open_prize_list_view)
    OpenPrizeListView openPrizeListView;

    public OpenPrizeFragment() {
        // Required empty public constructor
    }

    @Override
    protected void networkReload() {

    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_open_prize;
    }

    @Override
    protected String getPageTitle() {
        return "开奖记录";
    }

    @Override
    protected void handle() {
        openPrizeListView.requestData();
    }
}
