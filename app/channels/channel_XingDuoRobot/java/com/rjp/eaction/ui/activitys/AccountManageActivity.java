package com.rjp.eaction.ui.activitys;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import com.rjp.eaction.R;
import com.rjp.eaction.base.BaseActivity;
import com.rjp.eaction.function.SelectPhotoPoupWindow;
import com.rjp.eaction.views.other.CircleImageView;

public class AccountManageActivity extends BaseActivity implements SelectPhotoPoupWindow.OnSelectPhotoListener {

    @BindView(R.id.iv_user_head_image)
    CircleImageView ivUserHeadImage;
    @BindView(R.id.tv_user_wechat)
    TextView tvUserWechat;
    @BindView(R.id.tv_user_phone)
    TextView tvUserPhone;
    @BindView(R.id.tv_user_age)
    TextView tvUserAge;
    @BindView(R.id.tv_user_sex)
    TextView tvUserSex;
    @BindView(R.id.tv_user_location)
    TextView tvUserLocation;
    private SelectPhotoPoupWindow selectPhotoPoupWindow;

    public static void trendTo(Context mContext) {
        mContext.startActivity(new Intent(mContext, AccountManageActivity.class));
    }

    @Override
    protected void handle() {

    }

    @Override
    protected void networkReload() {

    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_account_manage;
    }

    @Override
    protected String getPageTitle() {
        return "账号管理";
    }

    @OnClick({R.id.ll_user_modify_password, R.id.ll_user_head, R.id.ll_user_wechat, R.id.ll_user_phone, R.id.ll_user_age, R.id.ll_user_sex, R.id.ll_user_location})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ll_user_head:
                if(selectPhotoPoupWindow == null) {
                    selectPhotoPoupWindow = new SelectPhotoPoupWindow(this, SelectPhotoPoupWindow.NO_LAYOUT);
                    selectPhotoPoupWindow.setOnSelectPhotoListener(this);
                }
                selectPhotoPoupWindow.show(getWindow().getDecorView(), Gravity.BOTTOM);
                break;
            case R.id.ll_user_wechat:
                BindWechatActivity.trendTo(mContext);
                break;
            case R.id.ll_user_phone:
                BindMobileActivity.trendTo(mContext);
                break;
            case R.id.ll_user_age:
                break;
            case R.id.ll_user_sex:
                break;
            case R.id.ll_user_location:
                break;
            case R.id.ll_user_modify_password:
                ModifyPasswordActivity.trendTo(mContext);
                break;
        }
    }

    @Override
    public void onSelectOnePhoto(String filePath) {

    }
}
