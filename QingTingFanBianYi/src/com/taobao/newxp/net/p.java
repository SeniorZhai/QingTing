package com.taobao.newxp.net;

import com.taobao.munion.base.Log;
import java.io.IOException;
import java.net.URI;
import java.net.URL;
import org.apache.http.Header;
import org.apache.http.HttpResponse;
import org.apache.http.StatusLine;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpUriRequest;

public class p extends f
{
  private String c = null;
  private CharSequence d = "simba.taobao.com";
  private boolean e = true;

  public p(HttpClient paramHttpClient)
  {
    super(paramHttpClient);
  }

  protected HttpResponse a(HttpUriRequest paramHttpUriRequest)
    throws IOException
  {
    HttpResponse localHttpResponse = this.a.execute(paramHttpUriRequest, this.b);
    int i = localHttpResponse.getStatusLine().getStatusCode();
    boolean bool = new URL(paramHttpUriRequest.getURI().toString()).getHost().contains(this.d);
    paramHttpUriRequest = localHttpResponse;
    if (i == 302)
    {
      paramHttpUriRequest = localHttpResponse;
      if (this.e)
      {
        paramHttpUriRequest = localHttpResponse;
        if (!bool)
        {
          Log.i("statusCode =" + i, new Object[0]);
          this.c = localHttpResponse.getFirstHeader("location").getValue();
          if (this.c == null)
            this.c = localHttpResponse.getFirstHeader("Location").getValue();
          paramHttpUriRequest = localHttpResponse;
          if (this.c != null)
            paramHttpUriRequest = a(new HttpGet(this.c));
        }
      }
    }
    return paramHttpUriRequest;
  }

  public void a(String paramString)
  {
    this.d = paramString;
  }

  public void a(boolean paramBoolean)
  {
    this.e = paramBoolean;
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     com.taobao.newxp.net.p
 * JD-Core Version:    0.6.2
 */