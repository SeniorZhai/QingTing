package fm.qingting.async.future;

import fm.qingting.async.AsyncServer.AsyncSemaphore;
import java.util.concurrent.CancellationException;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

public class SimpleFuture<T> extends SimpleCancellable
  implements DependentFuture<T>
{
  FutureCallback<T> callback;
  Exception exception;
  T result;
  AsyncServer.AsyncSemaphore waiter;

  private T getResult()
    throws ExecutionException
  {
    if (this.exception != null)
      throw new ExecutionException(this.exception);
    return this.result;
  }

  private void handleCallbackUnlocked(FutureCallback<T> paramFutureCallback)
  {
    if (paramFutureCallback != null)
      paramFutureCallback.onCompleted(this.exception, this.result);
  }

  private FutureCallback<T> handleCompleteLocked()
  {
    FutureCallback localFutureCallback = this.callback;
    this.callback = null;
    return localFutureCallback;
  }

  public boolean cancel()
  {
    if (!super.cancel())
      return false;
    try
    {
      this.exception = new CancellationException();
      releaseWaiterLocked();
      return true;
    }
    finally
    {
    }
  }

  public boolean cancel(boolean paramBoolean)
  {
    return cancel();
  }

  AsyncServer.AsyncSemaphore ensureWaiterLocked()
  {
    if (this.waiter == null)
      this.waiter = new AsyncServer.AsyncSemaphore();
    return this.waiter;
  }

  public T get()
    throws InterruptedException, ExecutionException
  {
    try
    {
      if ((isCancelled()) || (isDone()))
      {
        localObject1 = getResult();
        return localObject1;
      }
      Object localObject1 = ensureWaiterLocked();
      ((AsyncServer.AsyncSemaphore)localObject1).acquire();
      return getResult();
    }
    finally
    {
    }
  }

  public T get(long paramLong, TimeUnit paramTimeUnit)
    throws InterruptedException, ExecutionException, TimeoutException
  {
    try
    {
      if ((isCancelled()) || (isDone()))
      {
        paramTimeUnit = getResult();
        return paramTimeUnit;
      }
      AsyncServer.AsyncSemaphore localAsyncSemaphore = ensureWaiterLocked();
      if (!localAsyncSemaphore.tryAcquire(paramLong, paramTimeUnit))
        throw new TimeoutException();
    }
    finally
    {
    }
    return getResult();
  }

  public FutureCallback<T> getCompletionCallback()
  {
    return new FutureCallback()
    {
      public void onCompleted(Exception paramAnonymousException, T paramAnonymousT)
      {
        SimpleFuture.this.setComplete(paramAnonymousException, paramAnonymousT);
      }
    };
  }

  void releaseWaiterLocked()
  {
    if (this.waiter != null)
    {
      this.waiter.release();
      this.waiter = null;
    }
  }

  public SimpleFuture<T> reset()
  {
    super.reset();
    this.result = null;
    this.exception = null;
    this.waiter = null;
    this.callback = null;
    return this;
  }

  public SimpleFuture<T> setCallback(FutureCallback<T> paramFutureCallback)
  {
    try
    {
      this.callback = paramFutureCallback;
      if (isDone());
      for (paramFutureCallback = handleCompleteLocked(); ; paramFutureCallback = null)
      {
        handleCallbackUnlocked(paramFutureCallback);
        return this;
      }
    }
    finally
    {
    }
    throw paramFutureCallback;
  }

  public boolean setComplete()
  {
    return setComplete((Object)null);
  }

  public boolean setComplete(Exception paramException)
  {
    try
    {
      if (!super.setComplete())
        return false;
      this.exception = paramException;
      releaseWaiterLocked();
      paramException = handleCompleteLocked();
      handleCallbackUnlocked(paramException);
      return true;
    }
    finally
    {
    }
    throw paramException;
  }

  public boolean setComplete(Exception paramException, T paramT)
  {
    if (paramException != null)
      return setComplete(paramException);
    return setComplete(paramT);
  }

  public boolean setComplete(T paramT)
  {
    try
    {
      if (!super.setComplete())
        return false;
      this.result = paramT;
      releaseWaiterLocked();
      paramT = handleCompleteLocked();
      handleCallbackUnlocked(paramT);
      return true;
    }
    finally
    {
    }
    throw paramT;
  }

  public SimpleFuture<T> setParent(Cancellable paramCancellable)
  {
    super.setParent(paramCancellable);
    return this;
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     fm.qingting.async.future.SimpleFuture
 * JD-Core Version:    0.6.2
 */