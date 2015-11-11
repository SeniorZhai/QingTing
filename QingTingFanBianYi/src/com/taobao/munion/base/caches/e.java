package com.taobao.munion.base.caches;

import android.content.Context;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.text.TextUtils;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class e
{
  private static SimpleDateFormat a = new SimpleDateFormat("EEE, dd MMM yyyy HH:mm:ss 'GMT'", Locale.ENGLISH);

  public static int a(String paramString)
  {
    if (TextUtils.isEmpty(paramString))
      return 0;
    try
    {
      int i = Integer.parseInt(paramString);
      return i;
    }
    catch (NumberFormatException paramString)
    {
    }
    return 0;
  }

  public static int a(String paramString1, String paramString2)
  {
    String str = paramString1;
    if (paramString1 == null)
      str = "0";
    paramString1 = paramString2;
    if (paramString2 == null)
      paramString1 = "0";
    paramString2 = str.split("\\.");
    paramString1 = paramString1.split("\\.");
    int m = paramString2.length;
    int n = paramString1.length;
    int i;
    int j;
    if (m > n)
    {
      i = m;
      j = 0;
    }
    while (true)
    {
      if (j >= i)
        break label157;
      if ((j < m) && (j < n))
      {
        k = a(paramString2[j]);
        int i1 = a(paramString1[j]);
        if (k == i1)
          break label148;
        return k - i1;
        i = n;
        break;
      }
      if (m > n);
      for (int k = a(paramString2[j]); k != 0; k = a(paramString1[j]) * -1)
        return k;
      label148: j += 1;
    }
    label157: return 0;
  }

  public static String a(long paramLong)
  {
    return a.format(new Date(paramLong));
  }

  public static boolean a(Context paramContext, String paramString)
  {
    if ((paramContext == null) || (TextUtils.isEmpty(paramString)));
    while (true)
    {
      return false;
      try
      {
        paramContext = paramContext.getPackageManager();
        if (paramContext != null)
        {
          paramContext = paramContext.getPackageInfo(paramString, 0);
          if (paramContext != null)
            return true;
        }
      }
      catch (PackageManager.NameNotFoundException paramContext)
      {
        paramContext.printStackTrace();
      }
    }
    return false;
  }

  public static String b(String paramString)
  {
    if (!TextUtils.isEmpty(paramString))
    {
      int i = paramString.indexOf("charset");
      if ((i != -1) && (paramString.indexOf("=", i) != -1))
      {
        String str = paramString.substring(paramString.indexOf("=", i) + 1);
        i = str.indexOf(";");
        paramString = str;
        if (i != -1)
          paramString = str.substring(0, i).trim();
        return paramString.trim();
      }
    }
    return "";
  }

  public static String c(String paramString)
  {
    if (!TextUtils.isEmpty(paramString))
    {
      int i = paramString.indexOf(";");
      if (i == -1)
        return paramString.trim();
      return paramString.substring(0, i).trim();
    }
    return "";
  }

  public static long d(String paramString)
  {
    if ((!TextUtils.isEmpty(paramString)) && (paramString.indexOf("max-age=") != -1))
    {
      StringBuilder localStringBuilder = new StringBuilder();
      paramString = paramString.substring(8);
      int i = 0;
      while ((i < paramString.length()) && (Character.isDigit(paramString.charAt(i))))
      {
        localStringBuilder.append(paramString.charAt(i));
        i += 1;
      }
      try
      {
        long l = Long.parseLong(localStringBuilder.toString());
        return l * 1000L;
      }
      catch (NumberFormatException paramString)
      {
        paramString.printStackTrace();
      }
    }
    return 0L;
  }

  public static long e(String paramString)
  {
    if (!TextUtils.isEmpty(paramString))
      try
      {
        long l = a.parse(paramString.trim()).getTime();
        return l;
      }
      catch (ParseException paramString)
      {
        paramString.printStackTrace();
      }
    return 0L;
  }

  public static boolean f(String paramString)
  {
    return (!TextUtils.isEmpty(paramString)) && (paramString.toLowerCase().startsWith("image"));
  }

  public static boolean g(String paramString)
  {
    return (!TextUtils.isEmpty(paramString)) && (paramString.equalsIgnoreCase("text/html"));
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     com.taobao.munion.base.caches.e
 * JD-Core Version:    0.6.2
 */