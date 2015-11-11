package org.apache.commons.httpclient;

import java.io.IOException;
import java.security.Provider;
import java.security.Security;
import org.apache.commons.httpclient.params.DefaultHttpParams;
import org.apache.commons.httpclient.params.HttpClientParams;
import org.apache.commons.httpclient.params.HttpConnectionParams;
import org.apache.commons.httpclient.params.HttpMethodParams;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class HttpClient
{
  private static final Log LOG;
  static Class class$org$apache$commons$httpclient$HttpClient;
  private HostConfiguration hostConfiguration = new HostConfiguration();
  private HttpConnectionManager httpConnectionManager;
  private HttpClientParams params = null;
  private HttpState state = new HttpState();

  static
  {
    Object localObject1;
    if (class$org$apache$commons$httpclient$HttpClient == null)
    {
      localObject1 = class$("org.apache.commons.httpclient.HttpClient");
      class$org$apache$commons$httpclient$HttpClient = (Class)localObject1;
    }
    while (true)
    {
      LOG = LogFactory.getLog((Class)localObject1);
      if (LOG.isDebugEnabled());
      try
      {
        LOG.debug("Java version: " + System.getProperty("java.version"));
        LOG.debug("Java vendor: " + System.getProperty("java.vendor"));
        LOG.debug("Java class path: " + System.getProperty("java.class.path"));
        LOG.debug("Operating system name: " + System.getProperty("os.name"));
        LOG.debug("Operating system architecture: " + System.getProperty("os.arch"));
        LOG.debug("Operating system version: " + System.getProperty("os.version"));
        localObject1 = Security.getProviders();
        int i = 0;
        while (true)
        {
          int j = localObject1.length;
          if (i >= j)
          {
            return;
            localObject1 = class$org$apache$commons$httpclient$HttpClient;
            break;
          }
          Object localObject2 = localObject1[i];
          LOG.debug(localObject2.getName() + " " + localObject2.getVersion() + ": " + localObject2.getInfo());
          i += 1;
        }
      }
      catch (SecurityException localSecurityException)
      {
      }
    }
  }

  public HttpClient()
  {
    this(new HttpClientParams());
  }

  public HttpClient(HttpConnectionManager paramHttpConnectionManager)
  {
    this(new HttpClientParams(), paramHttpConnectionManager);
  }

  public HttpClient(HttpClientParams paramHttpClientParams)
  {
    if (paramHttpClientParams == null)
      throw new IllegalArgumentException("Params may not be null");
    this.params = paramHttpClientParams;
    this.httpConnectionManager = null;
    paramHttpClientParams = paramHttpClientParams.getConnectionManagerClass();
    if (paramHttpClientParams != null);
    try
    {
      this.httpConnectionManager = ((HttpConnectionManager)paramHttpClientParams.newInstance());
      if (this.httpConnectionManager == null)
        this.httpConnectionManager = new SimpleHttpConnectionManager();
      if (this.httpConnectionManager != null)
        this.httpConnectionManager.getParams().setDefaults(this.params);
      return;
    }
    catch (Exception paramHttpClientParams)
    {
      while (true)
        LOG.warn("Error instantiating connection manager class, defaulting to SimpleHttpConnectionManager", paramHttpClientParams);
    }
  }

  public HttpClient(HttpClientParams paramHttpClientParams, HttpConnectionManager paramHttpConnectionManager)
  {
    if (paramHttpConnectionManager == null)
      throw new IllegalArgumentException("httpConnectionManager cannot be null");
    if (paramHttpClientParams == null)
      throw new IllegalArgumentException("Params may not be null");
    this.params = paramHttpClientParams;
    this.httpConnectionManager = paramHttpConnectionManager;
    if (this.httpConnectionManager != null)
      this.httpConnectionManager.getParams().setDefaults(this.params);
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

  public int executeMethod(HostConfiguration paramHostConfiguration, HttpMethod paramHttpMethod)
    throws IOException, HttpException
  {
    LOG.trace("enter HttpClient.executeMethod(HostConfiguration,HttpMethod)");
    return executeMethod(paramHostConfiguration, paramHttpMethod, null);
  }

  public int executeMethod(HostConfiguration paramHostConfiguration, HttpMethod paramHttpMethod, HttpState paramHttpState)
    throws IOException, HttpException
  {
    LOG.trace("enter HttpClient.executeMethod(HostConfiguration,HttpMethod,HttpState)");
    if (paramHttpMethod == null)
      throw new IllegalArgumentException("HttpMethod parameter may not be null");
    Object localObject2 = getHostConfiguration();
    Object localObject1 = paramHostConfiguration;
    if (paramHostConfiguration == null)
      localObject1 = localObject2;
    Object localObject3 = paramHttpMethod.getURI();
    if (localObject1 != localObject2)
    {
      paramHostConfiguration = (HostConfiguration)localObject1;
      if (!((URI)localObject3).isAbsoluteURI());
    }
    else
    {
      paramHostConfiguration = new HostConfiguration((HostConfiguration)localObject1);
      if (((URI)localObject3).isAbsoluteURI())
        paramHostConfiguration.setHost((URI)localObject3);
    }
    localObject2 = getHttpConnectionManager();
    localObject3 = this.params;
    localObject1 = paramHttpState;
    if (paramHttpState == null)
      localObject1 = getState();
    new HttpMethodDirector((HttpConnectionManager)localObject2, paramHostConfiguration, (HttpClientParams)localObject3, (HttpState)localObject1).executeMethod(paramHttpMethod);
    return paramHttpMethod.getStatusCode();
  }

  public int executeMethod(HttpMethod paramHttpMethod)
    throws IOException, HttpException
  {
    LOG.trace("enter HttpClient.executeMethod(HttpMethod)");
    return executeMethod(null, paramHttpMethod, null);
  }

  public String getHost()
  {
    return this.hostConfiguration.getHost();
  }

  public HostConfiguration getHostConfiguration()
  {
    try
    {
      HostConfiguration localHostConfiguration = this.hostConfiguration;
      return localHostConfiguration;
    }
    finally
    {
      localObject = finally;
      throw localObject;
    }
  }

  public HttpConnectionManager getHttpConnectionManager()
  {
    try
    {
      HttpConnectionManager localHttpConnectionManager = this.httpConnectionManager;
      return localHttpConnectionManager;
    }
    finally
    {
      localObject = finally;
      throw localObject;
    }
  }

  public HttpClientParams getParams()
  {
    return this.params;
  }

  public int getPort()
  {
    return this.hostConfiguration.getPort();
  }

  public HttpState getState()
  {
    try
    {
      HttpState localHttpState = this.state;
      return localHttpState;
    }
    finally
    {
      localObject = finally;
      throw localObject;
    }
  }

  public boolean isStrictMode()
  {
    return false;
  }

  public void setConnectionTimeout(int paramInt)
  {
    try
    {
      this.httpConnectionManager.getParams().setConnectionTimeout(paramInt);
      return;
    }
    finally
    {
      localObject = finally;
      throw localObject;
    }
  }

  public void setHostConfiguration(HostConfiguration paramHostConfiguration)
  {
    try
    {
      this.hostConfiguration = paramHostConfiguration;
      return;
    }
    finally
    {
      paramHostConfiguration = finally;
    }
    throw paramHostConfiguration;
  }

  public void setHttpConnectionFactoryTimeout(long paramLong)
  {
    try
    {
      this.params.setConnectionManagerTimeout(paramLong);
      return;
    }
    finally
    {
      localObject = finally;
      throw localObject;
    }
  }

  public void setHttpConnectionManager(HttpConnectionManager paramHttpConnectionManager)
  {
    try
    {
      this.httpConnectionManager = paramHttpConnectionManager;
      if (this.httpConnectionManager != null)
        this.httpConnectionManager.getParams().setDefaults(this.params);
      return;
    }
    finally
    {
      paramHttpConnectionManager = finally;
    }
    throw paramHttpConnectionManager;
  }

  public void setParams(HttpClientParams paramHttpClientParams)
  {
    if (paramHttpClientParams == null)
      throw new IllegalArgumentException("Parameters may not be null");
    this.params = paramHttpClientParams;
  }

  public void setState(HttpState paramHttpState)
  {
    try
    {
      this.state = paramHttpState;
      return;
    }
    finally
    {
      paramHttpState = finally;
    }
    throw paramHttpState;
  }

  public void setStrictMode(boolean paramBoolean)
  {
    if (paramBoolean);
    try
    {
      this.params.makeStrict();
      while (true)
      {
        return;
        this.params.makeLenient();
      }
    }
    finally
    {
    }
  }

  public void setTimeout(int paramInt)
  {
    try
    {
      this.params.setSoTimeout(paramInt);
      return;
    }
    finally
    {
      localObject = finally;
      throw localObject;
    }
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     org.apache.commons.httpclient.HttpClient
 * JD-Core Version:    0.6.2
 */