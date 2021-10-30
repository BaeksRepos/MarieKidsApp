package com.mariekids.mariekids_app

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.webkit.CookieManager
import android.webkit.WebChromeClient
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.Toast
import com.mariekids.mariekids_app.databinding.ActivityMainWebViewBinding

class MainWebView : AppCompatActivity() {

    private var backBtnTime: Long = 0;
    private lateinit var webView: WebView;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding = ActivityMainWebViewBinding.inflate(layoutInflater);
        setContentView(binding.root);

        webView = binding.webView;

        webView.webChromeClient  = WebChromeClient();
        webView.webViewClient = WebViewClientClass();
        webView.settings.javaScriptEnabled = true;

        val cookieManager = CookieManager.getInstance();
        cookieManager.setAcceptCookie(true);


       // webView.loadUrl(getString(R.string.marie_url));
        webView.loadUrl("https://nid.naver.com/nidlogin.login?svctype=262144&mode=form&url=https%3A%2F%2Fm.smartstore.naver.com%2Fmarie_kids%2Fsidemenu%3FprevUrl%3D%252Fmarie_kids");
    }

    override fun onBackPressed() {
        val curTime = System.currentTimeMillis();
        val gapTime = curTime - backBtnTime;

        if (webView.canGoBack()) {
            webView.goBack();
        } else if (0 <= gapTime && 2000 >= gapTime) {
            super.onBackPressed()
        } else {
            backBtnTime = curTime;
            Toast.makeText(this, "\'뒤로\' 버튼을 한번 더 누르면 전 화면으로 돌아갑니다.", Toast.LENGTH_SHORT).show()
        }
    }


    // 웹 페이지 이동 클래스
    inner class WebViewClientClass : WebViewClient(){
        override fun shouldOverrideUrlLoading(view: WebView?, url: String?): Boolean {
            if(url != null && (url.startsWith("intent:") || url.startsWith("intent://"))){
                val intent = Intent.parseUri(url, Intent.URI_INTENT_SCHEME);
                val existPackage = packageManager.getLaunchIntentForPackage(intent.`package`!!);
                if(existPackage != null)
                    startActivity(intent)
                else{
                    val marketIntent = Intent(Intent.ACTION_VIEW);
                    marketIntent.data = Uri.parse("market://details?id=" + intent.`package`!!);
                    startActivity(marketIntent);
                }

                return true;
            }
            else if(url != null && url.startsWith("market://")){
                val intent = Intent.parseUri(url, Intent.URI_INTENT_SCHEME);
                if(intent != null)
                    startActivity(intent);
                return true;
            }
            else if(url != null && url.equals("http://m.naver.com/")){
                val chngeUrl = getString(R.string.marie_url);
                view!!.loadUrl(chngeUrl);
                return true;
            }
            view!!.loadUrl(url!!);
            return false;
        }
    }
}