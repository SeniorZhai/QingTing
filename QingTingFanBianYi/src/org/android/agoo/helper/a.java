package org.android.agoo.helper;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.text.TextUtils;
import com.umeng.message.proguard.Q;
import com.umeng.message.proguard.aI;
import com.umeng.message.proguard.ag;
import com.umeng.message.proguard.aq;
import com.umeng.message.proguard.as;
import com.umeng.message.proguard.as.a;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.android.agoo.client.AgooSettings;
import org.android.agoo.client.AgooSettings.Mode;
import org.apache.http.HttpHost;

public class a
{
  private static final String a = "HostClient";
  private static final String b = "AGOO_HOST";
  private static final String c = "AGOO_HOST_SIZE";
  private static final String d = "AGOO_HOST_VALUE_";
  private volatile int e = 0;
  private volatile ThreadPoolExecutor f;
  private volatile as g = null;
  private volatile Context h;
  private volatile String i;

  public a(Context paramContext, String paramString)
  {
    this.h = paramContext;
    this.i = paramString;
    this.f = ((ThreadPoolExecutor)Executors.newFixedThreadPool(2));
    this.g = new as();
  }

  private int a(Context paramContext, String[] paramArrayOfString)
  {
    int k = 0;
    paramContext = paramContext.getSharedPreferences("AGOO_HOST", 4).edit();
    paramContext.clear();
    int n = paramArrayOfString.length;
    int j = 0;
    while (j < n)
    {
      int m = k;
      if (!TextUtils.isEmpty(paramArrayOfString[j]))
      {
        m = k;
        if (a(paramArrayOfString[j]))
        {
          paramContext.putString("AGOO_HOST_VALUE_" + k, paramArrayOfString[j]);
          m = k + 1;
        }
      }
      j += 1;
      k = m;
    }
    paramContext.putInt("AGOO_HOST_SIZE", k);
    paramContext.commit();
    return k;
  }

  private static String a(Context paramContext, int paramInt)
  {
    return paramContext.getSharedPreferences("AGOO_HOST", 4).getString("AGOO_HOST_VALUE_" + paramInt, null);
  }

  private void a(as.a parama, b paramb, AgooSettings.Mode paramMode)
  {
    if (parama == null)
    {
      paramb.a(408, paramMode.getPushApollIp());
      return;
    }
    if (200 != parama.a)
    {
      paramb.a(404, "get [" + paramMode.getPushApollIp() + "] error");
      return;
    }
    if (TextUtils.isEmpty(parama.b))
    {
      paramb.a(504, "get [" + paramMode.getPushApollIp() + "] error");
      return;
    }
    if (TextUtils.indexOf(parama.b, "<html>") != -1)
    {
      paramb.a(307, "get [" + paramMode.getPushApollIp() + "] error");
      return;
    }
    parama = parama.b.split("\\|");
    if (parama.length <= 0)
    {
      paramb.a(504, "get [" + paramMode.getPushApollIp() + "] error");
      return;
    }
    if (parama.length <= 0)
    {
      paramb.a(504, "get [" + paramMode.getPushApollIp() + "] error");
      return;
    }
    if (a(this.h, parama) <= 0)
    {
      paramb.a(504, "get [" + paramMode.getPushApollIp() + "] error");
      return;
    }
    paramb.a(a(this.h, 0));
  }

  private boolean a(String paramString)
  {
    String str = "(" + "(2[0-4]\\d)|(25[0-5])" + ")|(" + "1\\d{2}" + ")|(" + "[1-9]\\d" + ")|(" + "\\d" + ")";
    return Pattern.compile("(" + str + ").(" + str + ").(" + str + ").(" + str + "):\\d*$").matcher(paramString).matches();
  }

  private void b(b paramb)
  {
    this.f.submit(new a(this.h, this.i, paramb));
  }

  public String a(Context paramContext)
  {
    paramContext = PhoneHelper.getImsi(paramContext);
    if (!TextUtils.isEmpty(paramContext))
    {
      if ((paramContext.startsWith("46000")) || (paramContext.startsWith("46002")))
        return "china_mobile";
      if (paramContext.startsWith("46001"))
        return "china_unicom";
      if (paramContext.startsWith("46003"))
        return "china_telecom";
    }
    return null;
  }

  public void a(b paramb)
  {
    if (paramb == null)
      throw new NullPointerException("IHostHandler is null");
    int j = b(this.h);
    if (j <= 0)
    {
      Q.c("HostClient", "local host size <=0");
      b(paramb);
      return;
    }
    if (this.e >= j)
    {
      Q.c("HostClient", "next host >= localhost size");
      b(paramb);
      return;
    }
    String str = a(this.h, this.e);
    if (TextUtils.isEmpty(str))
    {
      Q.c("HostClient", "next host == null");
      b(paramb);
      return;
    }
    Q.c("HostClient", "next host [" + str + "]");
    paramb.a(str);
    this.e += 1;
  }

  public int b(Context paramContext)
  {
    return paramContext.getSharedPreferences("AGOO_HOST", 4).getInt("AGOO_HOST_SIZE", 0);
  }

  class a
    implements Runnable
  {
    String a;
    a.b b;
    Context c;

    public a(Context paramString, String paramb, a.b arg4)
    {
      this.c = paramString;
      this.a = paramb;
      Object localObject;
      this.b = localObject;
    }

    public void run()
    {
      Object localObject1 = null;
      AgooSettings.Mode localMode = AgooSettings.getMode(this.c);
      try
      {
        Object localObject2 = new aq();
        ((aq)localObject2).a("id", this.a);
        ((aq)localObject2).a("app_version_code", "" + aI.b(this.c));
        ((aq)localObject2).a("agoo_version_code", "" + AgooSettings.getAgooReleaseTime());
        Object localObject3 = new ag(this.c);
        Object localObject4 = ((ag)localObject3).c();
        if (!TextUtils.isEmpty((CharSequence)localObject4))
          ((aq)localObject2).a("agoo_network", (String)localObject4);
        localObject3 = ((ag)localObject3).b();
        if (!TextUtils.isEmpty((CharSequence)localObject3))
          ((aq)localObject2).a("agoo_apn", (String)localObject3);
        localObject3 = a.this.a(this.c);
        if (!TextUtils.isEmpty((CharSequence)localObject3))
          ((aq)localObject2).a("agoo_operators", (String)localObject3);
        localObject4 = localMode.getPushApollIp();
        localObject3 = AgooSettings.getApollUrl(this.c);
        int i = localMode.getPushApollPort();
        if (a.a(a.this) == null)
          a.a(a.this, new as());
        if (!AgooSettings.isAgooTestMode(this.c))
          localObject2 = a.a(a.this).get(this.c, (String)localObject3, (aq)localObject2);
        for (localObject1 = localObject2; ; localObject1 = localObject2)
        {
          a.a(a.this, localObject1, this.b, localMode);
          return;
          Q.c("HostClient", "test host ip [ " + (String)localObject4 + " ]");
          localObject4 = new HttpHost((String)localObject4, i);
          localObject2 = a.a(a.this).get(this.c, (HttpHost)localObject4, (String)localObject3, (aq)localObject2);
        }
      }
      catch (Throwable localThrowable)
      {
        while (true)
          Q.d("HostClient", "host Throwable", localThrowable);
      }
    }
  }

  public static abstract interface b
  {
    public abstract void a(int paramInt, String paramString);

    public abstract void a(String paramString);
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     org.android.agoo.helper.a
 * JD-Core Version:    0.6.2
 */