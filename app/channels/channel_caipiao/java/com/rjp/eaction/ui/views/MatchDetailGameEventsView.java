package com.rjp.eaction.ui.views;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;

import com.rjp.eaction.R;
import com.rjp.eaction.bean.MatchDetailGameEventsModel;
import com.rjp.eaction.sort.MatchDetailGameComparator;

import java.util.Collections;
import java.util.List;

/**
 *
 * 比赛事件
 * author : Gimpo create on 2018/7/24 18:19
 * email  : jimbo922@163.com
 */
public class MatchDetailGameEventsView extends MatchDetailBaseFunctionView<MatchDetailGameEventsModel> {

    public MatchDetailGameEventsView(Context context) {
        super(context);
    }

    public MatchDetailGameEventsView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public void bindData(List<MatchDetailGameEventsModel> models) {
        llChildContainer.removeAllViews();
        Collections.sort(models, new MatchDetailGameComparator());
        for (MatchDetailGameEventsModel model : models) {
            View view = layoutInflater.inflate(R.layout.item_match_detail_game_events_view, null);
            llChildContainer.addView(view);
        }
    }

}
