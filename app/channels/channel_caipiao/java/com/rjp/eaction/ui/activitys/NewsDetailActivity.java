package com.rjp.eaction.ui.activitys;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.webkit.WebView;
import android.widget.TextView;

import com.alibaba.fastjson.JSONArray;
import com.rjp.eaction.R;
import com.rjp.eaction.baseAF.BaseActivity;
import com.rjp.eaction.bean.HomeNewsModel;
import com.rjp.eaction.network.NetUtils;
import com.rjp.eaction.network.callback.ResponseCallback;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.rjp.eaction.network.retrofit.ApiService.WANGYI_NEWS_URL;

public class NewsDetailActivity extends BaseActivity {


    @BindView(R.id.tv_news_title)
    TextView tvNewsTitle;
    @BindView(R.id.tv_news_tag)
    TextView tvNewsTag;
    @BindView(R.id.tv_news_time)
    TextView tvNewsTime;
    @BindView(R.id.web_view)
    WebView webView;
    private HomeNewsModel model;

    public static void trendTo(Context context, HomeNewsModel homeNewsModel) {
        Intent intent = new Intent(context, NewsDetailActivity.class);
        intent.putExtra("model", homeNewsModel);
        context.startActivity(intent);
    }

    @Override
    protected void networkReload() {

    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_news_detail;
    }

    @Override
    protected String getPageTitle() {
        return "新闻详情";
    }

    @Override
    protected void handle() {
        Intent intent = getIntent();
        if (intent != null && intent.hasExtra("model")) {
            model = (HomeNewsModel) intent.getSerializableExtra("model");
            String content = model.getContent();
            if (!TextUtils.isEmpty(content)) {
                setData(model);
            } else {
                getNewsContent(model.getUrl());
            }
        }
    }

    /**
     * 获取新闻数据
     *
     * @param url
     */
    private void getNewsContent(String url) {
        new NetUtils.Builder()
                .context(mContext)
                .url(url)
                .build()
                .originModel(WANGYI_NEWS_URL, new ResponseCallback<String>() {
                    @Override
                    public void success(String result) {
                        List<HomeNewsModel> models = new ArrayList<>();
                        try {
                            Document document = Jsoup.parse(result);
                            Element element = document.select("div.articleCon").first();
                            model.setContent(element.html());
                            setData(model);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }

                    @Override
                    public void failure(int code, String msg) {

                    }
                });
    }

    private void setData(HomeNewsModel model) {
        tvNewsTitle.setText(model.getTitle());
        String tag = model.getTag();
        if (!TextUtils.isEmpty(tag)) {
            List<String> strings = JSONArray.parseArray(tag, String.class);
            tvNewsTag.setText(strings.get(0));
            tvNewsTag.setVisibility(View.VISIBLE);
        } else {
            tvNewsTag.setVisibility(View.GONE);
        }
        tvNewsTime.setText(model.getNewsTime());
        webView.loadDataWithBaseURL(null, model.getContent().replace("网易", getString(R.string.app_name)), "text/html", "utf-8", null);
    }
}
