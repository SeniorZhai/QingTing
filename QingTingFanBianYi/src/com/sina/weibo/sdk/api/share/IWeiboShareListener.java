package com.sina.weibo.sdk.api.share;

import com.sina.weibo.sdk.auth.Oauth2AccessToken;
import com.sina.weibo.sdk.exception.WeiboException;

public abstract interface IWeiboShareListener
{
  public abstract void onAuthorizeCancel();

  public abstract void onAuthorizeComplete(Oauth2AccessToken paramOauth2AccessToken);

  public abstract void onAuthorizeException(WeiboException paramWeiboException);

  public abstract void onTokenError(String paramString);
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     com.sina.weibo.sdk.api.share.IWeiboShareListener
 * JD-Core Version:    0.6.2
 */