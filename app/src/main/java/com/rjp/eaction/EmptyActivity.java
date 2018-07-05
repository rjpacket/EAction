package com.rjp.eaction;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.rjp.eaction.util.FileUtils;
import com.rjp.eaction.views.pick_photo.PhotoModel;
import com.rjp.eaction.views.pick_photo.PickPhotoView;

public class EmptyActivity extends Activity {
    private Activity mContext;
    private PickPhotoView pickPhotoView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_empty);

        mContext = this;

        TextView tvAppId = (TextView) findViewById(R.id.tv_appid);
        tvAppId.setText(BuildConfig.APPLICATION_ID);

        TextView appName = (TextView) findViewById(R.id.tv_appname);
        appName.setText("AppName:" + getResources().getString(R.string.app_name));

        pickPhotoView = (PickPhotoView) findViewById(R.id.pick_photo_view);

        findViewById(R.id.tv_detail).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Uri uri = data.getData();
        String filePath = FileUtils.getFilePathByUri(mContext, uri);
        pickPhotoView.addPickPhotoModel(new PhotoModel(filePath));
    }
}
