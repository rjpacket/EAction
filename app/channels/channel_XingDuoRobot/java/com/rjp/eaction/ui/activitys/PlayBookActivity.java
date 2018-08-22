package com.rjp.eaction.ui.activitys;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.widget.LinearLayout;

import com.rjp.eaction.R;
import com.rjp.eaction.baseAF.BaseActivity;
import com.rjp.eaction.ui.fragments.BookFragment;
import com.rjp.eaction.ui.fragments.HomeFragment;
import com.rjp.eaction.ui.fragments.MineFragment;
import com.rjp.eaction.ui.fragments.StoreFragment;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.OnClick;

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
