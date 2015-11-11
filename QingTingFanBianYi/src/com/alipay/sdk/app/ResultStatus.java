package com.alipay.sdk.app;

public enum ResultStatus
{
  private int g;
  private String h;

  private ResultStatus(int arg3, String arg4)
  {
    int j;
    this.g = j;
    Object localObject;
    this.h = localObject;
  }

  public static ResultStatus a(int paramInt)
  {
    switch (paramInt)
    {
    default:
      return b;
    case 9000:
      return a;
    case 6001:
      return c;
    case 6002:
      return d;
    case 4001:
      return e;
    case 8000:
    }
    return f;
  }

  private void a(String paramString)
  {
    this.h = paramString;
  }

  private void b(int paramInt)
  {
    this.g = paramInt;
  }

  public final int a()
  {
    return this.g;
  }

  public final String b()
  {
    return this.h;
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     com.alipay.sdk.app.ResultStatus
 * JD-Core Version:    0.6.2
 */