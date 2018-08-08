package com.rjp.eaction.ui.views;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

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
        if(models.size() == 0){
            llChildContainer.addView(layoutInflater.inflate(R.layout.layout_match_detail_no_data, null));
            return;
        }
        for (MatchDetailInjuredEventsModel model : models) {
            View view = layoutInflater.inflate(R.layout.item_match_detail_injured_events_view, null);
            TextView tvName = view.findViewById(R.id.tv_name);
            TextView tvNumber = view.findViewById(R.id.tv_number);
            TextView tvPosition = view.findViewById(R.id.tv_position);
            TextView tvCountry = view.findViewById(R.id.tv_country);
            TextView tvAge = view.findViewById(R.id.tv_age);
            TextView tvType = view.findViewById(R.id.tv_type);
            TextView tvData = view.findViewById(R.id.tv_data);
            tvName.setText(model.getPlayerNameCn());
            tvNumber.setText(model.getShirtNumber());
            tvPosition.setText(model.getPosition());
            tvCountry.setText("国籍：" + model.getCountry());
            tvAge.setText("年龄：" + model.getAge());
            tvType.setText("伤病类型：" + model.getType());
            tvData.setText("出场数据：" + model.getStastics());
            llChildContainer.addView(view);
        }
    }
}
