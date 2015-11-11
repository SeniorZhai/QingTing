package com.alipay.sdk.util;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.telephony.TelephonyManager;
import android.text.TextUtils;

public class DeviceInfo
{
  static final String a = "com.alipay.android.app";
  static final String b = "com.eg.android.AlipayGphone";
  static final String c = "com.eg.android.AlipayGphoneRC";
  private static final String d = "00:00:00:00:00:00";
  private static DeviceInfo h = null;
  private String e;
  private String f;
  private String g;

  private DeviceInfo(Context paramContext)
  {
    try
    {
      Object localObject = (TelephonyManager)paramContext.getSystemService("phone");
      b(((TelephonyManager)localObject).getDeviceId());
      String str = ((TelephonyManager)localObject).getSubscriberId();
      localObject = str;
      if (str != null)
        localObject = (str + "000000000000000").substring(0, 15);
      this.e = ((String)localObject);
      this.g = ((WifiManager)paramContext.getSystemService("wifi")).getConnectionInfo().getMacAddress();
      return;
    }
    catch (Exception paramContext)
    {
      return;
    }
    finally
    {
      if (TextUtils.isEmpty(this.g))
        this.g = "00:00:00:00:00:00";
    }
    throw paramContext;
  }

  public static DeviceInfo a(Context paramContext)
  {
    if (h == null)
      h = new DeviceInfo(paramContext);
    return h;
  }

  private void a(String paramString)
  {
    String str = paramString;
    if (paramString != null)
      str = (paramString + "000000000000000").substring(0, 15);
    this.e = str;
  }

  public static NetConnectionType b(Context paramContext)
  {
    paramContext = (ConnectivityManager)paramContext.getSystemService("connectivity");
    try
    {
      paramContext = paramContext.getActiveNetworkInfo();
      if ((paramContext != null) && (paramContext.getType() == 0))
        return NetConnectionType.a(paramContext.getSubtype());
      if ((paramContext != null) && (paramContext.getType() == 1))
        return NetConnectionType.a;
      paramContext = NetConnectionType.o;
      return paramContext;
    }
    catch (Exception paramContext)
    {
    }
    return NetConnectionType.o;
  }

  private void b(String paramString)
  {
    String str = paramString;
    if (paramString != null)
    {
      paramString = paramString.getBytes();
      int i = 0;
      while (i < paramString.length)
      {
        if ((paramString[i] < 48) || (paramString[i] > 57))
          paramString[i] = 48;
        i += 1;
      }
      paramString = new String(paramString);
      str = (paramString + "000000000000000").substring(0, 15);
    }
    this.f = str;
  }

  public static String c(Context paramContext)
  {
    paramContext = a(paramContext);
    String str = paramContext.b();
    str = str + "|";
    paramContext = paramContext.a();
    if (TextUtils.isEmpty(paramContext));
    for (paramContext = str + "000000000000000"; ; paramContext = str + paramContext)
      return paramContext.substring(0, 8);
  }

  private String d()
  {
    String str1 = b();
    str1 = str1 + "|";
    String str2 = a();
    if (TextUtils.isEmpty(str2))
      return str1 + "000000000000000";
    return str1 + str2;
  }

  public final String a()
  {
    if (TextUtils.isEmpty(this.e))
      this.e = "000000000000000";
    return this.e;
  }

  public final String b()
  {
    if (TextUtils.isEmpty(this.f))
      this.f = "000000000000000";
    return this.f;
  }

  public final String c()
  {
    return this.g;
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     com.alipay.sdk.util.DeviceInfo
 * JD-Core Version:    0.6.2
 */