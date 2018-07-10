package com.rjp.eaction.ui.home;


import android.support.v4.app.Fragment;
import android.widget.ListView;

import com.rjp.commonadapter.CommonAdapter;
import com.rjp.commonadapter.ViewHolder;
import com.rjp.eaction.R;
import com.rjp.eaction.baseAF.BaseFragment;
import com.rjp.eaction.swiper.ImageSwiper;
import com.rjp.eaction.swiper.SwiperView;

import java.util.ArrayList;

import butterknife.BindView;

/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends BaseFragment {

    @BindView(R.id.swiper_view)
    SwiperView<String> swiperView;
    @BindView(R.id.news_list_view)
    ListView newsListView;
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
        ArrayList<String> datas = new ArrayList<>();
        datas.add("https://img.streetop.com/1710241618545645538.jpg");
        datas.add("https://img.streetop.com/1804131729278089036.jpg");
        datas.add("https://img.streetop.com/1801151845405184711.jpg");
        datas.add("https://img.streetop.com/1805141703528136246.jpg");
        datas.add("https://img.streetop.com/1805241411233323748.jpg");
        swiperView.setDatas(datas);

        newsModels = new ArrayList<>();
        newsListView.setAdapter(new CommonAdapter<String>(mContext, R.layout.activity_web, newsModels) {
            @Override
            protected void convert(ViewHolder viewHolder, String item, int position) {

            }
        });
    }

    @Override
    protected String getPageTitle() {
        return "首页";
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_home;
    }
}
