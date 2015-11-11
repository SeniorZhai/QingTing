package com.sina.weibo.sdk.api.share;

import android.content.Context;
import com.sina.weibo.sdk.WeiboAppManager.WeiboInfo;
import com.sina.weibo.sdk.api.WeiboMessage;
import com.sina.weibo.sdk.api.WeiboMultiMessage;

abstract interface IVersionCheckHandler
{
  public abstract boolean checkRequest(Context paramContext, WeiboAppManager.WeiboInfo paramWeiboInfo, WeiboMessage paramWeiboMessage);

  public abstract boolean checkRequest(Context paramContext, WeiboAppManager.WeiboInfo paramWeiboInfo, WeiboMultiMessage paramWeiboMultiMessage);

  public abstract boolean checkResponse(Context paramContext, String paramString, WeiboMessage paramWeiboMessage);

  public abstract boolean checkResponse(Context paramContext, String paramString, WeiboMultiMessage paramWeiboMultiMessage);
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     com.sina.weibo.sdk.api.share.IVersionCheckHandler
 * JD-Core Version:    0.6.2
 */