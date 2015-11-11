package com.umeng.common.message;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.location.Location;
import android.location.LocationManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.NetworkInfo.State;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.os.Environment;
import android.provider.Settings.Secure;
import android.provider.Settings.System;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.WindowManager;
import com.umeng.message.proguard.y;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Field;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Set;
import java.util.TimeZone;
import javax.microedition.khronos.opengles.GL10;
import org.agoo.ut.device.UTDevice;

public class DeviceConfig
{
  public static final int DEFAULT_TIMEZONE = 8;
  protected static final String a = DeviceConfig.class.getName();
  protected static final String b = "Unknown";
  private static final String c = "2G/3G";
  private static final String d = "Wi-Fi";

  private static int a(Object paramObject, String paramString)
  {
    try
    {
      paramString = DisplayMetrics.class.getDeclaredField(paramString);
      paramString.setAccessible(true);
      int i = paramString.getInt(paramObject);
      return i;
    }
    catch (Exception paramObject)
    {
      paramObject.printStackTrace();
    }
    return -1;
  }

  private static Locale a(Context paramContext)
  {
    Object localObject = null;
    try
    {
      Configuration localConfiguration = new Configuration();
      Settings.System.getConfiguration(paramContext.getContentResolver(), localConfiguration);
      paramContext = (Context)localObject;
      if (localConfiguration != null)
        paramContext = localConfiguration.locale;
      localObject = paramContext;
      if (paramContext == null)
        localObject = Locale.getDefault();
      return localObject;
    }
    catch (Exception paramContext)
    {
      while (true)
      {
        Log.b(a, "fail to read user config locale");
        paramContext = (Context)localObject;
      }
    }
  }

  public static boolean checkPermission(Context paramContext, String paramString)
  {
    return paramContext.getPackageManager().checkPermission(paramString, paramContext.getPackageName()) == 0;
  }

  public static String getAppLabel(Context paramContext)
  {
    PackageManager localPackageManager = paramContext.getPackageManager();
    try
    {
      paramContext = localPackageManager.getApplicationInfo(paramContext.getPackageName(), 0);
      if (paramContext != null)
      {
        paramContext = localPackageManager.getApplicationLabel(paramContext);
        return (String)paramContext;
      }
    }
    catch (PackageManager.NameNotFoundException paramContext)
    {
      while (true)
      {
        paramContext = null;
        continue;
        paramContext = "";
      }
    }
  }

  public static String getAppVersionCode(Context paramContext)
  {
    try
    {
      int i = paramContext.getPackageManager().getPackageInfo(paramContext.getPackageName(), 0).versionCode;
      return String.valueOf(i);
    }
    catch (PackageManager.NameNotFoundException paramContext)
    {
    }
    return "Unknown";
  }

  public static String getAppVersionName(Context paramContext)
  {
    try
    {
      paramContext = paramContext.getPackageManager().getPackageInfo(paramContext.getPackageName(), 0).versionName;
      return paramContext;
    }
    catch (PackageManager.NameNotFoundException paramContext)
    {
    }
    return "Unknown";
  }

  public static String getAppkey(Context paramContext)
  {
    return getMetaData(paramContext, "UMENG_APPKEY");
  }

  public static String getApplicationLable(Context paramContext)
  {
    return paramContext.getPackageManager().getApplicationLabel(paramContext.getApplicationInfo()).toString();
  }

  public static String getCPU()
  {
    Object localObject2 = null;
    Object localObject1 = null;
    try
    {
      FileReader localFileReader = new FileReader("/proc/cpuinfo");
      if (localFileReader != null)
        localObject1 = localObject2;
      while (true)
      {
        try
        {
          localBufferedReader = new BufferedReader(localFileReader, 1024);
          localObject1 = localObject2;
          localObject2 = localBufferedReader.readLine();
          localObject1 = localObject2;
          localObject3 = localObject2;
        }
        catch (IOException localIOException)
        {
          try
          {
            BufferedReader localBufferedReader;
            localBufferedReader.close();
            localObject1 = localObject2;
            localObject3 = localObject2;
            localFileReader.close();
            localObject1 = localObject2;
            localObject2 = localObject1;
            if (localObject1 != null)
              localObject2 = localObject1.substring(localObject1.indexOf(':') + 1);
            return ((String)localObject2).trim();
            localIOException = localIOException;
            localObject3 = localObject1;
            Log.b(a, "Could not read from file /proc/cpuinfo", localIOException);
            continue;
          }
          catch (FileNotFoundException localFileNotFoundException1)
          {
            Object localObject3;
            localObject1 = localObject3;
          }
        }
        Log.b(a, "Could not open file /proc/cpuinfo", localFileNotFoundException1);
      }
    }
    catch (FileNotFoundException localFileNotFoundException2)
    {
      while (true)
        localObject1 = null;
    }
  }

