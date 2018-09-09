package com.rjp.eaction.ui.activitys;

import android.content.Context;
import android.content.Intent;
import com.rjp.eaction.R;
import com.rjp.eaction.base.BaseActivity;
import com.rjp.eaction.network.NetUtils;
import com.rjp.eaction.network.callback.ResponseCallback;
import com.rjp.eaction.utils.SPUtils;

import static com.rjp.eaction.network.UrlConst.URL_MINE_HAD_READ;
import static com.rjp.eaction.network.UrlConst.URL_MINE_READ_TIME;

public class ReadTimeActivity extends BaseActivity {

    public static void trendTo(Context mContext){
        mContext.startActivity(new Intent(mContext, ReadTimeActivity.class));
    }

    @Override
    protected String getPageTitle() {
        return "阅读时长";
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_read_time;
    }

    @Override
    protected void networkReload() {

    }

    @Override
    protected void handle() {
        getReadTime();
    }

    private void getReadTime() {
        new NetUtils.Builder()
                .url(URL_MINE_READ_TIME)
                .param("token", SPUtils.getInstance(mContext).getString(SPUtils.USER_TOKEN))
                .context(mContext)
                .build()
                .model(new ResponseCallback<String>() {
                    @Override
                    public void success(String models) {

                    }

                    @Override
                    public void failure(String code, String msg) {

                    }
                });
    }

}
