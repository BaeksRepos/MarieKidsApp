package com.mariekids.mariekids_app

import android.content.Context
import android.graphics.Bitmap
import android.os.Build
import android.util.AttributeSet
import android.webkit.WebChromeClient
import android.webkit.WebSettings
import android.webkit.WebView
import android.webkit.WebViewClient

class CustomWebView(context: Context, attrs:AttributeSet, defStyle:Int) : WebView(context, attrs, defStyle) {
    init {

        with(settings) {
            userAgentString += "Android_inApp";
            javaScriptEnabled = true;
            javaScriptCanOpenWindowsAutomatically = true;
            useWideViewPort = true;
            loadWithOverviewMode = true
            builtInZoomControls = false;
            cacheMode = WebSettings.LOAD_NO_CACHE;
            domStorageEnabled = true;
            allowFileAccess = true;
            defaultTextEncodingName = "UTF-8"
            setSupportMultipleWindows(true);
            databaseEnabled = true;

            if (Build.VERSION.SDK_INT > Build.VERSION_CODES.LOLLIPOP)
                mixedContentMode = WebSettings.MIXED_CONTENT_ALWAYS_ALLOW;
        }

        //webViewClient = CustomWebVi
        webChromeClient = WebChromeClient();
    }

    private var mWebViewListener: MainActivity.WebViewListener? = null;

    fun setWebViewListener(webViewListener: MainActivity.WebViewListener){
        this.mWebViewListener = webViewListener;
    }

    // https://0391kjy.tistory.com/48 참고
    inner class CustomWebViewClient : WebViewClient(){
        override fun onPageStarted(view: WebView?, url: String?, favicon: Bitmap?) {
            super.onPageStarted(view, url, favicon)
            mWebViewListener?.onPageStarted(url, favicon);
        }
    }



}