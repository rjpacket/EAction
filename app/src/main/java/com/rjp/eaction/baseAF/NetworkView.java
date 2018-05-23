package com.rjp.eaction.baseAF;

import android.content.Context;
import android.graphics.Color;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.rjp.eaction.R;
import com.rjp.eaction.network.exception.ExceptionHandle;

/**
 * author : Gimpo create on 2018/5/23 18:41
 * email  : jimbo922@163.com
 */
public class NetworkView extends FrameLayout{

    private TextView tvErrorMsg;
    private ReloadListener reloadListener;

    public NetworkView(@android.support.annotation.NonNull Context context) {
        this(context, null);
    }

    public NetworkView(@android.support.annotation.NonNull Context context, @android.support.annotation.Nullable AttributeSet attrs) {
        super(context, attrs);
        initView(context, attrs);
    }

    private void initView(Context context, AttributeSet attrs) {
        setBackgroundColor(Color.WHITE);
        LayoutInflater.from(context).inflate(R.layout.view_network_view_layout, this);
        tvErrorMsg = findViewById(R.id.tv_error_msg);
        findViewById(R.id.tv_reload).setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                setVisibility(GONE);
                if(reloadListener != null){
                    reloadListener.reload();
                }
            }
        });
        setVisibility(GONE);
    }

    public void setNetworkCode(int code){
        tvErrorMsg.setText(ExceptionHandle.getErrorMsg(code));
        setVisibility(VISIBLE);
    }

    public void setReloadListener(ReloadListener reloadListener){
        this.reloadListener = reloadListener;
    }
}
