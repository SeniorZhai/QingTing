package com.taobao.newxp.net;

import android.content.Context;
import android.net.http.AndroidHttpClient;
import com.taobao.munion.base.Log;
import com.taobao.munion.base.volley.a.a;
import com.taobao.munion.base.volley.a.d;
import com.taobao.munion.base.volley.l;
import com.taobao.munion.base.volley.m;
import java.io.File;
import org.apache.http.client.CookieStore;

public class k
  implements c
{
  private m b;
  private CookieStore c;
  private p d;
  private String e = "";

  public k(String paramString)
  {
    this.e = paramString;
  }

  public <T> l<T> a(l<T> paraml)
  {
    if (this.b != null)
    {
      if (!e.z())
      {
        e locale = e.a();
        if (locale == null)
          break label53;
        locale.a = true;
        this.b.a(locale);
        Log.i("add cna-requset for udpdate cookie.", new Object[0]);
      }
      while (true)
      {
        return this.b.a(paraml);
        label53: Log.i("exist activity cna-request in queue.", new Object[0]);
      }
    }
    throw new RuntimeException("MunionVolley is not initized..");
  }

  public c a(Context paramContext)
  {
    paramContext = new File(paramContext.getCacheDir(), "taobao_munion");
    this.d = new p(AndroidHttpClient.newInstance(this.e));
    if (this.c != null)
      this.d.a(this.c);
    a locala = new a(this.d);
    this.b = new m(new d(paramContext), locala, 1);
    this.b.a();
    return this;
  }

  public k a(CookieStore paramCookieStore)
  {
    this.c = paramCookieStore;
    return this;
  }

  public p a()
  {
    return this.d;
  }

  public void a(Boolean paramBoolean)
  {
    this.d.a(paramBoolean.booleanValue());
  }

  public void a(String paramString)
  {
    this.d.a(paramString);
  }

  public CookieStore b()
  {
    return this.c;
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     com.taobao.newxp.net.k
 * JD-Core Version:    0.6.2
 */