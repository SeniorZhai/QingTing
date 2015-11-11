package org.apache.commons.httpclient;

public class CircularRedirectException extends RedirectException
{
  public CircularRedirectException()
  {
  }

  public CircularRedirectException(String paramString)
  {
    super(paramString);
  }

  public CircularRedirectException(String paramString, Throwable paramThrowable)
  {
    super(paramString, paramThrowable);
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     org.apache.commons.httpclient.CircularRedirectException
 * JD-Core Version:    0.6.2
 */