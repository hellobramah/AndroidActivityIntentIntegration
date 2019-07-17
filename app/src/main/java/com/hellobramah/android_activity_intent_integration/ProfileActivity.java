package com.hellobramah.android_activity_intent_integration;

import android.content.DialogInterface;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.http.SslError;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.webkit.SslErrorHandler;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class ProfileActivity extends AppCompatActivity {
    private WebView webView;
    private ActionBar profileActionBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        profileActionBar=getSupportActionBar();
        profileActionBar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#3359DE")));

        webView = (WebView) findViewById(R.id.webview);
        webView.setWebViewClient(new WebViewClient());
        webView.loadUrl("https://andela.com/alc/");

        WebSettings webSettings = webView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        //credits to Unknown @ http://vardhan-justlikethat.blogspot.com/2014/07/android-workaround-for-webview-not.html

        webView.setWebViewClient(new WebViewClient() {
            @Override
            public void onReceivedSslError(final WebView view, final SslErrorHandler handler, SslError error) {
                Log.d("CHECK", "onReceivedSslError");
                AlertDialog.Builder builder = new AlertDialog.Builder(ProfileActivity.this);
                AlertDialog alertDialog = builder.create();
                String message = "Certificate error.";
                switch (error.getPrimaryError()) {
                    case SslError.SSL_UNTRUSTED:
                        message = "The certificate authority is not trusted.";
                        break;
                    case SslError.SSL_EXPIRED:
                        message = "The certificate has expired.";
                        break;
                    case SslError.SSL_IDMISMATCH:
                        message = "The certificate Hostname mismatch.";
                        break;
                    case SslError.SSL_NOTYETVALID:
                        message = "The certificate is not yet valid.";
                        break;
                }
                message += " Do you want to continue anyway?";
                alertDialog.setTitle("SSL Certificate Error");
                alertDialog.setMessage(message);
                alertDialog.setButton(DialogInterface.BUTTON_POSITIVE, "OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Log.d("CHECK", "Button ok pressed");
                        // Ignore SSL certificate errors
                        handler.proceed();
                    }
                });
                alertDialog.setButton(DialogInterface.BUTTON_NEGATIVE, "Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Log.d("CHECK", "Button cancel pressed");
                        handler.cancel();
                    }
                });
                alertDialog.show();
            }
        });
    }
    @Override
    public void onBackPressed() {
        if (webView.canGoBack()) {
            webView.goBack();
        } else {
            super.onBackPressed();
        }
    }
}
