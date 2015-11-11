package com.alipay.sdk.auth;

public class APAuthInfo
{
  private String a;
  private String b;
  private String c;
  private String d;

  private APAuthInfo(String paramString1, String paramString2, String paramString3)
  {
    this(paramString1, paramString2, paramString3, (byte)0);
  }

  private APAuthInfo(String paramString1, String paramString2, String paramString3, byte paramByte)
  {
    this.a = paramString1;
    this.b = paramString2;
    this.d = paramString3;
    this.c = null;
  }

  public final String a()
  {
    return this.a;
  }

  public final String b()
  {
    return this.b;
  }

  public final String c()
  {
    return this.c;
  }

  public final String d()
  {
    return this.d;
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     com.alipay.sdk.auth.APAuthInfo
 * JD-Core Version:    0.6.2
 */