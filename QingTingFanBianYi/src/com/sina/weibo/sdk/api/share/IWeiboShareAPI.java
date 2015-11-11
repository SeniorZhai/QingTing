package com.sina.weibo.sdk.api.share;

import android.app.Activity;
import android.content.Intent;
import com.sina.weibo.sdk.auth.AuthInfo;
import com.sina.weibo.sdk.auth.WeiboAuthListener;

public abstract interface IWeiboShareAPI
{
  public abstract int getWeiboAppSupportAPI();

  public abstract boolean handleWeiboRequest(Intent paramIntent, IWeiboHandler.Request paramRequest);

  public abstract boolean handleWeiboResponse(Intent paramIntent, IWeiboHandler.Response paramResponse);

  public abstract boolean isSupportWeiboPay();

  public abstract boolean isWeiboAppInstalled();

  public abstract boolean isWeiboAppSupportAPI();

  public abstract boolean launchWeibo(Activity paramActivity);

  public abstract boolean launchWeiboPay(Activity paramActivity, String paramString);

  public abstract boolean registerApp();

  public abstract boolean sendRequest(Activity paramActivity, BaseRequest paramBaseRequest);

  public abstract boolean sendRequest(Activity paramActivity, BaseRequest paramBaseRequest, AuthInfo paramAuthInfo, String paramString, WeiboAuthListener paramWeiboAuthListener);

  public abstract boolean sendResponse(BaseResponse paramBaseResponse);
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     com.sina.weibo.sdk.api.share.IWeiboShareAPI
 * JD-Core Version:    0.6.2
 */