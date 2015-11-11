package cn.com.mma.mobile.tracking.util;

import android.util.Log;

public final class Logger
{
  public static boolean DEBUG_LOG = false;
  public static String Tag = "mmachina";

  public static void d(String paramString)
  {
    if ((DEBUG_LOG) && (paramString != null) && (!paramString.equals("")))
      Log.d(Tag, paramString);
  }

  public static void d(String paramString1, String paramString2)
  {
    if ((DEBUG_LOG) && (paramString2 != null) && (!paramString2.equals("")))
      Log.d(paramString1, paramString2);
  }

  public static void e(String paramString)
  {
    if ((DEBUG_LOG) && (paramString != null) && (!paramString.equals("")))
      Log.d(Tag, paramString);
  }

  public static void e(String paramString1, String paramString2)
  {
    if ((DEBUG_LOG) && (paramString2 != null) && (!paramString2.equals("")))
      Log.d(paramString1, paramString2);
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     cn.com.mma.mobile.tracking.util.Logger
 * JD-Core Version:    0.6.2
 */