package com.taobao.munion.base.volley.a;

import com.taobao.munion.base.volley.a;
import com.taobao.munion.base.volley.l;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
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

public class h
  implements g
{
  private static final String a = "Content-Type";
  private final a b;
  private final SSLSocketFactory c;

  public h()
  {
    this(null);
  }

  public h(a parama)
  {
    this(parama, null);
  }

  public h(a parama, SSLSocketFactory paramSSLSocketFactory)
  {
    this.b = parama;
    this.c = paramSSLSocketFactory;
  }

  private HttpURLConnection a(URL paramURL, l<?> paraml)
    throws IOException
  {
    HttpURLConnection localHttpURLConnection = a(paramURL);
    int i = paraml.v();
    localHttpURLConnection.setConnectTimeout(i);
    localHttpURLConnection.setReadTimeout(i);
    localHttpURLConnection.setUseCaches(false);
    localHttpURLConnection.setDoInput(true);
    if (("https".equals(paramURL.getProtocol())) && (this.c != null))
      ((HttpsURLConnection)localHttpURLConnection).setSSLSocketFactory(this.c);
    return localHttpURLConnection;
  }

  private static HttpEntity a(HttpURLConnection paramHttpURLConnection)
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

  static void a(HttpURLConnection paramHttpURLConnection, l<?> paraml)
    throws IOException, a
  {
    switch (paraml.b())
    {
    default:
      throw new IllegalStateException("Unknown method type.");
    case -1:
      byte[] arrayOfByte = paraml.o();
      if (arrayOfByte != null)
      {
        paramHttpURLConnection.setDoOutput(true);
        paramHttpURLConnection.setRequestMethod("POST");
        paramHttpURLConnection.addRequestProperty("Content-Type", paraml.n());
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
      b(paramHttpURLConnection, paraml);
      return;
    case 2:
      paramHttpURLConnection.setRequestMethod("PUT");
      b(paramHttpURLConnection, paraml);
      return;
    case 4:
      paramHttpURLConnection.setRequestMethod("HEAD");
      return;
    case 5:
      paramHttpURLConnection.setRequestMethod("OPTIONS");
      return;
    case 6:
      paramHttpURLConnection.setRequestMethod("TRACE");
      return;
    case 7:
    }
    b(paramHttpURLConnection, paraml);
    paramHttpURLConnection.setRequestMethod("PATCH");
  }

  private static void b(HttpURLConnection paramHttpURLConnection, l<?> paraml)
    throws IOException, a
  {
    byte[] arrayOfByte = paraml.s();
    if (arrayOfByte != null)
    {
      paramHttpURLConnection.setDoOutput(true);
      paramHttpURLConnection.addRequestProperty("Content-Type", paraml.r());
      paramHttpURLConnection = new DataOutputStream(paramHttpURLConnection.getOutputStream());
      paramHttpURLConnection.write(arrayOfByte);
      paramHttpURLConnection.close();
    }
  }

  protected HttpURLConnection a(URL paramURL)
    throws IOException
  {
    return (HttpURLConnection)paramURL.openConnection();
  }

  public HttpResponse a(l<?> paraml, Map<String, String> paramMap)
    throws IOException, a
  {
    Object localObject = paraml.f();
    HashMap localHashMap = new HashMap();
    localHashMap.putAll(paramMap);
    localHashMap.putAll(paraml.k());
    String str;
    if (this.b != null)
    {
      str = this.b.a((String)localObject);
      paramMap = str;
      if (str == null)
        throw new IOException("URL blocked by rewriter: " + (String)localObject);
    }
    else
    {
      paramMap = (Map<String, String>)localObject;
    }
    paramMap = a(new URL(paramMap), paraml);
    localObject = localHashMap.keySet().iterator();
    while (((Iterator)localObject).hasNext())
    {
      str = (String)((Iterator)localObject).next();
      paramMap.addRequestProperty(str, (String)localHashMap.get(str));
    }
    a(paramMap, paraml);
    paraml = new ProtocolVersion("HTTP", 1, 1);
    if (paramMap.getResponseCode() == -1)
      throw new IOException("Could not retrieve response code from HttpUrlConnection.");
    paraml = new BasicHttpResponse(new BasicStatusLine(paraml, paramMap.getResponseCode(), paramMap.getResponseMessage()));
    paraml.setEntity(a(paramMap));
    paramMap = paramMap.getHeaderFields().entrySet().iterator();
    while (paramMap.hasNext())
    {
      localObject = (Map.Entry)paramMap.next();
      if (((Map.Entry)localObject).getKey() != null)
        paraml.addHeader(new BasicHeader((String)((Map.Entry)localObject).getKey(), (String)((List)((Map.Entry)localObject).getValue()).get(0)));
    }
    return paraml;
  }

  public static abstract interface a
  {
    public abstract String a(String paramString);
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     com.taobao.munion.base.volley.a.h
 * JD-Core Version:    0.6.2
 */