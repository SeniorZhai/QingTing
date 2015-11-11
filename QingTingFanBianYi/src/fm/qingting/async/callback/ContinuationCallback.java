package fm.qingting.async.callback;

import fm.qingting.async.future.Continuation;

public abstract interface ContinuationCallback
{
  public abstract void onContinue(Continuation paramContinuation, CompletedCallback paramCompletedCallback)
    throws Exception;
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     fm.qingting.async.callback.ContinuationCallback
 * JD-Core Version:    0.6.2
 */