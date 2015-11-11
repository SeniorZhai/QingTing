package com.a.a;

import android.view.animation.Interpolator;
import java.util.ArrayList;
import java.util.Arrays;

class f
{
  int a;
  e b;
  e c;
  Interpolator d;
  ArrayList<e> e;
  h f;

  public f(e[] paramArrayOfe)
  {
    this.a = paramArrayOfe.length;
    this.e = new ArrayList();
    this.e.addAll(Arrays.asList(paramArrayOfe));
    this.b = ((e)this.e.get(0));
    this.c = ((e)this.e.get(this.a - 1));
    this.d = this.c.c();
  }

  public static f a(float[] paramArrayOfFloat)
  {
    int i = 1;
    int j = paramArrayOfFloat.length;
    e.a[] arrayOfa = new e.a[Math.max(j, 2)];
    if (j == 1)
    {
      arrayOfa[0] = ((e.a)e.a(0.0F));
      arrayOfa[1] = ((e.a)e.a(1.0F, paramArrayOfFloat[0]));
    }
    while (true)
    {
      return new c(arrayOfa);
      arrayOfa[0] = ((e.a)e.a(0.0F, paramArrayOfFloat[0]));
      while (i < j)
      {
        arrayOfa[i] = ((e.a)e.a(i / (j - 1), paramArrayOfFloat[i]));
        i += 1;
      }
    }
  }

  public Object a(float paramFloat)
  {
    float f1;
    if (this.a == 2)
    {
      f1 = paramFloat;
      if (this.d != null)
        f1 = this.d.getInterpolation(paramFloat);
      return this.f.a(f1, this.b.a(), this.c.a());
    }
    Object localObject2;
    if (paramFloat <= 0.0F)
    {
      localObject1 = (e)this.e.get(1);
      localObject2 = ((e)localObject1).c();
      f1 = paramFloat;
      if (localObject2 != null)
        f1 = ((Interpolator)localObject2).getInterpolation(paramFloat);
      paramFloat = this.b.b();
      paramFloat = (f1 - paramFloat) / (((e)localObject1).b() - paramFloat);
      return this.f.a(paramFloat, this.b.a(), ((e)localObject1).a());
    }
    if (paramFloat >= 1.0F)
    {
      localObject1 = (e)this.e.get(this.a - 2);
      localObject2 = this.c.c();
      f1 = paramFloat;
      if (localObject2 != null)
        f1 = ((Interpolator)localObject2).getInterpolation(paramFloat);
      paramFloat = ((e)localObject1).b();
      paramFloat = (f1 - paramFloat) / (this.c.b() - paramFloat);
      return this.f.a(paramFloat, ((e)localObject1).a(), this.c.a());
    }
    Object localObject1 = this.b;
    int i = 1;
    while (true)
    {
      if (i >= this.a)
        return this.c.a();
      localObject2 = (e)this.e.get(i);
      if (paramFloat < ((e)localObject2).b())
      {
        Interpolator localInterpolator = ((e)localObject2).c();
        f1 = paramFloat;
        if (localInterpolator != null)
          f1 = localInterpolator.getInterpolation(paramFloat);
        paramFloat = ((e)localObject1).b();
        paramFloat = (f1 - paramFloat) / (((e)localObject2).b() - paramFloat);
        return this.f.a(paramFloat, ((e)localObject1).a(), ((e)localObject2).a());
      }
      i += 1;
      localObject1 = localObject2;
    }
  }

  public void a(h paramh)
  {
    this.f = paramh;
  }

  public f b()
  {
    ArrayList localArrayList = this.e;
    int j = this.e.size();
    e[] arrayOfe = new e[j];
    int i = 0;
    while (true)
    {
      if (i >= j)
        return new f(arrayOfe);
      arrayOfe[i] = ((e)localArrayList.get(i)).d();
      i += 1;
    }
  }

  public String toString()
  {
    String str = " ";
    int i = 0;
    while (true)
    {
      if (i >= this.a)
        return str;
      str = str + ((e)this.e.get(i)).a() + "  ";
      i += 1;
    }
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     com.a.a.f
 * JD-Core Version:    0.6.2
 */