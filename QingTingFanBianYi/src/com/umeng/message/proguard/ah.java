package com.umeng.message.proguard;

import android.content.Context;
import android.text.TextUtils;
import android.util.Log;
import org.apache.http.HttpHost;
import org.apache.http.conn.ClientConnectionManager;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import org.apache.http.params.HttpProtocolParams;

public class ah extends DefaultHttpClient
{
  private static final String a = ah.class.getSimpleName();
  private static final int b = 30000;
  private static final int c = 8192;
  private String d;
  private int e;
  private boolean f;
  private RuntimeException g = new IllegalStateException("ProxyHttpClient created and never closed");

  public ah(Context paramContext, ag paramag)
  {
    this(paramContext, null, paramag);
  }

  public ah(Context paramContext, String paramString, ag paramag)
  {
    ag localag = paramag;
    if (paramag == null)
      localag = new ag(paramContext);
    this.f = localag.a();
    this.d = localag.d();
    this.e = localag.e();
    if (this.f)
    {
      paramContext = new HttpHost(this.d, this.e);
      getParams().setParameter("http.route.default-proxy", paramContext);
    }
    HttpConnectionParams.setConnectionTimeout(getParams(), 30000);
    HttpConnectionParams.setSoTimeout(getParams(), 30000);
    HttpConnectionParams.setSocketBufferSize(getParams(), 8192);
    if (!TextUtils.isEmpty(paramString))
      HttpProtocolParams.setUserAgent(getParams(), paramString);
  }

  public void a()
  {
    if (this.g != null)
    {
      getConnectionManager().shutdown();
      this.g = null;
    }
  }

  public boolean b()
  {
    return this.f;
  }

  protected HttpParams createHttpParams()
  {
    HttpParams localHttpParams = super.createHttpParams();
    HttpProtocolParams.setUseExpectContinue(localHttpParams, false);
    return localHttpParams;
  }

  protected void finalize()
    throws Throwable
  {
    super.finalize();
    if (this.g != null)
      Log.e(a, "Leak found", this.g);
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     com.umeng.message.proguard.ah
 * JD-Core Version:    0.6.2
 */