package com.rjp.eaction.network;

import android.content.Context;

import com.google.gson.Gson;
import com.rjp.eaction.network.callback.ResponseCallback;
import com.rjp.eaction.network.exception.ExceptionHandle;
import com.rjp.eaction.network.model.BaseModel;
import com.rjp.eaction.network.observer.CustomObserver;
import com.rjp.eaction.network.retrofit.RetrofitClient;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
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
    private Type modelType;

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

    public static class Builder {
        private Context context;
        private String url;
        private Map<String, String> params = new HashMap<>();
        private boolean isShowLoading = true;  //默认显示加载框
        private String tag = "net";  //默认存一个net

        public Builder() {

        }

        public Builder context(Context context) {
            this.context = context;
            return this;
        }

        public Builder url(String url) {
            this.url = url;
            return this;
        }

        public Builder param(String key, String value) {
            this.params.put(key, value);
            return this;
        }

        public Builder showLoading(boolean isShowLoading) {
            this.isShowLoading = isShowLoading;
            return this;
        }

        public Builder tag(String tag) {
            this.tag = tag;
            return this;
        }

        public NetUtils build() {
            NetUtils netUtils = NetUtils.getInstance();
            if (this.context == null) {
                throw new IllegalArgumentException("context must be not null");
            }
            netUtils.context = this.context;
            if (this.url == null) {
                throw new IllegalArgumentException("url must be not null");
            }
            netUtils.url = this.url;
            netUtils.params = this.params;
            netUtils.isShowLoading = this.isShowLoading;
            netUtils.tag = this.tag;
            return netUtils;
        }
    }

    /**
     * baseurl 改变的请求
     * @param baseUrl
     * @param responseCallback
     * @param <T>
     */
    public <T> void model(String baseUrl, final ResponseCallback<T> responseCallback) {
        new RetrofitClient(context, baseUrl).post(url, params, new CustomObserver<ResponseBody>(context, isShowLoading, tag) {
            @Override
            public void onError(ExceptionHandle.ResponeThrowable e) {
                responseCallback.failure(e.code, e.message);
            }

            @Override
            public void onNext(ResponseBody responseBody) {
                try {
                    String response = responseBody.string();
                    Gson gson = new Gson();
                    Type resultType = wrapType(BaseModel.class, responseCallback.getGenericityType());
                    BaseModel<T> baseModel = gson.fromJson(response, resultType);
                    if (baseModel.isOk()) {
                        responseCallback.success(baseModel.getData());
                    } else {
                        responseCallback.failure(baseModel.getCode(), baseModel.getMsg());
                    }
                } catch (Exception e) {
                    ExceptionHandle.ResponeThrowable exception = ExceptionHandle.handleException(e);
                    responseCallback.failure(exception.code, exception.message);
                }
            }
        });
    }

    /**
     * 核心请求类
     * @param responseCallback
     * @param <T>
     */
    public <T> void model(final ResponseCallback<T> responseCallback) {
        RetrofitClient.getInstance(context).post(url, params, new CustomObserver<ResponseBody>(context, isShowLoading, tag) {
            @Override
            public void onError(ExceptionHandle.ResponeThrowable e) {
                responseCallback.failure(e.code, e.message);
            }

            @Override
            public void onNext(ResponseBody responseBody) {
                try {
                    String response = responseBody.string();
                    Gson gson = new Gson();
                    Type resultType = wrapType(BaseModel.class, responseCallback.getGenericityType());
                    BaseModel<T> baseModel = gson.fromJson(response, resultType);
                    if (baseModel.isOk()) {
                        responseCallback.success(baseModel.getData());
                    } else {
                        responseCallback.failure(baseModel.getCode(), baseModel.getMsg());
                    }
                } catch (Exception e) {
                    ExceptionHandle.ResponeThrowable exception = ExceptionHandle.handleException(e);
                    responseCallback.failure(exception.code, exception.message);
                }
            }
        });
    }

    public static ParameterizedType wrapType(final Class raw,final Type... args){
        return new ParameterizedType() {
            @Override
            public Type[] getActualTypeArguments() {
                return args;
            }

            @Override
            public Type getRawType() {
                return raw;
            }

            @Override
            public Type getOwnerType() {
                return null;
            }
        };
    }

    private static class ListParameterizedType implements ParameterizedType {
        private Type type;
        private ListParameterizedType(Type type) {
            this.type = type;
        }
        @Override
        public Type[] getActualTypeArguments() {
            return new Type[] {type};
        }
        @Override
        public Type getRawType() {
            return ArrayList.class;
        }
        @Override
        public Type getOwnerType() {
            return null;
        }
    }
}
