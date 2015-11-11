package com.taobao.munion.base.volley.a;

import com.taobao.munion.base.volley.b.a;
import com.taobao.munion.base.volley.i;
import java.util.Date;
import java.util.Map;
import org.apache.http.impl.cookie.DateParseException;
import org.apache.http.impl.cookie.DateUtils;

public class f
{
  public static long a(String paramString)
  {
    try
    {
      long l = DateUtils.parseDate(paramString).getTime();
      return l;
    }
    catch (DateParseException paramString)
    {
    }
    return 0L;
  }

  public static b.a a(i parami)
  {
    long l5 = System.currentTimeMillis();
    Map localMap = parami.c;
    long l2 = 0L;
    long l4 = 0L;
    long l1 = 0L;
    int j = 0;
    Object localObject1 = (String)localMap.get("Date");
    if (localObject1 != null)
      l2 = a((String)localObject1);
    localObject1 = (String)localMap.get("Cache-Control");
    long l3 = l1;
    int k;
    int i;
    if (localObject1 != null)
    {
      k = 1;
      localObject1 = ((String)localObject1).split(",");
      i = 0;
    }
    while (true)
    {
      l3 = l1;
      j = k;
      Object localObject2;
      if (i < localObject1.length)
      {
        localObject2 = localObject1[i].trim();
        if ((((String)localObject2).equals("no-cache")) || (((String)localObject2).equals("no-store")))
          return null;
        if (!((String)localObject2).startsWith("max-age="));
      }
      try
      {
        l3 = Long.parseLong(((String)localObject2).substring(8));
        for (l1 = l3; ; l1 = 0L)
          do
          {
            i += 1;
            break;
          }
          while ((!((String)localObject2).equals("must-revalidate")) && (!((String)localObject2).equals("proxy-revalidate")));
        localObject1 = (String)localMap.get("Expires");
        l1 = l4;
        if (localObject1 != null)
          l1 = a((String)localObject1);
        localObject1 = (String)localMap.get("ETag");
        if (j != 0);
        for (l1 = l3 * 1000L + l5; ; l1 = l1 - l2 + l5)
        {
          localObject2 = new b.a();
          ((b.a)localObject2).a = parami.b;
          ((b.a)localObject2).b = ((String)localObject1);
          ((b.a)localObject2).e = l1;
          ((b.a)localObject2).d = ((b.a)localObject2).e;
          ((b.a)localObject2).c = l2;
          ((b.a)localObject2).f = localMap;
          return localObject2;
          if ((l2 <= 0L) || (l1 < l2))
            break;
        }
      }
      catch (Exception localException)
      {
        while (true)
        {
          continue;
          l1 = 0L;
        }
      }
    }
  }

  public static String a(Map<String, String> paramMap)
  {
    paramMap = (String)paramMap.get("Content-Type");
    if (paramMap != null)
    {
      paramMap = paramMap.split(";");
      int i = 1;
      while (i < paramMap.length)
      {
        String[] arrayOfString = paramMap[i].trim().split("=");
        if ((arrayOfString.length == 2) && (arrayOfString[0].equals("charset")))
          return arrayOfString[1];
        i += 1;
      }
    }
    return "ISO-8859-1";
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     com.taobao.munion.base.volley.a.f
 * JD-Core Version:    0.6.2
 */