package com.rjp.eaction.ui.mine;

import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;

import com.rjp.eaction.R;
import com.rjp.eaction.base.BaseActivity;
import com.rjp.eaction.baseAF.Const;
import com.rjp.eaction.network.NetUtils;
import com.rjp.eaction.network.callback.ResponseCallback;
import com.rjp.eaction.util.ToastUtils;

public class PersonalInfoActivity extends BaseActivity {

    public static void trendTo(Context mContext){
        Intent intent = new Intent(mContext, PersonalInfoActivity.class);
        mContext.startActivity(intent);
    }

    @Override
    protected void networkReload() {

    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_personal_info;
    }

    @Override
    protected String getPageTitle() {
        return "个人信息";
    }

    @Override
    protected void handle() {
        new NetUtils.Builder()
                .context(mContext)
                .url(Const.URL_UPDATE_USER_INFO)
                .showLoading(true)
                .param("username", "rjp")
                .param("sex", "1")
                .param("signature", "android coder")
                .param("password", "123456")
                .build()
                .model(new ResponseCallback<Object>() {
                    @Override
                    public void success(Object model) {
                        ToastUtils.showToast(mContext, "获取个人信息成功");
                    }

                    @Override
                    public void failure(String code, String msg) {
                        ToastUtils.showToast(mContext, TextUtils.isEmpty(msg) ? "获取个人信息失败" : msg);
                    }
                });
    }
}
