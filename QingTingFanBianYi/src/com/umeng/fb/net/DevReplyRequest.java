package com.umeng.fb.net;

import org.json.JSONObject;

public class DevReplyRequest extends URequest
{
  public String mUrl;

  public DevReplyRequest(String paramString)
  {
    super(paramString);
    this.mUrl = paramString;
  }

  public String getHttpMethod()
  {
    return GET;
  }

  public String toGetUrl()
  {
    return this.mUrl;
  }

  public JSONObject toJson()
  {
    return null;
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     com.umeng.fb.net.DevReplyRequest
 * JD-Core Version:    0.6.2
 */