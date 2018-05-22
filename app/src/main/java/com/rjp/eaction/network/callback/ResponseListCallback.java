package com.rjp.eaction.network.callback;

import java.util.List;

/**
 * author : Gimpo create on 2018/5/15 16:54
 * email  : jimbo922@163.com
 */
public interface ResponseListCallback<T> {
    //回调T类型的
    void success(List<T> models);

    //失败回调
    void failure(int code, String msg);
}
