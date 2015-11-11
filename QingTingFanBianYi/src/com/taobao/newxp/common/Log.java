package com.taobao.newxp.common;

public class Log
{
  public static boolean LOG = false;
  static boolean a = false;

  public static void a(String paramString)
  {
    if (a)
      android.util.Log.d("TestData", paramString);
  }

  public static void a(String paramString1, String paramString2)
  {
    if (LOG)
      android.util.Log.i(paramString1, paramString2);
  }

  public static void a(String paramString1, String paramString2, Exception paramException)
  {
    if (LOG)
      android.util.Log.i(paramString1, paramException.toString() + ":  [" + paramString2 + "]");
  }

  public static void b(String paramString1, String paramString2)
  {
    if (LOG)
      android.util.Log.e(paramString1, paramString2);
  }

  public static void b(String paramString1, String paramString2, Exception paramException)
  {
    if (LOG)
    {
      android.util.Log.e(paramString1, paramException.toString() + ":  [" + paramString2 + "]");
      paramString2 = paramException.getStackTrace();
      int j = paramString2.length;
      int i = 0;
      while (i < j)
      {
        paramException = paramString2[i];
        android.util.Log.e(paramString1, "        at\t " + paramException.toString());
        i += 1;
      }
    }
  }

  public static void c(String paramString1, String paramString2)
  {
    if (LOG)
      android.util.Log.d(paramString1, paramString2);
  }

  public static void c(String paramString1, String paramString2, Exception paramException)
  {
    if (LOG)
      android.util.Log.d(paramString1, paramException.toString() + ":  [" + paramString2 + "]");
  }

  public static void d(String paramString1, String paramString2)
  {
    if (LOG)
      android.util.Log.v(paramString1, paramString2);
  }

  public static void d(String paramString1, String paramString2, Exception paramException)
  {
    if (LOG)
      android.util.Log.v(paramString1, paramException.toString() + ":  [" + paramString2 + "]");
  }

  public static void e(String paramString1, String paramString2)
  {
    if (LOG)
      android.util.Log.w(paramString1, paramString2);
  }

  public static void e(String paramString1, String paramString2, Exception paramException)
  {
    if (LOG)
    {
      android.util.Log.w(paramString1, paramException.toString() + ":  [" + paramString2 + "]");
      paramString2 = paramException.getStackTrace();
      int j = paramString2.length;
      int i = 0;
      while (i < j)
      {
        paramException = paramString2[i];
        android.util.Log.w(paramString1, "        at\t " + paramException.toString());
        i += 1;
      }
    }
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     com.taobao.newxp.common.Log
 * JD-Core Version:    0.6.2
 */