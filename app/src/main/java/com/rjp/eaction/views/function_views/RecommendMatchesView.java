package com.rjp.eaction.views.function_views;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.LinearLayout;

import com.rjp.eaction.R;

/**
 * Created by small on 2018/7/20.
 */

public class RecommendMatchesView extends LinearLayout {
    public RecommendMatchesView(Context context) {
        this(context, null);
    }

    public RecommendMatchesView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initView(context, attrs);
    }

    private void initView(Context context, AttributeSet attrs) {
        LayoutInflater.from(context).inflate(R.layout.view_recommend_matches_view, this);
    }

}
