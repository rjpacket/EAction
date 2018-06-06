package com.rjp.eaction;

import android.Manifest;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.rjp.eaction.permission.PermissionCallback;
import com.rjp.eaction.permission.PermissionUtils;

public class EmptyActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_empty);

        TextView tvAppId = (TextView) findViewById(R.id.tv_appid);
        tvAppId.setText(BuildConfig.APPLICATION_ID);

        TextView appName = (TextView) findViewById(R.id.tv_appname);
        appName.setText("AppName:" + getResources().getString(R.string.app_name));

        findViewById(R.id.tv_detail).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new PermissionUtils.Builder()
                        .context(EmptyActivity.this)
                        .permission(Manifest.permission.CAMERA)
                        .build()
                        .request(new PermissionCallback() {
                            @Override
                            public void allow() {
                                Toast.makeText(EmptyActivity.this, "允许", Toast.LENGTH_SHORT).show();
                            }

                            @Override
                            public void deny() {
                                Toast.makeText(EmptyActivity.this, "拒绝", Toast.LENGTH_SHORT).show();
                            }
                        });


            }
        });
    }
}
