package com.umeng.analytics.social;

public class a extends RuntimeException
{
  private static final long b = -4656673116019167471L;
  protected int a = 5000;
  private String c = "";

  public a(int paramInt, String paramString)
  {
    super(paramString);
    this.a = paramInt;
    this.c = paramString;
  }

  public a(String paramString)
  {
    super(paramString);
    this.c = paramString;
  }

  public a(String paramString, Throwable paramThrowable)
  {
    super(paramString, paramThrowable);
    this.c = paramString;
  }

  public int a()
  {
    return this.a;
  }

  public String getMessage()
  {
    return this.c;
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     com.umeng.analytics.social.a
 * JD-Core Version:    0.6.2
 */