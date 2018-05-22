package com.rjp.eaction;

import android.app.Activity;
import android.os.Bundle;

import com.rjp.eaction.network.NetUtils;
import com.rjp.eaction.network.callback.ResponseCallback;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

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

                    }
                });
    }
}
