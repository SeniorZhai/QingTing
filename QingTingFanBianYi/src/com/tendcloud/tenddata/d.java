package com.tendcloud.tenddata;

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
    e.a(j.h());
    Object localObject1 = paraman.e;
    e.b(paraman.f);
    e.c(paraman.g);
    e.d(paraman.h);
    localObject1 = ((List)localObject1).iterator();
    while (((Iterator)localObject1).hasNext())
    {
      Object localObject2 = (z)((Iterator)localObject1).next();
      switch (((z)localObject2).a)
      {
      default:
        break;
      case 1:
        j.b(false);
        break;
      case 2:
        localObject2 = ((z)localObject2).b;
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
      j.f();
  }

  public static void b()
  {
    try
    {
      f = false;
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
        Context localContext = j.h();
        if (!b.b(localContext))
        {
          Log.w("TDLog", "network is disabled.");
          j.j = true;
          return;
        }
        if ((j.f) && (!b.c(localContext)))
        {
          Log.w("TDLog", "wifi is not connected.");
          j.j = true;
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

  static n e()
  {
    Context localContext = j.h();
    n localn = new n();
    Object localObject = k.h();
    try
    {
      localn.a = localObject[0];
      localn.b = Integer.valueOf(localObject[1]).intValue();
      localn.d = localObject[2];
      localn.c = Float.valueOf(localObject[3]).floatValue();
      label56: localObject = k.j();
      localn.g = localObject[0];
      localn.h = localObject[1];
      localObject = k.k();
      localn.i = localObject[0];
      localn.j = localObject[1];
      localn.k = localObject[2];
      localn.l = localObject[3];
      localn.m = k.l();
      localObject = new DisplayMetrics();
      ((WindowManager)localContext.getSystemService("window")).getDefaultDisplay().getMetrics((DisplayMetrics)localObject);
      localn.n = (((DisplayMetrics)localObject).widthPixels / ((DisplayMetrics)localObject).xdpi);
      localn.o = (((DisplayMetrics)localObject).heightPixels / ((DisplayMetrics)localObject).ydpi);
      localn.p = ((DisplayMetrics)localObject).densityDpi;
      localn.q = Build.DISPLAY;
      localn.r = "unknown";
      try
      {
        localn.r = ((String)Class.forName("android.os.SystemProperties").getDeclaredMethod("get", new Class[] { String.class }).invoke(null, new Object[] { "gsm.version.baseband" }));
        label235: localObject = a.d(localContext);
        if (localObject != null)
          localn.s = ((String)localObject);
        localObject = a.g(localContext);
        if (localObject != null)
          localn.t = ((String)localObject);
        localn.y = a.f(localContext);
        localn.z = ae.d(localContext).toString();
        localn.A = a.e(localContext);
        localn.B = a.c(localContext);
        return localn;
      }
      catch (Exception localException1)
      {
        break label235;
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
      d = new ac(g.getLooper());
    if (e == null)
      e = new ab(g.getLooper());
  }

  private static boolean g()
  {
    if (j.h() == null)
      return false;
    an localan = h();
    if (localan == null)
      return true;
    if (TCAgent.LOG_ON)
      Log.i("TDLog", "Post data to server...");
    boolean bool = p.a(localan);
    if (TCAgent.LOG_ON)
      Log.i("TDLog", "server return success:" + bool);
    if (bool)
      a(localan);
    for (j.j = false; ; j.j = true)
      return bool;
  }

  private static an h()
  {
    Object localObject1 = j.h();
    an localan = new an();
    localan.a = a.b((Context)localObject1);
    localan.b = j.i();
    localan.c = j.r();
    localan.d = j.s();
    localan.i = j.e;
    int i = localan.a() + 3 + 0;
    if (j.j())
    {
      localObject1 = new z();
      ((z)localObject1).a = 1;
      ((z)localObject1).c = e();
      localan.e.add(localObject1);
      j = q.c(((z)localObject1).a);
      i += ((z)localObject1).c.a() + j;
    }
    for (int j = 1; ; j = 0)
    {
      e.a(j.h());
      localan.h = e.e("error_report");
      Object localObject2 = e.d();
      localObject1 = new ArrayList();
      localObject2 = ((List)localObject2).iterator();
      int k = 0;
      while (true)
      {
        c localc;
        int m;
        z localz;
        if (((Iterator)localObject2).hasNext())
        {
          localc = (c)((Iterator)localObject2).next();
          m = k + 1;
          localc.h = e.a(localc.a, localan.f);
          localc.i = e.b(localc.a, localan.g);
          localz = new z();
          localz.a = 2;
          localz.b = localc;
          k = localc.a();
          if ((k + i > 20480) && (m != 1))
            f = true;
        }
        else
        {
          localan.f = e.a((List)localObject1);
          localan.g = e.b((List)localObject1);
          if (localan.h <= 0L)
            break;
          localObject1 = e.e(localan.h).iterator();
          while (((Iterator)localObject1).hasNext())
          {
            localObject2 = (z)((Iterator)localObject1).next();
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
          localan.e.add(localz);
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
    if (j.h() == null);
    List localList;
    do
    {
      return false;
      e.a(j.h());
      localList = e.d();
      e.b();
    }
    while (localList.size() <= 0);
    return true;
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     com.tendcloud.tenddata.d
 * JD-Core Version:    0.6.2
 */