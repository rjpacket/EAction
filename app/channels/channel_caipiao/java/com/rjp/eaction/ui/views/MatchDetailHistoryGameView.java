package com.rjp.eaction.ui.views;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.rjp.eaction.R;
import com.rjp.eaction.bean.MatchDetailCurrentScoreModel;

import java.util.List;

/**
 * 历史对阵
 * author : Gimpo create on 2018/7/24 18:26
 * email  : jimbo922@163.com
 */
public class MatchDetailHistoryGameView extends MatchDetailBaseFunctionView<MatchDetailCurrentScoreModel> {
    public MatchDetailHistoryGameView(Context context) {
        super(context);
    }

    public MatchDetailHistoryGameView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public void bindData(List<MatchDetailCurrentScoreModel> models) {
        llChildContainer.removeAllViews();
        for (MatchDetailCurrentScoreModel model : models) {
            View view = layoutInflater.inflate(R.layout.item_match_detail_current_score_view, null);
            TextView tvTeam = view.findViewById(R.id.tv_team);
            TextView tvTime = view.findViewById(R.id.tv_time);
            TextView tvHost = view.findViewById(R.id.tv_host);
            TextView tvScore = view.findViewById(R.id.tv_score);
            TextView tvVisit = view.findViewById(R.id.tv_visit);
            TextView tvResult = view.findViewById(R.id.tv_result);

            tvTeam.setText(model.getLeagueName());
            tvTeam.setText(model.getLeagueName());
            tvTeam.setText(model.getLeagueName());
            tvTeam.setText(model.getLeagueName());
            tvTeam.setText(model.getLeagueName());
            tvTeam.setText(model.getLeagueName());
            llChildContainer.addView(view);
        }
    }
}
