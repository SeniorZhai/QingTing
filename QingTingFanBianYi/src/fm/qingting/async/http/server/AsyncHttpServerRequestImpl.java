package fm.qingting.async.http.server;

import fm.qingting.async.AsyncSocket;
import fm.qingting.async.FilteredDataEmitter;
import fm.qingting.async.LineEmitter;
import fm.qingting.async.LineEmitter.StringCallback;
import fm.qingting.async.callback.CompletedCallback;
import fm.qingting.async.callback.DataCallback;
import fm.qingting.async.http.AsyncHttpRequestBody;
import fm.qingting.async.http.HttpUtil;
import fm.qingting.async.http.libcore.RawHeaders;
import fm.qingting.async.http.libcore.RequestHeaders;
import java.io.PrintStream;
import java.util.regex.Matcher;

public abstract class AsyncHttpServerRequestImpl extends FilteredDataEmitter
  implements AsyncHttpServerRequest, CompletedCallback
{
  AsyncHttpRequestBody mBody;
  LineEmitter.StringCallback mHeaderCallback = new LineEmitter.StringCallback()
  {
    public void onStringAvailable(String paramAnonymousString)
    {
      try
      {
        if (AsyncHttpServerRequestImpl.this.mRawHeaders.getStatusLine() == null)
        {
          AsyncHttpServerRequestImpl.this.mRawHeaders.setStatusLine(paramAnonymousString);
          if (AsyncHttpServerRequestImpl.this.mRawHeaders.getStatusLine().contains("HTTP/"))
            return;
          AsyncHttpServerRequestImpl.this.onNotHttp();
          AsyncHttpServerRequestImpl.this.mSocket.setDataCallback(null);
          return;
        }
        if (!"\r".equals(paramAnonymousString))
        {
          AsyncHttpServerRequestImpl.this.mRawHeaders.addLine(paramAnonymousString);
          return;
        }
      }
      catch (Exception paramAnonymousString)
      {
        AsyncHttpServerRequestImpl.this.onCompleted(paramAnonymousString);
        return;
      }
      paramAnonymousString = HttpUtil.getBodyDecoder(AsyncHttpServerRequestImpl.this.mSocket, AsyncHttpServerRequestImpl.this.mRawHeaders, true);
      AsyncHttpServerRequestImpl.this.mBody = HttpUtil.getBody(paramAnonymousString, AsyncHttpServerRequestImpl.this.mReporter, AsyncHttpServerRequestImpl.this.mRawHeaders);
      AsyncHttpServerRequestImpl.this.mBody.parse(paramAnonymousString, AsyncHttpServerRequestImpl.this.mReporter);
      AsyncHttpServerRequestImpl.access$202(AsyncHttpServerRequestImpl.this, new RequestHeaders(null, AsyncHttpServerRequestImpl.this.mRawHeaders));
      AsyncHttpServerRequestImpl.this.onHeadersReceived();
    }
  };
  private RequestHeaders mHeaders;
  Matcher mMatcher;
  private RawHeaders mRawHeaders = new RawHeaders();
  private CompletedCallback mReporter = new CompletedCallback()
  {
    public void onCompleted(Exception paramAnonymousException)
    {
      AsyncHttpServerRequestImpl.this.onCompleted(paramAnonymousException);
    }
  };
  AsyncSocket mSocket;
  String method;

  public AsyncHttpRequestBody getBody()
  {
    return this.mBody;
  }

  public DataCallback getDataCallback()
  {
    return this.mSocket.getDataCallback();
  }

  public RequestHeaders getHeaders()
  {
    return this.mHeaders;
  }

  public Matcher getMatcher()
  {
    return this.mMatcher;
  }

  public String getMethod()
  {
    return this.method;
  }

  RawHeaders getRawHeaders()
  {
    return this.mRawHeaders;
  }

  public AsyncSocket getSocket()
  {
    return this.mSocket;
  }

  public boolean isChunked()
  {
    return this.mSocket.isChunked();
  }

  public boolean isPaused()
  {
    return this.mSocket.isPaused();
  }

  public void onCompleted(Exception paramException)
  {
    report(paramException);
  }

  protected abstract void onHeadersReceived();

  protected void onNotHttp()
  {
    System.out.println("not http: " + this.mRawHeaders.getStatusLine());
    System.out.println("not http: " + this.mRawHeaders.getStatusLine().length());
  }

  public void pause()
  {
    this.mSocket.pause();
  }

  public void resume()
  {
    this.mSocket.resume();
  }

  public void setDataCallback(DataCallback paramDataCallback)
  {
    this.mSocket.setDataCallback(paramDataCallback);
  }

  void setSocket(AsyncSocket paramAsyncSocket)
  {
    this.mSocket = paramAsyncSocket;
    paramAsyncSocket = new LineEmitter();
    this.mSocket.setDataCallback(paramAsyncSocket);
    paramAsyncSocket.setLineCallback(this.mHeaderCallback);
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     fm.qingting.async.http.server.AsyncHttpServerRequestImpl
 * JD-Core Version:    0.6.2
 */