package com.taobao.newxp.common.c;

import com.taobao.newxp.common.Log;

public class b extends a
{
  int b;
  int c;
  long d;
  long e;

  public b a(int paramInt)
  {
    this.b = paramInt;
    return this;
  }

  public b a(long paramLong)
  {
    this.d = paramLong;
    return this;
  }

  protected void a(e parame)
  {
    if (a())
    {
      parame.i = this.d;
      parame.g = this.b;
      parame.h = this.c;
      parame.j = this.e;
      return;
    }
    Log.e(a, "Integrity verification failed！");
  }

  protected boolean a()
  {
    return (this.d != 0L) && (this.b != 0) && (this.c != 0) && (this.e != 0L);
  }

  public b b(int paramInt)
  {
    this.c = paramInt;
    return this;
  }

  public b b(long paramLong)
  {
    this.e = paramLong;
    return this;
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     com.taobao.newxp.common.c.b
 * JD-Core Version:    0.6.2
 */