package com.umeng.message.proguard;

import android.text.TextUtils;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Proxy;
import java.net.Proxy.Type;
import java.net.URL;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

public class az extends at
{
  private static final String l = "HttpURLChunked";
  private volatile HttpURLConnection m = null;

  private String a(List<String> paramList)
  {
    StringBuffer localStringBuffer = new StringBuffer();
    int j = paramList.size();
    int i = 0;
    while (i < j)
    {
      localStringBuffer.append((String)paramList.get(i));
      if (i < j - 1)
        localStringBuffer.append(",");
      i += 1;
    }
    return localStringBuffer.toString();
  }

  private Map<String, String> a(HttpURLConnection paramHttpURLConnection)
  {
    HashMap localHashMap = new HashMap();
    paramHttpURLConnection = paramHttpURLConnection.getHeaderFields().entrySet().iterator();
    while (paramHttpURLConnection.hasNext())
    {
      Object localObject = (Map.Entry)paramHttpURLConnection.next();
      String str = (String)((Map.Entry)localObject).getKey();
      if (!TextUtils.isEmpty(str))
      {
        localObject = a((List)((Map.Entry)localObject).getValue());
        if (!TextUtils.isEmpty((CharSequence)localObject))
          localHashMap.put(str, localObject);
      }
    }
    return localHashMap;
  }

  private void b(HttpURLConnection paramHttpURLConnection)
    throws IOException
  {
    if (paramHttpURLConnection == null)
      throw new IOException();
    this.b = paramHttpURLConnection.getInputStream();
  }

  protected void a(String paramString)
  {
    while (true)
    {
      try
      {
        Q.c("HttpURLChunked", "http chunked connectId:[" + b() + "]==>" + paramString);
        paramString = new URL(paramString);
        if (k())
        {
          this.m = ((HttpURLConnection)paramString.openConnection(new Proxy(Proxy.Type.HTTP, new InetSocketAddress(InetAddress.getByName(super.i()), super.j()))));
          this.m.setRequestProperty("User-agent", String.format("Agoo-sdk-%s", new Object[] { Double.valueOf(2.0D) }));
          if (this.f == null)
            break;
          paramString = this.f.keySet().iterator();
          if (!paramString.hasNext())
            break;
          String str = (String)paramString.next();
          this.m.setRequestProperty(str, (String)this.f.get(str));
          continue;
        }
      }
      catch (Throwable paramString)
      {
        if (!hasCallError())
        {
          callError(true);
          Q.d("HttpURLChunked", "http chunked connectId:[" + b() + "]==>[Throwable]", paramString);
          a(504, paramString);
        }
        return;
      }
      this.m = ((HttpURLConnection)paramString.openConnection());
    }
    this.m.connect();
    int i = this.m.getResponseCode();
    paramString = a(this.m);
    if (200 == i)
    {
      b(this.m);
      a(paramString);
      e();
      return;
    }
    a(i, paramString);
    f();
  }

  protected void c()
  {
    if (this.m != null)
    {
      this.m.disconnect();
      this.m = null;
    }
  }

  protected void d()
  {
    if (this.m != null)
    {
      this.m.disconnect();
      this.m = null;
    }
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     com.umeng.message.proguard.az
 * JD-Core Version:    0.6.2
 */