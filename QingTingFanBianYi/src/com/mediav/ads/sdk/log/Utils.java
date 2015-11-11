package com.mediav.ads.sdk.log;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.os.Build.VERSION;
import android.os.Environment;
import android.provider.Settings.Secure;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import android.util.Base64;
import android.util.DisplayMetrics;
import java.io.File;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.lang.reflect.Method;
import java.security.MessageDigest;

public class Utils
{
  private static Context mContext;

  public static String MD5(String paramString)
  {
    char[] arrayOfChar = new char[16];
    char[] tmp6_5 = arrayOfChar;
    tmp6_5[0] = 48;
    char[] tmp11_6 = tmp6_5;
    tmp11_6[1] = 49;
    char[] tmp16_11 = tmp11_6;
    tmp16_11[2] = 50;
    char[] tmp21_16 = tmp16_11;
    tmp21_16[3] = 51;
    char[] tmp26_21 = tmp21_16;
    tmp26_21[4] = 52;
    char[] tmp31_26 = tmp26_21;
    tmp31_26[5] = 53;
    char[] tmp36_31 = tmp31_26;
    tmp36_31[6] = 54;
    char[] tmp42_36 = tmp36_31;
    tmp42_36[7] = 55;
    char[] tmp48_42 = tmp42_36;
    tmp48_42[8] = 56;
    char[] tmp54_48 = tmp48_42;
    tmp54_48[9] = 57;
    char[] tmp60_54 = tmp54_48;
    tmp60_54[10] = 65;
    char[] tmp66_60 = tmp60_54;
    tmp66_60[11] = 66;
    char[] tmp72_66 = tmp66_60;
    tmp72_66[12] = 67;
    char[] tmp78_72 = tmp72_66;
    tmp78_72[13] = 68;
    char[] tmp84_78 = tmp78_72;
    tmp84_78[14] = 69;
    char[] tmp90_84 = tmp84_78;
    tmp90_84[15] = 70;
    tmp90_84;
    while (true)
    {
      Object localObject;
      int k;
      int i;
      int j;
      try
      {
        paramString = paramString.getBytes();
        localObject = MessageDigest.getInstance("MD5");
        ((MessageDigest)localObject).update(paramString);
        paramString = ((MessageDigest)localObject).digest();
        k = paramString.length;
        localObject = new char[k * 2];
        i = 0;
        j = 0;
        break label176;
        paramString = new String((char[])localObject);
        return paramString;
      }
      catch (Exception paramString)
      {
        MVLog.e("工具-fileName-MD5 Error=" + paramString.getMessage());
        return null;
      }
      label176: 
      while (i < k)
      {
        int m = paramString[i];
        int n = j + 1;
        localObject[j] = arrayOfChar[(m >>> 4 & 0xF)];
        j = n + 1;
        localObject[n] = arrayOfChar[(m & 0xF)];
        i += 1;
      }
    }
  }

  public static String base64Encode(String paramString)
  {
    if (paramString == null)
      return "";
    return new String(Base64.encode(paramString.getBytes(), 2));
  }

  public static String getAndroidid()
  {
    try
    {
      String str = Settings.Secure.getString(getContext().getContentResolver(), "android_id");
      return str;
    }
    catch (Exception localException)
    {
      MVLog.e("获取AndroidId失败");
    }
    return "";
  }

  public static String getAndroididWithMD5()
  {
    Object localObject = "";
    try
    {
      String str = Settings.Secure.getString(getContext().getContentResolver(), "android_id");
      localObject = str;
      if (str != null)
      {
        localObject = str;
        str = MD5(str);
        localObject = str;
      }
      return localObject;
    }
    catch (Exception localException)
    {
      MVLog.e("获取AndroidId失败");
    }
    return localObject;
  }

  public static String getAppPackageName()
  {
    try
    {
      String str = mContext.getPackageManager().getPackageInfo(mContext.getPackageName(), 0).packageName;
      return str;
    }
    catch (Exception localException)
    {
      MVLog.e("工具-应用包名 Error=" + localException.getMessage());
    }
    return "";
  }

  public static String getAppVersion()
  {
    try
    {
      String str = mContext.getPackageManager().getPackageInfo(mContext.getPackageName(), 0).versionName;
      return str;
    }
    catch (Exception localException)
    {
      MVLog.e("工具-AppVer Error=" + localException.getMessage());
    }
    return "";
  }

  public static String getAppVersionCode()
  {
    try
    {
      int i = mContext.getPackageManager().getPackageInfo(mContext.getPackageName(), 0).versionCode;
      return String.valueOf(i);
    }
    catch (Exception localException)
    {
      MVLog.e("工具-AppVer Error=" + localException.getMessage());
    }
    return "";
  }

  public static String getAppname()
  {
    try
    {
      Object localObject = mContext.getPackageManager();
      localObject = ((PackageManager)localObject).getPackageInfo(mContext.getPackageName(), 0).applicationInfo.loadLabel((PackageManager)localObject).toString();
      return localObject;
    }
    catch (Exception localException)
    {
      MVLog.e("工具-AppName Error=" + localException.getMessage());
    }
    return "";
  }

