package com.tencent.weibo.sdk.android.component.sso;

public abstract interface OnAuthListener
{
  public abstract void onAuthFail(int paramInt, String paramString);

  public abstract void onAuthPassed(String paramString, WeiboToken paramWeiboToken);

  public abstract void onWeiBoNotInstalled();

  public abstract void onWeiboVersionMisMatch();
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     com.tencent.weibo.sdk.android.component.sso.OnAuthListener
 * JD-Core Version:    0.6.2
 */