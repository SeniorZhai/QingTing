package com.talkingdata.pingan.sdk;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.pm.Signature;
import android.os.Build.VERSION;
import java.io.File;

public class i
{
  static PackageManager a;
  static PackageInfo b;

  static void a(Context paramContext)
  {
    a = paramContext.getPackageManager();
    try
    {
      b = a.getPackageInfo(paramContext.getPackageName(), 64);
      return;
    }
    catch (PackageManager.NameNotFoundException paramContext)
    {
    }
  }

  public static String b(Context paramContext)
  {
    return paramContext.getPackageName();
  }

  public static int c(Context paramContext)
  {
    try
    {
      if (b == null)
        a(paramContext);
      int i = b.versionCode;
      return i;
    }
    catch (Exception paramContext)
    {
    }
    return -1;
  }

  public static String d(Context paramContext)
  {
    try
    {
      if (b == null)
        a(paramContext);
      paramContext = b.versionName;
      return paramContext;
    }
    catch (Exception paramContext)
    {
    }
    return "unknown";
  }

  public static long e(Context paramContext)
  {
    try
    {
      if (Integer.valueOf(Build.VERSION.SDK).intValue() < 9)
        return -1L;
      long l = a.a(paramContext);
      return l;
    }
    catch (Exception paramContext)
    {
    }
    return -1L;
  }

  public static long f(Context paramContext)
  {
    try
    {
      if (Integer.valueOf(Build.VERSION.SDK).intValue() < 9)
        return -1L;
      long l = a.b(paramContext);
      return l;
    }
    catch (Exception paramContext)
    {
    }
    return -1L;
  }

  public static long g(Context paramContext)
  {
    try
    {
      if (a == null)
        a(paramContext);
      long l = new File(a.getApplicationInfo(paramContext.getPackageName(), 0).sourceDir).length();
      return l;
    }
    catch (Exception paramContext)
    {
    }
    return -1L;
  }

  public static String h(Context paramContext)
  {
    if (b == null)
      a(paramContext);
    return b.signatures[0].toCharsString();
  }

  public static String i(Context paramContext)
  {
    return paramContext.getApplicationInfo().loadLabel(paramContext.getPackageManager()).toString();
  }

  private static class a
  {
    static long a(Context paramContext)
    {
      try
      {
        long l = i.b.firstInstallTime;
        return l;
      }
      catch (Exception paramContext)
      {
      }
      return -1L;
    }

    static long b(Context paramContext)
    {
      try
      {
        long l = i.b.lastUpdateTime;
        return l;
      }
      catch (Exception paramContext)
      {
      }
      return -1L;
    }
  }

  private static class b
  {
    static long a()
    {
      try
      {
        long l = new File(i.b.applicationInfo.sourceDir).lastModified();
        return l;
      }
      catch (Exception localException)
      {
      }
      return -1L;
    }

    static long b()
    {
      try
      {
        long l = new File(i.b.applicationInfo.sourceDir).lastModified();
        return l;
      }
      catch (Exception localException)
      {
      }
      return -1L;
    }
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     com.talkingdata.pingan.sdk.i
 * JD-Core Version:    0.6.2
 */