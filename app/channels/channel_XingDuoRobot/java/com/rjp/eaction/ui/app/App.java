package com.rjp.eaction.ui.app;

import android.app.Application;
import android.content.Context;
import android.net.Uri;

import com.danikula.videocache.HttpProxyCacheServer;
import com.danikula.videocache.file.FileNameGenerator;
import com.rjp.eaction.util.FileUtils;

import java.io.File;

/**
 * author : Gimpo create on 2018/7/10 12:37
 * email  : jimbo922@163.com
 */
public class App extends Application {

    private static App mContext = null;
    private HttpProxyCacheServer proxy;

    public static HttpProxyCacheServer getProxy(Context context) {
        App app = (App) context.getApplicationContext();
        return app.proxy == null ? (app.proxy = app.newProxy()) : app.proxy;
    }

    private HttpProxyCacheServer newProxy() {
        String musicDir = FileUtils.getMusicDir(this);
        return new HttpProxyCacheServer.Builder(this).cacheDirectory(new File(musicDir))
                .fileNameGenerator(new MyFileNameGenerator())
                .build();
    }

    public class MyFileNameGenerator implements FileNameGenerator {//缓存的命名规则

        public String generate(String url) {
            Uri uri = Uri.parse(url);
            String audioId = uri.getQueryParameter("guid");
            return audioId + ".mp3";
        }
    }

    @Override
    public void onCreate() {
        super.onCreate();
        mContext = this;
    }

    public static App getContext() {
        return mContext;
    }
}
