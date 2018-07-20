package com.rjp.eaction.ui.social;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.text.TextUtils;
import android.widget.EditText;
import android.widget.Toast;

import com.rjp.eaction.R;
import com.rjp.eaction.baseAF.BaseActivity;
import com.rjp.eaction.baseAF.Const;
import com.rjp.eaction.util.dialog.DialogUtils;
import com.rjp.eaction.util.dialog.OnDialogClickListener;
import com.rjp.eaction.network.NetUtils;
import com.rjp.eaction.network.callback.ResponseCallback;
import com.rjp.eaction.util.FileUtils;
import com.rjp.eaction.util.LogUtils;
import com.rjp.eaction.views.pick_photo.PhotoModel;
import com.rjp.eaction.views.pick_photo.PickPhotoView;

import butterknife.BindView;

public class SendSocialActivity extends BaseActivity {

    @BindView(R.id.pick_photo_view)
    PickPhotoView pickPhotoView;
    @BindView(R.id.et_content)
    EditText etContent;

    public static void trendTo(Context mContext){
        Intent intent = new Intent(mContext, SendSocialActivity.class);
        mContext.startActivity(intent);
    }

    @Override
    protected void networkReload() {

    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_send_social;
    }

    @Override
    protected String getPageTitle() {
        return "发布动态";
    }

    @Override
    protected void handle() {
        setIcon0(R.mipmap.icon_write);
    }

    @Override
    protected void clickOnIcon0() {
        String trim = etContent.getText().toString().trim();
        if(TextUtils.isEmpty(trim)){
            Toast.makeText(mContext, "还没有任何内容呢~", Toast.LENGTH_SHORT).show();
        }else{
            new DialogUtils.Builder()
                    .context(mContext)
                    .notice("确定要发布这条动态吗？")
                    .onClickListener(new OnDialogClickListener() {
                        @Override
                        public void onConfirm() {
                            sendTalk();
                        }

                        @Override
                        public void onCancel() {

                        }
                    })
                    .build()
                    .show();
        }
    }

    public void sendTalk(){
        String trim = etContent.getText().toString().trim();
        new NetUtils.Builder()
                .context(mContext)
                .url(Const.URL_SEND_TALK)
                .showLoading(true)
                .param("content", trim)
                .param("imageUrls", "")
                .build()
                .model(new ResponseCallback<String>() {
                    @Override
                    public void success(String model) {
                        LogUtils.e(model);
                    }

                    @Override
                    public void failure(int code, String msg) {

                    }
                });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(data != null) {
            Uri uri = data.getData();
            String filePath = FileUtils.getFilePathByUri(mContext, uri);
            pickPhotoView.addPickPhotoModel(new PhotoModel(PhotoModel.TYPE_FILE, filePath));
        }
    }
}
