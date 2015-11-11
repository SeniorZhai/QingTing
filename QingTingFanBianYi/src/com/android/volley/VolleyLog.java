package com.android.volley;

import android.os.SystemClock;
import android.util.Log;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;

public class VolleyLog
{
  public static boolean DEBUG = Log.isLoggable(TAG, 2);
  public static String TAG = "Volley";

  private static String buildMessage(String paramString, Object[] paramArrayOfObject)
  {
    StackTraceElement[] arrayOfStackTraceElement;
    int i;
    if (paramArrayOfObject == null)
    {
      arrayOfStackTraceElement = new Throwable().fillInStackTrace().getStackTrace();
      paramArrayOfObject = "<unknown>";
      i = 2;
    }
    while (true)
    {
      if (i >= arrayOfStackTraceElement.length);
      while (true)
      {
        return String.format(Locale.US, "[%d] %s: %s", new Object[] { Long.valueOf(Thread.currentThread().getId()), paramArrayOfObject, paramString });
        paramString = String.format(Locale.US, paramString, paramArrayOfObject);
        break;
        if (arrayOfStackTraceElement[i].getClass().equals(VolleyLog.class))
          break label151;
        paramArrayOfObject = arrayOfStackTraceElement[i].getClassName();
        paramArrayOfObject = paramArrayOfObject.substring(paramArrayOfObject.lastIndexOf('.') + 1);
        paramArrayOfObject = paramArrayOfObject.substring(paramArrayOfObject.lastIndexOf('$') + 1) + "." + arrayOfStackTraceElement[i].getMethodName();
      }
      label151: i += 1;
    }
  }

  public static void d(String paramString, Object[] paramArrayOfObject)
  {
    Log.d(TAG, buildMessage(paramString, paramArrayOfObject));
  }

  public static void e(String paramString, Object[] paramArrayOfObject)
  {
    Log.e(TAG, buildMessage(paramString, paramArrayOfObject));
  }

  public static void e(Throwable paramThrowable, String paramString, Object[] paramArrayOfObject)
  {
    Log.e(TAG, buildMessage(paramString, paramArrayOfObject), paramThrowable);
  }

  public static void setTag(String paramString)
  {
    d("Changing log tag to %s", new Object[] { paramString });
    TAG = paramString;
    DEBUG = Log.isLoggable(TAG, 2);
  }

  public static void v(String paramString, Object[] paramArrayOfObject)
  {
    if (DEBUG)
      Log.v(TAG, buildMessage(paramString, paramArrayOfObject));
  }

  public static void w(Throwable paramThrowable, String paramString, Object[] paramArrayOfObject)
  {
    Log.w(TAG, buildMessage(paramString, paramArrayOfObject), paramThrowable);
  }

  public static void wtf(String paramString, Object[] paramArrayOfObject)
  {
    Log.wtf(TAG, buildMessage(paramString, paramArrayOfObject));
  }

  public static void wtf(Throwable paramThrowable, String paramString, Object[] paramArrayOfObject)
  {
    Log.wtf(TAG, buildMessage(paramString, paramArrayOfObject), paramThrowable);
  }

  static class MarkerLog
  {
    public static final boolean ENABLED = VolleyLog.DEBUG;
    private static final long MIN_DURATION_FOR_LOGGING_MS = 0L;
    private boolean mFinished = false;
    private final List<Marker> mMarkers = new ArrayList();

    private long getTotalDuration()
    {
      if (this.mMarkers.size() == 0)
        return 0L;
      long l = ((Marker)this.mMarkers.get(0)).time;
      return ((Marker)this.mMarkers.get(this.mMarkers.size() - 1)).time - l;
    }

    public void add(String paramString, long paramLong)
    {
      try
      {
        if (this.mFinished)
          throw new IllegalStateException("Marker added to finished log");
      }
      finally
      {
      }
      this.mMarkers.add(new Marker(paramString, paramLong, SystemClock.elapsedRealtime()));
    }

    protected void finalize()
      throws Throwable
    {
      if (!this.mFinished)
      {
        finish("Request on the loose");
        VolleyLog.e("Marker log finalized without finish() - uncaught exit point for request", new Object[0]);
      }
    }

    public void finish(String paramString)
    {
      try
      {
        this.mFinished = true;
        long l2 = getTotalDuration();
        if (l2 <= 0L);
        while (true)
        {
          return;
          long l1 = ((Marker)this.mMarkers.get(0)).time;
          VolleyLog.d("(%-4d ms) %s", new Object[] { Long.valueOf(l2), paramString });
          paramString = this.mMarkers.iterator();
          while (paramString.hasNext())
          {
            Marker localMarker = (Marker)paramString.next();
            l2 = localMarker.time;
            VolleyLog.d("(+%-4d) [%2d] %s", new Object[] { Long.valueOf(l2 - l1), Long.valueOf(localMarker.thread), localMarker.name });
            l1 = l2;
          }
        }
      }
      finally
      {
      }
      throw paramString;
    }

    private static class Marker
    {
      public final String name;
      public final long thread;
      public final long time;

      public Marker(String paramString, long paramLong1, long paramLong2)
      {
        this.name = paramString;
        this.thread = paramLong1;
        this.time = paramLong2;
      }
    }
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     com.android.volley.VolleyLog
 * JD-Core Version:    0.6.2
 */