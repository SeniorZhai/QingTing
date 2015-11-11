package com.taobao.munion.base.volley.a;

import com.taobao.munion.base.volley.l;
import com.taobao.munion.base.volley.n.a;
import com.taobao.munion.base.volley.n.b;
import com.taobao.munion.base.volley.s;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

public class k<T>
  implements n.a, n.b<T>, Future<T>
{
  private l<?> a;
  private boolean b = false;
  private T c;
  private s d;

  public static <E> k<E> a()
  {
    return new k();
  }

  private T a(Long paramLong)
    throws InterruptedException, ExecutionException, TimeoutException
  {
    try
    {
      if (this.d != null)
        throw new ExecutionException(this.d);
    }
    finally
    {
    }
    if (this.b);
    for (paramLong = this.c; ; paramLong = this.c)
    {
      return paramLong;
      if (paramLong == null)
        wait(0L);
      while (this.d != null)
      {
        throw new ExecutionException(this.d);
        if (paramLong.longValue() > 0L)
          wait(paramLong.longValue());
      }
      if (!this.b)
        throw new TimeoutException();
    }
  }

  public void a(l<?> paraml)
  {
    this.a = paraml;
  }

  public void a(s params)
  {
    try
    {
      this.d = params;
      notifyAll();
      return;
    }
    finally
    {
      params = finally;
    }
    throw params;
  }

  public void a(T paramT)
  {
    try
    {
      this.b = true;
      this.c = paramT;
      notifyAll();
      return;
    }
    finally
    {
      paramT = finally;
    }
    throw paramT;
  }

  public boolean cancel(boolean paramBoolean)
  {
    paramBoolean = false;
    try
    {
      l locall = this.a;
      if (locall == null);
      while (true)
      {
        return paramBoolean;
        if (!isDone())
        {
          this.a.i();
          paramBoolean = true;
        }
      }
    }
    finally
    {
    }
  }

  public T get()
    throws InterruptedException, ExecutionException
  {
    try
    {
      Object localObject = a(null);
      return localObject;
    }
    catch (TimeoutException localTimeoutException)
    {
      throw new AssertionError(localTimeoutException);
    }
  }

  public T get(long paramLong, TimeUnit paramTimeUnit)
    throws InterruptedException, ExecutionException, TimeoutException
  {
    return a(Long.valueOf(TimeUnit.MILLISECONDS.convert(paramLong, paramTimeUnit)));
  }

  public boolean isCancelled()
  {
    if (this.a == null)
      return false;
    return this.a.j();
  }

  public boolean isDone()
  {
    try
    {
      if ((!this.b) && (this.d == null))
      {
        bool = isCancelled();
        if (!bool);
      }
      else
      {
        bool = true;
        return bool;
      }
      boolean bool = false;
    }
    finally
    {
    }
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     com.taobao.munion.base.volley.a.k
 * JD-Core Version:    0.6.2
 */