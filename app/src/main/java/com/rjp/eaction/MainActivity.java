package com.rjp.eaction;

import android.app.Dialog;
import android.view.View;
import android.widget.Toast;

import com.rjp.eaction.baseAF.BaseActivity;
import com.rjp.eaction.network.NetUtils;
import com.rjp.eaction.network.callback.ResponseCallback;

public class MainActivity extends BaseActivity {

    @Override
    protected void handle() {
        findViewById(R.id.tv_hello).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                net();
            }
        });
    }

    @Override
    protected void networkReload() {
        net();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected String getPageTitle() {
        return "详情页";
    }

    private void net() {
        new NetUtils.Builder()
                .context(this)
                .url("v2/movie/top250")
                .param("start", "0")
                .param("count", "10")
                .showLoading(true)
                .tag("main")
                .Build()
                .model(new ResponseCallback<String>() {
                    @Override
                    public void success(String model) {
                        setNetworkSuccess();
                    }

                    @Override
                    public void failure(int code, String msg) {
                        setNetworkFail(msg);
                        Toast.makeText(MainActivity.this, msg, Toast.LENGTH_SHORT).show();
                    }
                });
    }
}
