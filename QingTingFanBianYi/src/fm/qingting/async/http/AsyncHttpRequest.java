package fm.qingting.async.http;

import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import fm.qingting.async.AsyncSSLException;
import fm.qingting.async.http.libcore.RawHeaders;
import fm.qingting.async.http.libcore.RequestHeaders;
import java.net.URI;
import java.util.List;
import java.util.Map;
import org.apache.http.Header;
import org.apache.http.HeaderIterator;
import org.apache.http.HttpRequest;
import org.apache.http.ProtocolVersion;
import org.apache.http.RequestLine;
import org.apache.http.message.BasicHeader;
import org.apache.http.params.HttpParams;

public class AsyncHttpRequest
{
  public static final int DEFAULT_TIMEOUT = 30000;
  String LOGTAG;
  long executionTime;
  int logLevel;
  private AsyncHttpRequestBody mBody;
  private boolean mFollowRedirect;

  @Deprecated
  private Handler mHandler;
  private RequestHeaders mHeaders;
  private String mMethod;
  private RawHeaders mRawHeaders = new RawHeaders();
  int mTimeout;
  String proxyHost;
  int proxyPort;

  static
  {
    if (!AsyncHttpRequest.class.desiredAssertionStatus());
    for (boolean bool = true; ; bool = false)
    {
      $assertionsDisabled = bool;
      return;
    }
  }

  public AsyncHttpRequest(URI paramURI, String paramString)
  {
    this(paramURI, paramString, null);
  }

  public AsyncHttpRequest(URI paramURI, String paramString, RawHeaders paramRawHeaders)
  {
    if (Looper.myLooper() == null);
    for (Handler localHandler = null; ; localHandler = new Handler())
    {
      this.mHandler = localHandler;
      this.mFollowRedirect = true;
      this.mTimeout = 30000;
      this.proxyPort = -1;
      if (($assertionsDisabled) || (paramURI != null))
        break;
      throw new AssertionError();
    }
    this.mMethod = paramString;
    paramString = paramRawHeaders;
    if (paramRawHeaders == null)
      paramString = new RawHeaders();
    this.mRawHeaders = paramString;
    this.mHeaders = new RequestHeaders(paramURI, this.mRawHeaders);
    this.mRawHeaders.setStatusLine(getRequestLine().toString());
    this.mHeaders.setHost(paramURI.getHost());
    this.mHeaders.setUserAgent(getDefaultUserAgent());
    this.mHeaders.setAcceptEncoding("gzip, deflate");
    this.mHeaders.setConnection("keep-alive");
    this.mHeaders.getHeaders().set("Accept", "*/*");
  }

  public static AsyncHttpRequest create(HttpRequest paramHttpRequest)
  {
    AsyncHttpRequest localAsyncHttpRequest = new AsyncHttpRequest(URI.create(paramHttpRequest.getRequestLine().getUri()), paramHttpRequest.getRequestLine().getMethod());
    paramHttpRequest = paramHttpRequest.getAllHeaders();
    int j = paramHttpRequest.length;
    int i = 0;
    while (i < j)
    {
      Object localObject = paramHttpRequest[i];
      localAsyncHttpRequest.getHeaders().getHeaders().add(localObject.getName(), localObject.getValue());
      i += 1;
    }
    return localAsyncHttpRequest;
  }

  private String getLogMessage(String paramString)
  {
    long l = 0L;
    if (this.executionTime != 0L)
      l = System.currentTimeMillis() - this.executionTime;
    return String.format("(%d ms) %s: %s", new Object[] { Long.valueOf(l), getUri(), paramString });
  }

  public AsyncHttpRequest addHeader(String paramString1, String paramString2)
  {
    getHeaders().getHeaders().add(paramString1, paramString2);
    return this;
  }

  public HttpRequest asHttpRequest()
  {
    return new HttpRequestWrapper(this);
  }

  public void disableProxy()
  {
    this.proxyHost = null;
    this.proxyPort = -1;
  }

  public void enableProxy(String paramString, int paramInt)
  {
    this.proxyHost = paramString;
    this.proxyPort = paramInt;
  }

  public AsyncHttpRequestBody getBody()
  {
    return this.mBody;
  }

  protected final String getDefaultUserAgent()
  {
    String str = System.getProperty("http.agent");
    if (str != null)
      return str;
    return "Java" + System.getProperty("java.version");
  }

  public boolean getFollowRedirect()
  {
    return this.mFollowRedirect;
  }

