package com.tencent.open.a;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.os.Build.VERSION;
import android.telephony.TelephonyManager;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.WindowManager;

public class a
{
  static String a = null;
  private static String b = null;

  public static String a(Context paramContext)
  {
    if ((a != null) && (a.length() > 0))
      return a;
    if (paramContext == null)
      return "";
    try
    {
      paramContext = ((TelephonyManager)paramContext.getSystemService("phone")).getDeviceId();
      return paramContext;
    }
    catch (Exception paramContext)
    {
    }
    return "";
  }

  public static String b(Context paramContext)
  {
    try
    {
      if (b == null)
      {
        Object localObject = (WindowManager)paramContext.getSystemService("window");
        DisplayMetrics localDisplayMetrics = new DisplayMetrics();
        ((WindowManager)localObject).getDefaultDisplay().getMetrics(localDisplayMetrics);
        localObject = new StringBuilder();
        ((StringBuilder)localObject).append("imei=").append(a(paramContext)).append('&');
        ((StringBuilder)localObject).append("model=").append(Build.MODEL).append('&');
        ((StringBuilder)localObject).append("os=").append(Build.VERSION.RELEASE).append('&');
        ((StringBuilder)localObject).append("apilevel=").append(Build.VERSION.SDK_INT).append('&');
        ((StringBuilder)localObject).append("display=").append(localDisplayMetrics.widthPixels).append('*').append(localDisplayMetrics.heightPixels).append('&');
        ((StringBuilder)localObject).append("manu=").append(Build.MANUFACTURER).append("&");
        b = ((StringBuilder)localObject).toString();
      }
      paramContext = b;
      return paramContext;
    }
    catch (Exception paramContext)
    {
    }
    return null;
  }

  public static String c(Context paramContext)
  {
    paramContext = (ConnectivityManager)paramContext.getSystemService("connectivity");
    if (paramContext == null)
      return "MOBILE";
    paramContext = paramContext.getActiveNetworkInfo();
    if (paramContext != null)
      return paramContext.getTypeName();
    return "MOBILE";
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     com.tencent.open.a.a
 * JD-Core Version:    0.6.2
 */