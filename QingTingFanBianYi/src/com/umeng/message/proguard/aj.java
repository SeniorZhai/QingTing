package com.umeng.message.proguard;

import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import org.apache.http.HttpRequest;
import org.apache.http.HttpRequestInterceptor;
import org.apache.http.protocol.HttpContext;

class aj
  implements HttpRequestInterceptor
{
  aj(ai paramai)
  {
  }

  public void process(HttpRequest paramHttpRequest, HttpContext paramHttpContext)
  {
    if (!paramHttpRequest.containsHeader("Accept-Encoding"))
      paramHttpRequest.addHeader("Accept-Encoding", "gzip");
    paramHttpContext = ai.a(this.a).keySet().iterator();
    while (paramHttpContext.hasNext())
    {
      String str = (String)paramHttpContext.next();
      paramHttpRequest.addHeader(str, (String)ai.a(this.a).get(str));
    }
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     com.umeng.message.proguard.aj
 * JD-Core Version:    0.6.2
 */