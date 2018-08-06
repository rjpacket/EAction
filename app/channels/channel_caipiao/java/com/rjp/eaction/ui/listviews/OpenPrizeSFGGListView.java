package com.rjp.eaction.ui.listviews;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.widget.BaseAdapter;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.rjp.commonadapter.CommonAdapter;
import com.rjp.commonadapter.ViewHolder;
import com.rjp.eaction.R;
import com.rjp.eaction.bean.OpenPrizeSFGGModel;
import com.rjp.eaction.network.NetUtils;
import com.rjp.eaction.network.callback.ResponseCallback;
import com.rjp.eaction.util.TimeUtils;
import com.rjp.eaction.views.base_listview.RefreshListView;

import java.util.List;

import static com.rjp.eaction.network.retrofit.ApiService.WANGYI_URL;
import static com.rjp.eaction.util.TimeUtils.T2;
import static com.rjp.eaction.util.TimeUtils.T3;

/**
 * Created by small on 2018/8/2.
 */

public class OpenPrizeSFGGListView extends RefreshListView<OpenPrizeSFGGModel> {
    public OpenPrizeSFGGListView(Context context) {
        super(context);
    }

    public OpenPrizeSFGGListView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected BaseAdapter getListAdapter() {
        return new CommonAdapter<OpenPrizeSFGGModel>(mContext, R.layout.item_open_prize_sfgg_list_view, mDatas) {
            @Override
            protected void convert(ViewHolder viewHolder, OpenPrizeSFGGModel model, int i) {
                viewHolder.setText(R.id.tv_match_cup, model.getLeague());
                viewHolder.setText(R.id.tv_match_date, model.getMatchOrder() + "  " + model.getDeadlineTime());
                viewHolder.setText(R.id.tv_match_host, model.getTeamA());
                viewHolder.setText(R.id.tv_match_visit, model.getTeamB());
                String score = model.getScore();
                try {
                    String[] split = score.split(":");
                    viewHolder.setText(R.id.tv_match_score, score);
                    viewHolder.setText(R.id.tv_match_rang, "(让" + model.getConcedeBall() + ")");
                    if (Integer.parseInt(split[0]) > Integer.parseInt(split[1])) {
                        viewHolder.setText(R.id.tv_match_result, "主胜");
                        viewHolder.setText(R.id.tv_match_sp, model.getSp3());
                    } else {
                        viewHolder.setText(R.id.tv_match_result, "主负");
                        viewHolder.setText(R.id.tv_match_sp, model.getSp0());
                    }
                }catch (Exception e){

                }
            }
        };
    }

    @Override
    public void requestData() {
        String date = TimeUtils.parseTime(System.currentTimeMillis()  - 2 * 24 * 60 * 60 * 1000, T3);
        new NetUtils.Builder()
                .context(mContext)
                .url("football_showDcsfgg.html?mobileType=android&ver=4.31&channel=miui_cps&apiVer=1.1&period=8" + date + "&isAward=1")
                .build()
                .originModel(WANGYI_URL, new ResponseCallback<String>() {
                    @Override
                    public void success(String response) {
                        JSONObject jsonObject = JSONObject.parseObject(response);
                        JSONArray matchOddsInfo = jsonObject.getJSONArray("matchInfo");
                        Gson gson = new Gson();
                        List<OpenPrizeSFGGModel> oddsModels = gson.fromJson(matchOddsInfo.toJSONString(), new TypeToken<List<OpenPrizeSFGGModel>>() {
                        }.getType());
                        dealSuccessData(oddsModels);
                    }

                    @Override
                    public void failure(int code, String msg) {

                    }
                });
    }
}
