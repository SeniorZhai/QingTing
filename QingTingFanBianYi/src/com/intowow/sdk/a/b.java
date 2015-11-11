package com.intowow.sdk.a;

import com.intowow.sdk.j.h;
import com.intowow.sdk.model.ADProfile.a;
import com.intowow.sdk.model.ADProfile.j;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONObject;

public class b
{
  private boolean a = false;
  private String b = null;
  private String c = null;
  private int d = 2;
  private int e = 1000000;
  private int f = 7;
  private long g = 600000L;
  private long h = 15000L;
  private long i = 3600000L;
  private long j = 300000L;
  private List<c> k = null;
  private Map<String, f> l = null;
  private Map<String, g> m = null;
  private Map<String, e> n = null;
  private Map<String, b> o = null;
  private Map<String, b> p = null;

  public static b a(JSONObject paramJSONObject)
  {
    int i2 = 0;
    if (paramJSONObject == null)
      return null;
    b localb = new b();
    localb.a = paramJSONObject.optBoolean("enabled", false);
    localb.b = paramJSONObject.optString("ad_list_base_url", null);
    localb.c = paramJSONObject.optString("prefetch_hint", null);
    localb.g = paramJSONObject.optLong("ad_list_check_interval", 600000L);
    localb.d = paramJSONObject.optInt("stream_serving_min_pos", 2);
    localb.e = paramJSONObject.optInt("stream_serving_max_pos", 1000000);
    localb.f = paramJSONObject.optInt("stream_serving_freq", 7);
    localb.h = paramJSONObject.optLong("serving_guard_time", 15000L);
    localb.i = paramJSONObject.optLong("open_splash_guard_time", 3600000L);
    localb.j = paramJSONObject.optLong("section_splash_guard_time", 300000L);
    JSONArray localJSONArray = paramJSONObject.optJSONArray("group_serving_configs");
    int i1;
    if (localJSONArray != null)
      i1 = 0;
    while (true)
    {
      if (i1 >= localJSONArray.length())
      {
        localJSONArray = paramJSONObject.optJSONArray("placement_group_request_guard_time");
        if (localJSONArray != null)
        {
          i1 = 0;
          if (i1 < localJSONArray.length())
            break label292;
        }
        localJSONArray = paramJSONObject.optJSONArray("placement_request_guard_time");
        if (localJSONArray != null)
        {
          i1 = 0;
          if (i1 < localJSONArray.length())
            break label330;
        }
        localJSONArray = paramJSONObject.optJSONArray("placement_group_dismiss_time");
        if (localJSONArray != null)
        {
          i1 = i2;
          if (i1 < localJSONArray.length())
            break label368;
        }
        a(paramJSONObject.optJSONArray("placement_ad_display_control"), localb.o);
        a(paramJSONObject.optJSONArray("placement_group_ad_display_control"), localb.p);
        return localb;
      }
      try
      {
        while (true)
        {
          Object localObject = c.a(localJSONArray.getJSONObject(i1));
          if (localObject != null)
            localb.k.add(localObject);
          label283: i1 += 1;
          break;
          try
          {
            while (true)
            {
              label292: localObject = f.a(localJSONArray.getJSONObject(i1));
              if (localObject != null)
                localb.l.put(((f)localObject).a, localObject);
              label321: i1 += 1;
              break;
              try
              {
                while (true)
                {
                  label330: localObject = g.a(localJSONArray.getJSONObject(i1));
                  if (localObject != null)
                    localb.m.put(((g)localObject).a, localObject);
                  label359: i1 += 1;
                  break;
                  try
                  {
                    label368: localObject = e.a(localJSONArray.getJSONObject(i1));
                    if (localObject != null)
                      localb.n.put(((e)localObject).a, localObject);
                    label397: i1 += 1;
                  }
                  catch (Exception localException1)
                  {
                    break label397;
                  }
                }
              }
              catch (Exception localException2)
              {
                break label359;
              }
            }
          }
          catch (Exception localException3)
          {
            break label321;
          }
        }
      }
      catch (Exception localException4)
      {
        break label283;
      }
    }
  }

  private static void a(JSONArray paramJSONArray, Map<String, b> paramMap)
  {
    int i1;
    if (paramJSONArray != null)
      i1 = 0;
    while (true)
    {
      if (i1 >= paramJSONArray.length())
        return;
      try
      {
        b localb = b.a(paramJSONArray.getJSONObject(i1));
        if (localb != null)
          paramMap.put(localb.a, localb);
        label40: i1 += 1;
      }
      catch (Exception localException)
      {
        break label40;
      }
    }
  }

  public b a(String paramString)
  {
    if (paramString != null)
      return (b)this.o.get(paramString);
    return null;
  }

  public boolean a()
  {
    return this.a;
  }

