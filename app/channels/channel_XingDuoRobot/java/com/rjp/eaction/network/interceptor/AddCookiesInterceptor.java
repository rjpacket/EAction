package com.rjp.eaction.network.interceptor;

import android.util.Log;
import com.rjp.eaction.baseAF.App;
import com.rjp.eaction.util.LogUtils;
import com.rjp.eaction.utils.SPUtils;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

import java.io.IOException;
import java.util.HashSet;

public class AddCookiesInterceptor implements Interceptor {

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request.Builder builder = chain.request().newBuilder();
        HashSet<String> preferences = (HashSet) SPUtils.getInstance(App.getContext()).getSet("cookie");
        if (preferences != null) {
            for (String cookie : preferences) {
                builder.addHeader("Cookie", cookie);
                // This is done so I know which headers are being added; this interceptor is used after the normal logging of OkHttp
                LogUtils.e("-----cookie----->", cookie);
            }
        }
        return chain.proceed(builder.build());
    }
}