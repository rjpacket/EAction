package com.rjp.eaction.ui.activitys;

import android.content.Context;
import android.content.Intent;

import com.rjp.eaction.R;
import com.rjp.eaction.baseAF.BaseActivity;
import com.rjp.eaction.ui.listviews.OpenPrizeJCLQListView;

import butterknife.BindView;

public class OpenPrizeJCLQActivity extends BaseActivity {
    @BindView(R.id.jclq_open_prize_list_view)
    OpenPrizeJCLQListView openPrizeJCLQListView;

    public static void trendTo(Context context) {
        Intent intent = new Intent(context, OpenPrizeJCLQActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void networkReload() {

    }

    @Override
    protected String getPageTitle() {
        return "篮球近期对阵";
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_jclqopen_prize;
    }

    @Override
    protected void handle() {
        openPrizeJCLQListView.requestData();
    }
}