  public b b(String paramString)
  {
    if (paramString != null)
      return (b)this.p.get(paramString);
    return null;
  }

  public String b()
  {
    return this.b;
  }

  public long c(String paramString)
  {
    Iterator localIterator = this.k.iterator();
    c localc;
    do
    {
      if (!localIterator.hasNext())
        return this.h;
      localc = (c)localIterator.next();
    }
    while (!localc.a.equals(paramString));
    return localc.b;
  }

  public String c()
  {
    return this.c;
  }

  public long d()
  {
    return this.g;
  }

  public e d(String paramString)
  {
    if (paramString != null)
      return (e)this.n.get(paramString);
    return null;
  }

  public long e()
  {
    return this.i;
  }

  public long e(String paramString)
  {
    if (this.l.containsKey(paramString))
      return ((f)this.l.get(paramString)).b;
    return 0L;
  }

  public long f()
  {
    return this.j;
  }

  public long f(String paramString)
  {
    if (this.m.containsKey(paramString))
      return ((g)this.m.get(paramString)).b;
    return 0L;
  }

  public static class a
  {
    public ADProfile.a a = null;
    public int b = -1;

    public static a a(JSONObject paramJSONObject)
    {
      if (paramJSONObject == null)
        return null;
      a locala = new a();
      try
      {
        locala.a = ADProfile.a.valueOf(paramJSONObject.getString("type").toUpperCase());
        locala.b = paramJSONObject.optInt("value");
        return locala;
      }
      catch (Exception paramJSONObject)
      {
      }
      return null;
    }
  }

  public static class b
  {
    public String a = null;
    public Map<ADProfile.j, b.a> b = new HashMap();

    public static b a(JSONObject paramJSONObject)
    {
      if (paramJSONObject == null);
      JSONArray localJSONArray;
      b localb;
      do
      {
        return null;
        localJSONArray = paramJSONObject.names();
        localb = new b();
        localb.a = paramJSONObject.optString("name");
      }
      while ((localb.a == null) || (localJSONArray == null));
      int i = 0;
      while (true)
      {
        if (i >= localJSONArray.length())
          return localb;
        try
        {
          String str = localJSONArray.getString(i);
          if (!str.equals("name"))
            localb.b.put(ADProfile.j.valueOf(str.toUpperCase()), b.a.a(paramJSONObject.getJSONObject(str)));
        }
        catch (Exception localException)
        {
          h.a(localException);
        }
        i += 1;
      }
    }

    public b.a a(ADProfile.j paramj)
    {
      return (b.a)this.b.get(paramj);
    }
  }

  public static class c
  {
    public String a = null;
    public long b = 15000L;

    public static c a(JSONObject paramJSONObject)
    {
      if (paramJSONObject == null)
        return null;
      try
      {
        c localc = new c();
        localc.a = paramJSONObject.getString("group");
        localc.b = paramJSONObject.getLong("serving_guard_time");
        return localc;
      }
      catch (Exception paramJSONObject)
      {
      }
      return null;
    }
  }

  public static class d
  {
    public String a = null;
    public long b = 15000L;
  }

  public static class e extends b.d
  {
    protected String[] c = null;

    public static e a(JSONObject paramJSONObject)
    {
      if (paramJSONObject == null);
      while (true)
      {
        return null;
        try
        {
          e locale = new e();
          locale.a = paramJSONObject.getString("name");
          locale.b = paramJSONObject.getLong("value");
          if (paramJSONObject.has("types"))
          {
            paramJSONObject = paramJSONObject.getJSONArray("types");
            locale.c = new String[paramJSONObject.length()];
            int i = 0;
            while (true)
            {
              if (i >= paramJSONObject.length())
                return locale;
              locale.c[i] = paramJSONObject.getString(i);
              i += 1;
            }
          }
        }
        catch (Exception paramJSONObject)
        {
        }
      }
      return null;
    }

    public String[] a()
    {
      return this.c;
    }
  }

  public static class f extends b.d
  {
    public static f a(JSONObject paramJSONObject)
    {
      if (paramJSONObject == null)
        return null;
      try
      {
        f localf = new f();
        localf.a = paramJSONObject.getString("name");
        localf.b = paramJSONObject.getLong("value");
        return localf;
      }
      catch (Exception paramJSONObject)
      {
      }
      return null;
    }
  }

  public static class g extends b.d
  {
    public static g a(JSONObject paramJSONObject)
    {
      if (paramJSONObject == null)
        return null;
      try
      {
        g localg = new g();
        localg.a = paramJSONObject.getString("name");
        localg.b = paramJSONObject.getLong("value");
        return localg;
      }
      catch (Exception paramJSONObject)
      {
      }
      return null;
    }
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     com.intowow.sdk.a.b
 * JD-Core Version:    0.6.2
 */