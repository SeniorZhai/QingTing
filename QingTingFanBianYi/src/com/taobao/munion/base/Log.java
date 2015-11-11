package com.taobao.munion.base;

import android.os.SystemClock;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Log
{
  public static boolean DEBUG = true;
  public static String TAG = "Munion";

  private static String a(String paramString, Object[] paramArrayOfObject)
  {
    String str = paramString;
    int i;
    if (paramArrayOfObject != null)
    {
      if (paramArrayOfObject.length == 0)
        str = paramString;
    }
    else
    {
      paramString = new Throwable().fillInStackTrace().getStackTrace();
      i = 2;
      label29: if (i >= paramString.length)
        break label157;
      if (paramString[i].getClass().equals(Log.class))
        break label150;
      paramArrayOfObject = paramString[i].getClassName();
      paramArrayOfObject = paramArrayOfObject.substring(paramArrayOfObject.lastIndexOf('.') + 1);
      paramArrayOfObject = paramArrayOfObject.substring(paramArrayOfObject.lastIndexOf('$') + 1);
    }
    label150: label157: for (paramString = paramArrayOfObject + "." + paramString[i].getMethodName(); ; paramString = "<unknown>")
    {
      return String.format("[%d] %s: %s", new Object[] { Long.valueOf(Thread.currentThread().getId()), paramString, str });
      str = String.format(paramString, paramArrayOfObject);
      break;
      i += 1;
      break label29;
    }
  }

  public static void d(String paramString, Object[] paramArrayOfObject)
  {
    if (DEBUG)
      android.util.Log.d(TAG, a(paramString, paramArrayOfObject));
  }

  public static void e(String paramString, Object[] paramArrayOfObject)
  {
    android.util.Log.e(TAG, a(paramString, paramArrayOfObject));
  }

  public static void e(Throwable paramThrowable, String paramString, Object[] paramArrayOfObject)
  {
    android.util.Log.e(TAG, a(paramString, paramArrayOfObject), paramThrowable);
  }

  public static void i(String paramString, Object[] paramArrayOfObject)
  {
    if (DEBUG)
      android.util.Log.i(TAG, a(paramString, paramArrayOfObject));
  }

  public static void setTag(String paramString)
  {
    d("Changing log tag to %s", new Object[] { paramString });
    TAG = paramString;
  }

  public static void v(String paramString, Object[] paramArrayOfObject)
  {
    if (DEBUG)
      android.util.Log.v(TAG, a(paramString, paramArrayOfObject));
  }

  public static void w(String paramString, Object[] paramArrayOfObject)
  {
    android.util.Log.w(TAG, a(paramString, paramArrayOfObject));
  }

  public static void w(Throwable paramThrowable, String paramString, Object[] paramArrayOfObject)
  {
    android.util.Log.w(TAG, a(paramString, paramArrayOfObject), paramThrowable);
  }

  public static void wtf(String paramString, Object[] paramArrayOfObject)
  {
    android.util.Log.wtf(TAG, a(paramString, paramArrayOfObject));
  }

  public static void wtf(Throwable paramThrowable, String paramString, Object[] paramArrayOfObject)
  {
    android.util.Log.wtf(TAG, a(paramString, paramArrayOfObject), paramThrowable);
  }

  public static class a
  {
    public static final boolean a = Log.DEBUG;
    private static final long b = 0L;
    private final List<a> c = new ArrayList();
    private boolean d = false;

    private long a()
    {
      if (this.c.size() == 0)
        return 0L;
      long l = ((a)this.c.get(0)).c;
      return ((a)this.c.get(this.c.size() - 1)).c - l;
    }

    public void a(String paramString)
    {
      try
      {
        this.d = true;
        long l2 = a();
        if (l2 <= 0L);
        while (true)
        {
          return;
          long l1 = ((a)this.c.get(0)).c;
          Log.d("(%-4d ms) %s", new Object[] { Long.valueOf(l2), paramString });
          paramString = this.c.iterator();
          while (paramString.hasNext())
          {
            a locala = (a)paramString.next();
            l2 = locala.c;
            Log.d("(+%-4d) [%2d] %s", new Object[] { Long.valueOf(l2 - l1), Long.valueOf(locala.b), locala.a });
            l1 = l2;
          }
        }
      }
      finally
      {
      }
      throw paramString;
    }

    public void a(String paramString, long paramLong)
    {
      try
      {
        boolean bool = this.d;
        if (bool);
        while (true)
        {
          return;
          this.c.add(new a(paramString, paramLong, SystemClock.elapsedRealtime()));
        }
      }
      finally
      {
      }
      throw paramString;
    }

    protected void finalize()
      throws Throwable
    {
      if (!this.d)
      {
        a("Request on the loose");
        Log.e("Marker log finalized without finish() - uncaught exit point for request", new Object[0]);
      }
    }

    private static class a
    {
      public final String a;
      public final long b;
      public final long c;

      public a(String paramString, long paramLong1, long paramLong2)
      {
        this.a = paramString;
        this.b = paramLong1;
        this.c = paramLong2;
      }
    }
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     com.taobao.munion.base.Log
 * JD-Core Version:    0.6.2
 */