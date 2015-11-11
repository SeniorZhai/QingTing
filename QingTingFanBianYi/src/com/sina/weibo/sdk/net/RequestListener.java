package com.sina.weibo.sdk.net;

import com.sina.weibo.sdk.exception.WeiboException;

public abstract interface RequestListener
{
  public abstract void onComplete(String paramString);

  public abstract void onWeiboException(WeiboException paramWeiboException);
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     com.sina.weibo.sdk.net.RequestListener
 * JD-Core Version:    0.6.2
 */