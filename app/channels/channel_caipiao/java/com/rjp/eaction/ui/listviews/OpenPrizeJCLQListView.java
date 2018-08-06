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
import com.rjp.eaction.bean.OpenPrizeJCLQModel;
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

public class OpenPrizeJCLQListView extends RefreshListView<OpenPrizeJCLQModel> {
    public OpenPrizeJCLQListView(Context context) {
        super(context);
    }

    public OpenPrizeJCLQListView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected BaseAdapter getListAdapter() {
        return new CommonAdapter<OpenPrizeJCLQModel>(mContext, R.layout.item_open_prize_jclq_list_view, mDatas) {
            @Override
            protected void convert(ViewHolder viewHolder, OpenPrizeJCLQModel model, int i) {
                viewHolder.setText(R.id.tv_match_cup, model.getLeague());
                viewHolder.setText(R.id.tv_match_date, model.getMatchNumCn() + "  " + model.getStartTime());
                viewHolder.setText(R.id.tv_match_host, model.getHostName());
                viewHolder.setText(R.id.tv_match_score, model.getHostScore() + ":" + model.getGuestScore());
                viewHolder.setText(R.id.tv_match_half_score, "");
                viewHolder.setText(R.id.tv_match_visit, model.getGuestName());
                viewHolder.setText(R.id.tv_match_1, model.getLottResCnSf());
                viewHolder.setText(R.id.tv_match_2, model.getSpSf());
                viewHolder.setText(R.id.tv_match_3, model.getLottResCnRfsf());
                viewHolder.setText(R.id.tv_match_4, model.getSpRfsf());
                viewHolder.setText(R.id.tv_match_5, model.getLottResCnDxf());
                viewHolder.setText(R.id.tv_match_6, model.getSpDxf());
                viewHolder.setText(R.id.tv_match_7, model.getLottResCnSfc());
                viewHolder.setText(R.id.tv_match_8, model.getSpSfc());
            }
        };
    }

    @Override
    public void requestData() {
        String date = TimeUtils.parseTime(System.currentTimeMillis()  - 2 * 24 * 60 * 60 * 1000, T2);
        new NetUtils.Builder()
                .context(mContext)
                .url("award_jcAwardInfoAssemble.html?mobileType=android&ver=4.31&channel=miui_cps&apiVer=1.1&apiLevel=27&selectedDate=" + date + "&gameEn=jclq")
                .build()
                .originModel(WANGYI_URL, new ResponseCallback<String>() {
                    @Override
                    public void success(String response) {
                        if(response.contains("lottResCnZjq")){
                            requestData();
                            return;
                        }
                        JSONObject jsonObject = JSONObject.parseObject(response);
                        JSONArray matchOddsInfo = jsonObject.getJSONArray("awardMatchList");
                        Gson gson = new Gson();
                        List<OpenPrizeJCLQModel> oddsModels = gson.fromJson(matchOddsInfo.toJSONString(), new TypeToken<List<OpenPrizeJCLQModel>>() {
                        }.getType());
                        dealSuccessData(oddsModels);
                    }

                    @Override
                    public void failure(int code, String msg) {

                    }
                });
    }
}