  public static String getChannel(Context paramContext)
  {
    try
    {
      paramContext = paramContext.getPackageManager().getApplicationInfo(paramContext.getPackageName(), 128);
      if ((paramContext != null) && (paramContext.metaData != null))
      {
        paramContext = paramContext.metaData.get("UMENG_CHANNEL");
        if (paramContext != null)
        {
          paramContext = paramContext.toString();
          if (paramContext != null)
            return paramContext;
          Log.a(a, "Could not read UMENG_CHANNEL meta-data from AndroidManifest.xml.");
        }
      }
      return "Unknown";
    }
    catch (Exception paramContext)
    {
      while (true)
      {
        Log.a(a, "Could not read UMENG_CHANNEL meta-data from AndroidManifest.xml.");
        paramContext.printStackTrace();
      }
    }
  }

  public static String getDeviceId(Context paramContext)
  {
    Object localObject = (TelephonyManager)paramContext.getSystemService("phone");
    if (localObject == null)
      Log.e(a, "No IMEI.");
    try
    {
      if (checkPermission(paramContext, "android.permission.READ_PHONE_STATE"))
      {
        str = ((TelephonyManager)localObject).getDeviceId();
        localObject = str;
        if (TextUtils.isEmpty(str))
        {
          Log.e(a, "No IMEI.");
          str = getMac(paramContext);
          localObject = str;
          if (TextUtils.isEmpty(str))
          {
            Log.e(a, "Failed to take mac as IMEI. Try to use Secure.ANDROID_ID instead.");
            localObject = Settings.Secure.getString(paramContext.getContentResolver(), "android_id");
            Log.a(a, "getDeviceId: Secure.ANDROID_ID: " + (String)localObject);
          }
        }
        return localObject;
      }
    }
    catch (Exception localException)
    {
      while (true)
      {
        Log.e(a, "No IMEI.", localException);
        String str = "";
      }
    }
  }

  public static String getDeviceIdMD5(Context paramContext)
  {
    return y.a(getDeviceId(paramContext));
  }

  public static String getDeviceIdUmengMD5(Context paramContext)
  {
    return y.b(getDeviceId(paramContext));
  }

  public static String getDisplayResolution(Context paramContext)
  {
    try
    {
      DisplayMetrics localDisplayMetrics = new DisplayMetrics();
      ((WindowManager)paramContext.getSystemService("window")).getDefaultDisplay().getMetrics(localDisplayMetrics);
      int i = localDisplayMetrics.widthPixels;
      int j = localDisplayMetrics.heightPixels;
      paramContext = String.valueOf(j) + "*" + String.valueOf(i);
      return paramContext;
    }
    catch (Exception paramContext)
    {
      paramContext.printStackTrace();
    }
    return "Unknown";
  }

  public static String[] getGPU(GL10 paramGL10)
  {
    try
    {
      String str = paramGL10.glGetString(7936);
      paramGL10 = paramGL10.glGetString(7937);
      return new String[] { str, paramGL10 };
    }
    catch (Exception paramGL10)
    {
      Log.b(a, "Could not read gpu infor:", paramGL10);
    }
    return new String[0];
  }

  public static Set<String> getInstalledPackages(Context paramContext)
  {
    HashSet localHashSet = new HashSet();
    paramContext = paramContext.getPackageManager().getInstalledPackages(0);
    int i = 0;
    while (i < paramContext.size())
    {
      localHashSet.add(((PackageInfo)paramContext.get(i)).packageName);
      i += 1;
    }
    return localHashSet;
  }

