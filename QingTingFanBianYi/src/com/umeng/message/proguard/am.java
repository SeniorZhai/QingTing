package com.umeng.message.proguard;

import android.content.Context;
import java.io.IOException;
import java.net.ConnectException;
import java.net.URI;
import org.apache.http.HttpHost;
import org.apache.http.HttpResponse;
import org.apache.http.StatusLine;
import org.apache.http.client.HttpRequestRetryHandler;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.impl.client.AbstractHttpClient;
import org.apache.http.params.HttpParams;
import org.apache.http.protocol.HttpContext;

class am
  implements Runnable
{
  private static final String a = "AsyncHttp.request";
  private final AbstractHttpClient b;
  private final HttpContext c;
  private final HttpUriRequest d;
  private final an e;
  private volatile boolean f;
  private int g;
  private Context h;

  public am(Context paramContext, AbstractHttpClient paramAbstractHttpClient, HttpContext paramHttpContext, HttpHost paramHttpHost, HttpUriRequest paramHttpUriRequest, an paraman)
  {
    this.b = paramAbstractHttpClient;
    this.c = paramHttpContext;
    this.h = paramContext;
    this.d = paramHttpUriRequest;
    this.e = paraman;
    if (paramHttpHost != null)
      paramAbstractHttpClient.getParams().setParameter("http.route.default-proxy", paramHttpHost);
    if ((paraman instanceof ap))
      this.f = true;
  }

  private void a()
    throws IOException
  {
    if (!Thread.currentThread().isInterrupted())
    {
      HttpResponse localHttpResponse = this.b.execute(this.d, this.c);
      Q.b("AsyncHttp.request", "http request:[" + this.d.getURI().toString() + "]===response[:" + localHttpResponse.getStatusLine().getStatusCode() + "]");
      if (this.e != null)
        this.e.a(localHttpResponse);
    }
  }

  private void b()
    throws ConnectException
  {
    boolean bool = true;
    Object localObject1 = null;
    Object localObject2 = this.b.getHttpRequestRetryHandler();
    IOException localIOException2;
    while (bool)
      try
      {
        a();
        return;
      }
      catch (IOException localIOException1)
      {
        Q.e("AsyncHttp.request", "http request makeRequestWithRetries", localIOException1);
        i = this.g + 1;
        this.g = i;
        bool = ((HttpRequestRetryHandler)localObject2).retryRequest(localIOException1, i, this.c);
      }
      catch (NullPointerException localNullPointerException)
      {
        Q.e("AsyncHttp.request", "", localNullPointerException);
        localIOException2 = new IOException("NPE in HttpClient" + localNullPointerException.getMessage());
        int i = this.g + 1;
        this.g = i;
        bool = ((HttpRequestRetryHandler)localObject2).retryRequest(localIOException2, i, this.c);
      }
    localObject2 = new ConnectException();
    ((ConnectException)localObject2).initCause(localIOException2);
    throw ((Throwable)localObject2);
  }

  public void run()
  {
    try
    {
      if (this.e != null)
        this.e.a();
      if (!ag.a(this.h))
        this.e.a(new RuntimeException("http request network connection error[" + this.d.getURI().toString() + "]"), (byte[])null);
      while (this.e != null)
      {
        this.e.b();
        return;
        b();
      }
    }
    catch (IOException localIOException)
    {
      Q.e("AsyncHttp.request", "http request io", localIOException);
      if (this.e != null)
      {
        this.e.b();
        if (this.f)
        {
          this.e.a(localIOException, (byte[])null);
          return;
        }
        this.e.a(localIOException, localIOException.getMessage());
      }
    }
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     com.umeng.message.proguard.am
 * JD-Core Version:    0.6.2
 */