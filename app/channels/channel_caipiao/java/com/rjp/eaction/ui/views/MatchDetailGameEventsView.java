package com.rjp.eaction.ui.views;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.rjp.eaction.R;
import com.rjp.eaction.bean.MatchDetailGameEventsModel;
import com.rjp.eaction.sort.MatchDetailGameComparator;
import com.rjp.eaction.utils.EventType;

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
        if(models.size() == 0){
            llChildContainer.addView(layoutInflater.inflate(R.layout.layout_match_detail_no_data, null));
            return;
        }

        Collections.sort(models, new MatchDetailGameComparator());
        for (MatchDetailGameEventsModel model : models) {
            View view = layoutInflater.inflate(R.layout.item_match_detail_game_events_view, null);
            TextView tvHostPerson = view.findViewById(R.id.tv_home_person);
            TextView tvHomeEvent = view.findViewById(R.id.tv_home_event);
            TextView tvTime = view.findViewById(R.id.tv_time);
            TextView tvAwayEvent = view.findViewById(R.id.tv_away_event);
            TextView tvAwayPerson = view.findViewById(R.id.tv_away_person);
            if("0".equals(model.getIshost())){
                tvHostPerson.setVisibility(INVISIBLE);
                tvHomeEvent.setVisibility(INVISIBLE);
                tvAwayEvent.setText(EventType.getTypeName(model.getEventType()));
                tvAwayPerson.setText(model.getPlayerName());
            }else{
                tvAwayPerson.setVisibility(INVISIBLE);
                tvAwayEvent.setVisibility(INVISIBLE);
                tvHomeEvent.setText(EventType.getTypeName(model.getEventType()));
                tvHostPerson.setText(model.getPlayerName());
            }
            tvTime.setText(model.getEventTime());
            llChildContainer.addView(view);
        }
    }

}
