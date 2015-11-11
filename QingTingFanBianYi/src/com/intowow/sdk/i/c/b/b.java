package com.intowow.sdk.i.c.b;

import com.intowow.sdk.i.c.c.c.a;

public class b
  implements com.intowow.sdk.i.c.c.b
{
  private long a = 0L;
  private long b = 0L;
  private a c = null;

  public b(long paramLong, a parama)
  {
    this.b = paramLong;
    this.c = parama;
  }

  public static b a(long paramLong, c.a parama)
  {
    return new b(paramLong, new a()
    {
      public void a()
      {
        if (b.this != null)
          b.this.g();
      }
    });
  }

  public void a()
  {
    this.a = 0L;
  }

  public void a(int paramInt)
  {
    long l = System.currentTimeMillis();
    if (this.a == 0L)
      this.a = l;
    while ((l - this.a < this.b) || (this.c == null))
      return;
    this.c.a();
  }

  public void e()
  {
  }

  public void f()
  {
  }

  public static abstract interface a
  {
    public abstract void a();
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     com.intowow.sdk.i.c.b.b
 * JD-Core Version:    0.6.2
 */