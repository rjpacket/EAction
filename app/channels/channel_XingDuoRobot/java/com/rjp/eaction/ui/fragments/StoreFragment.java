package com.rjp.eaction.ui.fragments;


import android.content.Context;
import android.support.v4.app.Fragment;

import android.widget.ImageView;
import android.widget.RelativeLayout;
import butterknife.BindView;
import com.rjp.eaction.R;
import com.rjp.eaction.baseAF.BaseFragment;
import com.rjp.eaction.bean.HomeBannerModel;
import com.rjp.eaction.network.NetUtils;
import com.rjp.eaction.network.callback.ResponseCallback;
import com.rjp.eaction.ui.views.SearchLabelView;
import com.rjp.eaction.util.AppUtils;
import com.rjp.eaction.util.ImageUtils;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.Transformer;
import com.youth.banner.loader.ImageLoader;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class StoreFragment extends BaseFragment {
    @BindView(R.id.store_search_view)
    SearchLabelView storeSearchView;
    @BindView(R.id.home_banner)
    Banner banner;
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
        RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams) storeSearchView.getLayoutParams();
        params.setMargins(0, AppUtils.getStatusBarHeight(mContext), 0, 0);

        initBanner();
        getStoreBanner();
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
