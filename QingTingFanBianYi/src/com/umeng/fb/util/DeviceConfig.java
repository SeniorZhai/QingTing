package com.umeng.fb.util;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.location.Location;
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
import java.lang.reflect.Field;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;
import javax.microedition.khronos.opengles.GL10;

public class DeviceConfig
{
  public static final int DEFAULT_TIMEZONE = 8;
  protected static final String LOG_TAG = DeviceConfig.class.getName();
  private static final String MOBILE_NETWORK = "2G/3G";
  protected static final String UNKNOW = "Unknown";
  private static final String WIFI = "Wi-Fi";

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
    try
    {
      paramContext = paramContext.getPackageManager().getApplicationInfo(paramContext.getPackageName(), 128);
      if (paramContext != null)
      {
        paramContext = paramContext.metaData.getString("UMENG_APPKEY");
        if (paramContext != null)
          return paramContext.trim();
        Log.e(LOG_TAG, "Could not read UMENG_APPKEY meta-data from AndroidManifest.xml.");
      }
      return null;
    }
    catch (Exception paramContext)
    {
      while (true)
        Log.e(LOG_TAG, "Could not read UMENG_APPKEY meta-data from AndroidManifest.xml.", paramContext);
    }
  }

  public static String getApplicationLable(Context paramContext)
  {
    return paramContext.getPackageManager().getApplicationLabel(paramContext.getApplicationInfo()).toString();
  }

  // ERROR //
  public static String getCPU()
  {
    // Byte code:
    //   0: aconst_null
    //   1: astore_3
    //   2: aconst_null
    //   3: astore_1
    //   4: aconst_null
    //   5: astore_0
    //   6: aconst_null
    //   7: astore 4
    //   9: aconst_null
    //   10: astore_2
    //   11: aconst_null
    //   12: astore 5
    //   14: new 129	java/io/FileReader
    //   17: dup
    //   18: ldc 131
    //   20: invokespecial 134	java/io/FileReader:<init>	(Ljava/lang/String;)V
    //   23: astore 6
    //   25: aload 5
    //   27: astore_0
    //   28: aload 6
    //   30: ifnull +48 -> 78
    //   33: new 136	java/io/BufferedReader
    //   36: dup
    //   37: aload 6
    //   39: sipush 1024
    //   42: invokespecial 139	java/io/BufferedReader:<init>	(Ljava/io/Reader;I)V
    //   45: astore 5
    //   47: aload 4
    //   49: astore_0
    //   50: aload_2
    //   51: astore_1
    //   52: aload 5
    //   54: invokevirtual 142	java/io/BufferedReader:readLine	()Ljava/lang/String;
    //   57: astore_2
    //   58: aload_2
    //   59: astore_0
    //   60: aload_2
    //   61: astore_1
    //   62: aload 5
    //   64: invokevirtual 145	java/io/BufferedReader:close	()V
    //   67: aload_2
    //   68: astore_0
    //   69: aload_2
    //   70: astore_1
    //   71: aload 6
    //   73: invokevirtual 146	java/io/FileReader:close	()V
    //   76: aload_2
    //   77: astore_0
    //   78: aload_0
    //   79: astore_1
    //   80: aload_0
    //   81: ifnull +16 -> 97
    //   84: aload_0
    //   85: aload_0
    //   86: bipush 58
    //   88: invokevirtual 150	java/lang/String:indexOf	(I)I
    //   91: iconst_1
    //   92: iadd
    //   93: invokevirtual 153	java/lang/String:substring	(I)Ljava/lang/String;
    //   96: astore_1
    //   97: aload_1
    //   98: invokevirtual 102	java/lang/String:trim	()Ljava/lang/String;
    //   101: areturn
    //   102: astore_2
    //   103: aload_3
    //   104: astore_0
    //   105: aload_0
    //   106: astore_1
    //   107: getstatic 28	com/umeng/fb/util/DeviceConfig:LOG_TAG	Ljava/lang/String;
    //   110: ldc 155
    //   112: aload_2
    //   113: invokestatic 113	com/umeng/fb/util/Log:e	(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Exception;)V
    //   116: goto -38 -> 78
    //   119: astore_2
    //   120: aload_1
    //   121: astore_0
    //   122: getstatic 28	com/umeng/fb/util/DeviceConfig:LOG_TAG	Ljava/lang/String;
    //   125: ldc 157
    //   127: aload_2
    //   128: invokestatic 113	com/umeng/fb/util/Log:e	(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Exception;)V
    //   131: goto -53 -> 78
    //   134: astore_2
    //   135: goto -13 -> 122
    //   138: astore_2
    //   139: goto -17 -> 122
    //   142: astore_2
    //   143: aload_1
    //   144: astore_0
    //   145: goto -40 -> 105
    //
    // Exception table:
    //   from	to	target	type
    //   33	47	102	java/io/IOException
    //   33	47	119	java/io/FileNotFoundException
    //   107	116	119	java/io/FileNotFoundException
    //   14	25	134	java/io/FileNotFoundException
    //   52	58	138	java/io/FileNotFoundException
    //   62	67	138	java/io/FileNotFoundException
    //   71	76	138	java/io/FileNotFoundException
    //   52	58	142	java/io/IOException
    //   62	67	142	java/io/IOException
    //   71	76	142	java/io/IOException
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
          Log.i(LOG_TAG, "Could not read UMENG_CHANNEL meta-data from AndroidManifest.xml.");
          return "Unknown";
        }
      }
    }
    catch (Exception paramContext)
    {
      Log.i(LOG_TAG, "Could not read UMENG_CHANNEL meta-data from AndroidManifest.xml.");
      paramContext.printStackTrace();
    }
    return "Unknown";
  }

  public static String getDeviceId(Context paramContext)
  {
    TelephonyManager localTelephonyManager = (TelephonyManager)paramContext.getSystemService("phone");
    if (localTelephonyManager == null)
      Log.w(LOG_TAG, "No IMEI.");
    Object localObject3 = "";
    Object localObject1 = localObject3;
    try
    {
      if (checkPermission(paramContext, "android.permission.READ_PHONE_STATE"))
        localObject1 = localTelephonyManager.getDeviceId();
      localObject3 = localObject1;
      if (TextUtils.isEmpty((CharSequence)localObject1))
      {
        Log.w(LOG_TAG, "No IMEI.");
        localObject1 = getMac(paramContext);
        localObject3 = localObject1;
        if (TextUtils.isEmpty((CharSequence)localObject1))
        {
          Log.w(LOG_TAG, "Failed to take mac as IMEI. Try to use Secure.ANDROID_ID instead.");
          paramContext = Settings.Secure.getString(paramContext.getContentResolver(), "android_id");
          Log.i(LOG_TAG, "getDeviceId: Secure.ANDROID_ID: " + paramContext);
          return paramContext;
        }
      }
    }
    catch (Exception localException)
    {
      while (true)
      {
        Log.w(LOG_TAG, "No IMEI.", localException);
        Object localObject2 = localObject3;
      }
    }
    return localObject3;
  }

  public static String getDeviceIdUmengMD5(Context paramContext)
  {
    return Helper.getUmengMD5(getDeviceId(paramContext));
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
      Log.e(LOG_TAG, "Could not read gpu infor:", paramGL10);
    }
    return new String[0];
  }

  public static int getIntervalSeconds(Date paramDate1, Date paramDate2)
  {
    Date localDate2 = paramDate1;
    Date localDate1 = paramDate2;
    if (paramDate1.after(paramDate2))
    {
      localDate1 = paramDate1;
      localDate2 = paramDate2;
    }
    long l = localDate2.getTime();
    return (int)((localDate1.getTime() - l) / 1000L);
  }

  private static Locale getLocale(Context paramContext)
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
        Log.e(LOG_TAG, "fail to read user config locale");
        paramContext = (Context)localObject;
      }
    }
  }

  public static String[] getLocaleInfo(Context paramContext)
  {
    String[] arrayOfString = new String[2];
    try
    {
      paramContext = getLocale(paramContext);
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
      Log.e(LOG_TAG, "error in getLocaleInfo", paramContext);
    }
    return arrayOfString;
  }

  public static Location getLocation(Context paramContext)
  {
    return null;
  }

  public static String getMac(Context paramContext)
  {
    try
    {
      WifiManager localWifiManager = (WifiManager)paramContext.getSystemService("wifi");
      if (checkPermission(paramContext, "android.permission.ACCESS_WIFI_STATE"))
        return localWifiManager.getConnectionInfo().getMacAddress();
      Log.w(LOG_TAG, "Could not get mac address.[no permission android.permission.ACCESS_WIFI_STATE");
      return "";
    }
    catch (Exception paramContext)
    {
      while (true)
        Log.w(LOG_TAG, "Could not get mac address." + paramContext.toString());
    }
  }

  public static String[] getNetworkAccessMode(Context paramContext)
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
    }
    catch (Exception paramContext)
    {
      paramContext.printStackTrace();
      return arrayOfString;
    }
    if (paramContext.getNetworkInfo(1).getState() == NetworkInfo.State.CONNECTED)
    {
      arrayOfString[0] = "Wi-Fi";
      return arrayOfString;
    }
    paramContext = paramContext.getNetworkInfo(0);
    if (paramContext.getState() == NetworkInfo.State.CONNECTED)
    {
      arrayOfString[0] = "2G/3G";
      arrayOfString[1] = paramContext.getSubtypeName();
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
      Log.i(LOG_TAG, "read carrier fail", paramContext);
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
      int j;
      int i;
      int k;
      try
      {
        DisplayMetrics localDisplayMetrics = new DisplayMetrics();
        ((WindowManager)paramContext.getSystemService("window")).getDefaultDisplay().getMetrics(localDisplayMetrics);
        j = -1;
        i = -1;
        if ((paramContext.getApplicationInfo().flags & 0x2000) == 0)
        {
          j = reflectMetrics(localDisplayMetrics, "noncompatWidthPixels");
          i = reflectMetrics(localDisplayMetrics, "noncompatHeightPixels");
          break label127;
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
        Log.e(LOG_TAG, "read resolution fail", paramContext);
        return "Unknown";
      }
      label127: if (j != -1)
      {
        k = i;
        if (i != -1);
      }
    }
  }

  public static String getTimeString(Date paramDate)
  {
    return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(paramDate);
  }

  public static int getTimeZone(Context paramContext)
  {
    try
    {
      paramContext = Calendar.getInstance(getLocale(paramContext));
      if (paramContext != null)
      {
        int i = paramContext.getTimeZone().getRawOffset() / 3600000;
        return i;
      }
    }
    catch (Exception paramContext)
    {
      Log.i(LOG_TAG, "error in getTimeZone", paramContext);
    }
    return 8;
  }

  public static String getToday()
  {
    Date localDate = new Date();
    return new SimpleDateFormat("yyyy-MM-dd").format(localDate);
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

  private static int reflectMetrics(Object paramObject, String paramString)
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
 * Qualified Name:     com.umeng.fb.util.DeviceConfig
 * JD-Core Version:    0.6.2
 */