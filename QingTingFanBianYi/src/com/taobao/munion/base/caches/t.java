package com.taobao.munion.base.caches;

import java.util.Iterator;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class t
{
  public static ConcurrentLinkedQueue<m> a = new ConcurrentLinkedQueue();
  private static final int b = Runtime.getRuntime().availableProcessors();
  private static final int c = b + 1;
  private static final int d = b * 2 + 1;
  private static final int e = 500;
  private static t f;
  private ExecutorService g = null;

  private t()
  {
    if (this.g == null)
      this.g = new ThreadPoolExecutor(c, d, 500L, TimeUnit.MILLISECONDS, new LinkedBlockingQueue());
  }

  public static t b()
  {
    try
    {
      if (f == null)
        f = new t();
      t localt = f;
      return localt;
    }
    finally
    {
    }
  }

  public void a()
  {
    if (!a.isEmpty())
    {
      Iterator localIterator = a.iterator();
      while (localIterator.hasNext())
        ((m)localIterator.next()).b();
    }
    a.clear();
  }

  public void a(m paramm)
  {
    if ((a != null) && (a.contains(paramm)))
      a.remove(paramm);
  }

  public void a(Runnable paramRunnable)
  {
    if (paramRunnable == null)
      return;
    this.g.execute(paramRunnable);
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     com.taobao.munion.base.caches.t
 * JD-Core Version:    0.6.2
 */