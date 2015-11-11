package com.intowow.sdk.a;

import org.json.JSONObject;

public class l
{
  private String a = null;
  private String b = null;
  private String c = null;
  private String d = null;
  private String e = null;
  private String f = null;
  private long g = 60000L;
  private long h = 15000L;
  private long i = 3000L;

  public static l a(JSONObject paramJSONObject)
  {
    if (paramJSONObject == null)
      return null;
    l locall = new l();
    locall.a = paramJSONObject.optString("id", null);
    locall.b = paramJSONObject.optString("stream", null);
    locall.c = paramJSONObject.optString("pool", null);
    locall.d = paramJSONObject.optString("role", null);
    locall.e = paramJSONObject.optString("region", null);
    locall.f = paramJSONObject.optString("endpoint", null);
    locall.g = paramJSONObject.optLong("flush_interval", 60000L);
    locall.h = paramJSONObject.optLong("tracking_guard_time", 15000L);
    locall.i = paramJSONObject.optLong("effective_view_time", 3000L);
    return locall;
  }

  public String a()
  {
    return this.f;
  }

  public long b()
  {
    return this.g;
  }

  public long c()
  {
    return this.h;
  }

  public long d()
  {
    return this.i;
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     com.intowow.sdk.a.l
 * JD-Core Version:    0.6.2
 */