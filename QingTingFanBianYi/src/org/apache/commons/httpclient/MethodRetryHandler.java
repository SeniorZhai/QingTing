package org.apache.commons.httpclient;

public abstract interface MethodRetryHandler
{
  public abstract boolean retryMethod(HttpMethod paramHttpMethod, HttpConnection paramHttpConnection, HttpRecoverableException paramHttpRecoverableException, int paramInt, boolean paramBoolean);
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     org.apache.commons.httpclient.MethodRetryHandler
 * JD-Core Version:    0.6.2
 */