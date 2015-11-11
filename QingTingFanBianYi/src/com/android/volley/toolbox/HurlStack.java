package com.android.volley.toolbox;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.VolleyLog;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.Proxy;
import java.net.URL;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Map<Ljava.lang.String;Ljava.lang.String;>;
import java.util.Set;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLSocketFactory;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.ProtocolVersion;
import org.apache.http.entity.BasicHttpEntity;
import org.apache.http.message.BasicHeader;
import org.apache.http.message.BasicHttpResponse;
import org.apache.http.message.BasicStatusLine;

public class HurlStack
  implements HttpStack
{
  private static final String HEADER_CONTENT_TYPE = "Content-Type";
  private boolean isEnableProxy = false;
  private final SSLSocketFactory mSslSocketFactory;
  private final UrlRewriter mUrlRewriter;
  private Proxy proxy = null;

  public HurlStack()
  {
    this(null);
  }

  public HurlStack(UrlRewriter paramUrlRewriter)
  {
    this(paramUrlRewriter, null);
  }

  public HurlStack(UrlRewriter paramUrlRewriter, SSLSocketFactory paramSSLSocketFactory)
  {
    this.mUrlRewriter = paramUrlRewriter;
    this.mSslSocketFactory = paramSSLSocketFactory;
  }

  private static void addBodyIfExists(HttpURLConnection paramHttpURLConnection, Request<?> paramRequest)
    throws IOException, AuthFailureError
  {
    byte[] arrayOfByte = paramRequest.getBody();
    if (arrayOfByte != null)
    {
      paramHttpURLConnection.setDoOutput(true);
      paramHttpURLConnection.addRequestProperty("Content-Type", paramRequest.getBodyContentType());
      paramHttpURLConnection = new DataOutputStream(paramHttpURLConnection.getOutputStream());
      paramHttpURLConnection.write(arrayOfByte);
      paramHttpURLConnection.close();
    }
  }

  private static HttpEntity entityFromConnection(HttpURLConnection paramHttpURLConnection)
  {
    BasicHttpEntity localBasicHttpEntity = new BasicHttpEntity();
    try
    {
      InputStream localInputStream1 = paramHttpURLConnection.getInputStream();
      localBasicHttpEntity.setContent(localInputStream1);
      localBasicHttpEntity.setContentLength(paramHttpURLConnection.getContentLength());
      localBasicHttpEntity.setContentEncoding(paramHttpURLConnection.getContentEncoding());
      localBasicHttpEntity.setContentType(paramHttpURLConnection.getContentType());
      return localBasicHttpEntity;
    }
    catch (IOException localIOException)
    {
      while (true)
        InputStream localInputStream2 = paramHttpURLConnection.getErrorStream();
    }
  }

  private HttpURLConnection openConnection(URL paramURL, Request<?> paramRequest)
    throws IOException
  {
    if (paramRequest.isEnableProxy());
    for (HttpURLConnection localHttpURLConnection = (HttpURLConnection)paramURL.openConnection(paramRequest.getProxy()); ; localHttpURLConnection = (HttpURLConnection)paramURL.openConnection())
    {
      int i = paramRequest.getTimeoutMs();
      localHttpURLConnection.setConnectTimeout(i);
      localHttpURLConnection.setReadTimeout(i);
      localHttpURLConnection.setUseCaches(false);
      localHttpURLConnection.setDoInput(true);
      if (("https".equals(paramURL.getProtocol())) && (this.mSslSocketFactory != null))
        ((HttpsURLConnection)localHttpURLConnection).setSSLSocketFactory(this.mSslSocketFactory);
      return localHttpURLConnection;
    }
  }

  static void setConnectionParametersForRequest(HttpURLConnection paramHttpURLConnection, Request<?> paramRequest)
    throws IOException, AuthFailureError
  {
    switch (paramRequest.getMethod())
    {
    default:
      throw new IllegalStateException("Unknown method type.");
    case -1:
      byte[] arrayOfByte = paramRequest.getPostBody();
      if (arrayOfByte != null)
      {
        paramHttpURLConnection.setDoOutput(true);
        paramHttpURLConnection.setRequestMethod("POST");
        paramHttpURLConnection.addRequestProperty("Content-Type", paramRequest.getPostBodyContentType());
        paramHttpURLConnection = new DataOutputStream(paramHttpURLConnection.getOutputStream());
        paramHttpURLConnection.write(arrayOfByte);
        paramHttpURLConnection.close();
      }
      return;
    case 0:
      paramHttpURLConnection.setRequestMethod("GET");
      return;
    case 3:
      paramHttpURLConnection.setRequestMethod("DELETE");
      return;
    case 1:
      paramHttpURLConnection.setRequestMethod("POST");
      addBodyIfExists(paramHttpURLConnection, paramRequest);
      return;
    case 2:
    }
    paramHttpURLConnection.setRequestMethod("PUT");
    addBodyIfExists(paramHttpURLConnection, paramRequest);
  }

  public HttpResponse performRequest(Request<?> paramRequest, Map<String, String> paramMap)
    throws IOException, AuthFailureError
  {
    Object localObject1 = paramRequest.getUrl();
    HashMap localHashMap = new HashMap();
    localHashMap.putAll(paramRequest.getHeaders());
    localHashMap.putAll(paramMap);
    paramMap = (Map<String, String>)localObject1;
    if (this.mUrlRewriter != null)
    {
      paramMap = this.mUrlRewriter.rewriteUrl((String)localObject1);
      if (paramMap == null)
        throw new IOException("URL blocked by rewriter: " + (String)localObject1);
    }
    paramMap = openConnection(new URL(paramMap), paramRequest);
    Object localObject2;
    if (paramRequest.isEnableProxy())
    {
      VolleyLog.d("HurlStack use proxy: %s\n", new Object[] { paramRequest.getUrl() });
      localObject1 = paramRequest.getProxyHeader();
      localObject2 = ((HashMap)localObject1).keySet().iterator();
      if (((Iterator)localObject2).hasNext());
    }
    else
    {
      localObject1 = localHashMap.keySet().iterator();
    }
    while (true)
    {
      if (!((Iterator)localObject1).hasNext())
      {
        setConnectionParametersForRequest(paramMap, paramRequest);
        paramRequest = new ProtocolVersion("HTTP", 1, 1);
        if (paramMap.getResponseCode() != -1)
          break label257;
        throw new IOException("Could not retrieve response code from HttpUrlConnection.");
        String str = (String)((Iterator)localObject2).next();
        paramMap.addRequestProperty(str, (String)((HashMap)localObject1).get(str));
        break;
      }
      localObject2 = (String)((Iterator)localObject1).next();
      paramMap.addRequestProperty((String)localObject2, (String)localHashMap.get(localObject2));
    }
    label257: paramRequest = new BasicHttpResponse(new BasicStatusLine(paramRequest, paramMap.getResponseCode(), paramMap.getResponseMessage()));
    paramRequest.setEntity(entityFromConnection(paramMap));
    paramMap = paramMap.getHeaderFields().entrySet().iterator();
    while (true)
    {
      if (!paramMap.hasNext())
        return paramRequest;
      localObject1 = (Map.Entry)paramMap.next();
      if (((Map.Entry)localObject1).getKey() != null)
        paramRequest.addHeader(new BasicHeader((String)((Map.Entry)localObject1).getKey(), (String)((List)((Map.Entry)localObject1).getValue()).get(0)));
    }
  }

  public static abstract interface UrlRewriter
  {
    public abstract String rewriteUrl(String paramString);
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     com.android.volley.toolbox.HurlStack
 * JD-Core Version:    0.6.2
 */