package com.taobao.newxp.net;

import com.taobao.munion.base.volley.a.e;
import java.io.IOException;
import org.apache.http.HttpResponse;
import org.apache.http.client.CookieStore;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.protocol.BasicHttpContext;
import org.apache.http.protocol.HttpContext;

public class f extends e
{
  protected HttpContext b;

  public f(HttpClient paramHttpClient)
  {
    super(paramHttpClient);
  }

  protected HttpResponse a(HttpUriRequest paramHttpUriRequest)
    throws IOException
  {
    return this.a.execute(paramHttpUriRequest, this.b);
  }

  public void a(CookieStore paramCookieStore)
  {
    this.b = new BasicHttpContext();
    this.b.setAttribute("http.cookie-store", paramCookieStore);
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     com.taobao.newxp.net.f
 * JD-Core Version:    0.6.2
 */