package fm.qingting.async.http.server;

import fm.qingting.async.DataEmitter;
import fm.qingting.async.DataSink;
import fm.qingting.async.NullDataCallback;
import fm.qingting.async.callback.CompletedCallback;
import fm.qingting.async.http.AsyncHttpRequest;
import fm.qingting.async.http.AsyncHttpRequestBody;

public class UnknownRequestBody
  implements AsyncHttpRequestBody<Void>
{
  private String mContentType;

  static
  {
    if (!UnknownRequestBody.class.desiredAssertionStatus());
    for (boolean bool = true; ; bool = false)
    {
      $assertionsDisabled = bool;
      return;
    }
  }

  public UnknownRequestBody(String paramString)
  {
    this.mContentType = paramString;
  }

  public Void get()
  {
    return null;
  }

  public String getContentType()
  {
    return this.mContentType;
  }

  public int length()
  {
    return -1;
  }

  public void parse(DataEmitter paramDataEmitter, CompletedCallback paramCompletedCallback)
  {
    paramDataEmitter.setEndCallback(paramCompletedCallback);
    paramDataEmitter.setDataCallback(new NullDataCallback());
  }

  public boolean readFullyOnRequest()
  {
    return false;
  }

  public void write(AsyncHttpRequest paramAsyncHttpRequest, DataSink paramDataSink)
  {
    if (!$assertionsDisabled)
      throw new AssertionError();
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     fm.qingting.async.http.server.UnknownRequestBody
 * JD-Core Version:    0.6.2
 */