package com.intowow.sdk;

import android.content.Context;
import com.intowow.sdk.b.e;
import org.json.JSONObject;

public class I2WAPI
{
  public static long getSectionSplashGuardTime(Context paramContext)
  {
    try
    {
      long l = e.a(paramContext).f();
      return l;
    }
    catch (Exception paramContext)
    {
    }
    return 0L;
  }

  public static void init(Context paramContext)
  {
    try
    {
      init(paramContext, false);
      return;
    }
    finally
    {
      paramContext = finally;
    }
    throw paramContext;
  }

  // ERROR //
  public static void init(Context paramContext, boolean paramBoolean)
  {
    // Byte code:
    //   0: ldc 2
    //   2: monitorenter
    //   3: aload_0
    //   4: invokestatic 32	com/intowow/sdk/j/i:a	(Landroid/content/Context;)V
    //   7: aload_0
    //   8: invokestatic 19	com/intowow/sdk/b/e:a	(Landroid/content/Context;)Lcom/intowow/sdk/b/e;
    //   11: iload_1
    //   12: invokevirtual 35	com/intowow/sdk/b/e:a	(Z)V
    //   15: aload_0
    //   16: instanceof 37
    //   19: ifeq +14 -> 33
    //   22: aload_0
    //   23: invokestatic 19	com/intowow/sdk/b/e:a	(Landroid/content/Context;)Lcom/intowow/sdk/b/e;
    //   26: aload_0
    //   27: checkcast 37	android/app/Activity
    //   30: invokevirtual 40	com/intowow/sdk/b/e:a	(Landroid/app/Activity;)V
    //   33: ldc 2
    //   35: monitorexit
    //   36: return
    //   37: astore_0
    //   38: ldc 2
    //   40: monitorexit
    //   41: aload_0
    //   42: athrow
    //   43: astore_0
    //   44: goto -11 -> 33
    //
    // Exception table:
    //   from	to	target	type
    //   3	7	37	finally
    //   7	33	37	finally
    //   7	33	43	java/lang/Exception
  }

  // ERROR //
  public static boolean isAdServing(Context paramContext)
  {
    // Byte code:
    //   0: ldc 2
    //   2: monitorenter
    //   3: aload_0
    //   4: invokestatic 19	com/intowow/sdk/b/e:a	(Landroid/content/Context;)Lcom/intowow/sdk/b/e;
    //   7: invokevirtual 46	com/intowow/sdk/b/e:d	()Z
    //   10: istore_1
    //   11: ldc 2
    //   13: monitorexit
    //   14: iload_1
    //   15: ireturn
    //   16: astore_0
    //   17: iconst_0
    //   18: istore_1
    //   19: goto -8 -> 11
    //   22: astore_0
    //   23: ldc 2
    //   25: monitorexit
    //   26: aload_0
    //   27: athrow
    //
    // Exception table:
    //   from	to	target	type
    //   3	11	16	java/lang/Exception
    //   3	11	22	finally
  }

  public static boolean isOpenSplashInGuardTime(Context paramContext)
  {
    try
    {
      boolean bool = e.a(paramContext).g();
      return bool;
    }
    catch (Exception paramContext)
    {
    }
    return false;
  }

  public static void onActivityPause(Context paramContext)
  {
    try
    {
      e.a(paramContext).c();
      return;
    }
    catch (Exception paramContext)
    {
    }
  }

  public static void onActivityResume(Context paramContext)
  {
    try
    {
      e.a(paramContext).b();
      return;
    }
    catch (Exception paramContext)
    {
    }
  }

  public static SplashAD requesSingleOfferAD(Context paramContext, String paramString)
  {
    try
    {
      paramContext = e.a(paramContext).a(paramContext, paramString, false, false);
      return paramContext;
    }
    catch (Exception paramContext)
    {
    }
    return null;
  }

  public static SplashAD requesSingleOfferAD(Context paramContext, String paramString, boolean paramBoolean)
  {
    try
    {
      paramContext = e.a(paramContext).a(paramContext, paramString, paramBoolean, false);
      return paramContext;
    }
    catch (Exception paramContext)
    {
    }
    return null;
  }

  public static void setADEventListener(Context paramContext, ADEventListener paramADEventListener)
  {
    try
    {
      e.a(paramContext).a(paramADEventListener);
      return;
    }
    catch (Exception paramContext)
    {
    }
  }

  public static void setOpenSplashLastViewTime(Context paramContext, long paramLong)
  {
    try
    {
      e.a(paramContext).a(paramLong);
      return;
    }
    catch (Exception paramContext)
    {
    }
  }

  public static void trackEvent(Context paramContext, String paramString, JSONObject paramJSONObject)
  {
    try
    {
      e.a(paramContext).a(paramString, paramJSONObject);
      return;
    }
    catch (Exception paramContext)
    {
    }
  }

  public static void triggerBackgroundFetch(Context paramContext)
  {
    try
    {
      e.a(paramContext).i();
      return;
    }
    catch (Exception paramContext)
    {
    }
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     com.intowow.sdk.I2WAPI
 * JD-Core Version:    0.6.2
 */