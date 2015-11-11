package com.sina.weibo.sdk.component;

import android.app.Activity;
import android.graphics.Bitmap;
import android.net.http.SslError;
import android.os.Bundle;
import android.webkit.SslErrorHandler;
import android.webkit.WebView;
import com.sina.weibo.sdk.auth.WeiboAuthListener;
import com.sina.weibo.sdk.utils.Utility;

class WidgetWeiboWebViewClient extends WeiboWebViewClient
{
  private Activity mAct;
  private WeiboAuthListener mListener;
  private WidgetRequestParam.WidgetRequestCallback mWidgetCallback;
  private WidgetRequestParam mWidgetRequestParam;

  public WidgetWeiboWebViewClient(Activity paramActivity, WidgetRequestParam paramWidgetRequestParam)
  {
    this.mAct = paramActivity;
    this.mWidgetRequestParam = paramWidgetRequestParam;
    this.mWidgetCallback = paramWidgetRequestParam.getWidgetRequestCallback();
    this.mListener = paramWidgetRequestParam.getAuthListener();
  }

  public void onPageFinished(WebView paramWebView, String paramString)
  {
    if (this.mCallBack != null)
      this.mCallBack.onPageFinishedCallBack(paramWebView, paramString);
    super.onPageFinished(paramWebView, paramString);
  }

  public void onPageStarted(WebView paramWebView, String paramString, Bitmap paramBitmap)
  {
    if (this.mCallBack != null)
      this.mCallBack.onPageStartedCallBack(paramWebView, paramString, paramBitmap);
    super.onPageStarted(paramWebView, paramString, paramBitmap);
  }

  public void onReceivedError(WebView paramWebView, int paramInt, String paramString1, String paramString2)
  {
    if (this.mCallBack != null)
      this.mCallBack.onReceivedErrorCallBack(paramWebView, paramInt, paramString1, paramString2);
    super.onReceivedError(paramWebView, paramInt, paramString1, paramString2);
  }

  public void onReceivedSslError(WebView paramWebView, SslErrorHandler paramSslErrorHandler, SslError paramSslError)
  {
    if (this.mCallBack != null)
      this.mCallBack.onReceivedSslErrorCallBack(paramWebView, paramSslErrorHandler, paramSslError);
    super.onReceivedSslError(paramWebView, paramSslErrorHandler, paramSslError);
  }

  public boolean shouldOverrideUrlLoading(WebView paramWebView, String paramString)
  {
    if (this.mCallBack != null)
      this.mCallBack.shouldOverrideUrlLoadingCallBack(paramWebView, paramString);
    boolean bool = paramString.startsWith("sinaweibo://browser/close");
    if ((paramString.startsWith("sinaweibo://browser/close")) || (paramString.startsWith("sinaweibo://browser/datatransfer")))
    {
      paramWebView = Utility.parseUri(paramString);
      if ((!paramWebView.isEmpty()) && (this.mListener != null))
        this.mListener.onComplete(paramWebView);
      if (this.mWidgetCallback != null)
        this.mWidgetCallback.onWebViewResult(paramString);
      if (bool)
        WeiboSdkBrowser.closeBrowser(this.mAct, this.mWidgetRequestParam.getAuthListenerKey(), this.mWidgetRequestParam.getWidgetRequestCallbackKey());
      return true;
    }
    return super.shouldOverrideUrlLoading(paramWebView, paramString);
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     com.sina.weibo.sdk.component.WidgetWeiboWebViewClient
 * JD-Core Version:    0.6.2
 */