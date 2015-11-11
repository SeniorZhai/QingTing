package com.intowow.sdk.j;

import android.util.Log;
import com.intowow.sdk.a.e;

public class h
{
  private static String a = "<tag unset>";
  private static a b = a.a;

  private static b a()
  {
    StackTraceElement[] arrayOfStackTraceElement = Thread.currentThread().getStackTrace();
    if (arrayOfStackTraceElement.length >= 6);
    for (int i = 5; ; i = arrayOfStackTraceElement.length - 1)
      return new b(arrayOfStackTraceElement[i]);
  }

  public static void a(String paramString)
  {
    a = paramString;
  }

  public static void a(String paramString, Object[] paramArrayOfObject)
  {
    if ((!e.a) || (b.a() > a.b.a()))
      return;
    paramString = c(paramString, paramArrayOfObject);
    Log.d(a, paramString);
  }

  public static void a(Throwable paramThrowable)
  {
    if ((!e.a) || (b.a() > a.e.a()))
      return;
    String str = c("%s", new Object[] { paramThrowable.toString() });
    Log.e(a, str, paramThrowable);
  }

  public static void b(String paramString, Object[] paramArrayOfObject)
  {
    if ((!e.a) || (b.a() > a.d.a()))
      return;
    paramString = c(paramString, paramArrayOfObject);
    Log.w(a, paramString);
  }

  private static String c(String paramString)
  {
    int i = paramString.lastIndexOf(".");
    if (i == -1)
      return paramString;
    return paramString.substring(i + 1);
  }

  private static final String c(String paramString, Object[] paramArrayOfObject)
  {
    paramString = String.format(paramString, paramArrayOfObject);
    paramArrayOfObject = a();
    return paramArrayOfObject.a + "." + paramArrayOfObject.b + "@" + paramArrayOfObject.c + ": " + paramString;
  }

  public static enum a
  {
    private int f;

    private a(int arg3)
    {
      int j;
      this.f = j;
    }

    int a()
    {
      return this.f;
    }
  }

  private static class b
  {
    String a;
    String b;
    int c;

    b(StackTraceElement paramStackTraceElement)
    {
      this.a = h.b(paramStackTraceElement.getClassName());
      this.b = paramStackTraceElement.getMethodName();
      this.c = paramStackTraceElement.getLineNumber();
    }
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     com.intowow.sdk.j.h
 * JD-Core Version:    0.6.2
 */