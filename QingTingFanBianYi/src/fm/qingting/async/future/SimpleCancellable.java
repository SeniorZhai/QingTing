package fm.qingting.async.future;

public class SimpleCancellable
  implements DependentCancellable
{
  public static final Cancellable COMPLETED;
  boolean cancelled;
  boolean complete;
  private Cancellable parent;

  static
  {
    if (!SimpleCancellable.class.desiredAssertionStatus());
    for (boolean bool = true; ; bool = false)
    {
      $assertionsDisabled = bool;
      COMPLETED = new SimpleCancellable()
      {
      };
      return;
    }
  }

  public boolean cancel()
  {
    try
    {
      if (this.complete)
        return false;
      if (this.cancelled)
        return true;
    }
    finally
    {
    }
    this.cancelled = true;
    Cancellable localCancellable = this.parent;
    this.parent = null;
    if (localCancellable != null)
      localCancellable.cancel();
    cancelCleanup();
    cleanup();
    return true;
  }

  protected void cancelCleanup()
  {
  }

  protected void cleanup()
  {
  }

  protected void completeCleanup()
  {
  }

  public boolean isCancelled()
  {
    return (this.cancelled) || ((this.parent != null) && (this.parent.isCancelled()));
  }

  public boolean isDone()
  {
    return this.complete;
  }

  public Cancellable reset()
  {
    cancel();
    this.complete = false;
    this.cancelled = false;
    return this;
  }

  public boolean setComplete()
  {
    try
    {
      if (this.cancelled)
        return false;
      if (!this.complete)
        break label43;
      if (!$assertionsDisabled)
        throw new AssertionError();
    }
    finally
    {
    }
    return true;
    label43: this.complete = true;
    this.parent = null;
    completeCleanup();
    cleanup();
    return true;
  }

  public SimpleCancellable setParent(Cancellable paramCancellable)
  {
    try
    {
      if (!isDone())
        this.parent = paramCancellable;
      return this;
    }
    finally
    {
    }
    throw paramCancellable;
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     fm.qingting.async.future.SimpleCancellable
 * JD-Core Version:    0.6.2
 */