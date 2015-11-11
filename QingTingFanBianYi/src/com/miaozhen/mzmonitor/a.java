package com.miaozhen.mzmonitor;

import android.content.ContentValues;

final class a
{
  private String a;
  private String b;
  private String c;
  private String d;
  private String e;
  private String f;
  private long g;
  private int h;

  public a()
  {
  }

  public a(String paramString)
  {
    this.b = paramString;
    this.g = System.currentTimeMillis();
    this.h = 0;
    this.a = c.a.a(paramString + this.g + (int)(Math.random() * 10000.0D));
  }

  public final String a()
  {
    return this.b;
  }

  public final void a(int paramInt)
  {
    this.h = paramInt;
  }

  public final void a(long paramLong)
  {
    this.g = paramLong;
  }

  public final void a(String paramString)
  {
    this.b = paramString;
  }

  public final String b()
  {
    return this.c;
  }

  public final void b(String paramString)
  {
    this.c = paramString;
  }

  public final String c()
  {
    return this.d;
  }

  public final void c(String paramString)
  {
    this.d = paramString;
  }

  public final String d()
  {
    return this.e;
  }

  public final void d(String paramString)
  {
    this.e = paramString;
  }

  public final String e()
  {
    return this.a;
  }

  public final void e(String paramString)
  {
    this.a = paramString;
  }

  public final long f()
  {
    return this.g;
  }

  public final void f(String paramString)
  {
    this.f = paramString;
  }

  public final long g()
  {
    return this.g / 1000L;
  }

  public final int h()
  {
    return this.h;
  }

  public final String i()
  {
    return this.f;
  }

  public final ContentValues j()
  {
    ContentValues localContentValues = new ContentValues();
    localContentValues.put("cacheId", this.a);
    localContentValues.put("url", this.b);
    localContentValues.put("timestamp", Long.valueOf(this.g));
    localContentValues.put("times", Integer.valueOf(this.h));
    return localContentValues;
  }

  public final String toString()
  {
    return "cacheId: " + this.a + ", url: " + this.b + ", eventType:" + this.e + ", userId: " + this.d + ", panelId: " + this.c + ", timestamp: " + this.g + ", times: " + this.h;
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     com.miaozhen.mzmonitor.a
 * JD-Core Version:    0.6.2
 */