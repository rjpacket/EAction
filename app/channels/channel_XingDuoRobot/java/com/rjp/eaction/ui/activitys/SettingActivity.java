package com.rjp.eaction.ui.activitys;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import com.rjp.eaction.R;
import com.rjp.eaction.base.BaseActivity;
import com.rjp.eaction.bean.UserLoginModel;
import com.rjp.eaction.network.NetUtils;
import com.rjp.eaction.network.callback.ResponseCallback;
import com.rjp.eaction.utils.SPUtils;

import static com.rjp.eaction.network.UrlConst.URL_LOGOUT;

public class SettingActivity extends BaseActivity {

    @BindView(R.id.iv_setting_notice)
    ImageView ivSettingNotice;
    @BindView(R.id.tv_cache_size)
    TextView tvCacheSize;
    @BindView(R.id.tv_current_version)
    TextView tvCurrentVersion;
    @BindView(R.id.tv_logout)
    TextView tvLogout;

    public static void trendTo(Context mContext) {
        mContext.startActivity(new Intent(mContext, SettingActivity.class));
    }

    @Override
    protected String getPageTitle() {
        return "设置";
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_setting;
    }

    @Override
    protected void networkReload() {

    }

    @Override
    protected void handle() {
        tvLogout.setVisibility(SPUtils.getInstance(mContext).getIsLogin() ? View.VISIBLE : View.GONE);
    }

    @OnClick({R.id.iv_setting_notice, R.id.ll_address_manage, R.id.ll_clear_cache, R.id.tv_check_update, R.id.tv_logout})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_setting_notice:
                ivSettingNotice.setSelected(!ivSettingNotice.isSelected());
                break;
            case R.id.ll_clear_cache:
                break;
            case R.id.tv_check_update:
                break;
            case R.id.tv_logout:
                logout();
                break;
            case R.id.ll_address_manage:
                AddressManageActivity.trendTo(mContext);
                break;
        }
    }

    /**
     * 退出登录接口
     */
    private void logout() {
        new NetUtils.Builder()
                .context(mContext)
                .url(URL_LOGOUT)
                .param("token", SPUtils.getInstance(mContext).getString(SPUtils.USER_TOKEN))
                .build()
                .model(new ResponseCallback<UserLoginModel>() {
                    @Override
                    public void success(UserLoginModel model) {
                        LoginActivity.trendTo(mContext);
                        SPUtils.getInstance(mContext).remove(SPUtils.USER_TOKEN);
                        finish();
                    }

                    @Override
                    public void failure(String code, String msg) {
                        Toast.makeText(mContext, msg, Toast.LENGTH_SHORT).show();
                    }
                });
    }
}
