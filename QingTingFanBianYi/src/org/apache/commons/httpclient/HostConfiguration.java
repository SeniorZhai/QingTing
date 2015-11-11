package org.apache.commons.httpclient;

import java.net.InetAddress;
import org.apache.commons.httpclient.params.DefaultHttpParams;
import org.apache.commons.httpclient.params.HostParams;
import org.apache.commons.httpclient.protocol.Protocol;
import org.apache.commons.httpclient.util.LangUtils;

public class HostConfiguration
  implements Cloneable
{
  public static final HostConfiguration ANY_HOST_CONFIGURATION = new HostConfiguration();
  private HttpHost host = null;
  private InetAddress localAddress = null;
  private HostParams params = new HostParams();
  private ProxyHost proxyHost = null;

  public HostConfiguration()
  {
  }

  public HostConfiguration(HostConfiguration paramHostConfiguration)
  {
    while (true)
    {
      try
      {
        if (paramHostConfiguration.host != null)
        {
          this.host = ((HttpHost)paramHostConfiguration.host.clone());
          if (paramHostConfiguration.proxyHost != null)
          {
            this.proxyHost = ((ProxyHost)paramHostConfiguration.proxyHost.clone());
            this.localAddress = paramHostConfiguration.getLocalAddress();
            this.params = ((HostParams)paramHostConfiguration.getParams().clone());
          }
        }
        else
        {
          this.host = null;
          continue;
        }
      }
      catch (CloneNotSupportedException localCloneNotSupportedException)
      {
        throw new IllegalArgumentException("Host configuration could not be cloned");
      }
      finally
      {
      }
      this.proxyHost = null;
    }
  }

  public Object clone()
  {
    return new HostConfiguration(this);
  }

  public boolean equals(Object paramObject)
  {
    boolean bool1 = true;
    try
    {
      boolean bool2 = paramObject instanceof HostConfiguration;
      if (bool2)
        if (paramObject != this);
      while (true)
      {
        return bool1;
        paramObject = (HostConfiguration)paramObject;
        if ((LangUtils.equals(this.host, paramObject.host)) && (LangUtils.equals(this.proxyHost, paramObject.proxyHost)))
        {
          bool2 = LangUtils.equals(this.localAddress, paramObject.localAddress);
          if (bool2);
        }
        else
        {
          bool1 = false;
          continue;
          bool1 = false;
        }
      }
    }
    finally
    {
    }
    throw paramObject;
  }

  public String getHost()
  {
    try
    {
      if (this.host != null)
      {
        str = this.host.getHostName();
        return str;
      }
      String str = null;
    }
    finally
    {
    }
  }

  public String getHostURL()
  {
    try
    {
      if (this.host == null)
        throw new IllegalStateException("Host must be set to create a host URL");
    }
    finally
    {
    }
    String str = this.host.toURI();
    return str;
  }

  public InetAddress getLocalAddress()
  {
    try
    {
      InetAddress localInetAddress = this.localAddress;
      return localInetAddress;
    }
    finally
    {
      localObject = finally;
      throw localObject;
    }
  }

  public HostParams getParams()
  {
    return this.params;
  }

  public int getPort()
  {
    try
    {
      if (this.host != null)
      {
        i = this.host.getPort();
        return i;
      }
      int i = -1;
    }
    finally
    {
    }
  }

  public Protocol getProtocol()
  {
    try
    {
      if (this.host != null)
      {
        localProtocol = this.host.getProtocol();
        return localProtocol;
      }
      Protocol localProtocol = null;
    }
    finally
    {
    }
  }

  public String getProxyHost()
  {
    try
    {
      if (this.proxyHost != null)
      {
        str = this.proxyHost.getHostName();
        return str;
      }
      String str = null;
    }
    finally
    {
    }
  }

  public int getProxyPort()
  {
    try
    {
      if (this.proxyHost != null)
      {
        i = this.proxyHost.getPort();
        return i;
      }
      int i = -1;
    }
    finally
    {
    }
  }

  public String getVirtualHost()
  {
    try
    {
      String str = this.params.getVirtualHost();
      return str;
    }
    finally
    {
      localObject = finally;
      throw localObject;
    }
  }

  public int hashCode()
  {
    try
    {
      int i = LangUtils.hashCode(LangUtils.hashCode(LangUtils.hashCode(17, this.host), this.proxyHost), this.localAddress);
      return i;
    }
    finally
    {
      localObject = finally;
      throw localObject;
    }
  }

  public boolean hostEquals(HttpConnection paramHttpConnection)
  {
    boolean bool2 = false;
    if (paramHttpConnection == null)
      try
      {
        throw new IllegalArgumentException("Connection may not be null");
      }
      finally
      {
      }
    boolean bool1 = bool2;
    if (this.host != null)
    {
      bool1 = this.host.getHostName().equalsIgnoreCase(paramHttpConnection.getHost());
      if (bool1)
        break label57;
      bool1 = bool2;
    }
    while (true)
    {
      return bool1;
      label57: bool1 = bool2;
      if (this.host.getPort() == paramHttpConnection.getPort())
      {
        bool1 = bool2;
        if (this.host.getProtocol().equals(paramHttpConnection.getProtocol()))
          if (this.localAddress != null)
          {
            bool1 = bool2;
            if (this.localAddress.equals(paramHttpConnection.getLocalAddress()))
              break label132;
          }
          else
          {
            paramHttpConnection = paramHttpConnection.getLocalAddress();
            if (paramHttpConnection != null)
              bool1 = bool2;
            else
              label132: bool1 = true;
          }
      }
    }
  }

  public boolean isHostSet()
  {
    try
    {
      HttpHost localHttpHost = this.host;
      if (localHttpHost != null)
      {
        bool = true;
        return bool;
      }
      boolean bool = false;
    }
    finally
    {
    }
  }

  public boolean isProxySet()
  {
    try
    {
      ProxyHost localProxyHost = this.proxyHost;
      if (localProxyHost != null)
      {
        bool = true;
        return bool;
      }
      boolean bool = false;
    }
    finally
    {
    }
  }

  public boolean proxyEquals(HttpConnection paramHttpConnection)
  {
    boolean bool = true;
    if (paramHttpConnection == null)
      try
      {
        throw new IllegalArgumentException("Connection may not be null");
      }
      finally
      {
      }
    if (this.proxyHost != null)
      if (this.proxyHost.getHostName().equalsIgnoreCase(paramHttpConnection.getProxyHost()))
      {
        int i = this.proxyHost.getPort();
        int j = paramHttpConnection.getProxyPort();
        if (i != j);
      }
    while (true)
    {
      return bool;
      bool = false;
      continue;
      paramHttpConnection = paramHttpConnection.getProxyHost();
      if (paramHttpConnection != null)
        bool = false;
    }
  }

  public void setHost(String paramString)
  {
    try
    {
      Protocol localProtocol = Protocol.getProtocol("http");
      setHost(paramString, localProtocol.getDefaultPort(), localProtocol);
      return;
    }
    finally
    {
      paramString = finally;
    }
    throw paramString;
  }

  public void setHost(String paramString, int paramInt)
  {
    try
    {
      setHost(paramString, paramInt, Protocol.getProtocol("http"));
      return;
    }
    finally
    {
      paramString = finally;
    }
    throw paramString;
  }

  public void setHost(String paramString1, int paramInt, String paramString2)
  {
    try
    {
      this.host = new HttpHost(paramString1, paramInt, Protocol.getProtocol(paramString2));
      return;
    }
    finally
    {
      paramString1 = finally;
    }
    throw paramString1;
  }

  public void setHost(String paramString, int paramInt, Protocol paramProtocol)
  {
    if (paramString == null)
      try
      {
        throw new IllegalArgumentException("host must not be null");
      }
      finally
      {
      }
    if (paramProtocol == null)
      throw new IllegalArgumentException("protocol must not be null");
    this.host = new HttpHost(paramString, paramInt, paramProtocol);
  }

  public void setHost(String paramString1, String paramString2, int paramInt, Protocol paramProtocol)
  {
    try
    {
      setHost(paramString1, paramInt, paramProtocol);
      this.params.setVirtualHost(paramString2);
      return;
    }
    finally
    {
      paramString1 = finally;
    }
    throw paramString1;
  }

  public void setHost(HttpHost paramHttpHost)
  {
    try
    {
      this.host = paramHttpHost;
      return;
    }
    finally
    {
      paramHttpHost = finally;
    }
    throw paramHttpHost;
  }

  public void setHost(URI paramURI)
  {
    try
    {
      setHost(paramURI.getHost(), paramURI.getPort(), paramURI.getScheme());
      return;
    }
    catch (URIException paramURI)
    {
      throw new IllegalArgumentException(paramURI.toString());
    }
    finally
    {
    }
    throw paramURI;
  }

  public void setLocalAddress(InetAddress paramInetAddress)
  {
    try
    {
      this.localAddress = paramInetAddress;
      return;
    }
    finally
    {
      paramInetAddress = finally;
    }
    throw paramInetAddress;
  }

  public void setParams(HostParams paramHostParams)
  {
    if (paramHostParams == null)
      throw new IllegalArgumentException("Parameters may not be null");
    this.params = paramHostParams;
  }

  public void setProxy(String paramString, int paramInt)
  {
    try
    {
      this.proxyHost = new ProxyHost(paramString, paramInt);
      return;
    }
    finally
    {
      paramString = finally;
    }
    throw paramString;
  }

  public void setProxyHost(ProxyHost paramProxyHost)
  {
    try
    {
      this.proxyHost = paramProxyHost;
      return;
    }
    finally
    {
      paramProxyHost = finally;
    }
    throw paramProxyHost;
  }

  public String toString()
  {
    int i = 0;
    try
    {
      Object localObject1 = new StringBuffer(50);
      ((StringBuffer)localObject1).append("HostConfiguration[");
      if (this.host != null)
      {
        i = 1;
        ((StringBuffer)localObject1).append("host=").append(this.host);
      }
      int j = i;
      if (this.proxyHost != null)
      {
        if (i != 0)
        {
          ((StringBuffer)localObject1).append(", ");
          ((StringBuffer)localObject1).append("proxyHost=").append(this.proxyHost);
          j = i;
        }
      }
      else if (this.localAddress != null)
      {
        if (j == 0)
          break label158;
        ((StringBuffer)localObject1).append(", ");
        label98: ((StringBuffer)localObject1).append("localAddress=").append(this.localAddress);
        if (j == 0)
          break label163;
        ((StringBuffer)localObject1).append(", ");
      }
      label158: label163: 
      while (true)
      {
        ((StringBuffer)localObject1).append("params=").append(this.params);
        ((StringBuffer)localObject1).append("]");
        localObject1 = ((StringBuffer)localObject1).toString();
        return localObject1;
        i = 1;
        break;
        j = 1;
        break label98;
      }
    }
    finally
    {
    }
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     org.apache.commons.httpclient.HostConfiguration
 * JD-Core Version:    0.6.2
 */