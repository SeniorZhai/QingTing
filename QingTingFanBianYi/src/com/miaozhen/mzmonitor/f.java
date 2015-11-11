package com.miaozhen.mzmonitor;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.NetworkInfo.State;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.os.Build.VERSION;
import android.provider.Settings.Secure;
import android.provider.Settings.System;
import android.telephony.TelephonyManager;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.WindowManager;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.util.Enumeration;
import java.util.Locale;

public class f
{
  private static boolean a = false;
  private static f c = null;
  private Context b;

  private f(Context paramContext)
  {
    this.b = paramContext;
  }

  public static f a(Context paramContext)
  {
    try
    {
      if (c == null)
        c = new f(paramContext.getApplicationContext());
      paramContext = c;
      return paramContext;
    }
    finally
    {
    }
    throw paramContext;
  }

  public static String a()
  {
    return Build.MODEL;
  }

  public static String b(Context paramContext)
  {
    paramContext = (TelephonyManager)paramContext.getSystemService("phone");
    if (paramContext != null)
    {
      String str = paramContext.getNetworkOperatorName();
      if ((paramContext.getNetworkOperatorName() != null) && (!paramContext.getNetworkOperatorName().equals("")))
        return str;
    }
    return null;
  }

  public static String g()
  {
    return Build.VERSION.RELEASE;
  }

  public static String n()
  {
    Locale localLocale = Locale.getDefault();
    return localLocale.getLanguage() + "_" + localLocale.getCountry();
  }

  public static String o()
  {
    try
    {
      Object localObject = ((NetworkInterface)NetworkInterface.getNetworkInterfaces().nextElement()).getInetAddresses();
      while (((Enumeration)localObject).hasMoreElements())
      {
        InetAddress localInetAddress = (InetAddress)((Enumeration)localObject).nextElement();
        if (!localInetAddress.isLinkLocalAddress())
        {
          localObject = localInetAddress.getHostAddress();
          return localObject;
        }
      }
    }
    catch (Exception localException)
    {
      localException.printStackTrace();
    }
    return null;
  }

  public final String b()
  {
    return ((TelephonyManager)this.b.getSystemService("phone")).getDeviceId();
  }

  public final String c()
  {
    return Settings.Secure.getString(this.b.getContentResolver(), "android_id");
  }

  public final String d()
  {
    try
    {
      Object localObject = this.b.getPackageManager().getApplicationInfo(this.b.getPackageName(), 128);
      if (localObject != null)
      {
        if (((ApplicationInfo)localObject).labelRes != 0)
          return this.b.getResources().getString(((ApplicationInfo)localObject).labelRes);
        if (((ApplicationInfo)localObject).nonLocalizedLabel == null)
          localObject = null;
        else
          localObject = ((ApplicationInfo)localObject).nonLocalizedLabel.toString();
      }
    }
    catch (Exception localException)
    {
      localException.printStackTrace();
    }
    return "";
    return localException;
  }

  public final String e()
  {
    String str2 = "";
    try
    {
      Object localObject = this.b.getPackageManager();
      String str1 = str2;
      if (localObject != null)
      {
        localObject = ((PackageManager)localObject).getPackageInfo(this.b.getPackageName(), 0);
        str1 = str2;
        if (localObject != null)
          str1 = ((PackageInfo)localObject).packageName;
      }
      return str1;
    }
    catch (Exception localException)
    {
      localException.printStackTrace();
    }
    return "";
  }

  protected final String f()
  {
    h.a(this.b);
    if (h.b())
      return h.a();
    return "";
  }

  public final String h()
  {
    WifiInfo localWifiInfo = ((WifiManager)this.b.getSystemService("wifi")).getConnectionInfo();
    if (localWifiInfo != null)
      return localWifiInfo.getMacAddress();
    return "";
  }

  public final String i()
  {
    WindowManager localWindowManager = (WindowManager)this.b.getSystemService("window");
    DisplayMetrics localDisplayMetrics = new DisplayMetrics();
    localWindowManager.getDefaultDisplay().getMetrics(localDisplayMetrics);
    return localDisplayMetrics.widthPixels + "x" + localDisplayMetrics.heightPixels;
  }

  public final String j()
  {
    try
    {
      String str1 = Settings.System.getString(this.b.getContentResolver(), "android_id");
      return c.a.b(str1);
    }
    catch (Exception localException1)
    {
      try
      {
        String str2 = Settings.System.getString(this.b.getContentResolver(), "android_id");
      }
      catch (Exception localException2)
      {
      }
    }
    return null;
  }

  public final boolean k()
  {
    return ((ConnectivityManager)this.b.getSystemService("connectivity")).getNetworkInfo(1).getState() == NetworkInfo.State.CONNECTED;
  }

  public final String l()
  {
    try
    {
      NetworkInfo localNetworkInfo = ((ConnectivityManager)this.b.getSystemService("connectivity")).getActiveNetworkInfo();
      if (localNetworkInfo != null)
      {
        if (localNetworkInfo.getType() == 1)
          return "1";
      }
      else
        return "0";
    }
    catch (Exception localException)
    {
      localException.printStackTrace();
      return "0";
    }
    return "2";
  }

  public final String m()
  {
    try
    {
      Object localObject = ((ConnectivityManager)this.b.getSystemService("connectivity")).getActiveNetworkInfo();
      if ((localObject != null) && (((NetworkInfo)localObject).getType() == 1))
      {
        localObject = ((WifiManager)this.b.getSystemService("wifi")).getConnectionInfo();
        if (localObject == null)
          return "NULL";
        localObject = ((WifiInfo)localObject).getSSID();
        return localObject;
      }
    }
    catch (Exception localException)
    {
      localException.printStackTrace();
    }
    return null;
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     com.miaozhen.mzmonitor.f
 * JD-Core Version:    0.6.2
 */