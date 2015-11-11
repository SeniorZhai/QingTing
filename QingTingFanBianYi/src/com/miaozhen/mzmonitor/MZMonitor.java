package com.miaozhen.mzmonitor;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Handler;
import android.os.HandlerThread;
import java.util.Iterator;
import java.util.List;

public class MZMonitor
{
  private static Handler a = null;
  private static HandlerThread b = null;
  private static long c;

  private static void a(Context paramContext, final a parama)
  {
    try
    {
      if (b == null)
      {
        HandlerThread localHandlerThread = new HandlerThread("MZMonitor");
        b = localHandlerThread;
        localHandlerThread.start();
        a = new Handler(b.getLooper());
      }
      paramContext = new Runnable()
      {
        public final void run()
        {
          if (c.a(MZMonitor.this).a() == null)
            c.a(MZMonitor.this).b();
          if (j.i(MZMonitor.this))
          {
            long l = c.a.a();
            if ((j.a == 0L) || (l > j.a))
            {
              j.a = 900L * ()Math.pow(2.0D, j.b) + l;
              if (j.b < 2)
                j.b += 1;
              j.l(MZMonitor.this);
            }
          }
          new i(MZMonitor.this, parama).a();
        }
      };
      a.post(paramContext);
      return;
    }
    finally
    {
    }
    throw paramContext;
  }

  public static void adTrack(Context paramContext, String paramString)
  {
    a(paramContext, new a(paramString));
  }

  public static void adTrackWithIESId(Context paramContext, String paramString1, String paramString2)
  {
    paramString1 = new a(paramString1);
    paramString1.f(paramString2);
    a(paramContext, paramString1);
  }

  public static void adTrackWithUserId(Context paramContext, String paramString1, String paramString2)
  {
    paramString1 = new a(paramString1);
    paramString1.c(paramString2);
    a(paramContext, paramString1);
  }

  public static void eventTrack(Context paramContext, String paramString1, String paramString2)
  {
    paramString1 = new a(paramString1);
    paramString1.d(paramString2);
    a(paramContext, paramString1);
  }

  @Deprecated
  public static boolean isMZURL(String paramString)
  {
    return true;
  }

  public static void panelTrack(Context paramContext, String paramString1, String paramString2, String paramString3)
  {
    paramString1 = new a(paramString1);
    paramString1.b(paramString2);
    paramString1.c(paramString3);
    a(paramContext, paramString1);
  }

  @Deprecated
  public static void reSendReport(Context paramContext)
  {
    retryCachedRequests(paramContext);
  }

  @Deprecated
  public static void reportAction(Context paramContext, String paramString)
  {
    adTrack(paramContext, paramString);
  }

  @Deprecated
  public static void reportAction(Context paramContext, String paramString1, String paramString2, String paramString3)
  {
    panelTrack(paramContext, paramString1, paramString2, paramString3);
  }

  public static void retryCachedRequests(Context paramContext)
  {
    try
    {
      long l1 = c.a.a();
      if (f.a(paramContext).l() != "0")
      {
        if (c == 0L)
          break label43;
        long l2 = c;
        if (l1 >= l2)
          break label43;
      }
      while (true)
      {
        return;
        label43: Object localObject = b.a(paramContext).a();
        if ((localObject != null) && (((List)localObject).size() != 0))
        {
          c = ((List)localObject).size() * 10000 / 1000 + l1;
          localObject = ((List)localObject).iterator();
          while (((Iterator)localObject).hasNext())
            a(paramContext, (a)((Iterator)localObject).next());
        }
      }
    }
    finally
    {
    }
    throw paramContext;
  }

  @Deprecated
  public static void setCustomProfile(Context paramContext, String paramString)
  {
    if (isMZURL(paramString))
      j.a(paramContext, paramString);
  }

  public static void setOption(Context paramContext, String paramString, boolean paramBoolean)
  {
    if (c.a.a(paramString).equals("0a9896360edb4c54030c25b12f447fb0"))
    {
      paramString = paramContext.getSharedPreferences("mzSdkProfilePrefs", 0).edit();
      paramString.putBoolean("0a9896360edb4c54030c25b12f447fb0", paramBoolean);
      paramString.commit();
      if (paramBoolean)
      {
        g.a(paramContext).a();
        j.a(paramContext, "[UNKNOWN]", "0x0");
      }
    }
  }

  public static String version()
  {
    return "a3.2";
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     com.miaozhen.mzmonitor.MZMonitor
 * JD-Core Version:    0.6.2
 */