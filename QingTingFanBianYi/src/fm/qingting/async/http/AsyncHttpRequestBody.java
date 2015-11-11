package fm.qingting.async.http;

import fm.qingting.async.DataEmitter;
import fm.qingting.async.DataSink;
import fm.qingting.async.callback.CompletedCallback;

public abstract interface AsyncHttpRequestBody<T>
{
  public abstract T get();

  public abstract String getContentType();

  public abstract int length();

  public abstract void parse(DataEmitter paramDataEmitter, CompletedCallback paramCompletedCallback);

  public abstract boolean readFullyOnRequest();

  public abstract void write(AsyncHttpRequest paramAsyncHttpRequest, DataSink paramDataSink);
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     fm.qingting.async.http.AsyncHttpRequestBody
 * JD-Core Version:    0.6.2
 */