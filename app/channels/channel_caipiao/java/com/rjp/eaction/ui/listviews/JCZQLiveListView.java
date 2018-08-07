package com.rjp.eaction.ui.listviews;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.rjp.baselistview.pinned.PinnedHeaderExpandableAdapter;
import com.rjp.baselistview.simple.PinnedListView;
import com.rjp.eaction.R;
import com.rjp.eaction.bean.JCZQBiFenGroupModel;
import com.rjp.eaction.bean.JCZQBiFenModel;
import com.rjp.eaction.network.NetUtils;
import com.rjp.eaction.network.callback.ResponseCallback;
import com.rjp.eaction.ui.activitys.MatchDetailActivity;
import com.rjp.eaction.util.ImageUtils;
import com.rjp.eaction.views.base_listview.EActionRefreshHeader;
import com.scwang.smartrefresh.layout.api.RefreshFooter;
import com.scwang.smartrefresh.layout.api.RefreshHeader;

import java.util.ArrayList;
import java.util.List;

import static com.rjp.eaction.network.retrofit.ApiService.WANGYI_URL;

/**
 * author : Gimpo create on 2018/4/10 19:10
 * email  : jimbo922@163.com
 */

public class JCZQLiveListView extends PinnedListView<JCZQBiFenGroupModel> {
    private String gameEn;
    private String type;

    public JCZQLiveListView(Context context) {
        super(context);
    }

    public JCZQLiveListView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        setHeaderView(R.layout.item_jczq_bifen_list_view_group);
    }

    @Override
    protected RefreshFooter getRefreshFooter() {
        return null;
    }

    @Override
    protected RefreshHeader getRefreshHeader() {
        return new EActionRefreshHeader(mContext);
    }

    @Override
    protected PinnedHeaderExpandableAdapter getPinnedListAdapter() {
        return new PinnedHeaderExpandableAdapter<JCZQBiFenGroupModel, JCZQBiFenModel>(mContext, mDatas, listView) {
            @Override
            protected View initGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
                if(convertView == null){
                    convertView = layoutInflater.inflate(R.layout.item_jczq_bifen_list_view_group, parent, false);
                    convertView.setTag(new GroupHolder(convertView));
                }
                GroupHolder holder = (GroupHolder) convertView.getTag();
                JCZQBiFenGroupModel model = mDatas.get(groupPosition);
                holder.tvTime.setText(model.getPeriod());
                List<JCZQBiFenModel> children = model.getChildren();
                if(children == null){
                    holder.tvCount.setText("共0场");
                }else {
                    holder.tvCount.setText("共" + children.size() + "场");
                }
                return convertView;
            }

            @Override
            public View initChildView(int type, int groupPosition, int childPosition, View convertView, ViewGroup parent) {
                if(convertView == null){
                    convertView = layoutInflater.inflate(R.layout.item_jczq_bifen_list_view_child, parent, false);
                    convertView.setTag(new ChildHolder(convertView));
                }
                ChildHolder holder = (ChildHolder) convertView.getTag();
                JCZQBiFenModel model = getChild(groupPosition, childPosition);
                holder.tvWeek.setText(model.getMatchOrder());
                holder.tvClub.setText(model.getLeagueName());
                holder.tvTime.setText(model.getMatchTime());
                holder.tvHome.setText(model.getTeamA());
                holder.tvStatus.setText(model.getStatusDesc());
                holder.tvAllScore.setText("全场 " + model.getTeamAGoal() + ":" + model.getTeamBGoal());
                holder.tvHalfScore.setText("半场 " + model.getTeamAHalfGoal() + ":" + model.getTeamBHalfGoal());
                holder.tvAway.setText(model.getTeamB());
                ImageUtils.load(mContext, model.getTeamALogo(), holder.ivHomeLogo);
                ImageUtils.load(mContext, model.getTeamBLogo(), holder.ivAwayLogo);
                holder.llLabel.setTag(groupPosition + "," + childPosition);
                holder.llLabel.setOnClickListener(new OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String tag = (String) v.getTag();
                        String[] split = tag.split(",");
                        JCZQBiFenModel model = getChild(Integer.parseInt(split[0]), Integer.parseInt(split[1]));
                        MatchDetailActivity.trendTo(mContext);
                    }
                });
                return convertView;
            }

            @Override
            public void configureHeader(View header, int groupPosition, int childPosition, int alpha) {
                JCZQBiFenGroupModel model = mDatas.get(groupPosition);
                TextView tvTime = (TextView) header.findViewById(R.id.tv_time);
                TextView tvCount = (TextView) header.findViewById(R.id.tv_count);
                tvTime.setText(model.getPeriod());
                List<JCZQBiFenModel> children = model.getChildren();
                if(children == null){
                    tvCount.setText("共0场");
                }else {
                    tvCount.setText("共" + children.size() + "场");
                }
            }
        };
    }

    public class GroupHolder{
        TextView tvTime;
        TextView tvCount;

        public GroupHolder(View view){
            tvTime = (TextView) view.findViewById(R.id.tv_time);
            tvCount = (TextView) view.findViewById(R.id.tv_count);
        }
    }

    public class ChildHolder{
        TextView tvWeek;
        TextView tvClub;
        TextView tvTime;
        TextView tvHome;
        TextView tvStatus;
        TextView tvAllScore;
        TextView tvHalfScore;
        TextView tvAway;

        ImageView ivHomeLogo;
        ImageView ivAwayLogo;

        LinearLayout llLabel;

        public ChildHolder(View view){
            tvWeek = (TextView) view.findViewById(R.id.tv_week);
            tvClub = (TextView) view.findViewById(R.id.tv_club);
            tvTime = (TextView) view.findViewById(R.id.tv_time);
            tvHome = (TextView) view.findViewById(R.id.tv_home);
            tvStatus = (TextView) view.findViewById(R.id.tv_status);
            tvAllScore = (TextView) view.findViewById(R.id.tv_all_score);
            tvHalfScore = (TextView) view.findViewById(R.id.tv_half_score);
            tvAway = (TextView) view.findViewById(R.id.tv_away);
            ivHomeLogo = (ImageView) view.findViewById(R.id.tv_home_logo);
            ivAwayLogo = (ImageView) view.findViewById(R.id.tv_away_logo);
            llLabel = (LinearLayout) view.findViewById(R.id.ll_label);
        }
    }

    @Override
    protected void resetFirstPage() {

    }

    @Override
    protected View getEmptyView() {
        return null;
    }

    public void requestData(String gameEn, String type){
        this.gameEn = gameEn;
        this.type = type;
        requestData();
    }

    @Override
    protected void requestData() {
        new NetUtils.Builder()
                .context(mContext)
                .url("jjc_live.html?mobileType=android&channel=miui_cps&apiLevel=27&apiVer=1.1&ver=4.31&gameEn=" + gameEn)
                .build()
                .model(WANGYI_URL, new ResponseCallback<List<JCZQBiFenGroupModel>>() {
                    @Override
                    public void success(List<JCZQBiFenGroupModel> model) {
                        mDatas.clear();
                        for (JCZQBiFenGroupModel groupModel : model) {
                            List<JCZQBiFenModel> matches = groupModel.getMatchInfo();
                            groupModel.children = new ArrayList<>();
                            for (JCZQBiFenModel match : matches) {
                                if(type.equals(match.getMatchStatus())){
                                    groupModel.children.add(match);
                                }
                            }
                            mDatas.add(groupModel);
                        }
                        notifyDataSetChanged();
                        refreshLayout.finishRefresh();
                        listView.expandAll();
                    }

                    @Override
                    public void failure(int code, String msg) {

                    }
                });
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

    }
}