  @Deprecated
  public Handler getHandler()
  {
    return this.mHandler;
  }

  public RequestHeaders getHeaders()
  {
    return this.mHeaders;
  }

  public String getMethod()
  {
    return this.mMethod;
  }

  public String getProxyHost()
  {
    return this.proxyHost;
  }

  public int getProxyPort()
  {
    return this.proxyPort;
  }

  public RequestLine getProxyRequestLine()
  {
    return new RequestLine()
    {
      public String getMethod()
      {
        return AsyncHttpRequest.this.mMethod;
      }

      public ProtocolVersion getProtocolVersion()
      {
        return new ProtocolVersion("HTTP", 1, 1);
      }

      public String getUri()
      {
        return AsyncHttpRequest.this.getUri().toString();
      }

      public String toString()
      {
        return String.format("%s %s HTTP/1.1", new Object[] { AsyncHttpRequest.this.mMethod, AsyncHttpRequest.this.getUri() });
      }
    };
  }

  public RequestLine getRequestLine()
  {
    return new RequestLine()
    {
      public String getMethod()
      {
        return AsyncHttpRequest.this.mMethod;
      }

      public ProtocolVersion getProtocolVersion()
      {
        return new ProtocolVersion("HTTP", 1, 1);
      }

      public String getUri()
      {
        return AsyncHttpRequest.this.getUri().toString();
      }

      public String toString()
      {
        Object localObject2 = AsyncHttpRequest.this.getUri().getPath();
        Object localObject1 = localObject2;
        if (((String)localObject2).length() == 0)
          localObject1 = "/";
        String str = AsyncHttpRequest.this.getUri().getRawQuery();
        localObject2 = localObject1;
        if (str != null)
        {
          localObject2 = localObject1;
          if (str.length() != 0)
            localObject2 = (String)localObject1 + "?" + str;
        }
        return String.format("%s %s HTTP/1.1", new Object[] { AsyncHttpRequest.this.mMethod, localObject2 });
      }
    };
  }

  public String getRequestString()
  {
    return this.mRawHeaders.toHeaderString();
  }

  public int getTimeout()
  {
    return this.mTimeout;
  }

  public URI getUri()
  {
    return this.mHeaders.getUri();
  }

  public void logd(String paramString)
  {
    if (this.LOGTAG == null);
    while (this.logLevel > 3)
      return;
    Log.d(this.LOGTAG, getLogMessage(paramString));
  }

  public void logd(String paramString, Exception paramException)
  {
    if (this.LOGTAG == null);
    while (this.logLevel > 3)
      return;
    Log.d(this.LOGTAG, getLogMessage(paramString));
    Log.d(this.LOGTAG, paramException.getMessage(), paramException);
  }

  public void loge(String paramString)
  {
    if (this.LOGTAG == null);
    while (this.logLevel > 6)
      return;
    Log.e(this.LOGTAG, getLogMessage(paramString));
  }

  public void loge(String paramString, Exception paramException)
  {
    if (this.LOGTAG == null);
    while (this.logLevel > 6)
      return;
    Log.e(this.LOGTAG, getLogMessage(paramString));
    Log.e(this.LOGTAG, paramException.getMessage(), paramException);
  }

  public void logi(String paramString)
  {
    if (this.LOGTAG == null);
    while (this.logLevel > 4)
      return;
    Log.i(this.LOGTAG, getLogMessage(paramString));
  }

  public void logv(String paramString)
  {
    if (this.LOGTAG == null);
    while (this.logLevel > 2)
      return;
    Log.v(this.LOGTAG, getLogMessage(paramString));
  }

  public void logw(String paramString)
  {
    if (this.LOGTAG == null);
    while (this.logLevel > 5)
      return;
    Log.w(this.LOGTAG, getLogMessage(paramString));
  }

  public void onHandshakeException(AsyncSSLException paramAsyncSSLException)
  {
  }

  public void setBody(AsyncHttpRequestBody paramAsyncHttpRequestBody)
  {
    this.mBody = paramAsyncHttpRequestBody;
  }

  public AsyncHttpRequest setFollowRedirect(boolean paramBoolean)
  {
    this.mFollowRedirect = paramBoolean;
    return this;
  }

  @Deprecated
  public AsyncHttpRequest setHandler(Handler paramHandler)
  {
    this.mHandler = paramHandler;
    return this;
  }

  public AsyncHttpRequest setHeader(String paramString1, String paramString2)
  {
    getHeaders().getHeaders().set(paramString1, paramString2);
    return this;
  }

