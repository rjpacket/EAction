package com.rjp.eaction.ui.activitys;

import android.content.Context;
import android.content.Intent;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import butterknife.BindView;
import com.rjp.eaction.R;
import com.rjp.eaction.base.BaseActivity;
import com.rjp.eaction.util.AppUtils;

public class PlayBookActivity extends BaseActivity {
    @BindView(R.id.iv_play_back)
    ImageView ivPlayBack;

    public static void trendTo(Context mContext){
        mContext.startActivity(new Intent(mContext, PlayBookActivity.class));
    }

    @Override
    protected boolean showTopBar() {
        return false;
    }

    @Override
    protected void handle() {
        RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams) ivPlayBack.getLayoutParams();
        params.setMargins(0, AppUtils.getStatusBarHeight(mContext), 0, 0);
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