  public static String getBrand()
  {
    String str2 = Build.BRAND;
    String str1 = str2;
    if (str2 == null)
      str1 = "";
    return str1;
  }

  public static String getCacheDir()
  {
    if (("mounted".equals(Environment.getExternalStorageState())) || (!Environment.isExternalStorageRemovable()))
      try
      {
        String str = mContext.getExternalCacheDir().getPath();
        return str;
      }
      catch (Exception localException)
      {
        MVLog.e(localException.getMessage());
        return mContext.getCacheDir().getPath();
      }
    return mContext.getCacheDir().getPath();
  }

  public static Context getContext()
  {
    return mContext;
  }

  public static String getCurrentNetWorkInfo()
  {
    try
    {
      Object localObject = ((ConnectivityManager)mContext.getSystemService("connectivity")).getActiveNetworkInfo();
      if (((NetworkInfo)localObject).getType() == 1)
        return "0";
      localObject = ((NetworkInfo)localObject).getExtraInfo();
      return localObject;
    }
    catch (Exception localException)
    {
      MVLog.e("工具-CurrentNetInfo Error=" + localException.getMessage());
    }
    return "";
  }

  public static double getDeviceDensity()
  {
    try
    {
      float f = mContext.getResources().getDisplayMetrics().density;
      return f;
    }
    catch (Exception localException)
    {
      MVLog.e("工具-Density Error=" + localException.getMessage());
    }
    return -1.0D;
  }

  public static String getDeviceScreenSizeWithString(Boolean paramBoolean)
  {
    try
    {
      DisplayMetrics localDisplayMetrics = mContext.getResources().getDisplayMetrics();
      int i = localDisplayMetrics.widthPixels;
      int j = localDisplayMetrics.heightPixels;
      if (paramBoolean.booleanValue())
        return i + "";
      paramBoolean = j + "";
      return paramBoolean;
    }
    catch (Exception paramBoolean)
    {
      MVLog.e("工具-ScrrenSize Error=" + paramBoolean.getMessage());
    }
    return "";
  }

  public static String getDeviceSerial()
  {
    try
    {
      Object localObject = Class.forName("android.os.SystemProperties");
      localObject = (String)((Class)localObject).getMethod("get", new Class[] { String.class }).invoke(localObject, new Object[] { "ro.serialno" });
      return localObject;
    }
    catch (Exception localException)
    {
    }
    return "";
  }

  public static int getDeviceType()
  {
    if ((mContext.getResources().getConfiguration().screenLayout & 0xF) >= 3)
      return 2;
    return 1;
  }

  public static String getIMEI()
  {
    String str3 = "";
    String str2 = str3;
    try
    {
      TelephonyManager localTelephonyManager = (TelephonyManager)mContext.getSystemService("phone");
      String str1 = str3;
      if (localTelephonyManager != null)
      {
        str2 = str3;
        str3 = localTelephonyManager.getDeviceId();
        str1 = str3;
        str2 = str3;
        if (TextUtils.isEmpty(str3))
        {
          str2 = str3;
          str1 = Settings.Secure.getString(mContext.getContentResolver(), "android_id");
        }
      }
      return str1;
    }
    catch (Exception localException)
    {
      MVLog.e("工具-IMEI Error=" + localException.getMessage());
    }
    return str2;
  }

  public static String getIMEIWhitMD5()
  {
    String str2 = "";
    String str1 = str2;
    try
    {
      TelephonyManager localTelephonyManager = (TelephonyManager)mContext.getSystemService("phone");
      str1 = str2;
      if (localTelephonyManager != null)
      {
        str1 = str2;
        str2 = localTelephonyManager.getDeviceId();
        str1 = str2;
        if (TextUtils.isEmpty(str2))
        {
          str1 = str2;
          str2 = Settings.Secure.getString(mContext.getContentResolver(), "android_id");
          str1 = str2;
          if (str2 != null)
          {
            str1 = str2;
            return MD5(str2);
          }
        }
        else
        {
          str1 = str2;
          str2 = MD5(str2);
          return str2;
        }
      }
    }
    catch (Exception localException)
    {
      MVLog.e("工具-IMEI Error=" + localException.getMessage());
    }
    return str1;
  }

  public static String getIMSI()
  {
    Object localObject3 = "";
    Object localObject2 = localObject3;
    try
    {
      TelephonyManager localTelephonyManager = (TelephonyManager)mContext.getSystemService("phone");
      Object localObject1 = localObject3;
      if (localTelephonyManager != null)
      {
        localObject2 = localObject3;
        localObject1 = localTelephonyManager.getSubscriberId();
      }
      localObject3 = localObject1;
      localObject2 = localObject1;
      if (TextUtils.isEmpty((CharSequence)localObject1))
        localObject3 = "UNKNOWN";
      return localObject3;
    }
    catch (Exception localException)
    {
      MVLog.e("工具-IMSI Error=" + localException.getMessage());
    }
    return localObject2;
  }

