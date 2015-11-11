package com.sina.weibo.sdk.exception;

public class WeiboDialogException extends WeiboException
{
  private static final long serialVersionUID = 1L;
  private int mErrorCode;
  private String mFailingUrl;

  public WeiboDialogException(String paramString1, int paramInt, String paramString2)
  {
    super(paramString1);
    this.mErrorCode = paramInt;
    this.mFailingUrl = paramString2;
  }

  public int getErrorCode()
  {
    return this.mErrorCode;
  }

  public String getFailingUrl()
  {
    return this.mFailingUrl;
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     com.sina.weibo.sdk.exception.WeiboDialogException
 * JD-Core Version:    0.6.2
 */