package com.rjp.eaction.ui.activitys;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import com.rjp.eaction.R;
import com.rjp.eaction.base.BaseActivity;
import com.rjp.eaction.bean.HomeCategoryModel;
import com.rjp.eaction.network.NetUtils;
import com.rjp.eaction.network.callback.ResponseCallback;
import com.rjp.eaction.util.LogUtils;
import com.rjp.eaction.utils.SPUtils;

import java.util.List;

import static com.rjp.eaction.network.UrlConst.URL_HOME_CATEGORY;
import static com.rjp.eaction.network.UrlConst.URL_USER_ADD_ADDRESS;

public class AddAddressActivity extends BaseActivity {

    @BindView(R.id.et_username)
    EditText etUsername;
    @BindView(R.id.et_phone)
    EditText etPhone;
    @BindView(R.id.et_detail_address)
    EditText etDetailAddress;

    public static void trendTo(Context mContext) {
        mContext.startActivity(new Intent(mContext, AddAddressActivity.class));
    }

    @Override
    protected void handle() {

    }

    @Override
    protected void networkReload() {

    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_add_address;
    }

    @Override
    protected String getPageTitle() {
        return "新增地址";
    }

    @OnClick({R.id.ll_select_area, R.id.ll_set_default_address, R.id.tv_add_address})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ll_select_area:
                break;
            case R.id.ll_set_default_address:
                break;
            case R.id.tv_add_address:
                addAddress();
                break;
        }
    }

    private void addAddress() {
        new NetUtils.Builder()
                .url(URL_USER_ADD_ADDRESS)
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