  public static String getIMSIWhitMD5()
  {
    Object localObject3 = "";
    Object localObject2 = localObject3;
    try
    {
      TelephonyManager localTelephonyManager = (TelephonyManager)mContext.getSystemService("phone");
      Object localObject1 = localObject3;
      if (localTelephonyManager != null)
      {
        localObject2 = localObject3;
        localObject3 = localTelephonyManager.getSubscriberId();
        localObject1 = localObject3;
        if (localObject3 != null)
        {
          localObject2 = localObject3;
          localObject1 = MD5((String)localObject3);
        }
      }
      localObject3 = localObject1;
      localObject2 = localObject1;
      if (TextUtils.isEmpty((CharSequence)localObject1))
        localObject3 = "UNKNOWN";
      return localObject3;
    }
    catch (Exception localException)
    {
      MVLog.e("工具-IMSI Error=" + localException.getMessage());
    }
    return localObject2;
  }

  public static int getIdByName(Context paramContext, String paramString1, String paramString2)
  {
    String str = paramContext.getPackageName();
    return paramContext.getResources().getIdentifier(paramString2, paramString1, str);
  }

  public static String getMac()
  {
    Object localObject = "";
    try
    {
      String str = ((WifiManager)mContext.getSystemService("wifi")).getConnectionInfo().getMacAddress();
      if (str != null)
        localObject = str;
      return localObject;
    }
    catch (Exception localException)
    {
      MVLog.e("工具-Mac Error=" + localException.getMessage());
    }
    return "";
  }

  public static String getMacWhitMD5()
  {
    String str2 = "";
    try
    {
      String str3 = ((WifiManager)mContext.getSystemService("wifi")).getConnectionInfo().getMacAddress();
      String str1 = str2;
      if (str3 != null)
      {
        str1 = str2;
        if (isNotEmpty(str3))
          str1 = MD5(str3);
      }
      return str1;
    }
    catch (Exception localException)
    {
      MVLog.e("工具-Mac Error=" + localException.getMessage());
    }
    return "";
  }

  public static String getNetworkOperator()
  {
    try
    {
      Object localObject = (TelephonyManager)mContext.getSystemService("phone");
      if (((TelephonyManager)localObject).getNetworkOperator() != null)
      {
        localObject = ((TelephonyManager)localObject).getNetworkOperator();
        return localObject;
      }
    }
    catch (Exception localException)
    {
      MVLog.e("工具-getCarrierName=" + localException.getMessage());
    }
    return "";
  }

  public static String getProductModel()
  {
    try
    {
      String str = Build.MODEL;
      return str;
    }
    catch (Exception localException)
    {
      MVLog.e("工具-PhoneModel Error=" + localException.getMessage());
    }
    return "";
  }

  public static String getScreenOrientation()
  {
    int i = mContext.getResources().getConfiguration().orientation;
    return i + "";
  }

  private static String getString(byte[] paramArrayOfByte)
  {
    StringBuffer localStringBuffer = new StringBuffer();
    int i = 0;
    while (i < paramArrayOfByte.length)
    {
      localStringBuffer.append(paramArrayOfByte[i]);
      i += 1;
    }
    return localStringBuffer.toString();
  }

  public static String getSysteminfo()
  {
    try
    {
      String str = "Android%20" + Build.VERSION.RELEASE;
      return str;
    }
    catch (Exception localException)
    {
      MVLog.e("工具-SysVer Error=" + localException.getMessage());
    }
    return "";
  }

  public static String getm2id()
  {
    return MD5(getIMEI() + getAndroidid() + getDeviceSerial());
  }

  public static void init(Context paramContext)
  {
    mContext = paramContext;
  }

  public static boolean isNetEnable()
  {
    boolean bool2 = false;
    try
    {
      NetworkInfo localNetworkInfo = ((ConnectivityManager)mContext.getSystemService("connectivity")).getActiveNetworkInfo();
      boolean bool1 = bool2;
      if (localNetworkInfo != null)
      {
        boolean bool3 = localNetworkInfo.isConnected();
        bool1 = bool2;
        if (bool3)
          bool1 = true;
      }
      return bool1;
    }
    catch (Exception localException)
    {
      MVLog.e("工具-NetIsOn Error=" + localException.getMessage());
    }
    return false;
  }

  public static boolean isNotEmpty(String paramString)
  {
    return (paramString != null) && (!"".equals(paramString));
  }

  public static boolean isSDCardEnable()
  {
    return ("mounted".equals(Environment.getExternalStorageState())) && (Environment.getExternalStorageDirectory().canWrite());
  }

  public static String stackTraceToString(Throwable paramThrowable)
  {
    StringWriter localStringWriter = new StringWriter();
    PrintWriter localPrintWriter = new PrintWriter(localStringWriter);
    paramThrowable.printStackTrace(localPrintWriter);
    localPrintWriter.close();
    return localStringWriter.toString();
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     com.mediav.ads.sdk.log.Utils
 * JD-Core Version:    0.6.2
 */