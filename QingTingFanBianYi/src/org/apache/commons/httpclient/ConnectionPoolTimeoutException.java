package org.apache.commons.httpclient;

public class ConnectionPoolTimeoutException extends ConnectTimeoutException
{
  public ConnectionPoolTimeoutException()
  {
  }

  public ConnectionPoolTimeoutException(String paramString)
  {
    super(paramString);
  }

  public ConnectionPoolTimeoutException(String paramString, Throwable paramThrowable)
  {
    super(paramString, paramThrowable);
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     org.apache.commons.httpclient.ConnectionPoolTimeoutException
 * JD-Core Version:    0.6.2
 */