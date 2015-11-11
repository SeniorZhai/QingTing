package fm.qingting.async.http;

import fm.qingting.async.ByteBufferList;
import fm.qingting.async.DataEmitter;
import fm.qingting.async.DataSink;
import fm.qingting.async.Util;
import fm.qingting.async.callback.CompletedCallback;
import fm.qingting.async.callback.DataCallback;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Iterator;
import java.util.List;
import org.apache.http.NameValuePair;

public class UrlEncodedFormBody
  implements AsyncHttpRequestBody<Multimap>
{
  public static final String CONTENT_TYPE = "application/x-www-form-urlencoded";
  private byte[] mBodyBytes;
  private Multimap mParameters;

  public UrlEncodedFormBody()
  {
  }

  public UrlEncodedFormBody(Multimap paramMultimap)
  {
    this.mParameters = paramMultimap;
  }

  public UrlEncodedFormBody(List<NameValuePair> paramList)
  {
    this.mParameters = new Multimap(paramList);
  }

  private void buildData()
  {
    StringBuilder localStringBuilder = new StringBuilder();
    try
    {
      Iterator localIterator = this.mParameters.iterator();
      int i = 1;
      while (localIterator.hasNext())
      {
        NameValuePair localNameValuePair = (NameValuePair)localIterator.next();
        if (i == 0)
          localStringBuilder.append('&');
        i = 0;
        localStringBuilder.append(URLEncoder.encode(localNameValuePair.getName(), "UTF-8"));
        localStringBuilder.append('=');
        localStringBuilder.append(URLEncoder.encode(localNameValuePair.getValue(), "UTF-8"));
      }
      this.mBodyBytes = localStringBuilder.toString().getBytes("ISO-8859-1");
      return;
    }
    catch (UnsupportedEncodingException localUnsupportedEncodingException)
    {
    }
  }

  public Multimap get()
  {
    return this.mParameters;
  }

  public String getContentType()
  {
    return "application/x-www-form-urlencoded";
  }

  public int length()
  {
    if (this.mBodyBytes == null)
      buildData();
    return this.mBodyBytes.length;
  }

  public void parse(DataEmitter paramDataEmitter, final CompletedCallback paramCompletedCallback)
  {
    final ByteBufferList localByteBufferList = new ByteBufferList();
    paramDataEmitter.setDataCallback(new DataCallback()
    {
      public void onDataAvailable(DataEmitter paramAnonymousDataEmitter, ByteBufferList paramAnonymousByteBufferList)
      {
        paramAnonymousByteBufferList.get(localByteBufferList);
      }
    });
    paramDataEmitter.setEndCallback(new CompletedCallback()
    {
      public void onCompleted(Exception paramAnonymousException)
      {
        if (paramAnonymousException != null)
        {
          paramCompletedCallback.onCompleted(paramAnonymousException);
          return;
        }
        try
        {
          UrlEncodedFormBody.access$002(UrlEncodedFormBody.this, Multimap.parseQuery(localByteBufferList.readString()));
          paramCompletedCallback.onCompleted(null);
          return;
        }
        catch (Exception paramAnonymousException)
        {
          paramCompletedCallback.onCompleted(paramAnonymousException);
        }
      }
    });
  }

  public boolean readFullyOnRequest()
  {
    return true;
  }

  public void write(AsyncHttpRequest paramAsyncHttpRequest, DataSink paramDataSink)
  {
    if (this.mBodyBytes == null)
      buildData();
    Util.writeAll(paramDataSink, this.mBodyBytes, null);
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     fm.qingting.async.http.UrlEncodedFormBody
 * JD-Core Version:    0.6.2
 */