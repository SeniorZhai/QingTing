package com.alipay.sdk.auth;

import android.graphics.Bitmap;
import android.net.http.SslError;
import android.os.Handler;
import android.webkit.SslErrorHandler;
import android.webkit.WebView;
import android.webkit.WebViewClient;

class AuthActivity$7 extends WebViewClient
{
  AuthActivity$7(AuthActivity paramAuthActivity)
  {
  }

  public void onPageFinished(WebView paramWebView, String paramString)
  {
    AuthActivity.h(this.a);
    AuthActivity.g(this.a).removeCallbacks(AuthActivity.f(this.a));
  }

  public void onPageStarted(WebView paramWebView, String paramString, Bitmap paramBitmap)
  {
    AuthActivity.e(this.a);
    AuthActivity.g(this.a).postDelayed(AuthActivity.f(this.a), 30000L);
    super.onPageStarted(paramWebView, paramString, paramBitmap);
  }

  public void onReceivedSslError(WebView paramWebView, SslErrorHandler paramSslErrorHandler, SslError paramSslError)
  {
    if (AuthActivity.d(this.a))
    {
      paramSslErrorHandler.proceed();
      AuthActivity.a(this.a, false);
      return;
    }
    this.a.runOnUiThread(new AuthActivity.7.1(this, paramSslErrorHandler));
  }

  public boolean shouldOverrideUrlLoading(WebView paramWebView, String paramString)
  {
    if (AuthActivity.b(this.a, paramString))
    {
      paramWebView.stopLoading();
      return true;
    }
    return super.shouldOverrideUrlLoading(paramWebView, paramString);
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     com.alipay.sdk.auth.AuthActivity.7
 * JD-Core Version:    0.6.2
 */