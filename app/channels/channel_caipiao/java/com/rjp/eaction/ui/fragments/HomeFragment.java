package com.rjp.eaction.ui.fragments;


import android.support.v4.app.Fragment;
import com.rjp.eaction.R;
import com.rjp.eaction.baseAF.BaseFragment;
import com.rjp.eaction.bean.HomeNewsModel;
import com.rjp.eaction.network.NetUtils;
import com.rjp.eaction.network.callback.ResponseCallback;
import com.rjp.eaction.swiper.ImageSwiper;
import com.rjp.eaction.swiper.SwiperView;
import com.rjp.eaction.ui.listviews.HomeNewsListView;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

import static com.rjp.eaction.network.retrofit.ApiService.WANGYI_NEWS_URL;

/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends BaseFragment {

    @BindView(R.id.swiper_view)
    SwiperView<String> swiperView;
    @BindView(R.id.home_news_list_view)
    HomeNewsListView newsListView;
    private ArrayList<String> newsModels;

    public HomeFragment() {
        // Required empty public constructor
    }

    @Override
    protected void networkReload() {

    }

    @Override
    protected void handle() {
        swiperView.setLeftSwiper(new ImageSwiper(mContext));
        swiperView.setMidSwiper(new ImageSwiper(mContext));
        swiperView.setRightSwiper(new ImageSwiper(mContext));

        newsListView.requestData("toutiao");
        requestSwiperData();
    }

    private void requestSwiperData() {
        new NetUtils.Builder()
                .context(mContext)
                .url("zx/wap_list.html?cache=" + System.currentTimeMillis() + "&pageNo=1&tp=jingcai&ajax=1")
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
                            ArrayList<String> datas = new ArrayList<>();
                            for (HomeNewsModel model : models) {
                                datas.add(model.getImg());
                            }
                            swiperView.setDatas(datas);
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
    protected boolean showTopBar() {
        return false;
    }

    @Override
    protected String getPageTitle() {
        return "首页";
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_home_caipiao;
    }
}
