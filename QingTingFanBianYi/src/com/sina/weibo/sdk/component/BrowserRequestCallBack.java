package com.sina.weibo.sdk.component;

import android.graphics.Bitmap;
import android.net.http.SslError;
import android.webkit.SslErrorHandler;
import android.webkit.WebView;

abstract interface BrowserRequestCallBack
{
  public abstract void onPageFinishedCallBack(WebView paramWebView, String paramString);

  public abstract void onPageStartedCallBack(WebView paramWebView, String paramString, Bitmap paramBitmap);

  public abstract void onReceivedErrorCallBack(WebView paramWebView, int paramInt, String paramString1, String paramString2);

  public abstract void onReceivedSslErrorCallBack(WebView paramWebView, SslErrorHandler paramSslErrorHandler, SslError paramSslError);

  public abstract boolean shouldOverrideUrlLoadingCallBack(WebView paramWebView, String paramString);
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     com.sina.weibo.sdk.component.BrowserRequestCallBack
 * JD-Core Version:    0.6.2
 */