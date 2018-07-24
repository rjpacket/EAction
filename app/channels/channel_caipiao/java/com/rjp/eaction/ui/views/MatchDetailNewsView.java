package com.rjp.eaction.ui.views;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;

import com.rjp.eaction.R;
import com.rjp.eaction.bean.MatchDetailCurrentScoreModel;
import com.rjp.eaction.bean.MatchDetailNewsModel;

import java.util.List;

/**
 * author : Gimpo create on 2018/7/24 18:29
 * email  : jimbo922@163.com
 */
public class MatchDetailNewsView extends MatchDetailBaseFunctionView<MatchDetailNewsModel> {
    public MatchDetailNewsView(Context context) {
        super(context);
    }

    public MatchDetailNewsView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public void bindData(List<MatchDetailNewsModel> models) {
        llChildContainer.removeAllViews();
        for (MatchDetailNewsModel model : models) {
            View view = layoutInflater.inflate(R.layout.item_match_detail_news_view, null);
            llChildContainer.addView(view);
        }
    }
}
