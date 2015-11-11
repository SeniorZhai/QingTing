package com.sina.weibo.sdk.component;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.http.SslError;
import android.os.Bundle;
import android.webkit.SslErrorHandler;
import android.webkit.WebView;
import com.sina.weibo.sdk.auth.AuthInfo;
import com.sina.weibo.sdk.auth.WeiboAuthListener;
import com.sina.weibo.sdk.exception.WeiboAuthException;
import com.sina.weibo.sdk.utils.Utility;

class AuthWeiboWebViewClient extends WeiboWebViewClient
{
  private boolean isCallBacked = false;
  private Activity mAct;
  private AuthRequestParam mAuthRequestParam;
  private WeiboAuthListener mListener;

  public AuthWeiboWebViewClient(Activity paramActivity, AuthRequestParam paramAuthRequestParam)
  {
    this.mAct = paramActivity;
    this.mAuthRequestParam = paramAuthRequestParam;
    this.mListener = this.mAuthRequestParam.getAuthListener();
  }

  private void handleRedirectUrl(String paramString)
  {
    paramString = Utility.parseUrl(paramString);
    String str1 = paramString.getString("error");
    String str2 = paramString.getString("error_code");
    String str3 = paramString.getString("error_description");
    if ((str1 == null) && (str2 == null))
      if (this.mListener != null)
        this.mListener.onComplete(paramString);
    while (this.mListener == null)
      return;
    this.mListener.onWeiboException(new WeiboAuthException(str2, str1, str3));
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
    if ((paramString.startsWith(this.mAuthRequestParam.getAuthInfo().getRedirectUrl())) && (!this.isCallBacked))
    {
      this.isCallBacked = true;
      handleRedirectUrl(paramString);
      paramWebView.stopLoading();
      WeiboSdkBrowser.closeBrowser(this.mAct, this.mAuthRequestParam.getAuthListenerKey(), null);
      return;
    }
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
    if (paramString.startsWith("sms:"))
    {
      paramWebView = new Intent("android.intent.action.VIEW");
      paramWebView.putExtra("address", paramString.replace("sms:", ""));
      paramWebView.setType("vnd.android-dir/mms-sms");
      this.mAct.startActivity(paramWebView);
      return true;
    }
    if (paramString.startsWith("sinaweibo://browser/close"))
    {
      if (this.mListener != null)
        this.mListener.onCancel();
      WeiboSdkBrowser.closeBrowser(this.mAct, this.mAuthRequestParam.getAuthListenerKey(), null);
      return true;
    }
    return super.shouldOverrideUrlLoading(paramWebView, paramString);
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     com.sina.weibo.sdk.component.AuthWeiboWebViewClient
 * JD-Core Version:    0.6.2
 */