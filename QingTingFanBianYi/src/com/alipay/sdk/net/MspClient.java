package com.alipay.sdk.net;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Proxy;
import android.os.Build.VERSION;
import android.text.TextUtils;
import com.alipay.sdk.data.InteractionData;
import com.alipay.sdk.exception.NetErrorException;
import com.alipay.sdk.sys.GlobalContext;
import java.io.IOException;
import java.net.SocketException;
import java.net.SocketTimeoutException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;
import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpHost;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.conn.ConnectTimeoutException;
import org.apache.http.entity.ByteArrayEntity;
import org.apache.http.params.HttpParams;

public class MspClient
{
  private Context a;
  private String b;

  private MspClient(Context paramContext)
  {
    this(paramContext, null);
  }

  public MspClient(Context paramContext, String paramString)
  {
    this.a = paramContext;
    this.b = paramString;
  }

  private static ByteArrayEntity a(InteractionData paramInteractionData, String paramString)
    throws IOException
  {
    Object localObject = null;
    String str1 = paramString;
    if (paramInteractionData != null)
    {
      String str2 = paramInteractionData.b();
      localObject = str2;
      str1 = paramString;
      if (!TextUtils.isEmpty(paramInteractionData.c()))
      {
        str1 = paramInteractionData.c() + "=" + paramString;
        localObject = str2;
      }
    }
    paramInteractionData = localObject;
    if (TextUtils.isEmpty(localObject))
      paramInteractionData = "application/octet-stream;binary/octet-stream";
    paramString = new ByteArrayEntity(str1.getBytes("utf-8"));
    paramString.setContentType(paramInteractionData);
    return paramString;
  }

  private URL b()
  {
    try
    {
      URL localURL = new URL(this.b);
      return localURL;
    }
    catch (Exception localException)
    {
    }
    return null;
  }

  private HttpHost c()
  {
    Object localObject;
    if (Build.VERSION.SDK_INT >= 11)
    {
      localObject = g();
      if ((localObject == null) || (((String)localObject).contains("wap")));
    }
    int i;
    do
    {
      do
      {
        String str;
        do
        {
          do
          {
            return null;
            localObject = b();
          }
          while (localObject == null);
          "https".equalsIgnoreCase(((URL)localObject).getProtocol());
          localObject = System.getProperty("https.proxyHost");
          str = System.getProperty("https.proxyPort");
        }
        while (TextUtils.isEmpty((CharSequence)localObject));
        return new HttpHost((String)localObject, Integer.parseInt(str));
        localObject = f();
      }
      while ((localObject == null) || (!((NetworkInfo)localObject).isAvailable()) || (((NetworkInfo)localObject).getType() != 0));
      localObject = Proxy.getDefaultHost();
      i = Proxy.getDefaultPort();
    }
    while (localObject == null);
    return new HttpHost((String)localObject, i);
  }

  private HttpHost d()
  {
    Object localObject2 = null;
    Object localObject3 = f();
    Object localObject1 = localObject2;
    if (localObject3 != null)
    {
      localObject1 = localObject2;
      if (((NetworkInfo)localObject3).isAvailable())
      {
        localObject1 = localObject2;
        if (((NetworkInfo)localObject3).getType() == 0)
        {
          localObject3 = Proxy.getDefaultHost();
          int i = Proxy.getDefaultPort();
          localObject1 = localObject2;
          if (localObject3 != null)
            localObject1 = new HttpHost((String)localObject3, i);
        }
      }
    }
    return localObject1;
  }

  private HttpHost e()
  {
    Object localObject = g();
    if ((localObject != null) && (!((String)localObject).contains("wap")));
    String str;
    do
    {
      do
      {
        return null;
        localObject = b();
      }
      while (localObject == null);
      "https".equalsIgnoreCase(((URL)localObject).getProtocol());
      localObject = System.getProperty("https.proxyHost");
      str = System.getProperty("https.proxyPort");
    }
    while (TextUtils.isEmpty((CharSequence)localObject));
    return new HttpHost((String)localObject, Integer.parseInt(str));
  }

  private NetworkInfo f()
  {
    try
    {
      NetworkInfo localNetworkInfo = ((ConnectivityManager)this.a.getSystemService("connectivity")).getActiveNetworkInfo();
      return localNetworkInfo;
    }
    catch (Exception localException)
    {
    }
    return null;
  }

  private String g()
  {
    try
    {
      Object localObject = f();
      if ((localObject != null) && (((NetworkInfo)localObject).isAvailable()))
      {
        if (((NetworkInfo)localObject).getType() == 1)
          return "wifi";
        localObject = ((NetworkInfo)localObject).getExtraInfo().toLowerCase();
        return localObject;
      }
      return "none";
    }
    catch (Exception localException)
    {
    }
    return "none";
  }

  public final String a()
  {
    return this.b;
  }

