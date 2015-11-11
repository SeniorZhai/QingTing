package com.android.volley.toolbox;

import com.android.volley.Request;
import com.android.volley.Response.ErrorListener;
import com.android.volley.Response.Listener;
import com.android.volley.VolleyError;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

public class RequestFuture<T>
  implements Future<T>, Response.Listener<T>, Response.ErrorListener
{
  private VolleyError mException;
  private Request<?> mRequest;
  private T mResult;
  private boolean mResultReceived = false;

  private T doGet(Long paramLong)
    throws InterruptedException, ExecutionException, TimeoutException
  {
    try
    {
      if (this.mException != null)
        throw new ExecutionException(this.mException);
    }
    finally
    {
    }
    if (this.mResultReceived);
    for (paramLong = this.mResult; ; paramLong = this.mResult)
    {
      return paramLong;
      if (paramLong == null)
        wait(0L);
      while (this.mException != null)
      {
        throw new ExecutionException(this.mException);
        if (paramLong.longValue() > 0L)
          wait(paramLong.longValue());
      }
      if (!this.mResultReceived)
        throw new TimeoutException();
    }
  }

  public static <E> RequestFuture<E> newFuture()
  {
    return new RequestFuture();
  }

  public boolean cancel(boolean paramBoolean)
  {
    paramBoolean = false;
    try
    {
      Request localRequest = this.mRequest;
      if (localRequest == null);
      while (true)
      {
        return paramBoolean;
        if (!isDone())
        {
          this.mRequest.cancel();
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
      Object localObject = doGet(null);
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
    return doGet(Long.valueOf(TimeUnit.MILLISECONDS.convert(paramLong, paramTimeUnit)));
  }

  public boolean isCancelled()
  {
    if (this.mRequest == null)
      return false;
    return this.mRequest.isCanceled();
  }

  public boolean isDone()
  {
    try
    {
      if ((!this.mResultReceived) && (this.mException == null))
      {
        bool = isCancelled();
        if (!bool)
        {
          bool = false;
          return bool;
        }
      }
      boolean bool = true;
    }
    finally
    {
    }
  }

  public void onErrorResponse(VolleyError paramVolleyError)
  {
    try
    {
      this.mException = paramVolleyError;
      notifyAll();
      return;
    }
    finally
    {
      paramVolleyError = finally;
    }
    throw paramVolleyError;
  }

  public void onResponse(T paramT)
  {
    try
    {
      this.mResultReceived = true;
      this.mResult = paramT;
      notifyAll();
      return;
    }
    finally
    {
      paramT = finally;
    }
    throw paramT;
  }

  public void setRequest(Request<?> paramRequest)
  {
    this.mRequest = paramRequest;
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     com.android.volley.toolbox.RequestFuture
 * JD-Core Version:    0.6.2
 */