  public static int getIntervalSeconds(Date paramDate1, Date paramDate2)
  {
    Date localDate;
    if (paramDate1.after(paramDate2))
    {
      localDate = paramDate2;
      paramDate2 = paramDate1;
    }
    while (true)
    {
      long l = localDate.getTime();
      return (int)((paramDate2.getTime() - l) / 1000L);
      localDate = paramDate1;
    }
  }

  public static String[] getLocaleInfo(Context paramContext)
  {
    String[] arrayOfString = new String[2];
    try
    {
      paramContext = a(paramContext);
      if (paramContext != null)
      {
        arrayOfString[0] = paramContext.getCountry();
        arrayOfString[1] = paramContext.getLanguage();
      }
      if (TextUtils.isEmpty(arrayOfString[0]))
        arrayOfString[0] = "Unknown";
      if (TextUtils.isEmpty(arrayOfString[1]))
        arrayOfString[1] = "Unknown";
      return arrayOfString;
    }
    catch (Exception paramContext)
    {
      Log.b(a, "error in getLocaleInfo", paramContext);
    }
    return arrayOfString;
  }

  public static Location getLocation(Context paramContext)
  {
    try
    {
      LocationManager localLocationManager = (LocationManager)paramContext.getSystemService("location");
      if (checkPermission(paramContext, "android.permission.ACCESS_FINE_LOCATION"))
      {
        Location localLocation = localLocationManager.getLastKnownLocation("gps");
        if (localLocation != null)
        {
          Log.c(a, "get location from gps:" + localLocation.getLatitude() + "," + localLocation.getLongitude());
          return localLocation;
        }
      }
      if (checkPermission(paramContext, "android.permission.ACCESS_COARSE_LOCATION"))
      {
        paramContext = localLocationManager.getLastKnownLocation("network");
        if (paramContext != null)
        {
          Log.c(a, "get location from network:" + paramContext.getLatitude() + "," + paramContext.getLongitude());
          return paramContext;
        }
      }
    }
    catch (Exception paramContext)
    {
      Log.b(a, paramContext.getMessage());
      return null;
    }
    Log.c(a, "Could not get location from GPS or Cell-id, lack ACCESS_COARSE_LOCATION or ACCESS_COARSE_LOCATION permission?");
    return null;
  }

  public static String getMac(Context paramContext)
  {
    try
    {
      WifiManager localWifiManager = (WifiManager)paramContext.getSystemService("wifi");
      if (checkPermission(paramContext, "android.permission.ACCESS_WIFI_STATE"))
        return localWifiManager.getConnectionInfo().getMacAddress();
      Log.e(a, "Could not get mac address.[no permission android.permission.ACCESS_WIFI_STATE");
      return "";
    }
    catch (Exception paramContext)
    {
      while (true)
        Log.e(a, "Could not get mac address." + paramContext.toString());
    }
  }

  public static String getMetaData(Context paramContext, String paramString)
  {
    try
    {
      paramContext = paramContext.getPackageManager().getApplicationInfo(paramContext.getPackageName(), 128);
      if (paramContext != null)
      {
        paramContext = paramContext.metaData.getString(paramString);
        if (paramContext != null)
        {
          paramContext = paramContext.trim();
          return paramContext;
        }
      }
    }
    catch (Exception paramContext)
    {
      paramContext.printStackTrace();
      Log.b(a, String.format("Could not read meta-data %s from AndroidManifest.xml.", new Object[] { paramString }));
    }
    return null;
  }

