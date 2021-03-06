package com.rjp.eaction.network;

import android.content.Context;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.rjp.eaction.network.callback.ResponseCallback;
import com.rjp.eaction.network.callback.ResponseListCallback;
import com.rjp.eaction.network.exception.ExceptionHandle;
import com.rjp.eaction.network.model.BaseModel;
import com.rjp.eaction.network.observer.CustomObserver;
import com.rjp.eaction.network.retrofit.RetrofitClient;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import okhttp3.ResponseBody;

/**
 * 构建者 封装 网络请求封装库
 * author : Gimpo create on 2018/5/11 19:12
 * email  : jimbo922@163.com
 */
public class NetUtils {
    private Context context;
    private String url;
    private Map<String, String> params = new HashMap<>();
    private boolean isShowLoading;
    private String tag;

    private static NetUtils ourInstance = null;

    public static NetUtils getInstance() {
        if (ourInstance == null) {
            synchronized (NetUtils.class) {
                if (ourInstance == null) {
                    ourInstance = new NetUtils();
                }
            }
        }
        return ourInstance;
    }

    private NetUtils() {

    }

    public static class Builder{
        private Context context;
        private String url;
        private Map<String, String> params = new HashMap<>();
        private boolean isShowLoading = true;  //默认显示加载框
        private String tag = "net";  //默认存一个net

        public Builder(){

        }

        public Builder context(Context context){
            this.context = context;
            return this;
        }

        public Builder url(String url){
            this.url = url;
            return this;
        }

        public Builder param(String key, String value){
            this.params.put(key, value);
            return this;
        }

        public Builder showLoading(boolean isShowLoading){
            this.isShowLoading = isShowLoading;
            return this;
        }

        public Builder tag(String tag){
            this.tag = tag;
            return this;
        }

        public NetUtils build(){
            NetUtils netUtils = NetUtils.getInstance();
            if(this.context == null){
                throw new IllegalArgumentException("context must be not null");
            }
            netUtils.context = this.context;
            if(this.url == null){
                throw new IllegalArgumentException("url must be not null");
            }
            netUtils.url = this.url;
            netUtils.params = this.params;
            netUtils.isShowLoading = this.isShowLoading;
            netUtils.tag = this.tag;
            return netUtils;
        }
    }

    public <T> void model(final ResponseCallback<T> responseCallback){
        RetrofitClient.getInstance(context).post(url, params, new CustomObserver<ResponseBody>(context, isShowLoading, tag) {
            @Override
            public void onError(ExceptionHandle.ResponeThrowable e) {
                if (responseCallback != null) {
                    responseCallback.failure(e.code, e.message);
                }
            }

            @Override
            public void onNext(ResponseBody responseBody) {
                try {
                    String response = responseBody.string();
                    Gson gson = new Gson();
                    BaseModel baseModel = gson.fromJson(response, BaseModel.class);
                    if (baseModel.isOk()) {
                        T t = gson.fromJson(baseModel.getData().toString(), new TypeToken<T>() {
                        }.getType());
                        if (responseCallback != null) {
                            responseCallback.success(t);
                        }
                    } else {
                        if (responseCallback != null) {
                            responseCallback.failure(baseModel.getCode(), baseModel.getMsg());
                        }
                    }
                } catch (Exception e) {
                    ExceptionHandle.ResponeThrowable exception = ExceptionHandle.handleException(e);
                    if (responseCallback != null) {
                        responseCallback.failure(exception.code, exception.message);
                    }
                }
            }
        });
    }

    public <T> void models(final ResponseListCallback<T> responseCallback){
        RetrofitClient.getInstance(context).post(url, params, new CustomObserver<ResponseBody>(context, isShowLoading, tag) {
            @Override
            public void onError(ExceptionHandle.ResponeThrowable e) {
                if (responseCallback != null) {
                    responseCallback.failure(e.code, e.message);
                }
            }

            @Override
            public void onNext(ResponseBody responseBody) {
                try {
                    String response = responseBody.string();
                    Gson gson = new Gson();
                    BaseModel baseModel = gson.fromJson(response, BaseModel.class);
                    if (baseModel.isOk()) {
                        List<T> t = gson.fromJson(baseModel.getData().toString(), new TypeToken<List<T>>() {
                        }.getType());
                        if (responseCallback != null) {
                            responseCallback.success(t);
                        }
                    } else {
                        if (responseCallback != null) {
                            responseCallback.failure(baseModel.getCode(), baseModel.getMsg());
                        }
                    }
                } catch (Exception e) {
                    ExceptionHandle.ResponeThrowable exception = ExceptionHandle.handleException(e);
                    if (responseCallback != null) {
                        responseCallback.failure(exception.code, exception.message);
                    }
                }
            }
        });
    }
}
