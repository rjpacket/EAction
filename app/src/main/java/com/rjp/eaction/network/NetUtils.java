package com.rjp.eaction.network;

import android.content.Context;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.rjp.eaction.network.callback.FailureCallback;
import com.rjp.eaction.network.callback.SuccessCallback;
import com.rjp.eaction.network.callback.SuccessListCallback;
import com.rjp.eaction.network.exception.ExceptionHandle;
import com.rjp.eaction.network.model.BaseModel;
import com.rjp.eaction.network.model.Code;
import com.rjp.eaction.network.observer.CustomObserver;
import com.rjp.eaction.network.retrofit.RetrofitClient;

import java.util.List;
import java.util.Map;

import okhttp3.ResponseBody;

/**
 * 网络请求封装库
 * author : Gimpo create on 2018/5/11 19:12
 * email  : jimbo922@163.com
 */
public class NetUtils {
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

    /**
     * 获取object类型的数据
     *
     * @param context
     * @param url
     * @param params
     * @param isShowLoading
     * @param successCallback
     * @param failureCallback
     * @param <T>
     */
    public <T> void object(Context context, String url, Map<String, String> params, boolean isShowLoading, final SuccessCallback<T> successCallback, final FailureCallback failureCallback) {
        RetrofitClient.getInstance(context).post(url, params, new CustomObserver<ResponseBody>(context, isShowLoading) {
            @Override
            public void onError(ExceptionHandle.ResponeThrowable e) {
                if (failureCallback != null) {
                    failureCallback.failure(Code.NET_ERROR, Code.getErrorMsg(Code.NET_ERROR));
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
                        if (successCallback != null) {
                            successCallback.success(t);
                        }
                    } else {
                        if (failureCallback != null) {
                            failureCallback.failure(baseModel.getCode(), baseModel.getMsg());
                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    /**
     * 获取列表的数据
     *
     * @param context
     * @param url
     * @param params
     * @param isShowLoading
     * @param successCallback
     * @param failureCallback
     * @param <T>
     */
    public <T> void list(Context context, String url, Map<String, String> params, boolean isShowLoading, final SuccessListCallback<T> successCallback, final FailureCallback failureCallback) {
        RetrofitClient.getInstance(context).post(url, params, new CustomObserver<ResponseBody>(context, isShowLoading) {
            @Override
            public void onError(ExceptionHandle.ResponeThrowable e) {
                if (failureCallback != null) {
                    failureCallback.failure(Code.NET_ERROR, Code.getErrorMsg(Code.NET_ERROR));
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
                        if (successCallback != null) {
                            successCallback.success(t);
                        }
                    } else {
                        if (failureCallback != null) {
                            failureCallback.failure(baseModel.getCode(), baseModel.getMsg());
                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
