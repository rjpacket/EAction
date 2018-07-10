package com.rjp.eaction.network.interceptor;

import android.content.Context;

import com.rjp.eaction.network.Const;
import com.rjp.eaction.util.AppUtils;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * 添加头部拦截器  添加通用Header
 */
public class HeaderInterceptor implements Interceptor {

    private Map<String, String> headers;

    public HeaderInterceptor(Context mContext) {
        this.headers = new HashMap<>();
        headers.put(Const.HEADER_VERSION,               AppUtils.getVersionName());
        headers.put(Const.HEADER_APP_TYPE,              AppUtils.getAppType());
        headers.put(Const.HEADER_VERSION_CODE,          AppUtils.getVersionCode());
        headers.put(Const.HEADER_DEVICE_ID,             AppUtils.getDeviceId(mContext));
        headers.put(Const.HEADER_MODEL,                 AppUtils.getModel());
        headers.put(Const.HEADER_SIGN,                  "");
        headers.put(Const.HEADER_IMEI,                  AppUtils.getIMEI(mContext));
        headers.put(Const.HEADER_APPLICATION_ID,        AppUtils.getAppId());
        headers.put(Const.HEADER_MAC_ADDRESS,           AppUtils.getMacAddress(mContext));
        headers.put(Const.HEADER_CHANNEL,               AppUtils.getChannel(mContext));
        headers.put(Const.HEADER_BRAND,                 AppUtils.getBrand());
        headers.put(Const.HEADER_TIME_STAMP,            AppUtils.getTimeStamp());
        headers.put(Const.HEADER_OSVERSION,             AppUtils.getOSVersion());
        headers.put(Const.HEADER_ACCESS_USER_TOKEN,     AppUtils.getAccessUserToken());
    }

    public HeaderInterceptor(Map<String, String> headers) {
        this.headers = headers;
    }

    @Override
    public Response intercept(Chain chain) throws IOException {

        Request.Builder builder = chain.request()
                .newBuilder();
        if (headers != null && headers.size() > 0) {
            Set<String> keys = headers.keySet();
            for (String headerKey : keys) {
                builder.addHeader(headerKey, headers.get(headerKey)).build();
            }
        }
        return chain.proceed(builder.build());

    }
}