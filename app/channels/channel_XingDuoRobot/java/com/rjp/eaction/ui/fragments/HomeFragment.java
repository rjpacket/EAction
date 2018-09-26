package com.rjp.eaction.ui.fragments;


import android.content.Context;
import android.os.Build;
import android.support.v4.app.Fragment;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.rjp.eaction.R;
import com.rjp.eaction.baseAF.BaseFragment;
import com.rjp.eaction.bean.HomeBannerModel;
import com.rjp.eaction.bean.HomeCategoryModel;
import com.rjp.eaction.interfaces.OnCategoryClickListener;
import com.rjp.eaction.network.NetUtils;
import com.rjp.eaction.network.callback.ResponseCallback;
import com.rjp.eaction.ui.activitys.SearchActivity;
import com.rjp.eaction.ui.listviews.HomeReadBookListView;
import com.rjp.eaction.ui.views.IndicatorTabLayout;
import com.rjp.eaction.ui.views.SearchLabelView;
import com.rjp.eaction.util.AppUtils;
import com.rjp.eaction.util.ImageUtils;
import com.rjp.eaction.util.LogUtils;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.Transformer;
import com.youth.banner.loader.ImageLoader;

import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

import static com.rjp.eaction.network.UrlConst.URL_HOME_CATEGORY;

/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends BaseFragment {
    @BindView(R.id.home_search_view)
    SearchLabelView homeSearchView;
    @BindView(R.id.home_banner)
    Banner banner;
    @BindView(R.id.home_book_list_view)
    HomeReadBookListView homeReadBookListView;
    @BindView(R.id.indicator_tab_layout)
    IndicatorTabLayout indicatorTabLayout;

    public HomeFragment() {
        // Required empty public constructor
    }

    public static HomeFragment getInstance() {
        HomeFragment testFragment = new HomeFragment();
        return testFragment;
    }

    @Override
    protected void networkReload() {

    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_home;
    }

    @Override
    protected boolean showTopBar() {
        return false;
    }

    @Override
    protected String getPageTitle() {
        return "";
    }

    @Override
    protected void handle() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams) homeSearchView.getLayoutParams();
            params.setMargins(0, AppUtils.getStatusBarHeight(mContext), 0, 0);
        }

        homeSearchView.setCustomOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (v.getId()){
                    case R.id.ll_book_search_label:
                        SearchActivity.trendTo(mContext);
                        break;
                }
            }
        });

        initBanner();
        getHomeBanner();
        getHomeCategory();

        indicatorTabLayout.setOnCategoryClickListener(new OnCategoryClickListener() {
            @Override
            public void onCategoryClick(String categoryId) {
                homeReadBookListView.requestData(categoryId);
            }
        });
    }

    private void getHomeCategory() {
        new NetUtils.Builder()
                .url(URL_HOME_CATEGORY)
                .context(mContext)
                .build()
                .model(new ResponseCallback<List<HomeCategoryModel>>() {
                    @Override
                    public void success(List<HomeCategoryModel> models) {
                        indicatorTabLayout.setTabData(models);
                        indicatorTabLayout.setClickSelected(0);
                        HomeCategoryModel model = models.get(0);
                        homeReadBookListView.requestData(model.getId());
                    }

                    @Override
                    public void failure(String code, String msg) {

                    }
                });
    }

    private void getHomeBanner() {
        new NetUtils.Builder()
                .url("shuffling/findHome.jhtml")
                .context(mContext)
                .build()
                .model(new ResponseCallback<List<HomeBannerModel>>() {
                    @Override
                    public void success(List<HomeBannerModel> models) {
                        banner.setImages(models);
                        banner.start();
                    }

                    @Override
                    public void failure(String code, String msg) {

                    }
                });
    }

    /**
     * 配置banner
     */
    private void initBanner() {
        banner.setBannerStyle(BannerConfig.CIRCLE_INDICATOR);
        banner.setImageLoader(new ImageLoader() {
            @Override
            public void displayImage(Context context, Object path, ImageView imageView) {
                HomeBannerModel model = (HomeBannerModel) path;
                ImageUtils.load(context, model.getPicAddress(), imageView);
            }
        });
        banner.setBannerAnimation(Transformer.Default);
        banner.isAutoPlay(true);
        banner.setDelayTime(2000);
        banner.setIndicatorGravity(BannerConfig.CENTER);
    }
}
