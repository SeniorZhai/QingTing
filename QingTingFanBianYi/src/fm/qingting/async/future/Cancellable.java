package fm.qingting.async.future;

public abstract interface Cancellable
{
  public abstract boolean cancel();

  public abstract boolean isCancelled();

  public abstract boolean isDone();
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     fm.qingting.async.future.Cancellable
 * JD-Core Version:    0.6.2
 */