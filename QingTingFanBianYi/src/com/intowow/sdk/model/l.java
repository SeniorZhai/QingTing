package com.intowow.sdk.model;

import android.util.SparseIntArray;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import org.json.JSONObject;

public class l
{
  private Map<String, a> a = null;

  public static l a(JSONObject paramJSONObject, int paramInt)
  {
    try
    {
      l locall = new l();
      Iterator localIterator = paramJSONObject.keys();
      while (true)
      {
        if (!localIterator.hasNext())
          return locall;
        String str = (String)localIterator.next();
        a locala = a.a(paramJSONObject.getJSONObject(str), paramInt);
        if (locala != null)
          locall.a.put(str, locala);
      }
    }
    catch (Exception paramJSONObject)
    {
    }
    return null;
  }

  public static JSONObject a(l paraml)
  {
    if (paraml == null)
      return null;
    JSONObject localJSONObject;
    try
    {
      localJSONObject = new JSONObject();
      if (paraml.a != null)
      {
        Iterator localIterator = paraml.a.keySet().iterator();
        while (localIterator.hasNext())
        {
          String str = (String)localIterator.next();
          Object localObject = (a)paraml.a.get(str);
          if (localObject != null)
          {
            localObject = a.a((a)localObject);
            if (localObject != null)
              localJSONObject.put(str, localObject);
          }
        }
      }
    }
    catch (Exception paraml)
    {
      return null;
    }
    return localJSONObject;
  }

  public a a(String paramString)
  {
    if (this.a == null)
      return null;
    return (a)this.a.get(paramString);
  }

  public void a(String paramString, int paramInt1, int paramInt2)
  {
    if (this.a == null)
      return;
    if (!this.a.containsKey(paramString))
      this.a.put(paramString, new a());
    ((a)this.a.get(paramString)).a(paramInt1, paramInt2);
  }

  public static class a
  {
    private SparseIntArray a = null;

    public static a a(JSONObject paramJSONObject, int paramInt)
    {
      try
      {
        a locala = new a();
        Iterator localIterator = paramJSONObject.keys();
        while (true)
        {
          if (!localIterator.hasNext())
            return locala;
          String str = (String)localIterator.next();
          int i = Integer.parseInt(str);
          if (i >= paramInt)
            locala.a.put(i, paramJSONObject.getInt(str));
        }
      }
      catch (Exception paramJSONObject)
      {
      }
      return null;
    }

    public static JSONObject a(a parama)
    {
      Object localObject;
      if ((parama == null) || (parama.a == null) || (parama.a.size() == 0))
        localObject = null;
      while (true)
      {
        return localObject;
        try
        {
          JSONObject localJSONObject = new JSONObject();
          int i = 0;
          while (true)
          {
            localObject = localJSONObject;
            if (i >= parama.a.size())
              break;
            localJSONObject.put(String.valueOf(parama.a.keyAt(i)), parama.a.valueAt(i));
            i += 1;
          }
        }
        catch (Exception parama)
        {
        }
      }
      return null;
    }

    public int a()
    {
      int i = 0;
      if (this.a == null)
        return 0;
      int j = 0;
      while (true)
      {
        if (i >= this.a.size())
          return j;
        j += this.a.valueAt(i);
        i += 1;
      }
    }

    public void a(int paramInt1, int paramInt2)
    {
      int i = this.a.get(paramInt1);
      this.a.put(paramInt1, i + paramInt2);
    }

    public String toString()
    {
      if (this.a == null)
        return "";
      StringBuilder localStringBuilder = new StringBuilder();
      localStringBuilder.append("[");
      int i = 0;
      while (true)
      {
        if (i >= this.a.size())
        {
          localStringBuilder.append("]");
          return localStringBuilder.toString();
        }
        localStringBuilder.append(String.format("{%d : %d}", new Object[] { Integer.valueOf(this.a.keyAt(i)), Integer.valueOf(this.a.valueAt(i)) }));
        i += 1;
      }
    }
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     com.intowow.sdk.model.l
 * JD-Core Version:    0.6.2
 */