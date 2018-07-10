package com.rjp.eaction.baseAF;

import android.app.Application;

/**
 * author : Gimpo create on 2018/7/10 12:37
 * email  : jimbo922@163.com
 */
public class App extends Application {

    private static App mContext = null;

    @Override
    public void onCreate() {
        super.onCreate();
        mContext = this;
    }

    public static App getContext(){
        return mContext;
    }
}
