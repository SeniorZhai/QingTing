package com.taobao.munion.base;

import android.os.AsyncTask;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

public class b
{
  private static boolean a = false;
  private static AtomicInteger b = new AtomicInteger(0);
  private static Map<Integer, AsyncTask<?, ?, ?>> c = new ConcurrentHashMap();

  public static int a(AsyncTask<?, ?, ?> paramAsyncTask)
  {
    int i = b.getAndIncrement();
    c.put(Integer.valueOf(i), paramAsyncTask);
    return i;
  }

  public static void a(int paramInt)
  {
    c.remove(Integer.valueOf(paramInt));
  }

  public static void a(boolean paramBoolean)
  {
    a = paramBoolean;
  }

  public static boolean a()
  {
    return a;
  }

  public static boolean a(long paramLong, TimeUnit paramTimeUnit)
  {
    while (true)
    {
      try
      {
        Object localObject = c.entrySet();
        if (!((Set)localObject).isEmpty())
        {
          int j = ((Set)localObject).size();
          if (j <= 0)
            break label160;
          paramLong /= j;
          localObject = ((Set)localObject).iterator();
          i = 0;
          if (((Iterator)localObject).hasNext())
          {
            AsyncTask localAsyncTask = (AsyncTask)((Map.Entry)((Iterator)localObject).next()).getValue();
            try
            {
              localAsyncTask.get(paramLong, paramTimeUnit);
              i += 1;
            }
            catch (Exception localException)
            {
              if (!localAsyncTask.cancel(true))
                break label157;
            }
            i += 1;
            continue;
          }
          c.clear();
          if (i == j)
          {
            bool = true;
            return bool;
          }
          bool = false;
          continue;
        }
        boolean bool = true;
        continue;
      }
      finally
      {
      }
      label157: continue;
      label160: int i = 0;
    }
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     com.taobao.munion.base.b
 * JD-Core Version:    0.6.2
 */