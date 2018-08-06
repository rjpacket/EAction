package com.rjp.eaction.ui.activitys;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Adapter;
import android.widget.BaseAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.rjp.commonadapter.CommonAdapter;
import com.rjp.commonadapter.ViewHolder;
import com.rjp.eaction.R;
import com.rjp.eaction.baseAF.BaseActivity;
import com.rjp.eaction.bean.JCZQOddsModel;
import com.rjp.eaction.bean.OddsDetailModel;
import com.rjp.eaction.bean.OupeiCompanyModel;
import com.rjp.eaction.network.NetUtils;
import com.rjp.eaction.network.callback.ResponseCallback;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

import static com.rjp.eaction.network.retrofit.ApiService.WANGYI_ODDS_URL;

public class OddsOupeiActivity extends BaseActivity {

    @BindView(R.id.company_list_view)
    ListView leftListView;
    @BindView(R.id.odds_list_view)
    ListView rightListView;
    private List<OupeiCompanyModel> companyModels = new ArrayList<>();
    private List<OddsDetailModel> oddsModels = new ArrayList<>();
    private BaseAdapter leftAdapter;
    private BaseAdapter rightAdapter;
    private String matchId;
    private String hostId;
    private String visitId;
    private int index = 0;

    public static void trendTo(Context context, String matchId, String hostId, String visitId) {
        Intent intent = new Intent(context, OddsOupeiActivity.class);
        intent.putExtra("matchId", matchId);
        intent.putExtra("hostId", hostId);
        intent.putExtra("visitId", visitId);
        context.startActivity(intent);
    }

    @Override
    protected void networkReload() {

    }

    @Override
    protected String getPageTitle() {
        return "欧赔";
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_odds_oupei;
    }

    @Override
    protected void handle() {
        leftAdapter = new CommonAdapter<OupeiCompanyModel>(mContext, R.layout.item_odds_oupei_company_list_view, companyModels) {
            @Override
            protected void convert(ViewHolder viewHolder, OupeiCompanyModel model, int i) {
                viewHolder.setText(R.id.tv_company_name, model.getCompanyCnName());
                TextView tvCompany = viewHolder.getView(R.id.tv_company_name);
                tvCompany.setSelected(index == i);
                tvCompany.setTag(i);
                tvCompany.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        index = (Integer) v.getTag();
                        leftAdapter.notifyDataSetChanged();
                        requestRightData(companyModels.get(index).getCompanyId(), matchId, hostId, visitId);
                    }
                });
            }
        };
        leftListView.setAdapter(leftAdapter);
        rightAdapter = new CommonAdapter<OddsDetailModel>(mContext, R.layout.item_odds_oupei_detail_list_view, oddsModels) {
            @Override
            protected void convert(ViewHolder viewHolder, OddsDetailModel model, int i) {
                viewHolder.setText(R.id.tv_sheng, model.getOddsFirst());
                viewHolder.setText(R.id.tv_ping, model.getOddsSecond());
                viewHolder.setText(R.id.tv_fu, model.getOddsThird());
                viewHolder.setText(R.id.tv_time, model.getUpdateTime());
            }
        };
        rightListView.setAdapter(rightAdapter);

        Intent intent = getIntent();
        if(intent != null){
            matchId = intent.getStringExtra("matchId");
            hostId = intent.getStringExtra("hostId");
            visitId = intent.getStringExtra("visitId");
        }

        requestLeftData(matchId);
    }

    private void requestRightData(String companyId, String matchId, String hostId, String visitId) {
        new NetUtils.Builder()
                .context(mContext)
                .url("interface/oddsDetail.html?ver=4.31&channel=miui_cps&apiVer=1.1&mobileType=android&apiLevel=27&type=0&companyId=" + companyId + "&matchId=" + matchId + "&hostId=" + hostId + "&visitId=" + visitId)
                .build()
                .model(WANGYI_ODDS_URL, new ResponseCallback<JSONObject>() {
                    @Override
                    public void success(JSONObject response) {
                        JSONArray matchOddsInfo = response.getJSONArray("oddsChange");
                        Gson gson = new Gson();
                        List<OddsDetailModel> tempModels = gson.fromJson(matchOddsInfo.toJSONString(), new TypeToken<List<OddsDetailModel>>() {
                        }.getType());
                        oddsModels.clear();
                        oddsModels.addAll(tempModels);
                        rightAdapter.notifyDataSetChanged();
                    }

                    @Override
                    public void failure(int code, String msg) {

                    }
                });
    }

    private void requestLeftData(final String matchId) {
        new NetUtils.Builder()
                .context(mContext)
                .url("interface/matchCompany.html?ver=4.31&channel=miui_cps&apiVer=1.1&mobileType=android&apiLevel=27&type=0&matchId=" + matchId)
                .build()
                .model(WANGYI_ODDS_URL, new ResponseCallback<JSONObject>() {
                    @Override
                    public void success(JSONObject response) {
                        JSONArray matchOddsInfo = response.getJSONArray("companyInfo");
                        Gson gson = new Gson();
                        List<OupeiCompanyModel> tempModels = gson.fromJson(matchOddsInfo.toJSONString(), new TypeToken<List<OupeiCompanyModel>>() {
                        }.getType());
                        companyModels.addAll(tempModels);
                        leftAdapter.notifyDataSetChanged();
                        requestRightData(companyModels.get(0).getCompanyId(), matchId, hostId, visitId);
                    }

                    @Override
                    public void failure(int code, String msg) {

                    }
                });
    }
}
