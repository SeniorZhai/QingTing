package com.taobao.newxp.common;

import android.content.Context;
import android.webkit.WebSettings;
import android.webkit.WebView;
import com.taobao.munion.base.DefaultAppUtils;
import com.taobao.munion.base.DefaultAppUtils.a;
import com.taobao.munion.base.a;
import com.taobao.munion.base.caches.b;
import com.taobao.newxp.common.persistence.PersistentCookieStore;
import com.taobao.newxp.net.c;
import com.taobao.newxp.net.e;
import com.taobao.newxp.net.j;
import com.taobao.newxp.net.k;
import com.taobao.newxp.net.l;
import org.apache.http.client.CookieStore;

public class AlimmContext
{
  private static AlimmContext a;
  private Context b;
  private a c;
  private CookieStore d;
  private c e;
  private c f;
  private c g;
  private boolean h = false;
  private String i = "";

  private void a()
  {
    if (!this.h)
      throw new RuntimeException("AlimmContext is not initized.");
  }

  public static AlimmContext getAliContext()
  {
    if (a == null)
      a = new AlimmContext();
    return a;
  }

  public Context getAppContext()
  {
    a();
    return this.b;
  }

  public a getAppUtils()
  {
    a();
    return this.c;
  }

  public CookieStore getCookieStore()
  {
    a();
    return this.d;
  }

  public c getQueryQueue()
  {
    a();
    return this.e;
  }

  public c getRedirctQueue()
  {
    return this.g;
  }

  public c getReportQueue()
  {
    a();
    return this.f;
  }

  public String getWebviewUA()
  {
    return this.i;
  }

  public void init(Context paramContext)
  {
    if (!this.h)
    {
      this.b = paramContext.getApplicationContext();
      b.a().a(paramContext);
      DefaultAppUtils localDefaultAppUtils = new DefaultAppUtils();
      DefaultAppUtils.a locala = new DefaultAppUtils.a();
      locala.a = "*";
      locala.c = "2G/3G";
      locala.d = "Wi-Fi";
      locala.b = "Unknown";
      localDefaultAppUtils.init(this.b, locala);
      this.c = localDefaultAppUtils;
      paramContext = new WebView(paramContext);
      this.i = paramContext.getSettings().getUserAgentString();
      paramContext.destroy();
      this.d = new PersistentCookieStore(this.b);
      boolean bool = e.a(this.d);
      this.e = new j(this.i).a(this.d).a(bool).a(this.b);
      this.g = new k(this.i).a(this.d).a(this.b);
      this.f = new l().a(this.b);
      this.h = true;
    }
  }

  public boolean isInitized()
  {
    return this.h;
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     com.taobao.newxp.common.AlimmContext
 * JD-Core Version:    0.6.2
 */