package com.rjp.eaction.network.retrofit;


import java.util.Map;

import io.reactivex.Observable;
import okhttp3.ResponseBody;
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
    public static final String BASE_URL = "https://api.douban.com/";

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
    Observable<ResponseBody> post(@Path("url") String url, @QueryMap Map<String, String> map);
}
