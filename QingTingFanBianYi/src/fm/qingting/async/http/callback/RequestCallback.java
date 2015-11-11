package fm.qingting.async.http.callback;

import fm.qingting.async.callback.ResultCallback;
import fm.qingting.async.http.AsyncHttpResponse;

public abstract interface RequestCallback<T> extends ResultCallback<AsyncHttpResponse, T>
{
  public abstract void onConnect(AsyncHttpResponse paramAsyncHttpResponse);

  public abstract void onProgress(AsyncHttpResponse paramAsyncHttpResponse, int paramInt1, int paramInt2);
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     fm.qingting.async.http.callback.RequestCallback
 * JD-Core Version:    0.6.2
 */