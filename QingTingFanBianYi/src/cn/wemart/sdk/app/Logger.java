package cn.wemart.sdk.app;

import android.util.Log;

public class Logger
{
  public static final int DEBUG = 3;
  public static final int ERROR = 6;
  public static final int INFO = 4;
  public static int LOGLEVEL = 0;
  public static final int VERBOSE = 2;
  public static final int WARN = 5;

  public static void d(String paramString1, String paramString2)
  {
    if (3 >= LOGLEVEL)
      Log.d(paramString1, paramString2);
  }

  public static void d(String paramString1, String paramString2, Throwable paramThrowable)
  {
    if (3 >= LOGLEVEL)
      Log.d(paramString1, paramString2, paramThrowable);
  }

  public static void d(String paramString1, String paramString2, Object[] paramArrayOfObject)
  {
    if (3 >= LOGLEVEL)
      Log.d(paramString1, String.format(paramString2, paramArrayOfObject));
  }

  public static void e(String paramString1, String paramString2)
  {
    if (6 >= LOGLEVEL)
      Log.e(paramString1, paramString2);
  }

  public static void e(String paramString1, String paramString2, Throwable paramThrowable)
  {
    if (6 >= LOGLEVEL)
      Log.e(paramString1, paramString2, paramThrowable);
  }

  public static void e(String paramString1, String paramString2, Object[] paramArrayOfObject)
  {
    if (6 >= LOGLEVEL)
      Log.e(paramString1, String.format(paramString2, paramArrayOfObject));
  }

  public static void i(String paramString1, String paramString2)
  {
    if (4 >= LOGLEVEL)
      Log.i(paramString1, paramString2);
  }

  public static void i(String paramString1, String paramString2, Throwable paramThrowable)
  {
    if (4 >= LOGLEVEL)
      Log.i(paramString1, paramString2, paramThrowable);
  }

  public static void i(String paramString1, String paramString2, Object[] paramArrayOfObject)
  {
    if (4 >= LOGLEVEL)
      Log.i(paramString1, String.format(paramString2, paramArrayOfObject));
  }

  public static boolean isLoggable(int paramInt)
  {
    return paramInt >= LOGLEVEL;
  }

  public static void setLogLevel(int paramInt)
  {
    LOGLEVEL = paramInt;
  }

  public static void setLogLevel(String paramString)
  {
    if ("VERBOSE".equals(paramString))
      LOGLEVEL = 2;
    do
    {
      return;
      if ("DEBUG".equals(paramString))
      {
        LOGLEVEL = 3;
        return;
      }
      if ("INFO".equals(paramString))
      {
        LOGLEVEL = 4;
        return;
      }
      if ("WARN".equals(paramString))
      {
        LOGLEVEL = 5;
        return;
      }
    }
    while (!"ERROR".equals(paramString));
    LOGLEVEL = 6;
  }

  public static void v(String paramString1, String paramString2)
  {
    if (2 >= LOGLEVEL)
      Log.v(paramString1, paramString2);
  }

  public static void v(String paramString1, String paramString2, Throwable paramThrowable)
  {
    if (2 >= LOGLEVEL)
      Log.v(paramString1, paramString2, paramThrowable);
  }

  public static void v(String paramString1, String paramString2, Object[] paramArrayOfObject)
  {
    if (2 >= LOGLEVEL)
      Log.v(paramString1, String.format(paramString2, paramArrayOfObject));
  }

  public static void w(String paramString1, String paramString2)
  {
    if (5 >= LOGLEVEL)
      Log.w(paramString1, paramString2);
  }

  public static void w(String paramString1, String paramString2, Throwable paramThrowable)
  {
    if (5 >= LOGLEVEL)
      Log.w(paramString1, paramString2, paramThrowable);
  }

  public static void w(String paramString1, String paramString2, Object[] paramArrayOfObject)
  {
    if (5 >= LOGLEVEL)
      Log.w(paramString1, String.format(paramString2, paramArrayOfObject));
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     cn.wemart.sdk.app.Logger
 * JD-Core Version:    0.6.2
 */