package com.intowow.sdk.e;

import java.util.Comparator;

public class a
{
  private b.c a = null;
  private int b = 3;
  private String c = null;
  private String d = null;
  private String e = null;
  private long f = -1L;
  private long g = 0L;
  private boolean h = false;
  private long i = 0L;
  private int j = 0;
  private String k = null;
  private b.b l = null;

  public int a()
  {
    return this.a.a();
  }

  public void a(int paramInt)
  {
    this.j = paramInt;
  }

  public void a(long paramLong)
  {
    this.f = paramLong;
  }

  public void a(b.b paramb)
  {
    this.l = paramb;
  }

  public void a(b.c paramc)
  {
    this.a = paramc;
  }

  public void a(String paramString)
  {
    this.k = paramString;
  }

  public int b()
  {
    return this.j;
  }

  public void b(int paramInt)
  {
    this.b = paramInt;
  }

  public void b(long paramLong)
  {
    this.g = paramLong;
  }

  public void b(String paramString)
  {
    this.c = paramString;
  }

  public String c()
  {
    return this.k;
  }

  public void c(long paramLong)
  {
    this.i = paramLong;
  }

  public void c(String paramString)
  {
    this.e = paramString;
  }

  public int d()
  {
    return this.b;
  }

  public String e()
  {
    return this.c;
  }

  public String f()
  {
    return this.e;
  }

  public long g()
  {
    return this.f;
  }

  public boolean h()
  {
    return this.f != -1L;
  }

  public long i()
  {
    return this.g;
  }

  public b.b j()
  {
    return this.l;
  }

  public long k()
  {
    return this.i;
  }

  public static class a
    implements Comparator<a>
  {
    public int a(a parama1, a parama2)
    {
      if (parama1.a() != parama2.a())
        return parama2.a() - parama1.a();
      return (int)(parama1.k() - parama2.k());
    }
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     com.intowow.sdk.e.a
 * JD-Core Version:    0.6.2
 */