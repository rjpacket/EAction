package com.rjp.eaction.ui.app;

import android.Manifest;
import android.app.Application;
import android.content.Context;
import android.content.pm.PackageManager;
import android.net.Uri;

import android.support.v4.content.ContextCompat;
import android.text.TextUtils;
import android.util.Base64;
import android.webkit.PermissionRequest;
import com.danikula.videocache.HttpProxyCacheServer;
import com.danikula.videocache.file.FileNameGenerator;
import com.google.android.exoplayer2.*;
import com.google.android.exoplayer2.extractor.DefaultExtractorsFactory;
import com.google.android.exoplayer2.extractor.ExtractorsFactory;
import com.google.android.exoplayer2.source.ExtractorMediaSource;
import com.google.android.exoplayer2.source.MediaSource;
import com.google.android.exoplayer2.trackselection.DefaultTrackSelector;
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory;
import com.google.android.exoplayer2.util.Util;
import com.rjp.eaction.permission.PermissionUtils;
import com.rjp.eaction.util.AppUtils;
import com.rjp.eaction.util.FileUtils;

import java.io.File;
import java.security.Permissions;

/**
 * author : Gimpo create on 2018/7/10 12:37
 * email  : jimbo922@163.com
 */
public class App extends Application {

    private static App mContext = null;
    //缓存代理
    private HttpProxyCacheServer proxy;
    //播放器
    private SimpleExoPlayer mPlayer;

    @Override
    public void onCreate() {
        super.onCreate();
        mContext = this;
    }

    public static App getContext() {
        return mContext;
    }

    /**
     * 获取播放源
     * @param context
     * @param musicUrl
     * @return
     */
    public static MediaSource getMusicResourceByUrl(Context context, String musicUrl){
        String proxyUrl = App.getProxy(context).getProxyUrl(musicUrl, true);
        Uri mp3Uri = Uri.parse(proxyUrl);
        DefaultDataSourceFactory dataSourceFactory = new DefaultDataSourceFactory(context, Util.getUserAgent(context, "xingDuoRobot"));
        ExtractorsFactory extractorsFactory = new DefaultExtractorsFactory();
        return new ExtractorMediaSource(mp3Uri, dataSourceFactory, extractorsFactory, null, null);
    }

    /**
     * 获取音乐播放器
     * @param context
     * @return
     */
    public static SimpleExoPlayer getMusicPlayer(Context context){
        App app = (App) context.getApplicationContext();
        return app.mPlayer == null ? (app.mPlayer = app.newMusicPlayer()) : app.mPlayer;
    }

    /**
     * 初始化音乐播放器
     * @return
     */
    private SimpleExoPlayer newMusicPlayer() {
        RenderersFactory renderersFactory = new DefaultRenderersFactory(this);
        DefaultTrackSelector trackSelector = new DefaultTrackSelector();
        LoadControl loadControl = new DefaultLoadControl();
        return ExoPlayerFactory.newSimpleInstance(renderersFactory, trackSelector, loadControl);
    }

    /**
     * 获取缓存代理
     * @param context
     * @return
     */
    public static HttpProxyCacheServer getProxy(Context context) {
        App app = (App) context.getApplicationContext();
        return app.proxy == null ? (app.proxy = app.newProxy()) : app.proxy;
    }

    /**
     * 初始化缓存代理
     * @return
     */
    private HttpProxyCacheServer newProxy() {
        String musicDir;
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            musicDir = FileUtils.getCacheMusicDir(this);
        }else{
            musicDir = FileUtils.getMusicDir(this);
        }
        return new HttpProxyCacheServer.Builder(this).cacheDirectory(new File(musicDir))
                .fileNameGenerator(new MyFileNameGenerator())
                .build();
    }

    public class MyFileNameGenerator implements FileNameGenerator {//缓存的命名规则

        public String generate(String url) {
            String urlMd5 = AppUtils.md5(url);
            if(TextUtils.isEmpty(urlMd5)){
                //TODO 如何处理？
            }
            return urlMd5 + ".mp3";
        }
    }
}
