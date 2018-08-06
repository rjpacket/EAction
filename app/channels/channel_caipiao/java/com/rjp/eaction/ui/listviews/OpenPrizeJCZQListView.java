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
import com.rjp.eaction.bean.OpenPrizeJCZQModel;
import com.rjp.eaction.network.NetUtils;
import com.rjp.eaction.network.callback.ResponseCallback;
import com.rjp.eaction.util.TimeUtils;
import com.rjp.eaction.views.base_listview.RefreshListView;

import java.util.List;

import static com.rjp.eaction.network.retrofit.ApiService.WANGYI_URL;
import static com.rjp.eaction.util.TimeUtils.T2;

/**
 * Created by small on 2018/8/2.
 */

public class OpenPrizeJCZQListView extends RefreshListView<OpenPrizeJCZQModel> {
    public OpenPrizeJCZQListView(Context context) {
        super(context);
    }

    public OpenPrizeJCZQListView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected BaseAdapter getListAdapter() {
        return new CommonAdapter<OpenPrizeJCZQModel>(mContext, R.layout.item_open_prize_jczq_list_view, mDatas) {
            @Override
            protected void convert(ViewHolder viewHolder, OpenPrizeJCZQModel model, int i) {
                viewHolder.setText(R.id.tv_match_cup, model.getLeague());
                viewHolder.setText(R.id.tv_match_date, model.getMatchNumCn() + "  " + model.getStartTime());
                viewHolder.setText(R.id.tv_match_host, model.getHostName());
                viewHolder.setText(R.id.tv_match_score, model.getFinalHostScore() + ":" + model.getFinalGuestScore());
                viewHolder.setText(R.id.tv_match_half_score, model.getHalfHostScore() + ":" + model.getHalfGuestScore());
                viewHolder.setText(R.id.tv_match_visit, model.getGuestName());
                viewHolder.setText(R.id.tv_match_1, model.getLottResCnSpf());
                viewHolder.setText(R.id.tv_match_2, model.getSpSpf());
                viewHolder.setText(R.id.tv_match_3, model.getLottResCnRqspf());
                viewHolder.setText(R.id.tv_match_4, model.getSpRqspf());
                viewHolder.setText(R.id.tv_match_5, model.getLottResCnBf());
                viewHolder.setText(R.id.tv_match_6, model.getSpBf());
                viewHolder.setText(R.id.tv_match_7, model.getLottResCnZjq());
                viewHolder.setText(R.id.tv_match_8, model.getSpZjq());
                viewHolder.setText(R.id.tv_match_9, model.getLottResCnBqc());
                viewHolder.setText(R.id.tv_match_10, model.getSpBqc());
            }
        };
    }

    @Override
    public void requestData() {
        String date = TimeUtils.parseTime(System.currentTimeMillis()  - 2 * 24 * 60 * 60 * 1000, T2);
        new NetUtils.Builder()
                .context(mContext)
                .url("award_jcAwardInfoAssemble.html?mobileType=android&ver=4.31&channel=miui_cps&apiVer=1.1&apiLevel=27&selectedDate=" + date + "&gameEn=jczq")
                .build()
                .originModel(WANGYI_URL, new ResponseCallback<String>() {
                    @Override
                    public void success(String response) {
                        if(response.contains("lottResCnDxf")){
                            requestData();
                            return;
                        }
                        JSONObject jsonObject = JSONObject.parseObject(response);
                        JSONArray matchOddsInfo = jsonObject.getJSONArray("awardMatchList");
                        Gson gson = new Gson();
                        List<OpenPrizeJCZQModel> oddsModels = gson.fromJson(matchOddsInfo.toJSONString(), new TypeToken<List<OpenPrizeJCZQModel>>() {
                        }.getType());
                        dealSuccessData(oddsModels);
                    }

                    @Override
                    public void failure(int code, String msg) {

                    }
                });
    }
}
