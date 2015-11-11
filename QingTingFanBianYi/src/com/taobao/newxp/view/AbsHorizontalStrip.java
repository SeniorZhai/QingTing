package com.taobao.newxp.view;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import android.view.ViewParent;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

public abstract class AbsHorizontalStrip extends ViewGroup
{
  private static String g = AbsHorizontalStrip.class.getSimpleName();
  protected Context a;
  protected final float b;
  protected int c = 10;
  protected float d = 0.0F;
  protected float e;
  HashMap<Integer, Float[]> f = new HashMap();
  private boolean h;
  private float i;
  private a j;
  private final int k;
  private VelocityTracker l;
  private float m;
  private b n;

  public AbsHorizontalStrip(Context paramContext, AttributeSet paramAttributeSet, int paramInt)
  {
    super(paramContext, paramAttributeSet);
    this.a = paramContext;
    this.k = ViewConfiguration.get(paramContext).getScaledTouchSlop();
    float f1 = paramContext.getResources().getDisplayMetrics().density;
    ViewConfiguration.getScrollFriction();
    this.b = 5.0F;
    setWillNotDraw(false);
  }

  private void a(float paramFloat1, float paramFloat2)
  {
    this.i = paramFloat1;
    this.m = 0.0F;
  }

  private void a(float paramFloat1, float paramFloat2, boolean paramBoolean)
  {
  }

  private void b(float paramFloat1, float paramFloat2)
  {
    paramFloat2 = this.i - paramFloat1;
    this.i = paramFloat1;
    Log.i(g, "onTouchEventMove action= touchX=" + paramFloat1 + "  getScrollPosition=" + a() + " offsetX=" + paramFloat2);
    int i1 = getWidth();
    if (this.e > i1)
      e(a() - paramFloat2);
  }

  private void c()
  {
    if (this.l == null)
      this.l = VelocityTracker.obtain();
    this.l.clear();
  }

  private void c(float paramFloat1, float paramFloat2)
  {
    int i2 = 0;
    paramFloat1 = Math.abs(a());
    Object localObject = this.f.keySet().iterator();
    while (((Iterator)localObject).hasNext())
    {
      i1 = ((Integer)((Iterator)localObject).next()).intValue();
      Float[] arrayOfFloat = (Float[])this.f.get(Integer.valueOf(i1));
      if ((arrayOfFloat[0].floatValue() <= paramFloat1) && (arrayOfFloat[1].floatValue() >= paramFloat1))
      {
        paramFloat2 = arrayOfFloat[1].floatValue();
        float f1 = arrayOfFloat[0].floatValue();
        if (paramFloat1 - arrayOfFloat[0].floatValue() <= (paramFloat2 - f1) / 2.0F)
          onChildAcquiredFocus(i1);
        else if (this.f.containsKey(Integer.valueOf(i1 + 1)))
          onChildAcquiredFocus(i1 + 1);
      }
    }
    localObject = getVisibleChild();
    if (this.n != null)
      if (((List)localObject).size() <= 0)
        break label249;
    label249: for (int i1 = ((Integer)((List)localObject).get(0)).intValue(); ; i1 = 0)
    {
      if (((List)localObject).size() > 0)
        i2 = ((Integer)((List)localObject).get(((List)localObject).size() - 1)).intValue();
      this.n.onChildVisibleChanged(i1, i2);
      return;
    }
  }

  private void d()
  {
    if (this.l == null)
      this.l = VelocityTracker.obtain();
  }

  private void e()
  {
    if (this.l != null)
    {
      this.l.recycle();
      this.l = null;
    }
  }

  private void e(float paramFloat)
  {
    int i1 = -(int)d(paramFloat);
    Log.i(g, "updateScrollPosition toX=" + i1 + "  total=" + this.e);
    scrollTo(i1, 0);
    invalidate();
  }

  private void f()
  {
    ViewParent localViewParent = getParent();
    if (localViewParent != null)
      localViewParent.requestDisallowInterceptTouchEvent(true);
  }

  protected float a()
  {
    return -getScrollX();
  }

  protected float a(float paramFloat)
  {
    if (this.e == 0.0F);
    float f1;
    do
    {
      return paramFloat;
      f1 = paramFloat;
      if (paramFloat < 0.0F)
        f1 = paramFloat + this.e;
      paramFloat = f1;
    }
    while (f1 < this.e);
    return f1 - this.e;
  }

  abstract float a(int paramInt);

  protected a a(float paramFloat, long paramLong)
  {
    this.d = a();
    return new a(paramFloat, paramLong);
  }

  abstract float b(float paramFloat);

  abstract float c(float paramFloat);

  protected float d(float paramFloat)
  {
    float f1 = paramFloat;
    if (paramFloat > 0.0F)
      f1 = 0.0F;
    int i1 = (int)(this.e - getWidth());
    Log.i(g, "exSize " + i1 + "   " + f1);
    paramFloat = f1;
    if (-f1 > i1)
      paramFloat = -i1;
    return paramFloat;
  }

