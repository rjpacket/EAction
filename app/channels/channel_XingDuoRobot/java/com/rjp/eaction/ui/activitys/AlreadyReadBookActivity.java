package com.rjp.eaction.ui.activitys;

import android.content.Context;
import android.content.Intent;
import com.rjp.eaction.R;
import com.rjp.eaction.base.BaseActivity;

public class AlreadyReadBookActivity extends BaseActivity {

    public static void trendTo(Context mContext){
        mContext.startActivity(new Intent(mContext, AlreadyReadBookActivity.class));
    }

    @Override
    protected String getPageTitle() {
        return "已读书籍";
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_already_read_book;
    }

    @Override
    protected void networkReload() {

    }

    @Override
    protected void handle() {

    }

}
