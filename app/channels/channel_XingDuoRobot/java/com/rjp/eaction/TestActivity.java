package com.rjp.eaction;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.rjp.eaction.utils.UpdateService;

import static com.rjp.eaction.utils.UpdateService.DOWNLOAD_URL;

public class TestActivity extends Activity {

    public static void trendTo(Context mContext) {
        Intent intent = new Intent(mContext, TestActivity.class);
        mContext.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);

        findViewById(R.id.btn_update).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String downloadUrl = "http://download.dajiang365.com/app-zywl-agent_guanwang.apk";
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        //启动服务
                        Intent service = new Intent(TestActivity.this,UpdateService.class);
                        service.putExtra(DOWNLOAD_URL, downloadUrl);
                        startService(service);
                    }
                }).start();
            }
        });
    }

}
