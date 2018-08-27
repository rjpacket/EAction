package com.rjp.eaction.ui.activitys;

import com.rjp.eaction.R;
import com.rjp.eaction.base.BaseActivity;

public class BookDetailActivity extends BaseActivity {

    @Override
    protected boolean showTopBar() {
        return false;
    }

    @Override
    protected void handle() {

    }

    @Override
    protected void networkReload() {

    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_book_detail;
    }

    @Override
    protected String getPageTitle() {
        return "书架详情";
    }

}
