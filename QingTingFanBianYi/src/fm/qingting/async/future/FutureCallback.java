package fm.qingting.async.future;

public abstract interface FutureCallback<T>
{
  public abstract void onCompleted(Exception paramException, T paramT);
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     fm.qingting.async.future.FutureCallback
 * JD-Core Version:    0.6.2
 */