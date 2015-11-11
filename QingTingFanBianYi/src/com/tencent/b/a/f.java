package com.tencent.b.a;

import com.tencent.b.c.c.a;

public abstract class f
{
  private volatile int a = 63;
  private volatile boolean b = true;
  private e c = e.a;

  public f()
  {
    this(63, true, e.a);
  }

  public f(int paramInt, boolean paramBoolean, e parame)
  {
    a(paramInt);
    a(paramBoolean);
    a(parame);
  }

  public void a(int paramInt)
  {
    this.a = paramInt;
  }

  protected abstract void a(int paramInt, Thread paramThread, long paramLong, String paramString1, String paramString2, Throwable paramThrowable);

  public void a(e parame)
  {
    this.c = parame;
  }

  public void a(boolean paramBoolean)
  {
    this.b = paramBoolean;
  }

  public void b(int paramInt, Thread paramThread, long paramLong, String paramString1, String paramString2, Throwable paramThrowable)
  {
    if ((d()) && (c.a.a(this.a, paramInt)))
      a(paramInt, paramThread, paramLong, paramString1, paramString2, paramThrowable);
  }

  public boolean d()
  {
    return this.b;
  }

  public e e()
  {
    return this.c;
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     com.tencent.b.a.f
 * JD-Core Version:    0.6.2
 */