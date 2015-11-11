package com.sina.weibo.sdk.component;

import android.webkit.WebViewClient;

abstract class WeiboWebViewClient extends WebViewClient
{
  protected BrowserRequestCallBack mCallBack;

  public void setBrowserRequestCallBack(BrowserRequestCallBack paramBrowserRequestCallBack)
  {
    this.mCallBack = paramBrowserRequestCallBack;
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     com.sina.weibo.sdk.component.WeiboWebViewClient
 * JD-Core Version:    0.6.2
 */