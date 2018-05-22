package com.rjp.eaction.network.callback;

/**
 * author : Gimpo create on 2018/5/15 16:54
 * email  : jimbo922@163.com
 */
public interface ResponseCallback<T> {
    //成功回调
    void success(T model);

    //失败回调
    void failure(int code, String msg);
}
