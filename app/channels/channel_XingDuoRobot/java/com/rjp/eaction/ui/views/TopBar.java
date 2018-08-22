package com.rjp.eaction.ui.views;

import android.content.Context;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.rjp.eaction.R;

/**
 * author : Gimpo create on 2018/5/24 17:58
 * email  : jimbo922@163.com
 */
public class TopBar extends LinearLayout {

    private TextView tvBaseTitle;
    private ImageView ivBaseBack;
    private ImageView ivIcon0;
    private ImageView ivIcon1;

    public TopBar(Context context) {
        this(context, null);
    }

    public TopBar(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView(context, attrs);
    }

    private void initView(Context context, AttributeSet attrs) {
        LayoutInflater.from(context).inflate(R.layout.layout_top_bar, this);
        setBackgroundColor(context.getResources().getColor(R.color.main_color));

        ivBaseBack = (ImageView) findViewById(R.id.iv_base_back);
        tvBaseTitle = (TextView) findViewById(R.id.tv_base_title);
        ivIcon0 = (ImageView) findViewById(R.id.iv_base_icon0);
        ivIcon1 = (ImageView) findViewById(R.id.iv_base_icon1);

        setOrientation(VERTICAL);
    }

    public void setTitle(String pageTitle) {
        if (!TextUtils.isEmpty(pageTitle)) {
            tvBaseTitle.setText(pageTitle);
        }
    }

    public void setOnBackClickListener(OnClickListener onClickListener) {
        ivBaseBack.setOnClickListener(onClickListener);
    }

    public void setIcon1(int resId) {
        ivIcon1.setImageResource(resId);
        ivIcon1.setVisibility(View.VISIBLE);
    }

    public void setOnIcon1ClickListener(OnClickListener onClickListener) {
        ivIcon1.setOnClickListener(onClickListener);
    }

    public void setIcon0(int resId) {
        ivIcon0.setImageResource(resId);
        ivIcon0.setVisibility(View.VISIBLE);
    }

    public void setOnIcon0ClickListener(OnClickListener onClickListener) {
        ivIcon0.setOnClickListener(onClickListener);
    }

    /**
     * 返回按钮隐藏
     * @param hide
     */
    public void setBackHidden(boolean hide) {
        ivBaseBack.setVisibility(hide ? GONE : VISIBLE);
    }
}
