package org.apache.commons.httpclient;

class HttpMethodBase$1
  implements ResponseConsumedWatcher
{
  private final HttpMethodBase this$0;

  HttpMethodBase$1(HttpMethodBase paramHttpMethodBase)
  {
    this.this$0 = paramHttpMethodBase;
  }

  public void responseConsumed()
  {
    this.this$0.responseBodyConsumed();
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     org.apache.commons.httpclient.HttpMethodBase.1
 * JD-Core Version:    0.6.2
 */