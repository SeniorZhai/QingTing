package com.ta.utdid2.android.utils;

import android.content.Context;
import android.content.pm.PackageManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.NetworkInfo.State;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;

public class NetworkUtils
{
  public static final String DEFAULT_WIFI_ADDRESS = "00-00-00-00-00-00";
  public static final String WIFI = "Wi-Fi";

  private static String _convertIntToIp(int paramInt)
  {
    return (paramInt & 0xFF) + "." + (paramInt >> 8 & 0xFF) + "." + (paramInt >> 16 & 0xFF) + "." + (paramInt >> 24 & 0xFF);
  }

  public static String[] getNetworkState(Context paramContext)
  {
    String[] arrayOfString = new String[2];
    arrayOfString[0] = "Unknown";
    arrayOfString[1] = "Unknown";
    try
    {
      if (paramContext.getPackageManager().checkPermission("android.permission.ACCESS_NETWORK_STATE", paramContext.getPackageName()) != 0)
      {
        arrayOfString[0] = "Unknown";
        return arrayOfString;
      }
      paramContext = (ConnectivityManager)paramContext.getSystemService("connectivity");
      if (paramContext == null)
      {
        arrayOfString[0] = "Unknown";
        return arrayOfString;
      }
      NetworkInfo localNetworkInfo = paramContext.getNetworkInfo(1);
      if ((localNetworkInfo != null) && (localNetworkInfo.getState() == NetworkInfo.State.CONNECTED))
      {
        arrayOfString[0] = "Wi-Fi";
        return arrayOfString;
      }
      paramContext = paramContext.getNetworkInfo(0);
      if ((paramContext != null) && (paramContext.getState() == NetworkInfo.State.CONNECTED))
      {
        arrayOfString[0] = "2G/3G";
        arrayOfString[1] = paramContext.getSubtypeName();
      }
      return arrayOfString;
    }
    catch (Exception paramContext)
    {
    }
    return arrayOfString;
  }

  public static String getWifiAddress(Context paramContext)
  {
    if (paramContext != null)
    {
      paramContext = ((WifiManager)paramContext.getSystemService("wifi")).getConnectionInfo();
      if (paramContext != null)
      {
        String str = paramContext.getMacAddress();
        paramContext = str;
        if (StringUtils.isEmpty(str))
          paramContext = "00-00-00-00-00-00";
        return paramContext;
      }
      return "00-00-00-00-00-00";
    }
    return "00-00-00-00-00-00";
  }

  public static String getWifiIpAddress(Context paramContext)
  {
    Object localObject2 = null;
    Object localObject1 = localObject2;
    if (paramContext != null);
    try
    {
      paramContext = ((WifiManager)paramContext.getSystemService("wifi")).getConnectionInfo();
      localObject1 = localObject2;
      if (paramContext != null)
        localObject1 = _convertIntToIp(paramContext.getIpAddress());
      return localObject1;
    }
    catch (Exception paramContext)
    {
    }
    return null;
  }

  public static boolean isConnectInternet(Context paramContext)
  {
    paramContext = (ConnectivityManager)paramContext.getSystemService("connectivity");
    if (paramContext != null)
      try
      {
        paramContext = paramContext.getActiveNetworkInfo();
        if (paramContext != null)
        {
          boolean bool = paramContext.isAvailable();
          return bool;
        }
      }
      catch (Exception paramContext)
      {
      }
    return false;
  }

  public static boolean isWifi(Context paramContext)
  {
    boolean bool2 = false;
    boolean bool1 = bool2;
    if (paramContext != null);
    try
    {
      boolean bool3 = getNetworkState(paramContext)[0].equals("Wi-Fi");
      bool1 = bool2;
      if (bool3)
        bool1 = true;
      return bool1;
    }
    catch (Exception paramContext)
    {
    }
    return false;
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     com.ta.utdid2.android.utils.NetworkUtils
 * JD-Core Version:    0.6.2
 */