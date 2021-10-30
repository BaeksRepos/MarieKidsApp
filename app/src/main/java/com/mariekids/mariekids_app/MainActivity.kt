package com.mariekids.mariekids_app

import android.graphics.Bitmap
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.webkit.WebResourceError
import android.webkit.WebResourceRequest

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    interface WebViewListener{
        fun onPageStarted(url:String?, favicon:Bitmap?);
        fun onPageFinished(url:String?);
        fun onLoadResources(url:String?);
        fun shouldOverrideUrlLoading(request:WebResourceRequest?);
        fun onReceivedError(request:WebResourceRequest?, error:WebResourceError?);
    }
}