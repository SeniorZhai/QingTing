package com.taobao.newxp.common.a.a;

import android.content.Context;
import android.content.res.Resources;
import android.util.DisplayMetrics;
import android.view.MotionEvent;

public class g
{
  int a = 0;
  int b = 0;
  int c = 0;
  int d = 0;
  int e = 0;
  int f = 0;
  int g = 0;
  int h = 0;
  long i = 0L;
  private long j = 0L;

  private static int a(Context paramContext, float paramFloat)
  {
    if (paramContext != null)
      return (int)(paramFloat / paramContext.getResources().getDisplayMetrics().density + 0.5F);
    return 0;
  }

  public int a()
  {
    if (this.a != 0);
    for (this.g /= this.a; ; this.g = this.e)
      return this.g;
  }

  public void a(Context paramContext, MotionEvent paramMotionEvent)
  {
    if (paramMotionEvent != null);
    switch (paramMotionEvent.getAction())
    {
    default:
      return;
    case 1:
      this.c = a(paramContext, paramMotionEvent.getX());
      this.d = a(paramContext, paramMotionEvent.getY());
      this.i = (System.currentTimeMillis() - this.j);
      this.b += 1;
      return;
    case 0:
      this.e = a(paramContext, paramMotionEvent.getX());
      this.f = a(paramContext, paramMotionEvent.getY());
      this.j = System.currentTimeMillis();
      return;
    case 2:
    }
    this.a += 1;
    this.g += a(paramContext, paramMotionEvent.getX());
    this.h += a(paramContext, paramMotionEvent.getY());
  }

  public int b()
  {
    if (this.a != 0);
    for (this.h /= this.a; ; this.h = this.f)
      return this.h;
  }

  public void c()
  {
    this.j = 0L;
    this.a = 0;
    this.b = 0;
    this.c = 0;
    this.d = 0;
    this.e = 0;
    this.f = 0;
    this.g = 0;
    this.h = 0;
    this.i = 0L;
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     com.taobao.newxp.common.a.a.g
 * JD-Core Version:    0.6.2
 */