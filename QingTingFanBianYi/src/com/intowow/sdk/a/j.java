package com.intowow.sdk.a;

import com.intowow.sdk.model.ADProfile.i;
import org.json.JSONObject;

public class j
{
  public long a = 0L;
  public long b = 3600000L;
  public b c = null;
  public c d = null;
  public l e = null;
  public i f = null;
  public k g = null;
  public h h = null;
  public f i = null;
  public g j = null;
  public a k = null;
  public d l = null;

  public static j a(JSONObject paramJSONObject)
  {
    j localj = new j();
    localj.a = paramJSONObject.optLong("updated_time", 0L);
    localj.b = paramJSONObject.optLong("check_interval", 3600000L);
    localj.c = b.a(paramJSONObject.optJSONObject("ad_serving_config"));
    localj.d = c.a(paramJSONObject.optJSONObject("asset_config"));
    localj.e = l.a(paramJSONObject.optJSONObject("tracking_config"));
    localj.f = i.a(paramJSONObject.optJSONObject("prefetch_config"));
    localj.h = h.a(paramJSONObject.optJSONObject("placement_hierarchy"));
    localj.g = k.a(paramJSONObject.optJSONObject("storage_config"));
    localj.i = f.a(paramJSONObject.optJSONObject("device_level_config"));
    localj.j = g.a(paramJSONObject.optJSONObject("geographic_config"));
    localj.k = a.a(paramJSONObject.optJSONObject("ad_preview_config"));
    localj.l = d.a(paramJSONObject.optJSONArray("blocked_formats"));
    return localj;
  }

  public b.b a(com.intowow.sdk.model.g paramg, String paramString)
  {
    if (this.c == null)
      paramString = null;
    do
    {
      b.b localb;
      do
      {
        return paramString;
        localb = this.c.a(paramString);
        paramString = localb;
      }
      while (localb != null);
      paramString = localb;
    }
    while (paramg == null);
    return this.c.b(paramg.a());
  }

  public b.e a(String paramString)
  {
    if (this.c == null)
      return null;
    return this.c.d(paramString);
  }

  public String a()
  {
    if (this.k != null)
      return this.k.a();
    return null;
  }

  public boolean a(ADProfile.i parami)
  {
    return (this.l != null) && (this.l.a(parami));
  }

  public long b(String paramString)
  {
    if (this.c == null)
      return 0L;
    return this.c.e(paramString);
  }

  public boolean b()
  {
    if (this.c == null)
      return false;
    return this.c.a();
  }

  public long c(String paramString)
  {
    if (this.c == null)
      return 0L;
    return this.c.f(paramString);
  }

  public String c()
  {
    if (this.c == null)
      return null;
    return this.c.b();
  }

  public String d()
  {
    if (this.c == null)
      return null;
    return this.c.c();
  }

  public long e()
  {
    if (this.c == null)
      return 600000L;
    return this.c.d();
  }

  public String f()
  {
    if (this.d == null)
      return null;
    return this.d.a;
  }

  public long g()
  {
    if (this.c == null)
      return 3600000L;
    return this.c.e();
  }

  public long h()
  {
    if (this.c == null)
      return 300000L;
    return this.c.f();
  }

  public long i()
  {
    if (this.h == null)
      return 0L;
    return this.h.a;
  }

  public String j()
  {
    if (this.h == null)
      return null;
    return this.h.b;
  }

  public long k()
  {
    if (this.g == null)
      return 314572800L;
    return this.g.a();
  }

  public long l()
  {
    if (this.g == null)
      return 52428800L;
    return this.g.b();
  }

  public long m()
  {
    if (this.j == null)
      return 3600000L;
    return this.j.a;
  }

  public String n()
  {
    if (this.j == null)
      return null;
    return this.j.b;
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     com.intowow.sdk.a.j
 * JD-Core Version:    0.6.2
 */