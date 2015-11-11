package com.intowow.sdk.a;

import org.json.JSONObject;

public class g
{
  public long a = 3600000L;
  public String b = null;

  public static g a(JSONObject paramJSONObject)
  {
    if (paramJSONObject == null)
      return null;
    g localg = new g();
    localg.a = paramJSONObject.optLong("check_interval", 3600000L);
    localg.b = paramJSONObject.optString("endpoint", null);
    return localg;
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     com.intowow.sdk.a.g
 * JD-Core Version:    0.6.2
 */