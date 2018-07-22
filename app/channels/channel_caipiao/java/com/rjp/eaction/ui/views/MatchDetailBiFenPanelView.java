package com.rjp.eaction.ui.views;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.rjp.eaction.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by small on 2018/7/22.
 */

public class MatchDetailBiFenPanelView extends LinearLayout {
    @BindView(R.id.iv_home_logo)
    ImageView ivHomeLogo;
    @BindView(R.id.tv_home_name)
    TextView tvHomeName;
    @BindView(R.id.tv_home_rank)
    TextView tvHomeRank;
    @BindView(R.id.tv_bi_fen)
    TextView tvBiFen;
    @BindView(R.id.tv_time)
    TextView tvTime;
    @BindView(R.id.iv_away_logo)
    ImageView ivAwayLogo;
    @BindView(R.id.tv_away_name)
    TextView tvAwayName;
    @BindView(R.id.tv_away_rank)
    TextView tvAwayRank;

    public MatchDetailBiFenPanelView(Context context) {
        this(context, null);
    }

    public MatchDetailBiFenPanelView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initView(context, attrs);
    }

    private void initView(Context context, AttributeSet attrs) {
        LayoutInflater.from(context).inflate(R.layout.view_match_detail_bi_fen_panel_view, this);
        ButterKnife.bind(this);
    }
}