  public static String[] getNetworkAccessMode(Context paramContext)
  {
    String[] arrayOfString = new String[2];
    arrayOfString[0] = "Unknown";
    arrayOfString[1] = "Unknown";
    try
    {
      paramContext = (ConnectivityManager)paramContext.getSystemService("connectivity");
      if (paramContext == null)
        return arrayOfString;
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
        return arrayOfString;
      }
    }
    catch (Exception paramContext)
    {
    }
    return arrayOfString;
  }

  public static String getNetworkOperatorName(Context paramContext)
  {
    try
    {
      paramContext = (TelephonyManager)paramContext.getSystemService("phone");
      if (paramContext == null)
        return "Unknown";
      paramContext = paramContext.getNetworkOperatorName();
      return paramContext;
    }
    catch (Exception paramContext)
    {
      paramContext.printStackTrace();
    }
    return "Unknown";
  }

  public static String getOperator(Context paramContext)
  {
    try
    {
      paramContext = ((TelephonyManager)paramContext.getSystemService("phone")).getNetworkOperatorName();
      return paramContext;
    }
    catch (Exception paramContext)
    {
      Log.a(a, "read carrier fail", paramContext);
    }
    return "Unknown";
  }

  public static String getPackageName(Context paramContext)
  {
    return paramContext.getPackageName();
  }

  public static String getResolution(Context paramContext)
  {
    while (true)
    {
      int k;
      try
      {
        DisplayMetrics localDisplayMetrics = new DisplayMetrics();
        ((WindowManager)paramContext.getSystemService("window")).getDefaultDisplay().getMetrics(localDisplayMetrics);
        if ((paramContext.getApplicationInfo().flags & 0x2000) == 0)
        {
          j = a(localDisplayMetrics, "noncompatWidthPixels");
          i = a(localDisplayMetrics, "noncompatHeightPixels");
          break label128;
          j = localDisplayMetrics.widthPixels;
          k = localDisplayMetrics.heightPixels;
          paramContext = new StringBuffer();
          paramContext.append(j);
          paramContext.append("*");
          paramContext.append(k);
          paramContext = paramContext.toString();
          return paramContext;
        }
      }
      catch (Exception paramContext)
      {
        Log.b(a, "read resolution fail", paramContext);
        return "Unknown";
      }
      int i = -1;
      int j = -1;
      label128: if (j != -1)
      {
        k = i;
        if (i != -1);
      }
    }
  }

  public static String getTimeString(Date paramDate)
  {
    return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.US).format(paramDate);
  }

  public static int getTimeZone(Context paramContext)
  {
    try
    {
      paramContext = Calendar.getInstance(a(paramContext));
      if (paramContext != null)
      {
        int i = paramContext.getTimeZone().getRawOffset() / 3600000;
        return i;
      }
    }
    catch (Exception paramContext)
    {
      Log.a(a, "error in getTimeZone", paramContext);
    }
    return 8;
  }

  public static String getToday()
  {
    Date localDate = new Date();
    return new SimpleDateFormat("yyyy-MM-dd", Locale.US).format(localDate);
  }

  public static String getUtdid(Context paramContext)
  {
    try
    {
      paramContext = UTDevice.getUtdid(paramContext);
      return paramContext;
    }
    catch (Throwable paramContext)
    {
      Log.b(a, "fail to get utdid. " + paramContext.getMessage());
    }
    return "";
  }

  public static boolean isAppInstalled(String paramString, Context paramContext)
  {
    paramContext = paramContext.getPackageManager();
    try
    {
      paramContext.getPackageInfo(paramString, 1);
      return true;
    }
    catch (PackageManager.NameNotFoundException paramString)
    {
    }
    return false;
  }

  public static boolean isChinese(Context paramContext)
  {
    return paramContext.getResources().getConfiguration().locale.toString().equals(Locale.CHINA.toString());
  }

  public static boolean isDebug(Context paramContext)
  {
    boolean bool = false;
    try
    {
      int i = paramContext.getApplicationInfo().flags;
      if ((i & 0x2) != 0)
        bool = true;
      return bool;
    }
    catch (Exception paramContext)
    {
    }
    return false;
  }

  public static boolean isOnline(Context paramContext)
  {
    try
    {
      paramContext = ((ConnectivityManager)paramContext.getSystemService("connectivity")).getActiveNetworkInfo();
      if (paramContext != null)
      {
        boolean bool = paramContext.isConnectedOrConnecting();
        return bool;
      }
      return false;
    }
    catch (Exception paramContext)
    {
    }
    return true;
  }

  public static boolean isScreenPortrait(Context paramContext)
  {
    return paramContext.getResources().getConfiguration().orientation == 1;
  }

  public static boolean isSdCardWrittenable()
  {
    return Environment.getExternalStorageState().equals("mounted");
  }

  public static boolean isWiFiAvailable(Context paramContext)
  {
    return "Wi-Fi".equals(getNetworkAccessMode(paramContext)[0]);
  }

  public static Date toTime(String paramString)
  {
    try
    {
      paramString = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(paramString);
      return paramString;
    }
    catch (Exception paramString)
    {
    }
    return null;
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     com.umeng.common.message.DeviceConfig
 * JD-Core Version:    0.6.2
 */