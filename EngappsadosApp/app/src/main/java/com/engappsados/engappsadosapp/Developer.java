package com.engappsados.engappsadosapp;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.webkit.WebView;

/**
 * Created by Rodrigo on 22/09/2017.
 */

public class Developer extends AppCompatActivity {
    private String fileLocation;
    WebView mWebView;

    public Developer(String fileLocation) {
        this.fileLocation = fileLocation;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_devs);

        mWebView = (WebView) findViewById(R.id.myBrowser);
        mWebView.getSettings().setJavaScriptEnabled(true);

        /*

        mWebView = (WebView) findViewById(R.id.myBrowser);
        mWebView.getSettings().setJavaScriptEnabled(true);
        mWebView.loadUrl("file:///android_asset/terminosYCondiciones.html");
         */
    }
}