  public void draw(Canvas paramCanvas)
  {
    super.draw(paramCanvas);
  }

  public int getChildByX(float paramFloat)
    throws IllegalArgumentException
  {
    Iterator localIterator = this.f.keySet().iterator();
    while (localIterator.hasNext())
    {
      int i1 = ((Integer)localIterator.next()).intValue();
      Float[] arrayOfFloat = (Float[])this.f.get(Integer.valueOf(i1));
      if ((arrayOfFloat[0].floatValue() <= paramFloat) && (arrayOfFloat[1].floatValue() >= paramFloat))
        return i1;
    }
    throw new IllegalArgumentException();
  }

  public int getLayoutMargin()
  {
    return this.c;
  }

  public List<Integer> getVisibleChild()
  {
    float f1 = a();
    ArrayList localArrayList = new ArrayList();
    f1 = Math.abs(f1);
    float f2 = f1 + getWidth();
    Iterator localIterator = this.f.keySet().iterator();
    while (localIterator.hasNext())
    {
      int i1 = ((Integer)localIterator.next()).intValue();
      Float[] arrayOfFloat = (Float[])this.f.get(Integer.valueOf(i1));
      if (((arrayOfFloat[0].floatValue() >= f1) && (arrayOfFloat[0].floatValue() < f2)) || ((arrayOfFloat[1].floatValue() >= f1) && (arrayOfFloat[1].floatValue() < f2)))
        localArrayList.add(Integer.valueOf(i1));
    }
    return localArrayList;
  }

  public void onChildAcquiredFocus(int paramInt)
  {
    runScrollAnimation(-(a(paramInt) + a()) / 0.1F, 0.1F);
  }

  public boolean onInterceptTouchEvent(MotionEvent paramMotionEvent)
  {
    boolean bool = false;
    float f1 = paramMotionEvent.getY();
    float f2 = paramMotionEvent.getX();
    int i1 = paramMotionEvent.getAction() & 0xFF;
    Log.i(g, "onInterceptTouchEvent action=" + i1 + " touchX=" + f2 + " touchY" + f1);
    switch (i1)
    {
    default:
      bool = super.onInterceptTouchEvent(paramMotionEvent);
    case 0:
    case 2:
    case 1:
    }
    do
    {
      do
      {
        return bool;
        this.i = f2;
        this.m = 0.0F;
        a(f2, f1);
        return false;
        this.h = false;
        this.m += Math.abs(this.i - f2);
        this.i = f2;
        if (this.m > this.k)
          this.h = true;
      }
      while (!this.h);
      onTouchEvent(paramMotionEvent);
      return true;
    }
    while (!this.h);
    this.h = false;
    onTouchEvent(paramMotionEvent);
    return true;
  }

  public boolean onTouchEvent(MotionEvent paramMotionEvent)
  {
    int i1 = paramMotionEvent.getAction();
    float f1 = paramMotionEvent.getY();
    float f2 = paramMotionEvent.getX();
    Log.i(g, "onTouch action=" + i1 + "   " + (i1 & 0xFF) + " touchX=" + f2 + " touchY=" + f1);
    d();
    this.l.addMovement(paramMotionEvent);
    switch (i1 & 0xFF)
    {
    default:
    case 2:
    case 0:
    case 1:
    }
    while (true)
    {
      return true;
      b(f2, f1);
      continue;
      a(f2, f1);
      continue;
      c(f2, f1);
    }
  }

  public void runScrollAnimation(float paramFloat1, float paramFloat2)
  {
    this.j = a(paramFloat1, ()Math.abs(1000.0F * paramFloat2));
    this.j.a();
  }

  public void setChildVisibleChanged(b paramb)
  {
    this.n = paramb;
  }

  public void setLayoutMargin(int paramInt)
  {
    this.c = paramInt;
  }

  class a
    implements Runnable
  {
    private float b;
    private long c = System.nanoTime();
    private float d;

    public a(float paramLong, long arg3)
    {
      Object localObject;
      this.b = ((float)localObject / 1000.0F);
      this.d = paramLong;
    }

    private void b()
    {
      AbsHorizontalStrip.this.post(this);
    }

    public void a()
    {
      b();
    }

    public void run()
    {
      float f2 = (float)(System.nanoTime() - this.c) / 1.0E+009F;
      float f1 = f2;
      if (f2 > this.b)
        f1 = this.b;
      f2 = this.d;
      float f3 = AbsHorizontalStrip.this.d;
      f2 = Math.round(f2 * f1) + f3;
      Log.d(AbsHorizontalStrip.b(), "do animation " + f2 + "  " + f1);
      AbsHorizontalStrip.a(AbsHorizontalStrip.this, f2);
      if (f1 < this.b)
        b();
    }
  }

  public static abstract interface b
  {
    public abstract void onChildVisibleChanged(int paramInt1, int paramInt2);
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     com.taobao.newxp.view.AbsHorizontalStrip
 * JD-Core Version:    0.6.2
 */