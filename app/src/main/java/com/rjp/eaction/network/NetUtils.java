package com.rjp.eaction.network;

/**
 *
 * 网络请求封装库
 * author : Gimpo create on 2018/5/11 19:12
 * email  : jimbo922@163.com
 */
public class NetUtils {
    private static NetUtils ourInstance = null;

    public static NetUtils getInstance() {
        if(ourInstance == null){
            synchronized (NetUtils.class){
                if(ourInstance == null){
                    ourInstance = new NetUtils();
                }
            }
        }
        return ourInstance;
    }

    private NetUtils() {

    }

    /**
     * get 请求方法
     */
    public void get(){

    }
}
