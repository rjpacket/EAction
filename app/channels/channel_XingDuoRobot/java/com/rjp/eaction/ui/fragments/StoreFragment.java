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
        ArrayList<String> imageUrls = new ArrayList<>();
        imageUrls.add("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1535743329138&di=62567ffae5b736e3b2041bc8278a0dc9&imgtype=0&src=http%3A%2F%2Fimg.zcool.cn%2Fcommunity%2F01e4a2577deac20000018c1bdd823a.jpg%401280w_1l_2o_100sh.jpg");
        imageUrls.add("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1535743329138&di=d606e8d04c7a5323159216e64569948c&imgtype=0&src=http%3A%2F%2Fimg.zcool.cn%2Fcommunity%2F01ee55577deabf0000012e7ef4343a.jpg");
        banner.setImages(imageUrls);
        banner.setBannerAnimation(Transformer.Default);
        banner.isAutoPlay(true);
        banner.setDelayTime(2000);
        banner.setIndicatorGravity(BannerConfig.CENTER);
        banner.start();
    }
}
