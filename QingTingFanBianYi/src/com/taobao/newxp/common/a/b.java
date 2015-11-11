package com.taobao.newxp.common.a;

import android.content.Context;
import android.location.Location;
import android.os.Build.VERSION;
import android.text.TextUtils;
import com.taobao.newxp.common.AlimmContext;
import com.taobao.newxp.common.ExchangeConstants;
import com.taobao.newxp.common.a.a.e;
import com.taobao.newxp.common.a.a.f;
import com.taobao.newxp.common.a.a.h;
import com.taobao.newxp.common.a.a.o;
import com.taobao.newxp.common.a.a.p;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.protocol.BasicHttpContext;

public class b
{
  private static final long a = 10000L;
  private static final String b = "http://gm.mmstat.com/%s?gokey=";
  private static final String c = "af.2.1";
  private static final String d = "wapebs.3.1";
  private static final String e = "wapebs.3.2";
  private static final String f = "wapebs.4.3";
  private static final String g = "http://gm.mmstat.com/%s?";
  private static final String h = b.class.getName();
  private static b m = new b();
  private int i = 0;
  private Timer j;
  private Context k;
  private ExecutorService l = Executors.newSingleThreadExecutor();

  public static b a()
  {
    return m;
  }

  private void a(String paramString)
  {
    String str = null;
    try
    {
      paramString = String.format("http://gm.mmstat.com/%s?gokey=", new Object[] { "wapebs.3.2" }) + e.a(this.k, paramString).a();
      str = paramString;
      android.util.Log.i("statistics", "MMSTATA data: " + paramString);
      if ((paramString != null) && (paramString.trim().length() > 0))
      {
        paramString = new a(paramString);
        this.l.execute(paramString);
      }
      return;
    }
    catch (Exception paramString)
    {
      while (true)
      {
        paramString.printStackTrace();
        paramString = str;
      }
    }
  }

  private void b(String paramString)
  {
    String str = null;
    try
    {
      paramString = String.format("http://gm.mmstat.com/%s?gokey=", new Object[] { "wapebs.3.1" }) + p.a(paramString).a();
      str = paramString;
      android.util.Log.i("statistics", "MMSTATA data: " + paramString);
      if ((paramString != null) && (paramString.trim().length() > 0))
      {
        paramString = new a(paramString);
        this.l.execute(paramString);
      }
      return;
    }
    catch (Exception paramString)
    {
      while (true)
      {
        paramString.printStackTrace();
        paramString = str;
      }
    }
  }

  private void c()
  {
    g();
    e();
  }

  private void d()
  {
    f();
    g();
  }

  private void e()
  {
    TimerTask local1 = new TimerTask()
    {
      public void run()
      {
        b.a(b.this, 3);
        b.a(b.this);
      }
    };
    this.j = new Timer();
    this.j.schedule(local1, 10000L);
  }

  private void f()
  {
    if (this.j != null)
    {
      this.j.cancel();
      this.j = null;
    }
  }

  private void g()
  {
    Object localObject = null;
    try
    {
      String str = String.format("http://gm.mmstat.com/%s?gokey=", new Object[] { "af.2.1" }) + h.a(null).a(this.k, this.i);
      localObject = str;
      android.util.Log.i("statistics", "Pingback data: " + str);
      localObject = str;
      if ((localObject != null) && (((String)localObject).trim().length() > 0))
      {
        localObject = new a((String)localObject);
        this.l.execute((Runnable)localObject);
      }
      return;
    }
    catch (Exception localException)
    {
      while (true)
        localException.printStackTrace();
    }
  }

