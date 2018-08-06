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
import com.rjp.eaction.bean.JCZQOddsModel;
import com.rjp.eaction.network.NetUtils;
import com.rjp.eaction.network.callback.ResponseCallback;
import com.rjp.eaction.util.LogUtils;
import com.rjp.eaction.views.base_listview.RefreshListView;

import java.util.List;

import static com.rjp.eaction.network.retrofit.ApiService.WANGYI_ODDS_URL;

/**
 * Created by small on 2018/7/28.
 */

public class JCZQOddsListView extends RefreshListView<JCZQOddsModel> {
    public JCZQOddsListView(Context context) {
        super(context);
    }

    public JCZQOddsListView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected BaseAdapter getListAdapter() {
        return new CommonAdapter<JCZQOddsModel>(mContext, R.layout.item_jczq_odds_list_view, mDatas) {
            @Override
            protected void convert(ViewHolder viewHolder, JCZQOddsModel model, int i) {
                viewHolder.setText(R.id.tv_match_cup, model.getLeagueName());
                viewHolder.setText(R.id.tv_match_time, model.getMatchCode() + " " + model.getMatchTime());
                viewHolder.setText(R.id.tv_match_name, model.getHostName() + " VS " + model.getVisitName());

                List<JCZQOddsModel.OuOddsBean> ouOdds = model.getOuOdds();
                if(ouOdds.size() >= 3){
                    JCZQOddsModel.OuOddsBean ouOddsBean1 = ouOdds.get(0);
                    viewHolder.setText(R.id.tv_company1, ouOddsBean1.getCompanyCnName());
                    viewHolder.setText(R.id.tv_1, ouOddsBean1.getFirstHostOdds());
                    viewHolder.setText(R.id.tv_2, ouOddsBean1.getFirstDrawOdds());
                    viewHolder.setText(R.id.tv_3, ouOddsBean1.getFirstVisitOdds());
                    viewHolder.setText(R.id.tv_4, ouOddsBean1.getNowHostOdds());
                    viewHolder.setText(R.id.tv_5, ouOddsBean1.getNowDrawOdds());
                    viewHolder.setText(R.id.tv_6, ouOddsBean1.getNowVisitOdds());

                    JCZQOddsModel.OuOddsBean ouOddsBean2 = ouOdds.get(1);
                    viewHolder.setText(R.id.tv_company2, ouOddsBean2.getCompanyCnName());
                    viewHolder.setText(R.id.tv_7, ouOddsBean2.getFirstHostOdds());
                    viewHolder.setText(R.id.tv_8, ouOddsBean2.getFirstDrawOdds());
                    viewHolder.setText(R.id.tv_9, ouOddsBean2.getFirstVisitOdds());
                    viewHolder.setText(R.id.tv_10, ouOddsBean2.getNowHostOdds());
                    viewHolder.setText(R.id.tv_11, ouOddsBean2.getNowDrawOdds());
                    viewHolder.setText(R.id.tv_12, ouOddsBean2.getNowVisitOdds());

                    JCZQOddsModel.OuOddsBean ouOddsBean3 = ouOdds.get(2);
                    viewHolder.setText(R.id.tv_company3, ouOddsBean3.getCompanyCnName());
                    viewHolder.setText(R.id.tv_13, ouOddsBean3.getFirstHostOdds());
                    viewHolder.setText(R.id.tv_14, ouOddsBean3.getFirstDrawOdds());
                    viewHolder.setText(R.id.tv_15, ouOddsBean3.getFirstVisitOdds());
                    viewHolder.setText(R.id.tv_16, ouOddsBean3.getNowHostOdds());
                    viewHolder.setText(R.id.tv_17, ouOddsBean3.getNowDrawOdds());
                    viewHolder.setText(R.id.tv_18, ouOddsBean3.getNowVisitOdds());
                }
            }
        };
    }

    @Override
    public void requestData() {
        new NetUtils.Builder()
                .context(mContext)
                .url("interface/matchOddsInfo.html?ver=4.31&channel=miui_cps&apiVer=1.1&mobileType=android&apiLevel=27&gameEn=jczq")
                .build()
                .model(WANGYI_ODDS_URL, new ResponseCallback<JSONObject>() {
                    @Override
                    public void success(JSONObject response) {
                        JSONArray matchOddsInfo = response.getJSONArray("matchOddsInfo");
                        int size = matchOddsInfo.size();
                        for (int i = 0; i < size; i++) {
                            JSONObject jsonObject = matchOddsInfo.getJSONObject(i);
                            jsonObject.remove("hostLogoUrl");
                            jsonObject.remove("visitLogoUrl");
                        }
                        Gson gson = new Gson();
                        List<JCZQOddsModel> oddsModels = gson.fromJson(matchOddsInfo.toJSONString(), new TypeToken<List<JCZQOddsModel>>() {
                        }.getType());
                        dealSuccessData(oddsModels);
                    }

                    @Override
                    public void failure(int code, String msg) {

                    }
                });
    }
}
