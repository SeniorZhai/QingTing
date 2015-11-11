package fm.qingting.async.http;

import fm.qingting.async.DataEmitter;
import fm.qingting.async.DataSink;
import fm.qingting.async.Util;
import fm.qingting.async.callback.CompletedCallback;
import fm.qingting.async.future.Future;
import fm.qingting.async.future.FutureCallback;
import fm.qingting.async.parser.StringParser;

public class StringBody
  implements AsyncHttpRequestBody<String>
{
  public static final String CONTENT_TYPE = "text/plain";
  byte[] mBodyBytes;
  String string;

  public StringBody()
  {
  }

  public StringBody(String paramString)
  {
    this();
    this.string = paramString;
  }

  public String get()
  {
    return toString();
  }

  public String getContentType()
  {
    return "text/plain";
  }

  public int length()
  {
    if (this.mBodyBytes == null)
      this.mBodyBytes = this.string.getBytes();
    return this.mBodyBytes.length;
  }

  public void parse(DataEmitter paramDataEmitter, final CompletedCallback paramCompletedCallback)
  {
    new StringParser().parse(paramDataEmitter).setCallback(new FutureCallback()
    {
      public void onCompleted(Exception paramAnonymousException, String paramAnonymousString)
      {
        StringBody.this.string = paramAnonymousString;
        paramCompletedCallback.onCompleted(paramAnonymousException);
      }
    });
  }

  public boolean readFullyOnRequest()
  {
    return true;
  }

  public String toString()
  {
    return this.string;
  }

  public void write(AsyncHttpRequest paramAsyncHttpRequest, DataSink paramDataSink)
  {
    if (this.mBodyBytes == null)
      this.mBodyBytes = this.string.getBytes();
    Util.writeAll(paramDataSink, this.mBodyBytes, null);
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     fm.qingting.async.http.StringBody
 * JD-Core Version:    0.6.2
 */