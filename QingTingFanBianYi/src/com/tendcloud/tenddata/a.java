package com.tendcloud.tenddata;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Build.VERSION;
import android.os.Environment;
import android.preference.PreferenceManager;
import android.provider.Settings.Secure;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.lang.reflect.Method;
import java.util.Iterator;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class a
{
  static TelephonyManager a;
  static String b;
  private static final String c = "pref.deviceid.key";
  private static final String d = "00:00:00:00:00:00";
  private static final Pattern e = Pattern.compile("^([0-9A-F]{2}:){5}([0-9A-F]{2})$");
  private static final String f = ".tcookieid";
  private static String g = null;

  private static String a()
  {
    Object localObject1 = null;
    File[] arrayOfFile = new File("/").listFiles();
    int k = arrayOfFile.length;
    int i = 0;
    while (true)
    {
      Object localObject2 = localObject1;
      Object localObject3;
      if (i < k)
      {
        localObject3 = arrayOfFile[i];
        localObject2 = localObject1;
        if (!((File)localObject3).isDirectory())
          break label165;
        localObject2 = localObject1;
        if ("/sdcard".equals(((File)localObject3).getAbsolutePath()))
          break label165;
        if (((File)localObject3).canWrite())
        {
          localObject2 = a(new File((File)localObject3, ".tcookieid"));
          localObject1 = localObject2;
          if (x.a((String)localObject2));
        }
      }
      else
      {
        return localObject2;
      }
      localObject2 = localObject1;
      if (((File)localObject3).listFiles() != null)
      {
        localObject3 = ((File)localObject3).listFiles();
        int m = localObject3.length;
        int j = 0;
        while (true)
        {
          localObject2 = localObject1;
          if (j >= m)
            break label165;
          localObject2 = localObject3[j];
          if (((File)localObject2).isDirectory())
          {
            localObject1 = a(new File((File)localObject2, ".tcookieid"));
            localObject2 = localObject1;
            if (!x.a((String)localObject1))
              break;
          }
          j += 1;
        }
      }
      label165: i += 1;
      localObject1 = localObject2;
    }
  }

  private static String a(Context paramContext, boolean paramBoolean)
  {
    if ("mounted".equals(Environment.getExternalStorageState()))
    {
      Object localObject2 = Environment.getExternalStorageDirectory();
      if (paramBoolean);
      for (Object localObject1 = ".tcookieid"; ; localObject1 = ".tcookieid" + n(paramContext))
      {
        localObject2 = a(new File((File)localObject2, (String)localObject1));
        localObject1 = localObject2;
        if (x.a((String)localObject2))
          localObject1 = a(new File(Environment.getExternalStorageDirectory(), ".tid" + n(paramContext)));
        return localObject1;
      }
    }
    return "";
  }

  private static String a(File paramFile)
  {
    try
    {
      if ((paramFile.exists()) && (paramFile.canRead()))
      {
        paramFile = new FileInputStream(paramFile);
        byte[] arrayOfByte = new byte[''];
        int i = paramFile.read(arrayOfByte);
        paramFile.close();
        paramFile = new String(arrayOfByte, 0, i);
        return paramFile;
      }
    }
    catch (Exception paramFile)
    {
    }
    return null;
  }

  public static void a(Context paramContext)
  {
    a = (TelephonyManager)paramContext.getSystemService("phone");
  }

  private static void a(Context paramContext, String paramString)
  {
    File[] arrayOfFile = new File("/").listFiles();
    int k = arrayOfFile.length;
    int i = 0;
    while (i < k)
    {
      Object localObject = arrayOfFile[i];
      if ((((File)localObject).isDirectory()) && (!"/sdcard".equals(((File)localObject).getAbsolutePath())))
      {
        if ((((File)localObject).canWrite()) && (!new File((File)localObject, ".tcookieid" + n(paramContext)).exists()))
          a(new File((File)localObject, ".tcookieid"), paramString);
        if (((File)localObject).listFiles() != null)
        {
          localObject = ((File)localObject).listFiles();
          int m = localObject.length;
          int j = 0;
          while (j < m)
          {
            File localFile = localObject[j];
            if ((localFile.isDirectory()) && (localFile.canWrite()) && (!new File(localFile, ".tcookieid" + n(paramContext)).exists()))
              a(new File(localFile, ".tcookieid"), paramString);
            j += 1;
          }
        }
      }
      i += 1;
    }
  }

  private static void a(Context paramContext, String paramString, boolean paramBoolean)
  {
    File localFile = Environment.getExternalStorageDirectory();
    if (paramBoolean);
    for (paramContext = ".tcookieid"; ; paramContext = ".tcookieid" + n(paramContext))
    {
      a(new File(localFile, paramContext), paramString);
      return;
    }
  }

  private static void a(File paramFile, String paramString)
  {
    try
    {
      FileOutputStream localFileOutputStream = new FileOutputStream(paramFile);
      localFileOutputStream.write(paramString.getBytes());
      localFileOutputStream.close();
      if (Build.VERSION.SDK_INT < 9)
      {
        Runtime.getRuntime().exec("chmod 444 " + paramFile.getAbsolutePath());
        return;
      }
      paramFile.getClass().getMethod("setReadable", new Class[] { Boolean.TYPE, Boolean.TYPE }).invoke(paramFile, new Object[] { Boolean.valueOf(true), Boolean.valueOf(false) });
      return;
    }
    catch (Exception paramFile)
    {
    }
  }

  public static String b(Context paramContext)
  {
    try
    {
      if (b == null)
        b = j(paramContext);
      paramContext = b;
      return paramContext;
    }
    finally
    {
    }
    throw paramContext;
  }

  private static void b(Context paramContext, String paramString)
  {
    paramContext = paramContext.getSharedPreferences("tdid", 0);
    if (paramContext != null)
    {
      paramContext = paramContext.edit();
      paramContext.putString("pref.deviceid.key", paramString);
      paramContext.commit();
    }
  }

  private static boolean b()
  {
    while (true)
    {
      try
      {
        if (Build.VERSION.SDK_INT < 9)
          break label42;
        bool = ((Boolean)Environment.class.getMethod("isExternalStorageRemovable", null).invoke(null, null)).booleanValue();
        if (!bool)
          return true;
      }
      catch (Exception localException)
      {
        bool = true;
        continue;
      }
      return false;
      label42: boolean bool = true;
    }
  }

  public static String c(Context paramContext)
  {
    return Settings.Secure.getString(paramContext.getContentResolver(), "android_id");
  }

  public static String d(Context paramContext)
  {
    if (x.a(paramContext, "android.permission.READ_PHONE_STATE"))
    {
      if (a == null)
        a(paramContext);
      return a.getDeviceId();
    }
    return "";
  }

  public static String e(Context paramContext)
  {
    if (x.a(paramContext, "android.permission.READ_PHONE_STATE"))
    {
      if (a == null)
        a(paramContext);
      return a.getSimSerialNumber();
    }
    return "";
  }

  public static String f(Context paramContext)
  {
    if (x.a(paramContext, "android.permission.READ_PHONE_STATE"))
    {
      if (a == null)
        a(paramContext);
      return a.getSubscriberId();
    }
    return "";
  }

  public static String g(Context paramContext)
  {
    if (x.a(paramContext, "android.permission.ACCESS_WIFI_STATE"))
    {
      paramContext = (WifiManager)paramContext.getSystemService("wifi");
      if (paramContext.isWifiEnabled())
      {
        paramContext = paramContext.getConnectionInfo();
        if (paramContext != null)
        {
          String str = paramContext.getMacAddress().toUpperCase().trim();
          if (!"00:00:00:00:00:00".equals(str))
          {
            paramContext = str;
            if (e.matcher(str).matches());
          }
          else
          {
            paramContext = "";
          }
          return paramContext;
        }
      }
    }
    return "";
  }

  public static final String h(Context paramContext)
  {
    try
    {
      paramContext = Class.forName("com.google.android.gms.ads.identifier.AdvertisingIdClient").getMethod("getAdvertisingIdInfo", new Class[] { Context.class }).invoke(null, new Object[] { paramContext });
      paramContext = (String)Class.forName("com.google.android.gms.ads.identifier.AdvertisingIdClient.Info").getMethod("getId", null).invoke(paramContext, null);
      return paramContext;
    }
    catch (Exception paramContext)
    {
    }
    return null;
  }

  public static final String i(Context paramContext)
  {
    String str2 = g(paramContext);
    String str1 = str2;
    if (!TextUtils.isEmpty(str2))
      str1 = String.valueOf(Long.parseLong(str2.replaceAll(":", ""), 16));
    str2 = c(paramContext);
    String str3 = d(paramContext);
    String str4 = f(paramContext);
    String str5 = e(paramContext);
    String str6 = b(paramContext);
    paramContext = h(paramContext);
    return 2 + "|" + str1 + "|" + str2 + "|" + str3 + "|" + str4 + "|" + str5 + "|" + str6 + "|" + paramContext;
  }

  private static String j(Context paramContext)
  {
    String str2 = k(paramContext);
    String str3 = a();
    boolean bool = b();
    String str4 = a(paramContext, bool);
    Object localObject = new String[3];
    localObject[0] = str2;
    localObject[1] = str3;
    localObject[2] = str4;
    int j = localObject.length;
    int i = 0;
    String str1;
    if (i < j)
    {
      str1 = localObject[i];
      if (x.a(str1));
    }
    while (true)
    {
      localObject = str1;
      if (x.a(str1))
        localObject = l(paramContext);
      if (x.a(str2))
        b(paramContext, (String)localObject);
      if (x.a(str4))
        a(paramContext, (String)localObject, bool);
      if (x.a(str3))
        a(paramContext, (String)localObject);
      return localObject;
      i += 1;
      break;
      str1 = null;
    }
  }

  private static String k(Context paramContext)
  {
    String str2 = aa.b(paramContext, "tdid", "pref.deviceid.key", null);
    String str1 = str2;
    if (x.a(str2))
      str1 = PreferenceManager.getDefaultSharedPreferences(paramContext).getString("pref.deviceid.key", null);
    return str1;
  }

  private static String l(Context paramContext)
  {
    paramContext = m(paramContext);
    return "3" + x.b(paramContext);
  }

  private static String m(Context paramContext)
  {
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append(d(paramContext)).append('-').append(g(paramContext)).append('-').append(c(paramContext));
    return localStringBuilder.toString();
  }

  private static String n(Context paramContext)
  {
    if (g == null)
    {
      Object localObject = ((SensorManager)paramContext.getSystemService("sensor")).getSensorList(-1);
      paramContext = new Sensor[64];
      localObject = ((List)localObject).iterator();
      while (((Iterator)localObject).hasNext())
      {
        Sensor localSensor = (Sensor)((Iterator)localObject).next();
        if ((localSensor.getType() < paramContext.length) && (localSensor.getType() >= 0))
          paramContext[localSensor.getType()] = localSensor;
      }
      localObject = new StringBuffer();
      int i = 0;
      while (i < paramContext.length)
      {
        if (paramContext[i] != null)
          ((StringBuffer)localObject).append(i).append('.').append(paramContext[i].getVendor()).append('-').append(paramContext[i].getName()).append('-').append(paramContext[i].getVersion()).append('\n');
        i += 1;
      }
      g = String.valueOf(((StringBuffer)localObject).toString().hashCode());
    }
    return g;
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     com.tendcloud.tenddata.a
 * JD-Core Version:    0.6.2
 */