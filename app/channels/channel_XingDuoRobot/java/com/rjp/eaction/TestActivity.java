package com.rjp.eaction;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.view.Surface;
import android.view.View;
import android.widget.SeekBar;

import com.google.android.exoplayer2.*;
import com.google.android.exoplayer2.analytics.AnalyticsCollector;
import com.google.android.exoplayer2.analytics.AnalyticsListener;
import com.google.android.exoplayer2.decoder.DecoderCounters;
import com.google.android.exoplayer2.extractor.DefaultExtractorsFactory;
import com.google.android.exoplayer2.extractor.ExtractorsFactory;
import com.google.android.exoplayer2.metadata.Metadata;
import com.google.android.exoplayer2.source.*;
import com.google.android.exoplayer2.trackselection.DefaultTrackSelector;
import com.google.android.exoplayer2.trackselection.TrackSelectionArray;
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory;
import com.google.android.exoplayer2.util.Util;
import com.rjp.eaction.ui.app.App;
import com.rjp.eaction.utils.UpdateService;

import java.io.IOException;

import static com.rjp.eaction.utils.UpdateService.DOWNLOAD_URL;

public class TestActivity extends Activity {

    private final String testUrl = "http://ws.stream.qqmusic.qq.com/M500001VfvsJ21xFqb.mp3?guid=ffffffff82def4af4b12b3cd9337d5e7&uin=346897220&vkey=6292F51E1E384E06DCBDC9AB7C49FD713D632D313AC4858BACB8DDD29067D3C601481D36E62053BF8DFEAF74C0A5CCFADD6471160CAF3E6A&fromtag=46";
    private SimpleExoPlayer mPlayer;
    private SeekBar seekBar;

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

        seekBar = findViewById(R.id.seek_bar);
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                mPlayer.seekTo(seekBar.getProgress());
            }
        });

        mPlayer = App.getMusicPlayer(this);

        MediaSource mediaSource = App.getMusicResourceByUrl(this, testUrl);
        mediaSource.addEventListener(mHandler, new DefaultMediaSourceEventListener() {
            @Override
            public void onLoadStarted(int windowIndex, @Nullable MediaSource.MediaPeriodId mediaPeriodId, LoadEventInfo loadEventInfo, MediaLoadData mediaLoadData) {
                super.onLoadStarted(windowIndex, mediaPeriodId, loadEventInfo, mediaLoadData);
                mHandler.sendEmptyMessage(0);
            }
        });
        mPlayer.prepare(mediaSource);
        mPlayer.setPlayWhenReady(true);
    }

    private Handler mHandler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            seekBar.setMax((int) mPlayer.getDuration());
            seekBar.setProgress((int) mPlayer.getCurrentPosition());
            seekBar.setSecondaryProgress((int) mPlayer.getBufferedPosition());
            mHandler.sendEmptyMessageDelayed(0, 300);
        }
    };

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mHandler.removeCallbacksAndMessages(null);
    }
}
