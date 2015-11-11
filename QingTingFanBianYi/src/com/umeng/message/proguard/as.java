package com.umeng.message.proguard;

import android.content.Context;
import java.io.IOException;
import java.net.URI;
import org.apache.http.HttpEntity;
import org.apache.http.HttpHost;
import org.apache.http.HttpResponse;
import org.apache.http.StatusLine;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.entity.BufferedHttpEntity;
import org.apache.http.impl.client.AbstractHttpClient;
import org.apache.http.params.HttpParams;
import org.apache.http.util.EntityUtils;

public class as extends ai
{
  private static final String a = "SyncHttp.client";

  public a get(Context paramContext, String paramString, aq paramaq)
    throws Exception
  {
    return get(paramContext, null, paramString, paramaq);
  }

  public a get(Context paramContext, HttpHost paramHttpHost, String paramString, aq paramaq)
    throws Exception
  {
    Object localObject = null;
    try
    {
      paramaq = new HttpGet(a(paramString, paramaq));
      if (!ag.a(paramContext))
      {
        Q.c("SyncHttp.client", "network connection error[" + paramaq.getURI().toString() + "]");
        throw new RuntimeException("network connection error[" + paramaq.getURI().toString() + "]");
      }
    }
    catch (IOException paramContext)
    {
      Q.e("SyncHttp.client", "request url error:[" + paramString + "]", paramContext);
      throw paramContext;
    }
    paramContext = getHttpClient();
    if (paramHttpHost != null)
      paramContext.getParams().setParameter("http.route.default-proxy", paramHttpHost);
    paramContext = paramContext.execute(paramaq);
    paramHttpHost = new a();
    StatusLine localStatusLine = paramContext.getStatusLine();
    paramHttpHost.a = localStatusLine.getStatusCode();
    if (localStatusLine.getStatusCode() >= 300)
    {
      Q.c("SyncHttp.client", "request url [" + paramaq.getURI().toString() + "]  result code:[" + localStatusLine.getStatusCode() + "]");
      return paramHttpHost;
    }
    HttpEntity localHttpEntity = paramContext.getEntity();
    paramContext = localObject;
    if (localHttpEntity != null)
    {
      paramContext = EntityUtils.toString(new BufferedHttpEntity(localHttpEntity), "UTF-8");
      paramHttpHost.b = paramContext;
    }
    Q.c("SyncHttp.client", "request url:[" + paramaq.getURI().toString() + "] : result code [" + localStatusLine.getStatusCode() + "]:[" + paramContext + "]");
    return paramHttpHost;
  }

  public static class a
  {
    public int a;
    public String b;
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     com.umeng.message.proguard.as
 * JD-Core Version:    0.6.2
 */