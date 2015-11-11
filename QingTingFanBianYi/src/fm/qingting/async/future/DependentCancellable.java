package fm.qingting.async.future;

public abstract interface DependentCancellable extends Cancellable
{
  public abstract DependentCancellable setParent(Cancellable paramCancellable);
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     fm.qingting.async.future.DependentCancellable
 * JD-Core Version:    0.6.2
 */