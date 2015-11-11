package org.apache.commons.httpclient;

import java.io.IOException;

public abstract interface HttpMethodRetryHandler
{
  public abstract boolean retryMethod(HttpMethod paramHttpMethod, IOException paramIOException, int paramInt);
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     org.apache.commons.httpclient.HttpMethodRetryHandler
 * JD-Core Version:    0.6.2
 */