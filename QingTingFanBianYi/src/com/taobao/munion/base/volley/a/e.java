package com.taobao.munion.base.volley.a;

import com.taobao.munion.base.volley.a;
import com.taobao.munion.base.volley.l;
import java.io.IOException;
import java.net.URI;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpEntityEnclosingRequestBase;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpHead;
import org.apache.http.client.methods.HttpOptions;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.client.methods.HttpTrace;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.entity.ByteArrayEntity;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.params.HttpConnectionParams;

public class e
  implements g
{
  private static final String b = "Content-Type";
  protected final HttpClient a;

  public e(HttpClient paramHttpClient)
  {
    this.a = paramHttpClient;
  }

  private static List<NameValuePair> a(Map<String, String> paramMap)
  {
    ArrayList localArrayList = new ArrayList(paramMap.size());
    Iterator localIterator = paramMap.keySet().iterator();
    while (localIterator.hasNext())
    {
      String str = (String)localIterator.next();
      localArrayList.add(new BasicNameValuePair(str, (String)paramMap.get(str)));
    }
    return localArrayList;
  }

  private static void a(HttpEntityEnclosingRequestBase paramHttpEntityEnclosingRequestBase, l<?> paraml)
    throws a
  {
    paraml = paraml.s();
    if (paraml != null)
      paramHttpEntityEnclosingRequestBase.setEntity(new ByteArrayEntity(paraml));
  }

  private static void a(HttpUriRequest paramHttpUriRequest, Map<String, String> paramMap)
  {
    Iterator localIterator = paramMap.keySet().iterator();
    while (localIterator.hasNext())
    {
      String str = (String)localIterator.next();
      paramHttpUriRequest.setHeader(str, (String)paramMap.get(str));
    }
  }

  static HttpUriRequest b(l<?> paraml, Map<String, String> paramMap)
    throws a
  {
    switch (paraml.b())
    {
    default:
      throw new IllegalStateException("Unknown request method.");
    case -1:
      paramMap = paraml.o();
      if (paramMap != null)
      {
        HttpPost localHttpPost = new HttpPost(paraml.f());
        localHttpPost.addHeader("Content-Type", paraml.n());
        localHttpPost.setEntity(new ByteArrayEntity(paramMap));
        return localHttpPost;
      }
      return new HttpGet(paraml.f());
    case 0:
      return new HttpGet(paraml.f());
    case 3:
      return new HttpDelete(paraml.f());
    case 1:
      paramMap = new HttpPost(paraml.f());
      paramMap.addHeader("Content-Type", paraml.r());
      a(paramMap, paraml);
      return paramMap;
    case 2:
      paramMap = new HttpPut(paraml.f());
      paramMap.addHeader("Content-Type", paraml.r());
      a(paramMap, paraml);
      return paramMap;
    case 4:
      return new HttpHead(paraml.f());
    case 5:
      return new HttpOptions(paraml.f());
    case 6:
      return new HttpTrace(paraml.f());
    case 7:
    }
    paramMap = new a(paraml.f());
    paramMap.addHeader("Content-Type", paraml.r());
    a(paramMap, paraml);
    return paramMap;
  }

  public HttpResponse a(l<?> paraml, Map<String, String> paramMap)
    throws IOException, a
  {
    HttpUriRequest localHttpUriRequest = b(paraml, paramMap);
    a(localHttpUriRequest, paramMap);
    a(localHttpUriRequest, paraml.k());
    b(localHttpUriRequest);
    paramMap = localHttpUriRequest.getParams();
    int i = paraml.v();
    HttpConnectionParams.setConnectionTimeout(paramMap, 5000);
    HttpConnectionParams.setSoTimeout(paramMap, i);
    return a(localHttpUriRequest);
  }

  protected HttpResponse a(HttpUriRequest paramHttpUriRequest)
    throws IOException
  {
    return this.a.execute(paramHttpUriRequest);
  }

  protected void b(HttpUriRequest paramHttpUriRequest)
    throws IOException
  {
  }

  public static final class a extends HttpEntityEnclosingRequestBase
  {
    public static final String a = "PATCH";

    public a()
    {
    }

    public a(String paramString)
    {
      setURI(URI.create(paramString));
    }

    public a(URI paramURI)
    {
      setURI(paramURI);
    }

    public String getMethod()
    {
      return "PATCH";
    }
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     com.taobao.munion.base.volley.a.e
 * JD-Core Version:    0.6.2
 */