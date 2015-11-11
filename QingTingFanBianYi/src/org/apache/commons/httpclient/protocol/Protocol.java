package org.apache.commons.httpclient.protocol;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import org.apache.commons.httpclient.util.LangUtils;

public class Protocol
{
  private static final Map PROTOCOLS = Collections.synchronizedMap(new HashMap());
  private int defaultPort;
  private String scheme;
  private boolean secure;
  private ProtocolSocketFactory socketFactory;

  public Protocol(String paramString, ProtocolSocketFactory paramProtocolSocketFactory, int paramInt)
  {
    if (paramString == null)
      throw new IllegalArgumentException("scheme is null");
    if (paramProtocolSocketFactory == null)
      throw new IllegalArgumentException("socketFactory is null");
    if (paramInt <= 0)
      throw new IllegalArgumentException("port is invalid: " + paramInt);
    this.scheme = paramString;
    this.socketFactory = paramProtocolSocketFactory;
    this.defaultPort = paramInt;
    this.secure = (paramProtocolSocketFactory instanceof SecureProtocolSocketFactory);
  }

  public Protocol(String paramString, SecureProtocolSocketFactory paramSecureProtocolSocketFactory, int paramInt)
  {
    this(paramString, paramSecureProtocolSocketFactory, paramInt);
  }

  public static Protocol getProtocol(String paramString)
    throws IllegalStateException
  {
    if (paramString == null)
      throw new IllegalArgumentException("id is null");
    Protocol localProtocol2 = (Protocol)PROTOCOLS.get(paramString);
    Protocol localProtocol1 = localProtocol2;
    if (localProtocol2 == null)
      localProtocol1 = lazyRegisterProtocol(paramString);
    return localProtocol1;
  }

  private static Protocol lazyRegisterProtocol(String paramString)
    throws IllegalStateException
  {
    if ("http".equals(paramString))
    {
      paramString = new Protocol("http", DefaultProtocolSocketFactory.getSocketFactory(), 80);
      registerProtocol("http", paramString);
      return paramString;
    }
    if ("https".equals(paramString))
    {
      paramString = new Protocol("https", SSLProtocolSocketFactory.getSocketFactory(), 443);
      registerProtocol("https", paramString);
      return paramString;
    }
    throw new IllegalStateException("unsupported protocol: '" + paramString + "'");
  }

  public static void registerProtocol(String paramString, Protocol paramProtocol)
  {
    if (paramString == null)
      throw new IllegalArgumentException("id is null");
    if (paramProtocol == null)
      throw new IllegalArgumentException("protocol is null");
    PROTOCOLS.put(paramString, paramProtocol);
  }

  public static void unregisterProtocol(String paramString)
  {
    if (paramString == null)
      throw new IllegalArgumentException("id is null");
    PROTOCOLS.remove(paramString);
  }

  public boolean equals(Object paramObject)
  {
    boolean bool2 = false;
    boolean bool1 = bool2;
    if ((paramObject instanceof Protocol))
    {
      paramObject = (Protocol)paramObject;
      bool1 = bool2;
      if (this.defaultPort == paramObject.getDefaultPort())
      {
        bool1 = bool2;
        if (this.scheme.equalsIgnoreCase(paramObject.getScheme()))
        {
          bool1 = bool2;
          if (this.secure == paramObject.isSecure())
          {
            bool1 = bool2;
            if (this.socketFactory.equals(paramObject.getSocketFactory()))
              bool1 = true;
          }
        }
      }
    }
    return bool1;
  }

  public int getDefaultPort()
  {
    return this.defaultPort;
  }

  public String getScheme()
  {
    return this.scheme;
  }

  public ProtocolSocketFactory getSocketFactory()
  {
    return this.socketFactory;
  }

  public int hashCode()
  {
    return LangUtils.hashCode(LangUtils.hashCode(LangUtils.hashCode(LangUtils.hashCode(17, this.defaultPort), this.scheme.toLowerCase()), this.secure), this.socketFactory);
  }

  public boolean isSecure()
  {
    return this.secure;
  }

  public int resolvePort(int paramInt)
  {
    int i = paramInt;
    if (paramInt <= 0)
      i = getDefaultPort();
    return i;
  }

  public String toString()
  {
    return this.scheme + ":" + this.defaultPort;
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     org.apache.commons.httpclient.protocol.Protocol
 * JD-Core Version:    0.6.2
 */