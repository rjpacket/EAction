package com.rjp.eaction.ui.activitys;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.rjp.eaction.R;
import com.rjp.eaction.baseAF.BaseActivity;
import com.rjp.eaction.ui.listviews.OpenPrizeJCZQListView;
import com.rjp.eaction.ui.listviews.OpenPrizeSFGGListView;

import butterknife.BindView;

public class OpenPrizeSFGGActivity extends BaseActivity {

    @BindView(R.id.sfgg_open_prize_list_view)
    OpenPrizeSFGGListView openPrizeSFGGListView;

    public static void trendTo(Context context) {
        Intent intent = new Intent(context, OpenPrizeSFGGActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void networkReload() {

    }

    @Override
    protected String getPageTitle() {
        return "胜负过关记录";
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_open_prize_sfgg;
    }

    @Override
    protected void handle() {
        openPrizeSFGGListView.requestData();
    }
}
