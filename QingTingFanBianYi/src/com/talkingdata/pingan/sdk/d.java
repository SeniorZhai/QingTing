package com.talkingdata.pingan.sdk;

import android.content.Context;
import android.os.Build;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.SystemClock;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Display;
import android.view.WindowManager;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

final class d
{
  static long a = 0L;
  static final int b = 60000;
  private static final int c = 20480;
  private static Handler d = null;
  private static Handler e = null;
  private static boolean f = false;
  private static final HandlerThread g = new HandlerThread("ProcessingThread");

  static
  {
    g.start();
  }

  static Handler a()
  {
    try
    {
      if (e == null)
        f();
      Handler localHandler = e;
      return localHandler;
    }
    finally
    {
    }
  }

  private static void a(an paraman)
  {
    if (PAAgent.LOG_ON)
      Log.d("pinganLog", "Send Success, Clear Data");
    e.a(PAAgent.c());
    Object localObject1 = paraman.e;
    e.b(paraman.f);
    e.c(paraman.g);
    e.d(paraman.h);
    localObject1 = ((List)localObject1).iterator();
    while (((Iterator)localObject1).hasNext())
    {
      Object localObject2 = (ac)((Iterator)localObject1).next();
      switch (((ac)localObject2).a)
      {
      default:
        break;
      case 1:
        PAAgent.b(false);
        break;
      case 2:
        localObject2 = ((ac)localObject2).b;
        if (((c)localObject2).c == 1)
        {
          e.a(((c)localObject2).a);
        }
        else if (((c)localObject2).c == 3)
        {
          e.b(((c)localObject2).a);
          e.c(((c)localObject2).a);
          e.d(((c)localObject2).a);
        }
        break;
      }
    }
    e.b();
    if (paraman.i != null)
      PAAgent.a();
  }

  public static void b()
  {
    try
    {
      f = false;
      q.a(new String[] { "Test", "[HTTPThread]pre send" });
      g();
      if (f)
        d.sendEmptyMessageDelayed(0, 100L);
      return;
    }
    catch (Exception localException)
    {
    }
  }

  static void c()
  {
    long l2 = 0L;
    while (true)
    {
      long l1;
      try
      {
        long l3 = SystemClock.elapsedRealtime() - a;
        l1 = l2;
        if (a > 0L)
        {
          l1 = l2;
          if (l3 < 60000L)
            l1 = 60000L - l3;
        }
        Context localContext = PAAgent.c();
        if (!b.b(localContext))
        {
          Log.w("pinganLog", "network is disabled.");
          PAAgent.i = true;
          return;
        }
        if ((PAAgent.f) && (!b.c(localContext)))
        {
          Log.w("pinganLog", "wifi is not connected.");
          PAAgent.i = true;
          continue;
        }
      }
      finally
      {
      }
      d.sendEmptyMessageDelayed(0, l1);
      a = l1 + SystemClock.elapsedRealtime();
    }
  }

  static m e()
  {
    Context localContext = PAAgent.c();
    m localm = new m();
    Object localObject = j.h();
    try
    {
      localm.a = localObject[0];
      localm.b = Integer.valueOf(localObject[1]).intValue();
      localm.d = localObject[2];
      localm.c = Float.valueOf(localObject[3]).floatValue();
      label56: localObject = j.j();
      localm.g = localObject[0];
      localm.h = localObject[1];
      localObject = j.k();
      localm.i = localObject[0];
      localm.j = localObject[1];
      localm.k = localObject[2];
      localm.l = localObject[3];
      localm.m = j.l();
      localObject = new DisplayMetrics();
      ((WindowManager)localContext.getSystemService("window")).getDefaultDisplay().getMetrics((DisplayMetrics)localObject);
      localm.n = (((DisplayMetrics)localObject).widthPixels / ((DisplayMetrics)localObject).xdpi);
      localm.o = (((DisplayMetrics)localObject).heightPixels / ((DisplayMetrics)localObject).ydpi);
      localm.p = ((DisplayMetrics)localObject).densityDpi;
      localm.q = Build.DISPLAY;
      localm.r = "unknown";
      try
      {
        localm.r = ((String)Class.forName("android.os.SystemProperties").getDeclaredMethod("get", new Class[] { String.class }).invoke(null, new Object[] { "gsm.version.baseband" }));
        localObject = a.d(localContext);
        if (localObject != null)
          localm.s = ((String)localObject);
        localObject = a.g(localContext);
        if (localObject != null)
          localm.t = ((String)localObject);
        localm.y = a.f(localContext);
        localm.A = a.e(localContext);
        localm.B = a.c(localContext);
        return localm;
      }
      catch (Exception localException1)
      {
        while (true)
          q.a(new String[] { "fecth base band failed!" });
      }
    }
    catch (Exception localException2)
    {
      break label56;
    }
  }

