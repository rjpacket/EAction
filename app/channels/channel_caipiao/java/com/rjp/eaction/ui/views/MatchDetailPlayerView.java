package com.rjp.eaction.ui.views;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.rjp.eaction.R;
import com.rjp.eaction.bean.MatchDetailInjuredEventsModel;
import com.rjp.eaction.bean.MatchDetailPlayerModel;

import java.util.List;

/**
 *
 * 阵容
 * author : Gimpo create on 2018/7/24 18:22
 * email  : jimbo922@163.com
 */
public class MatchDetailPlayerView extends MatchDetailBaseFunctionView<MatchDetailPlayerModel> {
    public MatchDetailPlayerView(Context context) {
        super(context);
    }

    public MatchDetailPlayerView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public void bindData(List<MatchDetailPlayerModel> models) {
        llChildContainer.removeAllViews();
        if(models.size() == 0){
            llChildContainer.addView(layoutInflater.inflate(R.layout.layout_match_detail_no_data, null));
            return;
        }
        for (MatchDetailPlayerModel model : models) {
            View view = layoutInflater.inflate(R.layout.item_match_detail_player_view, null);
            TextView tvPos = view.findViewById(R.id.tv_pos);
            TextView tvPostion = view.findViewById(R.id.tv_position);
            TextView tvNumber = view.findViewById(R.id.tv_number);
            TextView tvName = view.findViewById(R.id.tv_name);
            tvPos.setText((model.getPositionX() == null ? "--":model.getPositionX()) + "|" + (model.getPositionY() == null ? "--":model.getPositionY()));
            tvPostion.setText(model.getPosition());
            tvNumber.setText(model.getNumber());
            tvName.setText(model.getName());
            llChildContainer.addView(view);
        }
    }
}
