#### 一、关于手淘的弹出层技术

最早听说这个技术还是16年10月份的时候。

只记得做外卖项目的时候，技术主管说要实现一个新注册用户进入app弹出赠送优惠券的项目。很简单，dialog就能实现对吧。但是，优惠券到底做成什么样的还不清楚，而项目这周上线。也就是说只能预埋页面。那就不好做了，上线了dialog是没办法替换的啊。

技术主管说那就做成H5的页面，随时可以替换。这个简单，在首页的布局上藏一个WebView，请求到H5地址的时候load(String url)。项目按时上线了。

过了些日子，需求要在商家详情页面弹出一些优惠信息。于是拷贝代码，在详情页面再藏一个WebView，原代码改改...

又过些日子，需求又加了，优惠券页面把过期的优惠券弹出来提示一下用户。再藏一个WebView，原代码改改...

这个时候需要改动的地方太多了，对原代码的入侵也多，webview本身问题就多，每改动一个，其它地方就要拷贝拷贝。

但是用过手淘app的都知道，在任何地方都可能会弹出一个活动框，应该不会像我这样每一个地方都加webview。问了一下技术主管，提到了H5的poplayer，不清楚他从哪听来的。

百度了很久才找到一篇大致相关的文章 [客户端还可以这么玩？](https://blog.csdn.net/lily_song_8989/article/details/52228172)

这个文章阅读数还不到300，可以说这个技术真的没什么人关心呐，因为像手淘这样临时业务多的app也很少。

#### 二、仿手淘的poplayer

怎样才能优雅的实现这种效果呢？透明的activity + webview。

这应该是最简单的办法了，而且代码侵入性极小。

```
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
```

整个activity的代码就是一个webview，因为一般的弹框只涉及到下载和跳转，所以只监听了.apk的后缀和非http或https的前缀，有什么其他需要监听的自行添加。

#### 三、透明化的activity

如果不设置透明的activity，效果就是跳转一个页面。设置透明有两个地方，第一，activity

```
    <style name="PopLayerTheme" parent="Theme.AppCompat.Light.NoActionBar">
        <item name="android:windowAnimationStyle">@android:color/transparent</item>
        <item name="android:windowIsTranslucent">true</item>
        <item name="android:windowBackground">@android:color/transparent</item>
        <item name="android:windowFullscreen">false</item>
        <item name="windowNoTitle">true</item>
    </style>
```

写在样式里效果最好，不要全屏，否则会有布局闪动的bug。

第二，webview

```
    webView.setBackgroundColor(0); // 设置背景色
```

给webview设置背景色透明。

#### 四、效果

![poplayer.gif](https://upload-images.jianshu.io/upload_images/5994029-5c9ac6e9ec1430a5.gif?imageMogr2/auto-orient/strip)
