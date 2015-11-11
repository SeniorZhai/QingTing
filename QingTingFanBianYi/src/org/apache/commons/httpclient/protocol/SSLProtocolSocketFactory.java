package org.apache.commons.httpclient.protocol;

import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import javax.net.SocketFactory;
import javax.net.ssl.SSLSocketFactory;
import org.apache.commons.httpclient.ConnectTimeoutException;
import org.apache.commons.httpclient.params.HttpConnectionParams;

public class SSLProtocolSocketFactory
  implements SecureProtocolSocketFactory
{
  static Class class$org$apache$commons$httpclient$protocol$SSLProtocolSocketFactory;
  private static final SSLProtocolSocketFactory factory = new SSLProtocolSocketFactory();

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

  static SSLProtocolSocketFactory getSocketFactory()
  {
    return factory;
  }

  public Socket createSocket(String paramString, int paramInt)
    throws IOException, UnknownHostException
  {
    return SSLSocketFactory.getDefault().createSocket(paramString, paramInt);
  }

  public Socket createSocket(String paramString, int paramInt1, InetAddress paramInetAddress, int paramInt2)
    throws IOException, UnknownHostException
  {
    return SSLSocketFactory.getDefault().createSocket(paramString, paramInt1, paramInetAddress, paramInt2);
  }

  public Socket createSocket(String paramString, int paramInt1, InetAddress paramInetAddress, int paramInt2, HttpConnectionParams paramHttpConnectionParams)
    throws IOException, UnknownHostException, ConnectTimeoutException
  {
    if (paramHttpConnectionParams == null)
      throw new IllegalArgumentException("Parameters may not be null");
    int i = paramHttpConnectionParams.getConnectionTimeout();
    if (i == 0)
      paramHttpConnectionParams = createSocket(paramString, paramInt1, paramInetAddress, paramInt2);
    Socket localSocket;
    do
    {
      return paramHttpConnectionParams;
      localSocket = ReflectionSocketFactory.createSocket("javax.net.ssl.SSLSocketFactory", paramString, paramInt1, paramInetAddress, paramInt2, i);
      paramHttpConnectionParams = localSocket;
    }
    while (localSocket != null);
    return ControllerThreadSocketFactory.createSocket(this, paramString, paramInt1, paramInetAddress, paramInt2, i);
  }

  public Socket createSocket(Socket paramSocket, String paramString, int paramInt, boolean paramBoolean)
    throws IOException, UnknownHostException
  {
    return ((SSLSocketFactory)SSLSocketFactory.getDefault()).createSocket(paramSocket, paramString, paramInt, paramBoolean);
  }

  public boolean equals(Object paramObject)
  {
    if (paramObject != null)
    {
      Class localClass = paramObject.getClass();
      if (class$org$apache$commons$httpclient$protocol$SSLProtocolSocketFactory == null)
      {
        paramObject = class$("org.apache.commons.httpclient.protocol.SSLProtocolSocketFactory");
        class$org$apache$commons$httpclient$protocol$SSLProtocolSocketFactory = paramObject;
      }
      while (localClass.equals(paramObject))
      {
        return true;
        paramObject = class$org$apache$commons$httpclient$protocol$SSLProtocolSocketFactory;
      }
    }
    return false;
  }

  public int hashCode()
  {
    Class localClass;
    if (class$org$apache$commons$httpclient$protocol$SSLProtocolSocketFactory == null)
    {
      localClass = class$("org.apache.commons.httpclient.protocol.SSLProtocolSocketFactory");
      class$org$apache$commons$httpclient$protocol$SSLProtocolSocketFactory = localClass;
    }
    while (true)
    {
      return localClass.hashCode();
      localClass = class$org$apache$commons$httpclient$protocol$SSLProtocolSocketFactory;
    }
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     org.apache.commons.httpclient.protocol.SSLProtocolSocketFactory
 * JD-Core Version:    0.6.2
 */