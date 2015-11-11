package fm.qingting.async;

import fm.qingting.async.callback.CompletedCallback;
import fm.qingting.async.callback.DataCallback;

public abstract interface DataEmitter
{
  public abstract void close();

  public abstract DataCallback getDataCallback();

  public abstract CompletedCallback getEndCallback();

  public abstract AsyncServer getServer();

  public abstract boolean isChunked();

  public abstract boolean isPaused();

  public abstract void pause();

  public abstract void resume();

  public abstract void setDataCallback(DataCallback paramDataCallback);

  public abstract void setEndCallback(CompletedCallback paramCompletedCallback);
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     fm.qingting.async.DataEmitter
 * JD-Core Version:    0.6.2
 */