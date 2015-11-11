package com.alipay.sdk.app;

import android.graphics.Bitmap;
import android.net.http.SslError;
import android.os.Handler;
import android.text.TextUtils;
import android.webkit.SslErrorHandler;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import java.net.URLDecoder;

class H5AuthActivity$7 extends WebViewClient
{
  H5AuthActivity$7(H5AuthActivity paramH5AuthActivity)
  {
  }

  public void onPageFinished(WebView paramWebView, String paramString)
  {
    H5AuthActivity.h(this.a);
    H5AuthActivity.g(this.a).removeCallbacks(H5AuthActivity.f(this.a));
  }

  public void onPageStarted(WebView paramWebView, String paramString, Bitmap paramBitmap)
  {
    H5AuthActivity.e(this.a);
    H5AuthActivity.g(this.a).postDelayed(H5AuthActivity.f(this.a), 30000L);
    super.onPageStarted(paramWebView, paramString, paramBitmap);
  }

  public void onReceivedSslError(WebView paramWebView, SslErrorHandler paramSslErrorHandler, SslError paramSslError)
  {
    if (H5AuthActivity.d(this.a))
    {
      paramSslErrorHandler.proceed();
      H5AuthActivity.a(this.a, false);
      return;
    }
    this.a.runOnUiThread(new H5AuthActivity.7.1(this, paramSslErrorHandler));
  }

  public boolean shouldOverrideUrlLoading(WebView paramWebView, String paramString)
  {
    if ((TextUtils.equals(paramString, "sdklite://h5quit")) || (TextUtils.equals(paramString, "http://m.alipay.com/?action=h5quit")))
    {
      Result.a(Result.b());
      this.a.finish();
      return true;
    }
    if (paramString.startsWith("sdklite://h5quit?result="))
      try
      {
        paramWebView = paramString.substring(paramString.indexOf("sdklite://h5quit?result=") + 24);
        int i = Integer.parseInt(paramWebView.substring(paramWebView.lastIndexOf("&end_code=") + 10));
        if (i == ResultStatus.a.a())
        {
          paramWebView = URLDecoder.decode(paramString);
          paramWebView = paramWebView.substring(paramWebView.indexOf("sdklite://h5quit?result=") + 24, paramWebView.lastIndexOf("&end_code="));
          paramString = ResultStatus.a(i);
          Result.a(Result.a(paramString.a(), paramString.b(), paramWebView));
        }
        while (true)
        {
          paramWebView = new H5AuthActivity.7.2(this);
          this.a.runOnUiThread(paramWebView);
          return true;
          paramWebView = ResultStatus.a(ResultStatus.b.a());
          Result.a(Result.a(paramWebView.a(), paramWebView.b(), ""));
        }
      }
      catch (Exception paramWebView)
      {
        while (true)
          Result.a(Result.c());
      }
    paramWebView.loadUrl(paramString);
    return true;
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     com.alipay.sdk.app.H5AuthActivity.7
 * JD-Core Version:    0.6.2
 */