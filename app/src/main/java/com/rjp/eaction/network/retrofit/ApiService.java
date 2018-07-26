package com.rjp.eaction.network.retrofit;


import com.rjp.eaction.BuildConfig;

import java.util.Map;

import io.reactivex.Observable;
import okhttp3.ResponseBody;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.QueryMap;

/**
 * author : Gimpo create on 2018/5/14 10:27
 * email  : jimbo922@163.com
 */
public interface ApiService {

    //地址
    String BASE_URL = BuildConfig.DEBUG ? "http://192.168.3.8:80" : "http://47.96.127.217";

    String WANGYI_URL = "http://api.caipiao.163.com/";

    String WANGYI_NEWS_URL = "http://zxwap.caipiao.163.com/";

    /**
     * 通用get方法
     * @return
     */
    @GET("{url}")
    Observable<ResponseBody> get(@Path("url") String url, @QueryMap Map<String, String> map);

    /**
     * 通用post方法
     * @return
     */
    @POST("{url}")
    Observable<ResponseBody> post(@Path("url") String url, @Body Map<String, String> map);
}
