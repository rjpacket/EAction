package com.rjp.eaction.ui.views;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.rjp.eaction.R;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * author : Gimpo create on 2018/7/24 18:41
 * email  : jimbo922@163.com
 */
public abstract class MatchDetailBaseFunctionView<T> extends LinearLayout {
    @BindView(R.id.rl_function_label)
    RelativeLayout rlFunctionLabel;
    @BindView(R.id.ll_child_container)
    LinearLayout llChildContainer;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    public LayoutInflater layoutInflater;

    public MatchDetailBaseFunctionView(Context context) {
        this(context, null);
    }

    public MatchDetailBaseFunctionView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initView(context, attrs);
    }

    private void initView(Context context, AttributeSet attrs) {
        layoutInflater = LayoutInflater.from(context);
        layoutInflater.inflate(R.layout.view_match_detail_base_function_view, this);
        ButterKnife.bind(this);
    }

    public abstract void bindData(List<T> models);

    @OnClick({R.id.rl_function_label})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.rl_function_label:
                if(llChildContainer.getVisibility() == GONE){
                    llChildContainer.setVisibility(VISIBLE);
                }else{
                    llChildContainer.setVisibility(GONE);
                }
                break;
        }
    }

    public void setTitle(String title){
        tvTitle.setText(title);
    }
}
