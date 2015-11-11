package org.apache.commons.httpclient;

import java.io.IOException;
import org.apache.commons.httpclient.util.ExceptionUtil;

public class NoHttpResponseException extends IOException
{
  public NoHttpResponseException()
  {
  }

  public NoHttpResponseException(String paramString)
  {
    super(paramString);
  }

  public NoHttpResponseException(String paramString, Throwable paramThrowable)
  {
    super(paramString);
    ExceptionUtil.initCause(this, paramThrowable);
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     org.apache.commons.httpclient.NoHttpResponseException
 * JD-Core Version:    0.6.2
 */