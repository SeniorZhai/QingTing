package com.intowow.sdk.a;

import com.intowow.sdk.model.ADProfile.c.a;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONObject;

public class i
{
  public long a = 10800000L;
  public long b = 259200000L;
  public long c = 21600000L;
  public List<a> d = new ArrayList();
  public int e = 15;
  public Map<String, d> f = null;
  public d g = null;
  public Map<String, c> h = null;

  public static i a(JSONObject paramJSONObject)
  {
    int j = 0;
    if (paramJSONObject == null)
      return null;
    i locali = new i();
    locali.b = paramJSONObject.optLong("behavior_window", 259200000L);
    locali.c = paramJSONObject.optLong("forecast", 21600000L);
    locali.a = paramJSONObject.optLong("background_fetch_interval", 10800000L);
    locali.e = paramJSONObject.optInt("high_battery_level_threshold", 15);
    JSONArray localJSONArray = paramJSONObject.optJSONArray("background_fetch_constraints");
    int i;
    if (localJSONArray != null)
      i = 0;
    while (true)
    {
      if (i >= localJSONArray.length())
      {
        localJSONArray = paramJSONObject.optJSONArray("foreground_fetch_policies");
        if (localJSONArray != null)
        {
          i = 0;
          label105: if (i < localJSONArray.length())
            break label181;
        }
        localJSONArray = paramJSONObject.optJSONArray("group_policies");
        if (localJSONArray != null)
        {
          i = j;
          if (i < localJSONArray.length())
            break label219;
        }
        locali.g = d.a(paramJSONObject.optJSONObject("default_group_policy"));
        return locali;
      }
      try
      {
        locali.d.add(a.valueOf(localJSONArray.getString(i)));
        label172: i += 1;
        continue;
        label181: Object localObject = c.a(localJSONArray.optJSONObject(i));
        if (localObject != null)
          locali.h.put(((c)localObject).a, localObject);
        i += 1;
        break label105;
        label219: localObject = d.a(localJSONArray.optJSONObject(i));
        if (localObject != null)
          locali.f.put(((d)localObject).a, localObject);
        i += 1;
      }
      catch (Exception localException)
      {
        break label172;
      }
    }
  }

  public d a(String paramString)
  {
    d locald = (d)this.f.get(paramString);
    paramString = locald;
    if (locald == null)
      paramString = this.g;
    return paramString;
  }

  public static enum a
  {
  }

  public static enum b
  {
  }

  public static class c
  {
    public String a = null;
    public List<ADProfile.c.a> b = null;

    public static c a(JSONObject paramJSONObject)
    {
      if (paramJSONObject == null)
        return null;
      try
      {
        c localc = new c();
        localc.a = paramJSONObject.getString("name");
        paramJSONObject = paramJSONObject.getJSONArray("value");
        int i = 0;
        while (true)
        {
          if (i >= paramJSONObject.length())
            return localc;
          ADProfile.c.a locala = ADProfile.c.a.valueOf(paramJSONObject.get(i).toString().toUpperCase());
          localc.b.add(locala);
          i += 1;
        }
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
    public List<i.e> b = null;

    public static d a(JSONObject paramJSONObject)
    {
      if (paramJSONObject == null)
        return null;
      try
      {
        d locald = new d();
        locald.a = paramJSONObject.getString("name");
        paramJSONObject = paramJSONObject.getJSONArray("policies");
        int i = 0;
        while (true)
        {
          if (i >= paramJSONObject.length())
            return locald;
          i.e locale = i.e.a(paramJSONObject.getJSONObject(i));
          if (locale != null)
            locald.b.add(locale);
          i += 1;
        }
      }
      catch (Exception paramJSONObject)
      {
      }
      return null;
    }

    public i.e a(int paramInt)
    {
      if (this.b == null)
        return null;
      Iterator localIterator = this.b.iterator();
      Object localObject = null;
      while (true)
      {
        if (!localIterator.hasNext())
          return localObject;
        i.e locale = (i.e)localIterator.next();
        if (paramInt >= locale.c)
          localObject = locale;
      }
    }
  }

  public static class e
  {
    public int a = 0;
    public int b = 1;
    public int c = 0;

    public static e a(JSONObject paramJSONObject)
    {
      if (paramJSONObject == null)
        return null;
      e locale = new e();
      locale.a = paramJSONObject.optInt("priority", 0);
      locale.b = paramJSONObject.optInt("depth", 1);
      locale.c = paramJSONObject.optInt("threshold", 0);
      return locale;
    }
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     com.intowow.sdk.a.i
 * JD-Core Version:    0.6.2
 */