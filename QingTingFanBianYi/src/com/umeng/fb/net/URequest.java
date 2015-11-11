package com.umeng.fb.net;

import org.json.JSONObject;

public abstract class URequest
{
  protected static String GET = "GET";
  protected static String POST = "POST";
  protected String baseUrl;

  public URequest(String paramString)
  {
    this.baseUrl = paramString;
  }

  public String getBaseUrl()
  {
    return this.baseUrl;
  }

  protected String getHttpMethod()
  {
    return POST;
  }

  public void setBaseUrl(String paramString)
  {
    this.baseUrl = paramString;
  }

  public abstract String toGetUrl();

  public abstract JSONObject toJson();
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     com.umeng.fb.net.URequest
 * JD-Core Version:    0.6.2
 */