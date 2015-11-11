package com.alipay.sdk.data;

import org.apache.http.Header;
import org.json.JSONObject;

public class Response
{
  public static final int a = 1000;
  public static final int b = 503;
  public static final int c = 0;
  Envelope d = null;
  Header[] e = null;
  private int f = 0;
  private String g = "";
  private long h = 0L;
  private String i = "";
  private String j = null;
  private String k = null;
  private JSONObject l = null;
  private String m;
  private boolean n = true;

  private boolean e()
  {
    return this.n;
  }

  private String f()
  {
    return this.i;
  }

  private String g()
  {
    return this.j;
  }

  private String h()
  {
    return this.k;
  }

  private String i()
  {
    return this.m;
  }

  public final Envelope a()
  {
    return this.d;
  }

  public final void a(int paramInt)
  {
    this.f = paramInt;
  }

  public final void a(long paramLong)
  {
    this.h = paramLong;
  }

  public final void a(Envelope paramEnvelope)
  {
    this.d = paramEnvelope;
  }

  public final void a(String paramString)
  {
    this.g = paramString;
  }

  public final void a(JSONObject paramJSONObject)
  {
    this.l = paramJSONObject;
  }

  public final void b()
  {
    this.n = false;
  }

  public final void b(String paramString)
  {
    this.i = paramString;
  }

  public final int c()
  {
    return this.f;
  }

  public final void c(String paramString)
  {
    this.j = paramString;
  }

  public final String d()
  {
    return this.g;
  }

  public final void d(String paramString)
  {
    this.k = paramString;
  }

  public final void e(String paramString)
  {
    this.m = paramString;
  }

  public String toString()
  {
    String str2 = this.d.toString() + ", code = " + this.f + ", errorMsg = " + this.g + ", timeStamp = " + this.h + ", endCode = " + this.i;
    String str1 = str2;
    if (this.l != null)
      str1 = str2 + ", reflectedData = " + this.l;
    return str1;
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     com.alipay.sdk.data.Response
 * JD-Core Version:    0.6.2
 */