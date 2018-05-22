package com.rjp.eaction.network.callback;

/**
 * author : Gimpo create on 2018/5/15 16:54
 * email  : jimbo922@163.com
 */
public interface SuccessCallback<T> {
    //回调T类型的
    void success(T model);
}
