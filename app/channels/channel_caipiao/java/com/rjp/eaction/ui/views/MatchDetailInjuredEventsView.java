package com.rjp.eaction.ui.views;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;

import com.rjp.eaction.R;
import com.rjp.eaction.bean.MatchDetailInjuredEventsModel;

import java.util.List;

/**
 * 伤停事件
 * author : Gimpo create on 2018/7/24 18:21
 * email  : jimbo922@163.com
 */
public class MatchDetailInjuredEventsView extends MatchDetailBaseFunctionView<MatchDetailInjuredEventsModel> {
    public MatchDetailInjuredEventsView(Context context) {
        super(context);
    }

    public MatchDetailInjuredEventsView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public void bindData(List<MatchDetailInjuredEventsModel> models) {
        llChildContainer.removeAllViews();
        for (MatchDetailInjuredEventsModel model : models) {
            View view = layoutInflater.inflate(R.layout.item_match_detail_injured_events_view, null);
            llChildContainer.addView(view);
        }
    }
}
