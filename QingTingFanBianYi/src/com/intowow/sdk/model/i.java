package com.intowow.sdk.model;

import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONObject;

public class i
{
  private long a = 0L;
  private List<g> b = null;
  private Map<String, g> c = null;

  public static i a(JSONObject paramJSONObject)
  {
    i locali;
    int j;
    int i;
    do
      try
      {
        locali = new i();
        locali.a = paramJSONObject.getLong("updated_time");
        paramJSONObject = paramJSONObject.getJSONArray("groups");
        j = paramJSONObject.length();
        i = 0;
        continue;
        g localg = g.a(paramJSONObject.getJSONObject(i));
        if (localg != null)
          locali.a(localg);
        i += 1;
      }
      catch (Exception paramJSONObject)
      {
        return null;
      }
    while (i < j);
    return locali;
  }

  public long a()
  {
    return this.a;
  }

  public g a(String paramString)
  {
    Iterator localIterator;
    if ((this.b != null) && (paramString != null))
      localIterator = this.b.iterator();
    g localg;
    do
    {
      if (!localIterator.hasNext())
        return null;
      localg = (g)localIterator.next();
    }
    while (!localg.a().equals(paramString));
    return localg;
  }

  public void a(g paramg)
  {
    this.b.add(paramg);
    Iterator localIterator = paramg.c().iterator();
    while (true)
    {
      if (!localIterator.hasNext())
        return;
      f localf = (f)localIterator.next();
      this.c.put(localf.a(), paramg);
    }
  }

  public g b(String paramString)
  {
    if (this.c != null)
      return (g)this.c.get(paramString);
    return null;
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     com.intowow.sdk.model.i
 * JD-Core Version:    0.6.2
 */