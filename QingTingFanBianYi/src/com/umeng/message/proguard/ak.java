package com.umeng.message.proguard;

import org.apache.http.Header;
import org.apache.http.HeaderElement;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpResponseInterceptor;
import org.apache.http.protocol.HttpContext;

class ak
  implements HttpResponseInterceptor
{
  ak(ai paramai)
  {
  }

  public void process(HttpResponse paramHttpResponse, HttpContext paramHttpContext)
  {
    paramHttpContext = paramHttpResponse.getEntity();
    if (paramHttpContext == null);
    while (true)
    {
      return;
      paramHttpContext = paramHttpContext.getContentEncoding();
      if (paramHttpContext != null)
      {
        paramHttpContext = paramHttpContext.getElements();
        int j = paramHttpContext.length;
        int i = 0;
        while (i < j)
        {
          if (paramHttpContext[i].getName().equalsIgnoreCase("gzip"))
          {
            paramHttpResponse.setEntity(new ai.a(paramHttpResponse.getEntity()));
            return;
          }
          i += 1;
        }
      }
    }
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     com.umeng.message.proguard.ak
 * JD-Core Version:    0.6.2
 */