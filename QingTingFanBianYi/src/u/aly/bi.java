package u.aly;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.res.Configuration;
import android.content.res.Resources;
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
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Field;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;
import javax.microedition.khronos.opengles.GL10;

public class bi
{
  protected static final String a = bi.class.getName();
  public static final String b = "";
  public static final String c = "2G/3G";
  public static final String d = "Wi-Fi";
  public static final int e = 8;

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

  public static int a(Date paramDate1, Date paramDate2)
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

  public static String a()
  {
    String str2 = null;
    Object localObject2 = null;
    String str1 = null;
    Object localObject1 = localObject2;
    try
    {
      FileReader localFileReader = new FileReader("/proc/cpuinfo");
      localObject1 = str1;
      if (localFileReader != null)
      {
        str1 = str2;
        localObject1 = localObject2;
      }
      try
      {
        BufferedReader localBufferedReader = new BufferedReader(localFileReader, 1024);
        str1 = str2;
        localObject1 = localObject2;
        str2 = localBufferedReader.readLine();
        str1 = str2;
        localObject1 = str2;
        localBufferedReader.close();
        str1 = str2;
        localObject1 = str2;
        localFileReader.close();
        localObject1 = str2;
        if (localObject1 != null)
          return ((String)localObject1).substring(((String)localObject1).indexOf(':') + 1).trim();
      }
      catch (IOException localIOException)
      {
        while (true)
        {
          localObject1 = str1;
          bj.b(a, "Could not read from file /proc/cpuinfo", localIOException);
          localObject1 = str1;
        }
      }
    }
    catch (FileNotFoundException localFileNotFoundException)
    {
      while (true)
        bj.b(a, "Could not open file /proc/cpuinfo", localFileNotFoundException);
    }
    return "";
  }

