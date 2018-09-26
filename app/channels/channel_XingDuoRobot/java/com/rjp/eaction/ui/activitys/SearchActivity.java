package com.rjp.eaction.ui.activitys;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import butterknife.BindView;
import com.rjp.eaction.R;
import com.rjp.eaction.base.BaseActivity;
import com.rjp.eaction.util.AppUtils;

import static android.graphics.Color.WHITE;

public class SearchActivity extends BaseActivity {

    @BindView(R.id.ll_root_view)
    LinearLayout llRootView;

    public static void trendTo(Context mContext){
        mContext.startActivity(new Intent(mContext, SearchActivity.class));
    }

    @Override
    protected boolean showTopBar() {
        return false;
    }

    @Override
    protected String getPageTitle() {
        return "搜索页面";
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_search;
    }

    @Override
    protected void networkReload() {

    }

    @Override
    protected void handle() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, AppUtils.getStatusBarHeight(mContext));
            View view = new View(mContext);
            view.setBackgroundColor(WHITE);
            view.setLayoutParams(params);
            llRootView.addView(view, 0);
        }
    }

}
