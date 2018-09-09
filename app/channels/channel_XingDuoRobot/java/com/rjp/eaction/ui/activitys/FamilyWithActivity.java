package com.rjp.eaction.ui.activitys;

import android.content.Context;
import android.content.Intent;
import com.rjp.eaction.R;
import com.rjp.eaction.base.BaseActivity;
import com.rjp.eaction.network.NetUtils;
import com.rjp.eaction.network.callback.ResponseCallback;
import com.rjp.eaction.utils.SPUtils;

import static com.rjp.eaction.network.UrlConst.URL_MINE_FAMILY_WITH;
import static com.rjp.eaction.network.UrlConst.URL_MINE_HAD_READ;

public class FamilyWithActivity extends BaseActivity {

    public static void trendTo(Context mContext){
        mContext.startActivity(new Intent(mContext, FamilyWithActivity.class));
    }

    @Override
    protected String getPageTitle() {
        return "亲子陪伴";
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_family_with;
    }

    @Override
    protected void networkReload() {

    }

    @Override
    protected void handle() {
        getFamilyWith();
    }

    private void getFamilyWith() {
        new NetUtils.Builder()
                .url(URL_MINE_FAMILY_WITH)
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
