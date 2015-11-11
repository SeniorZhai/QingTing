package com.intowow.sdk.a;

import java.util.Iterator;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONObject;

public class f
{
  public List<a> a = null;
  public int b = 3;

  public static f a(JSONObject paramJSONObject)
  {
    if (paramJSONObject == null)
      return null;
    f localf = new f();
    localf.b = paramJSONObject.optInt("tablet_level", 3);
    paramJSONObject = paramJSONObject.optJSONArray("levels");
    int i = 0;
    while (true)
    {
      if (i >= paramJSONObject.length())
        return localf;
      a locala = a.a(paramJSONObject.optJSONObject(i));
      if (locala != null)
        localf.a.add(locala);
      i += 1;
    }
  }

  public int a(int paramInt)
  {
    Iterator localIterator = this.a.iterator();
    a locala;
    do
    {
      if (!localIterator.hasNext())
        return 1;
      locala = (a)localIterator.next();
    }
    while ((locala.b > paramInt) || (paramInt >= locala.c));
    return locala.a;
  }

  static class a
  {
    public int a = -1;
    public int b = 0;
    public int c = 10000;

    public static a a(JSONObject paramJSONObject)
    {
      if (paramJSONObject == null)
        return null;
      try
      {
        a locala = new a();
        locala.a = paramJSONObject.getInt("level");
        locala.b = paramJSONObject.optInt("min_dpi", 0);
        locala.c = paramJSONObject.optInt("max_dpi", 10000);
        return locala;
      }
      catch (Exception paramJSONObject)
      {
      }
      return null;
    }
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     com.intowow.sdk.a.f
 * JD-Core Version:    0.6.2
 */