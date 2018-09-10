package com.rjp.eaction;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.SeekBar;

import com.google.android.exoplayer2.DefaultLoadControl;
import com.google.android.exoplayer2.DefaultRenderersFactory;
import com.google.android.exoplayer2.ExoPlayerFactory;
import com.google.android.exoplayer2.LoadControl;
import com.google.android.exoplayer2.RenderersFactory;
import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.exoplayer2.extractor.DefaultExtractorsFactory;
import com.google.android.exoplayer2.extractor.ExtractorsFactory;
import com.google.android.exoplayer2.source.ExtractorMediaSource;
import com.google.android.exoplayer2.source.MediaSource;
import com.google.android.exoplayer2.trackselection.DefaultTrackSelector;
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory;
import com.google.android.exoplayer2.util.Util;
import com.rjp.eaction.ui.app.App;
import com.rjp.eaction.utils.UpdateService;

import static com.rjp.eaction.utils.UpdateService.DOWNLOAD_URL;

public class TestActivity extends Activity {

    private final String testUrl = "http://ws.stream.qqmusic.qq.com/M500001VfvsJ21xFqb.mp3?guid=ffffffff82def4af4b12b3cd9337d5e7&uin=346897220&vkey=6292F51E1E384E06DCBDC9AB7C49FD713D632D313AC4858BACB8DDD29067D3C601481D36E62053BF8DFEAF74C0A5CCFADD6471160CAF3E6A&fromtag=46";
    private SimpleExoPlayer mPlayer;

    public static void trendTo(Context mContext) {
        Intent intent = new Intent(mContext, TestActivity.class);
        mContext.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);

        findViewById(R.id.btn_update).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String downloadUrl = "http://download.dajiang365.com/app-zywl-agent_guanwang.apk";
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        //启动服务
                        Intent service = new Intent(TestActivity.this,UpdateService.class);
                        service.putExtra(DOWNLOAD_URL, downloadUrl);
                        startService(service);
                    }
                }).start();
            }
        });

        SeekBar seekBar = findViewById(R.id.seek_bar);
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        RenderersFactory renderersFactory = new DefaultRenderersFactory(this);
        DefaultTrackSelector trackSelector = new DefaultTrackSelector();
        LoadControl loadControl = new DefaultLoadControl();
        mPlayer = ExoPlayerFactory.newSimpleInstance(renderersFactory, trackSelector, loadControl);

        String proxyUrl = App.getProxy(this)
                .getProxyUrl(testUrl, true);
        Uri mp3Uri = Uri.parse(proxyUrl);
        DefaultDataSourceFactory dataSourceFactory = new DefaultDataSourceFactory(
                this, Util.getUserAgent(this, "exoPlayerTest"));
        ExtractorsFactory extractorsFactory = new DefaultExtractorsFactory();
        MediaSource mediaSource = new ExtractorMediaSource(
                mp3Uri, dataSourceFactory, extractorsFactory, null, null);
        mPlayer.prepare(mediaSource);
        mPlayer.setPlayWhenReady(true);
    }

}