  public static String a(Date paramDate)
  {
    return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.US).format(paramDate);
  }

  public static Date a(String paramString)
  {
    try
    {
      paramString = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.US).parse(paramString);
      return paramString;
    }
    catch (Exception paramString)
    {
    }
    return null;
  }

  public static boolean a(Context paramContext)
  {
    return paramContext.getResources().getConfiguration().locale.toString().equals(Locale.CHINA.toString());
  }

  public static boolean a(Context paramContext, String paramString)
  {
    return paramContext.getPackageManager().checkPermission(paramString, paramContext.getPackageName()) == 0;
  }

  public static boolean a(String paramString, Context paramContext)
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

  public static String[] a(GL10 paramGL10)
  {
    try
    {
      String str = paramGL10.glGetString(7936);
      paramGL10 = paramGL10.glGetString(7937);
      return new String[] { str, paramGL10 };
    }
    catch (Exception paramGL10)
    {
      bj.b(a, "Could not read gpu infor:", paramGL10);
    }
    return new String[0];
  }

  public static boolean b()
  {
    return Environment.getExternalStorageState().equals("mounted");
  }

  public static boolean b(Context paramContext)
  {
    return paramContext.getResources().getConfiguration().orientation == 1;
  }

  public static String c()
  {
    Date localDate = new Date();
    return new SimpleDateFormat("yyyy-MM-dd", Locale.US).format(localDate);
  }

  public static String c(Context paramContext)
  {
    try
    {
      int i = paramContext.getPackageManager().getPackageInfo(paramContext.getPackageName(), 0).versionCode;
      return String.valueOf(i);
    }
    catch (PackageManager.NameNotFoundException paramContext)
    {
    }
    return "";
  }

  public static String d(Context paramContext)
  {
    try
    {
      paramContext = paramContext.getPackageManager().getPackageInfo(paramContext.getPackageName(), 0).versionName;
      return paramContext;
    }
    catch (PackageManager.NameNotFoundException paramContext)
    {
    }
    return "";
  }

  public static String e(Context paramContext)
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

  public static String f(Context paramContext)
  {
    Object localObject = (TelephonyManager)paramContext.getSystemService("phone");
    if (localObject == null)
      bj.e(a, "No IMEI.");
    try
    {
      if (a(paramContext, "android.permission.READ_PHONE_STATE"))
      {
        str = ((TelephonyManager)localObject).getDeviceId();
        localObject = str;
        if (TextUtils.isEmpty(str))
        {
          bj.e(a, "No IMEI.");
          str = p(paramContext);
          localObject = str;
          if (TextUtils.isEmpty(str))
          {
            bj.e(a, "Failed to take mac as IMEI. Try to use Secure.ANDROID_ID instead.");
            localObject = Settings.Secure.getString(paramContext.getContentResolver(), "android_id");
            bj.a(a, "getDeviceId: Secure.ANDROID_ID: " + (String)localObject);
          }
        }
        return localObject;
      }
    }
    catch (Exception localException)
    {
      while (true)
      {
        bj.e(a, "No IMEI.", localException);
        String str = "";
      }
    }
  }

  public static String g(Context paramContext)
  {
    return bv.b(f(paramContext));
  }

  public static String h(Context paramContext)
  {
    try
    {
      paramContext = (TelephonyManager)paramContext.getSystemService("phone");
      if (paramContext == null)
        return "";
      paramContext = paramContext.getNetworkOperatorName();
      return paramContext;
    }
    catch (Exception paramContext)
    {
      paramContext.printStackTrace();
    }
    return "";
  }

  public static String i(Context paramContext)
  {
    try
    {
      DisplayMetrics localDisplayMetrics = new DisplayMetrics();
      ((WindowManager)paramContext.getSystemService("window")).getDefaultDisplay().getMetrics(localDisplayMetrics);
      int i = localDisplayMetrics.widthPixels;
      paramContext = String.valueOf(localDisplayMetrics.heightPixels) + "*" + String.valueOf(i);
      return paramContext;
    }
    catch (Exception paramContext)
    {
      paramContext.printStackTrace();
    }
    return "";
  }

  public static String[] j(Context paramContext)
  {
    String[] arrayOfString = new String[2];
    arrayOfString[0] = "";
    arrayOfString[1] = "";
    try
    {
      if (paramContext.getPackageManager().checkPermission("android.permission.ACCESS_NETWORK_STATE", paramContext.getPackageName()) != 0)
      {
        arrayOfString[0] = "";
        return arrayOfString;
      }
      paramContext = (ConnectivityManager)paramContext.getSystemService("connectivity");
      if (paramContext == null)
      {
        arrayOfString[0] = "";
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
        return arrayOfString;
      }
    }
    catch (Exception paramContext)
    {
      paramContext.printStackTrace();
    }
    return arrayOfString;
  }

  public static boolean k(Context paramContext)
  {
    return "Wi-Fi".equals(j(paramContext)[0]);
  }

  public static boolean l(Context paramContext)
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

  public static int m(Context paramContext)
  {
    try
    {
      paramContext = Calendar.getInstance(x(paramContext));
      if (paramContext != null)
      {
        int i = paramContext.getTimeZone().getRawOffset() / 3600000;
        return i;
      }
    }
    catch (Exception paramContext)
    {
      bj.a(a, "error in getTimeZone", paramContext);
    }
    return 8;
  }

  public static String[] n(Context paramContext)
  {
    String[] arrayOfString = new String[2];
    try
    {
      paramContext = x(paramContext);
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
      bj.b(a, "error in getLocaleInfo", paramContext);
    }
    return arrayOfString;
  }

  public static String o(Context paramContext)
  {
    try
    {
      paramContext = paramContext.getPackageManager().getApplicationInfo(paramContext.getPackageName(), 128);
      if (paramContext != null)
      {
        paramContext = paramContext.metaData.getString("UMENG_APPKEY");
        if (paramContext != null)
          return paramContext.trim();
        bj.b(a, "Could not read UMENG_APPKEY meta-data from AndroidManifest.xml.");
      }
      return null;
    }
    catch (Exception paramContext)
    {
      while (true)
        bj.b(a, "Could not read UMENG_APPKEY meta-data from AndroidManifest.xml.", paramContext);
    }
  }

  public static String p(Context paramContext)
  {
    try
    {
      WifiManager localWifiManager = (WifiManager)paramContext.getSystemService("wifi");
      if (a(paramContext, "android.permission.ACCESS_WIFI_STATE"))
        return localWifiManager.getConnectionInfo().getMacAddress();
      bj.e(a, "Could not get mac address.[no permission android.permission.ACCESS_WIFI_STATE");
      return "";
    }
    catch (Exception paramContext)
    {
      while (true)
        bj.e(a, "Could not get mac address." + paramContext.toString());
    }
  }

  public static String q(Context paramContext)
  {
    paramContext = r(paramContext);
    if (paramContext != null)
    {
      StringBuffer localStringBuffer = new StringBuffer();
      localStringBuffer.append(paramContext[0]);
      localStringBuffer.append("*");
      localStringBuffer.append(paramContext[1]);
      return localStringBuffer.toString();
    }
    return "Unknown";
  }

  public static int[] r(Context paramContext)
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
        if ((paramContext.getApplicationInfo().flags & 0x2000) == 0)
        {
          j = a(localDisplayMetrics, "noncompatWidthPixels");
          i = a(localDisplayMetrics, "noncompatHeightPixels");
          break label130;
          i = localDisplayMetrics.widthPixels;
          j = localDisplayMetrics.heightPixels;
          k = i;
          i = j;
          label75: paramContext = new int[2];
          if (k > i)
          {
            paramContext[0] = i;
            paramContext[1] = k;
            return paramContext;
          }
          paramContext[0] = k;
          paramContext[1] = i;
          return paramContext;
        }
      }
      catch (Exception paramContext)
      {
        bj.b(a, "read resolution fail", paramContext);
        return null;
      }
      label130: 
      do
      {
        k = j;
        break label75;
        i = -1;
        j = -1;
        if (j == -1)
          break;
      }
      while (i != -1);
    }
  }

  public static String s(Context paramContext)
  {
    try
    {
      paramContext = ((TelephonyManager)paramContext.getSystemService("phone")).getNetworkOperatorName();
      return paramContext;
    }
    catch (Exception paramContext)
    {
      bj.a(a, "read carrier fail", paramContext);
    }
    return "Unknown";
  }

  public static String t(Context paramContext)
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
          bj.a(a, "Could not read UMENG_CHANNEL meta-data from AndroidManifest.xml.");
          return "Unknown";
        }
      }
    }
    catch (Exception paramContext)
    {
      bj.a(a, "Could not read UMENG_CHANNEL meta-data from AndroidManifest.xml.");
      paramContext.printStackTrace();
    }
    return "Unknown";
  }

  public static String u(Context paramContext)
  {
    return paramContext.getPackageName();
  }

  public static String v(Context paramContext)
  {
    return paramContext.getPackageManager().getApplicationLabel(paramContext.getApplicationInfo()).toString();
  }

  public static boolean w(Context paramContext)
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

  private static Locale x(Context paramContext)
  {
    Object localObject = null;
    try
    {
      Configuration localConfiguration = new Configuration();
      localConfiguration.setToDefaults();
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
        bj.b(a, "fail to read user config locale");
        paramContext = (Context)localObject;
      }
    }
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     u.aly.bi
 * JD-Core Version:    0.6.2
 */