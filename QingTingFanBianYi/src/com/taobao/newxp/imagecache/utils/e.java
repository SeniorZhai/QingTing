package com.taobao.newxp.imagecache.utils;

import android.os.Build.VERSION;
import android.os.StrictMode;
import android.os.StrictMode.ThreadPolicy.Builder;
import android.os.StrictMode.VmPolicy.Builder;

public class e
{
  public static void a()
  {
    if (c())
    {
      StrictMode.ThreadPolicy.Builder localBuilder = new StrictMode.ThreadPolicy.Builder().detectAll().penaltyLog();
      StrictMode.VmPolicy.Builder localBuilder1 = new StrictMode.VmPolicy.Builder().detectAll().penaltyLog();
      StrictMode.setThreadPolicy(localBuilder.build());
      StrictMode.setVmPolicy(localBuilder1.build());
    }
  }

  public static boolean b()
  {
    return Build.VERSION.SDK_INT >= 8;
  }

  public static boolean c()
  {
    return Build.VERSION.SDK_INT >= 9;
  }

  public static boolean d()
  {
    return Build.VERSION.SDK_INT >= 11;
  }

  public static boolean e()
  {
    return Build.VERSION.SDK_INT >= 12;
  }

  public static boolean f()
  {
    return Build.VERSION.SDK_INT >= 16;
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     com.taobao.newxp.imagecache.utils.e
 * JD-Core Version:    0.6.2
 */