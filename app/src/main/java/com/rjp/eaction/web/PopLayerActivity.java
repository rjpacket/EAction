package com.rjp.eaction.web;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.ViewGroup;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.FrameLayout;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PopLayerActivity extends Activity {

    private WebView webView;

    public static void start(Context mContext, String url) {
        Intent intent = new Intent(mContext, PopLayerActivity.class);
        intent.putExtra("pop_url", url);
        mContext.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        webView = new WebView(this);
        webView.setBackgroundColor(0); // 设置背景色
        FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        webView.setLayoutParams(params);
        initWebSetting(webView.getSettings());
        webView.setWebChromeClient(new WebChromeClient());
        webView.setWebViewClient(new MyWebviewClient());

        setContentView(webView);

        Intent intent = getIntent();
        if(intent != null && intent.hasExtra("pop_url")){
            String pop_url = intent.getStringExtra("pop_url");
            webView.loadUrl(pop_url);
        }
    }

    public class MyWebviewClient extends WebViewClient {
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            if(url.endsWith(".apk")){
                String reg = ".*(https://.*\\.apk).*";
                Pattern pattern = Pattern.compile(reg);
                Matcher matcher = pattern.matcher(url);
                if(matcher.find()){
                    String group = matcher.group(1);
                    openByBrowser(group);
                }
                return true;
            } else if (!url.startsWith("http")) {
                openByBrowser(url);
                return true;
            }
            return super.shouldOverrideUrlLoading(view, url);
        }

        /**
         * 通过浏览器打开一个url
         * @param url
         */
        private void openByBrowser(String url) {
            try {
                Intent intent = new Intent();
                intent.setAction(Intent.ACTION_VIEW);
                intent.setData(Uri.parse(url));
                startActivity(intent);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 配置webview
     *
     * @param webSettings
     */
    private void initWebSetting(WebSettings webSettings) {
        /**支持Js**/
        webSettings.setJavaScriptEnabled(true);

        /**设置自适应屏幕，两者合用**/
        //将图片调整到适合webview的大小
        webSettings.setUseWideViewPort(true);
        // 缩放至屏幕的大小
        webSettings.setLoadWithOverviewMode(true);

        /**缩放操作**/
        // 是否支持画面缩放，默认不支持  易导致闪退问题
//        webSettings.setBuiltInZoomControls(true);
//        webSettings.setSupportZoom(true);
        // 是否显示缩放图标，默认显示
        webSettings.setDisplayZoomControls(false);
        // 设置网页内容自适应屏幕大小
        webSettings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);

        /**设置允许JS弹窗**/
        webSettings.setJavaScriptCanOpenWindowsAutomatically(true);
        webSettings.setDomStorageEnabled(true);

        /**关闭webview中缓存**/
        webSettings.setCacheMode(WebSettings.LOAD_NO_CACHE);
        /**设置可以访问文件 **/
        webSettings.setAllowFileAccess(true);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
            webSettings.setAllowFileAccessFromFileURLs(true);
            webSettings.setAllowUniversalAccessFromFileURLs(true);
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            webSettings.setMixedContentMode(WebSettings.MIXED_CONTENT_ALWAYS_ALLOW);
        }
        webSettings.setPluginState(WebSettings.PluginState.ON);
    }

    @Override
    public void onPause() {
        super.onPause();
        if(webView != null){
            webView.onPause();
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        if(webView != null){
            webView.onResume();
        }
    }

    @Override
    public void onDestroy() {
        if(webView != null){
            webView.destroy();
            webView = null;
        }
        super.onDestroy();
    }
}