  private static void f()
  {
    if (d == null)
      d = new af(g.getLooper());
    if (e == null)
      e = new ae(g.getLooper());
  }

  private static boolean g()
  {
    if (PAAgent.c() == null)
    {
      q.a(new String[] { "TCAgent.getContext is null..." });
      return false;
    }
    an localan = h();
    if (localan == null)
    {
      q.a(new String[] { "No data to send. return true;" });
      return true;
    }
    if (PAAgent.LOG_ON)
      Log.d("pinganLog", "Post data to server...");
    boolean bool = o.a(localan);
    if (PAAgent.LOG_ON)
      Log.d("pinganLog", "server return success:" + bool);
    if (bool)
      a(localan);
    for (PAAgent.i = false; ; PAAgent.i = true)
      return bool;
  }

  private static an h()
  {
    Object localObject1 = PAAgent.c();
    an localan = new an();
    localan.a = a.b((Context)localObject1);
    localan.b = PAAgent.d();
    localan.c = PAAgent.m();
    localan.d = PAAgent.n();
    localan.i = PAAgent.e;
    int i = localan.a() + 3 + 0;
    if (PAAgent.e())
    {
      q.a(new String[] { "Prepare init Device profile" });
      localObject1 = new ac();
      ((ac)localObject1).a = 1;
      ((ac)localObject1).c = e();
      localan.e.add(localObject1);
      j = p.c(((ac)localObject1).a);
      i += ((ac)localObject1).c.a() + j;
    }
    for (int j = 1; ; j = 0)
    {
      e.a(PAAgent.c());
      localan.h = e.e("error_report");
      Object localObject2 = e.d();
      localObject1 = new ArrayList();
      localObject2 = ((List)localObject2).iterator();
      int k = 0;
      while (true)
      {
        c localc;
        int m;
        ac localac;
        if (((Iterator)localObject2).hasNext())
        {
          localc = (c)((Iterator)localObject2).next();
          m = k + 1;
          localc.h = e.a(localc.a, localan.f);
          localc.i = e.b(localc.a, localan.g);
          localac = new ac();
          localac.a = 2;
          localac.b = localc;
          k = localc.a();
          if ((k + i > 20480) && (m != 1))
            f = true;
        }
        else
        {
          q.a(new String[] { "************ " + ((List)localObject1).size() + " Session*****************" });
          localan.f = e.a((List)localObject1);
          localan.g = e.b((List)localObject1);
          if (localan.h <= 0L)
            break;
          localObject1 = e.e(localan.h).iterator();
          while (((Iterator)localObject1).hasNext())
          {
            localObject2 = (ac)((Iterator)localObject1).next();
            localan.e.add(localObject2);
          }
        }
        int n = i + k;
        ((List)localObject1).add(localc);
        if ((localc.c == 2) && (localc.h.size() == 0))
        {
          k = m;
          i = n;
          if (localc.i.size() == 0);
        }
        else
        {
          localan.e.add(localac);
          k = m;
          i = n;
        }
      }
      e.b();
      if ((j == 0) && (localan.e.size() == 0))
        return null;
      return localan;
    }
  }

  public boolean d()
  {
    if (PAAgent.c() == null);
    List localList;
    do
    {
      return false;
      e.a(PAAgent.c());
      localList = e.d();
      e.b();
    }
    while (localList.size() <= 0);
    return true;
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     com.talkingdata.pingan.sdk.d
 * JD-Core Version:    0.6.2
 */