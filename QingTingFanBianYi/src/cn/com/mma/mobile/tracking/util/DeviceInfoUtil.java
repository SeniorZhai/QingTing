package cn.com.mma.mobile.tracking.util;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
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
import java.math.BigInteger;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.security.MessageDigest;
import java.security.SecureRandom;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

public class DeviceInfoUtil
{
  private static final String CHAR_SET = "iso-8859-1";
  private static final String SHA1_ALGORITHM = "SHA-1";

  private static String SHA1(String paramString)
  {
    try
    {
      MessageDigest localMessageDigest = MessageDigest.getInstance("SHA-1");
      localMessageDigest.update(paramString.getBytes("iso-8859-1"), 0, paramString.length());
      paramString = convertToHex(localMessageDigest.digest());
      return paramString;
    }
    catch (Exception paramString)
    {
      Logger.e("ODIN Error generating generating SHA-1: " + paramString);
    }
    return null;
  }

  public static String appVersion(Context paramContext)
  {
    try
    {
      paramContext = paramContext.getPackageManager().getPackageInfo(paramContext.getPackageName(), 0).versionName;
      return paramContext;
    }
    catch (PackageManager.NameNotFoundException paramContext)
    {
    }
    return "1.0";
  }

  private static String convertToHex(byte[] paramArrayOfByte)
  {
    StringBuffer localStringBuffer = new StringBuffer();
    int i = 0;
    if (i >= paramArrayOfByte.length)
      return localStringBuffer.toString();
    int k = paramArrayOfByte[i] >>> 4 & 0xF;
    int j = 0;
    while (true)
    {
      if ((k >= 0) && (k <= 9))
        localStringBuffer.append((char)(k + 48));
      while (true)
      {
        k = paramArrayOfByte[i] & 0xF;
        if (j < 1)
          break label93;
        i += 1;
        break;
        localStringBuffer.append((char)(k - 10 + 97));
      }
      label93: j += 1;
    }
  }

  public static Map<String, String> fulfillTrackingInfo(Context paramContext)
  {
    HashMap localHashMap = new HashMap();
    localHashMap.put("LBS", LocationUtil.getInstance(paramContext).getLocation());
    String str = getMacAddress(paramContext);
    if (str != null)
      localHashMap.put("MAC", str.replaceAll(":", "").toUpperCase());
    localHashMap.put("ANDROIDID", getAndroidId(paramContext));
    localHashMap.put("OSVS", getOSVersion());
    localHashMap.put("TERM", getDevice());
    if (isWifi(paramContext));
    for (str = "1"; ; str = "0")
    {
      localHashMap.put("WIFI", str);
      localHashMap.put("ANAME", getAppName(paramContext));
      localHashMap.put("AKEY", getPackageName(paramContext));
      localHashMap.put("OSVS", getOSVersion());
      localHashMap.put("OS", "0");
      localHashMap.put("SCWH", getResolution(paramContext));
      localHashMap.put("IMEI", getImei(paramContext));
      localHashMap.put("SDKVS", "1.2");
      return localHashMap;
    }
  }

  private static String generateAndroidId(Context paramContext)
  {
    String str2 = Settings.Secure.getString(paramContext.getContentResolver(), "android_id");
    if ((str2 != null) && (!str2.equals("9774d56d682e549c")))
    {
      str1 = str2;
      if (str2.length() >= 15);
    }
    else
    {
      str1 = new BigInteger(64, new SecureRandom()).toString(16);
    }
    String str1 = CommonUtil.md5(str1);
    SharedPreferencedUtil.putString(paramContext, "cn.com.mma.mobile.tracking.other", "android_id", str1);
    return str1;
  }

  public static String getAndroidId(Context paramContext)
  {
    String str2 = SharedPreferencedUtil.getString(paramContext, "cn.com.mma.mobile.tracking.other", "android_id");
    String str1;
    if ((str2 != null) && (!str2.equals("")))
    {
      str1 = str2;
      if (!str2.equals("null"));
    }
    else
    {
      str1 = generateAndroidId(paramContext);
    }
    return str1;
  }

