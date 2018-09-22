package com.rjp.eaction;

import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.PopupWindow;
import android.widget.SeekBar;

import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.exoplayer2.source.DefaultMediaSourceEventListener;
import com.google.android.exoplayer2.source.MediaSource;
import com.rjp.commonadapter.ViewHolder;
import com.rjp.eaction.base.BaseActivity;
import com.rjp.eaction.ui.app.App;
import com.rjp.eaction.util.popup.AlphaPopupWindow;
import com.rjp.eaction.util.popup.OnPopupBindDataListener;
import com.rjp.eaction.util.popup.PopUtils;
import com.rjp.eaction.utils.UpdateService;

import static com.rjp.eaction.utils.UpdateService.DOWNLOAD_URL;

public class TestActivity extends BaseActivity {

    private final String testUrl = "http://ws.stream.qqmusic.qq.com/M500001VfvsJ21xFqb.mp3?guid=ffffffff82def4af4b12b3cd9337d5e7&uin=346897220&vkey=6292F51E1E384E06DCBDC9AB7C49FD713D632D313AC4858BACB8DDD29067D3C601481D36E62053BF8DFEAF74C0A5CCFADD6471160CAF3E6A&fromtag=46";
    private SimpleExoPlayer mPlayer;
    private SeekBar seekBar;
    private boolean isChange;
    private Button btnClick;

    public static void trendTo(Context mContext) {
        Intent intent = new Intent(mContext, TestActivity.class);
        mContext.startActivity(intent);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    protected void networkReload() {

    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_test;
    }

    @Override
    protected void handle() {
        setTopTitle("test");

        btnClick = findViewById(R.id.btn_click);

        findViewById(R.id.btn_click).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlphaPopupWindow popupWindow = new PopUtils.Builder<String>()
                        .context(mContext)
                        .layoutId(R.layout.item_address_manage_list_view)
                        .build()
                        .show();

                popupWindow.showAsDropDown(btnClick);
            }
        });
    }
}
