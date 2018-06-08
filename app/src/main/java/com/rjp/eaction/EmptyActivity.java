package com.rjp.eaction;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.rjp.eaction.function.SelectPhotoPoupWindow;
import com.rjp.eaction.util.AppUtils;

public class EmptyActivity extends Activity {

    private SelectPhotoPoupWindow poupWindow;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_empty);

        TextView tvAppId = (TextView) findViewById(R.id.tv_appid);
        tvAppId.setText(BuildConfig.APPLICATION_ID);

        TextView appName = (TextView) findViewById(R.id.tv_appname);
        appName.setText("AppName:" + getResources().getString(R.string.app_name));

        poupWindow = new SelectPhotoPoupWindow(EmptyActivity.this, -1);
        findViewById(R.id.tv_detail).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AppUtils.getMacAddress(EmptyActivity.this);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        poupWindow.onActivityResult(requestCode, resultCode, data);
    }
}
