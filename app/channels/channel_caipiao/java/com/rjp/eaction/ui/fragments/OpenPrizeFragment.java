package com.rjp.eaction.ui.fragments;


import android.support.v4.app.Fragment;
import android.view.View;

import com.rjp.eaction.R;
import com.rjp.eaction.baseAF.BaseFragment;
import com.rjp.eaction.ui.activitys.OpenPrizeJCLQActivity;
import com.rjp.eaction.ui.activitys.OpenPrizeJCZQActivity;
import com.rjp.eaction.ui.activitys.OpenPrizeSFGGActivity;
import com.rjp.eaction.ui.listviews.OpenPrizeListView;

import butterknife.BindView;
import butterknife.OnClick;

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

    @OnClick({R.id.ll_jczq_open_prize, R.id.ll_jclq_open_prize, R.id.ll_zqdc_open_prize})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ll_jczq_open_prize:
                OpenPrizeJCZQActivity.trendTo(mContext);
                break;
            case R.id.ll_jclq_open_prize:
                OpenPrizeJCLQActivity.trendTo(mContext);
                break;
            case R.id.ll_zqdc_open_prize:
                OpenPrizeSFGGActivity.trendTo(mContext);
                break;
        }
    }
}
