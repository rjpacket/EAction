package com.rjp.eaction.ui.views;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.rjp.commonadapter.ViewHolder;
import com.rjp.eaction.R;
import com.rjp.eaction.bean.RecommendMatchModel;
import com.rjp.eaction.util.MathUtils;
import com.rjp.eaction.util.popup.OnPopupBindDataListener;
import com.rjp.eaction.util.popup.PopUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by small on 2018/7/20.
 */

public class RecommendMatchesView extends LinearLayout {
    @BindView(R.id.tv_home_team)
    TextView tvHomeTeam;
    @BindView(R.id.tv_vs)
    TextView tvVs;
    @BindView(R.id.tv_away_team)
    TextView tvAwayTeam;
    @BindView(R.id.tv_pay)
    TextView tvPay;
    @BindView(R.id.tv_award)
    TextView tvAward;
    private List<String> sp;
    private Context mContext;
    private PopupWindow popupWindow;
    private String currentSp;
    private String currentPay;

    public RecommendMatchesView(Context context) {
        this(context, null);
    }

    public RecommendMatchesView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initView(context, attrs);
    }

    private void initView(Context context, AttributeSet attrs) {
        mContext = context;
        LayoutInflater.from(context).inflate(R.layout.view_recommend_matches_view, this);
        ButterKnife.bind(this);
        initPopupWindow();
    }

    public void bindData(RecommendMatchModel model) {
        sp = model.getSp();
        tvHomeTeam.setText(model.getHostName() + "\n" + sp.get(0));
        tvVs.setText("平\n" + model.getSp().get(1));
        tvAwayTeam.setText(model.getHostName() + "\n" + sp.get(2));
        tvHomeTeam.setSelected(true);
        currentPay = "20";
        currentSp = sp.get(0);
        refreshAward();
    }

    @OnClick({R.id.tv_home_team, R.id.tv_vs, R.id.tv_away_team, R.id.tv_pay})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_home_team:
                tvHomeTeam.setSelected(true);
                tvVs.setSelected(false);
                tvAwayTeam.setSelected(false);
                currentSp = sp.get(0);
                refreshAward();
                break;
            case R.id.tv_vs:
                tvHomeTeam.setSelected(false);
                tvVs.setSelected(true);
                tvAwayTeam.setSelected(false);
                currentSp = sp.get(1);
                refreshAward();
                break;
            case R.id.tv_away_team:
                tvHomeTeam.setSelected(false);
                tvVs.setSelected(false);
                tvAwayTeam.setSelected(true);
                currentSp = sp.get(2);
                refreshAward();
                break;
            case R.id.tv_pay:
                popupWindow.showAsDropDown(tvPay);
                break;
        }
    }

    private void refreshAward() {
        tvPay.setText(currentPay + "元");
        tvAward.setText("预计奖金：" + MathUtils.keep20(Double.parseDouble(currentSp) * Integer.parseInt(currentPay)) + "元");
    }

    private void initPopupWindow() {
        final List<String> models = new ArrayList<>();
        models.add("20");
        models.add("50");
        models.add("100");
        models.add("200");
        models.add("500");
        models.add("1000");
        popupWindow = new PopUtils.Builder<String>()
                .context(mContext)
                .width(120)
                .models(models)
                .bindData(new OnPopupBindDataListener<String>() {
                    @Override
                    public void convert(ViewHolder viewHolder, String item, int position) {
                        TextView tvOption = viewHolder.getView(R.id.tv_option);
                        tvOption.setText(item + "元");
                        tvOption.setTag(position);
                        tvOption.setOnClickListener(new OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                Integer tag = (Integer) v.getTag();
                                currentPay = models.get(tag);
                                refreshAward();
                                popupWindow.dismiss();
                            }
                        });
                    }
                })
                .build()
                .show();
    }
}
