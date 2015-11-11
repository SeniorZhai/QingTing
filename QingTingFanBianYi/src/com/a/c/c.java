package com.a.c;

import android.view.View;
import android.view.animation.Interpolator;
import com.a.a.a;
import com.a.a.a.a;
import com.a.a.i;
import com.a.a.i.b;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;

class c extends b
{
  ArrayList<b> a = new ArrayList();
  private final WeakReference<View> b;
  private long c;
  private boolean d = false;
  private long e = 0L;
  private boolean f = false;
  private Interpolator g;
  private boolean h = false;
  private a.a i = null;
  private a j = new a(null);
  private Runnable k = new Runnable()
  {
    public void run()
    {
      c.a(c.this);
    }
  };
  private HashMap<a, c> l = new HashMap();

  c(View paramView)
  {
    this.b = new WeakReference(paramView);
  }

  private float a(int paramInt)
  {
    View localView = (View)this.b.get();
    if (localView != null);
    switch (paramInt)
    {
    default:
      return 0.0F;
    case 1:
      return localView.getTranslationX();
    case 2:
      return localView.getTranslationY();
    case 16:
      return localView.getRotation();
    case 32:
      return localView.getRotationX();
    case 64:
      return localView.getRotationY();
    case 4:
      return localView.getScaleX();
    case 8:
      return localView.getScaleY();
    case 128:
      return localView.getX();
    case 256:
      return localView.getY();
    case 512:
    }
    return localView.getAlpha();
  }

  private void a(int paramInt, float paramFloat)
  {
    float f1 = a(paramInt);
    a(paramInt, f1, paramFloat - f1);
  }

  private void a(int paramInt, float paramFloat1, float paramFloat2)
  {
    Iterator localIterator;
    Object localObject;
    if (this.l.size() > 0)
    {
      localIterator = this.l.keySet().iterator();
      if (localIterator.hasNext())
        break label107;
      localObject = null;
    }
    while (true)
    {
      if (localObject != null)
        ((a)localObject).b();
      localObject = new b(paramInt, paramFloat1, paramFloat2);
      this.a.add(localObject);
      localObject = (View)this.b.get();
      if (localObject != null)
      {
        ((View)localObject).removeCallbacks(this.k);
        ((View)localObject).post(this.k);
      }
      return;
      label107: localObject = (a)localIterator.next();
      c localc = (c)this.l.get(localObject);
      if ((!localc.a(paramInt)) || (localc.a != 0))
        break;
    }
  }

  private void b()
  {
    i locali = i.a(new float[] { 1.0F });
    ArrayList localArrayList = (ArrayList)this.a.clone();
    this.a.clear();
    int i1 = localArrayList.size();
    int m = 0;
    int n = 0;
    while (true)
    {
      if (m >= i1)
      {
        this.l.put(locali, new c(n, localArrayList));
        locali.a(this.j);
        locali.a(this.j);
        if (this.f)
          locali.c(this.e);
        if (this.d)
          locali.a(this.c);
        if (this.h)
          locali.a(this.g);
        locali.a();
        return;
      }
      n |= ((b)localArrayList.get(m)).a;
      m += 1;
    }
  }

  private void b(int paramInt, float paramFloat)
  {
    a(paramInt, a(paramInt), paramFloat);
  }

  private void c(int paramInt, float paramFloat)
  {
    View localView = (View)this.b.get();
    if (localView != null);
    switch (paramInt)
    {
    default:
      return;
    case 1:
      localView.setTranslationX(paramFloat);
      return;
    case 2:
      localView.setTranslationY(paramFloat);
      return;
    case 16:
      localView.setRotation(paramFloat);
      return;
    case 32:
      localView.setRotationX(paramFloat);
      return;
    case 64:
      localView.setRotationY(paramFloat);
      return;
    case 4:
      localView.setScaleX(paramFloat);
      return;
    case 8:
      localView.setScaleY(paramFloat);
      return;
    case 128:
      localView.setX(paramFloat);
      return;
    case 256:
      localView.setY(paramFloat);
      return;
    case 512:
    }
    localView.setAlpha(paramFloat);
  }

