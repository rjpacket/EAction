package com.rjp.eaction.ui.activitys;

import com.rjp.eaction.R;
import com.rjp.eaction.baseAF.BaseActivity;

public class WifiLinkActivity extends BaseActivity {

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
        return R.layout.activity_wifi_link;
    }

    @Override
    protected String getPageTitle() {
        return "WIFI配网";
    }

}
