package fm.qingting.async.future;

import fm.qingting.async.callback.CompletedCallback;
import fm.qingting.async.callback.ContinuationCallback;
import java.util.LinkedList;

public class Continuation extends SimpleCancellable
  implements ContinuationCallback, Runnable, Cancellable
{
  CompletedCallback callback;
  Runnable cancelCallback;
  private boolean inNext;
  LinkedList<ContinuationCallback> mCallbacks = new LinkedList();
  boolean started;
  private boolean waiting;

  public Continuation()
  {
    this(null);
  }

  public Continuation(CompletedCallback paramCompletedCallback)
  {
    this(paramCompletedCallback, null);
  }

  public Continuation(CompletedCallback paramCompletedCallback, Runnable paramRunnable)
  {
    this.cancelCallback = paramRunnable;
    this.callback = paramCompletedCallback;
  }

  private ContinuationCallback hook(ContinuationCallback paramContinuationCallback)
  {
    if ((paramContinuationCallback instanceof DependentCancellable))
      ((DependentCancellable)paramContinuationCallback).setParent(this);
    return paramContinuationCallback;
  }

  private void next()
  {
    if (this.inNext);
    do
    {
      return;
      while ((this.mCallbacks.size() > 0) && (!this.waiting) && (!isDone()) && (!isCancelled()))
      {
        ContinuationCallback localContinuationCallback = (Cancellable)this.mCallbacks.remove();
        try
        {
          this.inNext = true;
          this.waiting = true;
          localContinuationCallback.onContinue(this, wrap());
          this.inNext = false;
        }
        catch (Exception localException)
        {
          reportCompleted(localException);
          this.inNext = false;
        }
        finally
        {
          this.inNext = false;
        }
      }
    }
    while ((this.waiting) || (isDone()) || (isCancelled()));
    reportCompleted(null);
  }

  private CompletedCallback wrap()
  {
    return new CompletedCallback()
    {
      boolean mThisCompleted;

      static
      {
        if (!Continuation.class.desiredAssertionStatus());
        for (boolean bool = true; ; bool = false)
        {
          $assertionsDisabled = bool;
          return;
        }
      }

      public void onCompleted(Exception paramAnonymousException)
      {
        if (this.mThisCompleted)
          return;
        this.mThisCompleted = true;
        assert (Continuation.this.waiting);
        Continuation.access$002(Continuation.this, false);
        if (paramAnonymousException == null)
        {
          Continuation.this.next();
          return;
        }
        Continuation.this.reportCompleted(paramAnonymousException);
      }
    };
  }

  public Continuation add(ContinuationCallback paramContinuationCallback)
  {
    this.mCallbacks.add(hook(paramContinuationCallback));
    return this;
  }

  public void add(final DependentFuture paramDependentFuture)
  {
    paramDependentFuture.setParent(this);
    add(new ContinuationCallback()
    {
      public void onContinue(Continuation paramAnonymousContinuation, CompletedCallback paramAnonymousCompletedCallback)
        throws Exception
      {
        paramDependentFuture.get();
        paramAnonymousCompletedCallback.onCompleted(null);
      }
    });
  }

  public boolean cancel()
  {
    if (!super.cancel())
      return false;
    if (this.cancelCallback != null)
      this.cancelCallback.run();
    return true;
  }

  public CompletedCallback getCallback()
  {
    return this.callback;
  }

  public Runnable getCancelCallback()
  {
    return this.cancelCallback;
  }

  public Continuation insert(ContinuationCallback paramContinuationCallback)
  {
    this.mCallbacks.add(0, hook(paramContinuationCallback));
    return this;
  }

  public void onContinue(Continuation paramContinuation, CompletedCallback paramCompletedCallback)
    throws Exception
  {
    setCallback(paramCompletedCallback);
    start();
  }

  void reportCompleted(Exception paramException)
  {
    if (!setComplete());
    while (this.callback == null)
      return;
    this.callback.onCompleted(paramException);
  }

  public void run()
  {
    start();
  }

  public void setCallback(CompletedCallback paramCompletedCallback)
  {
    this.callback = paramCompletedCallback;
  }

  public void setCancelCallback(final Cancellable paramCancellable)
  {
    if (paramCancellable == null)
    {
      this.cancelCallback = null;
      return;
    }
    this.cancelCallback = new Runnable()
    {
      public void run()
      {
        paramCancellable.cancel();
      }
    };
  }

  public void setCancelCallback(Runnable paramRunnable)
  {
    this.cancelCallback = paramRunnable;
  }

  public Continuation start()
  {
    if (this.started)
      throw new IllegalStateException("already started");
    this.started = true;
    next();
    return this;
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     fm.qingting.async.future.Continuation
 * JD-Core Version:    0.6.2
 */