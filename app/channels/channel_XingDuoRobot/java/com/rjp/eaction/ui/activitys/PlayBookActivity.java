package com.rjp.eaction.ui.activitys;

import com.rjp.eaction.R;
import com.rjp.eaction.base.BaseActivity;

public class PlayBookActivity extends BaseActivity {

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
        return R.layout.activity_play_book;
    }

    @Override
    protected String getPageTitle() {
        return "播放页面";
    }

}
