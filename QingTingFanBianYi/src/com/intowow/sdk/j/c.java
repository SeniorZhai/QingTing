package com.intowow.sdk.j;

import android.content.Context;
import android.os.Build.VERSION;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.WindowManager;

public class c
{
  private static boolean a = false;
  private static float b = 1.0F;
  private static int c = 320;
  private static int d = 1;
  private static int e = 720;
  private static int f = 1280;
  private static boolean g = false;

  public static void a(int paramInt)
  {
    d = paramInt;
  }

  public static void a(Context paramContext)
  {
    boolean bool2 = true;
    DisplayMetrics localDisplayMetrics = new DisplayMetrics();
    ((WindowManager)paramContext.getSystemService("window")).getDefaultDisplay().getMetrics(localDisplayMetrics);
    b = localDisplayMetrics.scaledDensity;
    c = localDisplayMetrics.densityDpi;
    e = localDisplayMetrics.widthPixels;
    f = localDisplayMetrics.heightPixels;
    if (Build.VERSION.SDK_INT >= 14)
    {
      bool1 = true;
      a = bool1;
      if (e <= b * 600.0F)
        break label96;
    }
    label96: for (boolean bool1 = bool2; ; bool1 = false)
    {
      g = bool1;
      return;
      bool1 = false;
      break;
    }
  }

  public static boolean a()
  {
    return a;
  }

  public static boolean b()
  {
    return g;
  }

  public static int c()
  {
    return c;
  }

  public static int d()
  {
    return d;
  }

  public static int e()
  {
    return e;
  }

  public static int f()
  {
    return f;
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     com.intowow.sdk.j.c
 * JD-Core Version:    0.6.2
 */