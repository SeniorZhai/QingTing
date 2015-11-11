package com.sina.weibo.sdk.api.share;

import android.content.Context;

public class WeiboShareSDK
{
  public static IWeiboShareAPI createWeiboAPI(Context paramContext, String paramString)
  {
    return createWeiboAPI(paramContext, paramString, false);
  }

  public static IWeiboShareAPI createWeiboAPI(Context paramContext, String paramString, boolean paramBoolean)
  {
    return new WeiboShareAPIImpl(paramContext, paramString, false);
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     com.sina.weibo.sdk.api.share.WeiboShareSDK
 * JD-Core Version:    0.6.2
 */