package com.rjp.eaction.network.retrofit;


import com.alibaba.fastjson.JSONObject;
import com.rjp.eaction.BuildConfig;

import java.util.Map;

import com.rjp.eaction.base.UrlConst;
import io.reactivex.Observable;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.http.*;

/**
 * author : Gimpo create on 2018/5/14 10:27
 * email  : jimbo922@163.com
 */
public interface ApiService {

    //地址
    String BASE_URL = BuildConfig.DEBUG ? UrlConst.RELEASE_HOST : "http://47.96.127.217";

    String WANGYI_URL = "http://api.caipiao.163.com/";

    String WANGYI_NEWS_URL = "http://zxwap.caipiao.163.com/";

    String WANGYI_ODDS_URL = "http://odds.caipiao.163.com/";

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
    Observable<ResponseBody> post(@Path("url") String url, @Body JSONObject map);
}
 