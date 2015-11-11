package com.tendcloud.tenddata;

import android.app.Activity;
import android.content.Context;
import java.util.Map;
import java.util.TreeMap;

public class TCAgent
{
  public static boolean LOG_ON = false;
  protected static final String a = "TD";
  static final boolean b = false;
  static final String c = "TDLog";
  static boolean d = false;
  static final Map e = new TreeMap();
  private static ao f;

  private static void a(Context paramContext)
  {
    try
    {
      ag localag;
      if (f == null)
      {
        System.currentTimeMillis();
        localag = new ag();
      }
      try
      {
        if (f == null)
          f = (ao)localag.a(paramContext, "analytics", "TD", ao.class, j.class, "com.tendcloud.tenddata.ota.j");
        return;
      }
      catch (Exception paramContext)
      {
        while (true)
          paramContext.printStackTrace();
      }
    }
    finally
    {
    }
    throw paramContext;
  }

  public static String getDeviceId(Context paramContext)
  {
    try
    {
      a(paramContext);
      paramContext = f.b(paramContext);
      return paramContext;
    }
    catch (Throwable paramContext)
    {
      paramContext.printStackTrace();
    }
    return null;
  }

  public static void init(Context paramContext)
  {
    try
    {
      a(paramContext);
      f.a(paramContext);
      return;
    }
    catch (Throwable paramContext)
    {
      paramContext.printStackTrace();
    }
  }

  public static void init(Context paramContext, String paramString1, String paramString2)
  {
    try
    {
      a(paramContext);
      f.a(paramContext, paramString1, paramString2);
      return;
    }
    catch (Throwable paramContext)
    {
      paramContext.printStackTrace();
    }
  }

  public static void onError(Context paramContext, Throwable paramThrowable)
  {
    try
    {
      a(paramContext);
      f.a(paramContext, paramThrowable);
      return;
    }
    catch (Throwable paramContext)
    {
      paramContext.printStackTrace();
    }
  }

  public static void onEvent(Context paramContext, String paramString)
  {
    onEvent(paramContext, paramString, null);
  }

  public static void onEvent(Context paramContext, String paramString1, String paramString2)
  {
    onEvent(paramContext, paramString1, paramString2, null);
  }

  public static void onEvent(Context paramContext, String paramString1, String paramString2, Map paramMap)
  {
    while (true)
      try
      {
        a(paramContext);
        Object localObject = paramMap;
        if (e.size() > 0)
        {
          localObject = new TreeMap();
          ((Map)localObject).putAll(e);
          if ((paramMap != null) && (paramMap.size() > 0))
            ((Map)localObject).putAll(paramMap);
        }
        else
        {
          f.a(paramContext, paramString1, paramString2, (Map)localObject);
          return;
        }
      }
      catch (Throwable paramContext)
      {
        paramContext.printStackTrace();
        return;
      }
  }

  public static void onPageEnd(Context paramContext, String paramString)
  {
    try
    {
      a(paramContext.getApplicationContext());
      f.c(paramContext, paramString);
      return;
    }
    catch (Throwable paramContext)
    {
      paramContext.printStackTrace();
    }
  }

  public static void onPageStart(Context paramContext, String paramString)
  {
    try
    {
      a(paramContext.getApplicationContext());
      f.b(paramContext, paramString);
      return;
    }
    catch (Throwable paramContext)
    {
      paramContext.printStackTrace();
    }
  }

  public static void onPause(Activity paramActivity)
  {
    try
    {
      a(paramActivity.getApplicationContext());
      f.b(paramActivity);
      return;
    }
    catch (Throwable paramActivity)
    {
      paramActivity.printStackTrace();
    }
  }

  public static void onResume(Activity paramActivity)
  {
    try
    {
      a(paramActivity.getApplicationContext());
      f.a(paramActivity);
      return;
    }
    catch (Throwable paramActivity)
    {
      paramActivity.printStackTrace();
    }
  }

  public static void removeGlobalKV(String paramString)
  {
    e.remove(paramString);
  }

  public static void setGlobalKV(String paramString, Object paramObject)
  {
    e.put(paramString, paramObject);
  }

  public static void setReportUncaughtExceptions(boolean paramBoolean)
  {
    try
    {
      d = paramBoolean;
      return;
    }
    catch (Throwable localThrowable)
    {
      localThrowable.printStackTrace();
    }
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     com.tendcloud.tenddata.TCAgent
 * JD-Core Version:    0.6.2
 */