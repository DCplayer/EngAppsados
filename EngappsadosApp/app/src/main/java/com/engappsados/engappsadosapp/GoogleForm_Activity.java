package com.engappsados.engappsadosapp;

import android.annotation.TargetApi;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class GoogleForm_Activity extends AppCompatActivity {
    //atributos
    WebView mWebView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_google_form_);
        //crea vista de la web view
        mWebView = (WebView) findViewById(R.id.navegadorEncuesta);
        mWebView.getSettings().setJavaScriptEnabled(true);
        mWebView.setWebViewClient(new MyBrowser());
        // Enable responsive layout
        mWebView.getSettings().setUseWideViewPort(true);
        // Zoom out if the content width is greater than the width of the viewport
        mWebView.getSettings().setLoadWithOverviewMode(true);
        // Cargar la URL
        mWebView.loadUrl("https://goo.gl/forms/aXF96L1lFr8EhPUD3");
        
    }

// Manages the behavior when URLs are loaded
    private class MyBrowser extends WebViewClient {
        @SuppressWarnings("deprecation")
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {

            if (url.contains("formResponse")){
                view.loadUrl(url);
                finish();  // close activity
            }
            else{
                view.loadUrl(url);
            }
            //formResponse
            return true;
        }
/*
        @TargetApi(Build.VERSION_CODES.M)
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
            view.loadUrl(request.getUrl().toString());
            return true;
        }
        */
    }

}
