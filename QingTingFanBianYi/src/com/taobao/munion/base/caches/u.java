package com.taobao.munion.base.caches;

import android.net.Uri;
import android.net.Uri.Builder;
import android.text.TextUtils;
import java.util.HashMap;
import java.util.Map;

public class u
{
  public static final String a = "url";
  public static final String b = "response-code";
  public static final String c = "content-type";
  public static final String d = "content-length";
  public static final String e = "location";
  public static final String f = "last-modified";
  public static final String g = "expires";
  public static final String h = "date";
  public static final String i = "set-cookie";
  public static final String j = "cache-control";
  public static final String k = "etag";
  public static final String l = "If-Modified-Since";
  public static final String m = "If-None-Match";
  public static final String n = "Mozilla/5.0 (Linux; U;Android munion-h5-sdk httpclient;)";
  private static Map<String, String> o = new HashMap();

  static
  {
    o[] arrayOfo = o.values();
    int i2 = arrayOfo.length;
    int i1 = 0;
    while (i1 < i2)
    {
      o localo = arrayOfo[i1];
      o.put(localo.a(), localo.b());
      i1 += 1;
    }
  }

  public static String a(String paramString1, String paramString2)
  {
    if ((paramString1 == null) || (paramString2 == null))
      return null;
    return Uri.parse(paramString1).getQueryParameter(paramString2);
  }

  public static String a(String paramString1, String paramString2, String paramString3)
  {
    if ((paramString1 == null) || (TextUtils.isEmpty(paramString2)));
    Uri localUri;
    do
    {
      return paramString1;
      localUri = Uri.parse(paramString1);
    }
    while (localUri.getQueryParameter(paramString2) != null);
    paramString1 = localUri.buildUpon();
    paramString1.appendQueryParameter(paramString2, paramString3);
    return paramString1.toString();
  }

  public static boolean a(String paramString)
  {
    paramString = e(paramString);
    return (o.a.a().equals(paramString)) || (o.b.a().equals(paramString));
  }

  public static boolean b(String paramString)
  {
    return f(paramString).startsWith("image");
  }

  public static boolean c(String paramString)
  {
    if (TextUtils.isEmpty(paramString));
    do
    {
      return false;
      paramString = Uri.parse(paramString).getPath();
    }
    while ((paramString == null) || ((!paramString.endsWith("." + o.h.a())) && (!paramString.endsWith("." + o.i.a())) && (!TextUtils.isEmpty(paramString)) && (!"/".equals(paramString))));
    return true;
  }

  public static Map<String, String> d(String paramString)
  {
    if (TextUtils.isEmpty(paramString))
      return null;
    HashMap localHashMap = new HashMap();
    int i1 = paramString.indexOf("?");
    if (i1 != -1)
    {
      Object localObject = paramString.substring(i1 + 1);
      paramString = (String)localObject;
      if (((String)localObject).contains("#"))
        paramString = ((String)localObject).substring(0, ((String)localObject).indexOf("#"));
      paramString = paramString.split("&");
      int i2 = paramString.length;
      i1 = 0;
      if (i1 < i2)
      {
        localObject = paramString[i1].split("=");
        if (localObject.length < 2)
          localHashMap.put(localObject[0], "");
        while (true)
        {
          i1 += 1;
          break;
          localHashMap.put(localObject[0], localObject[1]);
        }
      }
    }
    return localHashMap;
  }

  public static String e(String paramString)
  {
    if (TextUtils.isEmpty(paramString))
      return "";
    paramString = Uri.parse(paramString).getPath();
    if (paramString != null)
    {
      int i1 = paramString.lastIndexOf(".");
      if (i1 != -1)
        return paramString.substring(i1 + 1);
    }
    return "";
  }

  public static String f(String paramString)
  {
    paramString = e(paramString);
    String str = (String)o.get(paramString);
    paramString = str;
    if (str == null)
      paramString = "";
    return paramString;
  }

  public static boolean g(String paramString)
  {
    boolean bool2 = false;
    boolean bool1 = bool2;
    if (!TextUtils.isEmpty(paramString))
      if (!paramString.toLowerCase().startsWith("http://"))
      {
        bool1 = bool2;
        if (!paramString.toLowerCase().startsWith("https://"));
      }
      else
      {
        bool1 = true;
      }
    return bool1;
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     com.taobao.munion.base.caches.u
 * JD-Core Version:    0.6.2
 */