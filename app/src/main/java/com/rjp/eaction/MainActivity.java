package com.rjp.eaction;

import android.app.Activity;
import android.app.Dialog;
import android.os.Bundle;
import android.view.View;

import com.rjp.eaction.dialog.DialogUtils;

public class MainActivity extends Activity {

    private Dialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        new NetUtils.Builder()
//                .context(this)
//                .url("v2/movie/top250")
//                .param("start", "0")
//                .param("count", "10")
//                .showLoading(true)
//                .tag("main")
//                .Build()
//                .model(new ResponseCallback<String>() {
//                    @Override
//                    public void success(String model) {
//
//                    }
//
//                    @Override
//                    public void failure(int code, String msg) {
//
//                    }
//                });

        findViewById(R.id.tv_hello).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new DialogUtils.Builder()
                        .context(MainActivity.this)
                        .title("更新")
                        .notice("是否更新到最新版本？")
                        .onClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {

                            }
                        })
                        .build()
                        .show();
            }
        });
    }
}
