package com.rjp.eaction;

import android.app.Activity;
import android.app.Dialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.rjp.eaction.baseAF.NetworkView;
import com.rjp.eaction.baseAF.ReloadListener;
import com.rjp.eaction.network.NetUtils;
import com.rjp.eaction.network.callback.ResponseCallback;

public class MainActivity extends Activity {

    private Dialog dialog;
    private NetworkView networkView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.tv_hello).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                net();
            }
        });

        networkView = findViewById(R.id.nv_network_view);
        networkView.setReloadListener(new ReloadListener() {
            @Override
            public void reload() {
                net();
            }
        });
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

                    }

                    @Override
                    public void failure(int code, String msg) {
                        networkView.setNetworkCode(code);
                        Toast.makeText(MainActivity.this, msg, Toast.LENGTH_SHORT).show();
                    }
                });
    }
}