  public static String getAppName(Context paramContext)
  {
    PackageManager localPackageManager = paramContext.getPackageManager();
    try
    {
      paramContext = localPackageManager.getApplicationInfo(paramContext.getPackageName(), 128).loadLabel(localPackageManager).toString();
      return paramContext;
    }
    catch (PackageManager.NameNotFoundException paramContext)
    {
      paramContext.printStackTrace();
    }
    return "";
  }

  public static String getCarrier(Context paramContext)
  {
    return ((TelephonyManager)paramContext.getSystemService("phone")).getNetworkOperatorName();
  }

  public static String getDevice()
  {
    return Build.MODEL;
  }

  public static String getIP(Context paramContext)
  {
    try
    {
      paramContext = ((NetworkInterface)NetworkInterface.getNetworkInterfaces().nextElement()).getInetAddresses();
      InetAddress localInetAddress;
      do
      {
        if (!paramContext.hasMoreElements())
          return null;
        localInetAddress = (InetAddress)paramContext.nextElement();
      }
      while (localInetAddress.isLinkLocalAddress());
      paramContext = localInetAddress.getHostAddress();
      return paramContext;
    }
    catch (Exception paramContext)
    {
      paramContext.printStackTrace();
    }
    return null;
  }

  public static String getImei(Context paramContext)
  {
    return ((TelephonyManager)paramContext.getSystemService("phone")).getDeviceId();
  }

  public static String getLocale()
  {
    Locale localLocale = Locale.getDefault();
    return localLocale.getLanguage() + "_" + localLocale.getCountry();
  }

  public static String getMacAddress(Context paramContext)
  {
    String str = "";
    Object localObject = (WifiManager)paramContext.getSystemService("wifi");
    paramContext = str;
    if (localObject != null)
    {
      localObject = ((WifiManager)localObject).getConnectionInfo();
      paramContext = str;
      if (localObject != null)
        paramContext = ((WifiInfo)localObject).getMacAddress();
    }
    return paramContext;
  }

  public static String getModel()
  {
    String str1 = Build.DEVICE;
    String str2 = Build.ID;
    String str3 = Build.DISPLAY;
    String str4 = Build.PRODUCT;
    String str5 = Build.BOARD;
    String str6 = Build.BRAND;
    String str7 = Build.MODEL;
    return str1 + "," + str2 + "," + str3 + "," + str4 + "," + str5 + "," + str6 + "," + str7;
  }

  public static String getODIN1(Context paramContext)
  {
    return SHA1(Settings.System.getString(paramContext.getContentResolver(), "android_id"));
  }

  public static String getOSVersion()
  {
    return Build.VERSION.RELEASE;
  }

  public static String getPackageName(Context paramContext)
  {
    return paramContext.getPackageName();
  }

  public static String getResolution(Context paramContext)
  {
    paramContext = (WindowManager)paramContext.getSystemService("window");
    DisplayMetrics localDisplayMetrics = new DisplayMetrics();
    paramContext.getDefaultDisplay().getMetrics(localDisplayMetrics);
    return localDisplayMetrics.widthPixels + "x" + localDisplayMetrics.heightPixels;
  }

  public static boolean isEmulator(Context paramContext)
  {
    boolean bool2 = false;
    paramContext = Settings.Secure.getString(paramContext.getContentResolver(), "android_id");
    boolean bool1 = bool2;
    if (paramContext != null)
    {
      bool1 = bool2;
      if (paramContext.equals("9774d56d682e549c"))
        bool1 = true;
    }
    return bool1;
  }

  public static boolean isNetworkAvailable(Context paramContext)
  {
    if (paramContext != null)
    {
      paramContext = (ConnectivityManager)paramContext.getSystemService("connectivity");
      if (paramContext != null)
        break label21;
    }
    while (true)
    {
      return false;
      label21: paramContext = paramContext.getAllNetworkInfo();
      if (paramContext != null)
      {
        int i = 0;
        while (i < paramContext.length)
        {
          if (paramContext[i].getState() == NetworkInfo.State.CONNECTED)
            return true;
          i += 1;
        }
      }
    }
  }

  public static boolean isWifi(Context paramContext)
  {
    return ((ConnectivityManager)paramContext.getSystemService("connectivity")).getNetworkInfo(1).getState() == NetworkInfo.State.CONNECTED;
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     cn.com.mma.mobile.tracking.util.DeviceInfoUtil
 * JD-Core Version:    0.6.2
 */