  public void setLogging(String paramString, int paramInt)
  {
    this.LOGTAG = paramString;
    this.logLevel = paramInt;
  }

  public AsyncHttpRequest setMethod(String paramString)
  {
    if (getClass() != AsyncHttpRequest.class)
      throw new UnsupportedOperationException("can't change method on a subclass of AsyncHttpRequest");
    this.mMethod = paramString;
    this.mRawHeaders.setStatusLine(getRequestLine().toString());
    return this;
  }

  public AsyncHttpRequest setTimeout(int paramInt)
  {
    this.mTimeout = paramInt;
    return this;
  }

  private static class HttpRequestWrapper
    implements HttpRequest
  {
    HttpParams params;
    AsyncHttpRequest request;

    static
    {
      if (!AsyncHttpRequest.class.desiredAssertionStatus());
      for (boolean bool = true; ; bool = false)
      {
        $assertionsDisabled = bool;
        return;
      }
    }

    public HttpRequestWrapper(AsyncHttpRequest paramAsyncHttpRequest)
    {
      this.request = paramAsyncHttpRequest;
    }

    public void addHeader(String paramString1, String paramString2)
    {
      this.request.getHeaders().getHeaders().add(paramString1, paramString2);
    }

    public void addHeader(Header paramHeader)
    {
      this.request.getHeaders().getHeaders().add(paramHeader.getName(), paramHeader.getValue());
    }

    public boolean containsHeader(String paramString)
    {
      return this.request.getHeaders().getHeaders().get(paramString) != null;
    }

    public Header[] getAllHeaders()
    {
      Header[] arrayOfHeader = new Header[this.request.getHeaders().getHeaders().length()];
      int i = 0;
      while (i < arrayOfHeader.length)
      {
        arrayOfHeader[i] = new BasicHeader(this.request.getHeaders().getHeaders().getFieldName(i), this.request.getHeaders().getHeaders().getValue(i));
        i += 1;
      }
      return arrayOfHeader;
    }

    public Header getFirstHeader(String paramString)
    {
      String str = this.request.getHeaders().getHeaders().get(paramString);
      if (str == null)
        return null;
      return new BasicHeader(paramString, str);
    }

    public Header[] getHeaders(String paramString)
    {
      List localList = (List)this.request.getHeaders().getHeaders().toMultimap().get(paramString);
      if (localList == null)
        return new Header[0];
      Header[] arrayOfHeader = new Header[localList.size()];
      int i = 0;
      while (i < arrayOfHeader.length)
      {
        arrayOfHeader[i] = new BasicHeader(paramString, (String)localList.get(i));
        i += 1;
      }
      return arrayOfHeader;
    }

    public Header getLastHeader(String paramString)
    {
      paramString = getHeaders(paramString);
      if (paramString.length == 0)
        return null;
      return paramString[(paramString.length - 1)];
    }

    public HttpParams getParams()
    {
      return this.params;
    }

    public ProtocolVersion getProtocolVersion()
    {
      return new ProtocolVersion("HTTP", 1, 1);
    }

    public RequestLine getRequestLine()
    {
      return this.request.getRequestLine();
    }

    public HeaderIterator headerIterator()
    {
      if (!$assertionsDisabled)
        throw new AssertionError();
      return null;
    }

    public HeaderIterator headerIterator(String paramString)
    {
      if (!$assertionsDisabled)
        throw new AssertionError();
      return null;
    }

    public void removeHeader(Header paramHeader)
    {
      this.request.getHeaders().getHeaders().removeAll(paramHeader.getName());
    }

    public void removeHeaders(String paramString)
    {
      this.request.getHeaders().getHeaders().removeAll(paramString);
    }

    public void setHeader(String paramString1, String paramString2)
    {
      this.request.getHeaders().getHeaders().set(paramString1, paramString2);
    }

    public void setHeader(Header paramHeader)
    {
      setHeader(paramHeader.getName(), paramHeader.getValue());
    }

    public void setHeaders(Header[] paramArrayOfHeader)
    {
      int j = paramArrayOfHeader.length;
      int i = 0;
      while (i < j)
      {
        setHeader(paramArrayOfHeader[i]);
        i += 1;
      }
    }

    public void setParams(HttpParams paramHttpParams)
    {
      this.params = paramHttpParams;
    }
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     fm.qingting.async.http.AsyncHttpRequest
 * JD-Core Version:    0.6.2
 */