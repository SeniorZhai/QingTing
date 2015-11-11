package org.apache.commons.httpclient.params;

public class HttpConnectionParams extends DefaultHttpParams
{
  public static final String CONNECTION_TIMEOUT = "http.connection.timeout";
  public static final String SO_LINGER = "http.socket.linger";
  public static final String SO_RCVBUF = "http.socket.receivebuffer";
  public static final String SO_SNDBUF = "http.socket.sendbuffer";
  public static final String SO_TIMEOUT = "http.socket.timeout";
  public static final String STALE_CONNECTION_CHECK = "http.connection.stalecheck";
  public static final String TCP_NODELAY = "http.tcp.nodelay";

  public int getConnectionTimeout()
  {
    return getIntParameter("http.connection.timeout", 0);
  }

  public int getLinger()
  {
    return getIntParameter("http.socket.linger", -1);
  }

  public int getReceiveBufferSize()
  {
    return getIntParameter("http.socket.receivebuffer", -1);
  }

  public int getSendBufferSize()
  {
    return getIntParameter("http.socket.sendbuffer", -1);
  }

  public int getSoTimeout()
  {
    return getIntParameter("http.socket.timeout", 0);
  }

  public boolean getTcpNoDelay()
  {
    return getBooleanParameter("http.tcp.nodelay", true);
  }

  public boolean isStaleCheckingEnabled()
  {
    return getBooleanParameter("http.connection.stalecheck", true);
  }

  public void setConnectionTimeout(int paramInt)
  {
    setIntParameter("http.connection.timeout", paramInt);
  }

  public void setLinger(int paramInt)
  {
    setIntParameter("http.socket.linger", paramInt);
  }

  public void setReceiveBufferSize(int paramInt)
  {
    setIntParameter("http.socket.receivebuffer", paramInt);
  }

  public void setSendBufferSize(int paramInt)
  {
    setIntParameter("http.socket.sendbuffer", paramInt);
  }

  public void setSoTimeout(int paramInt)
  {
    setIntParameter("http.socket.timeout", paramInt);
  }

  public void setStaleCheckingEnabled(boolean paramBoolean)
  {
    setBooleanParameter("http.connection.stalecheck", paramBoolean);
  }

  public void setTcpNoDelay(boolean paramBoolean)
  {
    setBooleanParameter("http.tcp.nodelay", paramBoolean);
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     org.apache.commons.httpclient.params.HttpConnectionParams
 * JD-Core Version:    0.6.2
 */