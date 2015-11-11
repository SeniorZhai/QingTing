package org.apache.commons.httpclient.auth;

import org.apache.commons.httpclient.ProtocolException;

public class AuthenticationException extends ProtocolException
{
  public AuthenticationException()
  {
  }

  public AuthenticationException(String paramString)
  {
    super(paramString);
  }

  public AuthenticationException(String paramString, Throwable paramThrowable)
  {
    super(paramString, paramThrowable);
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     org.apache.commons.httpclient.auth.AuthenticationException
 * JD-Core Version:    0.6.2
 */