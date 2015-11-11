package com.taobao.newxp.common.c;

import com.taobao.newxp.common.Log;

public class c extends a
{
  int b;
  int c;

  public c a(int paramInt)
  {
    this.b = paramInt;
    return this;
  }

  protected void a(e parame)
  {
    if (a())
    {
      parame.e = this.b;
      parame.f = this.c;
      return;
    }
    Log.e(a, "Integrity verification failed！");
  }

  protected boolean a()
  {
    return true;
  }

  public c b(int paramInt)
  {
    this.c = paramInt;
    return this;
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     com.taobao.newxp.common.c.c
 * JD-Core Version:    0.6.2
 */