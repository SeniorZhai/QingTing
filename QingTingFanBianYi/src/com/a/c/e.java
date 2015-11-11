package com.a.c;

import android.view.View;
import android.view.animation.Interpolator;
import com.a.a.a.a;
import com.a.a.i;
import com.a.a.i.b;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;

class e extends b
{
  ArrayList<b> a = new ArrayList();
  private final com.a.c.a.a b;
  private final WeakReference<View> c;
  private long d;
  private boolean e = false;
  private long f = 0L;
  private boolean g = false;
  private Interpolator h;
  private boolean i = false;
  private a.a j = null;
  private a k = new a(null);
  private Runnable l = new Runnable()
  {
    public void run()
    {
      e.a(e.this);
    }
  };
  private HashMap<com.a.a.a, c> m = new HashMap();

  e(View paramView)
  {
    this.c = new WeakReference(paramView);
    this.b = com.a.c.a.a.a(paramView);
  }

  private float a(int paramInt)
  {
    switch (paramInt)
    {
    default:
      return 0.0F;
    case 1:
      return this.b.g();
    case 2:
      return this.b.h();
    case 16:
      return this.b.b();
    case 32:
      return this.b.c();
    case 64:
      return this.b.d();
    case 4:
      return this.b.e();
    case 8:
      return this.b.f();
    case 128:
      return this.b.i();
    case 256:
      return this.b.j();
    case 512:
    }
    return this.b.a();
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
    if (this.m.size() > 0)
    {
      localIterator = this.m.keySet().iterator();
      if (localIterator.hasNext())
        break label107;
      localObject = null;
    }
    while (true)
    {
      if (localObject != null)
        ((com.a.a.a)localObject).b();
      localObject = new b(paramInt, paramFloat1, paramFloat2);
      this.a.add(localObject);
      localObject = (View)this.c.get();
      if (localObject != null)
      {
        ((View)localObject).removeCallbacks(this.l);
        ((View)localObject).post(this.l);
      }
      return;
      label107: localObject = (com.a.a.a)localIterator.next();
      c localc = (c)this.m.get(localObject);
      if ((!localc.a(paramInt)) || (localc.a != 0))
        break;
    }
  }

  private void b()
  {
    i locali = i.a(new float[] { 1.0F });
    ArrayList localArrayList = (ArrayList)this.a.clone();
    this.a.clear();
    int i2 = localArrayList.size();
    int n = 0;
    int i1 = 0;
    while (true)
    {
      if (n >= i2)
      {
        this.m.put(locali, new c(i1, localArrayList));
        locali.a(this.k);
        locali.a(this.k);
        if (this.g)
          locali.c(this.f);
        if (this.e)
          locali.a(this.d);
        if (this.i)
          locali.a(this.h);
        locali.a();
        return;
      }
      i1 |= ((b)localArrayList.get(n)).a;
      n += 1;
    }
  }

  private void b(int paramInt, float paramFloat)
  {
    a(paramInt, a(paramInt), paramFloat);
  }

  private void c(int paramInt, float paramFloat)
  {
    switch (paramInt)
    {
    default:
      return;
    case 1:
      this.b.g(paramFloat);
      return;
    case 2:
      this.b.h(paramFloat);
      return;
    case 16:
      this.b.b(paramFloat);
      return;
    case 32:
      this.b.c(paramFloat);
      return;
    case 64:
      this.b.d(paramFloat);
      return;
    case 4:
      this.b.e(paramFloat);
      return;
    case 8:
      this.b.f(paramFloat);
      return;
    case 128:
      this.b.i(paramFloat);
      return;
    case 256:
      this.b.j(paramFloat);
      return;
    case 512:
    }
    this.b.a(paramFloat);
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
    this.e = true;
    this.d = paramLong;
    return this;
  }

  public b a(Interpolator paramInterpolator)
  {
    this.i = true;
    this.h = paramInterpolator;
    return this;
  }

  public b a(a.a parama)
  {
    this.j = parama;
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
      parami = (e.c)e.c(e.this).get(parami);
      Object localObject;
      if ((parami.a & 0x1FF) != 0)
      {
        localObject = (View)e.d(e.this).get();
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
          parami = (View)e.d(e.this).get();
          if (parami != null)
            parami.invalidate();
          return;
        }
        localObject = (e.b)parami.get(i);
        float f2 = ((e.b)localObject).b;
        float f3 = ((e.b)localObject).c;
        e.a(e.this, ((e.b)localObject).a, f2 + f3 * f1);
        i += 1;
      }
    }

    public void onAnimationCancel(com.a.a.a parama)
    {
      if (e.b(e.this) != null)
        e.b(e.this).onAnimationCancel(parama);
    }

    public void onAnimationEnd(com.a.a.a parama)
    {
      if (e.b(e.this) != null)
        e.b(e.this).onAnimationEnd(parama);
      e.c(e.this).remove(parama);
      if (e.c(e.this).isEmpty())
        e.a(e.this, null);
    }

    public void onAnimationRepeat(com.a.a.a parama)
    {
      if (e.b(e.this) != null)
        e.b(e.this).onAnimationRepeat(parama);
    }

    public void onAnimationStart(com.a.a.a parama)
    {
      if (e.b(e.this) != null)
        e.b(e.this).onAnimationStart(parama);
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
    ArrayList<e.b> b;

    c(int paramInt, ArrayList<e.b> paramArrayList)
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
        if (((e.b)this.b.get(i)).a == paramInt)
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
 * Qualified Name:     com.a.c.e
 * JD-Core Version:    0.6.2
 */