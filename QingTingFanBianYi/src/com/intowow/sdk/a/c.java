package com.intowow.sdk.a;

import org.json.JSONObject;

public class c
{
  public String a = null;
  public long b = 0L;

  public static c a(JSONObject paramJSONObject)
  {
    if (paramJSONObject == null)
      return null;
    c localc = new c();
    localc.a = paramJSONObject.optString("url", null);
    localc.b = paramJSONObject.optLong("updated_time", 0L);
    return localc;
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     com.intowow.sdk.a.c
 * JD-Core Version:    0.6.2
 */