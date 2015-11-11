package com.tendcloud.tenddata;

import org.apache.http.Header;
import org.apache.http.HttpRequest;
import org.apache.http.HttpRequestInterceptor;
import org.apache.http.protocol.HttpContext;

class t
  implements HttpRequestInterceptor
{
  t(ad paramad, String paramString)
  {
  }

  public void process(HttpRequest paramHttpRequest, HttpContext paramHttpContext)
  {
    paramHttpContext = paramHttpRequest.getAllHeaders();
    int j = paramHttpContext.length;
    int i = 0;
    while (i < j)
    {
      Header localHeader = paramHttpContext[i];
      if (!"Content-Length".equalsIgnoreCase(localHeader.getName()))
        paramHttpRequest.removeHeader(localHeader);
      i += 1;
    }
    paramHttpRequest.setHeader("Host", this.a);
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     com.tendcloud.tenddata.t
 * JD-Core Version:    0.6.2
 */