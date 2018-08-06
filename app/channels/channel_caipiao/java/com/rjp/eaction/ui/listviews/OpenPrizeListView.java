package com.rjp.eaction.ui.listviews;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.widget.BaseAdapter;

import com.rjp.commonadapter.CommonAdapter;
import com.rjp.commonadapter.ViewHolder;
import com.rjp.eaction.R;
import com.rjp.eaction.bean.OpenPrizeModel;
import com.rjp.eaction.network.NetUtils;
import com.rjp.eaction.network.callback.ResponseCallback;
import com.rjp.eaction.utils.LotteryType;
import com.rjp.eaction.views.base_listview.LoadMoreListView;
import com.rjp.eaction.views.base_listview.RefreshListView;

import java.util.List;

import static com.rjp.eaction.network.retrofit.ApiService.WANGYI_URL;

/**
 * Created by small on 2018/7/25.
 */

public class OpenPrizeListView extends RefreshListView<OpenPrizeModel> {
    public OpenPrizeListView(Context context) {
        super(context);
    }

    public OpenPrizeListView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected BaseAdapter getListAdapter() {
        return new CommonAdapter<OpenPrizeModel>(mContext, R.layout.item_open_prize_list_view, mDatas) {
            @Override
            protected void convert(ViewHolder viewHolder, OpenPrizeModel openPrizeModel, int i) {
                viewHolder.setText(R.id.lottery_name, LotteryType.getLotteryName(openPrizeModel.getGameEn()));
                viewHolder.setText(R.id.tv_phase, "第" + openPrizeModel.getPeriodName() + "期");
                viewHolder.setText(R.id.tv_number, openPrizeModel.getAwardNo());
            }
        };
    }

    @Override
    public void requestData() {
        new NetUtils.Builder()
                .context(mContext)
                .url("award_home.html?mobileType=android&ver=4.31&channel=miui_cps&apiVer=1.1&apiLevel=27")
                .build()
                .model(WANGYI_URL, new ResponseCallback<List<OpenPrizeModel>>() {
                    @Override
                    public void success(List<OpenPrizeModel> model) {
                        dealSuccessData(model);
                    }

                    @Override
                    public void failure(int code, String msg) {

                    }
                });
    }
}
