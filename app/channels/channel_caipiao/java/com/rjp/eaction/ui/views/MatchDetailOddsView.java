package com.rjp.eaction.ui.views;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import com.rjp.eaction.R;
import com.rjp.eaction.bean.MatchDetailOddsModel;
import com.rjp.eaction.bean.MatchDetailRankModel;

import java.util.List;

/**
 * author : Gimpo create on 2018/7/24 18:29
 * email  : jimbo922@163.com
 */
public class MatchDetailOddsView extends MatchDetailBaseFunctionView<MatchDetailOddsModel> {
    public MatchDetailOddsView(Context context) {
        super(context);
    }

    public MatchDetailOddsView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public void bindData(List<MatchDetailOddsModel> models) {
        llChildContainer.removeAllViews();
        for (MatchDetailOddsModel model : models) {
            View view = layoutInflater.inflate(R.layout.item_match_detail_news_view, null);
            llChildContainer.addView(view);
        }
    }
}
