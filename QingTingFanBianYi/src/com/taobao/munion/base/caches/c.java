package com.taobao.munion.base.caches;

import android.text.TextUtils;
import java.util.Map;

public class c
{
  private static c a;
  private Map<String, String> b;

  public static c a()
  {
    if (a == null);
    try
    {
      if (a == null)
        a = new c();
      return a;
    }
    finally
    {
    }
  }

  public void a(Map<String, String> paramMap)
  {
    this.b = paramMap;
  }

  public boolean a(String paramString)
  {
    int j = 0;
    int i = 0;
    long l2;
    long l3;
    if ((this.b != null) && (this.b.containsKey(paramString)))
    {
      l2 = c(paramString);
      l3 = l2;
    }
    while (true)
    {
      try
      {
        int k = 13 - Long.valueOf(l2).toString().length();
        long l1;
        if (k > 0)
        {
          i = j;
          break label137;
          l3 = l2;
          l1 = l2;
          if (i < Math.abs(k))
          {
            l3 = l2;
            l2 /= 10L;
            i += 1;
            continue;
          }
        }
      }
      catch (Exception localException)
      {
        l1 = l3;
      }
      label137: 
      do
      {
        w localw = b.a().c(paramString);
        if ((localw != null) && (l1 > localw.a()))
          b.a().d(paramString);
        return true;
        return false;
        while (true)
        {
          l1 = l2;
          if (i >= k)
            break;
          l2 *= 10L;
          i += 1;
        }
        l1 = l2;
      }
      while (k >= 0);
    }
  }

  public String b(String paramString)
  {
    try
    {
      if ((this.b != null) && (this.b.size() > 0))
      {
        paramString = (String)this.b.get(paramString);
        return paramString;
      }
    }
    catch (Exception paramString)
    {
      paramString.printStackTrace();
    }
    return null;
  }

  public long c(String paramString)
  {
    if ((this.b == null) || (this.b.size() <= 0))
      return 0L;
    try
    {
      paramString = (String)this.b.get(paramString);
      if (!TextUtils.isEmpty(paramString))
      {
        long l = Long.parseLong(paramString);
        return l;
      }
    }
    catch (Exception paramString)
    {
      return 0L;
    }
    return 0L;
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     com.taobao.munion.base.caches.c
 * JD-Core Version:    0.6.2
 */