package com.rjp.eaction;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;

import com.rjp.eaction.network.NetUtils;
import com.rjp.eaction.network.callback.FailureCallback;
import com.rjp.eaction.network.callback.SuccessListCallback;

import java.util.HashMap;
import java.util.List;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        HashMap<String, String> params = new HashMap<>();
        params.put("start", "0");
        params.put("count", "10");
        NetUtils.getInstance().list(this, "v2/movie/top250", params, true, new SuccessListCallback<String>() {
            @Override
            public void success(List<String> models) {
                for (String model : models) {
                    Log.d("---------->", model);
                }
            }
        }, new FailureCallback() {
            @Override
            public void failure(int code, String msg) {

            }
        });
    }
}
