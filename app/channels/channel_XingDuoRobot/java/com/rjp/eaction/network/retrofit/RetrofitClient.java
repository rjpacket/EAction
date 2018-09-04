package com.rjp.eaction.network.retrofit;

import android.content.Context;
import android.text.TextUtils;
import android.util.Log;

import com.alibaba.fastjson.JSONObject;
import com.rjp.eaction.network.interceptor.AddCookiesInterceptor;
import com.rjp.eaction.network.interceptor.HeaderInterceptor;
import com.rjp.eaction.network.interceptor.ReceivedCookiesInterceptor;
import com.rjp.eaction.network.interceptor.UrlDecodeInterceptor;

import java.io.File;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import okhttp3.*;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClient {

    private static final int DEFAULT_TIMEOUT = 15;
    private ApiService apiService;
    private OkHttpClient okHttpClient;
    public static String baseUrl = ApiService.BASE_URL;
    private static RetrofitClient sNewInstance;

    private Cache cache = null;
    private File httpCacheDirectory;
    public final HeaderInterceptor headerInterceptor;

    public static RetrofitClient getInstance(Context context) {
        if(sNewInstance == null){
            synchronized (RetrofitClient.class){
                if(sNewInstance == null){
                    sNewInstance = new RetrofitClient(context, baseUrl);
                }
            }
        }
        return sNewInstance;
    }

    /**
     * 初始化client
     * @param url
     */
    public RetrofitClient(Context context, String url) {
        if (TextUtils.isEmpty(url)) {
            url = baseUrl;
        }
        if (httpCacheDirectory == null) {
            httpCacheDirectory = new File(context.getCacheDir(), "eaction_cache");
        }

        try {
            if (cache == null) {
                cache = new Cache(httpCacheDirectory, 10 * 1024 * 1024);
            }
        } catch (Exception e) {
            Log.e("OKHttp", "Could not create http cache", e);
        }

        headerInterceptor = new HeaderInterceptor(context);
        okHttpClient = new OkHttpClient.Builder()
                .addNetworkInterceptor(new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
//                .cookieJar(new NovateCookieManger(context)) /*这里暂时用不到*/
                .cache(cache)
                .addInterceptor(new UrlDecodeInterceptor())
//                .addInterceptor(new ReceivedCookiesInterceptor())
//                .addInterceptor(new AddCookiesInterceptor())
                .addInterceptor(headerInterceptor)
//                .addInterceptor(new CaheInterceptor(context)) /*缓存暂时用不到*/
//                .addNetworkInterceptor(new CaheInterceptor(context)) /*缓存暂时用不到*/
                .connectTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS)
                .writeTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS)
                .connectionPool(new ConnectionPool(8, 15, TimeUnit.SECONDS))
                // 这里你可以根据自己的机型设置同时连接的个数和时间，我这里8个，和每个保持时间为10s
                .build();
        Retrofit retrofit = new Retrofit.Builder()
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .baseUrl(url)
                .build();
        apiService = retrofit.create(ApiService.class);
    }

    /**
     * get请求
     * @param url
     * @param params
     * @param observer
     */
    public void get(String url, Map<String, String> params, Observer<ResponseBody> observer) {
        apiService.get(url, params)
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer);
    }

    /**
     * post请求
     * @param url
     * @param params
     * @param observer
     */
    public void post(String url, JSONObject params, Observer<ResponseBody> observer) {
        apiService.post(url, params)
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer);
    }
}