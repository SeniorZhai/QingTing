package com.taobao.munion.base.caches;

import android.text.TextUtils;
import android.webkit.CookieManager;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

public class q
  implements Runnable
{
  public static final String a = "url";
  public static final String b = "response-code";
  public static final String c = "content-type";
  public static final String d = "Content-Type";
  public static final String e = "content-length";
  public static final String f = "location";
  public static final String g = "last-modified";
  public static final String h = "expires";
  public static final String i = "date";
  public static final String j = "set-cookie";
  public static final String k = "cache-control";
  public static final String l = "etag";
  public static final int m = 15000;
  public static final int n = 15000;
  private static Object s = new Object();
  String o;
  v p;
  String q;
  private m r;

  public q(String paramString1, v paramv, String paramString2, m paramm)
  {
    this.o = paramString1;
    this.p = paramv;
    this.q = paramString2;
    this.r = paramm;
  }

  public void run()
  {
    Object localObject5;
    while (true)
    {
      int i1;
      try
      {
        localObject3 = (HttpURLConnection)new URL(this.o).openConnection();
        ((HttpURLConnection)localObject3).setRequestProperty("Connection", "Keep-Alive");
        ((HttpURLConnection)localObject3).setReadTimeout(15000);
        ((HttpURLConnection)localObject3).setConnectTimeout(15000);
        if (this.o.contains("taobao.com"))
          ((HttpURLConnection)localObject3).setRequestProperty("Cookie", CookieManager.getInstance().getCookie("re.m.taobao.com"));
        ((HttpURLConnection)localObject3).connect();
        int i2 = ((HttpURLConnection)localObject3).getResponseCode();
        if ((i2 != 200) && (i2 != 304))
          break label354;
        HashMap localHashMap = new HashMap();
        i1 = 1;
        ??? = ((HttpURLConnection)localObject3).getHeaderFieldKey(i1);
        if (??? != null)
          break label219;
        localHashMap.put("response-code", String.valueOf(i2));
        localHashMap.put("url", this.o);
        ??? = ((HttpURLConnection)localObject3).getInputStream();
        localObject5 = new ByteArrayOutputStream();
        localObject3 = new byte[2048];
        i1 = ((InputStream)???).read((byte[])localObject3);
        if (i1 == -1)
          break;
        ((ByteArrayOutputStream)localObject5).write((byte[])localObject3, 0, i1);
        continue;
      }
      catch (Exception localException)
      {
        localException.printStackTrace();
        if (this.r != null)
        {
          this.r.a = -1;
          this.r.b();
        }
      }
      this.p = null;
      return;
      label219: i1 += 1;
      localObject5 = ((HttpURLConnection)localObject3).getHeaderField((String)???);
      localException.put(((String)???).toLowerCase(), localObject5);
    }
    Object localObject3 = ((ByteArrayOutputStream)localObject5).toByteArray();
    ((ByteArrayOutputStream)localObject5).close();
    ((InputStream)???).close();
    if ((b.f) && (!TextUtils.isEmpty(b.c)) && (b.c.equals(this.o)))
    {
      ??? = new String((byte[])localObject3, "utf-8");
      b.a().e((String)???);
    }
    while (true)
    {
      synchronized (s)
      {
        if (this.p != null)
          this.p.a((byte[])localObject3, localException, this.q, this.r);
      }
      label354: if (this.r != null)
        this.r.a = -1;
      Object localObject2 = null;
      localObject3 = null;
    }
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     com.taobao.munion.base.caches.q
 * JD-Core Version:    0.6.2
 */