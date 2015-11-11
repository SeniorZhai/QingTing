package com.alipay.sdk.sys;

import android.content.Context;
import android.location.Location;
import android.location.LocationManager;

public class UserLocation
{
  private static double a = -1.0D;
  private static double b = -1.0D;

  public static String a()
  {
    return b + ";" + a;
  }

  private static void a(Context paramContext)
  {
    try
    {
      paramContext = (LocationManager)paramContext.getSystemService("location");
      if (paramContext.isProviderEnabled("gps"))
      {
        paramContext = paramContext.getLastKnownLocation("gps");
        if (paramContext != null)
        {
          a = paramContext.getLatitude();
          b = paramContext.getLongitude();
        }
      }
      return;
    }
    catch (Exception paramContext)
    {
    }
  }

  private static double b()
  {
    return a;
  }

  private static double c()
  {
    return b;
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     com.alipay.sdk.sys.UserLocation
 * JD-Core Version:    0.6.2
 */