package fm.qingting.async.http;

import fm.qingting.async.DataEmitter;
import fm.qingting.async.DataSink;
import fm.qingting.async.Util;
import fm.qingting.async.callback.CompletedCallback;
import fm.qingting.async.future.Future;
import fm.qingting.async.future.FutureCallback;
import fm.qingting.async.parser.JSONObjectParser;
import org.json.JSONObject;

public class JSONObjectBody
  implements AsyncHttpRequestBody<JSONObject>
{
  public static final String CONTENT_TYPE = "application/json";
  JSONObject json;
  byte[] mBodyBytes;

  public JSONObjectBody()
  {
  }

  public JSONObjectBody(JSONObject paramJSONObject)
  {
    this();
    this.json = paramJSONObject;
  }

  public JSONObject get()
  {
    return this.json;
  }

  public String getContentType()
  {
    return "application/json";
  }

  public int length()
  {
    this.mBodyBytes = this.json.toString().getBytes();
    return this.mBodyBytes.length;
  }

  public void parse(DataEmitter paramDataEmitter, final CompletedCallback paramCompletedCallback)
  {
    new JSONObjectParser().parse(paramDataEmitter).setCallback(new FutureCallback()
    {
      public void onCompleted(Exception paramAnonymousException, JSONObject paramAnonymousJSONObject)
      {
        JSONObjectBody.this.json = paramAnonymousJSONObject;
        paramCompletedCallback.onCompleted(paramAnonymousException);
      }
    });
  }

  public boolean readFullyOnRequest()
  {
    return true;
  }

  public void write(AsyncHttpRequest paramAsyncHttpRequest, DataSink paramDataSink)
  {
    Util.writeAll(paramDataSink, this.mBodyBytes, null);
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     fm.qingting.async.http.JSONObjectBody
 * JD-Core Version:    0.6.2
 */