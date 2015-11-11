package org.android.agoo.helper;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.provider.Settings.Secure;
import android.telephony.TelephonyManager;
import com.umeng.message.proguard.P;
import java.lang.reflect.Method;
import java.util.Random;

public class PhoneHelper
{
  public static final String IMEI = "imei";
  public static final String IMSI = "imsi";
  public static final String MACADDRESS = "mac_address";

  private static String a()
  {
    StringBuffer localStringBuffer = new StringBuffer();
    long l = System.currentTimeMillis();
    Object localObject = Long.toString(l);
    localStringBuffer.append(((String)localObject).substring(((String)localObject).length() - 5));
    localObject = new StringBuffer();
    ((StringBuffer)localObject).append(Build.MODEL.replaceAll(" ", ""));
    while (((StringBuffer)localObject).length() < 6)
      ((StringBuffer)localObject).append('0');
    localStringBuffer.append(((StringBuffer)localObject).substring(0, 6));
    localObject = new Random(l);
    for (l = 0L; l < 4096L; l = ((Random)localObject).nextLong());
    localStringBuffer.append(Long.toHexString(l).substring(0, 4));
    return localStringBuffer.toString();
  }

  public static String getAndroidId(Context paramContext)
  {
    return Settings.Secure.getString(paramContext.getContentResolver(), "android_id");
  }

  public static String getImei(Context paramContext)
  {
    SharedPreferences localSharedPreferences = P.f(paramContext);
    String str = localSharedPreferences.getString("imei", null);
    Object localObject;
    if (str != null)
    {
      localObject = str;
      if (str.length() != 0);
    }
    else
    {
      localObject = ((TelephonyManager)paramContext.getSystemService("phone")).getDeviceId();
      if (localObject != null)
      {
        paramContext = (Context)localObject;
        if (((String)localObject).length() != 0);
      }
      else
      {
        paramContext = a();
      }
      for (paramContext = paramContext.replaceAll(" ", "").trim(); paramContext.length() < 15; paramContext = "0" + paramContext);
      localObject = localSharedPreferences.edit();
      ((SharedPreferences.Editor)localObject).putString("imei", paramContext);
      ((SharedPreferences.Editor)localObject).commit();
      localObject = paramContext;
    }
    return ((String)localObject).trim();
  }

  public static String getImsi(Context paramContext)
  {
    SharedPreferences localSharedPreferences = P.f(paramContext);
    String str = localSharedPreferences.getString("imsi", null);
    Object localObject;
    if (str != null)
    {
      localObject = str;
      if (str.length() != 0);
    }
    else
    {
      localObject = ((TelephonyManager)paramContext.getSystemService("phone")).getSubscriberId();
      if (localObject != null)
      {
        paramContext = (Context)localObject;
        if (((String)localObject).length() != 0);
      }
      else
      {
        paramContext = a();
      }
      for (paramContext = paramContext.replaceAll(" ", "").trim(); paramContext.length() < 15; paramContext = "0" + paramContext);
      localObject = localSharedPreferences.edit();
      ((SharedPreferences.Editor)localObject).putString("imsi", paramContext);
      ((SharedPreferences.Editor)localObject).commit();
      localObject = paramContext;
    }
    return localObject;
  }

  public static String getLocalMacAddress(Context paramContext)
  {
    String str = ((WifiManager)paramContext.getSystemService("wifi")).getConnectionInfo().getMacAddress();
    if ((str == null) || ("".equals(str)))
      return P.f(paramContext).getString("mac_address", "");
    paramContext = P.f(paramContext).edit();
    paramContext.putString("mac_address", str);
    paramContext.commit();
    return str;
  }

  public static String getOriginalImei(Context paramContext)
  {
    String str = ((TelephonyManager)paramContext.getSystemService("phone")).getDeviceId();
    paramContext = str;
    if (str != null)
      paramContext = str.trim();
    return paramContext;
  }

  public static String getOriginalImsi(Context paramContext)
  {
    String str = ((TelephonyManager)paramContext.getSystemService("phone")).getSubscriberId();
    paramContext = str;
    if (str != null)
      paramContext = str.trim();
    return paramContext;
  }

  public static String getSerialNum()
  {
    try
    {
      Object localObject = Class.forName("android.os.SystemProperties");
      localObject = (String)((Class)localObject).getMethod("get", new Class[] { String.class, String.class }).invoke(localObject, new Object[] { "ro.serialno", "unknown" });
      return localObject;
    }
    catch (Throwable localThrowable)
    {
    }
    return null;
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     org.android.agoo.helper.PhoneHelper
 * JD-Core Version:    0.6.2
 */