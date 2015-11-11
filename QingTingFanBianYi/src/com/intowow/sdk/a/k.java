package com.intowow.sdk.a;

import org.json.JSONObject;

public class k
{
  private long a = 314572800L;
  private long b = 52428800L;
  private long c = 21600000L;

  public static k a(JSONObject paramJSONObject)
  {
    if (paramJSONObject == null)
      return null;
    k localk = new k();
    localk.a = paramJSONObject.optLong("preserved_space_bytes", 314572800L);
    localk.b = paramJSONObject.optLong("max_usage", 52428800L);
    localk.c = paramJSONObject.optLong("minimum_ttl", 21600000L);
    return localk;
  }

  public long a()
  {
    return this.a;
  }

  public long b()
  {
    return this.b;
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     com.intowow.sdk.a.k
 * JD-Core Version:    0.6.2
 */