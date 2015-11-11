package fm.qingting.async.future;

public abstract interface Future<T> extends Cancellable, java.util.concurrent.Future<T>
{
  public abstract Future<T> setCallback(FutureCallback<T> paramFutureCallback);
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     fm.qingting.async.future.Future
 * JD-Core Version:    0.6.2
 */