  public b a(float paramFloat)
  {
    a(16, paramFloat);
    return this;
  }

  public b a(long paramLong)
  {
    if (paramLong < 0L)
      throw new IllegalArgumentException("Animators cannot have negative duration: " + paramLong);
    this.d = true;
    this.c = paramLong;
    return this;
  }

  public b a(Interpolator paramInterpolator)
  {
    this.h = true;
    this.g = paramInterpolator;
    return this;
  }

  public b a(a.a parama)
  {
    this.i = parama;
    return this;
  }

  public void a()
  {
    b();
  }

  public b b(float paramFloat)
  {
    a(1, paramFloat);
    return this;
  }

  public b c(float paramFloat)
  {
    b(1, paramFloat);
    return this;
  }

  public b d(float paramFloat)
  {
    a(2, paramFloat);
    return this;
  }

  public b e(float paramFloat)
  {
    a(4, paramFloat);
    return this;
  }

  public b f(float paramFloat)
  {
    a(8, paramFloat);
    return this;
  }

  public b g(float paramFloat)
  {
    a(512, paramFloat);
    return this;
  }

  private class a
    implements a.a, i.b
  {
    private a()
    {
    }

    public void a(i parami)
    {
      float f1 = parami.f();
      parami = (c.c)c.c(c.this).get(parami);
      Object localObject;
      if ((parami.a & 0x1FF) != 0)
      {
        localObject = (View)c.d(c.this).get();
        if (localObject != null)
          ((View)localObject).invalidate();
      }
      parami = parami.b;
      int j;
      int i;
      if (parami != null)
      {
        j = parami.size();
        i = 0;
      }
      while (true)
      {
        if (i >= j)
        {
          parami = (View)c.d(c.this).get();
          if (parami != null)
            parami.invalidate();
          return;
        }
        localObject = (c.b)parami.get(i);
        float f2 = ((c.b)localObject).b;
        float f3 = ((c.b)localObject).c;
        c.a(c.this, ((c.b)localObject).a, f2 + f3 * f1);
        i += 1;
      }
    }

    public void onAnimationCancel(a parama)
    {
      if (c.b(c.this) != null)
        c.b(c.this).onAnimationCancel(parama);
    }

    public void onAnimationEnd(a parama)
    {
      if (c.b(c.this) != null)
        c.b(c.this).onAnimationEnd(parama);
      c.c(c.this).remove(parama);
      if (c.c(c.this).isEmpty())
        c.a(c.this, null);
    }

    public void onAnimationRepeat(a parama)
    {
      if (c.b(c.this) != null)
        c.b(c.this).onAnimationRepeat(parama);
    }

    public void onAnimationStart(a parama)
    {
      if (c.b(c.this) != null)
        c.b(c.this).onAnimationStart(parama);
    }
  }

  private static class b
  {
    int a;
    float b;
    float c;

    b(int paramInt, float paramFloat1, float paramFloat2)
    {
      this.a = paramInt;
      this.b = paramFloat1;
      this.c = paramFloat2;
    }
  }

  private static class c
  {
    int a;
    ArrayList<c.b> b;

    c(int paramInt, ArrayList<c.b> paramArrayList)
    {
      this.a = paramInt;
      this.b = paramArrayList;
    }

    boolean a(int paramInt)
    {
      int j;
      int i;
      if (((this.a & paramInt) != 0) && (this.b != null))
      {
        j = this.b.size();
        i = 0;
      }
      while (true)
      {
        if (i >= j)
          return false;
        if (((c.b)this.b.get(i)).a == paramInt)
        {
          this.b.remove(i);
          this.a &= (paramInt ^ 0xFFFFFFFF);
          return true;
        }
        i += 1;
      }
    }
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     com.a.c.c
 * JD-Core Version:    0.6.2
 */