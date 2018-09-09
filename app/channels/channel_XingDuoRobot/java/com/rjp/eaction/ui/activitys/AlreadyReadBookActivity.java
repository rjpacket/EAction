package com.rjp.eaction.ui.activitys;

import android.content.Context;
import android.content.Intent;
import com.rjp.eaction.R;
import com.rjp.eaction.base.BaseActivity;
import com.rjp.eaction.bean.HomeCategoryModel;
import com.rjp.eaction.network.NetUtils;
import com.rjp.eaction.network.callback.ResponseCallback;
import com.rjp.eaction.util.LogUtils;
import com.rjp.eaction.utils.SPUtils;

import java.util.List;

import static com.rjp.eaction.network.UrlConst.URL_HOME_CATEGORY;
import static com.rjp.eaction.network.UrlConst.URL_MINE_HAD_READ;

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
        getReadBookCount();
    }

    private void getReadBookCount() {
        new NetUtils.Builder()
                .url(URL_MINE_HAD_READ)
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
