package com.a.a;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.AndroidRuntimeException;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.AnimationUtils;
import android.view.animation.Interpolator;
import android.view.animation.LinearInterpolator;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

public class i extends a
{
  private static ThreadLocal<a> h = new ThreadLocal();
  private static final ThreadLocal<ArrayList<i>> i = new ThreadLocal()
  {
    protected ArrayList<i> a()
    {
      return new ArrayList();
    }
  };
  private static final ThreadLocal<ArrayList<i>> j = new ThreadLocal()
  {
    protected ArrayList<i> a()
    {
      return new ArrayList();
    }
  };
  private static final ThreadLocal<ArrayList<i>> k = new ThreadLocal()
  {
    protected ArrayList<i> a()
    {
      return new ArrayList();
    }
  };
  private static final ThreadLocal<ArrayList<i>> l = new ThreadLocal()
  {
    protected ArrayList<i> a()
    {
      return new ArrayList();
    }
  };
  private static final ThreadLocal<ArrayList<i>> m = new ThreadLocal()
  {
    protected ArrayList<i> a()
    {
      return new ArrayList();
    }
  };
  private static final Interpolator n = new AccelerateDecelerateInterpolator();
  private static long x = 10L;
  private Interpolator A = n;
  private ArrayList<b> B = null;
  long b;
  long c = -1L;
  int d = 0;
  boolean e = false;
  g[] f;
  HashMap<String, g> g;
  private boolean o = false;
  private int p = 0;
  private float q = 0.0F;
  private boolean r = false;
  private long s;
  private boolean t = false;
  private boolean u = false;
  private long v = 300L;
  private long w = 0L;
  private int y = 0;
  private int z = 1;

  public static i a(float[] paramArrayOfFloat)
  {
    i locali = new i();
    locali.b(paramArrayOfFloat);
    return locali;
  }

  private void a(boolean paramBoolean)
  {
    if (Looper.myLooper() == null)
      throw new AndroidRuntimeException("Animators may only be run on Looper threads");
    this.o = paramBoolean;
    this.p = 0;
    this.d = 0;
    this.u = true;
    this.r = false;
    ((ArrayList)j.get()).add(this);
    Object localObject;
    int i2;
    int i1;
    if (this.w == 0L)
    {
      b(e());
      this.d = 0;
      this.t = true;
      if (this.a != null)
      {
        localObject = (ArrayList)this.a.clone();
        i2 = ((ArrayList)localObject).size();
        i1 = 0;
      }
    }
    while (true)
    {
      if (i1 >= i2)
      {
        a locala = (a)h.get();
        localObject = locala;
        if (locala == null)
        {
          localObject = new a(null);
          h.set(localObject);
        }
        ((a)localObject).sendEmptyMessage(0);
        return;
      }
      ((a.a)((ArrayList)localObject).get(i1)).onAnimationStart(this);
      i1 += 1;
    }
  }

  private boolean e(long paramLong)
  {
    if (!this.r)
    {
      this.r = true;
      this.s = paramLong;
    }
    long l1;
    do
    {
      return false;
      l1 = paramLong - this.s;
    }
    while (l1 <= this.w);
    this.b = (paramLong - (l1 - this.w));
    this.d = 1;
    return true;
  }

  private void n()
  {
    ((ArrayList)i.get()).remove(this);
    ((ArrayList)j.get()).remove(this);
    ((ArrayList)k.get()).remove(this);
    this.d = 0;
    ArrayList localArrayList;
    int i2;
    int i1;
    if ((this.t) && (this.a != null))
    {
      localArrayList = (ArrayList)this.a.clone();
      i2 = localArrayList.size();
      i1 = 0;
    }
    while (true)
    {
      if (i1 >= i2)
      {
        this.t = false;
        this.u = false;
        return;
      }
      ((a.a)localArrayList.get(i1)).onAnimationEnd(this);
      i1 += 1;
    }
  }

  private void o()
  {
    d();
    ((ArrayList)i.get()).add(this);
    ArrayList localArrayList;
    int i2;
    int i1;
    if ((this.w > 0L) && (this.a != null))
    {
      localArrayList = (ArrayList)this.a.clone();
      i2 = localArrayList.size();
      i1 = 0;
    }
    while (true)
    {
      if (i1 >= i2)
        return;
      ((a.a)localArrayList.get(i1)).onAnimationStart(this);
      i1 += 1;
    }
  }

  public i a(long paramLong)
  {
    if (paramLong < 0L)
      throw new IllegalArgumentException("Animators cannot have negative duration: " + paramLong);
    this.v = paramLong;
    return this;
  }

