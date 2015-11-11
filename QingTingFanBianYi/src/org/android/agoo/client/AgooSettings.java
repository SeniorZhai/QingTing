package org.android.agoo.client;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.text.TextUtils;
import com.umeng.message.proguard.P;
import com.umeng.message.proguard.Q;
import java.util.Calendar;

public class AgooSettings
{
  private static final String a = "42.120.111.1";
  private static final String b = "110.75.40.7";
  private static final String c = "110.75.120.15";
  private static final String d = "agoo_mode";
  private static final String e = "agoo_security_mode";
  private static final long f = 20131220L;
  private static final String g = "AGOO_MTOP_DOMAIN";
  private static final String h = "AGOO_APOLL_DOMAIN";
  private static final String i = "agoo_start_time";
  private static final String j = "agoo_end_time";

  public static long getAgooReleaseTime()
  {
    return 20131220L;
  }

  public static String getApollUrl(Context paramContext)
  {
    return String.format("http://%s/activeip/", new Object[] { P.g(paramContext).getString("AGOO_APOLL_DOMAIN", "upoll.umengcloud.com") });
  }

  public static Mode getMode(Context paramContext)
  {
    try
    {
      paramContext = P.g(paramContext).getString("AGOO_MTOP_DOMAIN", "utop.umengcloud.com");
      if (TextUtils.indexOf(paramContext, "waptest") != -1)
        paramContext = Mode.TEST;
      while (true)
      {
        return paramContext;
        if (TextUtils.indexOf(paramContext, "wapa") != -1)
          paramContext = Mode.PREVIEW;
        else
          paramContext = Mode.RELEASE;
      }
    }
    finally
    {
    }
    throw paramContext;
  }

  public static String getPullUrl(Context paramContext)
  {
    return String.format("http://%s/rest/api3.do", new Object[] { P.g(paramContext).getString("AGOO_MTOP_DOMAIN", "utop.umengcloud.com") });
  }

  public static long getTargetTime(Context paramContext)
  {
    return getTargetTime(paramContext, System.currentTimeMillis());
  }

  public static long getTargetTime(Context paramContext, long paramLong)
  {
    if (paramContext == null);
    int k;
    int m;
    do
    {
      return -1L;
      paramContext = P.g(paramContext);
      k = paramContext.getInt("agoo_start_time", -1);
      m = paramContext.getInt("agoo_end_time", -1);
    }
    while ((k == -1) || (m == -1));
    paramContext = Calendar.getInstance();
    paramContext.setTimeInMillis(paramLong);
    int n = paramContext.get(11) * 60 * 60 + paramContext.get(12) * 60 + paramContext.get(13);
    if (n < k)
      paramContext.add(13, k - n);
    while (true)
    {
      return paramContext.getTimeInMillis();
      if (n <= m)
        break;
      paramContext.add(13, k - n + 86400);
    }
  }

  public static boolean isAgooSoSecurityMode(Context paramContext)
  {
    return P.g(paramContext).getBoolean("agoo_security_mode", false);
  }

  public static boolean isAgooTestMode(Context paramContext)
  {
    return getMode(paramContext) != Mode.RELEASE;
  }

  public static void setAgooMode(Context paramContext, Mode paramMode)
  {
  }

  public static void setAgooSecurityMode(Context paramContext, boolean paramBoolean)
  {
    if (paramContext != null)
    {
      paramContext = P.g(paramContext).edit();
      paramContext.putBoolean("agoo_security_mode", paramBoolean);
      paramContext.commit();
    }
  }

  public static void setAvailableTime(Context paramContext, int paramInt1, int paramInt2, int paramInt3, int paramInt4)
  {
    if (paramContext != null)
    {
      paramContext = P.g(paramContext).edit();
      paramContext.putInt("agoo_start_time", paramInt1 * 60 * 60 + paramInt2 * 60 + 0);
      paramContext.putInt("agoo_end_time", paramInt3 * 60 * 60 + paramInt4 * 60 + 0);
      paramContext.commit();
    }
  }

  public static void setDebugMode(boolean paramBoolean)
  {
    Q.b(paramBoolean);
  }

  public static void setDomain(Context paramContext, String paramString1, String paramString2)
  {
    if (paramContext != null)
    {
      paramContext = P.g(paramContext).edit();
      paramContext.putString("AGOO_MTOP_DOMAIN", paramString1);
      paramContext.putString("AGOO_APOLL_DOMAIN", paramString2);
      paramContext.commit();
    }
  }

  public static void setLog(Context paramContext, boolean paramBoolean1, boolean paramBoolean2)
  {
    P.a(paramContext, paramBoolean1, paramBoolean2);
    Q.a(paramContext);
  }

  public static void setLog2File(boolean paramBoolean)
  {
    Q.a(paramBoolean);
  }

  public static void setUTVersion(Context paramContext, String paramString)
  {
    P.a(paramContext, paramString);
  }

  public static abstract enum Mode
  {
    private int a;

    private Mode(int paramInt)
    {
      this.a = paramInt;
    }

    public abstract String getPushApollIp();

    public abstract int getPushApollPort();

    public int getValue()
    {
      return this.a;
    }
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     org.android.agoo.client.AgooSettings
 * JD-Core Version:    0.6.2
 */