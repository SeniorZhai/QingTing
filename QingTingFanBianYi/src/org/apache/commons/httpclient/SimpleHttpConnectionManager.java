package org.apache.commons.httpclient;

import java.io.IOException;
import java.io.InputStream;
import org.apache.commons.httpclient.params.DefaultHttpParams;
import org.apache.commons.httpclient.params.HttpConnectionManagerParams;
import org.apache.commons.httpclient.params.HttpConnectionParams;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class SimpleHttpConnectionManager
  implements HttpConnectionManager
{
  private static final Log LOG;
  private static final String MISUSE_MESSAGE = "SimpleHttpConnectionManager being used incorrectly.  Be sure that HttpMethod.releaseConnection() is always called and that only one thread and/or method is using this connection manager at a time.";
  static Class class$org$apache$commons$httpclient$SimpleHttpConnectionManager;
  protected HttpConnection httpConnection;
  private long idleStartTime = 9223372036854775807L;
  private volatile boolean inUse = false;
  private HttpConnectionManagerParams params = new HttpConnectionManagerParams();

  static
  {
    Class localClass;
    if (class$org$apache$commons$httpclient$SimpleHttpConnectionManager == null)
    {
      localClass = class$("org.apache.commons.httpclient.SimpleHttpConnectionManager");
      class$org$apache$commons$httpclient$SimpleHttpConnectionManager = localClass;
    }
    while (true)
    {
      LOG = LogFactory.getLog(localClass);
      return;
      localClass = class$org$apache$commons$httpclient$SimpleHttpConnectionManager;
    }
  }

  static Class class$(String paramString)
  {
    try
    {
      paramString = Class.forName(paramString);
      return paramString;
    }
    catch (ClassNotFoundException paramString)
    {
    }
    throw new NoClassDefFoundError(paramString.getMessage());
  }

  static void finishLastResponse(HttpConnection paramHttpConnection)
  {
    InputStream localInputStream = paramHttpConnection.getLastResponseInputStream();
    if (localInputStream != null)
      paramHttpConnection.setLastResponseInputStream(null);
    try
    {
      localInputStream.close();
      return;
    }
    catch (IOException localIOException)
    {
      paramHttpConnection.close();
    }
  }

  public void closeIdleConnections(long paramLong)
  {
    long l = System.currentTimeMillis();
    if (this.idleStartTime <= l - paramLong)
      this.httpConnection.close();
  }

  public HttpConnection getConnection(HostConfiguration paramHostConfiguration)
  {
    return getConnection(paramHostConfiguration, 0L);
  }

  public HttpConnection getConnection(HostConfiguration paramHostConfiguration, long paramLong)
  {
    return getConnectionWithTimeout(paramHostConfiguration, paramLong);
  }

  public HttpConnection getConnectionWithTimeout(HostConfiguration paramHostConfiguration, long paramLong)
  {
    if (this.httpConnection == null)
    {
      this.httpConnection = new HttpConnection(paramHostConfiguration);
      this.httpConnection.setHttpConnectionManager(this);
      this.httpConnection.getParams().setDefaults(this.params);
    }
    while (true)
    {
      this.idleStartTime = 9223372036854775807L;
      if (this.inUse)
        LOG.warn("SimpleHttpConnectionManager being used incorrectly.  Be sure that HttpMethod.releaseConnection() is always called and that only one thread and/or method is using this connection manager at a time.");
      this.inUse = true;
      return this.httpConnection;
      if ((!paramHostConfiguration.hostEquals(this.httpConnection)) || (!paramHostConfiguration.proxyEquals(this.httpConnection)))
      {
        if (this.httpConnection.isOpen())
          this.httpConnection.close();
        this.httpConnection.setHost(paramHostConfiguration.getHost());
        this.httpConnection.setPort(paramHostConfiguration.getPort());
        this.httpConnection.setProtocol(paramHostConfiguration.getProtocol());
        this.httpConnection.setLocalAddress(paramHostConfiguration.getLocalAddress());
        this.httpConnection.setProxyHost(paramHostConfiguration.getProxyHost());
        this.httpConnection.setProxyPort(paramHostConfiguration.getProxyPort());
      }
      else
      {
        finishLastResponse(this.httpConnection);
      }
    }
  }

  public HttpConnectionManagerParams getParams()
  {
    return this.params;
  }

  public boolean isConnectionStaleCheckingEnabled()
  {
    return this.params.isStaleCheckingEnabled();
  }

  public void releaseConnection(HttpConnection paramHttpConnection)
  {
    if (paramHttpConnection != this.httpConnection)
      throw new IllegalStateException("Unexpected release of an unknown connection.");
    finishLastResponse(this.httpConnection);
    this.inUse = false;
    this.idleStartTime = System.currentTimeMillis();
  }

  public void setConnectionStaleCheckingEnabled(boolean paramBoolean)
  {
    this.params.setStaleCheckingEnabled(paramBoolean);
  }

  public void setParams(HttpConnectionManagerParams paramHttpConnectionManagerParams)
  {
    if (paramHttpConnectionManagerParams == null)
      throw new IllegalArgumentException("Parameters may not be null");
    this.params = paramHttpConnectionManagerParams;
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     org.apache.commons.httpclient.SimpleHttpConnectionManager
 * JD-Core Version:    0.6.2
 */