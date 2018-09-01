package com.rjp.eaction.network.interceptor;

import android.content.Context;

import com.rjp.eaction.baseAF.Const;
import com.rjp.eaction.util.AppUtils;
import com.rjp.eaction.util.LogUtils;

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
        refreshHeader(mContext);
    }

    public HeaderInterceptor(Map<String, String> headers) {
        this.headers = headers;
    }

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request.Builder builder = chain.request()
                .newBuilder();
//        if (headers != null && headers.size() > 0) {
//            Set<String> keys = headers.keySet();
//            for (String headerKey : keys) {
//                String value = headers.get(headerKey);
//                builder.addHeader(headerKey, value).build();
//            }
//        }
        return chain.proceed(builder.build());

    }

    /**
     * 刷新请求头
     */
    public void refreshHeader(Context mContext) {
        this.headers = new HashMap<>();
        headers.put(Const.HEADER_VERSION,               AppUtils.getVersionName());
        headers.put(Const.HEADER_APP_TYPE,              AppUtils.getAppType());
        headers.put(Const.HEADER_VERSION_CODE,          AppUtils.getVersionCode());
        headers.put(Const.HEADER_DEVICE_ID,             AppUtils.getMacAddress(mContext));
        headers.put(Const.HEADER_MODEL,                 AppUtils.getModel());
        headers.put(Const.HEADER_SIGN,                  "");
        headers.put(Const.HEADER_IMEI,                  AppUtils.getMacAddress(mContext));
        headers.put(Const.HEADER_APPLICATION_ID,        AppUtils.getAppId());
        headers.put(Const.HEADER_MAC_ADDRESS,           AppUtils.getMacAddress(mContext));
        headers.put(Const.HEADER_CHANNEL,               AppUtils.getChannel(mContext));
        headers.put(Const.HEADER_BRAND,                 AppUtils.getBrand());
        headers.put(Const.HEADER_TIME_STAMP,            AppUtils.getTimeStamp());
        headers.put(Const.HEADER_OSVERSION,             AppUtils.getOSVersion());
        headers.put(Const.HEADER_ACCESS_USER_TOKEN,     AppUtils.getAccessUserToken());
        LogUtils.e(headers.toString());
    }
}