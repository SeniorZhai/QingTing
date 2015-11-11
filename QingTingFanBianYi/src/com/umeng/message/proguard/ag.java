package com.umeng.message.proguard;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.text.TextUtils;

public class ag
{
  static final String a = "wifi";
  static final String b = "wimax";
  static final String c = "mobile";
  static final String d = "gsm";
  static final String e = "gprs";
  static final String f = "edge";
  static final String g = "cdma";
  static final String h = "umts";
  static final String i = "hspa";
  static final String j = "hsupa";
  static final String k = "hsdpa";
  static final String l = "ehrpd";
  static final String m = "evdo0";
  static final String n = "evdoa";
  static final String o = "evdob";
  static final String p = "lte";
  static final String q = "umb";
  static final String r = "hspa+";
  static final String s = "unknown";
  static final String t = "wifi";
  static final String u = "2g";
  static final String v = "3g";
  static final String w = "4g";
  static final String x = "none";
  static final String y = "1xrtt";
  private static final String z = "ConnectManager";
  private String A;
  private int B;
  private String C;
  private boolean D = false;
  private String E;

  public ag(Context paramContext)
  {
    b(paramContext);
  }

  private void a(Context paramContext, NetworkInfo paramNetworkInfo)
  {
    try
    {
      if (paramNetworkInfo.getExtraInfo() != null)
      {
        paramContext = paramNetworkInfo.getExtraInfo().toUpperCase().trim();
        if (paramContext != null)
        {
          this.C = paramContext;
          if ((paramContext.indexOf("CMWAP") != -1) || (paramContext.indexOf("UNIWAP") != -1) || (paramContext.indexOf("3GWAP") != -1))
          {
            this.D = true;
            this.A = "10.0.0.172";
            this.B = 80;
            return;
          }
          if (paramContext.indexOf("CTWAP") != -1)
          {
            this.D = true;
            this.A = "10.0.0.200";
            this.B = 80;
            return;
          }
        }
      }
    }
    catch (Throwable paramContext)
    {
      paramContext.printStackTrace();
      return;
    }
    this.D = false;
  }

  public static boolean a(Context paramContext)
  {
    try
    {
      paramContext = ((ConnectivityManager)paramContext.getApplicationContext().getSystemService("connectivity")).getActiveNetworkInfo();
      if (paramContext != null)
      {
        boolean bool = paramContext.isConnectedOrConnecting();
        return bool;
      }
    }
    catch (Throwable paramContext)
    {
    }
    return false;
  }

  public static boolean a(String paramString)
  {
    boolean bool = false;
    try
    {
      int i1 = Runtime.getRuntime().exec("ping -c 1 -w 5 " + paramString).waitFor();
      if (i1 == 0)
        bool = true;
      return bool;
    }
    catch (Throwable paramString)
    {
      Q.e("ConnectManager", "onping", paramString);
    }
    return false;
  }

  private void b(Context paramContext)
  {
    try
    {
      NetworkInfo localNetworkInfo = ((ConnectivityManager)paramContext.getApplicationContext().getSystemService("connectivity")).getActiveNetworkInfo();
      if (localNetworkInfo != null)
      {
        if ("wifi".equals(localNetworkInfo.getTypeName().toLowerCase()))
        {
          this.E = "wifi";
          this.D = false;
          return;
        }
        a(paramContext, localNetworkInfo);
        b(localNetworkInfo.getSubtypeName().toLowerCase());
        return;
      }
    }
    catch (Throwable paramContext)
    {
    }
  }

  private void b(String paramString)
  {
    if (TextUtils.isEmpty(paramString))
      this.E = "none";
    do
    {
      return;
      if ((paramString.equals("gsm")) || (paramString.equals("gprs")) || (paramString.equals("edge")))
      {
        this.E = "2g";
        return;
      }
      if ((paramString.startsWith("cdma")) || (paramString.equals("umts")) || (paramString.equals("1xrtt")) || (paramString.equals("ehrpd")) || (paramString.equals("evdo0")) || (paramString.equals("evdoa")) || (paramString.equals("evdob")) || (paramString.equals("hsupa")) || (paramString.equals("hsdpa")) || (paramString.equals("hspa")))
      {
        this.E = "3g";
        return;
      }
    }
    while ((!paramString.equals("lte")) && (!paramString.equals("umb")) && (!paramString.equals("hspa+")));
    this.E = "4g";
  }

  public boolean a()
  {
    return this.D;
  }

  public String b()
  {
    return this.C;
  }

  public String c()
  {
    return this.E;
  }

  public String d()
  {
    return this.A;
  }

  public int e()
  {
    return this.B;
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     com.umeng.message.proguard.ag
 * JD-Core Version:    0.6.2
 */