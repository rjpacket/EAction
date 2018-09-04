package com.rjp.eaction.network.interceptor;

import android.content.SharedPreferences;
import com.rjp.eaction.baseAF.App;
import com.rjp.eaction.utils.SPUtils;
import okhttp3.Interceptor;
import okhttp3.Response;

import java.io.IOException;
import java.util.HashSet;

public class ReceivedCookiesInterceptor implements Interceptor {
    @Override
    public Response intercept(Chain chain) throws IOException {
        Response originalResponse = chain.proceed(chain.request());

        if (!originalResponse.headers("Set-Cookie").isEmpty()) {
            HashSet<String> cookies = new HashSet<>();

            for (String header : originalResponse.headers("Set-Cookie")) {
                cookies.add(header);
            }

            SPUtils.getInstance(App.getContext()).save("cookie", cookies);
        }

        return originalResponse;
    }
}