package fm.qingting.async.http;

import fm.qingting.async.AsyncSocket;
import fm.qingting.async.callback.CompletedCallback;
import fm.qingting.async.http.libcore.ResponseHeaders;

public abstract interface AsyncHttpResponse extends AsyncSocket
{
  public abstract AsyncSocket detachSocket();

  public abstract void end();

  public abstract CompletedCallback getEndCallback();

  public abstract ResponseHeaders getHeaders();

  public abstract void setEndCallback(CompletedCallback paramCompletedCallback);
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     fm.qingting.async.http.AsyncHttpResponse
 * JD-Core Version:    0.6.2
 */