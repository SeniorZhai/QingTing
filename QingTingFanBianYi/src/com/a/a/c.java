package com.a.a;

import android.view.animation.Interpolator;
import java.util.ArrayList;

class c extends f
{
  private float g;
  private float h;
  private float i;
  private boolean j = true;

  public c(e.a[] paramArrayOfa)
  {
    super(paramArrayOfa);
  }

  public c a()
  {
    ArrayList localArrayList = this.e;
    int m = this.e.size();
    e.a[] arrayOfa = new e.a[m];
    int k = 0;
    while (true)
    {
      if (k >= m)
        return new c(arrayOfa);
      arrayOfa[k] = ((e.a)((e)localArrayList.get(k)).d());
      k += 1;
    }
  }

  public Object a(float paramFloat)
  {
    return Float.valueOf(b(paramFloat));
  }

  public float b(float paramFloat)
  {
    int k = 1;
    float f1;
    if (this.a == 2)
    {
      if (this.j)
      {
        this.j = false;
        this.g = ((e.a)this.e.get(0)).e();
        this.h = ((e.a)this.e.get(1)).e();
        this.i = (this.h - this.g);
      }
      f1 = paramFloat;
      if (this.d != null)
        f1 = this.d.getInterpolation(paramFloat);
      if (this.f == null)
        return this.g + this.i * f1;
      return ((Number)this.f.a(f1, Float.valueOf(this.g), Float.valueOf(this.h))).floatValue();
    }
    e.a locala;
    float f2;
    float f3;
    float f4;
    float f5;
    if (paramFloat <= 0.0F)
    {
      localObject = (e.a)this.e.get(0);
      locala = (e.a)this.e.get(1);
      f2 = ((e.a)localObject).e();
      f3 = locala.e();
      f4 = ((e.a)localObject).b();
      f5 = locala.b();
      localObject = locala.c();
      f1 = paramFloat;
      if (localObject != null)
        f1 = ((Interpolator)localObject).getInterpolation(paramFloat);
      paramFloat = (f1 - f4) / (f5 - f4);
      if (this.f == null)
        return paramFloat * (f3 - f2) + f2;
      return ((Number)this.f.a(paramFloat, Float.valueOf(f2), Float.valueOf(f3))).floatValue();
    }
    if (paramFloat >= 1.0F)
    {
      localObject = (e.a)this.e.get(this.a - 2);
      locala = (e.a)this.e.get(this.a - 1);
      f2 = ((e.a)localObject).e();
      f3 = locala.e();
      f4 = ((e.a)localObject).b();
      f5 = locala.b();
      localObject = locala.c();
      f1 = paramFloat;
      if (localObject != null)
        f1 = ((Interpolator)localObject).getInterpolation(paramFloat);
      paramFloat = (f1 - f4) / (f5 - f4);
      if (this.f == null)
        return paramFloat * (f3 - f2) + f2;
      return ((Number)this.f.a(paramFloat, Float.valueOf(f2), Float.valueOf(f3))).floatValue();
    }
    for (Object localObject = (e.a)this.e.get(0); ; localObject = locala)
    {
      if (k >= this.a)
        return ((Number)((e)this.e.get(this.a - 1)).a()).floatValue();
      locala = (e.a)this.e.get(k);
      if (paramFloat < locala.b())
      {
        Interpolator localInterpolator = locala.c();
        f1 = paramFloat;
        if (localInterpolator != null)
          f1 = localInterpolator.getInterpolation(paramFloat);
        paramFloat = (f1 - ((e.a)localObject).b()) / (locala.b() - ((e.a)localObject).b());
        f1 = ((e.a)localObject).e();
        f2 = locala.e();
        if (this.f == null)
          return (f2 - f1) * paramFloat + f1;
        return ((Number)this.f.a(paramFloat, Float.valueOf(f1), Float.valueOf(f2))).floatValue();
      }
      k += 1;
    }
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     com.a.a.c
 * JD-Core Version:    0.6.2
 */