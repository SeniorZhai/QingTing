package fm.qingting.async;

import fm.qingting.async.callback.CompletedCallback;
import fm.qingting.async.callback.DataCallback;

public abstract class DataEmitterBase
  implements DataEmitter
{
  CompletedCallback endCallback;
  boolean ended;
  DataCallback mDataCallback;

  public DataCallback getDataCallback()
  {
    return this.mDataCallback;
  }

  public final CompletedCallback getEndCallback()
  {
    return this.endCallback;
  }

  protected void report(Exception paramException)
  {
    if (this.ended);
    do
    {
      return;
      this.ended = true;
    }
    while (getEndCallback() == null);
    getEndCallback().onCompleted(paramException);
  }

  public void setDataCallback(DataCallback paramDataCallback)
  {
    this.mDataCallback = paramDataCallback;
  }

  public final void setEndCallback(CompletedCallback paramCompletedCallback)
  {
    this.endCallback = paramCompletedCallback;
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     fm.qingting.async.DataEmitterBase
 * JD-Core Version:    0.6.2
 */