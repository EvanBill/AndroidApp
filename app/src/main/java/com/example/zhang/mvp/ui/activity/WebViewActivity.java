package com.example.zhang.mvp.ui.activity;

import android.annotation.SuppressLint;
import android.net.http.SslError;
import android.os.Build;
import android.os.Bundle;
import androidx.annotation.Nullable;
import android.view.KeyEvent;
import android.webkit.SslErrorHandler;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.blankj.utilcode.util.NetworkUtils;
import com.example.zhang.R;
import com.example.zhang.app.Constants;
import com.example.zhang.base.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @author zzh
 */
public class WebViewActivity extends BaseActivity {
    @BindView(R.id.wv_web_view)
    WebView wvWebView;
    private final String loadUrl = "https://www.baidu.com";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_view);
        ButterKnife.bind(this);
        setBaseWebSettings(wvWebView);
        wvWebView.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
                //强制使用本WebView加载页面，而不跳转系统浏览器
                view.loadUrl(loadUrl);
                return true;
            }

            @Override
            public void onReceivedSslError(WebView view, SslErrorHandler handler, SslError error) {
                //webView默认是不处理https请求的，页面显示空白，需要进行如下设置：
                //表示等待证书响应
                handler.proceed();
                // handler.cancel();      //表示挂起连接，为默认方式
                // handler.handleMessage(null);    //可做其他处理
            }
        });
        wvWebView.loadUrl(loadUrl);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && wvWebView.canGoBack()) {
            wvWebView.goBack();
            return true;
        } else {
            return super.onKeyDown(keyCode, event);
        }

    }

    /**
     * 设置webView基础信息
     *
     * @param webView webView对象
     */
    @SuppressLint("SetJavaScriptEnabled")
    private void setBaseWebSettings(WebView webView) {
        WebSettings settings = webView.getSettings();
        //如果访问的页面中要与Javascript交互，则webView必须设置支持Javascript
        settings.setJavaScriptEnabled(true);
        // 若加载的 html 里有JS 在执行动画等操作，会造成资源浪费（CPU、电量）
        // 在 onStop 和 onResume 里分别把 setJavaScriptEnabled() 给设置成 false 和 true 即可


        //设置自适应屏幕，两者合用
        //将图片调整到适合webView的大小
        settings.setUseWideViewPort(true);
        // 缩放至屏幕的大小
        settings.setLoadWithOverviewMode(true);

        //缩放操作
        //支持缩放，默认为true。是下面那个的前提。
        settings.setSupportZoom(true);
        //设置内置的缩放控件。若为false，则该WebView不可缩放
        settings.setBuiltInZoomControls(true);
        //隐藏原生的缩放控件
        settings.setDisplayZoomControls(false);

        //其他细节操作
        //设置可以访问文件
        settings.setAllowFileAccess(true);
        //支持通过JS打开新窗口
        settings.setJavaScriptCanOpenWindowsAutomatically(true);
        //支持自动加载图片
        settings.setLoadsImagesAutomatically(true);
        //设置编码格式
        settings.setDefaultTextEncodingName("utf-8");
        if (NetworkUtils.isConnected()) {
            //根据cache-control决定是否从网络上取数据。
            settings.setCacheMode(WebSettings.LOAD_DEFAULT);
        } else {
            //没网，则从本地获取，即离线加载
            settings.setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK);
        }
        // 开启 DOM storage API 功能
        settings.setDomStorageEnabled(true);
        //开启 database storage API 功能
        settings.setDatabaseEnabled(true);
        //开启 Application Caches 功能
        settings.setAppCacheEnabled(true);

        //  每个 Application 只调用一次 WebSettings.setAppCachePath()，WebSettings.setAppCacheMaxSize()
        //设置  Application Caches 缓存目录
        settings.setAppCachePath(Constants.CACHE_DIR_PATH);


        // 特别注意：5.1以上默认禁止了https和http混用，以下方式是开启
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            if (NetworkUtils.isConnected()) {
                //根据cache-control决定是否从网络上取数据。
                settings.setMixedContentMode(WebSettings.LOAD_DEFAULT);
            } else {
                //没网，则从本地获取，即离线加载
                settings.setMixedContentMode(WebSettings.MIXED_CONTENT_ALWAYS_ALLOW);
            }
        }
    }
}
