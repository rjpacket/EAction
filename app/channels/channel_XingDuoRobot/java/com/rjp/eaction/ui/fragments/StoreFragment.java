package com.rjp.eaction.ui.fragments;


import android.content.Context;
import android.os.Build;
import android.support.v4.app.Fragment;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.rjp.eaction.R;
import com.rjp.eaction.baseAF.BaseFragment;
import com.rjp.eaction.bean.HomeBannerModel;
import com.rjp.eaction.bean.HomeCategoryModel;
import com.rjp.eaction.interfaces.OnCategoryClickListener;
import com.rjp.eaction.network.NetUtils;
import com.rjp.eaction.network.callback.ResponseCallback;
import com.rjp.eaction.ui.listviews.StoreListView;
import com.rjp.eaction.ui.views.IndicatorTabLayout;
import com.rjp.eaction.ui.views.SearchLabelView;
import com.rjp.eaction.util.AppUtils;
import com.rjp.eaction.util.ImageUtils;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.Transformer;
import com.youth.banner.loader.ImageLoader;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

import static com.rjp.eaction.network.UrlConst.URL_HOME_CATEGORY;
import static com.rjp.eaction.network.UrlConst.URL_STORE_CATEGORY;

/**
 * A simple {@link Fragment} subclass.
 */
public class StoreFragment extends BaseFragment {
    @BindView(R.id.store_search_view)
    SearchLabelView storeSearchView;
    @BindView(R.id.home_banner)
    Banner banner;
    @BindView(R.id.indicator_tab_layout)
    IndicatorTabLayout indicatorTabLayout;
    @BindView(R.id.store_list_view)
    StoreListView storeListView;
    private ArrayList<HomeBannerModel> imageUrls;

    public StoreFragment() {
        // Required empty public constructor
    }

    public static StoreFragment getInstance(){
        StoreFragment fragment = new StoreFragment();
        return fragment;
    }

    @Override
    protected void networkReload() {

    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_store;
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
            RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams) storeSearchView.getLayoutParams();
            params.setMargins(0, AppUtils.getStatusBarHeight(mContext), 0, 0);
        }

        initBanner();
        getStoreBanner();
        getStoreCategory();

        indicatorTabLayout.setOnCategoryClickListener(new OnCategoryClickListener() {
            @Override
            public void onCategoryClick(String categoryId) {
                storeListView.requestData(categoryId);
            }
        });
    }

    private void getStoreCategory() {
        new NetUtils.Builder()
                .url(URL_STORE_CATEGORY)
                .context(mContext)
                .build()
                .model(new ResponseCallback<List<HomeCategoryModel>>() {
                    @Override
                    public void success(List<HomeCategoryModel> models) {
                        indicatorTabLayout.setTabData(models);
                        indicatorTabLayout.setClickSelected(0);
                        HomeCategoryModel model = models.get(0);
                        storeListView.requestData(model.getId());
                    }

                    @Override
                    public void failure(String code, String msg) {

                    }
                });
    }

    private void getStoreBanner() {
        new NetUtils.Builder()
                .url("shuffling/findGoods.jhtml")
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
                ImageUtils.load(context, (String) path, imageView);
            }
        });
        banner.setBannerAnimation(Transformer.Default);
        banner.isAutoPlay(true);
        banner.setDelayTime(2000);
        banner.setIndicatorGravity(BannerConfig.CENTER);
    }
}
