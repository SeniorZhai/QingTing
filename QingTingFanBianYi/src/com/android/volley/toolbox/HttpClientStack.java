package com.android.volley.toolbox;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.VolleyLog;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Proxy;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.apache.http.HttpHost;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpEntityEnclosingRequestBase;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.entity.ByteArrayEntity;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;

public class HttpClientStack
  implements HttpStack
{
  private static final String HEADER_CONTENT_TYPE = "Content-Type";
  protected final HttpClient mClient;

  public HttpClientStack(HttpClient paramHttpClient)
  {
    this.mClient = paramHttpClient;
  }

  private static void addHeaders(HttpUriRequest paramHttpUriRequest, Map<String, String> paramMap)
  {
    Iterator localIterator = paramMap.keySet().iterator();
    while (true)
    {
      if (!localIterator.hasNext())
        return;
      String str = (String)localIterator.next();
      paramHttpUriRequest.setHeader(str, (String)paramMap.get(str));
    }
  }

  static HttpUriRequest createHttpRequest(Request<?> paramRequest, Map<String, String> paramMap)
    throws AuthFailureError
  {
    switch (paramRequest.getMethod())
    {
    default:
      throw new IllegalStateException("Unknown request method.");
    case -1:
      paramMap = paramRequest.getPostBody();
      if (paramMap != null)
      {
        HttpPost localHttpPost = new HttpPost(paramRequest.getUrl());
        localHttpPost.addHeader("Content-Type", paramRequest.getPostBodyContentType());
        localHttpPost.setEntity(new ByteArrayEntity(paramMap));
        return localHttpPost;
      }
      return new HttpGet(paramRequest.getUrl());
    case 0:
      return new HttpGet(paramRequest.getUrl());
    case 3:
      return new HttpDelete(paramRequest.getUrl());
    case 1:
      paramMap = new HttpPost(paramRequest.getUrl());
      paramMap.addHeader("Content-Type", paramRequest.getBodyContentType());
      setEntityIfNonEmptyBody(paramMap, paramRequest);
      return paramMap;
    case 2:
    }
    paramMap = new HttpPut(paramRequest.getUrl());
    paramMap.addHeader("Content-Type", paramRequest.getBodyContentType());
    setEntityIfNonEmptyBody(paramMap, paramRequest);
    return paramMap;
  }

  private static List<NameValuePair> getPostParameterPairs(Map<String, String> paramMap)
  {
    ArrayList localArrayList = new ArrayList(paramMap.size());
    Iterator localIterator = paramMap.keySet().iterator();
    while (true)
    {
      if (!localIterator.hasNext())
        return localArrayList;
      String str = (String)localIterator.next();
      localArrayList.add(new BasicNameValuePair(str, (String)paramMap.get(str)));
    }
  }

  private static void setEntityIfNonEmptyBody(HttpEntityEnclosingRequestBase paramHttpEntityEnclosingRequestBase, Request<?> paramRequest)
    throws AuthFailureError
  {
    paramRequest = paramRequest.getBody();
    if (paramRequest != null)
      paramHttpEntityEnclosingRequestBase.setEntity(new ByteArrayEntity(paramRequest));
  }

  protected void onPrepareRequest(HttpUriRequest paramHttpUriRequest)
    throws IOException
  {
  }

  public HttpResponse performRequest(Request<?> paramRequest, Map<String, String> paramMap)
    throws IOException, AuthFailureError
  {
    HttpUriRequest localHttpUriRequest = createHttpRequest(paramRequest, paramMap);
    if ((paramRequest.isEnableProxy()) && (paramRequest.getProxy() != null))
    {
      VolleyLog.d("HttpClientStack use proxy: %s", new Object[] { paramRequest.getUrl() });
      addHeaders(localHttpUriRequest, paramRequest.getProxyHeader());
      Object localObject = (InetSocketAddress)paramRequest.getProxy().address();
      localObject = new HttpHost(((InetSocketAddress)localObject).getHostName(), ((InetSocketAddress)localObject).getPort());
      this.mClient.getParams().setParameter("http.route.default-proxy", localObject);
    }
    addHeaders(localHttpUriRequest, paramMap);
    addHeaders(localHttpUriRequest, paramRequest.getHeaders());
    onPrepareRequest(localHttpUriRequest);
    paramMap = localHttpUriRequest.getParams();
    int i = paramRequest.getTimeoutMs();
    HttpConnectionParams.setConnectionTimeout(paramMap, 5000);
    HttpConnectionParams.setSoTimeout(paramMap, i);
    return this.mClient.execute(localHttpUriRequest);
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     com.android.volley.toolbox.HttpClientStack
 * JD-Core Version:    0.6.2
 */