  private void h()
  {
    try
    {
      localf = (f)a.a().b(4);
      locala = AlimmContext.getAliContext().getAppUtils();
      if (localf != null)
      {
        if (locala == null)
          return;
        arrayOfString = locala.E();
        localObject = locala.F();
        if (localObject != null)
        {
          localObject = ((Location)localObject).getLatitude() + "," + ((Location)localObject).getLongitude();
          if (!TextUtils.isEmpty(com.taobao.munion.base.caches.b.i))
          {
            str1 = com.taobao.munion.base.caches.b.i;
            str3 = String.format("http://gm.mmstat.com/%s?", new Object[] { "wapebs.4.3" });
            com.taobao.munion.base.Log.i("handleClickTime @Time:%s,landingUrlLoadStartTime @Time:%s,checkoutForFirstImageLoad @Time:%s,landingUrlLoadFisishedTime @Time:%s,closeWebview @Time:%s", new Object[] { Long.valueOf(localf.a()), Long.valueOf(localf.c()), Long.valueOf(localf.d()), Long.valueOf(localf.e()), Long.valueOf(localf.f()) });
            localStringBuffer = new StringBuffer();
          }
        }
      }
    }
    catch (Exception localException1)
    {
      while (true)
      {
        try
        {
          f localf;
          com.taobao.munion.base.a locala;
          String[] arrayOfString;
          String str1;
          String str3;
          StringBuffer localStringBuffer;
          localStringBuffer.append(str3).append("ta=" + URLEncoder.encode(new StringBuilder().append(localf.a()).append("").toString(), "utf-8")).append("&tb=" + URLEncoder.encode(new StringBuilder().append(localf.c()).append("").toString(), "utf-8")).append("&tc=" + URLEncoder.encode(new StringBuilder().append(localf.d()).append("").toString(), "utf-8")).append("&td=" + URLEncoder.encode(new StringBuilder().append(localf.e()).append("").toString(), "utf-8")).append("&te=" + URLEncoder.encode(new StringBuilder().append(localf.f()).append("").toString(), "utf-8")).append("&refpid=" + URLEncoder.encode(str1, "utf-8")).append("&ac=" + URLEncoder.encode(arrayOfString[0], "utf-8")).append("&acsub=" + URLEncoder.encode(arrayOfString[1], "utf-8")).append("&carrier=" + URLEncoder.encode(locala.G(), "utf-8")).append("&gps=" + URLEncoder.encode((String)localObject, "utf-8")).append("&os=" + URLEncoder.encode("android", "utf-8")).append("&osv=" + URLEncoder.encode(Build.VERSION.RELEASE, "utf-8")).append("&sdkv=" + URLEncoder.encode(ExchangeConstants.sdk_version, "utf-8")).append("&pgv=").append("&aurl=" + URLEncoder.encode(com.taobao.munion.base.caches.b.d, "utf-8")).append("&pageid=" + URLEncoder.encode(com.taobao.munion.base.caches.b.h, "utf-8"));
          localObject = localStringBuffer;
          if ((((StringBuffer)localObject).toString() == null) || (((StringBuffer)localObject).toString().trim().length() <= 0))
            break;
          android.util.Log.i("ping back url = ", ((StringBuffer)localObject).toString());
          localObject = new a(((StringBuffer)localObject).toString(), 4, true);
          this.l.execute((Runnable)localObject);
          return;
          localException1 = localException1;
          localObject = null;
          localException1.printStackTrace();
          continue;
        }
        catch (Exception localException3)
        {
          localObject = localException1;
          Exception localException2 = localException3;
          continue;
        }
        String str2 = "";
        continue;
        Object localObject = "";
      }
    }
  }

  public void a(Context paramContext, o paramo)
  {
    if ((paramContext != null) && (paramo != null));
    while (true)
    {
      try
      {
        this.k = paramContext;
        int n = paramo.b;
        switch (n)
        {
        case 3:
        case 4:
        case 7:
        default:
          return;
        case 1:
          this.i = 1;
          c();
          continue;
        case 2:
        case 5:
        case 6:
        case 8:
        }
      }
      finally
      {
      }
      if (this.i != 3)
      {
        this.i = 2;
        d();
        continue;
        if ((paramo.c != null) && ((paramo.c instanceof String)))
        {
          b((String)paramo.c);
          continue;
          if ((paramo.c != null) && ((paramo.c instanceof String)))
          {
            a((String)paramo.c);
            continue;
            h();
          }
        }
      }
    }
  }

  private class a
    implements Runnable
  {
    private String b;
    private int c;
    private boolean d;

    public a(String arg2)
    {
      Object localObject;
      this.b = localObject;
    }

    public a(String paramInt, int paramBoolean, boolean arg4)
    {
      this.b = paramInt;
      this.c = paramBoolean;
      boolean bool;
      this.d = bool;
    }

    public void run()
    {
      try
      {
        DefaultHttpClient localDefaultHttpClient = new DefaultHttpClient();
        BasicHttpContext localBasicHttpContext = new BasicHttpContext();
        HttpGet localHttpGet = new HttpGet(this.b);
        localHttpGet.setHeader("Referer", b.b(b.this).getPackageName());
        localDefaultHttpClient.execute(localHttpGet, localBasicHttpContext);
        if (this.d)
          a.a().a(this.c);
        return;
      }
      catch (ClientProtocolException localClientProtocolException)
      {
        android.util.Log.e(b.b(), "Failed on sending user datas, error code is: " + localClientProtocolException.toString());
        return;
      }
      catch (IOException localIOException)
      {
        android.util.Log.e(b.b(), "Failed on sending user datas, error code is: " + localIOException.toString());
        return;
      }
      catch (Exception localException)
      {
        android.util.Log.e(b.b(), "Failed on sending user datas, error code is: " + localException.toString());
      }
    }
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     com.taobao.newxp.common.a.b
 * JD-Core Version:    0.6.2
 */