package com.rjp.eaction.network.callback;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

/**
 * author : Gimpo create on 2018/5/15 16:54
 * email  : jimbo922@163.com
 */
public abstract class ResponseCallback<T> {
    //成功回调
    public abstract void success(T model);

    //失败回调
    public abstract void failure(int code, String msg);

    protected Type resultType;

    public ResponseCallback() {
        Type genericSuperclass = getClass().getGenericSuperclass();
        if (genericSuperclass instanceof ParameterizedType) {
            this.resultType = ((ParameterizedType) genericSuperclass).getActualTypeArguments()[0];
        } else {
            this.resultType = Object.class;
        }
    }

    public Type getGenericityType() {
        return resultType;
    }
}
