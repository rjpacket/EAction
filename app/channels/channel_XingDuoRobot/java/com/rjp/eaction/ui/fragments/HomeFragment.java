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
    }

    private void getHomeBanner() {
        new NetUtils.Builder()
                .url("shuffling/findHome.jhtml")
                .context(mContext)
                .build()
                .model(new ResponseCallback<String>() {
                    @Override
                    public void success(String model) {

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
