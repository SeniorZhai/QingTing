package com.sina.weibo.sdk.api.share;

public abstract interface IWeiboHandler
{
  public static abstract interface Request
  {
    public abstract void onRequest(BaseRequest paramBaseRequest);
  }

  public static abstract interface Response
  {
    public abstract void onResponse(BaseResponse paramBaseResponse);
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     com.sina.weibo.sdk.api.share.IWeiboHandler
 * JD-Core Version:    0.6.2
 */