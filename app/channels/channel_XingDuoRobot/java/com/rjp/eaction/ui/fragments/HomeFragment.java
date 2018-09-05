package com.rjp.eaction.ui.fragments;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
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

import static com.rjp.eaction.network.UrlConst.URL_HOME_CATEGORY;

/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends BaseFragment {
    @BindView(R.id.home_search_view)
    SearchLabelView homeSearchView;
    @BindView(R.id.home_banner)
    Banner banner;
    @BindView(R.id.tv_home_hot_read)
    TextView tvHomeHotRead;
    @BindView(R.id.tv_home_electronic_book)
    TextView tvHomeElectronicBook;
    @BindView(R.id.tv_home_voice_book)
    TextView tvHomeVoiceBook;
    @BindView(R.id.tv_home_fm)
    TextView tvHomeFm;

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
        RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams) homeSearchView.getLayoutParams();
        params.setMargins(0, AppUtils.getStatusBarHeight(mContext), 0, 0);

        initBanner();
        getHomeBanner();
        getHomeCategory();
    }

    private void getHomeCategory() {
        new NetUtils.Builder()
                .url(URL_HOME_CATEGORY)
                .context(mContext)
                .build()
                .model(new ResponseCallback<String>() {
                    @Override
                    public void success(String models) {

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

    @OnClick({R.id.tv_home_hot_read, R.id.tv_home_electronic_book, R.id.tv_home_voice_book, R.id.tv_home_fm})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_home_hot_read:
                resetTabSelectedStatus(tvHomeHotRead, tvHomeElectronicBook, tvHomeVoiceBook, tvHomeFm);
                break;
            case R.id.tv_home_electronic_book:
                resetTabSelectedStatus(tvHomeElectronicBook, tvHomeHotRead, tvHomeVoiceBook, tvHomeFm);
                break;
            case R.id.tv_home_voice_book:
                resetTabSelectedStatus(tvHomeVoiceBook, tvHomeElectronicBook, tvHomeHotRead, tvHomeFm);
                break;
            case R.id.tv_home_fm:
                resetTabSelectedStatus(tvHomeFm, tvHomeElectronicBook, tvHomeVoiceBook, tvHomeHotRead);
                break;
        }
    }

    private void resetTabSelectedStatus(TextView tab1, TextView tab2, TextView tab3, TextView tab4) {
        tab1.setSelected(true);
        tab2.setSelected(false);
        tab3.setSelected(false);
        tab4.setSelected(false);
    }
}
