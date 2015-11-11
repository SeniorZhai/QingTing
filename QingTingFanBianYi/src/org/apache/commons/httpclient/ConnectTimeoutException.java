package org.apache.commons.httpclient;

import java.io.InterruptedIOException;
import org.apache.commons.httpclient.util.ExceptionUtil;

public class ConnectTimeoutException extends InterruptedIOException
{
  public ConnectTimeoutException()
  {
  }

  public ConnectTimeoutException(String paramString)
  {
    super(paramString);
  }

  public ConnectTimeoutException(String paramString, Throwable paramThrowable)
  {
    super(paramString);
    ExceptionUtil.initCause(this, paramThrowable);
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     org.apache.commons.httpclient.ConnectTimeoutException
 * JD-Core Version:    0.6.2
 */