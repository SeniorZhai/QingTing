package com.sina.weibo.sdk.component;

import android.app.Activity;
import android.graphics.Bitmap;
import android.net.http.SslError;
import android.os.Bundle;
import android.text.TextUtils;
import android.webkit.SslErrorHandler;
import android.webkit.WebView;
import com.sina.weibo.sdk.auth.WeiboAuthListener;
import com.sina.weibo.sdk.utils.Utility;

class ShareWeiboWebViewClient extends WeiboWebViewClient
{
  private static final String RESP_PARAM_CODE = "code";
  private static final String RESP_PARAM_MSG = "msg";
  private static final String RESP_SUCC_CODE = "0";
  private Activity mAct;
  private WeiboAuthListener mListener;
  private ShareRequestParam mShareRequestParam;

  public ShareWeiboWebViewClient(Activity paramActivity, ShareRequestParam paramShareRequestParam)
  {
    this.mAct = paramActivity;
    this.mShareRequestParam = paramShareRequestParam;
    this.mListener = paramShareRequestParam.getAuthListener();
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
    this.mShareRequestParam.sendSdkErrorResponse(this.mAct, paramString1);
    WeiboSdkBrowser.closeBrowser(this.mAct, this.mShareRequestParam.getAuthListenerKey(), null);
  }

  public void onReceivedSslError(WebView paramWebView, SslErrorHandler paramSslErrorHandler, SslError paramSslError)
  {
    if (this.mCallBack != null)
      this.mCallBack.onReceivedSslErrorCallBack(paramWebView, paramSslErrorHandler, paramSslError);
    paramSslErrorHandler.cancel();
    this.mShareRequestParam.sendSdkErrorResponse(this.mAct, "ReceivedSslError");
    WeiboSdkBrowser.closeBrowser(this.mAct, this.mShareRequestParam.getAuthListenerKey(), null);
  }

  public boolean shouldOverrideUrlLoading(WebView paramWebView, String paramString)
  {
    if (this.mCallBack != null)
      this.mCallBack.shouldOverrideUrlLoadingCallBack(paramWebView, paramString);
    if (paramString.startsWith("sinaweibo://browser/close"))
    {
      paramString = Utility.parseUri(paramString);
      if ((!paramString.isEmpty()) && (this.mListener != null))
        this.mListener.onComplete(paramString);
      paramWebView = paramString.getString("code");
      paramString = paramString.getString("msg");
      if (TextUtils.isEmpty(paramWebView))
        this.mShareRequestParam.sendSdkCancleResponse(this.mAct);
      while (true)
      {
        WeiboSdkBrowser.closeBrowser(this.mAct, this.mShareRequestParam.getAuthListenerKey(), null);
        return true;
        if (!"0".equals(paramWebView))
          this.mShareRequestParam.sendSdkErrorResponse(this.mAct, paramString);
        else
          this.mShareRequestParam.sendSdkOkResponse(this.mAct);
      }
    }
    return super.shouldOverrideUrlLoading(paramWebView, paramString);
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     com.sina.weibo.sdk.component.ShareWeiboWebViewClient
 * JD-Core Version:    0.6.2
 */