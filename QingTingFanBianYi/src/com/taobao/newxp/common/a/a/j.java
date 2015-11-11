package com.taobao.newxp.common.a.a;

import android.content.Context;
import android.view.MotionEvent;

public class j
  implements l
{
  private int a;
  private int b;
  private g c = new g();
  private long d = 0L;
  private long e = 0L;

  public int a()
  {
    return this.a;
  }

  public void a(Context paramContext, MotionEvent paramMotionEvent)
  {
    if (paramMotionEvent != null)
      this.c.a(paramContext, paramMotionEvent);
  }

  public void a(o paramo)
  {
    switch (paramo.a)
    {
    case 2:
    default:
    case 1:
    case 3:
    }
    do
    {
      do
        return;
      while (paramo.b != 1);
      this.d = (System.currentTimeMillis() / 1000L);
      return;
    }
    while (paramo.b != 4);
    this.a = this.c.c;
    this.b = this.c.d;
    this.e = (System.currentTimeMillis() / 1000L);
  }

  public void b()
  {
    this.a = -1;
    this.b = -1;
    this.c.c();
    this.d = 0L;
    this.e = 0L;
  }

  public int c()
  {
    return this.b;
  }

  public g d()
  {
    return this.c;
  }

  public long e()
  {
    return this.d;
  }

  public long f()
  {
    return this.e;
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     com.taobao.newxp.common.a.a.j
 * JD-Core Version:    0.6.2
 */