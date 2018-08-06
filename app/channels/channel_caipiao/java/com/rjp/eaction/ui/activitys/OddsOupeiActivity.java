package com.rjp.eaction.ui.activitys;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.rjp.eaction.R;
import com.rjp.eaction.baseAF.BaseActivity;
import com.rjp.eaction.bean.JCZQOddsModel;
import com.rjp.eaction.bean.OupeiCompanyModel;
import com.rjp.eaction.network.NetUtils;
import com.rjp.eaction.network.callback.ResponseCallback;

import java.util.List;

import static com.rjp.eaction.network.retrofit.ApiService.WANGYI_ODDS_URL;

public class OddsOupeiActivity extends BaseActivity {

    public static void trendTo(Context context) {
        Intent intent = new Intent(context, OpenPrizeJCLQActivity.class);
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
        requestLeftData("1404535");
    }

    private void requestRightData(String companyId, String matchId, String hostId, String visitId) {
        new NetUtils.Builder()
                .context(mContext)
                .url("interface/oddsDetail.html?ver=4.31&channel=miui_cps&apiVer=1.1&mobileType=android&apiLevel=27&type=0")
                .param("companyId", companyId)
                .param("matchId", matchId)
                .param("hostId", hostId)
                .param("visitId", visitId)
                .build()
                .model(WANGYI_ODDS_URL, new ResponseCallback<JSONObject>() {
                    @Override
                    public void success(JSONObject response) {
                        JSONArray matchOddsInfo = response.getJSONArray("companyInfo");
                        Gson gson = new Gson();
                        List<OupeiCompanyModel> companyModels = gson.fromJson(matchOddsInfo.toJSONString(), new TypeToken<List<OupeiCompanyModel>>() {
                        }.getType());

                    }

                    @Override
                    public void failure(int code, String msg) {

                    }
                });
    }

    private void requestLeftData(String matchId) {
        new NetUtils.Builder()
                .context(mContext)
                .url("interface/matchCompany.html?ver=4.31&channel=miui_cps&apiVer=1.1&mobileType=android&apiLevel=27&type=0&matchId=" + matchId)
                .build()
                .model(WANGYI_ODDS_URL, new ResponseCallback<JSONObject>() {
                    @Override
                    public void success(JSONObject response) {
                        JSONArray matchOddsInfo = response.getJSONArray("companyInfo");
                        Gson gson = new Gson();
                        List<OupeiCompanyModel> companyModels = gson.fromJson(matchOddsInfo.toJSONString(), new TypeToken<List<OupeiCompanyModel>>() {
                        }.getType());

                    }

                    @Override
                    public void failure(int code, String msg) {

                    }
                });
    }
}
