package com.rjp.eaction.network.interceptor;

import com.rjp.eaction.util.LogUtils;

import java.io.IOException;
import java.net.URLDecoder;

import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * 添加头部拦截器  添加通用Header
 */
public class UrlDecodeInterceptor implements Interceptor {


    public UrlDecodeInterceptor() {

    }

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request.Builder builder = chain.request().newBuilder();
        HttpUrl url = chain.request().url();
        String decodeUrl = URLDecoder.decode(url.toString(), "utf-8");
        LogUtils.e(decodeUrl);
        builder.url(decodeUrl);
        return chain.proceed(builder.build());
    }
}