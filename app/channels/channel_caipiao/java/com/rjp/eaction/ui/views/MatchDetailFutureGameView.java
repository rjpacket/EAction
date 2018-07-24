package com.rjp.eaction.ui.views;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;

import com.rjp.eaction.R;
import com.rjp.eaction.bean.MatchDetailFutureGameModel;
import com.rjp.eaction.bean.MatchDetailGameEventsModel;
import com.rjp.eaction.sort.MatchDetailGameComparator;

import java.util.Collections;
import java.util.List;

/**
 * 未来赛事
 * author : Gimpo create on 2018/7/24 18:28
 * email  : jimbo922@163.com
 */
public class MatchDetailFutureGameView extends MatchDetailBaseFunctionView<MatchDetailFutureGameModel> {
    public MatchDetailFutureGameView(Context context) {
        super(context);
    }

    public MatchDetailFutureGameView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public void bindData(List<MatchDetailFutureGameModel> models) {
        llChildContainer.removeAllViews();
        for (MatchDetailFutureGameModel model : models) {
            View view = layoutInflater.inflate(R.layout.item_match_detail_future_game_view, null);
            llChildContainer.addView(view);
        }
    }
}
