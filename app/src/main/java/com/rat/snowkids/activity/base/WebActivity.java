package com.rat.snowkids.activity.base;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.TextView;
import android.widget.Toast;

import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.snowkids.snowkids.R;

public class WebActivity extends BaseActivity {
    public static final String INTENT_MARKET_TYPE = "market_type";
    public static final int INTENT_MARKET_TYPE_JD = 1;
    public static final int INTENT_MARKET_TYPE_TB = 2;
    private static final String MARKET_URL_JD = "http://www.jd.com";
    private static final String MARKET_URL_TB = "http://www.taobao.com";

    @ViewInject(R.id.top_name)
    private TextView topTitleView;
    @ViewInject(R.id.top_left)
    private TextView topLeftView;

    @ViewInject(R.id.webview)
    private WebView webView;
    @ViewInject(R.id.loadingView)
    private View loadingView;

    private int type;
    private String url;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web);
        // 基础框架初始化
        ViewUtils.inject(this);//xUtils框架注解注入view和事件
        type = getIntent().getIntExtra(INTENT_MARKET_TYPE, 0);
        initView();
    }

    /**
     * 初始化界面
     */
    public void initView() {
        topLeftView.setVisibility(View.VISIBLE);
        topLeftView.setOnClickListener(this);
        // 浏览器视图自定义
        WebSettings webSettings = webView.getSettings();
        webSettings.setJavaScriptCanOpenWindowsAutomatically(true);
        webSettings.setUseWideViewPort(true);//关键点
        webSettings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);
        webSettings.setDisplayZoomControls(false);
        webSettings.setJavaScriptEnabled(true); // 设置支持javascript脚本
        webSettings.setAllowFileAccess(true); // 允许访问文件
        webSettings.setBuiltInZoomControls(true); // 设置显示缩放按钮
        webSettings.setSupportZoom(true); // 支持缩放
        webSettings.setLoadWithOverviewMode(true);
        //覆盖WebView默认使用第三方或系统默认浏览器打开网页的行为，使网页用WebView打开
        webView.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                //返回值是true的时候控制去WebView打开，为false调用系统浏览器或第三方浏览器
                view.loadUrl(url);
                return true;
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
                loadingView.setVisibility(View.GONE);
            }

            @Override
            public void onReceivedError(WebView view, int errorCode, String description, String failingUrl) {
                super.onReceivedError(view, errorCode, description, failingUrl);
                loadingView.setVisibility(View.GONE);
                Toast.makeText(getApplicationContext(), "加载失败：code：" + errorCode, Toast.LENGTH_LONG).show();
            }

            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                loadingView.setVisibility(View.VISIBLE);
                super.onPageStarted(view, url, favicon);
            }
        });

        switch (type) {
            // 京东
            case INTENT_MARKET_TYPE_JD:
                topTitleView.setText(R.string.snowkids_market_jingdong);
                url = MARKET_URL_JD;
                break;
            // 淘宝
            case INTENT_MARKET_TYPE_TB:
                topTitleView.setText(R.string.snowkids_market_taobao);
                url = MARKET_URL_TB;
                break;
        }
        // 加载数据
        initData();
    }

    /**
     * 初始化数据
     */
    public void initData() {
        webView.loadUrl(url);
    }


    @Override
    public void onClick(View v) {
        Intent intent;
        switch (v.getId()) {
            case R.id.top_left:
                finish();
            default:
                break;
        }
    }
}