package com.rjp.eaction.ui.views;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;

import com.rjp.eaction.R;
import com.rjp.eaction.bean.MatchDetailCurrentScoreModel;
import com.rjp.eaction.bean.MatchDetailRankListModel;

import java.util.List;

/**
 * 积分榜
 * author : Gimpo create on 2018/7/24 18:31
 * email  : jimbo922@163.com
 */
public class MatchDetailRankListView extends MatchDetailBaseFunctionView<MatchDetailRankListModel> {
    public MatchDetailRankListView(Context context) {
        super(context);
    }

    public MatchDetailRankListView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public void bindData(List<MatchDetailRankListModel> models) {
        llChildContainer.removeAllViews();
        for (MatchDetailRankListModel model : models) {
            View view = layoutInflater.inflate(R.layout.item_match_detail_current_score_view, null);
            llChildContainer.addView(view);
        }
    }
}