  public void a()
  {
    a(false);
  }

  void a(float paramFloat)
  {
    paramFloat = this.A.getInterpolation(paramFloat);
    this.q = paramFloat;
    int i2 = this.f.length;
    int i1 = 0;
    if (i1 >= i2)
      if (this.B != null)
      {
        i2 = this.B.size();
        i1 = 0;
      }
    while (true)
    {
      if (i1 >= i2)
      {
        return;
        this.f[i1].a(paramFloat);
        i1 += 1;
        break;
      }
      ((b)this.B.get(i1)).a(this);
      i1 += 1;
    }
  }

  public void a(Interpolator paramInterpolator)
  {
    if (paramInterpolator != null)
    {
      this.A = paramInterpolator;
      return;
    }
    this.A = new LinearInterpolator();
  }

  public void a(b paramb)
  {
    if (this.B == null)
      this.B = new ArrayList();
    this.B.add(paramb);
  }

  public void a(g[] paramArrayOfg)
  {
    int i2 = paramArrayOfg.length;
    this.f = paramArrayOfg;
    this.g = new HashMap(i2);
    int i1 = 0;
    while (true)
    {
      if (i1 >= i2)
      {
        this.e = false;
        return;
      }
      g localg = paramArrayOfg[i1];
      this.g.put(localg.c(), localg);
      i1 += 1;
    }
  }

  public void b()
  {
    Iterator localIterator;
    if ((this.d != 0) || (((ArrayList)j.get()).contains(this)) || (((ArrayList)k.get()).contains(this)))
      if ((this.t) && (this.a != null))
        localIterator = ((ArrayList)this.a.clone()).iterator();
    while (true)
    {
      if (!localIterator.hasNext())
      {
        n();
        return;
      }
      ((a.a)localIterator.next()).onAnimationCancel(this);
    }
  }

  public void b(long paramLong)
  {
    d();
    long l1 = AnimationUtils.currentAnimationTimeMillis();
    if (this.d != 1)
    {
      this.c = paramLong;
      this.d = 2;
    }
    this.b = (l1 - paramLong);
    d(l1);
  }

  public void b(float[] paramArrayOfFloat)
  {
    if ((paramArrayOfFloat == null) || (paramArrayOfFloat.length == 0))
      return;
    if ((this.f == null) || (this.f.length == 0))
      a(new g[] { g.a("", paramArrayOfFloat) });
    while (true)
    {
      this.e = false;
      return;
      this.f[0].a(paramArrayOfFloat);
    }
  }

  public void c(long paramLong)
  {
    this.w = paramLong;
  }

  void d()
  {
    int i2;
    int i1;
    if (!this.e)
    {
      i2 = this.f.length;
      i1 = 0;
    }
    while (true)
    {
      if (i1 >= i2)
      {
        this.e = true;
        return;
      }
      this.f[i1].b();
      i1 += 1;
    }
  }

  boolean d(long paramLong)
  {
    boolean bool2 = false;
    if (this.d == 0)
    {
      this.d = 1;
      if (this.c >= 0L)
        break label58;
      this.b = paramLong;
    }
    while (true)
      switch (this.d)
      {
      default:
        return false;
        label58: this.b = (paramLong - this.c);
        this.c = -1L;
      case 1:
      case 2:
      }
    float f1;
    int i1;
    label145: boolean bool1;
    if (this.v > 0L)
    {
      f1 = (float)(paramLong - this.b) / (float)this.v;
      if (f1 < 1.0F)
        break label282;
      if ((this.p >= this.y) && (this.y != -1))
        break label270;
      if (this.a != null)
      {
        int i2 = this.a.size();
        i1 = 0;
        if (i1 < i2)
          break label237;
      }
      if (this.z == 2)
      {
        if (!this.o)
          break label264;
        bool1 = false;
        label170: this.o = bool1;
      }
      this.p += (int)f1;
      f1 %= 1.0F;
      this.b += this.v;
      bool1 = bool2;
    }
    while (true)
    {
      float f2 = f1;
      if (this.o)
        f2 = 1.0F - f1;
      a(f2);
      return bool1;
      f1 = 1.0F;
      break;
      label237: ((a.a)this.a.get(i1)).onAnimationRepeat(this);
      i1 += 1;
      break label145;
      label264: bool1 = true;
      break label170;
      label270: f1 = Math.min(f1, 1.0F);
      bool1 = true;
      continue;
      label282: bool1 = bool2;
    }
  }

