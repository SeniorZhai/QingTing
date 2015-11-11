package fm.qingting.async.callback;

public abstract interface ResultCallback<S, T>
{
  public abstract void onCompleted(Exception paramException, S paramS, T paramT);
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     fm.qingting.async.callback.ResultCallback
 * JD-Core Version:    0.6.2
 */