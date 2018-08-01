package com.rjp.eaction.ui.views;

import android.content.Context;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.rjp.eaction.R;

/**
 * author : Gimpo create on 2018/8/1 17:44
 * email  : jimbo922@163.com
 */
public class CustomTextView extends LinearLayout {
    private TextView tvText;
    private ImageView ivImage;
    private boolean isOpen = false;
    private String text = "";

    public CustomTextView(Context context) {
        this(context, null);
    }

    public CustomTextView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initView(context, attrs);
    }

    private void initView(Context context, AttributeSet attrs) {
        LayoutInflater.from(context).inflate(R.layout.view_custom_text_view, this);
        tvText = findViewById(R.id.tv_text);
        ivImage = findViewById(R.id.iv_arrow);
        ivImage.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                isOpen = !isOpen;
                fitTextView();
            }
        });
    }

    private void fitTextView() {
        int width = tvText.getWidth();
        float measureText = tvText.getPaint().measureText(text);
        if(measureText > width){
            ivImage.setVisibility(VISIBLE);
            if(isOpen){
                int lines = (int) measureText / width;
                tvText.setEllipsize(TextUtils.TruncateAt.START);
                tvText.setLines(lines + 1);
            }else {
                tvText.setEllipsize(TextUtils.TruncateAt.END);
                tvText.setLines(1);
            }
        }else{
            ivImage.setVisibility(GONE);
            tvText.setEllipsize(TextUtils.TruncateAt.START);
            tvText.setLines(1);
        }
    }

    public void setText(String s) {
        this.text = s;
        tvText.setText(s);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                fitTextView();
            }
        }, 200);
    }
}
