package com.alipay.sdk.data;

public class Envelope
{
  private String a;
  private String b;
  private String c;
  private String d;
  private String e = "com.alipay.mcpay";

  private static String f(String paramString)
  {
    return paramString;
  }

  public final String a()
  {
    return this.e;
  }

  public final void a(String paramString)
  {
    this.e = paramString;
  }

  public final String b()
  {
    return this.a;
  }

  public final void b(String paramString)
  {
    this.a = paramString;
  }

  public final String c()
  {
    return this.b;
  }

  public final void c(String paramString)
  {
    this.b = paramString;
  }

  public final String d()
  {
    return this.c;
  }

  public final void d(String paramString)
  {
    this.c = paramString;
  }

  public final String e()
  {
    return this.d;
  }

  public final void e(String paramString)
  {
    this.d = paramString;
  }

  public String toString()
  {
    return "requestUrl = " + this.a + ", namespace = " + this.b + ", apiName = " + this.c + ", apiVersion = " + this.d;
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     com.alipay.sdk.data.Envelope
 * JD-Core Version:    0.6.2
 */