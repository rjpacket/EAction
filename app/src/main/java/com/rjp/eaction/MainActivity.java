//package com.rjp.eaction;
//
//import android.support.v4.app.Fragment;
//import android.widget.FrameLayout;
//import android.widget.Toast;
//
//import com.rjp.eaction.baseAF.BaseActivity;
//import com.rjp.eaction.ui.home.HomeFragment;
//import com.rjp.eaction.ui.mine.MineFragment;
//import com.rjp.eaction.ui.social.SocialFragment;
//import com.rjp.eaction.network.NetUtils;
//import com.rjp.eaction.network.callback.ResponseCallback;
//import com.rjp.navigationview.NavigationView;
//import com.rjp.navigationview.TabModel;
//
//import java.util.ArrayList;
//
//import butterknife.BindView;
//
//public class MainActivity extends BaseActivity {
//    @BindView(R.id.navigation_view)
//    NavigationView navigationView;
//    @BindView(R.id.main_container)
//    FrameLayout frameLayout;
//
//    @Override
//    protected boolean showTopBar() {
//        return false;
//    }
//
//    @Override
//    protected void handle() {
//        navigationView.setContainerId(R.id.main_container);
//        navigationView.setFragmentManager(getSupportFragmentManager());
//        ArrayList<Fragment> fragments = new ArrayList<>();
//        fragments.add(new HomeFragment());
//        fragments.add(new SocialFragment());
//        fragments.add(new MineFragment());
//        navigationView.setFragments(fragments);
//        ArrayList<TabModel> tabModels = new ArrayList<>();
//        tabModels.add(new TabModel("首页", R.drawable.selector_home_tab_1));
//        tabModels.add(new TabModel("社区", R.drawable.selector_home_tab_2));
//        tabModels.add(new TabModel("我的", R.drawable.selector_home_tab_3));
//        navigationView.setTabs(tabModels);
//    }
//
//    @Override
//    protected void networkReload() {
//        net();
//    }
//
//    @Override
//    protected int getLayoutId() {
//        return R.layout.activity_main;
//    }
//
//    @Override
//    protected String getPageTitle() {
//        return "首页";
//    }
//
//    private void net() {
//        new NetUtils.Builder()
//                .context(this)
//                .url("v2/movie/top250")
//                .param("start", "0")
//                .param("count", "10")
//                .showLoading(true)
//                .tag("main")
//                .build()
//                .model(new ResponseCallback<String>() {
//                    @Override
//                    public void success(String model) {
//                        setNetworkSuccess();
//                    }
//
//                    @Override
//                    public void failure(int code, String msg) {
//                        setNetworkFail(msg);
//                        Toast.makeText(MainActivity.this, msg, Toast.LENGTH_SHORT).show();
//                    }
//                });
//    }
//}
