package org.apache.commons.httpclient;

public class DefaultMethodRetryHandler
  implements MethodRetryHandler
{
  private boolean requestSentRetryEnabled = false;
  private int retryCount = 3;

  public int getRetryCount()
  {
    return this.retryCount;
  }

  public boolean isRequestSentRetryEnabled()
  {
    return this.requestSentRetryEnabled;
  }

  public boolean retryMethod(HttpMethod paramHttpMethod, HttpConnection paramHttpConnection, HttpRecoverableException paramHttpRecoverableException, int paramInt, boolean paramBoolean)
  {
    return ((!paramBoolean) || (this.requestSentRetryEnabled)) && (paramInt <= this.retryCount);
  }

  public void setRequestSentRetryEnabled(boolean paramBoolean)
  {
    this.requestSentRetryEnabled = paramBoolean;
  }

  public void setRetryCount(int paramInt)
  {
    this.retryCount = paramInt;
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     org.apache.commons.httpclient.DefaultMethodRetryHandler
 * JD-Core Version:    0.6.2
 */