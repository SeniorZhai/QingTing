package com.taobao.munion.base.caches;

import java.io.InputStream;

public class w extends i
{
  public static final long b = 604800000L;
  public static final long c = 1800000L;
  private static final long f = 1800000L;
  public long d;
  public InputStream e;

  public static w b(i parami)
  {
    w localw = new w();
    if (parami != null)
    {
      localw.d(parami.g());
      localw.a(parami.a());
      localw.c(parami.h());
      localw.a(parami.b());
      localw.c(parami.d());
      localw.b(parami.c());
    }
    return localw;
  }

  public boolean j()
  {
    long l = a() - System.currentTimeMillis();
    return (l < 0L) || ((e.g(d())) && (l > 1800000L));
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     com.taobao.munion.base.caches.w
 * JD-Core Version:    0.6.2
 */