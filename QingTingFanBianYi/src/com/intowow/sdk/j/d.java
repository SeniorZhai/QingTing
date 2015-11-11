package com.intowow.sdk.j;

import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.location.Location;
import android.location.LocationManager;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.os.Build.VERSION;
import android.provider.Settings.Secure;
import android.telephony.TelephonyManager;

public class d
{
  public static String a()
  {
    String str = null;
    if (Build.VERSION.SDK_INT >= 9)
      str = Build.SERIAL;
    return str;
  }

  public static double[] a(Context paramContext)
  {
    if (paramContext == null)
      return null;
    try
    {
      localLocation = ((LocationManager)paramContext.getSystemService("location")).getLastKnownLocation("passive");
      if (localLocation != null)
        paramContext = new double[2];
    }
    catch (Exception paramContext)
    {
      while (true)
      {
        try
        {
          Location localLocation;
          paramContext[0] = localLocation.getLatitude();
          paramContext[1] = localLocation.getLongitude();
          return paramContext;
          paramContext = paramContext;
          paramContext = null;
          continue;
        }
        catch (Exception localException)
        {
          continue;
        }
        paramContext = null;
      }
    }
  }

  public static a b(Context paramContext)
  {
    int i = paramContext.registerReceiver(null, new IntentFilter("android.intent.action.BATTERY_CHANGED")).getIntExtra("status", -1);
    if ((i != 2) && (i != 5));
    for (i = 0; i != 0; i = 1)
      return a.a;
    return a.b;
  }

  public static int c(Context paramContext)
  {
    if (paramContext == null)
      return -1;
    paramContext = paramContext.registerReceiver(null, new IntentFilter("android.intent.action.BATTERY_CHANGED"));
    int i = paramContext.getIntExtra("level", -1);
    int j = paramContext.getIntExtra("scale", -1);
    return (int)Math.floor(100.0F * i / j);
  }

  public static String d(Context paramContext)
  {
    if (paramContext == null)
      return null;
    return Settings.Secure.getString(paramContext.getContentResolver(), "android_id");
  }

  public static String e(Context paramContext)
  {
    if (paramContext == null);
    while (!i.f(paramContext))
      return null;
    paramContext = paramContext.getSystemService("phone");
    if (paramContext != null);
    for (paramContext = ((TelephonyManager)paramContext).getSubscriberId(); ; paramContext = null)
      return paramContext;
  }

  public static String f(Context paramContext)
  {
    if (paramContext == null);
    while (!i.f(paramContext))
      return null;
    paramContext = paramContext.getSystemService("phone");
    if (paramContext != null);
    for (paramContext = ((TelephonyManager)paramContext).getDeviceId(); ; paramContext = null)
      return paramContext;
  }

  public static String g(Context paramContext)
  {
    if (paramContext == null)
      return null;
    if (!i.g(paramContext))
      return null;
    paramContext = paramContext.getSystemService("wifi");
    if (paramContext == null)
      return null;
    return ((WifiManager)paramContext).getConnectionInfo().getMacAddress();
  }

  public static enum a
  {
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     com.intowow.sdk.j.d
 * JD-Core Version:    0.6.2
 */