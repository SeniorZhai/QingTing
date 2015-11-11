package com.tencent.a.b;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

public final class l
{
  private static l b;
  private Context a;

  public static l a()
  {
    if (b == null)
      b = new l();
    return b;
  }

  public static Context b()
  {
    return a().a;
  }

  public static boolean c()
  {
    try
    {
      NetworkInfo localNetworkInfo = ((ConnectivityManager)a().a.getSystemService("connectivity")).getActiveNetworkInfo();
      if (localNetworkInfo != null)
      {
        int i = localNetworkInfo.getType();
        if (i == 1)
          return true;
      }
    }
    catch (Exception localException)
    {
    }
    return false;
  }

  public static boolean d()
  {
    try
    {
      NetworkInfo localNetworkInfo = ((ConnectivityManager)a().a.getSystemService("connectivity")).getActiveNetworkInfo();
      if (localNetworkInfo != null)
      {
        boolean bool = localNetworkInfo.isAvailable();
        return bool;
      }
    }
    catch (Exception localException)
    {
    }
    return false;
  }

  public final void a(Context paramContext)
  {
    if (this.a == null)
      this.a = paramContext.getApplicationContext();
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     com.tencent.a.b.l
 * JD-Core Version:    0.6.2
 */