  public final HttpResponse a(String paramString, InteractionData paramInteractionData)
    throws NetErrorException
  {
    Object localObject1 = null;
    MspHttpClient localMspHttpClient = MspHttpClient.a();
    if (localMspHttpClient == null)
    {
      paramString = (String)localObject1;
      return paramString;
    }
    while (true)
    {
      try
      {
        localObject2 = localMspHttpClient.c();
        if (Build.VERSION.SDK_INT >= 11)
        {
          localObject1 = g();
          if ((localObject1 != null) && (!((String)localObject1).contains("wap")))
          {
            localObject1 = null;
            if (localObject1 != null)
              ((HttpParams)localObject2).setParameter("http.route.default-proxy", localObject1);
            new StringBuilder("requestUrl : ").append(this.b).toString();
            if (!TextUtils.isEmpty(paramString))
              continue;
            paramString = new HttpGet(this.b);
            if (paramInteractionData == null)
              continue;
            paramInteractionData = paramInteractionData.a();
            if (paramInteractionData == null)
              continue;
            paramInteractionData = paramInteractionData.iterator();
            if (!paramInteractionData.hasNext())
              continue;
            paramString.addHeader((Header)paramInteractionData.next());
            continue;
          }
        }
      }
      catch (NetErrorException paramString)
      {
        throw paramString;
        localObject1 = b();
        if (localObject1 == null)
          break label592;
        "https".equalsIgnoreCase(((URL)localObject1).getProtocol());
        localObject1 = System.getProperty("https.proxyHost");
        localObject3 = System.getProperty("https.proxyPort");
        if (TextUtils.isEmpty((CharSequence)localObject1))
          break label592;
        localObject1 = new HttpHost((String)localObject1, Integer.parseInt((String)localObject3));
        continue;
      }
      catch (ConnectTimeoutException paramString)
      {
        if (localMspHttpClient != null)
          localMspHttpClient.b();
        throw new NetErrorException();
        localObject1 = f();
        if ((localObject1 == null) || (!((NetworkInfo)localObject1).isAvailable()) || (((NetworkInfo)localObject1).getType() != 0))
          break label587;
        localObject1 = Proxy.getDefaultHost();
        int i = Proxy.getDefaultPort();
        if (localObject1 == null)
          break label587;
        localObject1 = new HttpHost((String)localObject1, i);
        continue;
      }
      catch (SocketException paramString)
      {
        throw new NetErrorException();
        Object localObject3 = new HttpPost(this.b);
        if (paramInteractionData != null)
        {
          String str = paramInteractionData.b();
          localObject1 = str;
          localObject2 = paramString;
          if (!TextUtils.isEmpty(paramInteractionData.c()))
          {
            localObject2 = paramInteractionData.c() + "=" + paramString;
            localObject1 = str;
          }
          paramString = (String)localObject1;
          if (TextUtils.isEmpty((CharSequence)localObject1))
            paramString = "application/octet-stream;binary/octet-stream";
          localObject1 = new ByteArrayEntity(((String)localObject2).getBytes("utf-8"));
          ((ByteArrayEntity)localObject1).setContentType(paramString);
          ((HttpPost)localObject3).setEntity((HttpEntity)localObject1);
          ((HttpUriRequest)localObject3).addHeader("Accept-Charset", "UTF-8");
          ((HttpUriRequest)localObject3).addHeader("Accept-Encoding", "gzip");
          ((HttpUriRequest)localObject3).addHeader("Connection", "Keep-Alive");
          ((HttpUriRequest)localObject3).addHeader("Keep-Alive", "timeout=180, max=100");
          paramString = (String)localObject3;
          continue;
        }
      }
      catch (SocketTimeoutException paramString)
      {
        if (localMspHttpClient != null)
          localMspHttpClient.b();
        throw new NetErrorException();
        GlobalContext.a();
        GlobalContext.d();
        paramInteractionData = localMspHttpClient.a(paramString);
        paramString = paramInteractionData.getHeaders("X-Hostname");
        if ((paramString != null) && (paramString.length > 0) && (paramString[0] != null))
          paramInteractionData.getHeaders("X-Hostname")[0].toString();
        localObject1 = paramInteractionData.getHeaders("X-ExecuteTime");
        paramString = paramInteractionData;
        if (localObject1 == null)
          break;
        paramString = paramInteractionData;
        if (localObject1.length <= 0)
          break;
        paramString = paramInteractionData;
        if (localObject1[0] == null)
          break;
        paramInteractionData.getHeaders("X-ExecuteTime")[0].toString();
        return paramInteractionData;
      }
      catch (Exception paramString)
      {
        throw new NetErrorException();
      }
      localObject1 = null;
      Object localObject2 = paramString;
      continue;
      label587: localObject1 = null;
      continue;
      label592: localObject1 = null;
    }
  }

  public final void a(String paramString)
  {
    this.b = paramString;
  }

  public final HttpResponse b(String paramString)
    throws NetErrorException
  {
    return a(paramString, null);
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     com.alipay.sdk.net.MspClient
 * JD-Core Version:    0.6.2
 */