package com.taobao.munion.base.volley;

import android.os.Handler;
import android.os.Looper;
import com.taobao.munion.base.Log;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.Set;
import java.util.concurrent.PriorityBlockingQueue;
import java.util.concurrent.atomic.AtomicInteger;

public class m
{
  private static final int f = 4;
  private AtomicInteger a = new AtomicInteger();
  private final Map<String, Queue<l<?>>> b = new HashMap();
  private final Set<l<?>> c = new HashSet();
  private final PriorityBlockingQueue<l<?>> d = new PriorityBlockingQueue();
  private final PriorityBlockingQueue<l<?>> e = new PriorityBlockingQueue();
  private final b g;
  private final f h;
  private final o i;
  private g[] j;
  private c k;

  public m(b paramb, f paramf)
  {
    this(paramb, paramf, 4);
  }

  public m(b paramb, f paramf, int paramInt)
  {
    this(paramb, paramf, paramInt, new e(new Handler(Looper.getMainLooper())));
  }

  public m(b paramb, f paramf, int paramInt, o paramo)
  {
    this.g = paramb;
    this.h = paramf;
    this.j = new g[paramInt];
    this.i = paramo;
  }

  public <T> l<T> a(l<T> paraml)
  {
    paraml.a(this);
    synchronized (this.c)
    {
      this.c.add(paraml);
      paraml.a(c());
      paraml.a("add-to-queue");
      if (!paraml.t())
      {
        this.e.add(paraml);
        return paraml;
      }
    }
    while (true)
    {
      String str;
      synchronized (this.b)
      {
        str = paraml.g();
        if (this.b.containsKey(str))
        {
          Queue localQueue = (Queue)this.b.get(str);
          ??? = localQueue;
          if (localQueue == null)
            ??? = new LinkedList();
          ((Queue)???).add(paraml);
          this.b.put(str, ???);
          if (Log.DEBUG)
            Log.v("Request for cacheKey=%s is in flight, putting on hold.", new Object[] { str });
          return paraml;
        }
      }
      this.b.put(str, null);
      this.d.add(paraml);
    }
  }

  public void a()
  {
    b();
    this.k = new c(this.d, this.e, this.g, this.i);
    this.k.start();
    int m = 0;
    while (m < this.j.length)
    {
      g localg = new g(this.e, this.h, this.g, this.i);
      this.j[m] = localg;
      localg.start();
      m += 1;
    }
  }

  public void a(a parama)
  {
    synchronized (this.c)
    {
      Iterator localIterator = this.c.iterator();
      while (localIterator.hasNext())
      {
        l locall = (l)localIterator.next();
        if (parama.a(locall))
          locall.i();
      }
    }
  }

  public void a(final Object paramObject)
  {
    if (paramObject == null)
      throw new IllegalArgumentException("Cannot cancelAll with a null tag");
    a(new a()
    {
      public boolean a(l<?> paramAnonymousl)
      {
        return paramAnonymousl.c() == paramObject;
      }
    });
  }

  public void b()
  {
    if (this.k != null)
      this.k.a();
    int m = 0;
    while (m < this.j.length)
    {
      if (this.j[m] != null)
        this.j[m].a();
      m += 1;
    }
  }

  void b(l<?> paraml)
  {
    synchronized (this.c)
    {
      this.c.remove(paraml);
      if (!paraml.t());
    }
    synchronized (this.b)
    {
      paraml = paraml.g();
      Queue localQueue = (Queue)this.b.remove(paraml);
      if (localQueue != null)
      {
        if (Log.DEBUG)
          Log.v("Releasing %d waiting requests for cacheKey=%s.", new Object[] { Integer.valueOf(localQueue.size()), paraml });
        this.d.addAll(localQueue);
      }
      return;
      paraml = finally;
      throw paraml;
    }
  }

  public int c()
  {
    return this.a.incrementAndGet();
  }

  public b d()
  {
    return this.g;
  }

  public static abstract interface a
  {
    public abstract boolean a(l<?> paraml);
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     com.taobao.munion.base.volley.m
 * JD-Core Version:    0.6.2
 */