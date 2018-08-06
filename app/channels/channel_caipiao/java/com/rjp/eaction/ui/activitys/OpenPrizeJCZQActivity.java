package com.rjp.eaction.ui.activitys;

import android.content.Context;
import android.content.Intent;

import com.rjp.eaction.R;
import com.rjp.eaction.baseAF.BaseActivity;
import com.rjp.eaction.ui.listviews.OpenPrizeJCZQListView;

import butterknife.BindView;

public class OpenPrizeJCZQActivity extends BaseActivity {

    @BindView(R.id.jczq_open_prize_list_view)
    OpenPrizeJCZQListView openPrizeJCZQListView;

    public static void trendTo(Context context) {
        Intent intent = new Intent(context, OpenPrizeJCZQActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void networkReload() {

    }

    @Override
    protected String getPageTitle() {
        return "足球历史对阵";
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_jczqopen_prize;
    }

    @Override
    protected void handle() {
        openPrizeJCZQListView.requestData();
    }
}
