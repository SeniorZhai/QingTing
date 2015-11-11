package com.intowow.sdk.a;

import org.json.JSONObject;

public class h
{
  public long a = 0L;
  public String b = null;

  public static h a(JSONObject paramJSONObject)
  {
    if (paramJSONObject == null)
      return null;
    h localh = new h();
    localh.a = paramJSONObject.optLong("updated_time", 0L);
    localh.b = paramJSONObject.optString("url", null);
    return localh;
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     com.intowow.sdk.a.h
 * JD-Core Version:    0.6.2
 */