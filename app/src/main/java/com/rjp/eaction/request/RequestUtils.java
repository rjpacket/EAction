package com.rjp.eaction.request;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.widget.Toast;

import com.alibaba.fastjson.JSONObject;

import java.io.IOException;
import java.util.Set;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * author：RJP on 2017/4/20 20:35
 * <p>
 * 请求统一接口
 * 默认不显示加载框
 */

public class RequestUtils {
    public static boolean DEBUG = false;
    private static String DOMAIN = DEBUG ? "https://test.dajiang365.com/" : "https://filter.dajiang365.com/";  //测试线 外网：118.26.65.150 | 内网：192.168.2.237  正式线  https://filter.dajiang365.com/
    public static final String URL_LOT_SERVER_API = DOMAIN + "lotserver/api/request";
    public static final String URL_EURASIAN_API = DOMAIN + "sports/api/v1/"; // (新的欧亚析接口)
    private final OkHttpClient okHttpClient;
    private final Handler handler;

    private RequestUtils() {
        okHttpClient = new OkHttpClient();
        handler = new Handler(Looper.getMainLooper());
    }

    private static RequestUtils mInstance = new RequestUtils();

    public static RequestUtils getInstance() {
        return mInstance;
    }

    public void get(final Context mContext, String url, final NetSuccessCallback successCallback){
        Request request = new Request.Builder().url(url).build();
        Call call = okHttpClient.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(mContext, "网络出问题啦~", Toast.LENGTH_SHORT).show();
                    }
                });
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                final String result = response.body().string();
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        if(successCallback != null){
                            successCallback.onSuccess(result);
                        }
                    }
                });
            }
        });
    }

    public void post(final Context mContext, String url, JSONObject jsonObject, final NetSuccessCallback successCallback){
        FormBody.Builder builder = new FormBody.Builder();
        Set<String> keySets = jsonObject.keySet();
        for (String keySet : keySets) {
            builder.add(keySet, jsonObject.getString(keySet));
        }
        FormBody formBody = builder.build();

        Request request = new Request.Builder()
                .url(url)
                .post(formBody)
                .build();

        Call call = okHttpClient.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(mContext, "网络出问题啦~", Toast.LENGTH_SHORT).show();
                    }
                });
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                final String result = response.body().string();
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        if(successCallback != null){
                            successCallback.onSuccess(result);
                        }
                    }
                });
            }
        });
    }
}
