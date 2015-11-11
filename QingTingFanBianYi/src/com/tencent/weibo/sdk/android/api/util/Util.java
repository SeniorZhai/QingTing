package com.tencent.weibo.sdk.android.api.util;

import android.app.Activity;
import android.content.Context;
import android.graphics.BitmapFactory;
import android.graphics.BitmapFactory.Options;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.NetworkInfo.State;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.util.Log;
import java.net.URL;
import java.net.URLConnection;
import java.util.Properties;

public class Util
{
  public static void clearSharePersistent(Context paramContext)
  {
    SharePersistent localSharePersistent = SharePersistent.getInstance();
    localSharePersistent.clear(paramContext, "ACCESS_TOKEN");
    localSharePersistent.clear(paramContext, "EXPIRES_IN");
    localSharePersistent.clear(paramContext, "OPEN_ID");
    localSharePersistent.clear(paramContext, "OPEN_KEY");
    localSharePersistent.clear(paramContext, "REFRESH_TOKEN");
    localSharePersistent.clear(paramContext, "NAME");
    localSharePersistent.clear(paramContext, "NICK");
    localSharePersistent.clear(paramContext, "CLIENT_ID");
  }

  public static void clearSharePersistent(Context paramContext, String paramString)
  {
    SharePersistent.getInstance().clear(paramContext, paramString);
  }

  public static Properties getConfig()
  {
    Properties localProperties = new Properties();
    localProperties.setProperty("APP_KEY", "801439222");
    localProperties.setProperty("APP_KEY_SEC", "9a0504d18beda2583aa2ddfb2046d4f9");
    localProperties.setProperty("REDIRECT_URI", "http://tencent.callback.qingting.fm");
    return localProperties;
  }

  public static String getLocalIPAddress(Context paramContext)
  {
    int i = ((WifiManager)paramContext.getSystemService("wifi")).getConnectionInfo().getIpAddress();
    return (i & 0xFF) + "." + (i >> 8 & 0xFF) + "." + (i >> 16 & 0xFF) + "." + (i >> 24 & 0xFF);
  }

  public static Location getLocation(Context paramContext)
  {
    try
    {
      paramContext = (LocationManager)paramContext.getSystemService("location");
      Object localObject = new Criteria();
      ((Criteria)localObject).setAccuracy(2);
      ((Criteria)localObject).setAltitudeRequired(false);
      ((Criteria)localObject).setBearingRequired(false);
      ((Criteria)localObject).setCostAllowed(true);
      ((Criteria)localObject).setPowerRequirement(3);
      ((Criteria)localObject).setSpeedRequired(false);
      localObject = paramContext.getBestProvider((Criteria)localObject, true);
      Log.d("Location", "currentProvider: " + (String)localObject);
      paramContext = paramContext.getLastKnownLocation((String)localObject);
      return paramContext;
    }
    catch (Exception paramContext)
    {
    }
    return null;
  }

  public static String getSharePersistent(Context paramContext, String paramString)
  {
    return SharePersistent.getInstance().get(paramContext, paramString);
  }

  public static Long getSharePersistentLong(Context paramContext, String paramString)
  {
    return Long.valueOf(SharePersistent.getInstance().getLong(paramContext, paramString));
  }

  private String intToIp(int paramInt)
  {
    return (paramInt & 0xFF) + "." + (paramInt >> 8 & 0xFF) + "." + (paramInt >> 16 & 0xFF) + "." + (paramInt >> 24 & 0xFF);
  }

  public static boolean isNetworkAvailable(Activity paramActivity)
  {
    paramActivity = (ConnectivityManager)paramActivity.getApplicationContext().getSystemService("connectivity");
    if (paramActivity == null);
    while (true)
    {
      return false;
      paramActivity = paramActivity.getAllNetworkInfo();
      if (paramActivity != null)
      {
        int i = 0;
        while (i < paramActivity.length)
        {
          if (paramActivity[i].getState() == NetworkInfo.State.CONNECTED)
            return true;
          i += 1;
        }
      }
    }
  }

  public static Drawable loadImageFromUrl(String paramString)
  {
    try
    {
      paramString = new URL(paramString).openConnection();
      paramString.connect();
      paramString = paramString.getInputStream();
      BitmapFactory.Options localOptions = new BitmapFactory.Options();
      localOptions.inJustDecodeBounds = false;
      localOptions.inSampleSize = 2;
      paramString = new BitmapDrawable(BitmapFactory.decodeStream(paramString, null, localOptions));
      return paramString;
    }
    catch (Exception paramString)
    {
    }
    return null;
  }

  public static void saveSharePersistent(Context paramContext, String paramString, long paramLong)
  {
    SharePersistent.getInstance().put(paramContext, paramString, paramLong);
  }

  public static void saveSharePersistent(Context paramContext, String paramString1, String paramString2)
  {
    SharePersistent.getInstance().put(paramContext, paramString1, paramString2);
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     com.tencent.weibo.sdk.android.api.util.Util
 * JD-Core Version:    0.6.2
 */