package com.rjp.eaction.web;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.view.ViewGroup;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.FrameLayout;

import com.rjp.eaction.R;
import com.rjp.eaction.baseAF.BaseActivity;
import com.rjp.eaction.util.AppUtils;

import butterknife.BindView;

/**
 * 加载H5的通用页面
 */
public class WebActivity extends BaseActivity {
    public static final String WEB_TITLE = "web_title";
    public static final String WEB_URL = "web_url";

    @BindView(R.id.web_container)
    FrameLayout webContainer;

    /**
     * 意图跳转
     * @param mContext
     * @param title
     * @param url
     */
    public static void trendTo(Context mContext, String title, String url){
        Intent intent = new Intent(mContext, WebActivity.class);
        intent.putExtra(WEB_TITLE, title);
        intent.putExtra(WEB_URL, url);
        mContext.startActivity(intent);
    }

    @Override
    protected void networkReload() {

    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_web;
    }

    @Override
    protected void handle() {
        Intent intent = getIntent();
        if(intent != null && intent.hasExtra(WEB_URL)){
            String title = intent.getStringExtra(WEB_TITLE);
            setTopTitle(title);
            addWebView(intent.getStringExtra(WEB_URL));
        }
        AndroidBug5497Workaround.assistActivity(this);
    }

    /**
     * 增加页面层叠
     * @param url
     */
    private void addWebView(String url) {
        WebView webview = new WebView(mContext);
        FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        webview.setLayoutParams(params);
        initWebSetting(webview.getSettings());
        webview.loadUrl(url);
        webview.setWebViewClient(new MyWebviewClient());
        webview.setWebChromeClient(new WebChromeClient());
        webContainer.addView(webview);
    }

    /**
     * 配置webview
     * @param webSettings
     */
    private void initWebSetting(WebSettings webSettings) {
        webSettings.setUseWideViewPort(true);
        webSettings.setLoadWithOverviewMode(true);
        webSettings.setSaveFormData(true);
        webSettings.setJavaScriptEnabled(true);
        webSettings.setBuiltInZoomControls(true);
        webSettings.setSupportZoom(true);
        String cacheDirPath = getCacheDir().getAbsolutePath() + "EActionWeb";
        webSettings.setAppCachePath(cacheDirPath);
        webSettings.setAppCacheEnabled(true);
        webSettings.setAllowFileAccess(true);
        webSettings.setDomStorageEnabled(true);
        webSettings.setDatabaseEnabled(true);
        webSettings.setCacheMode(WebSettings.LOAD_DEFAULT);
        webSettings.setDefaultTextEncodingName("utf-8");
        webSettings.setPluginState(WebSettings.PluginState.ON);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            webSettings.setMixedContentMode(WebSettings.MIXED_CONTENT_ALWAYS_ALLOW);
        }
    }

    public class MyWebviewClient extends WebViewClient {
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            if(!url.startsWith("http")){
                AppUtils.browserCheck(mContext, url);
                return true;
            }
            addWebView(url);
            return true;
        }
    }

    @Override
    public void onBackPressed() {
        int childCount = webContainer.getChildCount();
        if (childCount > 1) {
            WebView webView = (WebView) webContainer.getChildAt(childCount - 1);
            webContainer.removeView(webView);
            webView.clearCache(true);
            webView.destroy();
            webView = null;
        } else {
            super.onBackPressed();
        }
    }
}
