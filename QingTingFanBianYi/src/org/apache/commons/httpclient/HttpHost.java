package org.apache.commons.httpclient;

import org.apache.commons.httpclient.protocol.Protocol;
import org.apache.commons.httpclient.util.LangUtils;

public class HttpHost
  implements Cloneable
{
  private String hostname = null;
  private int port = -1;
  private Protocol protocol = null;

  public HttpHost(String paramString)
  {
    this(paramString, -1, Protocol.getProtocol("http"));
  }

  public HttpHost(String paramString, int paramInt)
  {
    this(paramString, paramInt, Protocol.getProtocol("http"));
  }

  public HttpHost(String paramString, int paramInt, Protocol paramProtocol)
  {
    if (paramString == null)
      throw new IllegalArgumentException("Host name may not be null");
    if (paramProtocol == null)
      throw new IllegalArgumentException("Protocol may not be null");
    this.hostname = paramString;
    this.protocol = paramProtocol;
    if (paramInt >= 0)
    {
      this.port = paramInt;
      return;
    }
    this.port = this.protocol.getDefaultPort();
  }

  public HttpHost(HttpHost paramHttpHost)
  {
    this.hostname = paramHttpHost.hostname;
    this.port = paramHttpHost.port;
    this.protocol = paramHttpHost.protocol;
  }

  public HttpHost(URI paramURI)
    throws URIException
  {
    this(paramURI.getHost(), paramURI.getPort(), Protocol.getProtocol(paramURI.getScheme()));
  }

  public Object clone()
  {
    return new HttpHost(this);
  }

  public boolean equals(Object paramObject)
  {
    if ((paramObject instanceof HttpHost))
    {
      if (paramObject == this);
      do
      {
        return true;
        paramObject = (HttpHost)paramObject;
        if (!this.hostname.equalsIgnoreCase(paramObject.hostname))
          return false;
        if (this.port != paramObject.port)
          return false;
      }
      while (this.protocol.equals(paramObject.protocol));
      return false;
    }
    return false;
  }

  public String getHostName()
  {
    return this.hostname;
  }

  public int getPort()
  {
    return this.port;
  }

  public Protocol getProtocol()
  {
    return this.protocol;
  }

  public int hashCode()
  {
    return LangUtils.hashCode(LangUtils.hashCode(LangUtils.hashCode(17, this.hostname), this.port), this.protocol);
  }

  public String toString()
  {
    StringBuffer localStringBuffer = new StringBuffer(50);
    localStringBuffer.append(toURI());
    return localStringBuffer.toString();
  }

  public String toURI()
  {
    StringBuffer localStringBuffer = new StringBuffer(50);
    if (this.protocol != null)
    {
      localStringBuffer.append(this.protocol.getScheme());
      localStringBuffer.append("://");
    }
    localStringBuffer.append(this.hostname);
    if (this.port != this.protocol.getDefaultPort())
    {
      localStringBuffer.append(':');
      localStringBuffer.append(this.port);
    }
    return localStringBuffer.toString();
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     org.apache.commons.httpclient.HttpHost
 * JD-Core Version:    0.6.2
 */