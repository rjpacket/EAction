package com.rjp.eaction.ui;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.widget.FrameLayout;

import com.rjp.eaction.R;
import com.rjp.eaction.baseAF.BaseActivity;
import com.rjp.eaction.ui.fragments.RecommendFragment;
import com.rjp.eaction.ui.home.HomeFragment;
import com.rjp.eaction.ui.mine.MineFragment;
import com.rjp.eaction.ui.social.SocialFragment;
import com.rjp.navigationview.NavigationView;
import com.rjp.navigationview.TabModel;

import java.util.ArrayList;

import butterknife.BindView;

public class MainActivity extends BaseActivity {

    @BindView(R.id.navigation_view) NavigationView navigationView;
    @BindView(R.id.main_container) FrameLayout frameLayout;

    public static void trendTo(Context mContext) {
        Intent intent = new Intent(mContext, MainActivity.class);
        mContext.startActivity(intent);
    }

    @Override
    protected void networkReload() {

    }

    @Override
    protected boolean showTopBar() {
        return false;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected void handle() {
        navigationView.setContainerId(R.id.main_container);
        navigationView.setFragmentManager(getSupportFragmentManager());
        ArrayList<Fragment> fragments = new ArrayList<>();
        fragments.add(new HomeFragment());
        fragments.add(new RecommendFragment());
        fragments.add(new SocialFragment());
        fragments.add(new SocialFragment());
        fragments.add(new MineFragment());
        navigationView.setFragments(fragments);
        ArrayList<TabModel> tabModels = new ArrayList<>();
        tabModels.add(new TabModel("1", R.drawable.selector_home_tab_1));
        tabModels.add(new TabModel("推荐", R.drawable.selector_home_tab_2));
        tabModels.add(new TabModel("3", R.drawable.selector_home_tab_3));
        tabModels.add(new TabModel("4", R.drawable.selector_home_tab_2));
        tabModels.add(new TabModel("5", R.drawable.selector_home_tab_1));
        navigationView.setTabs(tabModels);
    }
}
