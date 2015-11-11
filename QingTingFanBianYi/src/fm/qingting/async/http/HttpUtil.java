package fm.qingting.async.http;

import fm.qingting.async.AsyncServer;
import fm.qingting.async.DataEmitter;
import fm.qingting.async.FilteredDataEmitter;
import fm.qingting.async.callback.CompletedCallback;
import fm.qingting.async.http.filter.ChunkedInputFilter;
import fm.qingting.async.http.filter.ContentLengthFilter;
import fm.qingting.async.http.filter.GZIPInputFilter;
import fm.qingting.async.http.filter.InflaterInputFilter;
import fm.qingting.async.http.libcore.RawHeaders;
import fm.qingting.async.http.server.UnknownRequestBody;

public class HttpUtil
{
  public static AsyncHttpRequestBody getBody(DataEmitter paramDataEmitter, CompletedCallback paramCompletedCallback, RawHeaders paramRawHeaders)
  {
    int j = 0;
    paramDataEmitter = paramRawHeaders.get("Content-Type");
    if (paramDataEmitter != null)
    {
      paramCompletedCallback = paramDataEmitter.split(";");
      int i = 0;
      while (i < paramCompletedCallback.length)
      {
        paramCompletedCallback[i] = paramCompletedCallback[i].trim();
        i += 1;
      }
      int k = paramCompletedCallback.length;
      i = j;
      while (i < k)
      {
        paramRawHeaders = paramCompletedCallback[i];
        if ("application/x-www-form-urlencoded".equals(paramRawHeaders))
          return new UrlEncodedFormBody();
        if ("application/json".equals(paramRawHeaders))
          return new JSONObjectBody();
        if ("text/plain".equals(paramRawHeaders))
          return new StringBody();
        if ("multipart/form-data".equals(paramRawHeaders))
          return new MultipartFormDataBody(paramDataEmitter, paramCompletedCallback);
        i += 1;
      }
    }
    return new UnknownRequestBody(paramDataEmitter);
  }

  public static DataEmitter getBodyDecoder(DataEmitter paramDataEmitter, RawHeaders paramRawHeaders, boolean paramBoolean)
  {
    try
    {
      i = Integer.parseInt(paramRawHeaders.get("Content-Length"));
      if (-1 != i)
        if (i < 0)
        {
          paramRawHeaders = EndEmitter.create(paramDataEmitter.getServer(), new Exception("not using chunked encoding, and no content-length found."));
          paramRawHeaders.setDataEmitter(paramDataEmitter);
          paramDataEmitter = paramRawHeaders;
          return paramDataEmitter;
        }
    }
    catch (Exception localException)
    {
      Object localObject;
      do
      {
        int i;
        while (true)
          i = -1;
        if (i == 0)
        {
          paramRawHeaders = EndEmitter.create(paramDataEmitter.getServer(), null);
          paramRawHeaders.setDataEmitter(paramDataEmitter);
          return paramRawHeaders;
        }
        localObject = new ContentLengthFilter(i);
        ((ContentLengthFilter)localObject).setDataEmitter(paramDataEmitter);
        while ("gzip".equals(paramRawHeaders.get("Content-Encoding")))
        {
          paramDataEmitter = new GZIPInputFilter();
          paramDataEmitter.setDataEmitter((DataEmitter)localObject);
          return paramDataEmitter;
          if ("chunked".equalsIgnoreCase(paramRawHeaders.get("Transfer-Encoding")))
          {
            localObject = new ChunkedInputFilter();
            ((ChunkedInputFilter)localObject).setDataEmitter(paramDataEmitter);
          }
          else if (!paramBoolean)
          {
            localObject = paramDataEmitter;
            if (!paramRawHeaders.getStatusLine().contains("HTTP/1.1"))
              break;
          }
          else
          {
            paramRawHeaders = EndEmitter.create(paramDataEmitter.getServer(), null);
            paramRawHeaders.setDataEmitter(paramDataEmitter);
            return paramRawHeaders;
          }
        }
        paramDataEmitter = (DataEmitter)localObject;
      }
      while (!"deflate".equals(paramRawHeaders.get("Content-Encoding")));
      paramDataEmitter = new InflaterInputFilter();
      paramDataEmitter.setDataEmitter((DataEmitter)localObject);
    }
    return paramDataEmitter;
  }

  private static class EndEmitter extends FilteredDataEmitter
  {
    public static EndEmitter create(AsyncServer paramAsyncServer, final Exception paramException)
    {
      EndEmitter localEndEmitter = new EndEmitter();
      paramAsyncServer.post(new Runnable()
      {
        public void run()
        {
          this.val$ret.report(paramException);
        }
      });
      return localEndEmitter;
    }
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     fm.qingting.async.http.HttpUtil
 * JD-Core Version:    0.6.2
 */