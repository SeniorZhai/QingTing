package org.android.agoo.client;

import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import android.util.Log;
import com.umeng.message.proguard.P;
import com.umeng.message.proguard.U;

public class BaseRegistrar
{
  static void a(Context paramContext)
  {
    try
    {
      e(paramContext);
      d(paramContext);
      f(paramContext);
      return;
    }
    catch (Throwable paramContext)
    {
    }
  }

  protected static void a(Context paramContext, String paramString1, String paramString2, String paramString3)
  {
    a(paramContext, paramString1, paramString2, paramString3, true);
  }

  protected static void a(Context paramContext, String paramString1, String paramString2, String paramString3, boolean paramBoolean)
  {
    try
    {
      U.a(paramContext);
      if (TextUtils.isEmpty(paramString2))
        AgooSettings.setAgooSecurityMode(paramContext, true);
      IppFacade.performProtectOnlyOnce(paramContext);
      P.j(paramContext);
      P.a(paramContext, paramString1, paramString2, paramString3);
      P.t(paramContext);
      b(paramContext);
      return;
    }
    catch (Throwable paramContext)
    {
    }
  }

  static void b(Context paramContext)
  {
    try
    {
      Intent localIntent = IntentHelper.createComandIntent(paramContext, "register");
      localIntent.setPackage(paramContext.getPackageName());
      paramContext.sendBroadcast(localIntent);
      return;
    }
    catch (Throwable paramContext)
    {
    }
  }

  static void c(Context paramContext)
  {
    try
    {
      Intent localIntent = IntentHelper.createComandIntent(paramContext, "unregister");
      localIntent.setPackage(paramContext.getPackageName());
      paramContext.sendBroadcast(localIntent);
      return;
    }
    catch (Throwable paramContext)
    {
    }
  }

  public static void checkDevice(Context paramContext)
  {
  }

  private static final void d(Context paramContext)
  {
  }

  private static final void e(Context paramContext)
  {
  }

  private static void f(Context paramContext)
  {
  }

  public static final String getRegistrationId(Context paramContext)
  {
    try
    {
      paramContext = P.q(paramContext);
      return paramContext;
    }
    catch (Throwable paramContext)
    {
    }
    return null;
  }

  public static final boolean isRegistered(Context paramContext)
  {
    boolean bool1 = false;
    try
    {
      boolean bool2 = TextUtils.isEmpty(P.q(paramContext));
      if (!bool2)
        bool1 = true;
      return bool1;
    }
    catch (Throwable paramContext)
    {
      Log.w("BaseRegistrar", "isRegistered", paramContext);
    }
    return false;
  }

  public static void unregister(Context paramContext)
  {
    try
    {
      U.h(paramContext);
      U.b(paramContext);
      P.j(paramContext);
      c(paramContext);
      return;
    }
    catch (Throwable paramContext)
    {
    }
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     org.android.agoo.client.BaseRegistrar
 * JD-Core Version:    0.6.2
 */