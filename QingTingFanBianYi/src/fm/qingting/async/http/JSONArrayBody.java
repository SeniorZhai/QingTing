package fm.qingting.async.http;

import fm.qingting.async.DataEmitter;
import fm.qingting.async.DataSink;
import fm.qingting.async.Util;
import fm.qingting.async.callback.CompletedCallback;
import fm.qingting.async.future.Future;
import fm.qingting.async.future.FutureCallback;
import fm.qingting.async.parser.JSONArrayParser;
import org.json.JSONArray;

public class JSONArrayBody
  implements AsyncHttpRequestBody<JSONArray>
{
  public static final String CONTENT_TYPE = "application/json";
  JSONArray json;
  byte[] mBodyBytes;

  public JSONArrayBody()
  {
  }

  public JSONArrayBody(JSONArray paramJSONArray)
  {
    this();
    this.json = paramJSONArray;
  }

  public JSONArray get()
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
    new JSONArrayParser().parse(paramDataEmitter).setCallback(new FutureCallback()
    {
      public void onCompleted(Exception paramAnonymousException, JSONArray paramAnonymousJSONArray)
      {
        JSONArrayBody.this.json = paramAnonymousJSONArray;
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
 * Qualified Name:     fm.qingting.async.http.JSONArrayBody
 * JD-Core Version:    0.6.2
 */