  public long e()
  {
    if ((!this.e) || (this.d == 0))
      return 0L;
    return AnimationUtils.currentAnimationTimeMillis() - this.b;
  }

  public float f()
  {
    return this.q;
  }

  public i g()
  {
    i locali = (i)super.c();
    Object localObject;
    int i2;
    int i1;
    if (this.B != null)
    {
      localObject = this.B;
      locali.B = new ArrayList();
      i2 = ((ArrayList)localObject).size();
      i1 = 0;
      if (i1 < i2);
    }
    else
    {
      locali.c = -1L;
      locali.o = false;
      locali.p = 0;
      locali.e = false;
      locali.d = 0;
      locali.r = false;
      localObject = this.f;
      if (localObject != null)
      {
        i2 = localObject.length;
        locali.f = new g[i2];
        locali.g = new HashMap(i2);
        i1 = 0;
      }
    }
    while (true)
    {
      if (i1 >= i2)
      {
        return locali;
        locali.B.add((b)((ArrayList)localObject).get(i1));
        i1 += 1;
        break;
      }
      g localg = localObject[i1].a();
      locali.f[i1] = localg;
      locali.g.put(localg.c(), localg);
      i1 += 1;
    }
  }

  public String toString()
  {
    String str1 = "ValueAnimator@" + Integer.toHexString(hashCode());
    String str2 = str1;
    int i1;
    if (this.f != null)
      i1 = 0;
    while (true)
    {
      if (i1 >= this.f.length)
      {
        str2 = str1;
        return str2;
      }
      str1 = str1 + "\n    " + this.f[i1].toString();
      i1 += 1;
    }
  }

  private static class a extends Handler
  {
    public void handleMessage(Message paramMessage)
    {
      ArrayList localArrayList1 = (ArrayList)i.h().get();
      ArrayList localArrayList2 = (ArrayList)i.i().get();
      int i;
      switch (paramMessage.what)
      {
      default:
        return;
      case 0:
        paramMessage = (ArrayList)i.j().get();
        if ((localArrayList1.size() > 0) || (localArrayList2.size() > 0))
        {
          i = 0;
          label76: if (paramMessage.size() > 0)
            break;
        }
        break;
      case 1:
      }
      while (true)
      {
        long l = AnimationUtils.currentAnimationTimeMillis();
        Object localObject = (ArrayList)i.k().get();
        paramMessage = (ArrayList)i.l().get();
        int k = localArrayList2.size();
        int j = 0;
        label118: if (j >= k)
        {
          k = ((ArrayList)localObject).size();
          if (k > 0)
          {
            j = 0;
            if (j < k)
              break label345;
            ((ArrayList)localObject).clear();
          }
          j = localArrayList1.size();
          k = 0;
          if (k < j)
            break label384;
          if (paramMessage.size() > 0)
            j = 0;
        }
        while (true)
        {
          label140: label161: if (j >= paramMessage.size())
          {
            paramMessage.clear();
            if ((i == 0) || ((localArrayList1.isEmpty()) && (localArrayList2.isEmpty())))
              break;
            sendEmptyMessageDelayed(1, Math.max(0L, i.m() - (AnimationUtils.currentAnimationTimeMillis() - l)));
            return;
            localObject = (ArrayList)paramMessage.clone();
            paramMessage.clear();
            k = ((ArrayList)localObject).size();
            j = 0;
            if (j >= k)
              break label76;
            i locali = (i)((ArrayList)localObject).get(j);
            if (i.a(locali) == 0L)
              i.b(locali);
            while (true)
            {
              j += 1;
              break;
              localArrayList2.add(locali);
            }
            locali = (i)localArrayList2.get(j);
            if (i.a(locali, l))
              ((ArrayList)localObject).add(locali);
            j += 1;
            break label118;
            label345: locali = (i)((ArrayList)localObject).get(j);
            i.b(locali);
            i.a(locali, true);
            localArrayList2.remove(locali);
            j += 1;
            break label140;
            localObject = (i)localArrayList1.get(k);
            if (((i)localObject).d(l))
              paramMessage.add(localObject);
            if (localArrayList1.size() == j)
            {
              k += 1;
              break label161;
            }
            j -= 1;
            paramMessage.remove(localObject);
            break label161;
          }
          label384: i.c((i)paramMessage.get(j));
          j += 1;
        }
        i = 1;
        break label76;
        i = 1;
      }
    }
  }

  public static abstract interface b
  {
    public abstract void a(i parami);
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     com.a.a.i
 * JD-Core Version:    0.6.2
 */