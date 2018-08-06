package com.rjp.eaction.ui.listviews;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.alibaba.fastjson.JSONArray;
import com.rjp.commonadapter.CommonAdapter;
import com.rjp.commonadapter.ViewHolder;
import com.rjp.eaction.R;
import com.rjp.eaction.bean.HomeNewsModel;
import com.rjp.eaction.bean.OpenPrizeModel;
import com.rjp.eaction.network.NetUtils;
import com.rjp.eaction.network.callback.ResponseCallback;
import com.rjp.eaction.ui.activitys.NewsDetailActivity;
import com.rjp.eaction.util.ImageUtils;
import com.rjp.eaction.views.base_listview.LoadMoreListView;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;
import java.util.List;

import static com.rjp.eaction.network.retrofit.ApiService.WANGYI_NEWS_URL;
import static com.rjp.eaction.network.retrofit.ApiService.WANGYI_URL;

/**
 * 通用的网易资讯请求入口
 * author : Gimpo create on 2018/4/10 15:18
 * email  : jimbo922@163.com
 */

public class HomeNewsListView extends LoadMoreListView<HomeNewsModel> {
    private String type = "ssq";

    public HomeNewsListView(Context context) {
        super(context);
    }

    public HomeNewsListView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected BaseAdapter getListAdapter() {
        return new CommonAdapter<HomeNewsModel>(mContext, R.layout.item_home_news_list_view, mDatas) {
            @Override
            protected void convert(ViewHolder viewHolder, HomeNewsModel item, int position) {
                String title = item.getTitle();
                viewHolder.setText(R.id.tv_news_title, title);
                viewHolder.setText(R.id.tv_news_desc, item.getDesc());
                viewHolder.setText(R.id.tv_news_time, item.getTime());
                ImageView ivBigPic = viewHolder.getView(R.id.iv_big_pic);
                ImageView ivSmallPic = viewHolder.getView(R.id.iv_small_pic);
                if (title.length() > 15) {
                    ivBigPic.setVisibility(VISIBLE);
                    ivSmallPic.setVisibility(GONE);
                    ImageUtils.load(mContext, item.getImg(), ivBigPic);
                } else {
                    ivBigPic.setVisibility(GONE);
                    ivSmallPic.setVisibility(VISIBLE);
                    ImageUtils.load(mContext, item.getImg(), ivSmallPic);
                }
            }
        };
    }

    public void requestData(String type) {
        this.type = type;
        requestData();
    }

    @Override
    public void requestData() {
        new NetUtils.Builder()
                .context(mContext)
                .url("zx/wap_list.html?cache=" + System.currentTimeMillis() + "&pageNo=" + mPage + "&tp=" + type + "&ajax=1")
                .build()
                .originModel(WANGYI_NEWS_URL, new ResponseCallback<String>() {
                    @Override
                    public void success(String result) {
                        List<HomeNewsModel> models = new ArrayList<>();
                        try {
                            Document document = Jsoup.parse(result);
                            Elements elements = document.select("li.newsItem");
                            for (Element element : elements) {
                                HomeNewsModel model = new HomeNewsModel();
                                Element a = element.select("a.newsLink").first();
                                model.setUrl(a.attr("href"));
                                Element img = element.select("img").first();
                                model.setImg(img.attr("src"));
                                Element h2 = element.select("h2").first();
                                model.setTitle(h2.text());
                                Element p = element.select("p").first();
                                model.setDesc(p.text());
                                Element i = element.select("i").first();
                                model.setTime(i.text());
                                models.add(model);
                            }
                            dealSuccessData(models);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }

                    @Override
                    public void failure(int code, String msg) {

                    }
                });
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        NewsDetailActivity.trendTo(mContext, mDatas.get(position));
    }
}
