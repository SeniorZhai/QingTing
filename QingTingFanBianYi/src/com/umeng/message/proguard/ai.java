package com.umeng.message.proguard;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.zip.GZIPInputStream;
import org.apache.http.HttpEntity;
import org.apache.http.HttpRequestInterceptor;
import org.apache.http.HttpVersion;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.CookieStore;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.conn.ClientConnectionManager;
import org.apache.http.conn.params.ConnManagerParams;
import org.apache.http.conn.params.ConnPerRouteBean;
import org.apache.http.conn.scheme.Scheme;
import org.apache.http.conn.scheme.SchemeRegistry;
import org.apache.http.conn.ssl.SSLSocketFactory;
import org.apache.http.entity.HttpEntityWrapper;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import org.apache.http.params.HttpProtocolParams;
import org.apache.http.protocol.BasicHttpContext;
import org.apache.http.protocol.HttpContext;
import org.apache.http.protocol.SyncBasicHttpContext;

class ai
{
  private static final int a = 10;
  private static final int b = 10000;
  private static final int c = 3;
  private static final int d = 8192;
  private static final String e = "Accept-Encoding";
  private static final String f = "gzip";
  private static int g = 10;
  private static int h = 10000;
  private final DefaultHttpClient i;
  private final HttpContext j;
  private final Map<String, String> k;

  public ai()
  {
    BasicHttpParams localBasicHttpParams = new BasicHttpParams();
    ConnManagerParams.setTimeout(localBasicHttpParams, h);
    ConnManagerParams.setMaxConnectionsPerRoute(localBasicHttpParams, new ConnPerRouteBean(g));
    ConnManagerParams.setMaxTotalConnections(localBasicHttpParams, 10);
    HttpConnectionParams.setSoTimeout(localBasicHttpParams, h);
    HttpConnectionParams.setConnectionTimeout(localBasicHttpParams, h);
    HttpConnectionParams.setTcpNoDelay(localBasicHttpParams, true);
    HttpConnectionParams.setSocketBufferSize(localBasicHttpParams, 8192);
    HttpProtocolParams.setVersion(localBasicHttpParams, HttpVersion.HTTP_1_1);
    HttpProtocolParams.setUserAgent(localBasicHttpParams, String.format("Agoo-sdk-%s", new Object[] { Double.valueOf(2.0D) }));
    this.k = new HashMap();
    this.j = new SyncBasicHttpContext(new BasicHttpContext());
    this.i = new DefaultHttpClient(localBasicHttpParams);
    this.i.addRequestInterceptor(new aj(this));
    this.i.addResponseInterceptor(new ak(this));
    this.i.setHttpRequestRetryHandler(new ar(3));
  }

  protected String a(String paramString, aq paramaq)
  {
    String str = paramString;
    if (paramaq != null)
    {
      paramaq = paramaq.c();
      str = paramString + "?" + paramaq;
    }
    return str;
  }

  public void addHeader(String paramString1, String paramString2)
  {
    this.k.put(paramString1, paramString2);
  }

  public void addRequestInterceptor(HttpRequestInterceptor paramHttpRequestInterceptor)
  {
    this.i.addRequestInterceptor(paramHttpRequestInterceptor);
  }

  public DefaultHttpClient getHttpClient()
  {
    return this.i;
  }

  public HttpContext getHttpContext()
  {
    return this.j;
  }

  public void setBasicAuth(String paramString1, String paramString2)
  {
    setBasicAuth(paramString1, paramString2, AuthScope.ANY);
  }

  public void setBasicAuth(String paramString1, String paramString2, AuthScope paramAuthScope)
  {
    paramString1 = new UsernamePasswordCredentials(paramString1, paramString2);
    this.i.getCredentialsProvider().setCredentials(paramAuthScope, paramString1);
  }

  public void setCookieStore(CookieStore paramCookieStore)
  {
    this.j.setAttribute("http.cookie-store", paramCookieStore);
  }

  public void setSSLSocketFactory(SSLSocketFactory paramSSLSocketFactory)
  {
    this.i.getConnectionManager().getSchemeRegistry().register(new Scheme("https", paramSSLSocketFactory, 443));
  }

  public void setTimeout(int paramInt)
  {
    HttpParams localHttpParams = this.i.getParams();
    ConnManagerParams.setTimeout(localHttpParams, paramInt);
    HttpConnectionParams.setSoTimeout(localHttpParams, paramInt);
    HttpConnectionParams.setConnectionTimeout(localHttpParams, paramInt);
  }

  public void setUserAgent(String paramString)
  {
    HttpProtocolParams.setUserAgent(this.i.getParams(), paramString);
  }

  private static class a extends HttpEntityWrapper
  {
    public a(HttpEntity paramHttpEntity)
    {
      super();
    }

    public InputStream getContent()
      throws IOException
    {
      return new GZIPInputStream(this.wrappedEntity.getContent());
    }

    public long getContentLength()
    {
      return -1L;
    }
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     com.umeng.message.proguard.ai
 * JD-Core Version:    0.6.2
 */