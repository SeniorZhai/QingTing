package cn.com.iresearch.mapptracker.fm.dao;

public final class b
{
  private String a = "http://m.irs01.com/rec/se?_iwt_t=i&sv=2";
  private String b = "http://m.irs01.com/rec/cl?_iwt_t=i&sv=2";
  private long c = 1440L;
  private String d = "http://m.irs01.com/cfg/appkey-";
  private int e = 30;
  private int f = 0;
  private int g = 100;
  private String h;

  public final String a()
  {
    return this.a;
  }

  public final void a(int paramInt)
  {
    this.e = paramInt;
  }

  public final void a(long paramLong)
  {
    this.c = paramLong;
  }

  public final void a(String paramString)
  {
    this.a = paramString;
  }

  public final String b()
  {
    return this.b;
  }

  public final void b(int paramInt)
  {
    this.f = paramInt;
  }

  public final void b(String paramString)
  {
    this.b = paramString;
  }

  public final long c()
  {
    return this.c;
  }

  public final void c(int paramInt)
  {
    this.g = paramInt;
  }

  public final void c(String paramString)
  {
    this.d = paramString;
  }

  public final String d()
  {
    return this.d;
  }

  public final void d(String paramString)
  {
    this.h = paramString;
  }

  public final int e()
  {
    return this.e;
  }

  public final int f()
  {
    return this.f;
  }

  public final int g()
  {
    return this.g;
  }

  public final String h()
  {
    return this.h;
  }

  public final String toString()
  {
    return "MATConfig [SendDataUrl=" + this.a + ", SendClientUrl=" + this.b + ", ConfigExpireTime=" + this.c + ", ConfigUrl=" + this.d + ", LimitInterval=" + this.e + ", sendMode=" + this.f + ", LimitCount=" + this.g + "]";
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     cn.com.iresearch.mapptracker.fm.dao.b
 * JD-Core Version:    0.6.2
 */